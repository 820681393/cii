package com.purchase.service.impl;

import com.purchase.model.UnitInfo;
import com.purchase.dao.IUnitInfoDao;
import com.purchase.service.IUnitInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-21
 */
@Service
public class UnitInfoServiceImpl extends MyBatisJapService<IUnitInfoDao, UnitInfo> implements IUnitInfoService {

}