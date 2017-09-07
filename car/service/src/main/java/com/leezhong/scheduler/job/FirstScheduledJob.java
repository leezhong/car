package com.leezhong.scheduler.job;

import com.leezhong.dao.UserInfoMapper;
import com.leezhong.domain.UserInfo;
import com.leezhong.scheduler.job.utils.AuthBean;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class FirstScheduledJob extends QuartzJobBean{

   private UserInfoMapper userInfoMapper;

    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("----scheduled-----");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(2);
        System.out.println(userInfo.getAddr());
    }
}
