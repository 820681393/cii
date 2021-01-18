package com.purchase.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.mybaits.jpa.annotation.PageQuery;
import com.mybaits.jpa.jpaEnum.QueryOrderType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IMenuInfoDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.ArrayList;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单信息
 * </p>
 *
 * @author Miracle
 * @since 2020-11-03
 */
@TableName(value = "menu_info")
@DaoClass(daoClass = IMenuInfoDao.class)
@ApiModel(value="菜单信息")
@Data
public class MenuInfo extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "菜单信息id")
    private Integer id;

    @ApiModelProperty(value = "上级菜单id（没有为0）")
    private Integer miid;

    @ApiModelProperty(value = "菜单名称")
    private String name;
    @ApiModelProperty(value = "菜单类型")
    private String type;

    @ApiModelProperty(value = "菜单访问地址")
    private String url;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "菜单顺序号")
    private Integer sort;


    @ApiModelProperty(value = "下级菜单集合")
    @TableField(exist = false)
    private List<MenuInfo> menuInfoList=new ArrayList<>();

    public MenuInfo(String name, String type, String url) {
        this.name = name;
        this.type = type;
        this.url = url;
    }

    public MenuInfo() {
    }

}