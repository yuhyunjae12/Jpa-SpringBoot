package com.spring.jpa.entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter 
@DiscriminatorValue(value = "A")
public class Album extends Item{

	private String artist;
	private String etc;
}
