package com.image.mlcplanet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
//JDBC설정이 필요없고 어떤 DB를 사용할지 결정하지 않았다면 아래 행 추가
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class RealtimeImageRecognitionApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealtimeImageRecognitionApplication.class, args);
	}
	
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
 
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
 
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
