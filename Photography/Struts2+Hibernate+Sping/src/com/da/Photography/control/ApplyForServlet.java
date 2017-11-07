package com.da.Photography.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBizInterface;
import com.da.Photography.bizImpl.UserBiz;
import com.da.Photography.entity.PaUser;

/**
 * 申请管理员
 * @author ZhangDa
 */
public class ApplyForServlet extends HttpServlet {

	/**
	 * serialVersionUID = 3241006506790237692L
	 */
	private static final long serialVersionUID = 3241006506790237692L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PaUser user = (PaUser) request.getSession().getAttribute("user");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(user == null) {
			out.print("尝试失败！请稍后再试!");
		}else {
			UserBizInterface uBiz = new UserBiz();
			boolean flag = uBiz.applyForAdmin(user.getUId());
			if(flag) {
				out.print("申请中,请稍后查看权限!");
			}else {
				out.print("尝试失败！请稍后再试!");
			}
		}
		out.flush();
		out.close();
	}

}
