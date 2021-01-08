package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IGoodsStockInfoDao;
import lombok.Data;
import java.math.BigDecimal;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 商品库存记录
 * </p>
 *
 * @author Musa
 * @since 2021-01-07
 */
@TableName(value = "goods_stock_info")
@DaoClass(daoClass = IGoodsStockInfoDao.class)
@ApiModel(value="商品库存记录")
@Data
public class GoodsStockInfo extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商品库存记录id")
    private Integer id;

    @ApiModelProperty(value = "单号")
    private String orderNumber;

    @ApiModelProperty(value = "数量")
    private Integer stockNumber;

    @ApiModelProperty(value = "类型：1手动入库，2手动出库，3销售出库")
    private Integer type;

    @ApiModelProperty(value = "时间")
    private Date createTime;

    @ApiModelProperty(value = "总价")
    private BigDecimal totalPrice;

}