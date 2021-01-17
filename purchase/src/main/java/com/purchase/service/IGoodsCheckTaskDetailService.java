package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.GoodsCheckTaskDetail;
import com.purchase.model.GoodsStockInfoDetail;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Musa
 * @since 2021-01-17
 */
public interface IGoodsCheckTaskDetailService extends IService<GoodsCheckTaskDetail>,Repository<GoodsCheckTaskDetail, Integer> {

    List<GoodsCheckTaskDetail> findByGctid(Integer gctid);
}