package com.spring.jpa.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jpa.entity.Member;
import com.spring.jpa.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;
	
	// ȸ�� ����
	@Transactional(readOnly = false)
	public Long Join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	// �ߺ� ȸ�� ����
	private void validateDuplicateMember (Member member) {
		//EXCPTION
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("�̹� �����ϴ� ȸ���Դϴ�.");
		}
	}
	
	// ȸ�� ��ü ��ȸ
	public List<Member> findMemebers(){
		return memberRepository.findAll();
	}
	
	// ȸ�� �ܰ� ��ȸ
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}
