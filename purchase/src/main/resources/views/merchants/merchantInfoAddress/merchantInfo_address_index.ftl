
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="商户收货地址">
    <#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="merchantInfoAddress">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">车辆列表</a>
                        <a class="collapsed"  onclick="location.href='/merchants/merchantInfoAddress/insert'" style="float: right">
                            <i class="icon-plus2"></i>
                            新增收货地址
                        </a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <#--                        <div style="overflow-x: auto;   width:2000px;">-->
                        <table class="table" >
                            <thead>
                            <tr class="border-bottom-danger">
                                <th>商家收货人</th>
                                <th>商家收货电话</th>
                                <th>商家收货地址</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if merchantInfoAddressList??>
                                <#list merchantInfoAddressList as myn>
                                    <tr class="border-top-primary">
                                        <td>${myn.name!}</td>
                                        <td>${myn.tel!}</td>
                                        <td>${myn.address!}</td>
                                        <td>
                                            <div onclick="deleteLayer('${basePath}/merchants/merchantInfoAddress/delete?id=${myn.id!}')" style="color:red;float: left;margin-left: 10px;">
                                                <i class="icon-cross2" id="deleteTow${myn.id!}" onmousemove="layerTips('删除地址','deleteTow${myn.id!}')"></i>
                                            </div>
                                            <div onclick="location.href='/merchants/merchantInfoAddress/update?id=${myn.id}'" style="color:#2196f3;float: left;margin-left: 10px;">
                                                <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改地址','update${myn.id!}')"></i>
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



