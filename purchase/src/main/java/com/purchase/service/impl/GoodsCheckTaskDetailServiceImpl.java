package com.purchase.service.impl;

import com.purchase.model.GoodsCheckTaskDetail;
import com.purchase.dao.IGoodsCheckTaskDetailDao;
import com.purchase.service.IGoodsCheckTaskDetailService;
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
public class GoodsCheckTaskDetailServiceImpl extends MyBatisJapService<IGoodsCheckTaskDetailDao, GoodsCheckTaskDetail> implements IGoodsCheckTaskDetailService {

    @Override
    public List<GoodsCheckTaskDetail> findByGctid(Integer gctid) {
        return jpaFindByMany(gctid);
    }
}