package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IGoodsStockInfoDetailDao;
import lombok.Data;
import java.math.BigDecimal;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 商品库存详细记录
 * </p>
 *
 * @author Musa
 * @since 2021-01-07
 */
@TableName(value = "goods_stock_info_detail")
@DaoClass(daoClass = IGoodsStockInfoDetailDao.class)
@ApiModel(value="商品库存详细记录")
@Data
public class GoodsStockInfoDetail extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商品库存详细记录id")
    private Integer id;

    @ApiModelProperty(value = "商品库存记录id")
    private Integer gsiid;

    @ApiModelProperty(value = "商品id")
    private Integer giid;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal price;

    @ApiModelProperty(value = "商品单位")
    private String unit;

    @ApiModelProperty(value = "商品数量")
    private Integer number;

    @ApiModelProperty(value = "商品操作前数量")
    private Integer beforeNumber;

    @ApiModelProperty(value = "类型：1 入库 2 出库")
    private Integer type;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "单位类型：1主 2辅")
    private Integer unitType;

    @ApiModelProperty(value = "总价")
    private BigDecimal totalPrice;

}