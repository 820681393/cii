package com.purchase.service.impl;

import com.purchase.model.MerchantToAdmin;
import com.purchase.dao.IMerchantToAdminDao;
import com.purchase.service.IMerchantToAdminService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-12-13
 */
@Service
public class MerchantToAdminServiceImpl extends MyBatisJapService<IMerchantToAdminDao, MerchantToAdmin> implements IMerchantToAdminService {

    @Override
    public List<MerchantToAdmin> findByMiid(Integer miid) {
        return jpaFindByMany(miid);
    }

    @Override
    public int deleteByAiidAndMiid(Integer aiid, Integer miid) {
        return jpaDelete(aiid,miid);
    }
}