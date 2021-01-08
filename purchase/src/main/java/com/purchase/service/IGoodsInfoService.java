package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.purchase.model.GoodsInfo;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
public interface IGoodsInfoService extends IService<GoodsInfo>,Repository<GoodsInfo, Integer> {

    PageInfo<GoodsInfo> selectGoodsInfoPageInfo(GoodsInfo goodsInfo);

    List<GoodsInfo> findByState(Integer state);
}