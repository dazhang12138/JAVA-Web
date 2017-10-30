package com.da.Photography.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.DownBiz;
import com.da.Photography.entity.PaUser;
import com.da.Photography.util.HibernateSessionFactory;

/**
 * 下载辅助
 * 
 * @author ZhangDa
 *
 */
public class D1Servlet extends HttpServlet {

	/**
	 * serialVersionUID = 2387456341185761607L
	 */
	private static final long serialVersionUID = 2387456341185761607L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		PaUser user = (PaUser) request.getSession().getAttribute("user");
		if (user == null) {
			out.write("请先登录");
		} else {
			if (pid == null || pid.equals("")) {
				out.write("下载出错!请稍后重试.");
			} else {
				// 检测用户积分是否够下载、积分扣除、专辑创建者增加积分、记录下载记录
				long uid = user.getUId();
				DownBiz dBiz = new DownBiz();
				int result = dBiz.down((int)uid, pid);
				if (result == 0 || result == -1) {
					if(result == 0) {
						user = dBiz.queryUserByuid((int)uid);
						if(user != null) {
							request.getSession().setAttribute("user", user);
						}
					}
					out.write((result == 0 ? "扣除您响应的下载积分." : "您之前下载过这张图片,此次免积分下载." ));
				} else if (result == 1) {
					out.write("您的积分不足,请充值或赚取积分后下载.");
				} else {
					out.write("下载出错!请稍后重试.");
				}
			}
		}
		HibernateSessionFactory.closeSession();
		out.flush();
		out.close();
	}

}
