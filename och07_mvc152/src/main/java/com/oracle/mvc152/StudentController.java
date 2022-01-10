package com.oracle.mvc152;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
	@RequestMapping("/studentForm")
	public String studentForm() {
		System.out.println("@Controller studentForm ");
		return "createPage";
	}
	
	@RequestMapping("/student/create")
	public String studentCreate(@ModelAttribute("student") Student student, BindingResult result) {
		// BindingResult -> 유효성 검증결과를 담아두는 객체
		String page = "createDonePage"; // 성공시
		
		StudentValidator validator = new StudentValidator();
		validator.validate(student, result);
		if(result.hasErrors()) { //error가 있으면 참으로 나옴
			System.out.println("StudentValidator 오류 발생...");
			page = "createPage"; // 에러 발생시
		}
		return page;
	}
	
	
}
