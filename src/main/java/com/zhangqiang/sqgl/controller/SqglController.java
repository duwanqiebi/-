package com.zhangqiang.sqgl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangqiang.sqgl.service.SqglService;

@Controller
@RequestMapping("/")
public class SqglController {
	
	@Autowired
	private SqglService sqglService;
	
	@RequestMapping("/sqgl")
	@ResponseBody
	public String readerBooks(){
		return String.valueOf(sqglService.getAll().size()) ;
		//return "sqgl";
	}
}
