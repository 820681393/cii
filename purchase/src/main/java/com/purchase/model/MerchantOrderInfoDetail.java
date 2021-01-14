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

    @ApiModelProperty(value = "商品批发价")
    private BigDecimal price;

    @ApiModelProperty(value = "商品单位")
    private String unit;

    @ApiModelProperty(value = "单位类型：1-主，2-辅")
    private Integer unitType;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "商品数量")
    private Integer number;

    @ApiModelProperty(value = "售价(批发价*利润率+额外费用)")
    private BigDecimal sellPrice;

    @ApiModelProperty(value = "商品利润分成率")
    private BigDecimal percentage;

    @ApiModelProperty(value = "商品额外费用")
    private BigDecimal extraCosts;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "状态：1正常配送 2临采配送 3次日配送 4取消配送")
    private Integer state;

    @TableField(exist = false)
    @ApiModelProperty(value = "商品库存")
    private Integer goodsStock;

    @TableField(exist = false)
    @ApiModelProperty(value = "合并的订单详细id")
    private String mergeOrderDetailId;

    @TableField(exist = false)
    private GoodsInfo goodsInfo;

}