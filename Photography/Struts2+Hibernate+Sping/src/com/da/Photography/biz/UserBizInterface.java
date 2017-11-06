package com.da.Photography.biz;

import java.util.List;

import com.da.Photography.entity.PaApplyadmin;
import com.da.Photography.entity.PaUser;

public interface UserBizInterface {

	/**
	 * 添加一个用户信息
	 * @param user 注册用户信息
	 * @return 注册结果 true 成功  false 失败
	 */
	boolean register(PaUser user);

	/**
	 * 登录
	 * @param uname 用户名
	 * @param pwd 密码
	 * @return 返回登录用户信息  user为null时登录失败
	 */
	PaUser login(String uname, String pwd);

	/**
	 * 检测用户名是否存在
	 * @param uname 用户名
	 * @return 返回是否存在的结果
	 */
	boolean detecUserName(String uname);

	/**
	 * 签到
	 * @param u_id
	 * @param u_price
	 * @return
	 */
	long signIn(long u_id, long u_price);

	/**
	 * 查询所有用户信息
	 * @param stat 状态
	 * @return
	 */
	List<PaUser> getAllUsers();

	/**
	 * 删除用户信息,级联删除
	 * @param uid 用户编号
	 * @return
	 */
	boolean deleteUser(String uid);

	/**
	 * 修改用户信息
	 * @param uid 用户编号
	 * @param name 用户姓名
	 * @param pwd 密码
	 * @param phone 电话
	 * @param email 邮箱
	 * @return 返回修改结果
	 */
	boolean updateUser(PaUser pu);

	/**
	 * 验证注册邮箱不能重复、通过邮箱找回密码
	 * @param email
	 * @return
	 */
	PaUser detecUserEmail(String email);

	/**
	 * 申请权限
	 * @param u_id
	 * @return
	 */
	boolean applyForAdmin(long u_id);

	/**
	 * 查询所有的管理员申请
	 * @return
	 */
	List<PaApplyadmin> queryAllApply();

	/**
	 * 用户充值
	 * @param uname
	 * @param num
	 * @return
	 */
	boolean rechargeUser(String uname, String num);

	/**
	 * 用户积分兑换
	 * @param u_id
	 * @param price
	 * @param money 
	 * @return
	 */
	boolean updatePriceUserByid(long u_id, long price, long money);

	/**
	 * 更新用户角色为管理员
	 * @param uid
	 * @return
	 */
	boolean updateRoleUserById(String uid);

	/**
	 * 删除申请表用户
	 * @param uid
	 * @return
	 */
	boolean deleteApplyByUId(String uid);

}