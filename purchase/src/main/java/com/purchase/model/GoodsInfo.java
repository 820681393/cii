package com.purchase.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.mybaits.jpa.annotation.PageQuery;
import com.mybaits.jpa.jpaEnum.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IGoodsInfoDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@TableName(value = "goods_info")
@DaoClass(daoClass = IGoodsInfoDao.class)
@ApiModel(value="商品信息")
@Data
public class GoodsInfo extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商品信息id")
    private Integer id;

    @ApiModelProperty(value = "商品编号")
    private String code;

    @ApiModelProperty(value = "中文名称")
    private String chName;

    @ApiModelProperty(value = "英文名称")
    private String enName;

    @ApiModelProperty(value = "图片地址")
    private String imgUrl;

    @ApiModelProperty(value = "一级分类id")
    private Integer goid;

    @ApiModelProperty(value = "二级分类id")
    private Integer gtid;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "采购成本价(主)")
    private BigDecimal price;

    @ApiModelProperty(value = "采购成本价(辅)")
    private BigDecimal priceSe;

    @ApiModelProperty(value = "商品应用单位类型：1-主单位，2-辅单位")
    private Integer unitType;

    @ApiModelProperty(value = "供应商ID")
    private Integer siid;

    @ApiModelProperty(value = "主单位ID")
    private Integer uiidPr;

    @ApiModelProperty(value = "辅单位ID")
    private Integer uiidPe;

    @ApiModelProperty(value = "主单位值")
    private BigDecimal unitPrVal;

    @ApiModelProperty(value = "辅单位值")
    private BigDecimal unitPeVal;

    @ApiModelProperty(value = "商品利润分成率")
    private BigDecimal percentage;

    @ApiModelProperty(value = "商品额外费用")
    private BigDecimal extraCosts;

    @ApiModelProperty(value = "批发状态：0-下架，1-上架")
    private Integer state;

    @ApiModelProperty(value = "批发价格(主)")
    private BigDecimal tradePrice;

    @ApiModelProperty(value = "批发价格(辅)")
    private BigDecimal tradePriceSe;

    @ApiModelProperty(value = "零售价格(主)")
    private BigDecimal retailPrice;

    @ApiModelProperty(value = "零售价格(辅)")
    private BigDecimal retailPriceSe;

    @ApiModelProperty(value = "零售状态：0-下架，1-上架")
    private Integer retailState;

    @ApiModelProperty(value = "库存(主)")
    private Integer stock;

    @ApiModelProperty(value = "库存(辅)")
    private Integer stockSe;

    @ApiModelProperty(value = "安全库存(主)")
    private Integer safeStock;

    @ApiModelProperty(value = "安全库存(辅)")
    private Integer safeStockSe;

    @ApiModelProperty(value = "一级分类名称")
    @TableField(exist = false)
    private String goName;

    @ApiModelProperty(value = "二级分类名称")
    @TableField(exist = false)
    private String gtName;

    @ApiModelProperty(value = "供应商名称")
    @TableField(exist = false)
    private String supplierName;

    @ApiModelProperty(value = "供应商地址")
    @TableField(exist = false)
    private String supplierAddress;

    @ApiModelProperty(value = "主单位名称")
    @TableField(exist = false)
    private String unitPrName;

    @ApiModelProperty(value = "辅单位名称")
    @TableField(exist = false)
    private String unitPeName;

    @ApiModelProperty(value = "创建时间")
    @TableField(exist = false)
    @PageQuery(queryType= QueryType.GE,column = "createTime")
    private Date createTimeStart;

    @ApiModelProperty(value = "创建时间")
    @TableField(exist = false)
    @PageQuery(queryType= QueryType.LE,column = "createTime")
    private Date createTimeEnd;
}