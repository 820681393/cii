package com.purchase.service.impl;

import com.github.pagehelper.PageInfo;
import com.purchase.model.AdminInfo;
import com.purchase.dao.IAdminInfoDao;
import com.purchase.service.IAdminInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台用户信息 服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@Service
public class AdminInfoServiceImpl extends MyBatisJapService<IAdminInfoDao, AdminInfo> implements IAdminInfoService {

    @Override
    public AdminInfo findByUserNameAndPassWord(String userName, String passWord) {
        return jpaFindByOne(userName,passWord);
    }

    @Override
    public PageInfo<AdminInfo> selectAdminInfoPageInfo(AdminInfo adminInfo) {
        return jpaPageInfo(adminInfo,adminInfo.getPageNum(),adminInfo.getPageSize());
    }

    @Override
    public List<AdminInfo> findByType(Integer type) {
        return jpaFindByMany(type);
    }
}