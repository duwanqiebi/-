package com.zhangqiang.sqgl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zhangqiang.sqgl.domain.Bookmark;

@org.springframework.stereotype.Repository
public interface BookmarkRepository extends Repository<Bookmark, Long> {
	@Query(value = "select * from  BOOKMARK  where id <> 1", nativeQuery = true)
    List<Bookmark> findAll();
    
    void save(Bookmark bookmark);
    
    void delete(Long id);
    
    @Transactional
	@Modifying
	@Query(value = "update BOOKMARK set name = ?2,classid=?3,modifydate=sysdate where id = ?1", nativeQuery = true)
	void save(Long id, String name, String sqzlId);
    
    @Transactional
	@Modifying
	@Query(value = "update BOOKMARK set clicksnum = ?2,lastdate=sysdate where id = ?1", nativeQuery = true)
	void updateClickSumById(Long id, Long clickSum);

	List<Bookmark> findByClassid(Long classId);

	Bookmark findOne(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "update BOOKMARK set classid = ?2,modifydate=sysdate where id = ?1", nativeQuery = true)
	void updateClassId(long parseLong, Long classId);
}
