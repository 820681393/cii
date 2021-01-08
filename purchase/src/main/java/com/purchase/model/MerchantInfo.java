package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IMerchantInfoDao;
import lombok.Data;
import java.math.BigDecimal;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 商家信息
 * </p>
 *
 * @author Musa
 * @since 2020-12-12
 */
@TableName(value = "merchant_info")
@DaoClass(daoClass = IMerchantInfoDao.class)
@ApiModel(value="商家信息")
@Data
public class MerchantInfo extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商家信息ID")
    private Integer id;

    @ApiModelProperty(value = "后台用户ID")
    private Integer aiid;

    @ApiModelProperty(value = "商家电话")
    private String phone;

    @ApiModelProperty(value = "商家地址")
    private String address;

    @ApiModelProperty(value = "商家联系人")
    private String contactName;
    @ApiModelProperty(value = "结算方式（1=现金结算 2=按周结算）")
    private int settlementMethod;
    @ApiModelProperty(value = "回扣方式（1=无回扣 2=数值回扣 3=百分比回扣）")
    private int rebateMethod;
    @ApiModelProperty(value = "数值回扣")
    private BigDecimal rebateNumber;
    @ApiModelProperty(value = "百分比回扣")
    private BigDecimal rebatePercentage;
    @ApiModelProperty(value = "商户属性")
    private String merchantAttribute;

}