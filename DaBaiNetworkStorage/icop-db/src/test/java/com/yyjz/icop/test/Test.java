package com.yyjz.icop.test;

import org.bson.types.ObjectId;

import com.yyjz.icop.util.GetFileType;

public class Test {
	
	@org.junit.Test
	public void test(){
		System.out.println(GetFileType.getType("新建文件夹.txt"));
	}
	
	@org.junit.Test
	public void test1(){
		ObjectId id = new ObjectId("5a557264a63224406c70ce2a");
		System.out.println(id);
	}
	
}
