package com.purchase.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.mybaits.jpa.annotation.PageQuery;
import com.mybaits.jpa.jpaEnum.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IMerchantDeliverInfoDao;
import lombok.Data;
import java.math.BigDecimal;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Musa
 * @since 2021-01-13
 */
@TableName(value = "merchant_deliver_info")
@DaoClass(daoClass = IMerchantDeliverInfoDao.class)
@ApiModel(value="")
@Data
public class MerchantDeliverInfo extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "配送订单ID")
    private Integer id;

    @ApiModelProperty(value = "配送订单号")
    private String orderNumber;

    @ApiModelProperty(value = "商户ID")
    private Integer miid;

    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @ApiModelProperty(value = "商户配送地址id")
    private Integer miaid;

    @ApiModelProperty(value = "订单状态：1未处理 2待配送 3未结算 4已结算")
    private Integer state;

    @ApiModelProperty(value = "订单总价格")
    private BigDecimal sumPrice;

    @ApiModelProperty(value = "配送差额")
    private BigDecimal deliverPriceDiff;

    @ApiModelProperty(value = "实际配送额")
    private BigDecimal realDeliverPrice;

    @ApiModelProperty(value = "结算方式：1日结 2周结 3月结")
    private Integer settleType;

    @ApiModelProperty(value = "回执图片")
    private String image;

    @ApiModelProperty(value = "打印配送单管理员id")
    private Integer printAiid;

    @ApiModelProperty(value = "结算管理员id")
    private Integer settleAiid;

    @ApiModelProperty(value = "结算金额")
    private BigDecimal settleBalance;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "结算时间")
    private Date settleTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(exist = false)
    @PageQuery(queryType= QueryType.GE,column = "createTime")
    private Date createTimeStart;

    @ApiModelProperty(value = "创建时间")
    @TableField(exist = false)
    @PageQuery(queryType= QueryType.LE,column = "createTime")
    private Date createTimeEnd;
}