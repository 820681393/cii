package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.GoodsStockInfoDetail;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>
 * 商品库存详细记录 服务类
 * </p>
 *
 * @author Musa
 * @since 2021-01-07
 */
public interface IGoodsStockInfoDetailService extends IService<GoodsStockInfoDetail>,Repository<GoodsStockInfoDetail, Integer> {
    List<GoodsStockInfoDetail> findByGsiid(Integer gsiid);
}