package kr.mjc.kys.spring.di.javaconfig;

import kr.mjc.kys.spring.ioc.beanfacotory.TV;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Speaker sonySpeaker() {
        return new SonySpeaker();
    }

    @Bean
    public Speaker appleSpeaker() {
        return new AppleSpeaker();
    }

    @Bean
    public TV samsungTV() {
        return new SamsungTV(sonySpeaker());
    }

    @Bean
    public TV lgTV() {
        LgTV lgTV = new LgTV();
        lgTV.setSpeaker(appleSpeaker());
        return lgTV;
    }
}
