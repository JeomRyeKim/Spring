package com.oracle.oBootMybatis03.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member3 {
	private String id; 		 
	private String name;		 
	// Not Used
	private String password;
	private Date   reg_date;

}
