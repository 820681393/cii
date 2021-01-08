package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.IGoodsInfoImagesDao;
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
 * @since 2020-11-24
 */
@TableName(value = "order_info_images")
@DaoClass(daoClass = IGoodsInfoImagesDao.class)
@ApiModel(value="")
@Data
public class OrderInfoImages extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "图片ID")
    private Integer id;

    @ApiModelProperty(value = "订单信息ID")
    private Integer oiid;

    @ApiModelProperty(value = "图片名称")
    private String imageName;

    @ApiModelProperty(value = "所属类型：1-采购单，2-商户单")
    private Integer type;

}