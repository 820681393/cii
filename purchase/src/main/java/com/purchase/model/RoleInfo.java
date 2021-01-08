package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IRoleInfoDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 用户角色信息
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@TableName(value = "role_info")
@DaoClass(daoClass = IRoleInfoDao.class)
@ApiModel(value="用户角色信息")
@Data
public class RoleInfo extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "角色id")
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}