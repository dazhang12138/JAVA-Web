package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBiz;
import com.da.Photography.dto.User;

/**
 * 用户注册
 * @author ZhangDa
 */
public class RegisterServlet extends HttpServlet {
	/**
	 * serialVersionUID = -933552895442249091L
	 */
	private static final long serialVersionUID = -933552895442249091L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		User user = new User();
		user.setU_name(name);
		user.setU_uname(uname);
		user.setU_pwd(pwd);
		user.setU_phone(phone);
		user.setU_email(email);
		UserBiz uBiz = new UserBiz();
		boolean flag = uBiz.register(user);
		if(flag) {
			request.setAttribute("result", "注册成功");
		}else {
			request.setAttribute("result", "注册失败");
		}
		request.getRequestDispatcher("sign-up.jsp").forward(request, response);
	}
}
