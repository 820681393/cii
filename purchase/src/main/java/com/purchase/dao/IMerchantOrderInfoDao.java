package com.purchase.dao;

import com.purchase.model.GoodsInfo;
import com.purchase.model.MerchantOrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.purchase.model.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商户订单信息 Mapper 接口
 * </p>
 *
 * @author Musa
 * @since 2020-12-12
 */
public interface IMerchantOrderInfoDao extends BaseMapper<MerchantOrderInfo> {
    Integer sumTodayOrderNumber();

    List<MerchantOrderInfo> orderInfoListByMonthAndMiid(@Param("miid")Integer miid);
}