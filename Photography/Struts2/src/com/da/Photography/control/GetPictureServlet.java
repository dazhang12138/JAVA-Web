package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;
import com.da.Photography.dto.Picture;

/**
 * 获取图片详情
 * @author ZhangDa
 */
public class GetPictureServlet extends HttpServlet {

	/**
	 * serialVersionUID = 9198138327882823344L
	 */
	private static final long serialVersionUID = 9198138327882823344L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(id != null && !id.equals("")) {
			AlbumsBiz aBiz = new AlbumsBiz();
			Picture picture = aBiz.queryPictureByAid(id);
			if(picture == null) {
				request.setAttribute("result", "查询图片详情失败");
			}else {
				request.setAttribute("picture", picture);
			}
		}else {
			request.setAttribute("result", "查询图片详情失败");
		}
		request.getRequestDispatcher("admin/alterPicture.jsp").forward(request, response);
	}
	
	
}
