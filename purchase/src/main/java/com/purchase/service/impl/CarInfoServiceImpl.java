package com.purchase.service.impl;

import com.purchase.model.CarInfo;
import com.purchase.dao.ICarInfoDao;
import com.purchase.service.ICarInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-02
 */
@Service
public class CarInfoServiceImpl extends MyBatisJapService<ICarInfoDao, CarInfo> implements ICarInfoService {

}