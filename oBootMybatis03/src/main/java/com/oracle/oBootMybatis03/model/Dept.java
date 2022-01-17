package com.oracle.oBootMybatis03.model;

import lombok.Getter;

@Getter
public class Dept {
	private int    deptno;
	private String dname;
	private String loc;
	
	public void setDeptno(int deptno) {
		if (deptno > 0 ) this.deptno = deptno;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
}
