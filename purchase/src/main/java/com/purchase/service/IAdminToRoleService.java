package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.AdminToRole;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
public interface IAdminToRoleService extends IService<AdminToRole>,Repository<AdminToRole, Integer> {

    List<AdminToRole> findByAiid(Integer aiid);

    int deleteByAiidAndRiid(Integer aiid,Integer riid);

    int deleteByAiid(Integer aiid);
}