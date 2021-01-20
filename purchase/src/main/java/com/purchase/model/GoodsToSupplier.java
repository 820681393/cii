package com.purchase.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IGoodsToSupplierDao;
import lombok.Data;
import java.math.BigDecimal;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 商品对应供应商关系信息
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@TableName(value = "goods_to_supplier")
@DaoClass(daoClass = IGoodsToSupplierDao.class)
@ApiModel(value="商品对应供应商关系信息")
@Data
public class GoodsToSupplier extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商品对应供应商id")
    private Integer id;

    @ApiModelProperty(value = "供应商id")
    private Integer siid;

    @ApiModelProperty(value = "商品单位")
    private String unit;

    @ApiModelProperty(value = "币种：CNY USD PHP")
    private String currencyType;

    @ApiModelProperty(value = "商品产地价格")
    private BigDecimal originPrice;

    @ApiModelProperty(value = "产地物流费")
    private BigDecimal originLogFee;

    @ApiModelProperty(value = "国际物流费")
    private BigDecimal intLogFee;

    @ApiModelProperty(value = "报关费")
    private BigDecimal awbFee;

    @ApiModelProperty(value = "本地物流费")
    private BigDecimal localLogFee;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品id")
    private Integer giid;

    @ApiModelProperty(value = "单位类型：1-主，2-辅")
    private Integer unitType;

    @ApiModelProperty(value = "供应价格")
    @TableField(exist = false)
    private BigDecimal supplierPrice;

    @ApiModelProperty(value = "关联商品信息")
    @TableField(exist = false)
    private GoodsInfo goodsInfo;

    @ApiModelProperty(value = "差额")
    @TableField(exist = false)
    private BigDecimal diifPrice;

}