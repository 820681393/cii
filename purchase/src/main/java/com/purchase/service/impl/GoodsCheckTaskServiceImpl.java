package com.purchase.service.impl;

import com.github.pagehelper.PageInfo;
import com.purchase.model.GoodsCheckTask;
import com.purchase.dao.IGoodsCheckTaskDao;
import com.purchase.service.IGoodsCheckTaskService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Musa
 * @since 2021-01-17
 */
@Service
public class GoodsCheckTaskServiceImpl extends MyBatisJapService<IGoodsCheckTaskDao, GoodsCheckTask> implements IGoodsCheckTaskService {

    @Override
    public PageInfo<GoodsCheckTask> selectGoodsCheckTaskPageInfo(GoodsCheckTask goodsCheckTask) {
        return jpaPageInfo(goodsCheckTask,goodsCheckTask.getPageNum(),goodsCheckTask.getPageSize());
    }

    @Override
    public List<GoodsCheckTask> findByStateAndReceiveAiid(Integer state, Integer receiveAiid) {
        return jpaFindByMany(state,receiveAiid);
    }

}