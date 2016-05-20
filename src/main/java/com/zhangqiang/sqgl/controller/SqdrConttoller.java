package com.zhangqiang.sqgl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SqdrConttoller {
	@RequestMapping("/sqdr")
	public String readerBooks(Model model){
		//model.addAttribute("bookmarks", sqglService.getAll());
		//return String.valueOf(sqglService.getAll().size()) ;
		return "drsq";
	}
}
