package com.oracle.oBootMybatis03.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Emp {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private int sal;
	private int comm;
	private int deptno;
	
	//조회용
	private String search;
	private String keyword;
	private String pageNum;
	private int start;
	private int end;
	
}
