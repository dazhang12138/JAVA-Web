package com.da.Photography.dao;

import java.sql.SQLException;
import java.util.List;

import com.da.Photography.entity.PaDown;
import com.da.Photography.entity.PaUser;

public interface DownDaoInterface extends BaseDaoInterface {
	//判断积分是否足够下载图片
	public boolean queryPriceSupport(int u_id, String pid) throws SQLException;
	//扣除用户积分
	public int deductUserPrice(int u_id, String pid) throws SQLException;
	//增加图片存在专辑的创建者的积分
	public int addUserPrice(String pid) throws SQLException ;
	//更新记录
	public int recordDown(int u_id, String pid) throws SQLException;
	//更新记录
	public int recordDown2(String pid) throws SQLException;
	//查找下载记录是否存在
	public boolean queryDownByUidAndPid(int u_id, String pid) throws SQLException;
	//查询记录
	public List<PaDown> queryAllDown(String type, long u_id) throws SQLException;
	//查询用户信息通过用户编号
	public PaUser queryUserByuid(int uid) throws SQLException;
	//查询记录的最大编号
	public long maxId();
}
