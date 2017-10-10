package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;
import com.da.Photography.dto.Albums;

/**
 * 通过专辑编号查询专辑详情
 * @author ZhangDa
 *
 */
public class GetAlbumsServlet extends HttpServlet {

	/**
	 *serialVersionUID = -1567379053019852721L 
	 */
	private static final long serialVersionUID = -1567379053019852721L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(id != null && !id.equals("")) {
			AlbumsBiz aBiz = new AlbumsBiz();
			Albums album = aBiz.queryAlbumsById(id);
			if(album == null) {
				request.setAttribute("result", "查询详情信息失败");
			}else {
				request.setAttribute("album",album);
			}
		}else {
			request.setAttribute("result", "查询详情信息失败");
		}
		request.getRequestDispatcher("admin/alterAlbums.jsp").forward(request, response);
	}

}
