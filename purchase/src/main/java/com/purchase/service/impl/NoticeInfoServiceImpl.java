package com.purchase.service.impl;

import com.purchase.model.NoticeInfo;
import com.purchase.dao.INoticeInfoDao;
import com.purchase.service.INoticeInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Musa
 * @since 2020-12-26
 */
@Service
public class NoticeInfoServiceImpl extends MyBatisJapService<INoticeInfoDao, NoticeInfo> implements INoticeInfoService {

    @Override
    public List<NoticeInfo> findByTypeOrderByCreateTimeDesc(Integer type) {
        return jpaFindByMany(type);
    }

    @Override
    public int deleteByEndTimeBefore(Date endTime) {
        return jpaDelete(endTime);
    }
}