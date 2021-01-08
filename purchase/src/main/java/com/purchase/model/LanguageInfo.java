package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.ILanguageInfoDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 语言信息
 * </p>
 *
 * @author Miracle
 * @since 2020-12-01
 */
@TableName(value = "language_info")
@DaoClass(daoClass = ILanguageInfoDao.class)
@ApiModel(value="语言信息")
@Data
public class LanguageInfo extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "语言信息id")
    private Integer id;

    @ApiModelProperty(value = "中文")
    private String cn;

    @ApiModelProperty(value = "英文")
    private String en;

}