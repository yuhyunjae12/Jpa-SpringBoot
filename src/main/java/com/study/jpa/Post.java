package com.study.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {

	@Id @GeneratedValue
	private Long id;
	
	private String title;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<Comment> comments = new HashSet<Comment>();
	
	public void addComment (Comment comment) {
		this.getComments().add(comment);
		comment.setPost(this);
	}
}
