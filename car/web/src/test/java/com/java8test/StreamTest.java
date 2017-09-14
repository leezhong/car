package com.java8test;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {


    @Test
    public void testForeach(){
        Integer[] integers = {1,6,8,4,23,96,0};
        List<Integer> list = Arrays.asList(integers);
        list.stream().forEach((x)->{
            System.out.println(x);
        });
    }
    @Test
    public void testMap(){
        Integer[] ints = {1,6,8,4,23,96,0};
     Arrays.asList(ints).stream().map(x -> 2 * x).collect(Collectors.toList()).forEach(System.out::println);
    }
    @Test
    public void testFilter(){
        String[] strings = {"aaa","bbba","ccccb"};
        List<String> stringList = Arrays.asList(strings);
        stringList.stream().filter(str->str.endsWith("a")).forEach(System.out::println);
    }

    @Test
    public void testLimit(){
        String[] strings = {"aaa","bbba","ccccb"};
        List<String> stringList = Arrays.asList(strings);
        stringList.stream().limit(1).forEach((x)-> System.out.println(x));
    }

    @Test
    public void testSorted(){
        String[] strings = {"aassssa","ba","ccccb"};
        List<String> stringList = Arrays.asList(strings);
        stringList.stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);
    }
    @Test
    public void testTj(){
        int[] integers = {1,6,8,4,23,96,0};
        IntSummaryStatistics stats = IntStream.of(integers).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }
    @Test
    public void testStream2XXXStream(){

    }

}
