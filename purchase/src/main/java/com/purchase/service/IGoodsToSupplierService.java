package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.GoodsToSupplier;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>
 * 商品对应供应商关系信息 服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
public interface IGoodsToSupplierService extends IService<GoodsToSupplier>,Repository<GoodsToSupplier, Integer> {


    List<GoodsToSupplier> findBySiid(Integer siid);

    int deleteByGotidAndGttidAndSiid(Integer gotid,Integer gttid,Integer siid);

    int deleteByGotidAndSiid(Integer gotid,Integer siid);

}