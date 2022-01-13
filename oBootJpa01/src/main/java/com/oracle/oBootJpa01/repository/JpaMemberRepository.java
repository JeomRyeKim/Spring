package com.oracle.oBootJpa01.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.oracle.oBootJpa01.domain.Member;

// SpringConfig에서  @Configuration , @Bean을 설정해뒀기때문에  @Repository를 하지 않아도 연결이 됨
public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em; // jdbc 사용: DataSource / jpa사용: EntityManager
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member save(Member member) {
		// JAP 저장
		em.persist(member); 
		// 내가 아무런 sysout문을 안 적어도  application.properties 의 spring.jpa.show-sql=true때문에 아래처럼 콘솔에 출력됨
		// Hibernate: insert into member (name, id) values (?, ?)
		return member;
	}

	@Override
	public List<Member> findAll() {
		// ""안에 있는 Member는 DB 멤버가 아니라 @Entity의 Member를 의미함, @Entity에 있는 정보를 뽑아서 .getResultList()로 List형식으로 만들어서 memberList에 넣어준다는 의미
		List<Member> memberList = em.createQuery("select a from Member a", Member.class).getResultList(); 
		return memberList;
	}

	@Override
	public List<Member> findByNames(String name) {
		String pname = name + '%';
		System.out.println("JpaMemberRepository findByNames name->" + name);
		// :name 이 부분이 PreparedStatement의 ?를 의미함 그래서 .setParameter("name", pname)으로 :name의 자리에 panme을 넣는다는 의미/ .getResultList()는 리스트 형식으로 만들어서 memberList에 넣어줌
		List<Member> memberList = em.createQuery("select m from Member m where name Like :name", Member.class).setParameter("name", pname).getResultList();
		System.out.println("JpaMemberRepository memberList.size()->" + memberList.size());
		
		return memberList;
	}
	


}
