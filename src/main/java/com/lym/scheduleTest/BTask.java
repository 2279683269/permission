package com.lym.scheduleTest;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/26 14:27
 * @Description:
 */
@Component
public class BTask {
    @Scheduled(cron = "0/5 * *  * * ? ")   //每5秒执行一次
    public void bTask() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()) + "*********B任务每5秒执行一次进入测试");
    }

}
