package com.da.Photography.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBiz;
import com.da.Photography.entity.PaUser;
import com.da.Photography.util.HibernateSessionFactory;

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
		PaUser user = (PaUser) request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("result", "请登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			UserBiz uBiz = new UserBiz();
			long price = uBiz.signIn(user.getUId(),user.getUPrice());
			user.setUPrice(user.getUPrice() + price);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println("您的积分:" + (user.getUPrice()) + "  +" + price);
			HibernateSessionFactory.closeSession();
			out.flush();
			out.close();
		}
	}
}
