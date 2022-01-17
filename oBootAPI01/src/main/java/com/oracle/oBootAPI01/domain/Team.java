package com.oracle.oBootAPI01.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator( name = "team_seq_gen",
					sequenceName = "team_seq_generator", // 매핑할 DB 시퀀스 이름
					initialValue = 1,
					allocationSize = 1)
@Setter
@Getter
public class Team {
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE,
					 generator = "team_seq_gen")
	private Long team_id;
	
	@Column(name = "teamname", length = 100)
	private String name;
	

}
