package com.purchase.service.impl;

import com.purchase.model.MerchantOrderInfoDetail;
import com.purchase.dao.IMerchantOrderInfoDetailDao;
import com.purchase.service.IMerchantOrderInfoDetailService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商户订单详细信息 服务实现类
 * </p>
 *
 * @author Musa
 * @since 2020-12-12
 */
@Service
public class MerchantOrderInfoDetailServiceImpl extends MyBatisJapService<IMerchantOrderInfoDetailDao, MerchantOrderInfoDetail> implements IMerchantOrderInfoDetailService {

    @Override
    public List<MerchantOrderInfoDetail> findByMoiid(Integer moiid) {
        return jpaFindByMany(moiid);
    }
}