package kr.mjc.kys.spring.ioc.componentscan;

import kr.mjc.kys.spring.ioc.beanfacotory.LgTV;
import kr.mjc.kys.spring.ioc.beanfacotory.SamsungTV;
import kr.mjc.kys.spring.ioc.beanfacotory.TV;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "kr.mjc.kys.spring.ioc.componentscan")
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

// 컴포넌트 스캔 appconfig에 @Bean을 구성해도 상관 없음