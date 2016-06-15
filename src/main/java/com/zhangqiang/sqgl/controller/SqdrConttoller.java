package com.zhangqiang.sqgl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.zhangqiang.sqgl.service.BookmarkUploadService;

@Controller
@RequestMapping("/")
public class SqdrConttoller {
	

	@Autowired
	private BookmarkUploadService sqdrService;
	
	@RequestMapping("/sqdr")
	public String readerBooks(Model model){
		//model.addAttribute("bookmarks", sqglService.getAll());
		//return String.valueOf(sqglService.getAll().size()) ;
		return "sqdr";
	}
	
	
	@RequestMapping("/sqdr/upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile[]  files){
		sqdrService.upload(files);
		return "导入成功";
	} 
}
