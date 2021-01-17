package com.purchase.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IGoodsCheckTaskDetailDao;
import lombok.Data;
import java.math.BigDecimal;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author Musa
 * @since 2021-01-17
 */
@TableName(value = "goods_check_task_detail")
@DaoClass(daoClass = IGoodsCheckTaskDetailDao.class)
@ApiModel(value="")
@Data
public class GoodsCheckTaskDetail extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商品盘点任务详细id")
    private Integer id;

    @ApiModelProperty(value = "商品盘点任务id")
    private Integer gctid;

    @ApiModelProperty(value = "商品id")
    private Integer giid;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品单位(主)")
    private String unit;

    @ApiModelProperty(value = "商品单位(辅)")
    private String unitSe;

    @ApiModelProperty(value = "盘点数量(主)")
    private Integer number;

    @ApiModelProperty(value = "盘点数量(辅)")
    private Integer numberSe;

    @ApiModelProperty(value = "商品单价(主)")
    private BigDecimal price;

    @ApiModelProperty(value = "商品单价(辅)")
    private BigDecimal priceSe;

    @ApiModelProperty(value = "状态：1正常 2未修正 3已修正")
    private Integer state;

    @ApiModelProperty(value = "商品系统库存(主)")
    private Integer goodsStock;

    @ApiModelProperty(value = "商品系统库存(辅)")
    private Integer goodsStockSe;

    @ApiModelProperty(value = "货品差额")
    private BigDecimal diffAmount;

}