package com.huan.scheduler;

import com.huan.quartz.HelloJobs;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException {
        Date date = new Date();
        date.setTime(3000);
        Date endDate = new Date();
        endDate.setTime(6000);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("map","map");
        //定义处理类
        JobDetail build = JobBuilder.newJob(HelloJobs.class).withIdentity("job", "jobGroup1")

                .build();
        //定义一个Tigger 实例，定义该job立即执行，并且没两秒执行一次
        jobDataMap.put("as","as");
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("tigger", "tiggergroup1").startNow()
                .startAt(date)
                .endAt(endDate)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(2)
                        .withRepeatCount(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW))
                .usingJobData(jobDataMap)

                .build();
        //创建scheduler 调度类
        SchedulerFactory sfat = new StdSchedulerFactory();
        Scheduler scheduler = sfat.getScheduler();
        //tigger 与 job 绑定
        scheduler.scheduleJob(build,trigger);
        scheduler.start();
    }
}
