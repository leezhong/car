package com.java8test;

import org.junit.Test;

import java.sql.Time;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LambdaDemo {

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("1111");
        }).start();
    }

    @Test
    public void test1(){
        Integer[] integers = {1,56,9,77,213};
        List<Integer> integers1 = Arrays.asList(integers);
        integers1.forEach(System.out::println);
        integers1.forEach((integer)->{
            System.out.println(integer);
        });
    }
    @Test
    public void test2(){
        String[] strings = {"azssafasfsd","sadsda","sdsssssss"};
        List<String> strs = Arrays.asList(strings);
        Collections.sort(strs,(String a,String b)->a.length()-b.length());
        System.out.println(strs);
    }
    @Test
    public void test3(){
        new Thread(()->{
            System.out.println(2222);
        }).start();

        Runnable runnable = ()->{ System.out.println(111); };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Test
    public void testPredicate(){
        String[] strings = {"sdkfh","sssaa","saoiuowi"};
        List<String> strList = Arrays.asList(strings);
        filter(strList,(str)->((String)str).endsWith("a"));
    }

    /**
     *test Stream
     */
    @Test
    public void test5(){
        String[] strings = {"sdkfh","sssaa","saoiuowi","ss","aaa","f"};
        List<String> strList = Arrays.asList(strings);
//        long count = strList.stream().filter(string -> string.isEmpty()).count();
//        System.out.println(count);
//        strList.stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);

        List<String> collect = strList.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }


    public void filter(List<String> list,Predicate predicate){
        list.forEach((string)->{
            if(predicate.test(string)){
                System.out.println(string);
            }
        });
    }

}
