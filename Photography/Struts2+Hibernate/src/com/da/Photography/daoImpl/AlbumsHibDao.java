package com.da.Photography.daoImpl;

import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
		String hql = "from PaAlbums where 1=1 ";
		if(stat.equals("1")){
			hql += " and paUser.UId = " + u_id;
		}
		hql += " order by AId ";
		Query q = session.createQuery(hql);
		return q.list();
	}

	@Override
	public void addAlbums(PaAlbums albums) throws SQLException {
		session.save(albums);
		session.flush();
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
	/**
	 * 获取图片的图片
	 */
	@Override
	public Blob queryPicturePicByid(String id) throws SQLException {
		String hql = "from PaPicture where PId=" + id;
		Query q = session.createQuery(hql);
		PaPicture p = (PaPicture) q.list().get(0);
		return p.getPPic();
	}
	
	/**
	 * 修改封面图片
	 */
	@Override
	public int updateAPic(byte[] pic, String id) throws SQLException {
		String hql = "update PaAlbums set APic = ? where AId = ?";
		Query q = session.createQuery(hql);
		q.setBinary(0, pic);
		q.setString(1, id);
		int result = q.executeUpdate();
		return result;
	}
	/**
	 * 修改图片的图片
	 */
	@Override
	public int updatePic(byte[] pic, String id) throws SQLException {
		String hql = "update PaPicture set PPic = ? where PId = ?";
		Query q = session.createQuery(hql);
		q.setBinary(0, pic);
		q.setString(1, id);
		int result = q.executeUpdate();
		return result;
	}
	/**
	 * 查询专辑信息通过编号
	 */
	@Override
	public PaAlbums queryAlbumsById(String id) throws SQLException {
		PaAlbums albums = null;
		String hql = "from PaAlbums where AId=?";
		Query q = session.createQuery(hql);
		q.setString(0, id);
		List<PaAlbums> list = q.list();
		if(list.size()>0){
			albums = list.get(0);
		}
		return albums;
	}
	/**
	 * 修改专辑信息
	 */
	@Override
	public int updateAlbums(String id, String name, String profile) throws SQLException {
		String hql = "update PaAlbums set AName=?,AProfile=? where AId=?";
		Query q = session.createQuery(hql);
		q.setString(0, name);
		q.setString(1, profile);
		q.setString(2, id);
		int result = q.executeUpdate();
		return result;
	}
	/**
	 * 删除专辑
	 */
	@Override
	public int deleteAlbums(String id) throws SQLException {
		String hql = "delete PaAlbums where AId=" + id;
		Query q = session.createQuery(hql);
		int result = q.executeUpdate();
		return result;
	}
	/**
	 * 查询专辑内的图片信息
	 */
	@Override
	public List<PaPicture> queryAPictureByAid(String id) throws SQLException {
		String hql = "from PaPicture where paAlbums.AId=?";
		Query q = session.createQuery(hql);
		q.setString(0, id);
		return q.list();
	}

	/**
	 * 添加图片信息
	 */
	@Override
	public void addPicture(PaPicture p) throws SQLException {
		session.save(p);
		session.flush();
	}
	/**
	 * 查询图片详情信息
	 */
	@Override
	public PaPicture queryPictureByAid(String id) throws SQLException {
		String hql = "from PaPicture where PId=" + id;
		Query q = session.createQuery(hql);
		PaPicture p = (PaPicture) q.list().get(0);
		return p;
	}
	/**
	 * 修改图片信息
	 */
	@Override
	public int updatePicture(PaPicture p) throws SQLException {
		String hql = "update PaPicture set PName=?,PTime=to_date(?,'yyyy-mm-dd'),PPrice=?,PProfile=? "
				+ "where PId=?";
		Query q = session.createQuery(hql);
		q.setString(0, p.getPName());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(format.format(p.getPTime()));
		q.setString(1, format.format(p.getPTime()));
		q.setLong(2, p.getPPrice());
		q.setString(3, p.getPProfile());
		q.setLong(4, p.getPId());
		int result = q.executeUpdate();
		return result;
	}
	/**
	 * 删除图片
	 */
	@Override
	public int deletePicture(String id) throws SQLException {
		String hql = "delete PaPicture where PId=" + id;
		Query q = session.createQuery(hql);
		int result = q.executeUpdate();
		return result;
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
		List<Integer> list = sq.list();
		if(list.size()>0) {
			result = list.get(0);
		}
		return result;
	}
	/**
	 * 获取专辑内所有图片的图片
	 */
	@Override
	public List<Blob> getAllAPicPicByaid(String aid) throws SQLException {
		String sql = "Select t.P_PIC From PA_PICTURE t Where t.A_ID = " + aid;
		SQLQuery sq = session.createSQLQuery(sql);
		List<Blob> list = sq.list();
		return list;
	}
	/**
	 * 查询图片的最大编号
	 */
	@Override
	public long maxPicId() {
		String sql = "select nvl(max(p_id),0) from Pa_Picture";
		SQLQuery sq = session.createSQLQuery(sql);
		long result = Long.valueOf(sq.list().get(0).toString());
		return result;
	}
	/**
	 * 查询专辑的最大编号
	 */
	@Override
	public long maxAlbId() {
		String sql = "select nvl(max(a_id),0) from Pa_Albums";
		SQLQuery sq = session.createSQLQuery(sql);
		long result = Long.valueOf(sq.list().get(0).toString());
		return result;
	}
	

}
