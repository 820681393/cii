package com.purchase.service.impl;

import com.github.pagehelper.PageInfo;
import com.purchase.model.MerchantDeliverInfo;
import com.purchase.dao.IMerchantDeliverInfoDao;
import com.purchase.service.IMerchantDeliverInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Musa
 * @since 2021-01-13
 */
@Service
public class MerchantDeliverInfoServiceImpl extends MyBatisJapService<IMerchantDeliverInfoDao, MerchantDeliverInfo> implements IMerchantDeliverInfoService {

    @Override
    public MerchantDeliverInfo findByMiidAndMiaidAndCreateTimeGreaterThan(Integer miid, Integer miaid, Date createTime) {
        return jpaFindByOne(miid,miaid,createTime);
    }

    @Override
    public PageInfo<MerchantDeliverInfo> selectMerchantDeliverInfoPageInfo(MerchantDeliverInfo merchantDeliverInfo) {
        return jpaPageInfo(merchantDeliverInfo,merchantDeliverInfo.getPageNum(),merchantDeliverInfo.getPageSize());
    }
}