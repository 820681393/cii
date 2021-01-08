package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.OrderInfoDetail;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 下单详细信息 服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
public interface IOrderInfoDetailService extends IService<OrderInfoDetail>,Repository<OrderInfoDetail, Integer> {

    List<OrderInfoDetail> findByOiid(Integer oiid);

    List<OrderInfoDetail> findByCreateTimeGreaterThanAndCreateTimeLessThan(Date startDate, Date endDate);

    int deleteByOiid(Integer oiid);
}