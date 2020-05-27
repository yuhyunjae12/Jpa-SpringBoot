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
	
	//�ֹ�
	@Transactional(readOnly = false)
	public Long order(Long memberId, Long itemId, int count) {
		
		// ��ƼƼ ��ȸ
		Member member = memberRepository.findOne(memberId);
		Item item =itemRepository.findOne(itemId);
		
		//������� ����
		Delivery delivery = new Delivery();
		delivery.setAdress(member.getAddress());
		
		//�ֹ� ��ǰ ����
		OrderItem orderItem =OrderItem.createOrderItem(item, item.getPrice(), count);
		
		//�ֹ�
		Order order = Order.createOrder(member, delivery, orderItem);
		
		//�ֹ� ����
		orderRepository.save(order);
		
		return order.getId();
	}
	
	// �ֹ� ���
	@Transactional(readOnly = false)
	public void cancelOrder(Long orderId) {
		// �ֹ� ��ƼƼ ��ȸ
		Order order = orderRepository.findOne(orderId);
		// �ֹ����
		order.cancel();
	}
	
	//�˻�
	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.findAllByCriteria(orderSearch);
	}
}
