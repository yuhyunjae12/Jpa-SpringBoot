package com.spring.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.spring.jpa.entity.item.Item;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager entityManager;
	
	public void save(Item item) {
		if(item.getId() == null) {
			entityManager.persist(item);
		}else {
			entityManager.merge(item);
		}
	}
	
	public Item findOne(Long id) {
		return entityManager.find(Item.class, id);
	}
	
	public List<Item> findAll(){
		return entityManager.createQuery("select i from Item i",Item.class)
				.getResultList();
	}
	
}
