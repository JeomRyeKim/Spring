package com.oracle.oBootMybatis03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.SampleVO;
import com.oracle.oBootMybatis03.service.EmpService;

@RestController // @Controller + @ResponseBody
public class EmpRestController {
	@Autowired	// 필드 연결
	private EmpService es;
	
	@RequestMapping("/helloText")
	public String helloText() {
		System.out.println("EmpRestController helloText start");
		String hello = "안녕";
		
		return hello; // 브라우저에 그냥 "안녕"이라고 뜸(StringConverter)
	}
	
	@RequestMapping("/sample/sendVO2")
	public SampleVO sendVO2(int deptno) { // 주소창에 ?deptno=10 값을 넣어줘야 함
		System.out.println("EmpRestController sendVO2 deptno->" + deptno);
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(deptno);
		
		return vo; // {"mno":10,"firstName":"길동","lastName":"홍"} (JsonConverter)
	}
	
	@RequestMapping("/sendVO3")
	public List<Dept> sendVO3(){ // DB 연결 (JsonConverter)
		System.out.println("EmpRestController sendVO3 start");
		List<Dept> deptList = es.deptSelect(); // 있던 코드 재활용
		
		return deptList;
//		[{"deptno":10,"dname":"ACCOUNTING","loc":"NEW YORK"},
//		{"deptno":20,"dname":"RESEARCH","loc":"DALLAS"},
//		{"deptno":30,"dname":"SALES","loc":"CHICAGO"},
//		{"deptno":40,"dname":"OPERATIONS","loc":"BOSTON"},
//		{"deptno":50,"dname":"인사팀","loc":"강남"},
//		{"deptno":51,"dname":"인사1팀","loc":"강남"},
//		{"deptno":52,"dname":"전산2팀","loc":"홍대"},
//		{"deptno":53,"dname":"전산3팀","loc":"홍대"},
//		{"deptno":54,"dname":"전산4팀","loc":"홍대"},
//		{"deptno":55,"dname":"전산5팀","loc":"홍대"}]
	}
	
	@RequestMapping("/empnoDelete")
	public String empnoDelete(Emp emp){ // 파라미터 2개 이상일 때 DTO롤 받으면 됨(Emp emp)이런식으로
		System.out.println("EmpRestController empnoDelete start");
		System.out.println("EmpRestController empnoDelete emp.getEname()->" + emp.getEname());
		int delStatus = es.delete(emp.getEmpno()); // 재활용
		String delStatusStr = Integer.toString(delStatus); // ajax로 받아야하니까 String으로 바꿔줘야 함
		
		return delStatusStr;
	}
	
}
