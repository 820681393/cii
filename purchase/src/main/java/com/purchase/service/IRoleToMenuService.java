package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.RoleToMenu;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>
 * 角色对应菜单信息 服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
public interface IRoleToMenuService extends IService<RoleToMenu>,Repository<RoleToMenu, Integer> {


    List<RoleToMenu> findByRiid(Integer riid);

    List<RoleToMenu> findByRiidIn(List<Integer> riids);

    int deleteByMiid(Integer miid);

    int deleteByMiidAndRiid(Integer miid,Integer riid);

    int deleteByRiid(Integer riid);

    int deleteByMiidIn(List<Integer> miids);
}