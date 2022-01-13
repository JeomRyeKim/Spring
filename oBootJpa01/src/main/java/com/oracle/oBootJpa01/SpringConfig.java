package com.oracle.oBootJpa01;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootJpa01.repository.JpaMemberRepository;
import com.oracle.oBootJpa01.repository.MemberRepository;

@Configuration
public class SpringConfig {
	private final DataSource    dataSource;
	private final EntityManager em;
	
	public SpringConfig(DataSource dataSource, EntityManager em) {
		this.dataSource = dataSource;
		this.em = em;
	}
	
	@Bean	// 여러개의 @Bean으로 해서 여러개의 Repository를 디비와 연결할 수 있음!
	public MemberRepository memberRepository() {
		return new JpaMemberRepository(em);
	}
}
