package com.purchase.service.impl;

import com.purchase.model.MerchantInfo;
import com.purchase.dao.IMerchantInfoDao;
import com.purchase.service.IMerchantInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商家信息 服务实现类
 * </p>
 *
 * @author Musa
 * @since 2020-12-12
 */
@Service
public class MerchantInfoServiceImpl extends MyBatisJapService<IMerchantInfoDao, MerchantInfo> implements IMerchantInfoService {

    @Override
    public MerchantInfo findByAiid(Integer aiid) {
        return jpaFindByOne(aiid);
    }
}