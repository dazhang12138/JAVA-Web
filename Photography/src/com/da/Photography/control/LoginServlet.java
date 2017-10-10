package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBiz;
import com.da.Photography.dto.User;

/**
 * 用户登录
 * @author ZhangDa
 */
public class LoginServlet extends HttpServlet{
	/**
	 * serialVersionUID = 2812955766016026962L
	 */
	private static final long serialVersionUID = 2812955766016026962L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		request.getParameter("remember"); //记住我
		UserBiz uBiz = new UserBiz();
		User user = uBiz.login(uname,pwd);
		if(user != null) {
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			request.setAttribute("result", "用户名或密码不正确");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
