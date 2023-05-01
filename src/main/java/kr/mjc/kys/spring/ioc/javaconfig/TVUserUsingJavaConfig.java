package kr.mjc.kys.spring.ioc.javaconfig;

import kr.mjc.kys.spring.ioc.beanfacotory.LgTV;
import kr.mjc.kys.spring.ioc.beanfacotory.SamsungTV;
import kr.mjc.kys.spring.ioc.beanfacotory.TV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Java-based configuration
@Slf4j
public class TVUserUsingJavaConfig {
    public static void main(String[] args) {
        // context 인스턴스에 TV 빈(= 인스턴스) 생성
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        log.info("빈 구성을 마쳤습니다.");

        // 타입으로 찾기, 느리지만 컴파일때 오류 확인 가능
        // 클래스 AppConfig에서 samsungTV 타입 찾아옴
        TV samsungTV = context.getBean(SamsungTV.class);
        samsungTV.powerOn();
        samsungTV.powerOff();

        // 이름으로 찾기, 속도 빠르지만 name 잘못 쓰면 런타임때 오류 확인
        // 클래스 AppConfig에서 lgTV 이름 찾아옴
        TV lgTV = (LgTV) context.getBean("lgTV");
        // == TV lgTV = context.getBean("lgTV", LgTV.class);
        lgTV.powerOn();
        lgTV.powerOff();
    }
}
