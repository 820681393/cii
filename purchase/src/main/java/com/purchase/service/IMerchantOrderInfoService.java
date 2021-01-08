package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.purchase.model.MerchantOrderInfo;
import com.purchase.model.OrderInfo;
import org.springframework.data.repository.Repository;
/**
 * <p>
 * 商户订单信息 服务类
 * </p>
 *
 * @author Musa
 * @since 2020-12-12
 */
public interface IMerchantOrderInfoService extends IService<MerchantOrderInfo>,Repository<MerchantOrderInfo, Integer> {

    PageInfo<MerchantOrderInfo> selectMerchantOrderInfoPageInfo(MerchantOrderInfo merchantOrderInfo);

}