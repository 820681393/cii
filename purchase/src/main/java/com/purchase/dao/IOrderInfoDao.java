package com.purchase.dao;

import com.purchase.model.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品下单信息 Mapper 接口
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@Repository
public interface IOrderInfoDao extends BaseMapper<OrderInfo> {
    Integer sumTodayOrderNumber();

    List<OrderInfo> orderInfoListByMonth();
}