package com.da.Photography.biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.da.Photography.dao.DownDao;
import com.da.Photography.dto.Down;
import com.da.Photography.dto.User;
import com.da.Photography.util.Log;

public class DownBiz {
	/**
	 * 检测用户积分是否够下载、积分扣除、专辑创建者增加积分、记录下载记录
	 * 积分足够下载则进行下载；积分不足时异常退出。
	 * 积分足够时判断是否有下载记录；有记录则直接下载，否则扣分下载.
	 * @param u_id
	 * @param pid
	 * @return 返回结果 1 = 积分不足 2 = 积分扣除失败 3 = 积分增加失败  0 = 成功
	 */
	public int down(int u_id, String pid) {
		int result = 2;
		DownDao dDao = new DownDao();
		dDao.beginTran();
		try {
			boolean orOne = dDao.queryDownByUidAndPid(u_id,pid);//判断是否有下载记录
			if(orOne) {
				result = -1;
			}else {
				boolean orDown = dDao.queryPriceSupport(u_id,pid);//判断积分是否足够
				if(orDown) {
					int minus = dDao.deductUserPrice(u_id,pid);//扣除下载人积分
					int downupdate = dDao.recordDown(u_id, pid);//记录积分走向
					if(minus != 0 && downupdate != 0) {
						int add = dDao.addUserPrice(pid);//增加创建人积分
						downupdate = dDao.recordDown2(pid);//记录积分走向
						if(add != 0  && downupdate != 0) {
							result = 0;//成功
						}else {
							result = 3;
							throw new SQLException();
						}
					}else {
						result = 2;
						throw new SQLException();
					}
				}else {
					result = 1;
//				throw new SQLException();
				}
			}
			dDao.commitTran();
		} catch (SQLException e) {
			dDao.rollbackTran();
			Log.LOGGER.debug(u_id + " 下载  " + pid + " 出错, 错误标志 " + result +" : " + e.getMessage());
			e.printStackTrace();
		}finally {
			dDao.closeAll();
		}
		return result;
	}

	/**
	 * 查询所有记录，通过type值判断查询条件
	 * @param type 1 = 查询用户特有的 2 = 查询全部
	 * @param u_id 
	 * @return
	 */
	public List<Down> queryAllDown(String type, int u_id) {
		List<Down> downs = new ArrayList<>();
		DownDao dDao = new DownDao();
		dDao.beginTran();
		try {
			downs = dDao.queryAllDown(type,u_id);
			dDao.commitTran();
		} catch (SQLException e) {
			dDao.rollbackTran();
			Log.LOGGER.debug("查询记录失败" + e.getMessage());
			e.printStackTrace();
		}finally {
			dDao.closeAll();
		}
		return downs;
	}
	/**
	 * 查询用户通过用户编号
	 * @param uid
	 * @return
	 */
	public User queryUserByuid(int uid) {
		User user = null;
		DownDao dDao = new DownDao();
		dDao.beginTran();
		try {
			user = dDao.queryUserByuid(uid);
			dDao.commitTran();
		} catch (SQLException e) {
			dDao.rollbackTran();
			Log.LOGGER.debug("查询用户通过编号失败 : "  +e.getMessage());
			e.printStackTrace();
		}finally {
			dDao.closeAll();
		}
		return user;
	}

}
