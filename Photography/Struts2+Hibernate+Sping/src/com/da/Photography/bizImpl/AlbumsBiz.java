package com.da.Photography.bizImpl;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.da.Photography.biz.AlbumsBizInterface;
import com.da.Photography.dao.AlbumsDaoInterface;
import com.da.Photography.entity.PaAlbums;
import com.da.Photography.entity.PaPicture;
import com.da.Photography.util.Log;

public class AlbumsBiz implements AlbumsBizInterface{
	private AlbumsDaoInterface aDao;
	
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#getAlbums(long, java.lang.String)
	 */
	@Override
	public List<PaAlbums> getAlbums(long u_id, String stat) {
		List<PaAlbums> albums = new ArrayList<>();
		try {
			albums = aDao.queryAlbums(u_id,stat);
		} catch (Exception e) {
			Log.LOGGER.debug("查询专辑失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return albums;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#addAlbums(com.da.Photography.entity.PaAlbums)
	 */
	@Override
	public boolean addAlbums(PaAlbums albums) {
		boolean flag = false;
		try {
			albums.setAId(aDao.maxAlbId()+1);
			aDao.addAlbums(albums);
			flag = true;
		} catch (SQLException e) {
			Log.LOGGER.debug("创建专辑失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#getAlbumPicByid(java.lang.String)
	 */
	@Override
	public Blob getAlbumPicByid(String id) {
		Blob pic = null;
		try {
			pic = aDao.queryAlbumsPicById(id);
		} catch (SQLException e) {
			Log.LOGGER.debug("获取封面图片失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return pic;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#getPicturePicByid(java.lang.String)
	 */
	@Override
	public Blob getPicturePicByid(String id) {
		Blob pic = null;
		try {
			pic = aDao.queryPicturePicByid(id);
		} catch (SQLException e) {
			Log.LOGGER.debug("获取图片失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return pic;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#getAllAPicPicByaid(java.lang.String)
	 */
	@Override
	public List<Blob> getAllAPicPicByaid(String aid) {
		List<Blob> pics = new ArrayList<>();
		try {
			pics = aDao.getAllAPicPicByaid(aid);
		} catch (SQLException e) {
			Log.LOGGER.debug("获取专辑内所有图片的图片失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return pics;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#updatePic(byte[], java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updatePic(byte[] pic, String id, String type) {
		boolean flag = false;
		if(type.equals("1")) {
			try {
				int result = aDao.updateAPic(pic,id);
				if(result != 0) {
					flag = true;
				}
			} catch (SQLException e) {
				Log.LOGGER.debug("修改封面图片失败 : " + e.getMessage());
				e.printStackTrace();
			}
		}else {
			try {
				int result = aDao.updatePic(pic,id);
				if(result != 0) {
					flag = true;
				}
			} catch (SQLException e) {
				Log.LOGGER.debug("修改图片图片失败 : " + e.getMessage());
				e.printStackTrace();
			}
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#queryAlbumsById(java.lang.String)
	 */
	@Override
	public PaAlbums queryAlbumsById(String id) {
		PaAlbums album = null;
		try {
			album = aDao.queryAlbumsById(id);
		} catch (SQLException e) {
			Log.LOGGER.debug("查询专辑详情失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return album;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#updateAlbums(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateAlbums(String id, String name, String profile) {
		boolean flag = false;
		try {
			int result = aDao.updateAlbums(id, name, profile);
			if(result != 0) {
				flag = true;
			}
		} catch (SQLException e) {
			Log.LOGGER.debug("修改专辑信息失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#deleteAlbums(java.lang.String)
	 */
	@Override
	public boolean deleteAlbums(String id) {
		boolean flag = false;
		try {
			int result = aDao.deleteAlbums(id);
			if(result != 0)
				flag = true;
		} catch (SQLException e) {
			Log.LOGGER.debug("删除专辑失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#queryAPictureByAid(java.lang.String)
	 */
	@Override
	public PaAlbums queryAPictureByAid(String id) {
		PaAlbums album = null;
		try {
			album = aDao.queryAlbumsById(id);
			Set<PaPicture> pics = new HashSet<>();
			pics.addAll(aDao.queryAPictureByAid(id));
			album.setPaPictures(pics);
		} catch (SQLException e) {
			Log.LOGGER.debug("查询专辑图片失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return album;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#addPicture(com.da.Photography.entity.PaPicture)
	 */
	@Override
	public boolean addPicture(PaPicture p) {
		boolean flag = false;
		try {
			p.setPId(aDao.maxPicId()+1);
			aDao.addPicture(p);
			flag = true;
		} catch (SQLException e) {
			Log.LOGGER.debug("添加图片失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#queryPictureByAid(java.lang.String)
	 */
	@Override
	public PaPicture queryPictureByAid(String id) {
		PaPicture picture = null;
		try {
			picture = aDao.queryPictureByAid(id);
		} catch (SQLException e) {
			Log.LOGGER.debug("查询图片信息失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return picture;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#updatePicture(com.da.Photography.entity.PaPicture)
	 */
	@Override
	public boolean updatePicture(PaPicture picture) {
		boolean flag = false;
		try {
			int result = aDao.updatePicture(picture);
			if(result != 0)
				flag = true;
		} catch (SQLException e) {
			Log.LOGGER.debug("修改图片信息失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#deletePicture(java.lang.String)
	 */
	@Override
	public boolean deletePicture(String id) {
		boolean flag = false;
		try {
			int result = aDao.deletePicture(id);
			if(result != 0)
				flag = true;
		} catch (SQLException e) {
			Log.LOGGER.debug("删除图片失败 : " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.da.Photography.bizImpl.AlbumsBizInterface#queryCount()
	 */
	@Override
	public int[] queryCount() {
		int result[] = new int[3];
		try {
			for (int i = 0; i < 3; i++) {
				result[i] = aDao.queryCount(i+1);
			}
			result[2] += result[1];
		} catch (SQLException e) {
			Log.LOGGER.debug("查询条数失败" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public AlbumsDaoInterface getaDao() {
		return aDao;
	}
	public void setaDao(AlbumsDaoInterface aDao) {
		this.aDao = aDao;
	}

}
