package com.oracle.oBootAPI01.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator( name = "member_seq_gen",
					sequenceName = "member_seq_generator",
					initialValue = 1,
					allocationSize = 1)
@Getter
@Setter
@Table(name = "member1")
public class Member {
	@Id	// jpa는 무조건 pk를 달아줘야 에러가 안 남
	@GeneratedValue( strategy = GenerationType.SEQUENCE,
					 generator = "member_seq_gen")
	@Column(name = "member_id")
	private Long id;
	@Column(name = "user_id")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	// 얘네는 버퍼로만 쓸꺼야(메모리상)
	@Transient
	private String teamname;
	@Transient
	private Long teamid;
}
