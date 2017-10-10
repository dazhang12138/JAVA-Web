package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;

/**
 * 获取图片信息
 * @author ZhangDa
 *
 */
public class getPicServlet extends HttpServlet {

	/**
	 * serialVersionUID = -2780712995364682177L
	 */
	private static final long serialVersionUID = -2780712995364682177L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		ServletOutputStream out = response.getOutputStream();
		if(type != null && id != null) {
			byte[] pic = null;
			AlbumsBiz aBiz = new AlbumsBiz();
			if(type.equals("1")) {
				pic = aBiz.getAlbumPicByid(id);
				out.write(pic);
			}else if(type.equals("2")){
				pic = aBiz.getPicturePicByid(id);
				out.write(pic);
			}
		}
		out.flush();
		out.close();
	}
	
}
