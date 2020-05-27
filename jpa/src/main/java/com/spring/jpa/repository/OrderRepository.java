package com.spring.jpa.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.spring.jpa.entity.Member;
import com.spring.jpa.entity.Order;
import com.spring.jpa.entity.OrderSearch;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager entityManager;

	public void save(Order order) {
		entityManager.persist(order);
	}

	public Order findOne(Long id) {
		return entityManager.find(Order.class, id);
	}

	public List<Order> findAll(OrderSearch orderSearch) {
		return entityManager
				.createQuery(
						"select o from Order o join o.member m" + "where o.status = :status" + "and m.name like :name",
						Order.class)
				.setParameter("status", orderSearch.getOrderStatus()).setParameter("name", orderSearch.getMemberName())
				.setMaxResults(1000) // �ִ� 1000��
				.getResultList();
	}

	public List<Order> findAllByString(OrderSearch orderSearch) { 
		
		// language=JPAQL
		String jpql = "select o From Order o join o.member m";
		boolean isFirstCondition = true;
		// �ֹ� ���� �˻�
		if (orderSearch.getOrderStatus() != null) {
			if (isFirstCondition) {
				jpql += " where";
				isFirstCondition = false;
			} else {
				jpql += " and";
			}
			jpql += " o.status = :status";
		}
		// ȸ�� �̸� �˻�
		if (StringUtils.hasText(orderSearch.getMemberName())) {
			if (isFirstCondition) {
				jpql += " where";
				isFirstCondition = false;
			} else {
				jpql += " and";
			}
			jpql += " m.name like :name";
		}
		TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class).setMaxResults(1000); // �ִ� 1000��
		if (orderSearch.getOrderStatus() != null) {
			query = query.setParameter("status", orderSearch.getOrderStatus());
		}
		if (StringUtils.hasText(orderSearch.getMemberName())) {
			query = query.setParameter("name", orderSearch.getMemberName());
		}
		return query.getResultList();
	}

	public List<Order> findAllByCriteria(OrderSearch orderSearch) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> o = cq.from(Order.class);
		Join<Order, Member> m = o.join("member", JoinType.INNER); // ȸ���� ����
		List<Predicate> criteria = new ArrayList<>();
		// �ֹ� ���� �˻�
		if (orderSearch.getOrderStatus() != null) {
			Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
			criteria.add(status);
		}
		// ȸ�� �̸� �˻�
		if (StringUtils.hasText(orderSearch.getMemberName())) {
			Predicate name = cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
			criteria.add(name);
		}
		cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
		TypedQuery<Order> query = entityManager.createQuery(cq).setMaxResults(1000); // �ִ� 1000��
		return query.getResultList();
	}

}
