package com.da.Photography.biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.da.Photography.dto.User;
import com.da.Photography.dao.UserDao;
import com.da.Photography.util.Log;

public class UserBiz {
	
	/**
	 * 注册
	 * @param user 注册用户信息
	 * @return 注册结果 true 成功  false 失败
	 */
	public boolean register(User user) {
		boolean flag = true;
		UserDao uDao = new UserDao();
		uDao.beginTran();
		try {
			int result = uDao.addUser(user);
			if(result != 0)
				flag = true;
			uDao.commitTran();
		} catch (SQLException e) {
			uDao.rollbackTran();
			Log.LOGGER.debug("注册失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return flag;
	}
	/**
	 * 登录
	 * @param uname 用户名
	 * @param pwd 密码
	 * @return 返回登录用户信息  user为null时登录失败
	 */
	public User login(String uname, String pwd) {
		User user = null;
		UserDao uDao = new UserDao();
		uDao.beginTran();
		try {
			user = uDao.queryUserByUnameAndPwd(uname,pwd);
			uDao.commitTran();
		} catch (SQLException e) {
			uDao.rollbackTran();
			Log.LOGGER.debug("登录操作失败 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return user;
	}
	
	/**
	 * 检测用户名是否存在
	 * @param uname 用户名
	 * @return 返回是否存在的结果
	 */
	public boolean detecUserName(String uname) {
		boolean flag = true;
		UserDao uDao = new UserDao();
		uDao.beginTran();
		try {
			flag = uDao.queryUserUnameByUname(uname);
		} catch (SQLException e) {
			uDao.rollbackTran();
			Log.LOGGER.debug("检测用户名失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return flag;
	}
	
	public int signIn(int u_id, int u_price) {
		int result = 0;
		int price = u_price;
		UserDao uDao = new UserDao();
		uDao.beginTran();
		try {
			result = uDao.queryUserSignDay(u_id) + 1; //查询连续签到天数，为0时表示今日以签到,不为0时表示可以签到
			if(result != 0) {
				if(result < 5) {
					price += 5;
				}else if(result < 10) {
					price += 10;
				}else if(result < 20) {
					price += 20;
				}else if(result < 30) {
					price += 30;
				}else if(result < 50) {
					price += 50;
				}else if(result < 100) {
					price += 100;
				}else if(result < 150) {
					price += 150;
				}else if(result < 200) {
					price += 200;
				}else if(result < 250) {
					price += 250;
				}else if(result < 300) {
					price += 300;
				}else if(result < 350) {
					price += 350;
				}else if(result < 400) {
					price += 400;
				}else if(result < 450) {
					price += 450;
				}else if(result < 500) {
					price += 500;
				}else {
					price += 1000;
				}
				int flag = uDao.updateUserLignDay(u_id, result, price); //更新用户签到信息
				if(flag == 0) {
					price = u_price;
				}
			}
			uDao.commitTran();
		} catch (SQLException e) {
			uDao.rollbackTran();
			price = u_price;
			Log.LOGGER.debug("签到失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return price-u_price;
	}
	/**
	 * 查询所有用户信息
	 * @param stat 状态
	 * @return
	 */
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		UserDao uDao = new UserDao();
		uDao.beginTran();
		try {
			users = uDao.getAllUsers();
			uDao.commitTran();
		} catch (SQLException e) {
			uDao.rollbackTran();
			Log.LOGGER.debug("注册失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return users;
	}
	/**
	 * 删除用户信息,级联删除
	 * @param uid 用户编号
	 * @return
	 */
	public boolean deleteUser(String uid) {
		boolean flag = false;
		UserDao uDao = new UserDao();
		uDao.beginTran();
		try {
			int result = uDao.deleteUser(uid);
			if(result != 0) {
				flag = true;
			}
			uDao.commitTran();
		} catch (SQLException e) {
			uDao.rollbackTran();
			Log.LOGGER.debug("删除用户失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return flag;
	}
	/**
	 * 修改用户信息
	 * @param uid 用户编号
	 * @param name 用户姓名
	 * @param pwd 密码
	 * @param phone 电话
	 * @param email 邮箱
	 * @return 返回修改结果
	 */
	public boolean updateUser(String uid, String name, String pwd, String phone, String email) {
		boolean flag = false;
		UserDao uDao = new UserDao();
		uDao.beginTran();
		try {
			int result = uDao.updateUser(uid, name, pwd, phone, email);
			if(result != 0) {
				flag = true;
			}
			uDao.commitTran();
		} catch (SQLException e) {
			uDao.rollbackTran();
			Log.LOGGER.debug("删除用户失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return flag;
	}
}
