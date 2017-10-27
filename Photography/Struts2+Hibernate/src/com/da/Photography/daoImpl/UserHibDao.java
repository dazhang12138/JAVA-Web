package com.da.Photography.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.da.Photography.dao.UserDaoInterface;
import com.da.Photography.dto.Apply;
import com.da.Photography.dto.User;
import com.da.Photography.entity.PaApplyadmin;
import com.da.Photography.entity.PaUser;

public class UserHibDao extends BaseHibDao implements UserDaoInterface {
	/**
	 * 添加用户
	 * @param user 用户信息
	 * @return 返回添加用户结果。0 失败
	 * @throws SQLException
	 */
	@Override
	public void addUser(PaUser puser) throws Exception{
		session.save(puser);
		session.flush();
	}
	/**
	 * 查找用户通过用户名和密码
	 * @param uname 用户名
	 * @param pwd 密码
	 * @return 返回用户信息
	 * @throws SQLException
	 */
	@Override
	public User queryUserByUnameAndPwd(String uname, String pwd) throws SQLException {
		User user = null;
		String sql = "select * from PA_USER where u_uname=? and u_pwd=?";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setString(0, uname);
		sq.setString(1, pwd);
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
		if(list.size()>0){
			user = list.get(0);
		}
		return user;
	}
	/**
	 * 查询用户通过用户名
	 * @param uname 用户名
	 * @return 返回是否找到用户
	 * @throws SQLException 
	 */
	@Override
	public boolean queryUserUnameByUname(String uname) throws SQLException {
		boolean flag = true;
		String sql = "select u_id from pa_user where u_uname=" + uname;
//		Object[] params = {uname};
		SQLQuery sq = session.createSQLQuery(sql);
		List list = sq.list();
		if(list.size()>0)
			flag = false;
		return flag;
	}
	/**
	 * 通过用户编号和当前日期与上次签到日期比较找到连签天数
	 * @param u_id 用户编号
	 * @return 连签天数
	 * @throws SQLException
	 */
	@Override
	public int queryUserSignDay(int u_id) throws SQLException {
		int result = 0;
		String sql = "select u_signday from pa_user where u_signdate < to_date(to_char(sysdate,'yyyy_mm_dd'),'yyyy-mm-dd') and u_id=" + u_id;
		String sql2 = "select u_signday from pa_user where u_signdate = to_date(to_char(sysdate,'yyyy_mm_dd'),'yyyy-mm-dd') and u_id=" + u_id;
		SQLQuery sq1 = session.createSQLQuery(sql);
		List list = sq1.list();
		if(list.size()>0) {
			result = (int) list.get(0);
		}else {
			SQLQuery sq2 = session.createSQLQuery(sql2);
			List list2 = sq2.list();
			if(list2.size()>0) {
				result = -1;
			}
		}
		return result;
	}
	/**
	 * 更新用户签到天数和积分
	 * @param u_id 用户编号
	 * @param result 签到天数
	 * @param price 积分
	 * @return 是否成功更新用户信息
	 * @throws SQLException
	 */
	@Override
	public int updateUserLignDay(int u_id, int result, int price) throws SQLException {
		String sql = "update pa_user set u_signday=?,u_price=?,u_signdate=to_date(to_char(sysdate,'yyyy_mm_dd'),'yyyy-mm-dd') where u_id=?";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setInteger(0, result);
		sq.setInteger(1, price);
		sq.setInteger(2, u_id);
		int result1 = sq.executeUpdate();
		return result1;
	}
	/**
	 * 查询所有用户信息
	 * @param stat
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public List<User> getAllUsers() throws SQLException {
		List<User> users = new ArrayList<>();
		String sql = "select * from PA_USER order by u_id ";
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
		users = sq.list();
		return users;
	}
	/**
	 * 删除用户信息通过用户编号,级联删除
	 * @param uid 用户编号
	 * @return 返回删除结果
	 * @throws SQLException 
	 */
	@Override
	public void deleteUser(String uid) throws Exception {
		PaUser pu = new PaUser(Long.valueOf(uid));
		session.delete(pu);
		session.flush();
	}
	/**
	 * 修改用户信息
	 * @param uid 用户编号
	 * @param name 用户姓名
	 * @param pwd 密码
	 * @param phone 电话
	 * @param email 邮箱
	 * @return 返回修改结果
	 * @throws SQLException 
	 */
	@Override
	public void updateUser(PaUser pu) throws SQLException {
		session.update(pu);
		session.flush();
	}
	/**
	 * 通过邮箱查询用户
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	@Override
	public User queryUserUnameByEmail(String email) throws SQLException {
		User user = null;
		String sql = "select u_name,u_uname,u_pwd from pa_user where u_email=" + email;
		SQLQuery sq = session.createSQLQuery(sql);
		List list = sq.list();
		if(list.size()>0) {
			Object[] obj = (Object[]) list.get(0);
			user = new User();
			user.setU_name(String.valueOf(obj[0]));
			user.setU_uname(String.valueOf(obj[1]));
			user.setU_pwd(String.valueOf(obj[2]));
		}
		return user;
	}
	/**
	 * 签到记录
	 * @param u_id
	 * @param price
	 * @return
	 * @throws SQLException
	 */
	@Override
	public void recordDown(int u_id, int price) throws SQLException {
		String sql = "insert into pa_down values((select nvl(max(d_id),0)+1 from pa_down),"
				+ "?,sysdate,1,?,null)";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setInteger(0, u_id);
		sq.setString(1,"+"+price );
		sq.executeUpdate();
	}
	/**
	 * 添加用户申请权限
	 * @param u_id
	 * @return
	 * @throws SQLException
	 */
	@Override
	public void applyForAdmin(int u_id) throws SQLException {
		String sql = "select nvl(max(ad_id),0) from Pa_Applyadmin";
		PaApplyadmin p = new PaApplyadmin();
		SQLQuery sq = session.createSQLQuery(sql);
		List list = sq.list();
		int max = Integer.valueOf(list.get(0).toString());
		p.setPaUser(new PaUser(u_id));
		p.setAdId(max+1);
		session.save(p);
		session.flush();
	}
	/**
	 * 查询所有管理员申请
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Apply> queryAllApply() throws SQLException {
		List<Apply> applys = new ArrayList<>();
		String sql = "Select DISTINCT u.U_ID,u.U_NAME,u.U_BALANCE,u.U_SIGNDAY,u.U_SIGNDATE,u.U_PHONE,u.U_EMAIL,u.U_PRICE "
				+ "From PA_APPLYADMIN ad, PA_USER u "
				+ "Where u.U_ID = ad.U_ID AND u.U_ROLE=1";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.addScalar("u_id", Hibernate.INTEGER);
		sq.addScalar("u_name", Hibernate.STRING);
		sq.addScalar("u_phone", Hibernate.STRING);
		sq.addScalar("u_email", Hibernate.STRING);
		sq.addScalar("u_price", Hibernate.INTEGER);
		sq.addScalar("u_balance", Hibernate.DOUBLE);
		sq.addScalar("u_signday", Hibernate.INTEGER);
		sq.addScalar("u_signdate", Hibernate.DATE);
		sq.setResultTransformer(Transformers.aliasToBean(User.class));
		List<User> list = sq.list();
		for (User user : list) {
			Apply a = new Apply();
			a.setUser(user);
			applys.add(a);
		}
		return applys;
	}
	/**
	 * 用户修改余额
	 * @param uname
	 * @param num
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int rechargeUser(String uname, double num) throws SQLException {
		String sql = "update pa_user set u_balance = u_balance+? where u_uname=?";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setDouble(0, num);
		sq.setString(1, uname);
		int result = sq.executeUpdate();
		return result;
	}
	/**
	 * 用户积分增加
	 * @param u_id
	 * @param price
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int updatePriceUserByid(int u_id, int price) throws SQLException {
		String sql = "update pa_user set u_price = u_price+? where u_id=?";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setInteger(0, price);
		sq.setInteger(1, u_id);
		int result = sq.executeUpdate();
		return result;
	}
	/**
	 * 用户修改余额减少
	 * @param u_id
	 * @param num
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int minBalance(int u_id, int num) throws SQLException {
		String sql = "update pa_user set u_balance = u_balance-? where u_id=?";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setInteger(0, num);
		sq.setInteger(1, u_id);
		int result = sq.executeUpdate();
		return result;
	}
	/**
	 * 记录积分修改
	 * @param u_id
	 * @param price
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int addDownByid(int u_id, int price) throws SQLException {
		String sql = "insert into pa_down values((select nvl(max(d_id),0)+1 from pa_down),"
				+ "?,sysdate,?,?,null)";
		SQLQuery sq = session.createSQLQuery(sql);
		sq.setInteger(0, u_id);
		sq.setInteger(1,4);
		sq.setString(2,"+"+price);
		sq.executeUpdate();
		int result = sq.executeUpdate();
		return result;
	}
	/**
	 * 更新用户角色为管理员
	 * @param uid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int updateRoleUserById(String uid) throws SQLException {
		String sql = "update pa_user set u_role=0 where u_id=" + uid;
		SQLQuery sq = session.createSQLQuery(sql);
		int result = sq.executeUpdate();
		return result;
	}
	/**
	 * 删除申请表用户
	 * @param uid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int deleteApplyByUId(String uid) throws SQLException {
		String sql = "delete pa_applyadmin where u_id=" + uid;
		SQLQuery sq = session.createSQLQuery(sql);
		int result = sq.executeUpdate();
		return result;
	}

}
