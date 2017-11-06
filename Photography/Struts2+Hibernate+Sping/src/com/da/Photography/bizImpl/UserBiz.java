package com.da.Photography.bizImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.da.Photography.biz.UserBizInterface;
import com.da.Photography.dao.DownDaoInterface;
import com.da.Photography.dao.UserDaoInterface;
import com.da.Photography.daoImpl.DownHibDao;
import com.da.Photography.entity.PaApplyadmin;
import com.da.Photography.entity.PaDown;
import com.da.Photography.entity.PaUser;
import com.da.Photography.util.Log;

public class UserBiz implements UserBizInterface {
	private UserDaoInterface uDao;
	
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.UserBizInterface#register(com.da.Photography.entity.PaUser)
	 */
	@Override
	public boolean register(PaUser user) {
		boolean flag = true;
		try {
			user.setUId(uDao.maxUid()+1);
			user.setURole("1");
			user.setUPrice((long)0);
			user.setUBalance(new BigDecimal(0));
			user.setUSignday((long)0);
			uDao.addUser(user);
		} catch (Exception e) {
			flag = false;
			Log.LOGGER.debug("注册用户失败" + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.UserBizInterface#login(java.lang.String, java.lang.String)
	 */
	@Override
	public PaUser login(String uname, String pwd) {
		PaUser user = null;
		try {
			user = uDao.queryUserByUnameAndPwd(uname,pwd);
		} catch (SQLException e) {
			Log.LOGGER.debug("登录操作失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return user;
	}
	
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.UserBizInterface#detecUserName(java.lang.String)
	 */
	@Override
	public boolean detecUserName(String uname) {
		boolean flag = true;
		try {
			flag = uDao.queryUserUnameByUname(uname);
		} catch (SQLException e) {
			Log.LOGGER.debug("检测用户名失败 : "  +e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.UserBizInterface#signIn(long, long)
	 */
	@Override
	public long signIn(long u_id, long u_price) {
		int result = 0;
		long price = u_price;
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
					DownDaoInterface dDao = new DownHibDao();
					dDao.getConn();
					PaDown d = new PaDown(dDao.maxId()+1, new PaUser(u_id), new Date(),(short) 1, "+"+(price-u_price), null);
					uDao.recordDown(d); //更新用户签到信息签到记录
				}
			}
		} catch (SQLException e) {
			price = u_price;
			Log.LOGGER.debug("签到失败 : "  +e.getMessage());
			e.printStackTrace();
		}
		return price-u_price;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.UserBizInterface#getAllUsers()
	 */
	@Override
	public List<PaUser> getAllUsers() {
		List<PaUser> users = new ArrayList<>();
		try {
			users = uDao.getAllUsers();
		} catch (SQLException e) {
			Log.LOGGER.debug("注册失败 : "  +e.getMessage());
			e.printStackTrace();
		}
		return users;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.UserBizInterface#deleteUser(java.lang.String)
	 */
	@Override
	public boolean deleteUser(String uid) {
		boolean flag = false;
		try {
			int result = uDao.deleteUser(uid);
			if(result != 0){
				flag = true;
			}
		} catch (Exception e) {
			Log.LOGGER.debug("删除用户失败 : "  +e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.UserBizInterface#updateUser(com.da.Photography.entity.PaUser)
	 */
	@Override
	public boolean updateUser(PaUser pu) {
		boolean flag = false;
		try {
			int result = uDao.updateUser(pu);
			if(result != 0){
				flag = true;
			}
		} catch (SQLException e) {
			Log.LOGGER.debug("删除用户失败 : "  +e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.UserBizInterface#detecUserEmail(java.lang.String)
	 */
	@Override
	public PaUser detecUserEmail(String email) {
		PaUser user = null;
		try {
			user = uDao.queryUserUnameByEmail(email);
		} catch (SQLException e) {
			Log.LOGGER.debug("检测邮箱失败 : "  +e.getMessage());
			e.printStackTrace();
		}
		return user;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.UserBizInterface#applyForAdmin(long)
	 */
	@Override
	public boolean applyForAdmin(long u_id) {
		boolean flag = true;
		try {
			PaApplyadmin pa = new PaApplyadmin(uDao.maxAdminid()+1,new PaUser(u_id));
			uDao.applyForAdmin(pa);
			flag = true;
		} catch (SQLException e) {
			Log.LOGGER.debug("申请权限失败 : "  +e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.UserBizInterface#queryAllApply()
	 */
	@Override
	public List<PaApplyadmin> queryAllApply() {
		List<PaApplyadmin> applys = new ArrayList<>();
		try {
			applys = uDao.queryAllApply();
		} catch (SQLException e) {
			Log.LOGGER.debug("查询管理员申请失败 : "  +e.getMessage());
			e.printStackTrace();
		}
		return applys;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.UserBizInterface#rechargeUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean rechargeUser(String uname, String num) {
		boolean flag = true;
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
		} catch (SQLException e) {
			Log.LOGGER.debug("充值失败 : "  +e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.UserBizInterface#updatePriceUserByid(long, long, long)
	 */
	@Override
	public boolean updatePriceUserByid(long u_id, long price, long money) {
		boolean flag = true;
		try {
			int r = uDao.minBalance(u_id,money);
			int result = uDao.updatePriceUserByid(u_id,price);
			PaDown d = new PaDown(uDao.maxDownid()+1, new PaUser(u_id), new Date(), (short) 4, "+"+price, null);
			uDao.addDownByid(d);
			if(result != 0 && r != 0)
				flag = true;
		} catch (SQLException e) {
			Log.LOGGER.debug("积分兑换失败 : "  +e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.UserBizInterface#updateRoleUserById(java.lang.String)
	 */
	@Override
	public boolean updateRoleUserById(String uid) {
		boolean flag = true;
		try {
			int r = uDao.updateRoleUserById(uid);
			if(r != 0)
				flag = true;
		} catch (SQLException e) {
			Log.LOGGER.debug("更新用户为管理员失败 : "  +e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.UserBizInterface#deleteApplyByUId(java.lang.String)
	 */
	@Override
	public boolean deleteApplyByUId(String uid) {
		boolean flag = true;
		try {
			int r = uDao.deleteApplyByUId(uid);
			if(r != 0)
				flag = true;
		} catch (SQLException e) {
			Log.LOGGER.debug("删除用户申请失败 : "  +e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	public UserDaoInterface getuDao() {
		return uDao;
	}
	public void setuDao(UserDaoInterface uDao) {
		this.uDao = uDao;
	}
	
}
