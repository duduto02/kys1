package kr.mjc.kys.basics.collections;

import java.util.HashSet;
import java.util.Set;

public class SetEx {
    public static void main(String[] args) {
        Set<Student> set1 = new HashSet<>();
        Student s1 = new Student(1,"Jacob");
        Student s2 = new Student(2,"Rachel");
        Student s3 = new Student(3, "David");
        Student s4 = new Student(1, "Jacob");

        set1.add(s1);
        set1.add(s2);
        set1.add(s3);
        set1.add(s4);

        System.out.println(set1);

        System.out.println(s1.hashCode());
        System.out.println(s4.hashCode());
        System.out.println(s1.equals(s4));
    }
}
