package com.cos.crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//세션체크, 권한체크, 파일업로드, 로그남기기
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/*
	 * .addPathPatterns("/**");
	 * .excludePathPatterns("/user/*")
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// /board
		registry.addInterceptor(new SessionInterceptor())
		//board경로를 타는 페이지들은 전부 세션 있어야 들어가지게 요청
		.addPathPatterns("/board/**")
		//리스트와 상세보기는 세션없이 가능하게 설정
		.excludePathPatterns("/board/list")
		.excludePathPatterns("/board/detail/**");
		
		
//		registry.addInterceptor(new AdminInterceptor())
//		.addPathPatterns("/admin/**");
	}
}
