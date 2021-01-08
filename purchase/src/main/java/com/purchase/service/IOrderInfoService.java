package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.purchase.model.GoodsInfo;
import com.purchase.model.OrderInfo;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品下单信息 服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
public interface IOrderInfoService extends IService<OrderInfo>,Repository<OrderInfo, Integer> {

    PageInfo<OrderInfo> selectOrderInfoPageInfo(OrderInfo orderInfo);

    List<OrderInfo> findByCreateTimeGreaterThanAndCreateTimeLessThanAndState(Date startDate,Date endDate,Integer state);

    OrderInfo findByOrderNumber(String orderNumber);
}