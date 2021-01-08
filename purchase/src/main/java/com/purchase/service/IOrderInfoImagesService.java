package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.OrderInfoImages;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-24
 */
public interface IOrderInfoImagesService extends IService<OrderInfoImages>,Repository<OrderInfoImages, Integer> {

    List<OrderInfoImages> findByOiid(Integer oiid);

    List<OrderInfoImages> findByOiidAndType(Integer oiid,Integer type);
}