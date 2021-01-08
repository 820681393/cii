package com.purchase.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IGoodsTowTypeDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 商品二级分类 信息
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@TableName(value = "goods_tow_type")
@DaoClass(daoClass = IGoodsTowTypeDao.class)
@ApiModel(value="商品二级分类 信息")
@Data
public class GoodsTowType extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商品二级分类id")
    private Integer id;

    @ApiModelProperty(value = "商品一级分类id")
    private Integer goid;

    @ApiModelProperty(value = "商品二级名称id")
    private String name;

    @ApiModelProperty(value = "商品二级分类英文名称")
    private String ename;

    @ApiModelProperty(value = "商品一级菜单名称")
    @TableField(exist = false)
    private String goodsOneTypeName;
}