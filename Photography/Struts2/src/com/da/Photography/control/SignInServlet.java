package com.da.Photography.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBiz;
import com.da.Photography.dto.User;

/**
 * 签到操作
 * @author ZhangDa
 */
public class SignInServlet extends HttpServlet {
	/**
	 * serialVersionUID = -1826626720652979183L
	 */
	private static final long serialVersionUID = -1826626720652979183L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("result", "请登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			UserBiz uBiz = new UserBiz();
			int price = uBiz.signIn(user.getU_id(),user.getU_price());
			user.setU_price(user.getU_price() + price);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println("您的积分:" + (user.getU_price()) + "  +" + price);
			out.flush();
			out.close();
		}
	}
}
