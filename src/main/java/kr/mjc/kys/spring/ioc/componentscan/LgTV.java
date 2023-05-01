package kr.mjc.kys.spring.ioc.componentscan;

import kr.mjc.kys.spring.ioc.beanfacotory.TV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LgTV implements TV {
    public LgTV() {
        log.debug("lgTV 인스턴스 생성됨.");
    }

    @Override
    public void powerOn() {
        log.debug("lgTV power on.");
    }

    @Override
    public void powerOff() {
        log.debug("lgTV power off.");
    }

    @Override
    public void volumeUp() {
        log.debug("lgTV volume up.");
    }

    @Override
    public void volumeDown() {
        log.debug("lgTV volume down.");
    }
}
