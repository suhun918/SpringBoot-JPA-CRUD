package com.cos.crud.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.crud.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	// update 쿼리 => @Transactional + @Modifying
	// insert 쿼리 => @Transactional
	// delete 쿼리 => @Transactional
	
	//트렌젝션 테스트 1
	@Transactional
	@Modifying
	//네이티브방식
	@Query(value = "UPDATE board SET count=count+1 WHERE id=?1", nativeQuery = true)
	public void increaseCount(int id);
	
	//트렌젝션 테스트 2
	@Transactional
	@Modifying
	@Query(value = "UPDATE board SET createDate=now() WHERE id=?1", nativeQuery = true)
	public void timeUpdate(int id);
}
