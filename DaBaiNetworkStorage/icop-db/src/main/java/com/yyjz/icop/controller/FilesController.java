package com.yyjz.icop.controller;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("Files")
public class FilesController {
	
	@RequestMapping(value="getFiles")
	@ResponseBody
	public Document getFiles(){
		
		return null;
	}
}
