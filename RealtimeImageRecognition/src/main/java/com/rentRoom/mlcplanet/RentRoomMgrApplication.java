package com.rentRoom.mlcplanet;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
//JDBC설정이 필요없고 어떤 DB를 사용할지 결정하지 않았다면 아래 행 추가
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class RentRoomMgrApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentRoomMgrApplication.class, args);
	}
	
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
 
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
 
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        
//        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
//        sessionFactory.setMapperLocations(res);
//        
//        return sessionFactory.getObject();
//    }
}
