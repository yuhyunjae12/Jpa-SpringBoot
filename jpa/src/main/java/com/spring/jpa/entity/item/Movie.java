package com.spring.jpa.entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "M")
public class Movie extends Item{

	private String director;
	private String actor;
}
