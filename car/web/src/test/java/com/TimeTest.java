package com;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeTest {
    public static void main(String[] args) {
//        timer11();
//        timer2();
//        timer3();
        timer4();
    }

    // 第一种方法：设定指定任务task在固定的(毫秒)时间后执行一次 schedule(TimerTask task, Long delay)
    public static void timer1() {
        System.out.println("开始时间:"+System.currentTimeMillis());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("执行时间:"+System.currentTimeMillis());
                System.out.println("-------设定要指定任务--------");
            }
        }, 2000);// 设定指定的时间time,此处为2000毫秒
    }
    //方法二: 设定固定时间执行一次
    public static void timer11() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 14); // 控制时
        calendar.set(Calendar.MINUTE, 19);    // 控制分
        calendar.set(Calendar.SECOND, 00);    // 控制秒
        Date time = calendar.getTime();

        System.out.println("开始时间:"+System.currentTimeMillis());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("执行时间:"+System.currentTimeMillis());
                System.out.println("-------设定要指定任务--------");
                System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            }
        }, time);
    }

    // 第三种方法：设定指定任务task在指定延迟delay执行一次,后进行按照固定频率peroid进行执行
    // schedule(TimerTask task, long delay, long period)
    public static void timer2() {
        Timer timer = new Timer();
        System.out.println("开始时间:"+System.currentTimeMillis());
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("执行时间:"+System.currentTimeMillis());
                System.out.println("-------设定要指定任务--------");
            }
        }, 5000, 10000);
    }


    // 第四种方法：安排指定的任务task在指定的时间firstTime开始进行重复的固定速率period执行．
    // Timer.scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
    public static void timer4() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 14); // 控制时
        calendar.set(Calendar.MINUTE, 30);    // 控制分
        calendar.set(Calendar.SECOND, 0);    // 控制秒

        Date time = calendar.getTime();     // 得出执行任务的时间,此处为今天的12：00：00

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("执行时间:"+System.currentTimeMillis());
                System.out.println(new Date());
                System.out.println("-------设定要指定任务--------");
            }
        }, time, 1000*60);// 这里设定将延时每天固定执行
    }
}
