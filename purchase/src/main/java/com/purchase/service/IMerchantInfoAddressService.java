package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.MerchantInfoAddress;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>
 * 商家收货信息 服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-12-13
 */
public interface IMerchantInfoAddressService extends IService<MerchantInfoAddress>,Repository<MerchantInfoAddress, Integer> {

    List<MerchantInfoAddress> findByMiid(Integer miid);
}