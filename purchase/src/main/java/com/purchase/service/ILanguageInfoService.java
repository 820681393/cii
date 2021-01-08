package com.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.model.LanguageInfo;
import org.springframework.data.repository.Repository;
/**
 * <p>
 * 语言信息 服务类
 * </p>
 *
 * @author Miracle
 * @since 2020-12-01
 */
public interface ILanguageInfoService extends IService<LanguageInfo>,Repository<LanguageInfo, Integer> {


}