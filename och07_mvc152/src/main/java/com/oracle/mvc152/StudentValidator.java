package com.oracle.mvc152;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator { //검증 로직

	@Override
	public boolean supports(Class<?> arg0) {
		//검증할 객체의 클래스 타입 정보 설정
		return Student.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 진짜 검증 로직 작성
		System.out.println("validate()");
		Student student = (Student)target;
		
		String studentName = student.getName();
		if(studentName == null || studentName.trim().isEmpty()) {
			System.out.println("studentName is null or empty");
			errors.rejectValue("name", "이름이 없거나 비어 있습니다"); // reject : 거절, rejectValue : 거절사유
		}
		
		int studentId = student.getId();
		if (studentId == 0) { //0이면 입력을 안 했다는 의미
			System.out.println("studentId is 0");
			errors.rejectValue("id", "trouble");
		}

	}

}
