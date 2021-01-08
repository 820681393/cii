package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IMerchantUserInfoDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 商户管理员信息
 * </p>
 *
 * @author Miracle
 * @since 2020-12-27
 */
@TableName(value = "merchant_user_info")
@DaoClass(daoClass = IMerchantUserInfoDao.class)
@ApiModel(value="商户管理员信息")
@Data
public class MerchantUserInfo extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商户管理员信息id")
    private Integer id;

    @ApiModelProperty(value = "后台用户id")
    private Integer aiid;

    @ApiModelProperty(value = "管理员类型（1=管理员 2=下单员）")
    private Integer type;

    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "手机号码")
    private String tel;

    @ApiModelProperty(value = "微信号码")
    private String wxNumber;

    @ApiModelProperty(value = "小飞机号码")
    private String tgNumber;

}