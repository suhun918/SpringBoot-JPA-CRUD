package com.cos.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.crud.model.User;

//<모델, id의 타입>
//CRUD를 구현해주는 클래스 JpaRepository
//CRUD를 제외한 기능은 직접 구현해줘야 한다.
public interface UserRepository extends JpaRepository<User, Integer>{
	
	//변수가 아니라 네이밍 규칙이다!
	User findByEmailAndPassword(String email, String password);
}
