package com.purchase.dao;

import com.purchase.model.GoodsInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.purchase.vo.SumGoodsInfoVO;
import com.purchase.vo.GoodsPriceTrendVO;
import com.purchase.vo.SupplierPriceTrendVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@Repository
public interface IGoodsInfoDao extends BaseMapper<GoodsInfo> {

    List<SumGoodsInfoVO> sumGoodsInfo(@Param("goodsInfo")GoodsInfo goodsInfo,@Param("goodsType")Integer goodsType,@Param("isSumYm")Boolean isSumYm);

    List<GoodsPriceTrendVO> goodsPriceTrend(@Param("goodsInfo")GoodsInfo goodsInfo, @Param("goodsIds")Integer[] goodsIds);

    List<SupplierPriceTrendVO> supplierPriceTrend(@Param("goodsInfo")GoodsInfo goodsInfo);
}