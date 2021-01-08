package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.MerchantInfo;
import org.springframework.data.repository.Repository;
/**
 * <p>
 * 商家信息 服务类
 * </p>
 *
 * @author Musa
 * @since 2020-12-12
 */
public interface IMerchantInfoService extends IService<MerchantInfo>,Repository<MerchantInfo, Integer> {


    MerchantInfo findByAiid(Integer aiid);


}