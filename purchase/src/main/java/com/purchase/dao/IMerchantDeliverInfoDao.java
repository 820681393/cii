package com.purchase.dao;

import com.purchase.model.MerchantDeliverInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Musa
 * @since 2021-01-13
 */
public interface IMerchantDeliverInfoDao extends BaseMapper<MerchantDeliverInfo> {
    Integer sumTodayOrderNumber();
}