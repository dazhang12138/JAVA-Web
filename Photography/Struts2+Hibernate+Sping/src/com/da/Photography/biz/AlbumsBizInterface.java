package com.da.Photography.biz;

import java.sql.Blob;
import java.util.List;

import com.da.Photography.entity.PaAlbums;
import com.da.Photography.entity.PaPicture;

public interface AlbumsBizInterface {

	/**
	 * 获取全部专辑信息
	 * @param u_id 用户编号
	 * @param stat 查询状态，为1时限制查询只查询一个用户的专辑信息。其他时候查询全部的专辑信息
	 * @return 返回专辑的集合
	 */
	List<PaAlbums> getAlbums(long u_id, String stat);

	/**
	 * 创建新的专辑
	 * @param albums 新专辑的信息
	 * @return 返回是否成功创建专辑
	 */
	boolean addAlbums(PaAlbums albums);

	/**
	 * 查询获取专辑的封面图片通过专辑编号
	 * @param id 专辑编号
	 * @return 返回Blob格式的封面图片
	 */
	Blob getAlbumPicByid(String id);

	/**
	 * 查询获取图片表的图片通过图片编号
	 * @param id 图片编号
	 * @return 返回Blob格式的图片
	 */
	Blob getPicturePicByid(String id);

	/**
	 * 查询一个专辑的里面的全部图片的图片
	 * @param aid 专辑编号
	 * @return 返回图片的Blob格式集合
	 */
	List<Blob> getAllAPicPicByaid(String aid);

	/**
	 * 修改更新图片（专辑封面或者专辑内图片的图片）
	 * @param pic 图片
	 * @param id 编号，如果type为1的时候为专辑编号，否则为图片编号
	 * @param type 修改类型，为1的时候修改专辑封面图片，否则修改专辑内的图片；
	 * @return 返回是否更新图片成功
	 */
	boolean updatePic(byte[] pic, String id, String type);

	/**
	 * 通过专辑编号查询专辑的详细信息
	 * @param id 专辑编号
	 * @return 返回专辑信息
	 */
	PaAlbums queryAlbumsById(String id);

	/**
	 * 更新修改专辑的信息
	 * @param id 专辑编号
	 * @param name 专辑名称
	 * @param profile 专辑简介
	 * @return 返回是否修改专辑信息成功
	 */
	boolean updateAlbums(String id, String name, String profile);

	/**
	 * 通过专辑编号删除一个专辑（为级联删除，同时删除专辑内的所有图片记录以及对下载的所有记录。）
	 * @param id 专辑编号
	 * @return 返回是否成功删除专辑
	 */
	boolean deleteAlbums(String id);

	/**
	 * 通过专辑编号查询专辑内的所有图片信息
	 * @param id 专辑编号
	 * @return 返回一个专辑的实体类。主要用里面的图片集合以及专辑名称
	 */
	PaAlbums queryAPictureByAid(String id);

	/**
	 * 添加新的图片
	 * @param p 图片信息
	 * @return 返回是否成功添加图片信心
	 */
	boolean addPicture(PaPicture p);

	/**
	 * 通过图片编号查询图片信息。
	 * @param id 图片编号
	 * @return 返回图片信息
	 */
	PaPicture queryPictureByAid(String id);

	/**
	 * 修改图片信息
	 * @param picture 新的图片信息
	 * @return 返回是否成功更新图片信息
	 */
	boolean updatePicture(PaPicture picture);

	/**
	 * 通过图片编号删除一张图片记录
	 * @param id 图片编号
	 * @return 返回是否成功删除一张图片
	 */
	boolean deletePicture(String id);

	/**
	 * 查询记录数
	 * 分别查询图片表、用户表、专辑表的所有记录条数
	 * 其中图片的记录条数为图片表的记录数加上专辑标的记录数
	 * @return
	 */
	int[] queryCount();

}