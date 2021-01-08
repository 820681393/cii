package com.purchase.service.impl;

import com.github.pagehelper.PageInfo;
import com.purchase.model.GoodsStockInfo;
import com.purchase.dao.IGoodsStockInfoDao;
import com.purchase.service.IGoodsStockInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品库存记录 服务实现类
 * </p>
 *
 * @author Musa
 * @since 2021-01-07
 */
@Service
public class GoodsStockInfoServiceImpl extends MyBatisJapService<IGoodsStockInfoDao, GoodsStockInfo> implements IGoodsStockInfoService {

    @Override
    public PageInfo<GoodsStockInfo> selectGoodsStockInfoPageInfo(GoodsStockInfo goodsStockInfo) {
        return jpaPageInfo(goodsStockInfo,goodsStockInfo.getPageNum(),goodsStockInfo.getPageSize());
    }
}