package com.purchase.service.impl;

import com.purchase.model.SupplierInfo;
import com.purchase.dao.ISupplierInfoDao;
import com.purchase.service.ISupplierInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 供应商信息 服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@Service
public class SupplierInfoServiceImpl extends MyBatisJapService<ISupplierInfoDao, SupplierInfo> implements ISupplierInfoService {

}