package com.purchase.service.impl;

import com.purchase.model.RoleToMenu;
import com.purchase.dao.IRoleToMenuDao;
import com.purchase.service.IRoleToMenuService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色对应菜单信息 服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@Service
public class RoleToMenuServiceImpl extends MyBatisJapService<IRoleToMenuDao, RoleToMenu> implements IRoleToMenuService {

    @Override
    public List<RoleToMenu> findByRiid(Integer riid) {
        return jpaFindByMany(riid);
    }

    @Override
    public List<RoleToMenu> findByRiidIn(List<Integer> riids) {
        return jpaFindByMany(riids);
    }

    @Override
    public int deleteByMiid(Integer miid) {
        return jpaDelete(miid);
    }

    @Override
    public int deleteByMiidAndRiid(Integer miid, Integer riid) {
        return jpaDelete(miid,riid);
    }

    @Override
    public int deleteByRiid(Integer riid) {
        return jpaDelete(riid);
    }

    @Override
    public int deleteByMiidIn(List<Integer> miids) {
        return jpaDelete(miids);
    }
}