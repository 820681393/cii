package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.purchase.model.GoodsInfo;
import com.purchase.model.GoodsStockInfo;
import org.springframework.data.repository.Repository;
/**
 * <p>
 * 商品库存记录 服务类
 * </p>
 *
 * @author Musa
 * @since 2021-01-07
 */
public interface IGoodsStockInfoService extends IService<GoodsStockInfo>,Repository<GoodsStockInfo, Integer> {

    PageInfo<GoodsStockInfo> selectGoodsStockInfoPageInfo(GoodsStockInfo goodsStockInfo);
}