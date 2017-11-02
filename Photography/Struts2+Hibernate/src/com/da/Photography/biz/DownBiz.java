package com.da.Photography.biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.da.Photography.dao.DownDaoInterface;
import com.da.Photography.daoImpl.DownHibDao;
import com.da.Photography.entity.PaDown;
import com.da.Photography.entity.PaPicture;
import com.da.Photography.entity.PaUser;
import com.da.Photography.util.Log;

public class DownBiz{
	/**
	 * 检测用户积分是否够下载、积分扣除、专辑创建者增加积分、记录下载记录 积分足够下载则进行下载；积分不足时异常退出。
	 * 积分足够时判断是否有下载记录；有记录则直接下载，否则扣分下载.
	 * 
	 * @param u_id 用户编号
	 * @param pid 图片编号
	 * @return 返回结果 1 = 积分不足 2 = 积分扣除失败 3 = 积分增加失败 0 = 成功
	 */
	public int down(int u_id, String pid) {
		int result = 2;
		DownDaoInterface dDao = new DownHibDao();
		dDao.beginTran();
		try {
			boolean orOne = dDao.queryDownByUidAndPid(u_id, pid);// 判断是否有下载记录
			if (orOne) {
				result = -1;
			} else {
				boolean orDown = dDao.queryPriceSupport(u_id, pid);// 判断积分是否足够
				if (orDown) {
					int minus = dDao.deductUserPrice(u_id, pid);// 扣除下载人积分
					dDao.recordDown(u_id, pid);// 记录积分走向
					if (minus != 0) {
						int add = dDao.addUserPrice(pid);// 增加创建人积分
						dDao.recordDown2(pid);// 记录积分走向
						if (add != 0) {
							result = 0;// 成功
						} else {
							result = 3;
							throw new SQLException();
						}
					} else {
						result = 2;
						throw new SQLException();
					}
				} else {
					result = 1;
					// throw new SQLException();
				}
			}
			dDao.commitTran();
		} catch (SQLException e) {
			dDao.rollbackTran();
			Log.LOGGER.debug(u_id + " 下载  " + pid + " 出错, 错误标志 " + result + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 用户下载专辑，已经检测过积分足够 需要检测图片是否下载过，下载过的图片不扣除积分，否则扣除积分
	 * @param u_id 用户编号
	 * @param pics 图片集合
	 * @return 返回是否成功下载所有图片
	 */
	public boolean down2(int u_id, List<PaPicture> pics) {
		boolean flag = false;
		DownDaoInterface dDao = new DownHibDao();
		dDao.beginTran();
		try {
			for (PaPicture pic : pics) {
				boolean orOne = dDao.queryDownByUidAndPid(u_id, String.valueOf(pic.getPId()));// 判断是否有下载记录
				if (!orOne) {
					int minus = dDao.deductUserPrice(u_id, String.valueOf(pic.getPId()));// 扣除下载人积分
					dDao.recordDown(u_id, String.valueOf(pic.getPId()));// 记录积分走向
					if (minus != 0) {
						int add = dDao.addUserPrice(String.valueOf(pic.getPId()));// 增加创建人积分
						dDao.recordDown2(String.valueOf(pic.getPId()));// 记录积分走向
						if (add != 0) {
							flag = true;// 成功
						} else {
							throw new SQLException();
						}
					} else {
						throw new SQLException();
					}
				}else {
					flag = true;
				}
			}
			dDao.commitTran();
		} catch (Exception e) {
			dDao.rollbackTran();
			flag = false;
			e.printStackTrace();
			Log.LOGGER.debug("下载专辑错误" + e.getMessage());
		}
		return flag;
	}

	/**
	 * 查询所有记录，通过type值判断查询条件 
	 * @param type 1 = 查询用户特有的 2 = 查询全部
	 * @param u_id 用户编号
	 * @return 返回下载记录的集合
	 */
	public List<PaDown> queryAllDown(String type, long u_id) {
		List<PaDown> downs = new ArrayList<>();
		DownDaoInterface dDao = new DownHibDao();
		dDao.beginTran();
		try {
			downs = dDao.queryAllDown(type, u_id);
			dDao.commitTran();
		} catch (SQLException e) {
			dDao.rollbackTran();
			Log.LOGGER.debug("查询记录失败" + e.getMessage());
			e.printStackTrace();
		}
		return downs;
	}

	/**
	 * 查询用户通过用户编号
	 * 
	 * @param uid 用户编号
	 * @return 返回查询的用户信息
	 */
	public PaUser queryUserByuid(int uid) {
		PaUser user = null;
		DownDaoInterface dDao = new DownHibDao();
		dDao.beginTran();
		try {
			user = dDao.queryUserByuid(uid);
			dDao.commitTran();
		} catch (SQLException e) {
			dDao.rollbackTran();
			Log.LOGGER.debug("查询用户通过编号失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

}
