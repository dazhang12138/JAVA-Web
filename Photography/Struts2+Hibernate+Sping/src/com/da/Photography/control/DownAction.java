package com.da.Photography.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.da.Photography.biz.DownBizInterface;
import com.da.Photography.entity.PaDown;
import com.da.Photography.entity.PaUser;

public class DownAction {

	private DownBizInterface dBiz;
	
	private String type;
	private String path;
	
	public String getAllDowns(){
		String result = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		PaUser user = (PaUser) request.getSession().getAttribute("user");
	  	if(user == null){
			request.setAttribute("result", "请登录管理员账户");
			result = "login";
		}else {
			if(!type.equals("1") && !user.getURole().equals("0")){
				request.setAttribute("result", "请登录管理员账户");
				result = "login";
			}else {
				List<PaDown> downs = dBiz.queryAllDown(type,user.getUId());
				request.setAttribute("downs", downs);
				result = "path";
			}
		}
	  	return result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public DownBizInterface getdBiz() {
		return dBiz;
	}

	public void setdBiz(DownBizInterface dBiz) {
		this.dBiz = dBiz;
	}

	
}
