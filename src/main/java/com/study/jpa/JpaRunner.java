package com.study.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
//		Account account = new Account("hyun","pass", new Date());
//		//entityManager.persist(account);
////		Study study = new Study("Spring Jpa", account);
//		Study study = new Study("Spring Jpa");
//		
////		account.getStudies().add(study);
////		study.setOwner(account);
//		
//		account.addStudy(study);
//		
//		Session session = entityManager.unwrap(Session.class);
//		session.save(account);
//		session.save(study);
//		
//		Account hyun =session.load(Account.class, account.getId());
//		System.out.println("=====================================");
//		System.out.println(hyun.getUsername());
		
//		Post post = new Post();
//		post.setTitle("力格1");
//		
//		Comment comment = new Comment();
//		comment.setComment("内膏飘1");
//		post.addComment(comment);
//		
//		Comment comment1 = new Comment();
//		comment1.setComment("内膏飘2");
//		post.addComment(comment1);
//		
//		Session session = entityManager.unwrap(Session.class);
//		session.save(post);

		Session session = entityManager.unwrap(Session.class);

		Post post1 = session.get(Post.class, 1L);
		
		post1.getComments().forEach(c -> 
			System.out.println(c.getComment())
		);
	}
}
