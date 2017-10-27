package com.da.Photography.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.da.Photography.dao.UserDaoInterface;
import com.da.Photography.dto.Apply;
import com.da.Photography.dto.User;
import com.da.Photography.entity.PaUser;

public class UserDao extends BaseDao implements UserDaoInterface{
	/**
	 * 添加用户
	 * @param user 用户信息
	 * @return 返回添加用户结果。0 失败
	 * @throws SQLException
	 */
	public void addUser(User user) throws Exception{
		String sql = "insert into pa_user values((select nvl(max(u_id),0)+1 from pa_user),"
				+ "?,?,?,?,?,0,0,0,null,1)";
		Object[] params = {user.getU_name(),user.getU_uname(),user.getU_pwd(),user.getU_phone(),user.getU_email()};
//		int result = executeUpdate(sql, params);
//		return result;
	}
	
	/**
	 * 查找用户通过用户名和密码
	 * @param uname 用户名
	 * @param pwd 密码
	 * @return 返回用户信息
	 * @throws SQLException
	 */
	public User queryUserByUnameAndPwd(String uname, String pwd) throws SQLException {
		User user = null;
		String sql = "select * from PA_USER where u_uname=? and u_pwd=?";
		Object[] params = {uname,pwd};
		rs = executeQuery(sql, params);
		if(rs.next()) {
			user = new User();
			user.setU_id(rs.getInt("u_id"));
			user.setU_name(rs.getString("u_name"));
			user.setU_phone(rs.getString("u_phone"));
			user.setU_uname(rs.getString("u_uname"));
			user.setU_pwd(rs.getString("u_pwd"));
			user.setU_email(rs.getString("u_email"));
			user.setU_price(rs.getInt("u_price"));
			user.setU_role(rs.getString("u_role"));
			user.setU_signday(rs.getInt("u_signday"));
			user.setU_signdate(rs.getDate("u_signdate"));
			user.setU_balance(rs.getDouble("u_balance"));
		}
		return user;
	}
	/**
	 * 查询用户通过用户名
	 * @param uname 用户名
	 * @return 返回是否找到用户
	 * @throws SQLException 
	 */
	public boolean queryUserUnameByUname(String uname) throws SQLException {
		boolean flag = true;
		String sql = "select u_id from pa_user where u_uname=?";
		Object[] params = {uname};
		rs = executeQuery(sql, params);
		if(rs.next())
			flag = false;
		return flag;
	}
	/**
	 * 通过用户编号和当前日期与上次签到日期比较找到连签天数
	 * @param u_id 用户编号
	 * @return 连签天数
	 * @throws SQLException
	 */
	public int queryUserSignDay(int u_id) throws SQLException {
		int result = 0;
		String sql = "select u_signday from pa_user where u_signdate < to_date(to_char(sysdate,'yyyy_mm_dd'),'yyyy-mm-dd') and u_id=?";
		String sql2 = "select u_signday from pa_user where u_signdate = to_date(to_char(sysdate,'yyyy_mm_dd'),'yyyy-mm-dd') and u_id=?";
		Object[] params = {u_id};
		rs = executeQuery(sql, params);
		if(rs.next()) {
			result = rs.getInt("u_signday");
		}else {
			rs = executeQuery(sql2, params);
			if(rs.next()) {
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
	public int updateUserLignDay(int u_id, int result, int price) throws SQLException {
		String sql = "update pa_user set u_signday=?,u_price=?,u_signdate=to_date(to_char(sysdate,'yyyy_mm_dd'),'yyyy-mm-dd') where u_id=?";
		Object[] params = {result,price,u_id};
		int result1 = executeUpdate(sql, params);
		return result1;
	}

	/**
	 * 查询所有用户信息
	 * @param stat
	 * @return
	 * @throws SQLException 
	 */
	public List<User> getAllUsers() throws SQLException {
		List<User> users = new ArrayList<>();
		String sql = "select * from PA_USER order by u_id ";
		rs = executeQuery(sql);
		while(rs.next()) {
			User user = new User();
			user.setU_id(rs.getInt("u_id"));
			user.setU_name(rs.getString("u_name"));
			user.setU_phone(rs.getString("u_phone"));
			user.setU_uname(rs.getString("u_uname"));
			user.setU_pwd(rs.getString("u_pwd"));
			user.setU_email(rs.getString("u_email"));
			user.setU_price(rs.getInt("u_price"));
			user.setU_role(rs.getString("u_role"));
			user.setU_signday(rs.getInt("u_signday"));
			user.setU_signdate(rs.getDate("u_signdate"));
			user.setU_balance(rs.getDouble("u_balance"));
			users.add(user);
		}
		return users;
	}
	/**
	 * 删除用户信息通过用户编号,级联删除
	 * @param uid 用户编号
	 * @return 返回删除结果
	 * @throws SQLException 
	 */
	public int deleteUser(String uid) throws SQLException {
		String sql = "delete pa_user where u_id=?";
		Object[] params = {uid};
		int result1 = executeUpdate(sql, params);
		return result1;
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
	public int updateUser(String uid, String name, String pwd, String phone, String email) throws SQLException {
		String sql = "update pa_user set u_name=?,u_pwd=?,u_phone=?,u_email=? where u_id=?";
		Object[] params = {name,pwd,phone,email,uid};
		int result1 = executeUpdate(sql, params);
		return result1;
	}
	/**
	 * 通过邮箱查询用户
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public User queryUserUnameByEmail(String email) throws SQLException {
		User user = null;
		String sql = "select u_name,u_uname,u_pwd from pa_user where u_email=?";
		Object[] params = {email};
		rs = executeQuery(sql, params);
		if(rs.next()) {
			user = new User();
			user.setU_pwd(rs.getString("u_pwd"));
			user.setU_name(rs.getString("u_name"));
			user.setU_uname(rs.getString("u_uname"));
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
	public int recordDown(int u_id, int price) throws SQLException {
		String sql = "insert into pa_down values((select nvl(max(d_id),0)+1 from pa_down),"
				+ "?,sysdate,1,?,null)";
		Object[] params = {u_id,"+"+price};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 添加用户申请权限
	 * @param u_id
	 * @return
	 * @throws SQLException
	 */
	public int applyForAdmin(int u_id) throws SQLException {
		String sql = "insert into PA_APPLYADMIN values((select nvl(max(ad_id),0)+1 from PA_APPLYADMIN),?)";
		Object[] params = {u_id};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 查询所有管理员申请
	 * @return
	 * @throws SQLException
	 */
	public List<Apply> queryAllApply() throws SQLException {
		List<Apply> applys = new ArrayList<>();
		String sql = "Select DISTINCT u.U_ID,u.U_NAME,u.U_BALANCE,u.U_SIGNDAY,u.U_SIGNDATE,u.U_PHONE,u.U_EMAIL,u.U_PRICE "
				+ "From PA_APPLYADMIN ad, PA_USER u "
				+ "Where u.U_ID = ad.U_ID AND u.U_ROLE=1";
		rs = executeQuery(sql);
		while(rs.next()) {
			Apply a = new Apply();
			a.getUser().setU_id(rs.getInt("u_id"));
			a.getUser().setU_name(rs.getString("u_name"));
			a.getUser().setU_balance(rs.getDouble("u_balance"));
			a.getUser().setU_signday(rs.getInt("u_signday"));
			a.getUser().setU_signdate(rs.getDate("u_signdate"));
			a.getUser().setU_phone(rs.getString("u_phone"));
			a.getUser().setU_email(rs.getString("u_email"));
			a.getUser().setU_price(rs.getInt("u_price"));
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
	public int rechargeUser(String uname, double num) throws SQLException {
		String sql = "update pa_user set u_balance = u_balance+? where u_uname=?";
		Object[] params = {num,uname};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 用户积分增加
	 * @param u_id
	 * @param price
	 * @return
	 * @throws SQLException
	 */
	public int updatePriceUserByid(int u_id, int price) throws SQLException {
		String sql = "update pa_user set u_price = u_price+? where u_id=?";
		Object[] params = {price,u_id};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 用户修改余额减少
	 * @param u_id
	 * @param num
	 * @return
	 * @throws SQLException 
	 */
	public int minBalance(int u_id, int num) throws SQLException {
		String sql = "update pa_user set u_balance = u_balance-? where u_id=?";
		Object[] params = {num,u_id};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 记录积分修改
	 * @param u_id
	 * @param price
	 * @return
	 * @throws SQLException 
	 */
	public int addDownByid(int u_id, int price) throws SQLException {
		String sql = "insert into pa_down values((select nvl(max(d_id),0)+1 from pa_down),"
				+ "?,sysdate,?,?,null)";
		Object[] params = {u_id,4,"+"+price};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 更新用户角色为管理员
	 * @param uid
	 * @return
	 * @throws SQLException 
	 */
	public int updateRoleUserById(String uid) throws SQLException {
		String sql = "update pa_user set u_role=0 where u_id=?";
		Object[] params = {uid};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 删除申请表用户
	 * @param uid
	 * @return
	 * @throws SQLException 
	 */
	public int deleteApplyByUId(String uid) throws SQLException {
		String sql = "delete pa_applyadmin where u_id=?";
		Object[] params = {uid};
		int result = executeUpdate(sql, params);
		return result;
	}

	@Override
	public void addUser(PaUser puser) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
