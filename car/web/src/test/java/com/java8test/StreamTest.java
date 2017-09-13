package com.java8test;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTest {

    @Test
    public void test1(){
        int[] ints = {1,6,8,4,23,96,0};
        IntStream.range(0,ints.length).forEach(System.out::println);
    }
    @Test
    public void test2(){
        Integer[] ints = {1,6,8,4,23,96,0};
//        IntSummaryStatistics statistics = IntStream.of(ints).summaryStatistics();
//        int max = statistics.getMax();
//        System.out.println(max);

        List<Integer> integers = Arrays.asList(ints);
        IntSummaryStatistics intSummaryStatistics = integers.stream().mapToInt(x -> x).summaryStatistics();

    }
}
