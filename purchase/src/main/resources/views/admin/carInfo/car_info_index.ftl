
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="车辆信息列表">
    <#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="carInfo">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">车辆列表</a>
                        <a class="collapsed"  onclick="location.href='/admin/carInfo/insert'" style="float: right">
                            <i class="icon-plus2"></i>
                            新增车辆
                        </a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
<#--                        <div style="overflow-x: auto;   width:2000px;">-->
                            <table class="table fixed-table" >
                                <thead>
                                <tr class="border-bottom-danger">
                                    <th>车辆名称</th>
                                    <th>车主</th>
                                    <th>使用人员</th>
                                    <th>OR时间</th>
                                    <th>车辆图片</th>
                                    <th>车牌号码</th>
                                    <th>限行日期</th>
                                    <th>车辆状态</th>
                                    <th width="175px">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if carInfoList??>
                                    <#list carInfoList as myn>
                                        <tr class="border-top-primary">
                                            <td>${myn.name!}</td>
                                            <td>${myn.owner!}</td>
                                            <td>${myn.useName!}</td>
                                            <td>${myn.carOrTime!}</td>
                                            <td>
                                                <#if myn.image??>
                                                    <img onclick="myImageOpen(this)" width="50px" src="${aliyunOosUrl!}${myn.image!}"/>
                                                <#else >
                                                    <img style="cursor: default" width="50px" src="/statics/admin/assets/images/default_goods.jpg"/>
                                                </#if>
                                            </td>
                                            <td>${myn.number!}</td>
                                            <td>${myn.limitDay!}</td>
                                            <td>
                                                <#if myn.state==1>
                                                    <span class="text-success">可用</span>
                                                <#elseif myn.state==2>
                                                    <span class="text-danger">不可用</span>
                                                <#elseif myn.state==3>
                                                    <span class="text-danger">维修中</span>
                                                <#elseif myn.state==4>
                                                    <span class="text-danger">行驶中</span>
                                                </#if>
                                            </td>
                                            <td>
                                                <div onclick="deleteLayer('${basePath}/admin/carInfo/delete?id=${myn.id!}')" style="color:red;float: left;margin-left: 10px;">
                                                    <i class="icon-cross2" id="deleteTow${myn.id!}" onmousemove="layerTips('删除车辆','deleteTow${myn.id!}')"></i>
                                                </div>
                                                <div onclick="location.href='/admin/carInfo/update?id=${myn.id}'" style="color:#2196f3;float: left;margin-left: 10px;">
                                                    <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改','update${myn.id!}')"></i>
                                                </div>
                                                <div id="detail2" onclick="myImageOpen(this)" style="color:#2196f3;float: left;margin-left: 10px;">
                                                    <i class="icon-eye" id="${myn.id!}imagePositive1" onmousemove="layerTips('查看车辆图片','${myn.id!}imagePositive1')"></i>
                                                </div>
                                                <div id="detail2" onclick="myImageOpen(this)" style="color:#2196f3;float: left;margin-left: 10px;">
                                                    <i class="icon-eye" id="${myn.id!}imagePositive2" onmousemove="layerTips('查看OR图片','${myn.id!}imagePositive2')"></i>
                                                </div>
                                                <div id="detail2" onclick="myImageOpen(this)" style="color:#2196f3;float: left;margin-left: 10px;">
                                                    <i class="icon-eye" id="${myn.id!}imagePositive3" onmousemove="layerTips('查看CR图片','${myn.id!}imagePositive3')"></i>
                                                </div>
                                            </td>
                                        </tr>
                                    </#list>
                                </#if>
                                </tbody>
                            </table>
                    </div>
                </div>
                <#include "../common/foot.ftl"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>



