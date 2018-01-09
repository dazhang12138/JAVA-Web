package com.yyjz.icop.util;

import java.io.File;

public class CreateFiles {
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
}
