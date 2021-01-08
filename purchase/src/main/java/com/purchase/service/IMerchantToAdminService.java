package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.MerchantToAdmin;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-12-13
 */
public interface IMerchantToAdminService extends IService<MerchantToAdmin>,Repository<MerchantToAdmin, Integer> {


    List<MerchantToAdmin> findByMiid(Integer miid);

    int deleteByAiidAndMiid(Integer aiid,Integer miid);
}