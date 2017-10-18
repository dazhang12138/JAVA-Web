package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;
import com.da.Photography.util.Log;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * 更新图片，专辑封面和图片图片
 * @author ZhangDa
 *
 */
public class UpdatePicServlet extends HttpServlet {

	/**
	 * serialVersionUID = -824270480941415902L
	 */
	private static final long serialVersionUID = -824270480941415902L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SmartUpload su = new SmartUpload();
		su.initialize(this.getServletConfig(),request,response);
		su.setCharset("utf-8");  
		try {
			su.upload();
			File file = su.getFiles().getFile(0);
			int size = file.getSize();
			byte[] pic = new byte[size];
			for (int i = 0; i < pic.length; i++) {
				pic[i] = file.getBinaryData(i);
			}
			String type = request.getParameter("type");
			String id = request.getParameter("id");
			if(type != null && id != null) {
				AlbumsBiz aBiz = new AlbumsBiz();
				boolean flag = aBiz.updatePic(pic,id,type);
				if(flag) {
					request.setAttribute("result", "修改图片成功");
				}else {
					request.setAttribute("result", "修改图片失败");
				}
			}else {
				request.setAttribute("result", "修改图片失败");
			}
		} catch (SmartUploadException e) {
			Log.LOGGER.debug("修改图片失败 : " + e.getMessage());
			request.setAttribute("result", "修改图片失败");
			e.printStackTrace();
		}
		request.getRequestDispatcher("admin/pic.jsp").forward(request, response);
	}
}
