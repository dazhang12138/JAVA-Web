package com.da.Photography.bizImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.da.Photography.biz.DownBizInterface;
import com.da.Photography.dao.DownDaoInterface;
import com.da.Photography.daoImpl.DownHibDao;
import com.da.Photography.entity.PaDown;
import com.da.Photography.entity.PaPicture;
import com.da.Photography.entity.PaUser;
import com.da.Photography.util.Log;

public class DownBiz implements DownBizInterface{
	private DownDaoInterface dDao;
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.DownBizInterface#down(int, java.lang.String)
	 */
	@Override
	public int down(int u_id, String pid) {
		int result = 2;
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
		} catch (SQLException e) {
			Log.LOGGER.debug(u_id + " 下载  " + pid + " 出错, 错误标志 " + result + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.DownBizInterface#down2(int, java.util.List)
	 */
	@Override
	public boolean down2(int u_id, List<PaPicture> pics) {
		boolean flag = false;
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
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			Log.LOGGER.debug("下载专辑错误" + e.getMessage());
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.DownBizInterface#queryAllDown(java.lang.String, long)
	 */
	@Override
	public List<PaDown> queryAllDown(String type, long u_id) {
		List<PaDown> downs = new ArrayList<>();
		try {
			downs = dDao.queryAllDown(type, u_id);
		} catch (SQLException e) {
			Log.LOGGER.debug("查询记录失败" + e.getMessage());
			e.printStackTrace();
		}
		return downs;
	}

	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.DownBizInterface#queryUserByuid(int)
	 */
	@Override
	public PaUser queryUserByuid(int uid) {
		PaUser user = null;
		DownDaoInterface dDao = new DownHibDao();
		try {
			user = dDao.queryUserByuid(uid);
		} catch (SQLException e) {
			Log.LOGGER.debug("查询用户通过编号失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

	public DownDaoInterface getdDao() {
		return dDao;
	}

	public void setdDao(DownDaoInterface dDao) {
		this.dDao = dDao;
	}

}
