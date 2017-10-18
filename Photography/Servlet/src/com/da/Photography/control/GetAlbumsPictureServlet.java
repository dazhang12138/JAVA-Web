package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;
import com.da.Photography.dto.Albums;

/**
 * 通过专辑编号查看专辑内图片信息
 * @author ZhangDa
 *
 */
public class GetAlbumsPictureServlet extends HttpServlet {

	/**
	 * serialVersionUID = 9034830475985074660L
	 */
	private static final long serialVersionUID = 9034830475985074660L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String path = request.getParameter("path");
		if(id != null && !id.equals("")) {
			AlbumsBiz aBiz = new AlbumsBiz();
			Albums album = aBiz.queryAPictureByAid(id);
			request.setAttribute("album", album);
		}else {
			request.setAttribute("result", "查询失败");	
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}
