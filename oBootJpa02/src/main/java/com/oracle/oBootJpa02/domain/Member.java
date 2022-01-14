package com.oracle.oBootJpa02.domain;

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
@Getter //getter 만들어줌
@Setter	//setter 만들어줌
@SequenceGenerator( name = "member_seq_gen",			   // Entity에서 쓸 변수명
				    sequenceName = "member_seq_generator", // 매핑할 DB 시퀀스의 이름
				    initialValue = 1,
				    allocationSize = 1
				  )
// Entity의 이름과 DB테이블 명을 통일시키는게 맞는데 프로젝트 중간에 DB 테이블을 변경해야 하는 경우 Entity이름 =/= DB테이블명
@Table(name = "member1")	// Entity는 Member로 만들었지만 DB에 테이블 이름은 member1로 생성한다(매핑한다)	
public class Member {
	
	@Id	// PK : 자동적으로 not null
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
					generator = "member_seq_gen" // Entity에서 쓸 변수명
					) //strategy : 전략 
	@Column(name = "member_id", precision = 10 ) // 변수명은 id지만 DB 컬럼명은 member_id로 할꺼야 
	private Long id;
	@Column(name = "user_id", length = 50) // length = 50 : 50byte, length는 String 타입에만 사용
	private String name;
	
	// FK
	@ManyToOne // Member는 N , Team 1 다대일 관계를 권장함, Team에 FK를 걸어도 되지만 일대다를 권장하지 않음
	@JoinColumn(name = "team_id") // 연관 관계 잡기 - 자동으로 join처리 해줌
	private Team team;
	
//	Hibernate: create sequence member_seq_generator start with 1 increment by  1
//	Hibernate: create table member1 (member_id number(19,0) not null, user_id varchar2(50 char), team_id number(19,0), primary key (member_id))
//	Hibernate: create table team (team_id number(19,0) not null, name varchar2(255 char), primary key (team_id))
//	Hibernate: alter table member1 add constraint FKhraf05ariq9gyb24lq0nr9qtd foreign key (team_id) references team
	
	//실제 테이블에서 사용하지 않을 변수들, 버퍼로만 사용할 것임 - 버퍼(Buffer)란, 데이터를 한 곳에서 다른 곳으로 전송하는 동안 일시적으로 그 데이터를 보관하는 메모리 영역을 의미
	//필드 매핑하지 않음
	//데이터베이스에 저장하지 않음, 조회하지 않음
	//주로 메모리상에서맊 임시로 어떤 값을 보관하고 싶을 때 사용
	@Transient // 특정 필드를 컬럼에 매핑하지 않음(매핑 무시)
	private Long teamid;
	@Transient
	private String teamname;
}
