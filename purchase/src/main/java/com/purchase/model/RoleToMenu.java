package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IRoleToMenuDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 角色对应菜单信息
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@TableName(value = "role_to_menu")
@DaoClass(daoClass = IRoleToMenuDao.class)
@ApiModel(value="角色对应菜单信息")
@Data
public class RoleToMenu extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "角色对应菜单id")
    private Integer id;

    @ApiModelProperty(value = "角色id")
    private Integer riid;

    @ApiModelProperty(value = "菜单id")
    private Integer miid;

}