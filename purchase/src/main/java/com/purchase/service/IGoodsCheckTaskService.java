package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.purchase.model.GoodsCheckTask;
import com.purchase.model.GoodsStockInfo;
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
public interface IGoodsCheckTaskService extends IService<GoodsCheckTask>,Repository<GoodsCheckTask, Integer> {

    PageInfo<GoodsCheckTask> selectGoodsCheckTaskPageInfo(GoodsCheckTask goodsCheckTask);

    List<GoodsCheckTask> findByStateAndReceiveAiid(Integer state,Integer receiveAiid);
}