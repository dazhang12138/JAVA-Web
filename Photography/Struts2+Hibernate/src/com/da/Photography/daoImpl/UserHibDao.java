package com.da.Photography.daoImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.da.Photography.dao.UserDaoInterface;
import com.da.Photography.dto.Apply;
import com.da.Photography.dto.User;
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
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 通过用户编号和当前日期与上次签到日期比较找到连签天数
	 * @param u_id 用户编号
	 * @return 连签天数
	 * @throws SQLException
	 */
	@Override
	public int queryUserSignDay(int u_id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 查询所有用户信息
	 * @param stat
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public List<User> getAllUsers() throws SQLException {
		return null;
		// TODO Auto-generated method stub
	}
	/**
	 * 删除用户信息通过用户编号,级联删除
	 * @param uid 用户编号
	 * @return 返回删除结果
	 * @throws SQLException 
	 */
	@Override
	public int deleteUser(String uid) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
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
	public int updateUser(String uid, String name, String pwd, String phone, String email) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 通过邮箱查询用户
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	@Override
	public User queryUserUnameByEmail(String email) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 签到记录
	 * @param u_id
	 * @param price
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int recordDown(int u_id, int price) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 添加用户申请权限
	 * @param u_id
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int applyForAdmin(int u_id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 查询所有管理员申请
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Apply> queryAllApply() throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 更新用户角色为管理员
	 * @param uid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int updateRoleUserById(String uid) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 删除申请表用户
	 * @param uid
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int deleteApplyByUId(String uid) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
