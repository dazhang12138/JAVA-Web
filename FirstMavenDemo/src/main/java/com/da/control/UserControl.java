package com.da.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserControl {
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String UserHello(String id,Model model){
//		String id = request.getParameter("id");
//		request.setAttribute("user", id);
		model.addAttribute("id",id);
		return "/hello.jsp";
	}
}
