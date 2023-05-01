package kr.mjc.kys.spring.ioc.beanfacotory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TVUserUsingBeanFactory {
    public static void main(String[] args) {
        BeanFactory factory = new BeanFactory(); // 삼성, 엘지 TV 빈 생성
        log.info("빈 구성을 마쳤습니다.");

        TV samsungTV = (TV) factory.getBean("samsung"); // 삼성 빈 리턴
        samsungTV.powerOn();
        samsungTV.powerOff();

        TV lgTV = (TV) factory.getBean("lg"); // 엘지 빈 리턴
        lgTV.powerOn();
        lgTV.powerOff();
    }
}
