package com.purchase.dao;

import com.purchase.model.GoodsCheckTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Musa
 * @since 2021-01-17
 */
public interface IGoodsCheckTaskDao extends BaseMapper<GoodsCheckTask> {
    Integer sumTodayGoodsCheckTaskNumber();
}