package com.da.Photography.daoImpl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.da.Photography.dao.AlbumsDaoInterface;
import com.da.Photography.dto.Albums;
import com.da.Photography.dto.Picture;

public class AlbumsDao extends BaseDao implements AlbumsDaoInterface{
	/**
	 * 查询所有专辑
	 * @param u_id
	 * @param stat 为1的时候限制只查询特定用户创建专辑
	 * @return
	 * @throws SQLException 
	 */
	public List<Albums> queryAlbums(int u_id, String stat) throws SQLException {
		List<Albums> albums = new ArrayList<>();
		String sql = "Select a.A_ID, a.A_NAME, a.A_TIME, a.A_PROFILE, u.U_NAME "
				+ "From PA_ALBUMS a, PA_USER u "
				+ "Where u.U_ID = a.U_ID";
		if(stat.equals("1")) {
			sql += " And a.U_ID = " + u_id;
		}
		sql += " order by a_id ";
		rs = executeQuery(sql);
		while(rs.next()) {
			Albums a = new Albums();
			a.setA_id(rs.getInt("a_id"));
			a.setA_name(rs.getString("a_name"));
			a.setA_time(rs.getTimestamp("a_time"));
			a.setA_profile(rs.getString("a_profile"));
			a.getUser().setU_name(rs.getString("u_name"));
			albums.add(a);
		}
		return albums;
	}
	/**
	 * 添加专辑
	 * @param u_id 创建用户编号
	 * @param albums 专辑信息
	 * @return 返回创建结果
	 * @throws SQLException 
	 */
	public int addAlbums(int u_id, Albums albums) throws SQLException {
		int result = 0;
		String sql = "insert into pa_albums "
				+ "values((select nvl(max(a_id),0)+1 from pa_albums),?,?,sysdate,?,?)";
		Object[] params = {albums.getA_name(),u_id,albums.getA_profile(),albums.getA_pic()};
		result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 查询专辑的封面图片通过专辑编号
	 * @param id 专辑编号
	 * @return 封面图片
	 * @throws SQLException 
	 */
	public byte[] queryAlbumsPicById(String id) throws SQLException {
		byte[] pic = null;
		String sql = "Select A_PIC From PA_ALBUMS where a_id=?";
		Object[] params = {id};
		rs = executeQuery(sql, params);
		if(rs.next()) {
			pic = rs.getBytes("A_PIC");
		}
		return pic;
	}
	/**
	 * 查询图片的图片通过图片编号
	 * @param id 图片编号
	 * @return 图片图片
	 * @throws SQLException 
	 */
	public byte[] queryPicturePicByid(String id) throws SQLException {
		byte[] pic = null;
		String sql = "Select P_PIC From PA_PICTURE where p_id=?";
		Object[] params = {id};
		rs = executeQuery(sql, params);
		if(rs.next()) {
			pic = rs.getBytes("P_PIC");
		}
		return pic;
	}
	/**
	 * 修改封面图片
	 * @param pic
	 * @param id
	 * @throws SQLException 
	 */
	public int updateAPic(byte[] pic, String id) throws SQLException {
		String sql = "update pa_albums set a_pic=? where a_id=?";
		Object[] params = {pic,id};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 修改图片图片
	 * @param pic
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public int updatePic(byte[] pic, String id) throws SQLException {
		String sql = "update pa_picture set p_pic=? where p_id=?";
		Object[] params = {pic,id};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 查询专辑详情通过专辑编号
	 * @param id 专辑编号
	 * @return
	 * @throws SQLException 
	 */
	public Albums queryAlbumsById(String id) throws SQLException {
		Albums album = null;
		String sql = "select * from PA_ALBUMS a, PA_USER u where a_id=? and a.u_id = u.u_id";
		Object[] params = {id};
		rs = executeQuery(sql, params);
		if(rs.next()) {
			album = new Albums();
			album.setA_id(rs.getInt("a_id"));
			album.setA_name(rs.getString("a_name"));
			album.setA_profile(rs.getString("a_profile"));
			album.getUser().setU_name(rs.getString("u_name"));
		}
		return album;
	}
	/**
	 * 修改专辑名称和介绍
	 * @param id
	 * @param name
	 * @param profile
	 * @return
	 * @throws SQLException 
	 */
	public int updateAlbums(String id, String name, String profile) throws SQLException {
		String sql = "update pa_albums set a_name=?,a_profile=? where a_id=?";
		Object[] params = {name,profile,id};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 删除专辑
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deleteAlbums(String id) throws SQLException {
		String sql = "delete pa_albums where a_id=?";
		Object[] params = {id};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 查询专辑内图片信息
	 * @param id 专辑编号
	 * @return
	 * @throws SQLException
	 */
	public List<Picture> queryAPictureByAid(String id) throws SQLException {
		List<Picture> pics = new ArrayList<>();
		String sql = "Select *" + 
				" From PA_PICTURE t, PA_ALBUMS a Where a.A_ID = t.A_ID and a.A_ID = ?";
		Object[] params = {id};
		rs = executeQuery(sql, params);
		while(rs.next()) {
			Picture pic = new Picture();
			pic.setP_id(rs.getInt("p_id"));
			pic.setP_name(rs.getString("p_name"));
			pic.setP_time(rs.getDate("p_time"));
			pic.setP_price(rs.getInt("p_price"));
			pic.setP_profile(rs.getString("p_profile"));
			pics.add(pic);
		}
		return pics;
	}
	/**
	 * 添加图片信息
	 * @param p
	 * @return
	 * @throws SQLException
	 */
	public int addPicture(Picture p) throws SQLException {
		int result = 0;
		String sql = "insert into pa_picture values((select nvl(max(p_id),0)+1 from pa_picture),"
				+ "?,?,to_date(?,'yyyy-mm-dd'),?,?,?)";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Object[] params = {p.getAlbums().getA_id(),p.getP_name(),format.format(p.getP_time()),p.getP_pic(),p.getP_price(),
				p.getP_profile()};
		result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 查询图片详情信息 
	 * @param id 图片编号
	 * @return
	 * @throws SQLException 
	 */
	public Picture queryPictureByAid(String id) throws SQLException {
		Picture  p = null;
		String sql = "select * from PA_PICTURE where p_id=?";
		Object[] params = {id};
		rs = executeQuery(sql, params);
		if(rs.next()) {
			p = new Picture();
			p.setP_id(rs.getInt("p_id"));
			p.setP_name(rs.getString("p_name"));
			p.setP_price(rs.getInt("p_price"));
			p.setP_profile(rs.getString("p_profile"));
			p.setP_time(rs.getDate("p_time"));
		}
		return p;
	}
	/**
	 * 修改图片信息
	 * @param p
	 * @return
	 * @throws SQLException
	 */
	public int updatePicture(Picture p) throws SQLException {
		String sql = "update pa_picture set p_name=?,p_time=to_date(?,'yyyy-mm-dd'),p_price=?,p_profile=? "
				+ "where p_id=?";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = {p.getP_name(),format.format(p.getP_time()),p.getP_price(),p.getP_profile(),p.getP_id()};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 删除图片
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public int deletePicture(String id) throws SQLException {
		String sql = "delete pa_picture where p_id=?";
		Object[] params = {id};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 查询条数
	 * @param type 类型：1专辑 2 用户 3图片
	 * @return
	 * @throws SQLException
	 */
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
		rs = executeQuery(sql);
		if(rs.next())
			result = rs.getInt("c");
		return result;
	}
	/**
	 * 查询专辑内所有图片的图片
	 * @param aid
	 * @return
	 * @throws SQLException
	 */
	public List<byte[]> getAllAPicPicByaid(String aid) throws SQLException {
		List<byte[]> pics = new ArrayList<>();
		String sql = "Select t.P_PIC From PA_PICTURE t Where t.A_ID = ?";
		Object[] params = {aid};
		rs = executeQuery(sql, params);
		while(rs.next()) {
			byte[] p = rs.getBytes("P_PIC");
			pics.add(p);
		}
		return pics;
	}
}
