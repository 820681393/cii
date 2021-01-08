package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IGoodsOneTypeDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 商品一级分类信息
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@TableName(value = "goods_one_type")
@DaoClass(daoClass = IGoodsOneTypeDao.class)
@ApiModel(value="商品一级分类信息")
@Data
public class GoodsOneType extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商品一级分类id")
    private Integer id;

    @ApiModelProperty(value = "商品一级分类名称")
    private String name;

    @ApiModelProperty(value = "商品一级分类英文名称")
    private String ename;

    @ApiModelProperty(value = "状态：0-下架，1-上架")
    private Integer state;

}