package com.purchase.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybaits.jpa.annotation.DaoClass;
import com.purchase.dao.INoticeInfoDao;
import lombok.Data;
import com.purchase.utils.PageInfoModel;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author Musa
 * @since 2020-12-26
 */
@TableName(value = "notice_info")
@DaoClass(daoClass = INoticeInfoDao.class)
@ApiModel(value="")
@Data
public class NoticeInfo extends PageInfoModel implements Serializable  {


    @TableId(value = "id")
    @ApiModelProperty(value = "公告id")
    private Integer id;

    @ApiModelProperty(value = "公告内容")
    private String content;
    @ApiModelProperty(value = "管理员昵称")
    private String nikeName;

    @ApiModelProperty(value = "公告标题")
    private String title;

    @ApiModelProperty(value = "管理员ID")
    private Integer aiid;
    @ApiModelProperty(value = "公告类型（1=BOOS公告 2=采购公告 3=商户公告 4=财务公告）")
    private Integer type;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}