package pers.mk.opspace.config;

import com.mk.data.annotation.DataAnnotation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;

@Configuration
@DataAnnotation
@ImportResource(locations = {"classpath:/config/*.xml"})
public class AppConfig {

//    @Value("${spring.datasource.username}")
//    private String username;
//
//    @Bean
//    public void mkDataSource(){
//        System.out.println(username);
//    }
}
