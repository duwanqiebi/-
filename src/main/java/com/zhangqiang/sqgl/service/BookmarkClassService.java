package com.zhangqiang.sqgl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.zhangqiang.sqgl.domain.BookmarkClass;
import com.zhangqiang.sqgl.repository.BookmarkClassRepository;

@Service
public class BookmarkClassService {
	@Autowired
	private BookmarkClassRepository classRepository;
	
	SimplePropertyPreFilter filter;
	
	public BookmarkClassService(){
		filter = new SimplePropertyPreFilter(BookmarkClass.class, "id","text","parent");
	}
	
	public String getAllJson(){
		List<BookmarkClass> list = classRepository.findAll();
		if(list.size() == 0){
			list.add(new BookmarkClass(0L,"Root",null,"#"));
		}
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(BookmarkClass.class, "id","text","parent");
		String json = JSON.toJSONString(list,filter );
		return json;
	}
	
	/**
	 * @param operation 
	 * @param id
	 * @param name
	 * */
	public String operateTree(String operation , Long id , String name ,Long parentId){
		BookmarkClass bookmarkClass = null;
		
		switch (operation) {
			/*****************添加节点********************/
			case "create_node":
				classRepository.insert(name, id);
				bookmarkClass = classRepository.getLastInsert();
				break;
			case "rename_node":
				classRepository.updateClassName(name, id);
				break;
			case "delete_node":
				classRepository.delete(id);
				break;
			case "move_node":
				classRepository.updateParentId(id,parentId);
			default:
				break;
		}

		return JSON.toJSONString(bookmarkClass,filter );
	}
	
}
