package com.purchase.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.mybaits.jpa.annotation.PageQuery;
import com.mybaits.jpa.jpaEnum.QueryOrderType;
import com.mybaits.jpa.jpaEnum.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IOrderInfoDao;
import lombok.Data;
import java.math.BigDecimal;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.ArrayList;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商品下单信息
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@TableName(value = "order_info")
@DaoClass(daoClass = IOrderInfoDao.class)
@ApiModel(value="商品下单信息")
@Data
public class OrderInfo extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "商品下单id")
    private Integer id;

    @ApiModelProperty(value = "订单号")
    private String orderNumber;

    @ApiModelProperty(value = "采购车辆")
    private Integer ciid;

    @ApiModelProperty(value = "下单用户id")
    private Integer xuid;

    @ApiModelProperty(value = "采购用户id")
    private Integer suid;

    @ApiModelProperty(value = "回执用户id")
    private Integer huid;

    @ApiModelProperty(value = "采购状态（1=未采购 2=提交绘制 3=确认回执 4=采购完成）")
    private Integer state;

    @ApiModelProperty(value = "订单总价格")
    private BigDecimal sumPrice;

    @ApiModelProperty(value = "完成时间")
    private Date successTime;


    @ApiModelProperty(value = "创建时间")
    @PageQuery(queryOrderType= QueryOrderType.DESC)
    private Date createTime;

    @ApiModelProperty(value = "订单商品总数")
    private BigDecimal sumNumber;

    @ApiModelProperty(value = "下单员名称")
    @TableField(exist = false)
    private String xuName;

    @ApiModelProperty(value = "采购员名称")
    @TableField(exist = false)
    private String suName;

    @ApiModelProperty(value = "回执员名称")
    @TableField(exist = false)
    private String huName;

    @ApiModelProperty(value = "车辆名称")
    @TableField(exist = false)
    private String carName;

    @ApiModelProperty(value = "创建时间")
    @TableField(exist = false)
    @PageQuery(queryType= QueryType.GE,column = "createTime")
    private Date createTimeStart;

    @ApiModelProperty(value = "创建时间")
    @TableField(exist = false)
    @PageQuery(queryType= QueryType.LE,column = "createTime")
    private Date createTimeEnd;

    @ApiModelProperty(value = "订单详细商品")
    @TableField(exist = false)
    private List<OrderInfoDetail> orderInfoDetailList = new ArrayList<>();
}