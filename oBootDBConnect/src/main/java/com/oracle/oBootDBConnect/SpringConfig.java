package com.oracle.oBootDBConnect;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootDBConnect.repository.JdbcMemberRepository;
import com.oracle.oBootDBConnect.repository.MemberRepository;
import com.oracle.oBootDBConnect.repository.MemoryMemberRepository;

@Configuration //DI 방식
public class SpringConfig {
	private final DataSource dataSource; //final 마지막 변수일 때 생성자 만들어서 집어넣어줘야함 - 12시18분
	
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository(); // 메모리와 연결되는 것 (휘발성O) - DB와는 연결X
		//return new JdbcMemberRepository(dataSource); // DB와 연결 잘 됨
	}
}
