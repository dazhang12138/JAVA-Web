package com.da.Photography.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.da.Photography.dao.UserDaoInterface;
import com.da.Photography.entity.PaApplyadmin;
import com.da.Photography.entity.PaDown;
import com.da.Photography.entity.PaUser;

@SuppressWarnings("unchecked")
public class UserHibDao extends BaseHibDao implements UserDaoInterface {
	/**
	 * 添加新用户
	 */
	@Override
	public void addUser(PaUser puser) throws Exception {
		session.save(puser);
		session.flush();
	}
	/**
	 * 查询用户信息通过用户名和密码
	 */
	@Override
	public PaUser queryUserByUnameAndPwd(String uname, String pwd) throws SQLException {
		PaUser user = null;
		String hql = "from PaUser where UUname = ? and UPwd = ?";
		Query q = session.createQuery(hql);
		q.setString(0, uname);
		q.setString(1, pwd);
		List<PaUser> list = q.list();
		if(list.size()>0){
			user = list.get(0);
		}
		return user;
	}
	/**
	 * 查询用户通过用户姓名
	 * 判断用户名是否存在
	 * true 可以注册
	 * false 以重复
	 */
	@Override
	public boolean queryUserUnameByUname(String uname) throws SQLException {
		boolean flag = true;
		String hql = "from PaUser where UUname=?";
		Query q = session.createQuery(hql);
		q.setString(0, uname);
		if(q.list().size()>0){
			flag = false;
		}
		return flag;
	}
	/**
	 * 通过用户编号和当前日期与上次签到日期比较找到连签天数
	 */
	@Override
	public int queryUserSignDay(long u_id) throws SQLException {
		int result = 0;
		String sql = "select u_signday from pa_user where u_signdate < to_date(to_char(sysdate,'yyyy_mm_dd'),'yyyy-mm-dd') and u_id=" + u_id;
		String sql2 = "select u_signday from pa_user where u_signdate = to_date(to_char(sysdate,'yyyy_mm_dd'),'yyyy-mm-dd') and u_id=" + u_id;
		SQLQuery sq = session.createSQLQuery(sql);
		List<Integer> sqlist = sq.list();
		if(sqlist.size()>0) {
			result = sqlist.get(0);
		}else {
			SQLQuery sq2 = session.createSQLQuery(sql2);
			List<Integer> sq2list = sq2.list();
			if(sq2list.size()>0) {
				result = -1;
			}
		}
		return result;
	}
	/**
	 * 更新用户签到天数和积分
	 */
	@Override
	public int updateUserLignDay(long u_id, int result, long price) throws SQLException {
		String hql = "update PaUser set USignday=?,UPrice=?,USigndate=? where UId=?";
		Query q = session.createQuery(hql);
		q.setInteger(0, result);
		q.setLong(1, price);
		q.setDate(2, new Date());
		q.setLong(3, u_id);
		int result1 = q.executeUpdate();
		return result1;
	}
	/**
	 * 获取全部用户信息
	 */
	@Override
	public List<PaUser> getAllUsers() throws SQLException {
		String hql = "from PaUser";
		Query q = session.createQuery(hql);
		List<PaUser> list = q.list();
		return list;
	}
	/**
	 * 删除用户
	 */
	@Override
	public int deleteUser(String uid) throws Exception {
		String hql = "delete PaUser where UId=" + uid;
		Query q = session.createQuery(hql);
		int result = q.executeUpdate();
		return result;
	}
	/**
	 * 修改用户信息
	 */
	@Override
	public int updateUser(PaUser pu) throws SQLException {
		String hql = "update PaUser set UName=?,UPwd=?,UPhone=?,UEmail=? where UId=?";
		Query q = session.createQuery(hql);
		q.setString(0, pu.getUName());
		q.setString(1, pu.getUPwd());
		q.setString(2, pu.getUPhone());
		q.setString(3, pu.getUEmail());
		q.setLong(4, pu.getUId());
		int result = q.executeUpdate();
		return result;
	}
	/**
	 * 查询用户信息通过邮箱
	 */
	@Override
	public PaUser queryUserUnameByEmail(String email) throws SQLException {
		String hql = "from PaUser where UEmail=?";
		Query q = session.createQuery(hql);
		q.setString(0, email);
		List<PaUser> list = q.list();
		PaUser user = null;
		if(list.size()>0){
			user = list.get(0);
		}
		return user;
	}
	/**
	 * 记录签到记录
	 */
	@Override
	public void recordDown(PaDown down) throws SQLException {
		session.save(down);
		session.flush();
	}
	/**
	 * 用户申请管理员
	 */
	@Override
	public void applyForAdmin(PaApplyadmin pa) throws SQLException {
		session.save(pa);
		session.flush();
	}
	/**
	 * 查询所有用户申请
	 */
	@Override
	public List<PaApplyadmin> queryAllApply() throws SQLException {
		String hql = "select DISTINCT p from PaApplyadmin p";
		Query q = session.createQuery(hql);
		List<PaApplyadmin> list = q.list();
		List<PaApplyadmin> as = new ArrayList<>();
		Set<Long> set = new HashSet<>();
		
		for (PaApplyadmin a : list) {
			if(set.add(a.getPaUser().getUId())){
				as.add(a);
			}
		}
		return as;
	}
	/**
	 * 用户充值
	 */
	@Override
	public int rechargeUser(String uname, double num) throws SQLException {
		String sql = "update PaUser set UBalance = UBalance+? where UUname=?";
		Query q = session.createQuery(sql);
		q.setDouble(0, num);
		q.setString(1, uname);
		int result = q.executeUpdate();
		return result;
	}
	/**
	 * 更新用户积分
	 */
	@Override
	public int updatePriceUserByid(long u_id, long price) throws SQLException {
		String hql = "update PaUser set UPrice = UPrice+? where UId=?";
		Query q = session.createQuery(hql);
		q.setLong(0, price);
		q.setLong(1, u_id);
		int result = q.executeUpdate();
		return result;
	}
	/**
	 * 修改用户余额
	 */
	@Override
	public int minBalance(long u_id, long num) throws SQLException {
		String hql = "update PaUser set UBalance = UBalance-? where UId=?";
		Query q = session.createQuery(hql);
		q.setLong(0, num);
		q.setLong(1, u_id);
		int result = q.executeUpdate();
		return result;
	}
	/**
	 * 添加用户积分动态
	 */
	@Override
	public void addDownByid(PaDown d) throws SQLException {
		session.save(d);
		session.flush();
	}
	/**
	 * 更新用户角色
	 */
	@Override
	public int updateRoleUserById(String uid) throws SQLException {
		String hql = "update PaUser set URole=0 where UId=" + uid;
		Query q = session.createQuery(hql);
		int result = q.executeUpdate();
		return result;
	}
	/**
	 * 删除用户申请
	 */
	@Override
	public int deleteApplyByUId(String uid) throws SQLException {
		String hql = "delete PaApplyadmin where UId=" + uid;
		Query q = session.createQuery(hql);
		int result = q.executeUpdate();
		return result;
	}
	
	/**
	 * 查询用户最大id
	 */
	@Override
	public long maxUid() {
		String sql = "select nvl(max(u_id),0) from Pa_User";
		SQLQuery sq = session.createSQLQuery(sql);
		long result = Long.valueOf(sq.list().get(0).toString());
		return result;
	}
	/**
	 * 查询专辑的最大id
	 */
	@Override
	public long maxAdminid() {
		String sql = "select nvl(max(ad_id),0) from PA_APPLYADMIN";
		SQLQuery sq = session.createSQLQuery(sql);
		long result = Long.valueOf(sq.list().get(0).toString());
		return result;
	}
	/**
	 * 查询下载的最大编号
	 */
	@Override
	public long maxDownid() {
		String sql = "select nvl(max(d_id),0) from Pa_Down";
		SQLQuery sq = session.createSQLQuery(sql);
		long result = Long.valueOf(sq.list().get(0).toString());
		return result;
	}
}
