package com.spring.jpa.entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "B")
public class Book extends Item{
	
	private String author;
	private String isbn;

}
