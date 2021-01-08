package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.GoodsInfo;
import com.purchase.model.GoodsOneType;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>
 * 商品一级分类信息 服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
public interface IGoodsOneTypeService extends IService<GoodsOneType>,Repository<GoodsOneType, Integer> {

    List<GoodsOneType> findByState(Integer state);

}