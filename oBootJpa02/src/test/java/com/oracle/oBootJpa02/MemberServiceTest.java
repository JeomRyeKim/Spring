package com.oracle.oBootJpa02;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
// 1. @RunWith(SpringRunner.class) : 스프링과 테스트 통합
// 2. @SpringBootTest : 스프링 부트 띄우고 테스트(이게 없으면 @Autowired 다 실패)
// @Transactional : 반복 가능한 테스트 지원, 각각의 테스트를 실행할 때마다 트랜잭션을 시작하고 
// *      테스트가 끝나면 트랜잭션을 강제로 롤백 (이 어노테이션이 테스트 케이스에서 사용될 때만 롤백) - 진짜로 DB에 저장X 말 그대로 테스트만 함

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.domain.Team;
import com.oracle.oBootJpa02.repository.MemberRepository;
import com.oracle.oBootJpa02.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;
	
	@Before
	public void before1() {
		System.out.println("Test Before1 Start...");
	}
	
	@After
	public void after1() {
		System.out.println("Test After1 Start...");
	}
	
	public void memberSave() throws Exception {
		System.out.println("Test memberSave Start...");
		// 조건
		 // 팀 저장 
		Member member = new Member();
		member.setTeamname("4class");
		member.setName("김우현");
		
		// 수행
		Member member3 = memberService.join(member);
		
		// 결과
		System.out.println("MemberServiceTest memberSave member3.getId()->" + member3.getId());
	}
}
