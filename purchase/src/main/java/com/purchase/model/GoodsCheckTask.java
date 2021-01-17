package com.purchase.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IGoodsCheckTaskDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Musa
 * @since 2021-01-17
 */
@TableName(value = "goods_check_task")
@DaoClass(daoClass = IGoodsCheckTaskDao.class)
@ApiModel(value="")
@Data
public class GoodsCheckTask extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商品盘点任务id")
    private Integer id;

    @ApiModelProperty(value = "单号")
    private String orderNumber;

    @ApiModelProperty(value = "商品一级分类id")
    private Integer goid;

    @ApiModelProperty(value = "任务发起人id")
    private Integer aiid;

    @ApiModelProperty(value = "任务接收人id")
    private Integer receiveAiid;

    @ApiModelProperty(value = "发起时间")
    private Date createTime;

    @ApiModelProperty(value = "盘点时间")
    private Date checkTime;

    @ApiModelProperty(value = "完成时间")
    private Date finishTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态：1未处理 2待确认 3已完成")
    private Integer state;

    @ApiModelProperty(value = "商品总库存货值")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "商品总盘点货值")
    private BigDecimal realTotalAmount;

    @ApiModelProperty(value = "商品盘点总库存差值")
    private BigDecimal totalDiffAmount;

    @ApiModelProperty(value = "商品一级分类名称")
    @TableField(exist = false)
    private String goName;

    @ApiModelProperty(value = "任务发起人姓名")
    @TableField(exist = false)
    private String adminName;

    @ApiModelProperty(value = "任务接收人姓名")
    @TableField(exist = false)
    private String receiveAdminName;

    @ApiModelProperty(value = "盘点商品详细列表")
    @TableField(exist = false)
    private List<GoodsCheckTaskDetail> goodsCheckTaskDetailList;

}