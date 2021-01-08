package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IUnitInfoDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author Miracle
 * @since 2020-11-21
 */
@TableName(value = "unit_info")
@DaoClass(daoClass = IUnitInfoDao.class)
@ApiModel(value="")
@Data
public class UnitInfo extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "单位ID")
    private Integer id;

    @ApiModelProperty(value = "单位名称")
    private String name;

    @ApiModelProperty(value = "1-主单位，2-辅单位")
    private Integer type;

}