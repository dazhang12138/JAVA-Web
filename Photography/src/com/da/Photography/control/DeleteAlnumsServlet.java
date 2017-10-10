package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;

/**
 * 删除专辑
 * @author ZhangDa
 *
 */
public class DeleteAlnumsServlet extends HttpServlet {

	/**
	 * serialVersionUID = 3677758002824975274L
	 */
	private static final long serialVersionUID = 3677758002824975274L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(id == null || id.equals("")) {
			request.setAttribute("result", "删除专辑失败,请注意合法操作");
		}else {
			AlbumsBiz aBiz = new AlbumsBiz();
			boolean flag = aBiz.deleteAlbums(id);
			if(flag) {
				request.setAttribute("result", "删除专辑成功");
			}else {
				request.setAttribute("result", "删除专辑失败,请先清空专辑内所有图片");
			}
		}
		request.getRequestDispatcher("admin/index.jsp").forward(request, response);
	}

}
