package com.zhangqiang.sqgl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangqiang.sqgl.service.BookmarkOrderService;

@Controller
@RequestMapping("/")
public class SqflController {
	@Autowired
	private BookmarkOrderService orderService;
	
	@RequestMapping("/sqfl")
	public String readerBooks(Model model){
		model.addAttribute("bookmarks", orderService.getAll());
		//return String.valueOf(sqglService.getAll().size()) ;
		return "sqfl";
	}
	
	@RequestMapping("/sqfl/click")
	@ResponseBody
	public String click(@RequestParam("id") Long id){
		orderService.click(id);
		return "";
	}
	
	@RequestMapping("/sqfl/classify")
	@ResponseBody
	public String classify(@RequestParam(value = "ids") String ids,@RequestParam(value="classId") Long classId){
		String[] idss = ids.split(",");
		orderService.classify(idss,classId);
		return "";
	}
	
	@RequestMapping("/sqfl/delete")
	@ResponseBody
	public String delete(@RequestParam(value = "ids") String ids){
		String[] idss = ids.split(",");
		orderService.delete(idss);
		return "";
	}
}
