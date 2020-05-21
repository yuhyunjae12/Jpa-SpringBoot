package com.study.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ������ ��ƼƼ ����
 * @Table ���� ����
 * @Entity(name="myAccount") ���� ���� ����/ ���� ���� ������ Ŭ���� ���� ���̺�� / Ű����� X
 *
 */
@Entity
@Getter
@RequiredArgsConstructor
public class Account {

	/**
	 * ��ƼƼ�� ��Ű�� ���� �� �� ���
	 * primitiveŸ�԰� wrapper Ÿ���� ����� �� ����
	 * @GeneratedValue �ڵ� ���� �Ǵ� ���� ���
	 * @GeneratedValue(strategy = ��������) �⺻�� auto
	 * ����Ű�� ���� ����
	 */
	@Id @GeneratedValue
	private Long id;
	
	/**
	 * @Column ���� ����
	 * 
	 */
	private final String username;
	
	private final String password;
	
	/**
	 * @Temporal(TemporalType.TIMESTAMP)
	 * ��¥ ���� ����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private final Date created;
	
	/**
	 * @Transient
	 * �ʵ带 �÷����� ���� �ϰ� ���� ������ ���
	 */
	@Transient
	private String yes;
	
}
