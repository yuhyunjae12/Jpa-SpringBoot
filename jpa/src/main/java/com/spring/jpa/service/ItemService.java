package com.spring.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jpa.entity.item.Item;
import com.spring.jpa.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;
	
	@Transactional(readOnly = false)
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	public List<Item> findItems(){
		return itemRepository.findAll();
	}
	
	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}
}
