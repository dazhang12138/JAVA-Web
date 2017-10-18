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

/**
 * 修改图片信息
 * @author ZhangDa
 *
 */
public class UpdatePictureServlet extends HttpServlet {

	/**
	 * serialVersionUID = 6527586826028803560L
	 */
	private static final long serialVersionUID = 6527586826028803560L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String profile = request.getParameter("profile");
		String price = request.getParameter("price");
		String date = request.getParameter("date");
		//这里除了简介可以为空，其他都不能空
		if(profile == null)
			profile = "";
		if(id == null || name == null || price == null || date == null
				|| id.equals("") || name.equals("") || price.equals("") || date.equals("")) {
			request.setAttribute("result", "修改信息失败");
		}else {
			Picture picture = new Picture();
			picture.setP_id(Integer.valueOf(id));
			picture.setP_name(name);
			picture.setP_price(Integer.valueOf(price));
			picture.setP_profile(profile);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				picture.setP_time(format.parse(date));
			} catch (ParseException e) {
				Log.LOGGER.debug("时间转换失败");
				e.printStackTrace();
			}
			AlbumsBiz aBiz = new AlbumsBiz();
			boolean flag = aBiz.updatePicture(picture);
			if(flag) {
				request.setAttribute("result", "修改信息成功");
			}else {
				request.setAttribute("result", "修改信息失败");
			}
		}
		request.getRequestDispatcher("admin/alterPicture.jsp").forward(request, response);
	}
}
