package com.da.Photography.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.*;
import javax.sql.DataSource;

import com.da.Photography.util.*;


public class BaseDao {
	public Connection conn = null;
	public PreparedStatement pstm = null;
	public Statement st = null;
	public ResultSet rs = null;
	public final static String JNDI_NAME="java:comp/env/jdbc/OraclePhotography";
	
	public void beginTran(){
		getConn(JNDI_NAME);
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			Log.LOGGER.debug("开启事物" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void commitTran(){
		try {
			conn.commit();
		} catch (SQLException e) {
			Log.LOGGER.debug("提交事物" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void rollbackTran(){
		try {
			conn.rollback();
		} catch (SQLException e) {
			Log.LOGGER.debug("回滚事物" + e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 使用数据库连接池连接数据库
	 * @param JNDIname
	 */
	public void getConn(String JNDIname) {
		try {
			if(conn == null || conn.isClosed()){
				InitialContext ic = new InitialContext();
				DataSource ds =  (DataSource)ic.lookup(JNDIname);
				conn = ds.getConnection();
			}
		} catch (SQLException e) {
			Log.LOGGER.debug("数据库连接池失败"  + e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			Log.LOGGER.debug("数据库连接池失败"  + e.getMessage());
			e.printStackTrace();
		}
	}
	public void closeAll(){
		try {
			if(conn != null){
				conn.close();
			}
			if(pstm != null){
				pstm.close();
			}
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("关闭数据库失败" + e.getMessage());
			e.printStackTrace();
		}
	}
	public ResultSet executeQuery(String sql, Object...params) throws SQLException{
//		getConn();
		pstm = conn.prepareStatement(sql);
		int i = 1;
		for (Object obj : params) {
			pstm.setObject(i++, obj);
		}
		rs = pstm.executeQuery();
		return rs;
	}
	
	public int executeUpdate(String sql, Object...params) throws SQLException{
		int result = 0;
		pstm = conn.prepareStatement(sql);
		int i = 1;
		for (Object obj : params) {
			pstm.setObject(i++, obj);
		}
		result = pstm.executeUpdate();
		return result;
	}
	
}
