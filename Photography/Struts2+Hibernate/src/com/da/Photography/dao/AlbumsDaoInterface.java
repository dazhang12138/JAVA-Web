package com.da.Photography.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import com.da.Photography.entity.PaAlbums;
import com.da.Photography.entity.PaPicture;


public interface AlbumsDaoInterface extends BaseDaoInterface{
	//查询所有专辑
	public List<PaAlbums> queryAlbums(long u_id, String stat) throws Exception;
	//添加专辑
	public void addAlbums(PaAlbums albums) throws SQLException;
	//查询专辑的封面图片通过专辑编号
	public Blob queryAlbumsPicById(String id) throws SQLException;
	//查询图片的图片通过图片编号
	public Blob queryPicturePicByid(String id) throws SQLException;
	//修改封面图片
	public int updateAPic(byte[] pic, String id) throws SQLException;
	// 修改图片图片
	public int updatePic(byte[] pic, String id) throws SQLException;
	// 查询专辑详情通过专辑编号
	public PaAlbums queryAlbumsById(String id) throws SQLException ;
	//修改专辑名称和介绍
	public int updateAlbums(String id, String name, String profile) throws SQLException;
	// 删除专辑
	public int deleteAlbums(String id) throws SQLException;
	// 查询专辑内图片信息
	public List<PaPicture> queryAPictureByAid(String id) throws SQLException ;
	//添加图片信息
	public void addPicture(PaPicture p) throws SQLException;
	//查询图片详情信息 
	public PaPicture queryPictureByAid(String id) throws SQLException;
	//修改图片信息
	public int updatePicture(PaPicture p) throws SQLException;
	//删除图片
	public int deletePicture(String id) throws SQLException;
	//查询条数
	public int queryCount(int type) throws SQLException ;
	//查询专辑内所有图片的图片
	public List<Blob> getAllAPicPicByaid(String aid) throws SQLException;
	
	public long maxPicId();
	public long maxAlbId();
	
}
