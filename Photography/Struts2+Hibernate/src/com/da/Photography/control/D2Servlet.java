package com.da.Photography.control;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;
import com.da.Photography.util.HibernateSessionFactory;

/**
 * 下载图片
 * @author ZhangDa
 *
 */
public class D2Servlet extends HttpServlet {

	/**
	 * serialVersionUID = -7995197851227257428L
	 */
	private static final long serialVersionUID = -7995197851227257428L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		AlbumsBiz aBiz = new AlbumsBiz();
		//获取数据库图片通过图片编号
		Blob pics = aBiz.getPicturePicByid(pid);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment; filename=" + pid + ".jpg");
		ServletOutputStream out = response.getOutputStream();
		try {
			out.write(pics.getBytes(0, (int) pics.length()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HibernateSessionFactory.closeSession();
		out.flush();
		out.close();
	}

}
