package com.da.Photography.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.da.Photography.biz.AlbumsBiz;
import com.da.Photography.dto.Albums;
import com.da.Photography.dto.User;

public class AlbumsAction {
	
	/**
	 * 跳转地址
	 * 从页面过来时指定跳转的地址。
	 * 
	 */
	private String path;
	/**
	 * 各种编号传递
	 */
	private String id;
	/**
	 * 特定为图片编号使用
	 */
	private String pid; 
	/**
	 * 一般指处理类型，不同的类型查询不同的值
	 * getAllAblums（）方法中:存在值为主界面查看，不需要登录。null时为后台查看需要登录权限
	 */
	private String type; 
	/**
	 * 一般指状态，不同的状态查询不同的值
	 * getAllAblums（）方法中:1为用户查看，0为管理员查看
	 */
	private String stat;
	/**
	 * 专辑信息获取
	 */
	private Albums albums;
	
	/**
	 * 删除专辑
	 * @return
	 */
	public String deleteAlbums(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(id == null || id.equals("")) {
			request.setAttribute("result", "删除专辑失败,请注意合法操作");
		}else {
			AlbumsBiz aBiz = new AlbumsBiz();
			boolean flag = aBiz.deleteAlbums(id);
			if(flag) {
				request.setAttribute("result", "删除专辑成功");
			}else {
				request.setAttribute("result", "删除专辑失败,请先清空专辑内所有图片");
			}
		}
		return "deleteAlbums_True";
	}
	
	/**
	 * 删除图片
	 * @return
	 */
	public String deletePicture(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(pid == null || pid.equals("")) {
			request.setAttribute("result", "删除图片失败");
		}else {
			AlbumsBiz aBiz = new AlbumsBiz();
			boolean flag = aBiz.deletePicture(pid);
			if(flag) {
				request.setAttribute("result", "删除图片成功");
			}else {
				request.setAttribute("result", "删除图片失败");
			}
		}
		return "deletePicture_True";
	}
	
	/**
	 * 查询专辑内所有图片信息
	 * @return
	 */
	public String queryAlbumsPicture(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(id != null && !id.equals("")) {
			AlbumsBiz aBiz = new AlbumsBiz();
			Albums album = aBiz.queryAPictureByAid(id);
			request.setAttribute("album", album);
		}else {
			request.setAttribute("result", "查询失败");	
		}
		return "path";
	}
	/**
	 * 查询全部专辑
	 * @return
	 */
	public String getAllAblums(){
		String result = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if(type == null) {
			if(user == null || path == null || stat == null || path.equals("") || stat.equals("")) {
				if(stat == null || stat.equals("1")) {
					request.setAttribute("result", "请登录");
				}else {
					request.setAttribute("result", "请登录管理员账户");
				}
				result = "login";
			}else {
				if(!stat.equals("1")) {
					if(!user.getU_role().equals("0")) {
						request.setAttribute("result", "请登录管理员账户");
						result = "login";
						return result;
					}
				}
				AlbumsBiz aBiz = new AlbumsBiz();
				List<Albums> albums = aBiz.getAlbums(user.getU_id(),stat);
				request.setAttribute("albums", albums);
				result = "path";
			}
		}else {
			AlbumsBiz aBiz = new AlbumsBiz();
			List<Albums> albums = aBiz.getAlbums(0,"0");
			request.setAttribute("albums", albums);
			result = "path";
		}
		return result;
	}
	/**
	 * 修改专辑信息
	 * @return
	 */
	public String updateAlbums(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(albums.getA_profile() == null)
			albums.setA_profile("");
		//这里修改信息时专辑名称不能为空，编号是控制修改条件不能为空，但是专辑简介是可以为空的
		if(albums.getA_name() == null || albums.getA_name().equals("")) {
			request.setAttribute("result", "修改信息失败,除去简介不能为空");
		}else {
			AlbumsBiz aBiz = new AlbumsBiz();
			boolean flag = aBiz.updateAlbums(String.valueOf(albums.getA_id()),albums.getA_name(),albums.getA_profile());
			if(flag) {
				request.setAttribute("result", "修改信息成功");
			}else {
				request.setAttribute("result", "修改信息失败");
			}
		}
		return "alterAlbums";
	}
	
	/**
	 * 通过专辑编号查询专辑详情信息
	 * @return
	 */
	public String getAlbumsByaid(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(id != null && !id.equals("")) {
			AlbumsBiz aBiz = new AlbumsBiz();
			Albums album = aBiz.queryAlbumsById(id);
			if(album == null) {
				request.setAttribute("result", "查询详情信息失败");
			}else {
				request.setAttribute("album",album);
			}
		}else {
			request.setAttribute("result", "查询详情信息失败");
		}
		return "alterAlbums";
	}
	
	/**
	 * 查询条数
	 * @return
	 */
	public String getCount(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AlbumsBiz aBiz = new AlbumsBiz();
		int count[] = aBiz.queryCount();
		request.setAttribute("ca", count[0]);
		request.setAttribute("cu", count[1]);
		request.setAttribute("cp", count[2]);
		return "count";
	}
	
	
	
	
	
	
	
	

	public Albums getAlbums() {
		return albums;
	}

	public void setAlbums(Albums albums) {
		this.albums = albums;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}
	
	
}
