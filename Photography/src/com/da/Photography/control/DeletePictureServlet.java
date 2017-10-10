package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;

/**
 * 删除图片信息,级联删除下载记录。
 * @author ZhangDa
 *
 */
public class DeletePictureServlet extends HttpServlet {

	/**
	 * serialVersionUID = 1566114676016568637L
	 */
	private static final long serialVersionUID = 1566114676016568637L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		if(pid == null || pid.equals("")) {
			request.setAttribute("result", "删除图片失败");
		}else {
			AlbumsBiz aBiz = new AlbumsBiz();
			boolean flag = aBiz.deletePicture(pid);
			if(flag) {
				request.setAttribute("result", "删除图片成功");
			}else {
				request.setAttribute("result", "删除图片失败");
			}
		}
		request.getRequestDispatcher("QueryAlbumsPicture").forward(request, response);
	}
	
}
