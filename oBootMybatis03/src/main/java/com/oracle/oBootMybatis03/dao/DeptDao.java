package com.oracle.oBootMybatis03.dao;

import java.util.List;

import com.oracle.oBootMybatis03.model.Dept;

public interface DeptDao {
	List<Dept> deptSelect();
}
