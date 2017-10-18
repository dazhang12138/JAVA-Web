package com.da.Photography.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBiz;
import com.da.Photography.dto.Apply;
import com.da.Photography.dto.User;

/**
 * 查找所有用户申请管理员记录
 * @author ZhangDa
 */
public class GetAllApplyServlet extends HttpServlet {

	/**
	 * serialVersionUID = 7900003461673110477L
	 */
	private static final long serialVersionUID = 7900003461673110477L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String path = "login.jsp";
		if(user != null && user.getU_role().equals("0")) {
			path = "admin/allAply.jsp";
			UserBiz uBiz = new UserBiz();
			List<Apply> applys = uBiz.queryAllApply();
			request.setAttribute("applys", applys);
		}else {
			request.setAttribute("result", "请登录管理员账户");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}
