package com.study.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Account {

	@Id @GeneratedValue
	private Long id;
	
//	@Column 생략 가능
	private final String username;
	
	private final String password;
	
}
