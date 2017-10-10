package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;

/**
 * 查询条数
 * @author ZhangDa
 *
 */
public class GetCountServlet extends HttpServlet {

	/**
	 * serialVersionUID = 3855853539215423751L
	 */
	private static final long serialVersionUID = 3855853539215423751L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AlbumsBiz aBiz = new AlbumsBiz();
		int count[] = aBiz.queryCount();
		request.setAttribute("ca", count[0]);
		request.setAttribute("cu", count[1]);
		request.setAttribute("cp", count[2]);
		request.getRequestDispatcher("about.jsp").forward(request, response);
	}

}
