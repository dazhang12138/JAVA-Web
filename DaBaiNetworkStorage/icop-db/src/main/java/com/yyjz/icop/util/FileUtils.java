package com.yyjz.icop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件辅助类
 * @author Mr.Da
 *
 */
public class FileUtils {
	
	public static String DEFAULT_FILE_PATH = "D:/DB_Files";
	
	/**
	 * 保存文件
	 * @return
	 */
	public static boolean saveFile(MultipartFile file,String path){
		boolean flag = false;
		//判断文件是否为空  
        if (!file.isEmpty()) {
        	//拼接文件地址
        	String filePath = getPath(path, file.getOriginalFilename());
        	File f = new File(filePath);
			try {
				//转存文件
				file.transferTo(f);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
        }  
		return flag;
	}
	/**
	 * 创建文件夹
	 * @param folderPath
	 * @return
	 */
	public static boolean createFolder(String folderPath) {
		File file = null;  
        try {  
            file = new File(folderPath);  
            if (!file.exists()) {  
                return file.mkdirs();  
            }  
            else{  
                return false;  
            }  
        } catch (Exception e) {
        	
        } finally {  
            file = null;  
        }  
        return false;  
	}
	/**
	 * 拼接文件地址
	 * @param path 文件中间地址
	 * @return
	 */
	public static String getPath(String path){
		return DEFAULT_FILE_PATH + "/" + path;
	}
	/**
	 * 拼接文件地址
	 * @param path 文件中间地址
	 * @param fileName 文件名称
	 * @return
	 */
	public static String getPath(String path,String fileName){
		return DEFAULT_FILE_PATH + "/" + path + "/" + fileName;
	}
	/**
	 * 通过文件物理地址获取文件MD5
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static String getFileMD5(String path,String fileName){
		String filepath = getPath(path,fileName);
		File file = new File(filepath);
		return getMD5(file);
	}
	
	/**
	 * 获取一个文件的md5值(可处理大文件)
	 * @return md5 value
	 */
	private static String getMD5(File file) {
		FileInputStream fileInputStream = null;
		try {
			MessageDigest MD5 = MessageDigest.getInstance("MD5");
			fileInputStream = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length;
			while ((length = fileInputStream.read(buffer)) != -1) {
				MD5.update(buffer, 0, length);
			}
			return new String(Hex.encodeHex(MD5.digest()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (fileInputStream != null){
					fileInputStream.close();
					}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
