package com.spring.jpa.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

	private String memberName;
	private OrderStatus orderStatus; // �ֹ� ����[ORDER, CANCEL]
	
}
