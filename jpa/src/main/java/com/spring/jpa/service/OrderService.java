package com.spring.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jpa.entity.Delivery;
import com.spring.jpa.entity.Member;
import com.spring.jpa.entity.Order;
import com.spring.jpa.entity.OrderItem;
import com.spring.jpa.entity.OrderSearch;
import com.spring.jpa.entity.item.Item;
import com.spring.jpa.repository.ItemRepository;
import com.spring.jpa.repository.MemberRepository;
import com.spring.jpa.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	
	//주문
	@Transactional(readOnly = false)
	public Long order(Long memberId, Long itemId, int count) {
		
		// 엔티티 조회
		Member member = memberRepository.findOne(memberId);
		Item item =itemRepository.findOne(itemId);
		
		//배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAdress(member.getAddress());
		
		//주문 상품 생성
		OrderItem orderItem =OrderItem.createOrderItem(item, item.getPrice(), count);
		
		//주문
		Order order = Order.createOrder(member, delivery, orderItem);
		
		//주문 저장
		orderRepository.save(order);
		
		return order.getId();
	}
	
	// 주문 취소
	@Transactional(readOnly = false)
	public void cancelOrder(Long orderId) {
		// 주문 엔티티 조회
		Order order = orderRepository.findOne(orderId);
		// 주문취소
		order.cancel();
	}
	
	//검색
	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.findAllByCriteria(orderSearch);
	}
}
