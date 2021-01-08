package com.purchase.task;

import com.purchase.common.log.MyLogger;
import com.purchase.service.INoticeInfoService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Miracle
 * @date 2020/12/30 20:37
 * 定时删除过期公告
 */
@Component
public class DeleteNoticeInfoTask {
    MyLogger myLogger = new MyLogger(DeleteNoticeInfoTask.class);

    @Autowired
    INoticeInfoService iNoticeInfoService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void task() {
        myLogger.info("删除公告到期定时任务启动。。。。");
        int a= iNoticeInfoService.deleteByEndTimeBefore(new Date());
        if(a>0){
            myLogger.info("删除公告到期定时任务成功。。。。");
        }else{
            myLogger.info("删除公告到期定时任务失败。。。。");
        }
    }


}
