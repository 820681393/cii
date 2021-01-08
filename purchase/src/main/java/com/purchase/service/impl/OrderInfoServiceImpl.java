package com.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.purchase.model.OrderInfo;
import com.purchase.dao.IOrderInfoDao;
import com.purchase.service.IOrderInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品下单信息 服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@Service
public class OrderInfoServiceImpl extends MyBatisJapService<IOrderInfoDao, OrderInfo> implements IOrderInfoService {

    @Autowired
    IOrderInfoDao iOrderInfoDao;
    @Override
    public PageInfo<OrderInfo> selectOrderInfoPageInfo(OrderInfo orderInfo) {
        return jpaPageInfo(orderInfo,orderInfo.getPageNum(),orderInfo.getPageSize());
    }

    @Override
    public List<OrderInfo> findByCreateTimeGreaterThanAndCreateTimeLessThanAndState(Date startDate, Date endDate,Integer state) {
        return jpaFindByMany(startDate,endDate,state);
    }

    @Override
    public OrderInfo findByOrderNumber(String orderNumber) {
        return jpaFindByOne(orderNumber);
    }
}