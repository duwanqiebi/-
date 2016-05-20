package com.zhangqiang.sqgl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zhangqiang.sqgl.domain.BookmarkClass;

/**
 * 
 * */
@org.springframework.stereotype.Repository
public interface BookmarkClassRepository extends Repository<BookmarkClass, Long> {

	List<BookmarkClass> findAll();

	@Transactional
	@Modifying
	@Query(value = "insert into BOOKMARKCLASS(classname,createdate,parentid) values(?1,sysdate,?2)", nativeQuery = true)
	void insert(String className, Long parentid);

	@Query(value = "select * from BOOKMARKCLASS where id = IDENTITY()", nativeQuery = true)
	BookmarkClass getLastInsert();

	@Transactional
	@Modifying
	@Query(value = "update BOOKMARKCLASS set classname = ?1 where id = ?2", nativeQuery = true)
	void updateClassName(String name, Long id);

	@Transactional
	@Modifying
	@Query(value = "delete from BOOKMARKCLASS where id = ?1", nativeQuery = true)
	void delete(Long id);
	
	
	@Transactional
	@Modifying
	@Query(value = "update BOOKMARKCLASS set parentid = ?2 where id = ?1", nativeQuery = true)
	void updateParentId(Long id, Long parentId);

}
