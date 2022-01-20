package com.oracle.oBootMybatis03.dao;

import java.util.HashMap;
import java.util.List;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVO;

public interface DeptDao {
	List<Dept> deptSelect();

	void insertDept(DeptVO deptVO);

	void selListDept(HashMap<String, Object> map);
}
