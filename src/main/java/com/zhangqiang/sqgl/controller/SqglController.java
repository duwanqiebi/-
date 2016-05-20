package com.zhangqiang.sqgl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangqiang.sqgl.service.BookmarkService;

@Controller
@RequestMapping("/")
public class SqglController {
	
	@Autowired
	private BookmarkService sqglService;
	
	@RequestMapping("/sqgl")
	//@ResponseBody
	public String readerBooks(Model model){
		model.addAttribute("bookmarks", sqglService.getAll());
		//return String.valueOf(sqglService.getAll().size()) ;
		return "sqgl";
	}
}
