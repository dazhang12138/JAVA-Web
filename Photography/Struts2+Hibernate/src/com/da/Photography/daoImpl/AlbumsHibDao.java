package com.da.Photography.daoImpl;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.da.Photography.dao.AlbumsDaoInterface;
import com.da.Photography.entity.PaAlbums;
import com.da.Photography.entity.PaPicture;

@SuppressWarnings("unchecked")
public class AlbumsHibDao extends BaseHibDao implements AlbumsDaoInterface {
	/**
	 * 查询全部专辑
	 */
	@Override
	public List<PaAlbums> queryAlbums(long u_id, String stat) throws Exception{
		String hql = "from PaAlbums";
		Query q = session.createQuery(hql);
		return q.list();
	}

	@Override
	public void addAlbums(int u_id, PaAlbums albums) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 获取封面图片通过专辑的编号
	 */
	@Override
	public Blob queryAlbumsPicById(String id) throws SQLException {
		String hql = "from PaAlbums where AId=" + id;
		Query q = session.createQuery(hql);
		PaAlbums a = (PaAlbums) q.list().get(0);
		return a.getAPic();
	}

	@Override
	public byte[] queryPicturePicByid(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateAPic(byte[] pic, String id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePic(byte[] pic, String id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PaAlbums queryAlbumsById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateAlbums(String id, String name, String profile) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAlbums(String id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PaPicture> queryAPictureByAid(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addPicture(PaPicture p) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PaPicture queryPictureByAid(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePicture(PaPicture p) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePicture(String id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 查询用户个数、专辑数量、图片总数量
	 */
	@Override
	public int queryCount(int type) throws SQLException {
		int result = 0;
		String sql = "";
		switch (type) {
		case 1:
			sql = "select count(a_id) c from PA_ALBUMS";
			break;
		case 2:
			sql = "select count(u_id) c from PA_USER";
			break;
		case 3: 
			sql = "select count(p_id) c from PA_PICTURE";
		}
		SQLQuery sq = session.createSQLQuery(sql);
		List list = sq.list();
		if(list.size()>0) {
			Object obj = list.get(0);
			result =  Integer.valueOf(obj.toString());
		}
		return result;
	}

	@Override
	public List<byte[]> getAllAPicPicByaid(String aid) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}
