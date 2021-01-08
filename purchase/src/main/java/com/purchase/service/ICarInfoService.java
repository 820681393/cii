package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.CarInfo;
import org.springframework.data.repository.Repository;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-02
 */
public interface ICarInfoService extends IService<CarInfo>,Repository<CarInfo, Integer> {


}