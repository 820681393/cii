package com.purchase.service.impl;

import com.purchase.model.RoleInfo;
import com.purchase.dao.IRoleInfoDao;
import com.purchase.service.IRoleInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色信息 服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@Service
public class RoleInfoServiceImpl extends MyBatisJapService<IRoleInfoDao, RoleInfo> implements IRoleInfoService {

}