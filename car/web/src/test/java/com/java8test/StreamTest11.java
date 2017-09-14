package com.java8test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamTest11 {

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        //计算空字符串数量
        long count = strings.stream().filter((x) -> x.isEmpty()).count();
        System.out.println("空字符串的数量:"+count);
        //计算字符串长度为3的字符串数量
        strings.stream().filter((x) -> x.length() == 3).map((str)->str+",").forEach(System.out::println);
        //删除空字符串
        List<String> collect = strings.stream().filter(x -> !x.isEmpty()).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
        //删除空字符串并用逗号间隔合并为字符串
        String collect1 = collect.stream().filter(str -> !str.isEmpty()).collect(Collectors.joining(", "));
        System.out.println(collect1);


        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        //获取列表元素平方
        numbers.stream().map(x->x*x).forEach(System.out::println);
        //获取列表元素最大/平均等
        IntSummaryStatistics statistics = numbers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("最大:"+statistics.getMax());
        System.out.println("列表中元素数量:"+statistics.getCount());
        //获取十个随机整数
        new Random().ints().limit(10).forEach(System.out::println);


    }
}
