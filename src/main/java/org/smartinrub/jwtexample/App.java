package org.smartinrub.jwtexample;

import org.smartinrub.jwtexample.filters.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter(jwtSecret));
        registrationBean.addUrlPatterns("/index");
        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
