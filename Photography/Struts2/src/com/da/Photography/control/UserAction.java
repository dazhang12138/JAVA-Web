package com.da.Photography.control;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.da.Photography.biz.UserBiz;
import com.da.Photography.dao.DownDao;
import com.da.Photography.dto.Apply;
import com.da.Photography.dto.User;
import com.da.Photography.util.Email;
import com.da.Photography.util.Log;

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
	 * 类型，通过不同类型控制不同功能
	 * setUserAdmin()方法中 1通过2拒绝
	 */
	private String type;
	/**
	 * 跳转地址
	 */
	private String path;
	
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
	
	/**
	 * 用户充值
	 * @return
	 */
	public String recharge(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String uname = request.getParameter("uname");
		String num = request.getParameter("num");
		if(uname == null || uname.equals("") || num == null || num.equals("")) {
			request.setAttribute("result", "充值失败，不能为空");
		}else {
			UserBiz uBiz = new UserBiz();
			boolean f = uBiz.detecUserName(uname);
			if(!f) {
				boolean flag = uBiz.rechargeUser(uname,num);
				if(flag) {
					request.setAttribute("result", "充值成功");
					//邮箱提醒充值成功
				}else {
					request.setAttribute("result", "充值失败！");
				}
			}else {
				request.setAttribute("result", "充值失败，用户不存在.");
			}
		}
		return "recharge";
	}
	
	/**
	 * 设置用户申请管理员通过和拒绝
	 * @return
	 */
	public String setUserAdmin(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(type == null || type.equals("") || uid == null || uid.equals("")) {
			request.setAttribute("result", "处理失败!");
		}else {
			UserBiz uBiz = new UserBiz();
			DownDao dDao = new DownDao();
			if(type.equals("1")) {
				uBiz.updateRoleUserById(uid);
			}
			uBiz.deleteApplyByUId(uid);
			User user;
			try {
				dDao.beginTran();
				user = dDao.queryUserByuid(Integer.valueOf(uid));
				dDao.commitTran();
				String Text = "您好  " + user.getU_name() + " 先生/小姐。<br> 我是Photography摄影网站后台管理员.<br>我们收到您的申请网站管理的要求,首先非常感谢您对我们网站的贡献及支持,我们很希望更多的人加入我们，以及加入我们内部管理.<br>我们管理人员经过大会讨论对于用户您的申请我们给出处理结果如下:"
						+ "<br><span style=\"color: red;\">" + (type.equals("1") ? "通过,恭喜您，你已经是我们Photography摄影网站的后台管理员，请您务必遵守管理员守则。" : "不通过，抱歉！经过我们讨论您不适合做我们社区管理员，请您谅解。") +"</span>"
						+ "<br>我们的承诺:<br><br>    为了营造一个开放和欢迎的环境，我们的贡献者和维护者承诺，无论年龄、体型、残疾、种族、性别身份和表情、经验、国籍、个人外貌、种族、宗教、性别身份和取向，都将参与到我们的平台和社区中，为每个人提供免费的体验。"
						+ "<br>    In the interest of fostering an open and welcoming environment, we ascontributors and maintainers pledge to making participation in our project andour community a harassment-free experience for everyone, regardless of age, bodysize, disability, ethnicity, gender identity and expression, level of experience,nationality, personal appearance, race, religion, or sexual identity andorientation.";
				Email.sendEmail(user.getU_email(),Text);
			} catch (SQLException e) {
				Log.LOGGER.debug("查询用户信息出错" + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				Log.LOGGER.debug("发送邮件出错" + e.getMessage());
				e.printStackTrace();
			}
		}
		return "setuseradmin" ;
	}
	/**
	 * 修改个人信息
	 * @return
	 */
	public String updateUser(){
		String result = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		if(user.getU_name() == null || user.getU_name().equals("") 
				|| user.getU_pwd() == null || user.getU_pwd().equals("") || user.getU_phone() == null 
				|| user.getU_phone().equals("") || user.getU_email() == null || user.getU_email().equals("") ) {
			request.setAttribute("result", "修改个人信息失败,所有项不能为空.");
			result = "updateUser";
		}else {
			UserBiz uBiz = new UserBiz();
			boolean flag = uBiz.updateUser(String.valueOf(user.getU_id()),user.getU_name(),user.getU_pwd(),user.getU_phone(),user.getU_email());
			if(flag) {
				request.setAttribute("result", "请重新登录.");
				request.getSession().removeAttribute("user");
				result = "Login_False";
			}else {
				request.setAttribute("result", "修改个人信息失败.邮箱可能已被注册!");
				result = "updateUser";
			}
		}
		return result;
	}
	
	/**
	 * 查询全部用户信息
	 * @return
	 */
	public String allUser(){
		String result = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		User u = (User) request.getSession().getAttribute("user");
		if(u == null || !u.getU_role().equals("0")) {
			request.setAttribute("result", "请登录管理员账户");
			result = "Login_False";
		}else {
			if(path == null) {
				request.setAttribute("result", "查询失败");
			}else {
				UserBiz uBiz = new UserBiz();
				List<User> users = uBiz.getAllUsers();
				request.setAttribute("users", users);
			}
			if(path == null || path.equals(""))
				path = "index.jsp";
			result = "path";
		}
		return result;
	}
	
	/**
	 * 找回密码，判断是否存在用户、查找用户信息、发送邮件
	 * @return
	 */
	public String forgotPwd(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String email = request.getParameter("email");
		if(email == null || email.equals("")) {
			request.setAttribute("result", "邮件发送失败！邮箱不能为空.");
		}else {
			UserBiz uBiz = new UserBiz();
			User user = uBiz.detecUserEmail(email);
			if(user == null) {
				request.setAttribute("result", "邮件发送失败！此邮箱未注册.");
			}else {
				try {
					String Text = "您好  " + user.getU_name() + " 先生/小姐。<br> 我是Photography摄影网站后台管理员.<br>我们收到您的找回密码要求,已将您的Photography网站登录用户名和密码发送至您的邮箱.<br>如果是您本人操作请记住登录信息并尽快删除邮件.<br>如果不是您本人在操作找回Photography网站密码，请尽快前往我们网站修改个人信息,并注意个人信息以及登录信息泄露问题.<br>我们Photography网站承诺不会将个人信息及隐私泄露给任何人,请您放心."
			        		+ "您的登录用户名:" + user.getU_uname() + ";登录密码:" + user.getU_pwd() + ";请牢记您的用户名密码。"
	        				+ "<br>我们的承诺:<br><br>    为了营造一个开放和欢迎的环境，我们的贡献者和维护者承诺，无论年龄、体型、残疾、种族、性别身份和表情、经验、国籍、个人外貌、种族、宗教、性别身份和取向，都将参与到我们的平台和社区中，为每个人提供免费的体验。"
	        				+ "<br>    In the interest of fostering an open and welcoming environment, we ascontributors and maintainers pledge to making participation in our project andour community a harassment-free experience for everyone, regardless of age, bodysize, disability, ethnicity, gender identity and expression, level of experience,nationality, personal appearance, race, religion, or sexual identity andorientation.";
					Email.sendEmail(email,Text);
					request.setAttribute("result", "邮件已发送.");
				} catch (Exception e) {
					request.setAttribute("result", "邮件发送失败.");
					Log.LOGGER.debug("发送邮件失败 : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return "forgot";
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
