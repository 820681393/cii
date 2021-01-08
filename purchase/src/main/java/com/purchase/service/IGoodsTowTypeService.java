package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.GoodsTowType;
import org.springframework.data.repository.Repository;
/**
 * <p>
 * 商品二级分类 信息 服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
public interface IGoodsTowTypeService extends IService<GoodsTowType>,Repository<GoodsTowType, Integer> {

    int deleteByGoid(Integer goid);

}