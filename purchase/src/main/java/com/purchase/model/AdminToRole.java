package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IAdminToRoleDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@TableName(value = "admin_to_role")
@DaoClass(daoClass = IAdminToRoleDao.class)
@ApiModel(value="")
@Data
public class AdminToRole extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "用户对应角色id")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer aiid;

    @ApiModelProperty(value = "角色id")
    private Integer riid;

}