package com.da.Photography.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBiz;
import com.da.Photography.entity.PaUser;

/**
 * 判断邮箱是否可以注册
 * @author ZhangDa
 *
 */
public class RegisterEmailServlet extends HttpServlet {

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
		String email = request.getParameter("email");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		UserBiz uBiz = new UserBiz();
		if(email == null || email.equals("")) {
			out.println("不能为空");
		}else {
			PaUser user = uBiz.detecUserEmail(email);
			if(user == null) {
				out.println("邮箱可用");
			}else {
				out.println("此邮箱已注册");
			}
		}
		out.flush();
		out.close();
	}

}
