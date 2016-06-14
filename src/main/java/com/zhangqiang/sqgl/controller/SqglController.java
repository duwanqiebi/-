package com.zhangqiang.sqgl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangqiang.sqgl.service.BookmarkService;

@Controller
@RequestMapping("/")
public class SqglController {
	
	@Autowired
	private BookmarkService sqglService;
	
	@RequestMapping("/sqgl")
	public String readerBooks(Model model){
		model.addAttribute("bookmarks", sqglService.getAll());
		//return String.valueOf(sqglService.getAll().size()) ;
		return "sqgl";
	}
	
	@RequestMapping("/sqgl/delete")
	@ResponseBody
	public String delete( @RequestParam(value = "id") Long id){
		sqglService.delete(id);
		return "";
	}
	
	@RequestMapping("/sqgl/save")
	@ResponseBody
	public String save(@RequestParam(value="id")Long id,@RequestParam(value="name") String name,@RequestParam(value="sqzl")String sqzl){
		sqglService.save(id,name,sqzl);
		return "";
	} 
	
	@RequestMapping("/sqgl/click")
	@ResponseBody
	public String click(@RequestParam(value="id")Long id,@RequestParam(value="clickSum") Long clickSum){
		sqglService.click(id,clickSum);
		return "";
	}
}
