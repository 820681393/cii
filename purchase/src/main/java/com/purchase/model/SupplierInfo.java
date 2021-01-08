package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.ISupplierInfoDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 供应商信息
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@TableName(value = "supplier_info")
@DaoClass(daoClass = ISupplierInfoDao.class)
@ApiModel(value="供应商信息")
@Data
public class SupplierInfo extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "供应商id")
    private Integer id;

    @ApiModelProperty(value = "供应商名称")
    private String name;

    @ApiModelProperty(value = "供应商地址")
    private String address;

    @ApiModelProperty(value = "供应商电话")
    private String tel;

    @ApiModelProperty(value = "联系人名称")
    private String linkUser;

    @ApiModelProperty(value = "微信号")
    private String wxNumber;

    @ApiModelProperty(value = "供应状态 1=营业 2=停业")
    private Integer state;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}