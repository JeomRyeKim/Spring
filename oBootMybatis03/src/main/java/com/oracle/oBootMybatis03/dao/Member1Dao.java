package com.oracle.oBootMybatis03.dao;

import java.util.List;

import com.oracle.oBootMybatis03.model.Member3;

public interface Member1Dao {
	int memCount(String id);	// Member1의 Count

	List<Member3> listMem(Member3 member3);
}
