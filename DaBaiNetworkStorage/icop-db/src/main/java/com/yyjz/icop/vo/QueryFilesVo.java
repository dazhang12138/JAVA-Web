package com.yyjz.icop.vo;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

public class QueryFilesVo {
	//用户编号
	private ObjectId userId;
	//查询条件
	private List<Map<String, Object>> condition;
	
	public ObjectId getUserId() {
		return userId;
	}
	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}
	public List<Map<String, Object>> getCondition() {
		return condition;
	}
	public void setCondition(List<Map<String, Object>> condition) {
		this.condition = condition;
	}
}
