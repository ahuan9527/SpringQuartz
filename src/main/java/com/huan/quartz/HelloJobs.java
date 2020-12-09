package com.huan.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * job hello world
 */
@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
public class HelloJobs implements Job {


    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间为："+simpleDateFormat.format(date));
        JobDataMap map = jobExecutionContext.getTrigger().getJobDataMap();
        Map<String, Object> wrappedMap = map.getWrappedMap();
        Iterator<Map.Entry<String, Object>> iterator = wrappedMap.entrySet().iterator();
        while ( iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            System.out.println(next.getKey()+"  "+next.getValue());
        }
    }
}
