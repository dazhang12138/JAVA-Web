package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBiz;
import com.da.Photography.dto.User;

/**
 * 积分兑换
 * @author ZhangDa
 *
 */
public class PointsforServlet extends HttpServlet{

	/**
	 * serialVersionUID = 1114471772121602102L
	 */
	private static final long serialVersionUID = 1114471772121602102L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String money = request.getParameter("money");
		int m = Integer.valueOf(money);
		UserBiz uBiz = new UserBiz();
		boolean flag = uBiz.updatePriceUserByid(user.getU_id(),m*100,m);
		if(flag) {
			request.setAttribute("result", "积分兑换成功");
			user.setU_balance(user.getU_balance()-m);
			user.setU_price(user.getU_price()+m*100);
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("admin/profile.jsp").forward(request, response);
		}else {
			request.setAttribute("result", "积分兑换失败");
			request.getRequestDispatcher("admin/pointsfor.jsp").forward(request, response);
		}
	}

}
