package com.purchase.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.mybaits.jpa.annotation.PageQuery;
import com.mybaits.jpa.jpaEnum.QueryOrderType;
import com.mybaits.jpa.jpaEnum.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IMerchantOrderInfoDao;
import lombok.Data;
import java.math.BigDecimal;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 商户订单信息
 * </p>
 *
 * @author Musa
 * @since 2020-12-12
 */
@TableName(value = "merchant_order_info")
@DaoClass(daoClass = IMerchantOrderInfoDao.class)
@ApiModel(value="商户订单信息")
@Data
public class MerchantOrderInfo extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商户订单ID")
    private Integer id;

    @ApiModelProperty(value = "商户订单号")
    private String orderNumber;

    @ApiModelProperty(value = "商户ID")
    private Integer miid;

    @ApiModelProperty(value = "商户配送地址ID")
    private Integer miaid;

    @ApiModelProperty(value = "订单状态：1=待处理 2=已受理 3=待结算 4=已结算")
    private Integer state;

    @ApiModelProperty(value = "订单总价格")
    private BigDecimal sumPrice;

    @ApiModelProperty(value = "订单商品总数")
    private Integer sumNumber;

    @ApiModelProperty(value = "创建时间")
    @PageQuery(queryOrderType= QueryOrderType.DESC)
    private Date createTime;

    @ApiModelProperty(value = "完成时间")
    private Date successTime;

    @ApiModelProperty(value = "收货人姓名")
    private String receiveName;

    @ApiModelProperty(value = "收货人手机号")
    private String receiveTel;

    @ApiModelProperty(value = "收货人地址")
    private String receiveAddress;

    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @ApiModelProperty(value = "下单人id")
    private Integer miAdminId;

    @ApiModelProperty(value = "配送单id")
    private Integer mdiid;

    @ApiModelProperty(value = "创建时间")
    @TableField(exist = false)
    @PageQuery(queryType= QueryType.GE,column = "createTime")
    private Date createTimeStart;

    @ApiModelProperty(value = "创建时间")
    @TableField(exist = false)
    @PageQuery(queryType= QueryType.LE,column = "createTime")
    private Date createTimeEnd;

}