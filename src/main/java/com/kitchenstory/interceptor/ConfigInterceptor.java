package com.kitchenstory.interceptor;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigInterceptor implements WebMvcConfigurer {
	@Autowired
	UserRequestInterceptor userRequestInterceptor;
	@Autowired
	AdminRequestInterceptor adminRequestInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.userRequestInterceptor).addPathPatterns("/user/**")
				.excludePathPatterns("/user/login", "/user/register");

		registry.addInterceptor(this.adminRequestInterceptor).addPathPatterns("/admin/**")
				.excludePathPatterns("/admin/login");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**")
		.addResourceLocations(
				new File("F:\\JavaWorkspace\\KitchenStrory\\src\\main\\resources\\static\\images\\").toURI()
						.toString());
	}
}
