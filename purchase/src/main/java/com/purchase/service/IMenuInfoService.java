package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.MenuInfo;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>
 * 菜单信息 服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
public interface IMenuInfoService extends IService<MenuInfo>,Repository<MenuInfo, Integer> {

    int deleteByMiid(Integer miid);

    int deleteByMiidIn(List<Integer> miids);
}