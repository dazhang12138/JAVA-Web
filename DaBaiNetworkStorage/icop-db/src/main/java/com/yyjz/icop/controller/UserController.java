package com.yyjz.icop.controller;

import java.util.Date;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyjz.icop.service.UserService;
import com.yyjz.icop.service.impl.UserServiceImpl;
import com.yyjz.icop.util.DateUtils;
import com.yyjz.icop.vo.UserUpdatePwdVo;
import com.yyjz.icop.vo.UserUpdateVo;
import com.yyjz.icop.vo.UserVo;

/**
 * 处理用户相关的处理Controller类
 * @author Mr.Da
 *
 */
@Controller
@RequestMapping("User")
public class UserController {

	/**
	 * 用户处理Service
	 */
	UserService userService = new UserServiceImpl();
	
	/**
	 * 登录
	 * @param user 用户Vo，包含用户登录名与登录密码
	 * @return 返回登录用户的用户信息，如果查询不到则返回用户信息中包含result字段为error
	 */
	@RequestMapping(value = "Login")
	@ResponseBody
	public Document userLogin(@RequestBody UserVo user){
		Document doc =  userService.loginUser(user);
		if(doc == null){
			doc = new Document("result","error");
		}else{
			Date date = doc.getDate("userStartTime");
			doc.append("userStartTime", DateUtils.DateOfString(date));
			if(doc.getDate("birthday") != null){
				Date date2 = doc.getDate("birthday");
				doc.append("birthday", DateUtils.DateOfStringD(date2));
			}
		}
		return doc;
	}
	
	/**
	 * 注册
	 * @param user 用户Vo，包含用户名、用户登录名、用户登录密码、用户邮箱
	 * @return 返回是否成功注册用户,返回true与false
	 */
	@RequestMapping(value = "Register")
	@ResponseBody
	public boolean userRegister(@RequestBody UserVo user){
		return userService.registerUser(user);
	}
	
	/**
	 * 用户信息校验\n如果userName有值则进行userName的唯一值校验，如果name有值则进行name的唯一值校验
	 * @param userName 用户登录名
	 * @param name 用户名
	 * @return 返回true表示库中存在此名称不能进行操作，返回false表示库里不存在
	 */
	@RequestMapping(value = "UserVerify")
	@ResponseBody
	public boolean userVerify(String userName,String name){
		Document document = new Document();
		if(userName != null && !userName.equals("")){
			document.append("userName", userName);
		}else if(name != null && !name.equals("")){
			document.append("name", name);
		}else{
			return false;
		}
		boolean result = userService.verifyUserByUserNameAndName(document);
		return result;
	}
	/**
	 * 更新用户基本信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "updateUser")
	@ResponseBody
	public Document updateUser(@RequestBody UserUpdateVo user){
		Document updateUser = new Document();
		try {
			updateUser = userService.updateUser(user);
			Date date = updateUser.getDate("userStartTime");
			updateUser.append("userStartTime", DateUtils.DateOfString(date));
			if(updateUser.getDate("birthday") != null){
				Date date2 = updateUser.getDate("birthday");
				updateUser.append("birthday", DateUtils.DateOfStringD(date2));
			}
			updateUser.append("result", "ok");
		} catch (Exception e) {
			updateUser.append("result", "error");
		}
		return updateUser;
	}
	
	@RequestMapping(value = "alterUserPwd")
	@ResponseBody
	public Document alterPwd(@RequestBody UserUpdatePwdVo user){
		
		return null;
	}
	
}
