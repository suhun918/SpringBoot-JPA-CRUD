package com.cos.crud.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	@Id
	// IDENTITY를 사용하면 사용자가 선택한 DB 시퀀스 방식을 따라가는 것 이다.
	// 어지간하면 아이덴티티 쓰자
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// @Column을 이용하여 컬럼에 제약조건을 설정 해줄 수 있다.
	@Column(nullable = false, length = 30)
	private String username;
	@Column(nullable = false, length = 20)
	private String password;
	private String phone;
	private String email;


	@CreationTimestamp
	private Timestamp createDate;

}
