package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBiz;

/**
 * 用户申请管理员设置通过或拒绝
 * @author ZhangDa
 *
 */
public class SetUserAdminServlet extends HttpServlet {

	/**
	 * serialVersionUID = -500541279399734222L
	 */
	private static final long serialVersionUID = -500541279399734222L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");//1通过2拒绝
		String uid = request.getParameter("uid");
		if(type == null || type.equals("") || uid == null || uid.equals("")) {
			request.setAttribute("result", "处理失败!");
		}else {
			UserBiz uBiz = new UserBiz();
			if(type.equals("1")) {
				uBiz.updateRoleUserById(uid);
				//准备发送邮件
			}else {
				//准备发送邮件
			}
			uBiz.deleteApplyByUId(uid);
		}
		request.getRequestDispatcher("admin/allAply.jsp").forward(request, response);
	}

}
