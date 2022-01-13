package com.oracle.oBootJpa01.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // =DTO
public class Member {
	
	@Id // primary key 설정하는 것
	private Long id;
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
