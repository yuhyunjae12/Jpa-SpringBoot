package com.spring.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;
	
	private String name;

	@Embedded
	private Address address;
	
	// mappedBy order¿¡  member field ¿¡ ¸ÊÇÎ
	@OneToMany(mappedBy = "member")
	private List<Order> orders =new ArrayList<>();

}
