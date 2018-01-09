package com.yyjz.icop.util;

/**
 * 文件类型枚举类,所有的文件的类型只能是这其中的列举
 * @author Mr.Da
 *
 */

public enum FileType {
	/*图片类*/
	PICTURE
	/*视频类*/
	,VIDEO
	/*音乐类*/
	,MUSIC
	/*种子类*/
	,SEED
	/*文档类*/
	,DOCUMENTS
	/*其他类型*/
	,OTHERS;
	
	public String toString(){
		String str = "OTHERS";
		switch (this) {
			case PICTURE:
				str = "PICTURE";
				break;
			case VIDEO:
				str = "VIDEO";
				break;
			case MUSIC:
				str = "MUSIC";
				break;
			case SEED:
				str = "SEED";
				break;
			case DOCUMENTS:
				str = "DOCUMENTS";
				break;
		}
		return str;
	}
}
