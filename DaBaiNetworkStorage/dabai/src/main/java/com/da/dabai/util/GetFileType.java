package com.da.dabai.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 匹配文件类型
 * @author Mr.Da
 * 
 */

public class GetFileType {
	/**
	 * 常用图片后缀集合
	 */
	public static Set<String> picture = new HashSet<>();
	/**
	 * 常用视频后缀集合
	 */
	public static Set<String> video = new HashSet<>();
	/**
	 * 常用音频后缀集合
	 */
	public static Set<String> music = new HashSet<>();
	/**
	 * 常用文档后缀集合
	 */
	public static Set<String> documents = new HashSet<>();
	
	/**
	 * 通过文件名称后后缀对比返回此文件所属类型
	 * @param fileNameSuffix 文件名后缀
	 * @return 返回文件类型
	 */
	public static FileType getTypeBySuffix(String fileNameSuffix){
		fileNameSuffix = fileNameSuffix.toLowerCase();
		FileType type = FileType.OTHERS;
		if(picture.contains(fileNameSuffix)){
			type = FileType.PICTURE;
		}else if(video.contains(fileNameSuffix)){
			type = FileType.VIDEO;
		}else if(music.contains(fileNameSuffix)){
			type = FileType.MUSIC;
		}else if(documents.contains(fileNameSuffix)){
			type = FileType.DOCUMENTS;
		}else{
			type = FileType.OTHERS;
		}
		return type;
	}
	
	/**
	 * 通过文件名称返回此文件所属的类型
	 * @param fileName 文件名称
	 * @return 返回文件类型
	 */
	public static FileType getType(String fileName){
		FileType type = FileType.OTHERS;
		String[] split = fileName.split("\\.");
		if(split.length > 0){
			String fileNameSuffix = split[split.length-1];
			type = getTypeBySuffix(fileNameSuffix);
		}
		return type;
	}
	
	public GetFileType() {
		/**
		 * 常用图片格式文件匹配后缀名
		 */
		picture.add("bmp");
		picture.add("dib");
		picture.add("pcp");
		picture.add("eps");
		picture.add("iff");
		picture.add("mpt");
		picture.add("gif");
		picture.add("png");
		picture.add("tif");
		picture.add("tiff");
		picture.add("cdr");
		picture.add("wmf");
		picture.add("jpg");
		picture.add("jpeg");
		picture.add("pcd");
		picture.add("tga");
		picture.add("pnt");
		picture.add("pict");
		picture.add("pict2");
		/**
		 * 常用视频格式文件匹配后缀名
		 */
		video.add("wmv");
		video.add("asf");
		video.add("asx");
		video.add("rm");
		video.add("mvb");
		video.add("mpg");
		video.add("mpeg");
		video.add("mpe");
		video.add("3pg");
		video.add("mov");
		video.add("mp4");
		video.add("m4v");
		video.add("avi");
		video.add("dat");
		video.add("mkv");
		video.add("flv");
		video.add("vob");
		/**
		 * 常用音频格式文件匹配后缀名
		 */
		music.add("mp3");
		music.add("aac");
		music.add("mav");
		music.add("wma");
		music.add("cda");
		music.add("flac");
		music.add("m4a");
		music.add("mid");
		music.add("mka");
		music.add("mp2");
		music.add("mpa");
		music.add("mpc");
		music.add("ape");
		music.add("ofr");
		music.add("ogg");
		music.add("ra");
		music.add("wv");
		music.add("tta");
		music.add("ac3");
		music.add("dts");
		/**
		 * 常用文档格式文件匹配后缀名
		 */
		documents.add("doc");
		documents.add("docx");
		documents.add("xls");
		documents.add("xlsx");
		documents.add("ppt");
		documents.add("pptx");
		documents.add("pdf");
		documents.add("txt");
	}
	
}
