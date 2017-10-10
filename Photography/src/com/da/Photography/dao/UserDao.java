package com.da.Photography.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.da.Photography.dto.User;

public class UserDao extends BaseDao{
	/**
	 * 添加用户
	 * @param user 用户信息
	 * @return 返回添加用户结果。0 失败
	 * @throws SQLException
	 */
	public int addUser(User user) throws SQLException {
		String sql = "insert into pa_user values((select nvl(max(u_id),0)+1 from pa_user),"
				+ "?,?,?,?,?,0,0,0,null,1)";
		Object[] params = {user.getU_name(),user.getU_uname(),user.getU_pwd(),user.getU_phone(),user.getU_email()};
		int result = executeUpdate(sql, params);
		return result;
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

}
