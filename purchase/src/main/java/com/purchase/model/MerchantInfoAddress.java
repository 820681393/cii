package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IMerchantInfoAddressDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 商家收货信息
 * </p>
 *
 * @author Miracle
 * @since 2020-12-13
 */
@TableName(value = "merchant_info_address")
@DaoClass(daoClass = IMerchantInfoAddressDao.class)
@ApiModel(value="商家收货信息")
@Data
public class MerchantInfoAddress extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商家收货地址ID")
    private Integer id;

    @ApiModelProperty(value = "商家收货人")
    private String name;

    @ApiModelProperty(value = "商家收货地址")
    private String address;

    @ApiModelProperty(value = "商家收货电话")
    private String tel;

    @ApiModelProperty(value = "商家信息ID")
    private Integer miid;

    @ApiModelProperty(value = "商家门市图片")
    private String image;

    @ApiModelProperty(value = "店级")
    private String level;

}