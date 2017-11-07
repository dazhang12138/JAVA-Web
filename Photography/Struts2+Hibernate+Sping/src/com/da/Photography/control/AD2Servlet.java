package com.da.Photography.control;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.AlbumsBizInterface;
import com.da.Photography.bizImpl.AlbumsBiz;

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
		AlbumsBizInterface aBiz = new AlbumsBiz();
		//获取数据
		List<Blob> pics = aBiz.getAllAPicPicByaid(aid);
		Blob apic = aBiz.getAlbumPicByid(aid);
		pics.add(apic);
		//字节数组流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//压缩流
		ZipOutputStream zos = new ZipOutputStream(baos);
		int i = 1;
		try {
			for (Blob blob : pics) {
				ZipEntry entry = new ZipEntry(i+".jpg");
				entry.setSize(blob.length());
				zos.putNextEntry(entry);
				BufferedInputStream is = new BufferedInputStream(blob.getBinaryStream());
				byte[] bs = new byte[(int) blob.length()];
				int len = bs.length,offset = 0,read = 0;
				while(offset < len && (read = is.read(bs,offset,len-offset))>=0){
					offset += read;
				}
				zos.write(bs);
				zos.closeEntry();
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
