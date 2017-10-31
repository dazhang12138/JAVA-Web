package com.da.Photography.control;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;

import com.da.Photography.biz.AlbumsBiz;
import com.da.Photography.entity.PaAlbums;
import com.da.Photography.entity.PaPicture;
import com.da.Photography.entity.PaUser;
import com.da.Photography.util.HibernateSessionFactory;
import com.da.Photography.util.Log;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 上传相关操作
 * @author Mr.Da
 *
 */
public class UploadAction extends ActionSupport {
	/**
	 * serialVersionUID = -3663438042790990237L
	 */
	private static final long serialVersionUID = -3663438042790990237L;

	private PaAlbums albums; //专辑信息
	private File pic; //专辑封面图片或图片图片
	private PaPicture picture; //图片信息
	
	/**
	 * 添加图片
	 * @return
	 */
	public String addPicture(){
		String result = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		PaUser user = (PaUser) request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("result", "请登录");
			result = "login";
		}else {
			try {
				String date = request.getParameter("date");
				if(picture.getPName() == null || picture.getPName().equals("")|| date == null || date.equals("")) {
					request.setAttribute("result", "除去介绍栏，不能为空。");
				}else {
					//设置时间
					SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
					picture.setPTime(format.parse(date));
					//设置图片
					byte[] pics = FileUtils.readFileToByteArray(pic);
					picture.setPPic(Hibernate.createBlob(pics));
					AlbumsBiz aBiz = new AlbumsBiz();
					boolean flag = aBiz.addPicture(picture);
					if(flag ) {
						request.setAttribute("result", "添加成功");
					}else {
						request.setAttribute("result", "添加失败");
					}
				}
			} catch (ParseException e) {
				Log.LOGGER.debug("时间转化失败");
				request.setAttribute("result", "时间转化失败");
				e.printStackTrace();
			} catch (IOException e) {
				Log.LOGGER.debug("上传图片失败");
				request.setAttribute("result", "上传图片失败");
				e.printStackTrace();
			}
			result = "addPicture_True";
			request.setAttribute("id", picture.getPaAlbums().getAId());
		}
		HibernateSessionFactory.closeSession();
		return result;
	}
	
	
	/**
	 * 创建专辑
	 * @return
	 */
	public String addAblums(){
		String result = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		PaUser user = (PaUser) request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("result", "请登录");
			result = "login";
		}else {
			try {
				if(pic != null){
					byte[] pics = FileUtils.readFileToByteArray(pic);
					albums.setAPic(Hibernate.createBlob(pics));
					if(albums.getAName() == null || albums.getAName().equals("")) {
						request.setAttribute("result", "专辑名不能为空");
					}else {
						AlbumsBiz aBiz = new AlbumsBiz();
						albums.setPaUser(new PaUser(user.getUId()));
						albums.setATime(new Date());
						boolean flag = aBiz.addAlbums(albums);
						if(flag) {
							request.setAttribute("result", "创建专辑成功");
						}else {
							request.setAttribute("result", "创建专辑失败");
						}
					}
				}else{
					request.setAttribute("result", "上传图片出错,图片不能为空");
				}
			} catch (IOException e) {
				Log.LOGGER.debug("上传图片封面出错 : " + e.getMessage());
				request.setAttribute("result", "上传图片出错");
				e.printStackTrace();
			}
			result = "addAblums_True";
		}
		HibernateSessionFactory.closeSession();
		return result;
	}
	
	/**
	 * 修改图片信息
	 * @return
	 */
	public String updatePictrue(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String date = request.getParameter("date");
		//这里除了简介可以为空，其他都不能空
		if(picture.getPProfile() == null)
			picture.setPProfile("");
		if(picture.getPName() == null || date == null || picture.getPName().equals("") || date.equals("")) {
			request.setAttribute("result", "修改信息失败");
		}else {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				picture.setPTime(format.parse(date));
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
		HibernateSessionFactory.closeSession();
		return "updatepictrue";
	}
	
	/**
	 * 修改图片(专辑封面或者图片图片)
	 * @return
	 */
	public String updatePic(){
		HttpServletRequest request = ServletActionContext.getRequest();
		byte[] pics = null;
		try {
			pics = FileUtils.readFileToByteArray(pic);
		} catch (IOException e) {
			Log.LOGGER.debug("上传图片失败 : " + e.getMessage());
			e.printStackTrace();
		}
		if(pics == null){
			request.setAttribute("result", "上传图片失败");
		}else{
			String type = request.getParameter("type");
			String id = request.getParameter("id");
			if (type != null && id != null) {
				AlbumsBiz aBiz = new AlbumsBiz();
				boolean flag = aBiz.updatePic(pics, id, type);
				if (flag) {
					request.setAttribute("result", "修改图片成功");
				} else {
					request.setAttribute("result", "修改图片失败");
				}
			} else {
				request.setAttribute("result", "修改图片失败");
			}
		}
		HibernateSessionFactory.closeSession();
		return "updatePic";
	}
	
	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public PaAlbums getAlbums() {
		return albums;
	}

	public void setAlbums(PaAlbums albums) {
		this.albums = albums;
	}

	public PaPicture getPicture() {
		return picture;
	}

	public void setPicture(PaPicture picture) {
		this.picture = picture;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
