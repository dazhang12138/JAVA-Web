package com.da.Photography.daoImpl;

import java.sql.SQLException;
import java.util.List;

import com.da.Photography.dao.AlbumsDaoInterface;
import com.da.Photography.dto.Albums;
import com.da.Photography.dto.Picture;

public class AlbumsHibDao extends BaseHibDao implements AlbumsDaoInterface {
	/**
	 * 查询所有专辑
	 * @param u_id
	 * @param stat 为1的时候限制只查询特定用户创建专辑
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public List<Albums> queryAlbums(int u_id, String stat) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 添加专辑
	 * @param u_id 创建用户编号
	 * @param albums 专辑信息
	 * @return 返回创建结果
	 * @throws SQLException 
	 */
	@Override
	public int addAlbums(int u_id, Albums albums) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 查询专辑的封面图片通过专辑编号
	 * @param id 专辑编号
	 * @return 封面图片
	 * @throws SQLException 
	 */
	@Override
	public byte[] queryAlbumsPicById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 查询图片的图片通过图片编号
	 * @param id 图片编号
	 * @return 图片图片
	 * @throws SQLException 
	 */
	@Override
	public byte[] queryPicturePicByid(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 修改封面图片
	 * @param pic
	 * @param id
	 * @throws SQLException 
	 */
	@Override
	public int updateAPic(byte[] pic, String id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 修改图片图片
	 * @param pic
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int updatePic(byte[] pic, String id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 查询专辑详情通过专辑编号
	 * @param id 专辑编号
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public Albums queryAlbumsById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 修改专辑名称和介绍
	 * @param id
	 * @param name
	 * @param profile
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int updateAlbums(String id, String name, String profile) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 删除专辑
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int deleteAlbums(String id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 查询专辑内图片信息
	 * @param id 专辑编号
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Picture> queryAPictureByAid(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 添加图片信息
	 * @param p
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int addPicture(Picture p) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 查询图片详情信息 
	 * @param id 图片编号
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public Picture queryPictureByAid(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 修改图片信息
	 * @param p
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int updatePicture(Picture p) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 删除图片
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int deletePicture(String id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 查询条数
	 * @param type 类型：1专辑 2 用户 3图片
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int queryCount(int type) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 查询专辑内所有图片的图片
	 * @param aid
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<byte[]> getAllAPicPicByaid(String aid) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
