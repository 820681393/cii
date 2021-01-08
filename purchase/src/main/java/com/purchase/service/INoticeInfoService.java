package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.NoticeInfo;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Musa
 * @since 2020-12-26
 */
public interface INoticeInfoService extends IService<NoticeInfo>,Repository<NoticeInfo, Integer> {

    List<NoticeInfo> findByTypeOrderByCreateTimeDesc(Integer type);

    int deleteByEndTimeBefore(Date endTime);
}