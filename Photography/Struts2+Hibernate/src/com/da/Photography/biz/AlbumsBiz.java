package com.da.Photography.biz;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.da.Photography.dao.AlbumsDaoInterface;
import com.da.Photography.daoImpl.AlbumsHibDao;
import com.da.Photography.entity.PaAlbums;
import com.da.Photography.entity.PaPicture;
import com.da.Photography.util.Log;

public class AlbumsBiz {
	/**
	 * 获取全部专辑信息
 	 * @param u_id 用户编号
	 * @param stat 查询状态，为1时限制查询只查询一个用户的专辑信息。其他时候查询全部的专辑信息
	 * @return 返回专辑的集合
	 */
	public List<PaAlbums> getAlbums(long u_id, String stat) {
		List<PaAlbums> albums = new ArrayList<>();
		AlbumsDaoInterface aDao = new AlbumsHibDao();
		aDao.beginTran();
		try {
			albums = aDao.queryAlbums(u_id,stat);
			aDao.commitTran();
		} catch (Exception e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("查询专辑失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return albums;
	}
	/**
	 * 创建新的专辑
	 * @param albums 新专辑的信息
	 * @return 返回是否成功创建专辑
	 */
	public boolean addAlbums(PaAlbums albums) {
		boolean flag = false;
		AlbumsDaoInterface aDao = new AlbumsHibDao();
		aDao.beginTran();
		try {
			albums.setAId(aDao.maxAlbId()+1);
			aDao.addAlbums(albums);
			flag = true;
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("创建专辑失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 查询获取专辑的封面图片通过专辑编号
	 * @param id 专辑编号
	 * @return 返回Blob格式的封面图片
	 */
	public Blob getAlbumPicByid(String id) {
		AlbumsDaoInterface aDao = new AlbumsHibDao();
		aDao.beginTran();
		Blob pic = null;
		try {
			pic = aDao.queryAlbumsPicById(id);
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("获取封面图片失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return pic;
	}
	/**
	 * 查询获取图片表的图片通过图片编号
	 * @param id 图片编号
	 * @return 返回Blob格式的图片
	 */
	public Blob getPicturePicByid(String id) {
		AlbumsDaoInterface aDao = new AlbumsHibDao();
		aDao.beginTran();
		Blob pic = null;
		try {
			pic = aDao.queryPicturePicByid(id);
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("获取图片失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return pic;
	}
	/**
	 * 查询一个专辑的里面的全部图片的图片
	 * @param aid 专辑编号
	 * @return 返回图片的Blob格式集合
	 */
	public List<Blob> getAllAPicPicByaid(String aid) {
		AlbumsDaoInterface aDao = new AlbumsHibDao();
		aDao.beginTran();
		List<Blob> pics = new ArrayList<>();
		try {
			pics = aDao.getAllAPicPicByaid(aid);
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("获取专辑内所有图片的图片失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return pics;
	}
	/**
	 * 修改更新图片（专辑封面或者专辑内图片的图片）
	 * @param pic 图片
	 * @param id 编号，如果type为1的时候为专辑编号，否则为图片编号
	 * @param type 修改类型，为1的时候修改专辑封面图片，否则修改专辑内的图片；
	 * @return 返回是否更新图片成功
	 */
	public boolean updatePic(byte[] pic, String id, String type) {
		boolean flag = false;
		AlbumsDaoInterface aDao = new AlbumsHibDao();
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
	 * 通过专辑编号查询专辑的详细信息
	 * @param id 专辑编号
	 * @return 返回专辑信息
	 */
	public PaAlbums queryAlbumsById(String id) {
		PaAlbums album = null;
		AlbumsDaoInterface aDao = new AlbumsHibDao();
		aDao.beginTran();
		try {
			album = aDao.queryAlbumsById(id);
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("查询专辑详情失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return album;
	}
	/**
	 * 更新修改专辑的信息
	 * @param id 专辑编号
	 * @param name 专辑名称
	 * @param profile 专辑简介
	 * @return 返回是否修改专辑信息成功
	 */
	public boolean updateAlbums(String id, String name, String profile) {
		boolean flag = false;
		AlbumsDaoInterface aDao = new AlbumsHibDao();
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
		}
		return flag;
	}
	/**
	 * 通过专辑编号删除一个专辑（为级联删除，同时删除专辑内的所有图片记录以及对下载的所有记录。）
	 * @param id 专辑编号
	 * @return 返回是否成功删除专辑
	 */
	public boolean deleteAlbums(String id) {
		boolean flag = false;
		AlbumsDaoInterface aDao = new AlbumsHibDao();
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
		}
		return flag;
	}
	/**
	 * 通过专辑编号查询专辑内的所有图片信息
	 * @param id 专辑编号
	 * @return 返回一个专辑的实体类。主要用里面的图片集合以及专辑名称
	 */
	public PaAlbums queryAPictureByAid(String id) {
		PaAlbums album = null;
		AlbumsDaoInterface aDao = new AlbumsHibDao();
		aDao.beginTran();
		try {
			album = aDao.queryAlbumsById(id);
			Set<PaPicture> pics = new HashSet<>();
			pics.addAll(aDao.queryAPictureByAid(id));
			album.setPaPictures(pics);
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("查询专辑图片失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return album;
	}
	/**
	 * 添加新的图片
	 * @param p 图片信息
	 * @return 返回是否成功添加图片信心
	 */
	public boolean addPicture(PaPicture p) {
		boolean flag = false;
		AlbumsDaoInterface aDao = new AlbumsHibDao();
		aDao.beginTran();
		try {
			p.setPId(aDao.maxPicId()+1);
			aDao.addPicture(p);
			flag = true;
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("添加图片失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 通过图片编号查询图片信息。
	 * @param id 图片编号
	 * @return 返回图片信息
	 */
	public PaPicture queryPictureByAid(String id) {
		PaPicture picture = null;
		AlbumsDaoInterface aDao = new AlbumsHibDao();
		aDao.beginTran();
		try {
			picture = aDao.queryPictureByAid(id);
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("查询图片信息失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return picture;
	}
	/**
	 * 修改图片信息
	 * @param picture 新的图片信息
	 * @return 返回是否成功更新图片信息
	 */
	public boolean updatePicture(PaPicture picture) {
		boolean flag = false;
		AlbumsDaoInterface aDao = new AlbumsHibDao();
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
		}
		return flag;
	}
	/**
	 * 通过图片编号删除一张图片记录
	 * @param id 图片编号
	 * @return 返回是否成功删除一张图片
	 */
	public boolean deletePicture(String id) {
		boolean flag = false;
		AlbumsDaoInterface aDao = new AlbumsHibDao();
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
		}
		return flag;
	}
	/**
	 * 查询记录数
	 * 分别查询图片表、用户表、专辑表的所有记录条数
	 * 其中图片的记录条数为图片表的记录数加上专辑标的记录数
	 * @return
	 */
	public int[] queryCount() {
		AlbumsDaoInterface aDao = new AlbumsHibDao();
		int result[] = new int[3];
		aDao.beginTran();
		try {
			for (int i = 0; i < 3; i++) {
				result[i] = aDao.queryCount(i+1);
			}
			result[2] += result[1];
			aDao.commitTran();
		} catch (SQLException e) {
			aDao.rollbackTran();
			Log.LOGGER.debug("查询条数失败" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

}
