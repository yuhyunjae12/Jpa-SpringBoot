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
 * 도메인 엔티티 선언
 * @Table 생략 가능
 * @Entity(name="myAccount") 네임 설정 가능/ 설정 하지 않으면 클래스 명이 테이블명 / 키워드명 X
 *
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Account {

	/**
	 * 엔티티의 주키를 맵핑 할 때 사용
	 * primitive타입과 wrapper 타입을 사용할 수 있음
	 * @GeneratedValue 자동 생성 되는 값을 사용
	 * @GeneratedValue(strategy = 설정가능) 기본은 auto
	 * 복합키도 맵핑 가능
	 */
	@Id @GeneratedValue
	private Long id;
	
	/**
	 * @Column 생략 가능
	 * 
	 */
	private final String username;
	
	private final String password;
	
	@OneToMany(mappedBy = "owner")
	private Set<Study> studies = new HashSet<Study>();
	
	/**
	 * @Temporal(TemporalType.TIMESTAMP)
	 * 날짜 포멧 설정
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private final Date created;
	
	/**
	 * @Transient
	 * 필드를 컬럼으로 맵핑 하고 싶지 않을때 사용
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
