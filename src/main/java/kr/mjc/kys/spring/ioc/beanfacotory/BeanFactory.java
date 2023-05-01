package kr.mjc.kys.spring.ioc.beanfacotory;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
    Map<String,Object> map = new HashMap<>();  // 1) 맵 생성

    public BeanFactory() {  // 2) 컨스트럭터에서 인스턴스(= 빈) 생성해서 맵에 보관
        map.put("samsung", new SamsungTV());
        map.put("lg", new LgTV());
    }
    public Object getBean(String name) {   // 3) 미리 생성한 빈 리턴
        return map.get(name);
    }
}
