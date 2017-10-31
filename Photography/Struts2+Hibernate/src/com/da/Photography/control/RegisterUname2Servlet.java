package com.da.Photography.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBiz;
import com.da.Photography.util.HibernateSessionFactory;

/**
 * 判断用户名是否可以注册
 * @author ZhangDa
 *
 */
public class RegisterUname2Servlet extends HttpServlet {

	/**
	 * serialVersionUID = 4198976942222063819L
	 */
	private static final long serialVersionUID = 4198976942222063819L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		UserBiz uBiz = new UserBiz();
		if(uname == null || uname.equals("")) {
			out.println("不能为空");
		}else {
			boolean flag = uBiz.detecUserName(uname);
			if(flag) {
				out.println("不存在的用户");
			}else {
				out.println("可以充值");
			}
		}
		HibernateSessionFactory.closeSession();
		out.flush();
		out.close();
	}

}
