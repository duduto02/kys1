package kr.mjc.kys.spring.ioc.componentscan;

import kr.mjc.kys.spring.ioc.beanfacotory.LgTV;
import kr.mjc.kys.spring.ioc.beanfacotory.TV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Component Scan
@Slf4j
public class TVUserUsingComponentScan {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class); // basePakage 사용
        log. info("빈 구성을 마쳤습니다.");

        TV samsungTV = context.getBean(SamsungTV.class);
        samsungTV.powerOn();
        samsungTV.powerOff();

        TV lgTV = context.getBean("lgTV", LgTV.class);
        lgTV.powerOn();
        lgTV.powerOff();
    }
}
