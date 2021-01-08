package com.purchase.service.impl;

import com.purchase.model.GoodsStockInfoDetail;
import com.purchase.dao.IGoodsStockInfoDetailDao;
import com.purchase.service.IGoodsStockInfoDetailService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品库存详细记录 服务实现类
 * </p>
 *
 * @author Musa
 * @since 2021-01-07
 */
@Service
public class GoodsStockInfoDetailServiceImpl extends MyBatisJapService<IGoodsStockInfoDetailDao, GoodsStockInfoDetail> implements IGoodsStockInfoDetailService {

}