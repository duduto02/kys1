package kr.mjc.kys.spring.di.javaconfig;

import kr.mjc.kys.spring.ioc.beanfacotory.TV;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LgTV implements TV {
    private Speaker speaker;

    public LgTV() {
        log.debug("lgTV 인스턴스 생성됨.");
    }

    // 세터로 스피커 주입 - 선택적인 의존성
    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
        log.debug("setter로 speaker 주입함.");
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
        speaker.volumeUp();
    }

    @Override
    public void volumeDown() {
        speaker.volumeDown();
    }
}
