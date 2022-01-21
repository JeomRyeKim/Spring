package com.oracle.oBootMybatis03.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.oracle.oBootMybatis03.service.SampleInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer { // WebMvcConfigurer : web에서 url을 쳤을 때 환경 설정하는 인터페이스
	// interCeptor
	public void addInterceptors(InterceptorRegistry registry) { //registry : 내부 저장소
		// url에 /interCeptor라고 치면 addInterceptor로 SampleInterceptor가 가로채서 처리하게 해주는 환경 셋팅을 해줄게
		registry.addInterceptor(new SampleInterceptor()).addPathPatterns("/interCeptor")
//														.addPathPatterns("/kk3")
//														.addPathPatterns("/kk3")
//														.addPathPatterns("/kk3")
//														.addPathPatterns("/kk3")
//														.addPathPatterns("/kk3") - interCeptor 여러개 사용할 때 이렇게 사용하면 됨!
		; 
	}
}
