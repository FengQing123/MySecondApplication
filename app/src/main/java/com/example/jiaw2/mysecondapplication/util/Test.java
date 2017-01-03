package com.example.jiaw2.mysecondapplication.util;

import java.lang.reflect.Type;
import java.security.KeyRep;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by jiaw2 on 2016/12/5.
 */
public class Test {
    public static void main(String[] args) {

//        if (new Object() {
//            public boolean f() {
//                return false;
//            }
//        }.f()) {
//            System.out.println("1");
//        } else {
//            System.out.println("2");
//        }
//
//        RemoveDuplicateElements();
        /*-------------------------------------------*/
    }

    /**
     * 删除list中重复的元素
     */
    public static void RemoveDuplicateElements() {
        List<String> list = new ArrayList<String>();
        list.add("JAVA");
        list.add("Android");
        list.add("PHP");
        list.add("Android");
        list.add("IOS");
        list.add("IOS");
        list.add("JAVA");
        System.out.println("duplicate elements=" + list);

        LinkedHashSet<String> set = new LinkedHashSet<>(list);
        List<String> list1 = new ArrayList<>(set);

        System.out.println("without duplicate elements=" + list1);

    }
}
