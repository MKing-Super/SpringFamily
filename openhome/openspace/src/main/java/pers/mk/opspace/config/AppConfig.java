package pers.mk.opspace.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mk.data.annotation.DataAnnotation;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;

@Configuration
//自定义的注解
@DataAnnotation
@ImportResource(locations = {"classpath:/config/*.xml"})
public class AppConfig {

//    从application.yml中获取的配置信息
//    @Value("${spring.datasource.username}")
//    private String username;

    /**
     * 等同于spring-beans中的数据库配置
     * @return
     */
//    @Bean
//    public DruidDataSource mkDataSource(){
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl("jdbc:mysql://localhost:3306/boot_crm?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8");
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUsername("root");
//        dataSource.setPassword("123");
//        return dataSource;
//    }

    @Bean
    public OkHttpClient okHttpClient(){

        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();
    }

}
