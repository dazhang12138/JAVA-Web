package com.da.Photography.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;
import com.da.Photography.dto.Albums;
import com.da.Photography.dto.User;

/**
 * 获取全部专辑信息
 * @author ZhangDa
 *
 */
public class GetAllAblumsServlet extends HttpServlet{

	/**
	 * serialVersionUID = 6798128763560534413L
	 */
	private static final long serialVersionUID = 6798128763560534413L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String stat = request.getParameter("stat");//1为用户查看，0为管理员查看
		String path = request.getParameter("path"); //跳转地址
		String type = request.getParameter("type"); //存在值为主界面查看，不需要登录。null时为后台查看需要登录权限
		if(type == null) {
			if(user == null || path == null || stat == null || path.equals("") || stat.equals("")) {
				if(stat == null || stat.equals("1")) {
					request.setAttribute("result", "请登录");
				}else {
					request.setAttribute("result", "请登录管理员账户");
				}
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else {
				if(!stat.equals("1")) {
					if(!user.getU_role().equals("0")) {
						request.setAttribute("result", "请登录管理员账户");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}
				}
				AlbumsBiz aBiz = new AlbumsBiz();
				List<Albums> albums = aBiz.getAlbums(user.getU_id(),stat);
				request.setAttribute("albums", albums);
				request.getRequestDispatcher(path).forward(request, response);
			}
		}else {
			AlbumsBiz aBiz = new AlbumsBiz();
			List<Albums> albums = aBiz.getAlbums(0,"0");
			request.setAttribute("albums", albums);
			request.getRequestDispatcher(path).forward(request, response);
		}
	}
}
