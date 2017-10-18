package com.da.Photography.biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.da.Photography.dto.Albums;
import com.da.Photography.dto.Picture;
import com.da.Photography.dao.AlbumsDao;
import com.da.Photography.util.Log;

public class AlbumsBiz {
	/**
	 * 获取全部专辑信息
	 * @param u_id 用户编号
	 * @param stat 1为用户查看0为管理员查看
	 * @return
	 */
	public List<Albums> getAlbums(int u_id, String stat) {
		List<Albums> albums = new ArrayList<>();
		AlbumsDao aDao = new AlbumsDao();
		aDao.beginTran();
		try {
			albums = aDao.queryAlbums(u_id,stat);
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("查询专辑失败 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			aDao.closeAll();
		}
		return albums;
	}
	/**
	 * 创建专辑
	 * @param u_id 创建用户编号
	 * @param albums 专辑信息
	 * @return 是否成功创建专辑
	 */
	public boolean addAlbums(int u_id, Albums albums) {
		boolean flag = false;
		AlbumsDao aDao = new AlbumsDao();
		aDao.beginTran();
		try {
			int result = aDao.addAlbums(u_id,albums);
			if(result != 0)
				flag = true;
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("创建专辑失败 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			aDao.closeAll();
		}
		return flag;
	}
	/**
	 * 获取封面图片
	 * @param id 专辑编号
	 * @return 封面图片
	 */
	public byte[] getAlbumPicByid(String id) {
		AlbumsDao aDao = new AlbumsDao();
		aDao.beginTran();
		byte[] pic = null;
		try {
			pic = aDao.queryAlbumsPicById(id);
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("获取封面图片失败 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			aDao.closeAll();
		}
		return pic;
	}
	/**
	 * 获取图片图片
	 * @param id 图片编号
	 * @return 图片
	 */
	public byte[] getPicturePicByid(String id) {
		AlbumsDao aDao = new AlbumsDao();
		aDao.beginTran();
		byte[] pic = null;
		try {
			pic = aDao.queryPicturePicByid(id);
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("获取图片失败 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			aDao.closeAll();
		}
		return pic;
	}
	public List<byte[]> getAllAPicPicByaid(String aid) {
		AlbumsDao aDao = new AlbumsDao();
		aDao.beginTran();
		List<byte[]> pics = new ArrayList<>();
		try {
			pics = aDao.getAllAPicPicByaid(aid);
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("获取专辑内所有图片的图片失败 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			aDao.closeAll();
		}
		return pics;
	}
	/**
	 * 修改图片
	 * @param pic 图片
	 * @param id 修改id
	 * @param type 1时为修改专辑封面图片 0时为修改图片图片
	 * @return 返回是否成功修改
	 */
	public boolean updatePic(byte[] pic, String id, String type) {
		boolean flag = false;
		AlbumsDao aDao = new AlbumsDao();
		aDao.beginTran();
		if(type.equals("1")) {
			try {
				int result = aDao.updateAPic(pic,id);
				if(result != 0) {
					flag = true;
				}
				aDao.commitTran();
			} catch (SQLException e) {
				aDao.rollbackTran();
				Log.LOGGER.debug("修改封面图片失败 : " + e.getMessage());
				e.printStackTrace();
			}
		}else {
			try {
				int result = aDao.updatePic(pic,id);
				if(result != 0) {
					flag = true;
				}
				aDao.commitTran();
			} catch (SQLException e) {
				aDao.rollbackTran();
				Log.LOGGER.debug("修改图片图片失败 : " + e.getMessage());
				e.printStackTrace();
			}
		}
		return flag;
	}
	/**
	 * 查询专辑详情通过专辑编号
	 * @param id 专辑编号
	 * @return 返回专辑详情，null时为失败查询
	 */
	public Albums queryAlbumsById(String id) {
		Albums album = null;
		AlbumsDao aDao = new AlbumsDao();
		aDao.beginTran();
		try {
			album = aDao.queryAlbumsById(id);
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("查询专辑详情失败 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			aDao.closeAll();
		}
		return album;
	}
	/**
	 * 修改专辑信息
	 * @param id 专辑编号
	 * @param name 专辑姓名
	 * @param profile 专辑简介
	 * @return
	 */
	public boolean updateAlbums(String id, String name, String profile) {
		boolean flag = false;
		AlbumsDao aDao = new AlbumsDao();
		aDao.beginTran();
		try {
			int result = aDao.updateAlbums(id, name, profile);
			if(result != 0) {
				flag = true;
			}
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("修改专辑信息失败 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			aDao.closeAll();
		}
		return flag;
	}
	/**
	 * 删除专辑，同时删除专辑内全部图片
	 * @param id
	 * @return
	 */
	public boolean deleteAlbums(String id) {
		boolean flag = false;
		AlbumsDao aDao = new AlbumsDao();
		aDao.beginTran();
		try {
			int result = aDao.deleteAlbums(id);
			if(result != 0)
				flag = true;
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("删除专辑失败 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			aDao.closeAll();
		}
		return flag;
	}
	/**
	 * 获取专辑内图片
	 * @param id
	 * @return
	 */
	public Albums queryAPictureByAid(String id) {
		Albums album = null;
		AlbumsDao aDao = new AlbumsDao();
		aDao.beginTran();
		try {
			album = aDao.queryAlbumsById(id);
			album.setPictures(aDao.queryAPictureByAid(id));
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("查询专辑图片失败 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			aDao.closeAll();
		}
		return album;
	}
	/**
	 * 专辑中添加图片
	 * @param p 图片详情
	 * @return
	 */
	public boolean addPicture(Picture p) {
		boolean flag = false;
		AlbumsDao aDao = new AlbumsDao();
		aDao.beginTran();
		try {
			int result = aDao.addPicture(p);
			if(result != 0)
				flag = true;
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("添加图片失败 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			aDao.closeAll();
		}
		return flag;
	}
	/**
	 * 查询图片详情信息
	 * @param id 图片编号
	 * @return
	 */
	public Picture queryPictureByAid(String id) {
		Picture picture = null;
		AlbumsDao aDao = new AlbumsDao();
		aDao.beginTran();
		try {
			picture = aDao.queryPictureByAid(id);
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("查询图片信息失败 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			aDao.closeAll();
		}
		return picture;
	}
	/**
	 * 修改图片信息
	 * @param picture
	 * @return 
	 */
	public boolean updatePicture(Picture picture) {
		boolean flag = false;
		AlbumsDao aDao = new AlbumsDao();
		aDao.beginTran();
		try {
			int result = aDao.updatePicture(picture);
			if(result != 0)
				flag = true;
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("修改图片信息失败 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			aDao.closeAll();
		}
		return flag;
	}
	/**
	 * 删除图片
	 * @param id
	 * @return
	 */
	public boolean deletePicture(String id) {
		boolean flag = false;
		AlbumsDao aDao = new AlbumsDao();
		aDao.beginTran();
		try {
			int result = aDao.deletePicture(id);
			if(result != 0)
				flag = true;
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("删除图片失败 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			aDao.closeAll();
		}
		return flag;
	}
	/**
	 * 查询条数
	 * @param type
	 * @return
	 */
	public int[] queryCount() {
		AlbumsDao aDao = new AlbumsDao();
		int result[] = new int[3];
		aDao.beginTran();
		try {
			for (int i = 0; i < 3; i++) {
				result[i] = aDao.queryCount(i+1);
			}
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("查询条数失败" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

}
