package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IAdminInfoDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 后台用户信息
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@TableName(value = "admin_info")
@DaoClass(daoClass = IAdminInfoDao.class)
@ApiModel(value="后台用户信息")
@Data
public class AdminInfo extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "后台用户id")
    private Integer id;
    @ApiModelProperty(value = "用户状态 1=正常账户 2=禁止登陆")
    private Integer state;

    @ApiModelProperty(value = "后台用户名")
    private String userName;

    @ApiModelProperty(value = "后台用户密码")
    private String passWord;

    @ApiModelProperty(value = "后台用户昵称")
    private String nikeName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "用户类型：1=采购后台 2=商户后台 3=财务后台")
    private Integer type;
    @ApiModelProperty(value = "手机号")
    private String tel;
    @ApiModelProperty(value = "微信号")
    private String wxName;
    @ApiModelProperty(value = "微信昵称")
    private String wxNikeName;

}