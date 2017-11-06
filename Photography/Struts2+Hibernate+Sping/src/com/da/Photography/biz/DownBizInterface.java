package com.da.Photography.biz;

import java.util.List;

import com.da.Photography.entity.PaDown;
import com.da.Photography.entity.PaPicture;
import com.da.Photography.entity.PaUser;

public interface DownBizInterface {

	/**
	 * 检测用户积分是否够下载、积分扣除、专辑创建者增加积分、记录下载记录 积分足够下载则进行下载；积分不足时异常退出。
	 * 积分足够时判断是否有下载记录；有记录则直接下载，否则扣分下载.
	 * 
	 * @param u_id 用户编号
	 * @param pid 图片编号
	 * @return 返回结果 1 = 积分不足 2 = 积分扣除失败 3 = 积分增加失败 0 = 成功
	 */
	int down(int u_id, String pid);

	/**
	 * 用户下载专辑，已经检测过积分足够 需要检测图片是否下载过，下载过的图片不扣除积分，否则扣除积分
	 * @param u_id 用户编号
	 * @param pics 图片集合
	 * @return 返回是否成功下载所有图片
	 */
	boolean down2(int u_id, List<PaPicture> pics);

	/**
	 * 查询所有记录，通过type值判断查询条件 
	 * @param type 1 = 查询用户特有的 2 = 查询全部
	 * @param u_id 用户编号
	 * @return 返回下载记录的集合
	 */
	List<PaDown> queryAllDown(String type, long u_id);

	/**
	 * 查询用户通过用户编号
	 * 
	 * @param uid 用户编号
	 * @return 返回查询的用户信息
	 */
	PaUser queryUserByuid(int uid);

}