package kr.mjc.kys.spring.di.javaconfig;

import kr.mjc.kys.spring.ioc.beanfacotory.TV;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SamsungTV implements TV {
    private Speaker speaker;

    // 컨스트럭터로 스피커 주입 - 필수적인 의존성
    public SamsungTV(Speaker speaker) {
        this.speaker = speaker;
        log.debug("samsungTV 인스턴스 생성됨.");
        log.debug("constructor로 speaker 주입함.");
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
        speaker.volumeUp();
    }

    @Override
    public void volumeDown() {
        speaker.volumeDown();
    }
}
