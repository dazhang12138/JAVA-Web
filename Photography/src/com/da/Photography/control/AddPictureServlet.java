package com.da.Photography.control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;
import com.da.Photography.dto.Picture;
import com.da.Photography.util.Log;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * 添加图片
 * @author ZhangDa
 */
public class AddPictureServlet extends HttpServlet {

	/**
	 * serialVersionUID = -4258772286900916830L
	 */
	private static final long serialVersionUID = -4258772286900916830L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SmartUpload su = new SmartUpload();
		su.initialize(this.getServletConfig(),request,response);
		su.setCharset("utf-8");
		String aid = "";
		try {
			su.upload();
			File file = su.getFiles().getFile(0);
			int size = file.getSize();
			byte[] pic = new byte[size];
			for (int i = 0; i < pic.length; i++) {
				pic[i] = file.getBinaryData(i);
			}
			aid = su.getRequest().getParameter("aid");
			String name = su.getRequest().getParameter("name");
			String profile = su.getRequest().getParameter("profile");
			String price = su.getRequest().getParameter("price");
			String date = su.getRequest().getParameter("date");
			if(profile == null) {
				profile = "";
			}
			if(aid == null || aid.equals("") || name == null || name.equals("") || price == null
					|| price.equals("") || date == null || date.equals("")) {
				request.setAttribute("result", "除去介绍栏，不能为空。");
			}else {
				Picture p = new Picture();
				p.getAlbums().setA_id(Integer.valueOf(aid));
				p.setP_name(name);
				p.setP_profile(profile);
				p.setP_price(Integer.valueOf(price));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
				p.setP_time(format.parse(date));
				p.setP_pic(pic);
				AlbumsBiz aBiz = new AlbumsBiz();
				boolean flag = aBiz.addPicture(p);
				if(flag ) {
					request.setAttribute("result", "添加成功");
				}else {
					request.setAttribute("result", "添加失败");
				}
			}
		} catch (SmartUploadException e) {
			Log.LOGGER.debug("添加图片失败");
			request.setAttribute("result", "添加失败");
			e.printStackTrace();
		} catch (ParseException e) {
			Log.LOGGER.debug("时间转化失败");
			request.setAttribute("result", "添加失败");
			e.printStackTrace();
		}
		request.getRequestDispatcher("admin/addPicture.jsp?id="+aid).forward(request, response);
	}
}
