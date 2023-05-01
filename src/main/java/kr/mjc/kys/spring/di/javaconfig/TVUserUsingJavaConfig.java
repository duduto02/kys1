package kr.mjc.kys.spring.di.javaconfig;

import kr.mjc.kys.spring.ioc.beanfacotory.TV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class TVUserUsingJavaConfig {
    public static void main(String[] args) {
        // AppConfig 작성, context (= container) 구성 ->
        // 애플, 소니 스피커/삼성, 엘지 티비 모두 구성 완료
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        log.debug("빈 구성을 마쳤습니다.");

        TV samsungTV = context.getBean(SamsungTV.class);
        samsungTV.volumeUp(); // 클래스 SamsungTV에 스피커가 인젝션 되어있는 상태

        TV lgTV = context.getBean(LgTV.class);
        lgTV.volumeDown(); // 클래스 LgTV에 스피커가 인젝션 되어있는 상태
    }
}
