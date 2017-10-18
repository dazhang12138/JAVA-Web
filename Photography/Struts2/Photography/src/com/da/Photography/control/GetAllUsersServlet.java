package com.da.Photography.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBiz;
import com.da.Photography.dto.User;

/**
 * 查询所有用户信息
 * @author ZhangDa
 *
 */
public class GetAllUsersServlet extends HttpServlet {

	/**
	 * serialVersionUID = 2109569995879310403L
	 */
	private static final long serialVersionUID = 2109569995879310403L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute("user");
		if(u == null || !u.getU_role().equals("0")) {
			request.setAttribute("result", "请登录管理员账户");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			String path = request.getParameter("path");
			if(path == null) {
				request.setAttribute("result", "查询失败");
			}else {
				UserBiz uBiz = new UserBiz();
				List<User> users = uBiz.getAllUsers();
				request.setAttribute("users", users);
			}
			request.getRequestDispatcher((path==null||path.equals("")) ? "index.jsp" : path).forward(request, response);
		}
	}

}
