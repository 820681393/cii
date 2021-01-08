package com.purchase.service.impl;

import com.purchase.model.MenuInfo;
import com.purchase.dao.IMenuInfoDao;
import com.purchase.service.IMenuInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单信息 服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@Service
public class MenuInfoServiceImpl extends MyBatisJapService<IMenuInfoDao, MenuInfo> implements IMenuInfoService {

    @Override
    public int deleteByMiid(Integer miid) {
        return jpaDelete(miid);
    }

    @Override
    public int deleteByMiidIn(List<Integer> miids) {
        return jpaDelete(miids);
    }
}