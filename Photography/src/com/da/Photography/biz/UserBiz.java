package com.da.Photography.biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.da.Photography.dto.Apply;
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
			uDao.commitTran();
		} catch (SQLException e) {
			uDao.rollbackTran();
			Log.LOGGER.debug("检测用户名失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return flag;
	}
	/**
	 * 签到
	 * @param u_id
	 * @param u_price
	 * @return
	 */
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
				}else {
					flag = uDao.recordDown(u_id, (price-u_price)); //更新用户签到信息
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
	/**
	 * 验证注册邮箱不能重复、通过邮箱找回密码
	 * @param email
	 * @return
	 */
	public User detecUserEmail(String email) {
		User user = null;
		UserDao uDao = new UserDao();
		uDao.beginTran();
		try {
			user = uDao.queryUserUnameByEmail(email);
			uDao.commitTran();
		} catch (SQLException e) {
			uDao.rollbackTran();
			Log.LOGGER.debug("检测邮箱失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return user;
	}
	/**
	 * 申请权限
	 * @param u_id
	 * @return
	 */
	public boolean applyForAdmin(int u_id) {
		boolean flag = true;
		UserDao uDao = new UserDao();
		uDao.beginTran();
		try {
			int result = uDao.applyForAdmin(u_id);
			if(result != 0)
				flag = true;
			uDao.commitTran();
		} catch (SQLException e) {
			uDao.rollbackTran();
			Log.LOGGER.debug("申请权限失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return flag;
	}
	/**
	 * 查询所有的管理员申请
	 * @return
	 */
	public List<Apply> queryAllApply() {
		List<Apply> applys = new ArrayList<>();
		UserDao uDao = new UserDao();
		uDao.beginTran();
		try {
			applys = uDao.queryAllApply();
			uDao.commitTran();
		} catch (SQLException e) {
			uDao.rollbackTran();
			Log.LOGGER.debug("查询管理员申请失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return applys;
	}
	/**
	 * 用户充值
	 * @param uname
	 * @param num
	 * @return
	 */
	public boolean rechargeUser(String uname, String num) {
		boolean flag = true;
		UserDao uDao = new UserDao();
		uDao.beginTran();
		try {
			double money = Integer.valueOf(num);
			if(money <= 10) {
				money *= 1.01;
			}else if(money <= 50) {
				money *= 1.05;
			}else if(money <= 100) {
				money *= 1.1;
			}else if(money <= 500) {
				money *= 1.15;
			}else {
				money *= 1.2;
			}
			int result = uDao.rechargeUser(uname,money);
			if(result != 0)
				flag = true;
			uDao.commitTran();
		} catch (SQLException e) {
			uDao.rollbackTran();
			Log.LOGGER.debug("充值失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return flag;
	}
	/**
	 * 用户积分兑换
	 * @param u_id
	 * @param price
	 * @param money 
	 * @return
	 */
	public boolean updatePriceUserByid(int u_id, int price, int money) {
		boolean flag = true;
		UserDao uDao = new UserDao();
		uDao.beginTran();
		try {
			int r = uDao.minBalance(u_id,money);
			int result = uDao.updatePriceUserByid(u_id,price);
			int e = uDao.addDownByid(u_id,price);
			if(result != 0 && r != 0 && e != 0)
				flag = true;
			uDao.commitTran();
		} catch (SQLException e) {
			uDao.rollbackTran();
			Log.LOGGER.debug("积分兑换失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return flag;
	}
	/**
	 * 更新用户角色为管理员
	 * @param uid
	 * @return
	 */
	public boolean updateRoleUserById(String uid) {
		boolean flag = true;
		UserDao uDao = new UserDao();
		uDao.beginTran();
		try {
			int r = uDao.updateRoleUserById(uid);
			if(r != 0)
				flag = true;
			uDao.commitTran();
		} catch (SQLException e) {
			uDao.rollbackTran();
			Log.LOGGER.debug("更新用户为管理员失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return flag;
	}
	/**
	 * 删除申请表用户
	 * @param uid
	 * @return
	 */
	public boolean deleteApplyByUId(String uid) {
		boolean flag = true;
		UserDao uDao = new UserDao();
		uDao.beginTran();
		try {
			int r = uDao.deleteApplyByUId(uid);
			if(r != 0)
				flag = true;
			uDao.commitTran();
		} catch (SQLException e) {
			uDao.rollbackTran();
			Log.LOGGER.debug("删除用户申请失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			uDao.closeAll();
		}
		return flag;
	}
}
