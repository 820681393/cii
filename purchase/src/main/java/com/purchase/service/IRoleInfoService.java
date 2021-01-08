package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.RoleInfo;
import org.springframework.data.repository.Repository;
/**
 * <p>
 * 用户角色信息 服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
public interface IRoleInfoService extends IService<RoleInfo>,Repository<RoleInfo, Integer> {


}