package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;

/**
 * 修改专辑信息
 * @author ZhangDa
 *
 */
public class UpdateAlbumsServlet extends HttpServlet {

	/**
	 * serialVersionUID = 3187291050548660129L
	 */
	private static final long serialVersionUID = 3187291050548660129L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String profile = request.getParameter("profile");
		if(profile == null)
			profile = "";
		//这里修改信息时专辑名称不能为空，编号是控制修改条件不能为空，但是专辑简介是可以为空的
		if(id == null || name == null || id.equals("") || name.equals("")) {
			request.setAttribute("result", "修改信息失败");
		}else {
			AlbumsBiz aBiz = new AlbumsBiz();
			boolean flag = aBiz.updateAlbums(id,name,profile);
			if(flag) {
				request.setAttribute("result", "修改信息成功");
			}else {
				request.setAttribute("result", "修改信息失败");
			}
		}
		request.getRequestDispatcher("admin/alterAlbums.jsp").forward(request, response);
	}

}
