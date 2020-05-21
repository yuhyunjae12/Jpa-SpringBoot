package com.study.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {

	@Id @GeneratedValue
	private Long id;
	
	private String comment;
	
	@ManyToOne
	private Post post;
}
