package kr.mjc.kys.spring.ioc.javaconfig;

import kr.mjc.kys.spring.ioc.beanfacotory.LgTV;
import kr.mjc.kys.spring.ioc.beanfacotory.SamsungTV;
import kr.mjc.kys.spring.ioc.beanfacotory.TV;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Java-based configuration
@Configuration
public class AppConfig {
    @Bean
    public TV samsungTV() {
        return new SamsungTV();
    }

    @Bean
    public TV lgTV() {
        return new LgTV();
    }
}
