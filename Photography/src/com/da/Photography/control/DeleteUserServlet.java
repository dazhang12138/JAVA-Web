package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBiz;

/**
 * 删除用户
 * @author ZhangDa
 */
public class DeleteUserServlet extends HttpServlet {

	/**
	 * serialVersionUID = -516044259176172109L
	 */
	private static final long serialVersionUID = -516044259176172109L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
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
		request.getRequestDispatcher("admin/allUser.jsp").forward(request, response);
	}
}
