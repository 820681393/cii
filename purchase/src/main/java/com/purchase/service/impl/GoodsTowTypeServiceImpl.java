package com.purchase.service.impl;

import com.purchase.model.GoodsTowType;
import com.purchase.dao.IGoodsTowTypeDao;
import com.purchase.service.IGoodsTowTypeService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品二级分类 信息 服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@Service
public class GoodsTowTypeServiceImpl extends MyBatisJapService<IGoodsTowTypeDao, GoodsTowType> implements IGoodsTowTypeService {

    @Override
    public int deleteByGoid(Integer goid) {
        return jpaDelete(goid);
    }
}