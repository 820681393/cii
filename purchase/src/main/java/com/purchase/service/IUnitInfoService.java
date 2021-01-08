package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.UnitInfo;
import org.springframework.data.repository.Repository;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-21
 */
public interface IUnitInfoService extends IService<UnitInfo>,Repository<UnitInfo, Integer> {


}