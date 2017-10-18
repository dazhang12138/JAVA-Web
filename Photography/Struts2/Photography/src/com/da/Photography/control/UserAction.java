package com.da.Photography.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.da.Photography.biz.UserBiz;
import com.da.Photography.dto.Apply;
import com.da.Photography.dto.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction{
	/**
	 * 用户编号
	 */
	private String uid;
	/**
	 * 用户信息
	 */
	private User user;
	/**
	 * 兑换积分，兑换的金额
	 */
	private String money;
	/**
	 * 用户登录
	 * @return
	 */
	public String login(){
		String result = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		UserBiz uBiz = new UserBiz();
		User u = uBiz.login(user.getU_uname(),user.getU_pwd());
		if(u != null) {
			request.getSession().setAttribute("user", u);
			result = "Login_True";
		}else {
			request.setAttribute("result", "用户名或密码不正确");
			result = "Login_False";
		}
		return result;
	}
	/**
	 * 退出登录
	 * @return
	 */
	public String loginOut(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().removeAttribute("user");
		return "Login_True";
	}
	/**
	 * 注册
	 * @return
	 */
	public String register(){
		HttpServletRequest request = ServletActionContext.getRequest();
		UserBiz uBiz = new UserBiz();
		boolean flag = uBiz.register(user);
		if(flag) {
			request.setAttribute("result", "注册成功");
		}else {
			request.setAttribute("result", "注册失败");
		}
		return "register";
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	public String deleteUser(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(uid == null) {
			request.setAttribute("result", "删除用户失败,用户编号为空.");
		}else {
			UserBiz uBiz = new UserBiz();
			boolean falg = uBiz.deleteUser(uid);
			if(falg) {
				request.setAttribute("result", "删除用户成功.");
			}else {
				request.setAttribute("result", "删除用户失败.");
			}
		}
		return "deleteUser_True";
	}
	
	/**
	 * 积分兑换
	 * @return
	 */
	public String pointsfor(){
		String result = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if(money != null && !money.equals("")){
			int m = Integer.valueOf(money);
			UserBiz uBiz = new UserBiz();
			boolean flag = uBiz.updatePriceUserByid(user.getU_id(),m*100,m);
			if(flag) {
				request.setAttribute("result", "积分兑换成功");
				user.setU_balance(user.getU_balance()-m);
				user.setU_price(user.getU_price()+m*100);
				request.getSession().setAttribute("user", user);
				result = "pointsfor_True";
			}else {
				request.setAttribute("result", "积分兑换失败");
				result = "pointsfor_False";
			}
		}else{
			request.setAttribute("result", "积分兑换失败");
			result = "pointsfor_False";
		}
		return result;
	}
	/**
	 * 查找所有用户申请管理员记录
	 * @return
	 */
	public String allApply(){
		String result = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if(user != null && user.getU_role().equals("0")) {
			UserBiz uBiz = new UserBiz();
			List<Apply> applys = uBiz.queryAllApply();
			request.setAttribute("applys", applys);
			result = "allApplys_True";
		}else {
			result = "Login_False";
			request.setAttribute("result", "请登录管理员账户");
		}
		return result;
	}
	
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
