package com.da.Photography.control;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;
import com.da.Photography.util.Log;

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
				pic = logoPic(pic);
				out.write(pic);
			}
		}
		out.flush();
		out.close();
	}
	/**
	 * 图片加密
	 * @param pic
	 * @return
	 */
	private byte[] logoPic(byte[] pic) {
		ByteArrayInputStream in = new  ByteArrayInputStream(pic);
		BufferedImage bimage = null;
		try {
			bimage = ImageIO.read(in);
		} catch (IOException e) {
			Log.LOGGER.debug("转化图片出错 ： " + e.getMessage());
			e.printStackTrace();
		}
		int width = bimage.getWidth();
		int heigh = bimage.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < heigh; j++) {
				if(j%100 == 0) {
					bimage.setRGB(i, j, 0xFFFFFF);
				}
				if(i%100 == 0) {
					bimage.setRGB(i, j, 0xFFFFFF);
				}
			}
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ImageIO.write(bimage, "jpg", os);
		} catch (IOException e) {
			Log.LOGGER.debug("转化图片出错 ： " + e.getMessage());
			e.printStackTrace();
		}
		pic = os.toByteArray();
		return pic;
	}
}
