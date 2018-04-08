package org.smartinrub.jwtexample;

import org.smartinrub.jwtexample.filters.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {
		FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new JwtFilter());
		bean.addUrlPatterns("/index");
		return bean;
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
