package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.MerchantOrderInfoDetail;
import com.purchase.model.OrderInfoDetail;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>
 * 商户订单详细信息 服务类
 * </p>
 *
 * @author Musa
 * @since 2020-12-12
 */
public interface IMerchantOrderInfoDetailService extends IService<MerchantOrderInfoDetail>,Repository<MerchantOrderInfoDetail, Integer> {

    List<MerchantOrderInfoDetail> findByMoiid(Integer moiid);

}