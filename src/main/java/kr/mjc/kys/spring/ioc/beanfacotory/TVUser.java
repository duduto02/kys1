package kr.mjc.kys.spring.ioc.beanfacotory;

public class TVUser {
    public static void main(String[] args) {
        TV samsungTV = new SamsungTV();
        samsungTV.powerOn();
        samsungTV.powerOff();

        TV lgTV = new LgTV();
        lgTV.powerOn();
        lgTV.powerOff();
    }
}
