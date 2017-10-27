package com.da.Photography.dao;

public interface BaseDaoInterface {
	public void beginTran();
	public void commitTran();
	public void rollbackTran();
	public void getConn();
	public void closeAll();
}
