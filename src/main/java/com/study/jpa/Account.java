package com.study.jpa;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * ������ ��ƼƼ ����
 * @Table ���� ����
 * @Entity(name="myAccount") ���� ���� ����/ ���� ���� ������ Ŭ���� ���� ���̺�� / Ű����� X
 *
 */
@Entity
@Getter
@Setter
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
	
	@OneToMany(mappedBy = "owner")
	private Set<Study> studies = new HashSet<Study>();
	
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
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "street", column = @Column(name = "home_street"))
	})
	private Address address;

	public void addStudy(Study study) {
		this.getStudies().add(study);
		study.setOwner(this);
	}
	
	public void removeStudy(Study study) {
		this.getStudies().remove(study);
		study.setOwner(null);
	}
	
}
