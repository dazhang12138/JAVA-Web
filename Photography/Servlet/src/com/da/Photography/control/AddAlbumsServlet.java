package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;
import com.da.Photography.util.Log;
import com.da.Photography.dto.Albums;
import com.da.Photography.dto.User;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class AddAlbumsServlet extends HttpServlet{
	/**
	 * serialVersionUID = 8062831740625844401L
	 */
	private static final long serialVersionUID = 8062831740625844401L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("result", "请登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			SmartUpload su = new SmartUpload();
			su.initialize(this.getServletConfig(), request,response);
			su.setCharset("utf-8");
			try {
				su.upload();
				File file = su.getFiles().getFile(0);
				int size = file.getSize();
				byte[] pic = new byte[size];
				for (int i = 0; i < pic.length; i++) {
					pic[i] = file.getBinaryData(i);
				}
				String name = su.getRequest().getParameter("name");
				String profile = su.getRequest().getParameter("profile");
				if(profile == null) {
					profile = "";
				}
				if(name == null || name.equals("")) {
					request.setAttribute("result", "专辑名不能为空");
				}else {
					Albums albums = new Albums();
					albums.setA_name(name);
					albums.setA_profile(profile);
					albums.setA_pic(pic);
					AlbumsBiz aBiz = new AlbumsBiz();
					boolean flag = aBiz.addAlbums(user.getU_id(),albums);
					if(flag) {
						request.setAttribute("result", "创建专辑成功");
					}else {
						request.setAttribute("result", "创建专辑失败");
					}
				}
			} catch (SmartUploadException e) {
				Log.LOGGER.debug("创建图片专辑失败");
				request.setAttribute("result", "创建专辑失败");
				e.printStackTrace();
			}
			request.getRequestDispatcher("admin/addAbums.jsp").forward(request, response);
		}
	}
}
