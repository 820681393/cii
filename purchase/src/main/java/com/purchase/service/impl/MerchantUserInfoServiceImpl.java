package com.purchase.service.impl;

import com.purchase.model.MerchantUserInfo;
import com.purchase.dao.IMerchantUserInfoDao;
import com.purchase.service.IMerchantUserInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商户管理员信息 服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-12-27
 */
@Service
public class MerchantUserInfoServiceImpl extends MyBatisJapService<IMerchantUserInfoDao, MerchantUserInfo> implements IMerchantUserInfoService {

    @Override
    public List<MerchantUserInfo> findByAiid(Integer aiid) {
        return jpaFindByMany(aiid);
    }
}