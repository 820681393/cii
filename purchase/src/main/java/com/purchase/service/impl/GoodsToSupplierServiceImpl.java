package com.purchase.service.impl;

import com.purchase.model.GoodsToSupplier;
import com.purchase.dao.IGoodsToSupplierDao;
import com.purchase.service.IGoodsToSupplierService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品对应供应商关系信息 服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@Service
public class GoodsToSupplierServiceImpl extends MyBatisJapService<IGoodsToSupplierDao, GoodsToSupplier> implements IGoodsToSupplierService {

    @Override
    public List<GoodsToSupplier> findBySiid(Integer siid) {
        return jpaFindByMany(siid);
    }

    @Override
    public int deleteByGotidAndGttidAndSiid(Integer gotid, Integer gttid, Integer siid) {
        return jpaDelete(gotid,gttid,siid);
    }

    @Override
    public int deleteByGotidAndSiid(Integer gotid, Integer siid) {
        return jpaDelete(gotid,siid);
    }
}