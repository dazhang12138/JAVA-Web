package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBiz;

/**
 * 修改用户信息
 * @author ZhangDa
 *
 */
public class UpdateUserServlet extends HttpServlet {

	/**
	 * serialVersionUID = 3736925824105134469L
	 */
	private static final long serialVersionUID = 3736925824105134469L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String uid = request.getParameter("uid");
		if(name == null || name.equals("") || pwd == null || pwd.equals("") || phone == null || 
				phone.equals("") || email == null || email.equals("") || uid == null || uid.equals("")) {
			request.setAttribute("result", "修改个人信息失败,所有项不能为空.");
			request.getRequestDispatcher("admin/alterUser.jsp").forward(request, response);
		}else {
			UserBiz uBiz = new UserBiz();
			boolean flag = uBiz.updateUser(uid,name,pwd,phone,email);
			if(flag) {
				request.setAttribute("result", "请重新登录.");
				request.getSession().removeAttribute("user");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else {
				request.setAttribute("result", "修改个人信息失败.邮箱可能已被注册!");
				request.getRequestDispatcher("admin/alterUser.jsp").forward(request, response);
			}
		}
	}

}
