package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBiz;

/**
 * 用户余额充值
 * @author ZhangDa
 */
public class RechargeServlet extends HttpServlet {

	/**
	 * serialVersionUID = -5629434068973262984L
	 */
	private static final long serialVersionUID = -5629434068973262984L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		request.getRequestDispatcher("admin/recharge.jsp").forward(request, response);
	}

}
