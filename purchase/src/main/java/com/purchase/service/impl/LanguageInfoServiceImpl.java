package com.purchase.service.impl;

import com.purchase.model.LanguageInfo;
import com.purchase.dao.ILanguageInfoDao;
import com.purchase.service.ILanguageInfoService;
import com.mybaits.jpa.service.MyBatisJapService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 语言信息 服务实现类
 * </p>
 *
 * @author Miracle
 * @since 2020-12-01
 */
@Service
public class LanguageInfoServiceImpl extends MyBatisJapService<ILanguageInfoDao, LanguageInfo> implements ILanguageInfoService {

}