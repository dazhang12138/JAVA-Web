package com.da.Photography.control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBiz;

/**
 * 下载图片
 * @author ZhangDa
 *
 */
public class AD2Servlet extends HttpServlet {

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
		String aid = request.getParameter("aid");
		AlbumsBiz aBiz = new AlbumsBiz();
		//获取数据
		List<byte[]> pics = aBiz.getAllAPicPicByaid(aid);
		byte[] apic = aBiz.getAlbumPicByid(aid);
		pics.add(apic);
		//字节数组流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//压缩流
		ZipOutputStream zos = new ZipOutputStream(baos);
		int i = 1;
		for (byte[] bs : pics) {
			ZipEntry entry = new ZipEntry(i+".jpg");
			entry.setSize(bs.length);
			zos.putNextEntry(entry);
			zos.write(bs);
			zos.closeEntry();
			i++;
		}
		zos.close();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment; filename=Album" + aid + ".zip");
		ServletOutputStream out = response.getOutputStream();
		out.write(baos.toByteArray());
		out.flush();
		out.close();
	}

}
