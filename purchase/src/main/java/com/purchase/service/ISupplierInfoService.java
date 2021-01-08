package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.SupplierInfo;
import org.springframework.data.repository.Repository;
/**
 * <p>
 * 供应商信息 服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
public interface ISupplierInfoService extends IService<SupplierInfo>,Repository<SupplierInfo, Integer> {


}