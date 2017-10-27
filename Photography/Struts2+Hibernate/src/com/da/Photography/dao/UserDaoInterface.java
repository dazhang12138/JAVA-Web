package com.da.Photography.dao;

import java.sql.SQLException;
import java.util.List;

import com.da.Photography.dto.Apply;
import com.da.Photography.dto.User;
import com.da.Photography.entity.PaUser;

public interface UserDaoInterface extends BaseDaoInterface{
	//添加用户
	public void addUser(PaUser puser) throws Exception;
	//查找用户通过用户名和密码
	public User queryUserByUnameAndPwd(String uname, String pwd) throws SQLException;
	//查询用户通过用户名
	public boolean queryUserUnameByUname(String uname) throws SQLException;
	//通过用户编号和当前日期与上次签到日期比较找到连签天数
	public int queryUserSignDay(int u_id) throws SQLException ;
	//更新用户签到天数和积分
	public int updateUserLignDay(int u_id, int result, int price) throws SQLException;
	// 查询所有用户信息
	public List<User> getAllUsers() throws SQLException;
	//删除用户信息通过用户编号,级联删除
	public void deleteUser(String uid) throws Exception;
	//修改用户信息
	public void updateUser(PaUser pu) throws SQLException;
	//通过邮箱查询用户
	public User queryUserUnameByEmail(String email) throws SQLException;
	//签到记录
	public void recordDown(int u_id, int price) throws SQLException;
	//添加用户申请权限
	public void applyForAdmin(int u_id) throws SQLException ;
	//查询所有管理员申请
	public List<Apply> queryAllApply() throws SQLException;
	//用户修改余额
	public int rechargeUser(String uname, double num) throws SQLException;
	//用户积分增加
	public int updatePriceUserByid(int u_id, int price) throws SQLException;
	//用户修改余额减少
	public int minBalance(int u_id, int num) throws SQLException;
	//记录积分修改
	public int addDownByid(int u_id, int price) throws SQLException;
	// 更新用户角色为管理员
	public int updateRoleUserById(String uid) throws SQLException;
	//删除申请表用户
	public int deleteApplyByUId(String uid) throws SQLException;
}

