package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.purchase.model.AdminInfo;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>
 * 后台用户信息 服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
public interface IAdminInfoService extends IService<AdminInfo>,Repository<AdminInfo, Integer> {


    AdminInfo findByUserNameAndPassWord(String userName,String passWord);

    PageInfo<AdminInfo> selectAdminInfoPageInfo(AdminInfo adminInfo);

    List<AdminInfo> findByType(Integer type);
}