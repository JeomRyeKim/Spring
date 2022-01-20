package com.oracle.oBootMybatis03.controller;

import java.util.HashMap;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVO;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;
import com.oracle.oBootMybatis03.service.EmpService;
import com.oracle.oBootMybatis03.service.Paging;

@Controller
public class EmpController {
	@Autowired	// 필드 연결
	private EmpService es;
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value = "list")
	public String list(Emp emp, String currentPage, Model model) {
		System.out.println("EmpController list Start...");
		int total = es.total();	//Emp count -> 14
		System.out.println("EmpController list total->" + total);
		System.out.println("EmpController currentPage->" + currentPage);
		//						42	  NULL(0,1...) 2
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart());	// 시작시 1
		emp.setEnd(pg.getEnd());		// 시작시 10
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController list listEmp.size()->" + listEmp.size());
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);
		
		return "list";
	}
	
	@GetMapping(value = "detail")
	public String detail(int empno, Model model) { // pk인 empno로 쿼리문에서 select하고 리턴값으론 Emp 정보 전부를 받음
		// Emp emp = es.detail(emp)
		// Dao -> 	 ed.detail(emp)
		// mapper -> tkEmpSelOne , empno
		System.out.println("EmpController detail start...");
		Emp emp = es.detail(empno);
		model.addAttribute("emp", emp);
		
		return "detail";
	}
	
	@GetMapping(value = "updateForm")
	public String updateForm(int empno, Model model) {
		System.out.println("EmpController updateForm start...");
		Emp emp = es.detail(empno);
		model.addAttribute("emp", emp);
		
		return "updateForm";
	}
	
	@PostMapping(value = "update")
	public String update(Emp emp, Model model) {
		//int k = es.update(emp);
	    //Dao -> kkk = session.update("TKempUpdate",emp);
	    //mapper -> TKempUpdate
		System.out.println("EmpController update start...");
		int uptCnt = es.update(emp);
		System.out.println("EmpController update Count->" + uptCnt);
		model.addAttribute("uptCnt", uptCnt);				//Test Controller간 Data 전달
		model.addAttribute("kk3", "Message Test");			//Test Controller간 Data 전달
		
		return "forward:list";		// uptCnt 값을 list.jsp에 나타냄
		//return "redirect:list";	- uptCnt 값을 list.jsp에 못 나타냄
	}
	
	@RequestMapping(value = "writeForm")
	public String writeForm(Model model) {
		//Emp emp = null;
		// 관리자 사번만 Get
		List<Emp> empList = es.listManager();
		System.out.println("EmpController writeForm emplist.size()->" + empList.size());
		model.addAttribute("empMngList", empList);	// emp Manager List
		// 부서(코드,부서명) Get
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList", deptList);	// dept
		
		return "writeForm";
	}
	
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(Emp emp, Model model) {
		System.out.println("EmpController write Start...");
		//Service, Dao, Mepper명[insertEmp]까지 -> insert
		int result = es.insert(emp); // 성공시 1, 실패시 0
		if (result > 0) return "redirect:list";
		else {
			model.addAttribute("msg","입력 실패 확인해 보세요");
			return "forward:writeForm";
		}
	}
	
	@GetMapping(value = "confirm") // 사번 중복확인
	public String confirm(int empno, Model model) {
		Emp emp = es.detail(empno);
		model.addAttribute("empno", empno);
		if(emp != null) { // 기존에 사번이 있다는 의미
			// writeForm에서 <c:if test="${msg!=null}">${msg}</c:if>로 ajax같은 느낌을 냄(ajax는 아님)
			model.addAttribute("msg", "중복된 사번입니다"); 
			return "forward:writeForm";
		}else {
			model.addAttribute("msg", "사용 가능한 사번입니다");
			return "forward:writeForm";
		}
	}
	
	@RequestMapping(value = "delete")
	public String delete(int empno, Model model) {
		System.out.println("EmpController delete start...");
		int result = es.delete(empno);
		
		return "redirect:list";
	}
	
	@GetMapping(value = "listEmpDept")
	public String listEmpDept(Model model) {
		EmpDept empDept = null;
		System.out.println("EmpController listEmpDept start...");
		// Service, DAO -> listEmpDept / mapper만 -> TKlistEmpDept
		List<EmpDept> listEmpDept = es.listEmpDept();
		model.addAttribute("listEmpDept", listEmpDept);
		
		return "listEmpDept";
	}
	
	@RequestMapping(value = "mailTransport")
	public String mailTransport(HttpServletRequest request, Model model) {
		System.out.println("EmpController mailTransport start...");
		String tomail = "";		// 받는 사람 이메일
		System.out.println("tomail");
		
		String setfrom = "보낼 내 구글 이메일";
		String title = "mailTransport 입니다";			// 제목
		try {
			// Mime 전자우편 Internet 표준 Format <- Mime프로토콜
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom);	// 보내는 사람 생략하거나 하면 정상작동을 안 함 (yml에 있는 이메일로 해야함)
			messageHelper.setTo(tomail);	// 받는 사람 이메일
			messageHelper.setSubject(title);// 메일 제목은 생략이 가능하다
			
			String tempPassword = (int) (Math.random() * 999999) + 1 + "";
			messageHelper.setText("임시 비밀번호입니다 : " + tempPassword); // 메일 내용
			System.out.println("임시 비밀번호입니다 : " + tempPassword);
			
			// import javax.activation.DataSource; (DB연결하는 것과는 다름)
			DataSource dataSource = new FileDataSource("c:\\log\\jung1.jpg"); // 첨부문서 보낼 때 
			// addAttachment : 첨부하다
			messageHelper.addAttachment(MimeUtility.encodeText("airport.png", "UTF-8", "B"), dataSource); //"airport.png"로 리네임 시킬 수 있다 / B : Base64
			mailSender.send(message);
			model.addAttribute("check", 1);	// 정상 전달
//			s.tempPw(u_id, tempPassword); 	// db에 비밀번호를 임시비밀번호로 업데이트
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("check", 2);	// 메일 전달 실패
		}
		return "mailResult";
	}
	
	// Procedure Test 입력화면
	@RequestMapping(value = "writeDeptIn", method = RequestMethod.GET) // 프로시저 입력 
	public String writeDeptIn(Model model) {
		System.out.println("EmpController writeDeptIn start...");
		
		return "writeDept3";
	}
	
	// Procedure Test 입력 후 VO 전달
	@PostMapping(value = "writeDept")
	public String writeDept(DeptVO deptVO, Model model) {
		//DeptVo reDeptVo = es.insertDept(deptVO);	// 일반 Service
		es.insertDept(deptVO);	// Procedure Call
		if(deptVO == null) {
			System.out.println("EmpController deptVo NULL");
		}else {
			System.out.println("EmpController deptVO.getOdeptno()->" + deptVO.getOdeptno());
			System.out.println("EmpController deptVO.getOdname()->" + deptVO.getOdname());
			System.out.println("EmpController deptVO.getOloc()->" + deptVO.getOloc());
			model.addAttribute("msg", "정상 입력 되었습니다^^");
			model.addAttribute("dept", deptVO);
		}
		return "writeDept3";
	}
	
	@GetMapping(value = "writeDeptCursor")
	public String writeDeptCursor(Model model) {
		System.out.println("EmpController writeDeptCursor start...");
		HashMap<String, Object> map = new HashMap<String, Object>(); //여러개의 파라미터를 담아서 가고 싶을 때 HashMap 사용
		map.put("sDeptno", 10);	// DTO(정형화O, 문서화)로 넣기는 애매하고, 1회성으로 사용할 변수는 map(정형화X, 파악하기 힘듬, 일일이 추적해야함-권장 안 함)으로 사용함
		map.put("eDeptno", 55); // DTO를 만들기 싫고(개발이 완료됐고 유지보수중일 때) 급할 때 사용함 그래서 map에 담아서 감
		es.selListDept(map);
		List<Dept> deptLists = (List<Dept>) map.get("dept"); // Dept.xml에서 선언한 #{dept,   mode=OUT, jdbcType=CURSOR,
		for(Dept dept : deptLists) { // ResultSet
			System.out.println("EmpController writeDeptCursor dept.getDname()->" + dept.getDname());
			System.out.println("EmpController writeDeptCursor dept.getLoc()->" + dept.getLoc());
		}
		System.out.println("EmpController writeDeptCursor deptLists.size()->" + deptLists.size());
		model.addAttribute("deptList", deptLists);
		
		return "writeDeptCursor";
	}
	
}
