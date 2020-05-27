package com.spring.jpa;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jpa.entity.Address;
import com.spring.jpa.entity.Member;
import com.spring.jpa.entity.Order;
import com.spring.jpa.entity.OrderStatus;
import com.spring.jpa.entity.item.Book;
import com.spring.jpa.entity.item.Item;
import com.spring.jpa.exception.NotEnoughStockException;
import com.spring.jpa.repository.OrderRepository;
import com.spring.jpa.service.OrderService;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

	@Autowired
	EntityManager em;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderRepository orderRepository;

	@Test
	public void ��ǰ�ֹ�() throws Exception {
		// given
		Member member = createMember();

		Item book = new Book();
		book.setName("�ð�jpa");
		book.setPrice(10000);
		book.setStockQuantity(10);
		em.persist(book);

		int orderCount = 2;

		// when
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

		// then
		Order getOrder = orderRepository.findOne(orderId);

		assertEquals("��ǰ �ֹ��� ���´� ORDER", OrderStatus.ORDER, getOrder.getStatus());
		assertEquals("�ֹ��� ��ǰ ���� ���� ��Ȯ�ؾ� �Ѵ�.", 1, getOrder.getOrderItems().size());
		assertEquals("�ֹ� ������ ���� * �����̴�.", 10000 * orderCount, getOrder.getTotalPrice());
		assertEquals("�ֹ� ������ŭ ��� �پ�� �Ѵ�.", 8, book.getStockQuantity());
	}

	@Test(expected = NotEnoughStockException.class)
	public void ��ǰ�ֹ�_�������ʰ�() throws Exception {
		// given
		Member member = createMember();
		Item item = createBook("�ð� JPA", 10000, 10);
		
		int orderCount = 11;
		// when
		
		orderService.order(member.getId(), item.getId(), orderCount);

		// then
		fail("��� ���� ������ �����Ѵ�.");
	}

	@Test
	public void �ֹ����() throws Exception {
		// given
		Member member = createMember();
		Book item = createBook("�ð� JPA", 10000, 10);

		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
		// when
		orderService.cancelOrder(orderId);
		
		// then
		Order getOrder =orderRepository.findOne(orderId);
		
		assertEquals("�ֹ� ��ҽ� ���´� CANCEL �̴�.", OrderStatus.CANCEL, getOrder.getStatus());
		assertEquals("�ֹ��� ��ҵ� ��ǰ�� �׸�ŭ ��� �����ؾ� �Ѵ�.", 10, item.getStockQuantity());
	}

	private Member createMember() {
		Member member = new Member();
		member.setName("ȸ��1");
		member.setAddress(new Address("����", "����", "123-123"));
		em.persist(member);
		return member;
	}

	private Book createBook(String name, int price, int stockQuantity) {
		Book book = new Book();
		book.setName(name);
		book.setStockQuantity(stockQuantity);
		book.setPrice(price);
		em.persist(book);
		return book;
	}
}
