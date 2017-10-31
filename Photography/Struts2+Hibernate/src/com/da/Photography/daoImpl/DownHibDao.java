package com.da.Photography.daoImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.da.Photography.dao.DownDaoInterface;
import com.da.Photography.entity.PaDown;
import com.da.Photography.entity.PaUser;

@SuppressWarnings("unchecked")
public class DownHibDao extends BaseHibDao implements DownDaoInterface {
	/**
	 * 查询图片下载积分是否小于用户积分
	 */
	@Override
	public boolean queryPriceSupport(int u_id, String pid) throws SQLException {
		boolean flag = false;
		String sql = "Select (t.U_PRICE-p.P_PRICE) p From PA_USER t, PA_PICTURE p Where t.U_ID = ? And p.P_ID = ?";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setInteger(0, u_id);
		sq.setString(1, pid);
		List<BigDecimal> list = sq.list();
		int result = 0;
		if(list.size()>0) {
			result = Integer.valueOf(list.get(0).toString());
		}
		if(result >= 0) {
			flag = true;
		}
		return flag;
	}
	/**
	 * 下载用户扣除图片所需下载积分
	 */
	@Override
	public int deductUserPrice(int u_id, String pid) throws SQLException {
		String sql = "update pa_user set u_price=u_price-(Select P_PRICE From PA_PICTURE Where P_ID = ?) where u_id=?";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setString(0, pid);
		sq.setInteger(1, u_id);
		int result = sq.executeUpdate();
		return result;
	}
	/**
	 * 专辑创建人增加积分
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
	 * 记录积分动向
	 */
	@Override
	public void recordDown(int u_id, String pid) throws SQLException {
		String sql1 = "Select P_PRICE From PA_PICTURE Where P_ID = " + pid;
		String update = "";
		SQLQuery sq1 = session.createSQLQuery(sql1);
		List<BigDecimal> list = sq1.list();
		if(list.size()>0) {
			update = "-" + list.get(0);
		}
		PaDown d = new PaDown(maxId()+1, new PaUser(u_id), new Date(), (short) 2, update, Long.valueOf(pid));
		session.save(d);
		session.flush();
	}
	/**
	 * 记录积分走向
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void recordDown2(String pid) throws SQLException {
		String sql1 = "Select p.P_PRICE,a.u_id From PA_PICTURE p,PA_ALBUMS a Where a.a_id=p.a_id and p.P_ID = " + pid;
		String update = "";
		String u_id = "";
		SQLQuery sq1 = session.createSQLQuery(sql1);
		List list = sq1.list();
		if(list.size()>0) {
			Object[] obj = (Object[]) list.get(0);
			update = "+" + obj[0];
			u_id = obj[1].toString();
		}
		PaDown d = new PaDown(maxId()+1, new PaUser(Long.valueOf(u_id)), new Date(), (short) 3, update, Long.valueOf(pid));
		session.save(d);
		session.flush();
	}
	/**
	 * 查询下载记录是否存在用户和图片编号匹配
	 */
	@Override
	public boolean queryDownByUidAndPid(int u_id, String pid) throws SQLException {
		boolean falg = false;
		String sql = "From PaDown t Where t.paUser.UId = ? And t.PId = ? And t.DType = 2";
		Query q = session.createQuery(sql);
		q.setInteger(0, u_id);
		q.setString(1, pid);
		if(q.list().size()>0) {
			falg = true;
		}
		return falg;
	}
	/**
	 * 查询全部积分动态
	 */
	@Override
	public List<PaDown> queryAllDown(String type, long u_id) throws SQLException {
		String hql = "from PaDown ";
		if(type.equals("1")) {
			hql += " where paUser.UId=" + u_id;
		}
		hql += " order by DId DESC ";
		Query q = session.createQuery(hql);
		List<PaDown> list = q.list();
		return list;
	}

	@Override
	public PaUser queryUserByuid(int uid) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 查询最大编号
	 */
	@Override
	public long maxId() {
		String sql = "select nvl(max(d_id),0) from Pa_Down";
		SQLQuery sq = session.createSQLQuery(sql);
		long result = Long.valueOf(sq.list().get(0).toString());
		return result;
	}
	
}
