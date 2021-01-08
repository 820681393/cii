package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.MerchantUserInfo;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>
 * 商户管理员信息 服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-12-27
 */
public interface IMerchantUserInfoService extends IService<MerchantUserInfo>,Repository<MerchantUserInfo, Integer> {

    List<MerchantUserInfo> findByAiid(Integer aiid);
}