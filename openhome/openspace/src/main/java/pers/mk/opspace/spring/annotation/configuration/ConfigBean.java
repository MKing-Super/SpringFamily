package pers.mk.opspace.spring.annotation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.spring.annotation.configuration
 * @Date 2023/5/11 9:28
 * @Version 1.0
 */
@Configuration
public class ConfigBean {

    @Bean
    public User user1(){
        return new User();
    }

    @Bean({"userBean2","mkbean"})
    public User user2(){
        return new User();
    }

    @Bean({"userBean3","userBean3Alias1","userBean3Alias2"})
    public User user3(){
        return new User();
    }

}
