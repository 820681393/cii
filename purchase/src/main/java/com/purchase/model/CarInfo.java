package com.purchase.model;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.ICarInfoDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import java.util.Date;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author Miracle
 * @since 2020-11-02
 */
@TableName(value = "car_info")
@DaoClass(daoClass = ICarInfoDao.class)
@ApiModel(value="车辆信息")
@Data
public class CarInfo extends PageInfoModel implements Serializable  {


    @TableId("id")
    @ApiModelProperty(value = "车辆id")
    private Integer id;

    @ApiModelProperty(value = "车辆名称")
    private String name;
    @ApiModelProperty(value = "车主")
    private String owner;
    @ApiModelProperty(value = "使用人员")
    private String useName;
    @ApiModelProperty(value = "车辆OR照片")
    private String carOrImage;

    @ApiModelProperty(value = "车辆CR照片")
    private String carCrImage;

    @ApiModelProperty(value = "车辆OR时间")
    private String carOrTime;

    @ApiModelProperty(value = "车辆CR时间")
    private String carCrTime;

    @ApiModelProperty(value = "车辆照片")
    private String image;

    @ApiModelProperty(value = "车牌号码")
    private String number;
    @ApiModelProperty(value = "限行日期周一到周日（1,2,3 为周一周二周三限行）")
    private String limitDay;

    @ApiModelProperty(value = "车辆状态 1=可用 2=不可用 3=维修中 4=行驶中")
    private Integer state;

    @ApiModelProperty(value = "车辆类型 1=正常车辆 2=无不能使用状态")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}