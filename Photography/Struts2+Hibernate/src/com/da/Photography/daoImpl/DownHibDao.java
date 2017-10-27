package com.da.Photography.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.da.Photography.dao.DownDaoInterface;
import com.da.Photography.dto.Down;
import com.da.Photography.dto.User;

public class DownHibDao extends BaseHibDao implements DownDaoInterface {
	/**
	 * 判断积分是否足够下载图片
	 * @param u_id
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public boolean queryPriceSupport(int u_id, String pid) throws SQLException {
		boolean flag = false;
		String sql = "Select (t.U_PRICE-p.P_PRICE) p From PA_USER t, PA_PICTURE p Where t.U_ID = ? And p.P_ID = ?";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setInteger(0, u_id);
		sq.setString(1, pid);
		List list = sq.list();
		int result = 0;
		if(list.size()>0){
			result = Integer.valueOf(list.get(0).toString());
		}
		if(result >= 0) {
			flag = true;
		}
		return flag;
	}
	/**
	 * 扣除用户积分
	 * @param u_id
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int deductUserPrice(int u_id, String pid) throws SQLException {
		String sql = "update pa_user set u_price=u_price-(Select P_PRICE From PA_PICTURE Where P_ID = ?) where u_id=?";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setInteger(0, u_id);
		sq.setString(1, pid);
		int result = sq.executeUpdate();
		return result;
	}
	/**
	 * 增加图片存在专辑的创建者的积分
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int addUserPrice(String pid) throws SQLException {
		String sql = "update pa_user set "
				+ "u_price=u_price+(Select P_PRICE From PA_PICTURE Where PA_PICTURE.P_ID = ?) "
				+ "where u_id=(Select a.U_ID From PA_PICTURE p, PA_ALBUMS a Where a.A_ID = p.A_ID And p.P_ID = ?)";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setString(0, pid);
		sq.setString(1, pid);
		int result = sq.executeUpdate();
		return result;
	}
	/**
	 * 更新记录
	 * @param u_id
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int recordDown(int u_id, String pid) throws SQLException {
		String sql1 = "Select P_PRICE From PA_PICTURE Where P_ID = " + pid;
		SQLQuery sq1 = session.createSQLQuery(sql1);
		String update = "";
		List list = sq1.list();
		if(list.size()>0) {
			update = "-" + list.get(0);
		}
		String sql = "insert into pa_down values((select nvl(max(d_id),0)+1 from pa_down),"
				+ "?,sysdate,2,?,?)";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setInteger(0, u_id);
		sq.setString(1, update);
		sq.setString(2, pid);
		int result = sq.executeUpdate();
		return result;
	}
	/**
	 * 更新记录
	 * @param u_id
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int recordDown2(String pid) throws SQLException {
		String sql1 = "Select p.P_PRICE,a.u_id From PA_PICTURE p,PA_ALBUMS a Where a.a_id=p.a_id and p.P_ID = " + pid;
		String update = "";
		String u_id = "";
		SQLQuery sq1 = session.createSQLQuery(sql1);
		Iterator it = sq1.list().iterator();
		if(it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			update = "+" + obj[0];
			u_id = obj[1].toString();
		}
		String sql = "insert into pa_down values((select nvl(max(d_id),0)+1 from pa_down),"
				+ "?,sysdate,3,?,?)";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setString(0, u_id);
		sq.setString(1, update);
		sq.setString(2, pid);
		int result = sq.executeUpdate();
		return result;
	}
	/**
	 * 查找下载记录是否存在
	 * @param u_id
	 * @param pid
	 * @return 返回结果 true 存在下载记录,false不存在记录
	 * @throws SQLException 
	 */
	@Override
	public boolean queryDownByUidAndPid(int u_id, String pid) throws SQLException {
		boolean falg = false;
		String sql = "Select t.U_ID, t.P_ID, t.D_TYPE From PA_DOWN t "
				+ "Where t.U_ID = ? "
				+ "And t.P_ID = ? "
				+ "And t.D_TYPE = 2";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setInteger(0, u_id);
		sq.setString(1, pid);
		List list = sq.list();
		if(list.size()>0) {
			falg = true;
		}
		return falg;
	}
	/**
	 * 查询记录
	 * @param type
	 * @param u_id
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public List<Down> queryAllDown(String type, int u_id) throws SQLException {
		List<Down> downs = new ArrayList<>();
		String sql = "select * from PA_DOWN t";
		if(type.equals("1")) {
			sql += " where u_id=" + u_id;
		}
		sql += " order by D_id DESC ";
		SQLQuery sq = session.createSQLQuery(sql);
		Iterator it = sq.list().iterator();
		while(it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			Down d = new Down();
			d.setD_id(Integer.valueOf(obj[0].toString()));
			d.getUser().setU_id(Integer.valueOf(obj[1].toString()));
			d.setD_date((Date) obj[2]);
			d.setD_type(Integer.valueOf(obj[3].toString()));
			d.setD_update(obj[4].toString());
			if(obj[5] != null){
				d.getPic().setP_id(Integer.valueOf(obj[5].toString()));
			}
			downs.add(d);
		}
		return downs;
	}
	/**
	 * 查询用户信息通过用户编号
	 * @param uid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public User queryUserByuid(int uid) throws SQLException {
		User user = null;
		String sql = "select * from PA_USER where u_id=" + uid;
		SQLQuery sq = session.createSQLQuery(sql);
		sq.addScalar("u_id", Hibernate.INTEGER);
		sq.addScalar("u_name", Hibernate.STRING);
		sq.addScalar("u_uname", Hibernate.STRING);
		sq.addScalar("u_pwd", Hibernate.STRING);
		sq.addScalar("u_phone", Hibernate.STRING);
		sq.addScalar("u_email", Hibernate.STRING);
		sq.addScalar("u_price", Hibernate.INTEGER);
		sq.addScalar("u_role", Hibernate.STRING);
		sq.addScalar("u_balance", Hibernate.DOUBLE);
		sq.addScalar("u_signday", Hibernate.INTEGER);
		sq.addScalar("u_signdate", Hibernate.DATE);
		sq.setResultTransformer(Transformers.aliasToBean(User.class));
		List<User> list = sq.list();
		if(list.size()>0) {
			user = list.get(0);
		}
		return user;
	}

}
