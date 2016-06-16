package com.zhangqiang.sqgl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhangqiang.sqgl.domain.Bookmark;
import com.zhangqiang.sqgl.repository.BookmarkRepository;

@Service
public class BookmarkService {
	@Autowired
	private BookmarkRepository repository;
	
	public List<Bookmark> getAll(){
		return repository.findByClassidNot(0L);
	}
	/**
	 * 删除书签
	 * */
	public void delete(Long id) {
		repository.delete(id);
	}
	
	/**
	 * 更新保存书签
	 * */
	public void save(Long id, String name, String sqzl) {
		String sqzlId;
		if(sqzl.contains("-")){
			sqzlId = sqzl.split("-")[0];
		}else{
			sqzlId = sqzl;
		}
		
		repository.save(id, name , sqzlId);
	}
	
	public void click(Long id, Long clickSum) {
		repository.updateClickSumById(id,clickSum);
	}
}
