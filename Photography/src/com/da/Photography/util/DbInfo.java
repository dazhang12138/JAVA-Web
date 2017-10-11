package com.da.Photography.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author		ZhangDa
 * 获取db.properties配置文件中的数据库信息
 * 单例模式 ：通过getInstance()方法获取对象
 */
public class DbInfo {
	/**
	 * 单例模式
	 * DbInfo类的唯一对象
	 */
	private static DbInfo DBINFO = null;
	/**
	 * 数据库配置文件实体类对象
	 */
	private ConfigInfo ci;
	private DbInfo() {
		ci = new ConfigInfo();
		getProperties();
	}
	/**
	 * 单例模式
	 * 获取DbInfo类对象
	 * @return DbInfo类对象
	 */
	public static DbInfo getInstance(){
		if(DBINFO == null){
			DBINFO = new DbInfo();
		}
		return DBINFO;
	}
	/**
	 * 通过反射获取数据库配置文件的内容
	 */
	public void getProperties(){
		FileInputStream fiStream = null;
		try {
			String path = DbInfo.class.getResource("/").toURI().getPath() + "db.properties";
			File file = new File(path);
			Properties p = new Properties();
			fiStream  = new FileInputStream(file);
			p.load(fiStream);
			ci.setDriver(p.getProperty("driver"));
			ci.setName(p.getProperty("name"));
			ci.setUrl(p.getProperty("url"));
			ci.setPwd(p.getProperty("pwd"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fiStream != null){
				try {
					fiStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 获取数据库驱动信息
	 * @return 驱动
	 */
	public String getDriver() {
		String dricer = null;
		if(ci != null){
			dricer = ci.getDriver();
		}
		return dricer;
	}
	/**
	 * 获取数据库连接地址信息
	 * @return 地址
	 */
	public String getUrl() {
		String url = null;
		if(ci != null){
			url = ci.getUrl();
		}
		return url;
	}
	/**
	 * 获取数据库连接用户名信息
	 * @return 用户名
	 */
	public String getName() {
		String name = null;
		if(ci != null){
			name = ci.getName();
		}
		return name;
	}
	/**
	 * 获取数据库连接密码信息
	 * @return 密码
	 */
	public String getPwd() {
		String pwd = null;
		if(ci != null){
			pwd = ci.getPwd();
		}
		return pwd;
	}
}
/**
 * db.properties配置文件对应的实体类
 * @author ZhangDa
 */
class ConfigInfo{
	/**
	 * 数据库连接驱动
	 */
	private String driver;
	/**
	 * 数据库连接地址
	 */
	private String url;
	/**
	 * 数据库连接用户名
	 */
	private String name;
	/**
	 * 数据库连接密码
	 */
	private String pwd;
	/**
	 * 获取数据库驱动信息
	 * @return 数据库驱动
	 */
	public String getDriver() {
		return driver;
	}
	/**
	 * 设置数据库驱动信息
	 * @param driver 驱动信息
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}
	/**
	 * 获取数据库连接地址信息
	 * @return 数据库连接地址信息
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置数据库连接地址信息
	 * @param url 数据库连接地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取数据库连接用户名信息
	 * @return 数据库连接用户名信息
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置数据库连接用户名信息
	 * @param name 用户名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取数据库连接密码信息
	 * @return 数据库连接密码
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * 设置数据库连接密码信息 
	 * @param pwd 密码
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
