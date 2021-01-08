package com.purchase.service.impl;

import com.purchase.model.MerchantInfoAddress;
import com.purchase.dao.IMerchantInfoAddressDao;
import com.purchase.service.IMerchantInfoAddressService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商家收货信息 服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-12-13
 */
@Service
public class MerchantInfoAddressServiceImpl extends MyBatisJapService<IMerchantInfoAddressDao, MerchantInfoAddress> implements IMerchantInfoAddressService {

    @Override
    public List<MerchantInfoAddress> findByMiid(Integer miid) {
        return jpaFindByMany(miid);
    }
}