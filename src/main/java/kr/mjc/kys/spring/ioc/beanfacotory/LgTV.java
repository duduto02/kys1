package kr.mjc.kys.spring.ioc.beanfacotory;

import lombok.extern.slf4j.Slf4j;

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
