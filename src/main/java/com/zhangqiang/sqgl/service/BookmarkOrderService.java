package com.zhangqiang.sqgl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangqiang.sqgl.domain.Bookmark;
import com.zhangqiang.sqgl.repository.BookmarkRepository;
import com.zhangqiang.sqgl.util.DateUtil;

@Service
public class BookmarkOrderService {
	
	@Autowired
	private BookmarkRepository repository;
	
	/**
	 * 获取所有未分类书签
	 * */
	public List<Bookmark> getAll() {
		
		return repository.findByClassid(1L);
	}

	public void click(Long id) {
		//先取出书签的点击数量，后期可通过缓存来取		
		Bookmark bookmark = repository.findOne(id);
		try{
			bookmark.setLastdate(DateUtil.getNowDate());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		bookmark.setClicksnum(bookmark.getClicksnum() + 1L);
		
		repository.save(bookmark);
	}

	public void classify(String[] ids, Long classId) {
		for(String id: ids){
			repository.updateClassId(Long.parseLong(id),classId);
		}
	}

	public void delete(String[] ids) {
		for(String id : ids){
			repository.delete(Long.parseLong(id));
		}
	}

}
