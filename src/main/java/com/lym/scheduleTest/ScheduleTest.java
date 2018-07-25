package com.lym.scheduleTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/25 10:51
 * @Description:
 */
@Component
@Slf4j
public class ScheduleTest {

    @Scheduled(cron = "0/5 * * * * ?")
    public void schTest1() {
        Date date = new Date();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sim.format(date);
        System.out.println("这是spring定时器1，每五秒执行一次,当前时间：" + dateStr);
        log.info("这是spring定时器1，每五秒执行一次,当前时间：" + dateStr);
    }

}
