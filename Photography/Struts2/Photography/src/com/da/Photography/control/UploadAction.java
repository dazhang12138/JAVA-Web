package com.da.Photography.control;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.da.Photography.biz.AlbumsBiz;
import com.da.Photography.dto.Albums;
import com.da.Photography.dto.Picture;
import com.da.Photography.dto.User;
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

	private Albums albums; //专辑信息
	private File pic; //专辑封面图片或图片图片
	private Picture picture; //图片信息
	
	/**
	 * 添加图片
	 * @return
	 */
	public String addPicture(){
		String result = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("result", "请登录");
			result = "login";
		}else {
			try {
				String date = request.getParameter("date");
				if(picture.getP_name() == null || picture.getP_name().equals("")|| date == null || date.equals("")) {
					request.setAttribute("result", "除去介绍栏，不能为空。");
				}else {
					//设置时间
					SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
					picture.setP_time(format.parse(date));
					//设置图片
					byte[] pics = FileUtils.readFileToByteArray(pic);
					picture.setP_pic(pics);
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
			request.setAttribute("id", picture.getAlbums().getA_id());
		}
		return result;
	}
	
	
	/**
	 * 创建专辑
	 * @return
	 */
	public String addAblums(){
		String result = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("result", "请登录");
			result = "login";
		}else {
			try {
				if(pic != null){
					byte[] pics = FileUtils.readFileToByteArray(pic);
					albums.setA_pic(pics);
					if(albums.getA_name() == null || albums.getA_name().equals("")) {
						request.setAttribute("result", "专辑名不能为空");
					}else {
						AlbumsBiz aBiz = new AlbumsBiz();
						boolean flag = aBiz.addAlbums(user.getU_id(),albums);
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
		return result;
	}
	
	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public Albums getAlbums() {
		return albums;
	}

	public void setAlbums(Albums albums) {
		this.albums = albums;
	}


	public Picture getPicture() {
		return picture;
	}


	public void setPicture(Picture picture) {
		this.picture = picture;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
