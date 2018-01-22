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
	
	/**
	 * 本类转String类型
	 */
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
		default:
			break;
		}
		return str;
	}
	
	/**
	 * String类型转化为FileType类型
	 * 如果不符合类型标准则为OTHERS
	 * @param type
	 * @return
	 */
	public FileType value(String type){
		FileType f  = FileType.OTHERS;
		switch (type) {
		case "PICTURE":
			f = FileType.PICTURE;
			break;
		case "VIDEO":
			f = FileType.VIDEO;
			break;
		case "MUSIC":
			f = FileType.MUSIC;
			break;
		case "SEED":
			f = FileType.SEED;
			break;
		}
		return f;
	}
	
}
