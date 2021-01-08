package com.purchase.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IMerchantToAdminDao;
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
 * @since 2020-12-13
 */
@TableName(value = "merchant_to_admin")
@DaoClass(daoClass = IMerchantToAdminDao.class)
@ApiModel(value="")
@Data
public class MerchantToAdmin extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商户对应管理id")
    private Integer id;

    @ApiModelProperty(value = "商户id")
    private Integer miid;

    @ApiModelProperty(value = "管理员id")
    private Integer aiid;
    @ApiModelProperty(value = "商户管理员与下单元类型（1=管理员 2=下单员）")
    private Integer type;

    @ApiModelProperty(value = "管理员名称")
    @TableField(exist = false)
    private String adminName;

    @ApiModelProperty(value = "管理员电话")
    @TableField(exist = false)
    private String adminTel;

}