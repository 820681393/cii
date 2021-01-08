package com.purchase.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IMerchantOrderInfoDetailDao;
import lombok.Data;
import java.math.BigDecimal;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 商户订单详细信息
 * </p>
 *
 * @author Musa
 * @since 2020-12-12
 */
@TableName(value = "merchant_order_info_detail")
@DaoClass(daoClass = IMerchantOrderInfoDetailDao.class)
@ApiModel(value="商户订单详细信息")
@Data
public class MerchantOrderInfoDetail extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商户订单详细信息id")
    private Integer id;

    @ApiModelProperty(value = "商户订单信息id")
    private Integer moiid;

    @ApiModelProperty(value = "商品信息id")
    private Integer giid;

    @ApiModelProperty(value = "供应商ID")
    private Integer siid;

    @ApiModelProperty(value = "参考价格(主)")
    private BigDecimal price;

    @ApiModelProperty(value = "参考价格(辅)")
    private BigDecimal priceSe;

    @ApiModelProperty(value = "单位(主)")
    private String unit;

    @ApiModelProperty(value = "单位(辅)")
    private String unitSe;

    @ApiModelProperty(value = "单位类型：1-主，2-辅")
    private Integer unitType;

    @ApiModelProperty(value = "实采总价")
    private BigDecimal realTotalPrice;

    @ApiModelProperty(value = "实采单位")
    private String realUnit;

    @ApiModelProperty(value = "实采数量")
    private BigDecimal realNumber;

    @ApiModelProperty(value = "实采单价")
    private BigDecimal realPrice;

    @ApiModelProperty(value = "状态：Y-完成，N-未完成")
    private String state;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "总价")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "数量")
    private BigDecimal number;

    @ApiModelProperty(value = "商品利润分成率")
    private BigDecimal percentage;

    @ApiModelProperty(value = "商品额外费用")
    private BigDecimal extraCosts;

    @ApiModelProperty(value = "零售价")
    private BigDecimal retailPrice;

    @ApiModelProperty(value = "供应商名称")
    @TableField(exist = false)
    private String supplierName;

    @ApiModelProperty(value = "供应商地址")
    @TableField(exist = false)
    private String supplierAddress;

    @TableField(exist = false)
    private GoodsInfo goodsInfo;

}