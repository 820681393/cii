package com.purchase.controller.log.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Miracle
 * @date 2020/12/20 0:50
 */
@Data
@ApiModel(value = "日志文件VO")
public class FileListVO {

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "是否是文件 1=是 2=否")
    private Integer isFile;

    @ApiModelProperty(value = "最后修改时间")
    private Long lastModified;

}
