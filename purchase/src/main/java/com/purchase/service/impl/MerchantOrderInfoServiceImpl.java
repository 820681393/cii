package com.purchase.service.impl;

import com.github.pagehelper.PageInfo;
import com.purchase.model.MerchantOrderInfo;
import com.purchase.dao.IMerchantOrderInfoDao;
import com.purchase.service.IMerchantOrderInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商户订单信息 服务实现类
 * </p>
 *
 * @author Musa
 * @since 2020-12-12
 */
@Service
public class MerchantOrderInfoServiceImpl extends MyBatisJapService<IMerchantOrderInfoDao, MerchantOrderInfo> implements IMerchantOrderInfoService {

    @Override
    public PageInfo<MerchantOrderInfo> selectMerchantOrderInfoPageInfo(MerchantOrderInfo merchantOrderInfo) {
        return jpaPageInfo(merchantOrderInfo,merchantOrderInfo.getPageNum(),merchantOrderInfo.getPageSize());
    }

    @Override
    public List<MerchantOrderInfo> findByMdiid(Integer mdiid) {
        return jpaFindByMany(mdiid);
    }


}