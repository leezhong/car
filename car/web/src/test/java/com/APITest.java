package com;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;

public class APITest {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,6);
        calendar.set(Calendar.HOUR_OF_DAY, 14); // 控制时
        calendar.set(Calendar.MINUTE, 19);    // 控制分
        calendar.set(Calendar.SECOND, 00);    // 控制秒
        System.out.println(calendar.getTime());
        for (int i = 0; i < 10 ; i++) {
            calendar.add(Calendar.DAY_OF_MONTH,1);
            System.out.println(calendar.getTime());
        }
    }
}
