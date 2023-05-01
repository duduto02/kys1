package kr.mjc.kys.spring.ioc.componentscan;

import kr.mjc.kys.spring.ioc.beanfacotory.TV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SamsungTV implements TV {
    public SamsungTV(){
        log.debug("samsungTV 인스턴스 생성됨.");
    }

    @Override
    public void powerOn() {
        log.debug("samsungTV power on.");
    }

    @Override
    public void powerOff() {
        log.debug("samsungTV power off.");
    }

    @Override
    public void volumeUp() {
        log.debug("samsungTV volume up.");
    }

    @Override
    public void volumeDown() {
        log.debug("samsungTV volume down.");
    }
}
