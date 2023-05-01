package kr.mjc.kys.basics.collections;

import java.util.HashMap;
import java.util.Map;

public class MapEx {
    public static void main(String[] args) {
        Student s1 = new Student(1, "Jacob");
        Student s2 = new Student(2, "Rachel");
        Student s3 = new Student(3, "David");

        Map<Integer, Student> map1 = new HashMap<>();
        map1.put(1, s1);
        map1.put(2, s2);
        map1.put(3, s3);

        System.out.println(map1);
        System.out.println(map1.get(2));
        System.out.println(map1.get(3));

        /* entryset */
        for (Map.Entry<Integer, Student> entry : map1.entrySet()) {
            System.out.format("key:%d, value:%s\n", entry.getKey(), entry.getValue());
        }

        /* keyset */
        for (int key : map1.keySet()) {
            Student value = map1.get(key);
            System.out.println(value);
        }

        map1.forEach((k, v) -> System.out.format("%d : %s\n", k, v));
    }
}
