package com.spring.jpa.entity;

import lombok.Setter;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.spring.jpa.entity.item.Item;

@Entity
@Getter
@Setter
public class OrderItem {
	
	@Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	
	private int orderPrice; // 주문가격
	private int count; // 주문 수량
	
	// 생성 메서드
	public static OrderItem createOrderItem (Item item, int orderPrice, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);
		item.removeStock(count);
		return orderItem;
	}
	
	// 비즈니스 로직
	public void cancel() {
		getItem().addStock(count);
	}
	
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}
	
}
