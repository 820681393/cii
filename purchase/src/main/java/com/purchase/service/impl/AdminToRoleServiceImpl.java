package com.purchase.service.impl;

import com.purchase.model.AdminToRole;
import com.purchase.dao.IAdminToRoleDao;
import com.purchase.service.IAdminToRoleService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@Service
public class AdminToRoleServiceImpl extends MyBatisJapService<IAdminToRoleDao, AdminToRole> implements IAdminToRoleService {

    @Override
    public List<AdminToRole> findByAiid(Integer aiid) {
        return jpaFindByMany(aiid);
    }

    @Override
    public int deleteByAiidAndRiid(Integer aiid, Integer riid) {
        return jpaDelete(aiid,riid);
    }

    @Override
    public int deleteByAiid(Integer aiid) {
        return jpaDelete(aiid);
    }
}