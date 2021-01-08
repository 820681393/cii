package com.purchase.model;

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

    @ApiModelProperty(value = "一级分类商品id")
    private Integer gotid;
    @ApiModelProperty(value = "二级分类商品id")
    private Integer gttid;

    @ApiModelProperty(value = "供应商id")
    private Integer siid;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal price;

    @ApiModelProperty(value = "商品单位")
    private String unit;

}