package com.zhangqiang.sqgl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangqiang.sqgl.service.BookmarkClassService;

@Controller
@RequestMapping("/")
public class SqzlController {
	@Autowired
	BookmarkClassService service;

	@RequestMapping("/sqzl")
	public String readerBooks(Model model) {
		// model.addAttribute("bookmarks", sqglService.getAll());
		// return String.valueOf(sqglService.getAll().size()) ;
		return "sqzl";
	}

	@RequestMapping("/sqzl/getall")
	@ResponseBody
	public String getAll() {
		String json = service.getAllJson();
		return json;
	}

	@RequestMapping("/sqzl/tree/{operation}")
	@ResponseBody
	public String treeOperation(@PathVariable String operation, @RequestParam(value = "id") Long id,
			@RequestParam(value = "text"  , required = false) String text,
			@RequestParam(value = "parent", required = false) Long parentId) {
		
		return service.operateTree(operation, id, text,parentId);
	}
}
