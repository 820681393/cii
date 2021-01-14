package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.purchase.model.MerchantDeliverInfo;
import com.purchase.model.MerchantOrderInfo;
import org.springframework.data.repository.Repository;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Musa
 * @since 2021-01-13
 */
public interface IMerchantDeliverInfoService extends IService<MerchantDeliverInfo>,Repository<MerchantDeliverInfo, Integer> {

    MerchantDeliverInfo findByMiidAndMiaidAndCreateTimeGreaterThan(Integer miid, Integer miaid,Date createTime);

    PageInfo<MerchantDeliverInfo> selectMerchantDeliverInfoPageInfo(MerchantDeliverInfo merchantDeliverInfo);
}