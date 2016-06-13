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
		return repository.findAll();
	}
	/**
	 * 删除书签
	 * */
	public void delete(Long id) {
		repository.delete(id);
	}
}
