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
	 * 删除文件或文件夹
	 * @param path
	 * @return
	 */
	public static boolean deleteFiles(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return false;
		} else {
			if (file.isFile())
				return deleteFile(path);
			else
				return deleteDirectory(path);
		}
	}
	/**
	 * 删除文件
	 * @param fileName
	 * @return
	 */
	private static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	/**
	 * 删除文件夹
	 * @param dir
	 * @return
	 */
	private static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = FileUtils.deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = FileUtils.deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
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
