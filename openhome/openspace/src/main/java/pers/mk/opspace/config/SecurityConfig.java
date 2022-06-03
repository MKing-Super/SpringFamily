package pers.mk.opspace.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description: TODO
 * @Author: kun.ma
 * @Date: 2022/6/3 12:40
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()			// 对请求进行授权
                .antMatchers("/index")		// 针对 /index.jsp路径进行授权
                .permitAll()					// 可以无条件访问
                .antMatchers("/layui/**")		// 针对 /layui目录下的静态资源进行授权
                .permitAll()					// 设置其可以无条件访问
                .and()
                .authorizeRequests()			// 对请求进行授权
                .anyRequest()					// 任意的请求
                .authenticated()				// 都需要登录之后才能访问
                .and()
                .formLogin()
        ;
    }
}
