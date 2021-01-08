package com.purchase.service.impl;

import com.purchase.model.OrderInfoImages;
import com.purchase.dao.IGoodsInfoImagesDao;
import com.purchase.service.IOrderInfoImagesService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-24
 */
@Service
public class OrderInfoImagesServiceImpl extends MyBatisJapService<IGoodsInfoImagesDao, OrderInfoImages> implements IOrderInfoImagesService {
    @Override
    public List<OrderInfoImages> findByOiid(Integer oiid) {
        return jpaFindByMany(oiid);
    }

    @Override
    public List<OrderInfoImages> findByOiidAndType(Integer oiid, Integer type) {
        return jpaFindByMany(oiid,type);
    }

}