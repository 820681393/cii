package com.purchase.service.impl;

import com.github.pagehelper.PageInfo;
import com.purchase.model.GoodsInfo;
import com.purchase.dao.IGoodsInfoDao;
import com.purchase.service.IGoodsInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@Service
public class GoodsInfoServiceImpl extends MyBatisJapService<IGoodsInfoDao, GoodsInfo> implements IGoodsInfoService {

    @Override
    public PageInfo<GoodsInfo> selectGoodsInfoPageInfo(GoodsInfo goodsInfo) {
        return jpaPageInfo(goodsInfo,goodsInfo.getPageNum(),goodsInfo.getPageSize());
    }

    @Override
    public List<GoodsInfo> findByState(Integer state) {
        return jpaFindByMany(state);
    }
}