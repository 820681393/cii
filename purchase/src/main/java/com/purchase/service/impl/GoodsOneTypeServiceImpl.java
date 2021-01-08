package com.purchase.service.impl;

import com.purchase.model.GoodsOneType;
import com.purchase.dao.IGoodsOneTypeDao;
import com.purchase.service.IGoodsOneTypeService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品一级分类信息 服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@Service
public class GoodsOneTypeServiceImpl extends MyBatisJapService<IGoodsOneTypeDao, GoodsOneType> implements IGoodsOneTypeService {

    @Override
    public List<GoodsOneType> findByState(Integer state) {
        return jpaFindByMany(state);
    }
}