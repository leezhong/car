package com.leezhong.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SecondScheduledJob extends QuartzJobBean{


    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("-----自动执行2-------");
    }
}
