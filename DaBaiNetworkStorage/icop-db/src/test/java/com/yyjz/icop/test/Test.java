package com.yyjz.icop.test;

import com.yyjz.icop.util.FileType;
import com.yyjz.icop.util.GetFileType;

public class Test {
	
	@org.junit.Test
	public void test(){
		System.out.println(GetFileType.getType("新建文件夹.txt"));
	}
	
	@org.junit.Test
	public void test1(){
		FileType f = FileType.MUSIC;
		System.out.println(f.toString());
	}
}
