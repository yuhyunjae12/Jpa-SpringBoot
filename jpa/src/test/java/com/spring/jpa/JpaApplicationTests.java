package com.spring.jpa;

import com.spring.jpa.entity.Member;
import com.spring.jpa.repository.MemberRepository;
import com.spring.jpa.service.MemberService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JpaApplicationTests {

	@Autowired MemberRepository memberRepository;
	@Autowired MemberService memberService;
	// qurey�� ���
	@Autowired EntityManager em;
	
//	@Test(expected = IllegalStateException.class)
//	public void �ߺ�_ȸ��_����() throws Exception{
//		//given
//		Member member1 = new Member();
//		member1.setName("kim");
//		
//		Member member2 = new Member();
//		member2.setName("kim");
//		//when
//		memberService.Join(member1);
//		memberService.Join(member2); // ���ܰ� �߻� �ؾ� �Ѵ�!!
//		//then
//		fail("���ܰ� �߻��ؾ� �Ѵ�.");
//	}

	@Test
	//@Rollback(false)
	public void ȸ������() throws Exception{
		//given
		Member member = new Member();
		member.setName("kim");
		
		//when
		Long saveId = memberService.Join(member);
		
		//then
		em.flush();
		assertEquals(member, memberRepository.findOne(saveId));
		
	}

}
