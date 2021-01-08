package com.purchase.service.impl;

import com.mybaits.jpa.annotation.LinkSelect;
import com.purchase.model.OrderInfoDetail;
import com.purchase.dao.IOrderInfoDetailDao;
import com.purchase.service.IOrderInfoDetailService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 下单详细信息 服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@Service
public class OrderInfoDetailServiceImpl extends MyBatisJapService<IOrderInfoDetailDao, OrderInfoDetail> implements IOrderInfoDetailService {

    @Override
    public List<OrderInfoDetail> findByOiid(Integer oiid) {
        return jpaFindByMany(oiid);
    }

    @Override
    public List<OrderInfoDetail> findByCreateTimeGreaterThanAndCreateTimeLessThan(Date startDate, Date endDate) {
        return jpaFindByMany(startDate,endDate);
    }

    @Override
    public int deleteByOiid(Integer oiid) {
        return jpaDelete(oiid);
    }


}