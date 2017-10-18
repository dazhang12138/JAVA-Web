package com.da.Photography.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.DownBiz;
import com.da.Photography.dto.Down;
import com.da.Photography.dto.User;


/**
 * 查询记录
 * @author ZhangDa
 */
public class GetAllDownServlet extends HttpServlet{

	/**
	 * serialVersionUID = -5326483565912229185L
	 */
	private static final long serialVersionUID = -5326483565912229185L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String path = request.getParameter("path");
		User user = (User) request.getSession().getAttribute("user");
	  	if(user == null){
			request.setAttribute("result", "请登录管理员账户");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			if(!type.equals("1") && !user.getU_role().equals("0")){
				request.setAttribute("result", "请登录管理员账户");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else {
				DownBiz dBiz = new DownBiz();
				List<Down> downs = dBiz.queryAllDown(type,user.getU_id());
				request.setAttribute("downs", downs);
				request.getRequestDispatcher(path).forward(request, response);
			}
		}
	}
	
}
