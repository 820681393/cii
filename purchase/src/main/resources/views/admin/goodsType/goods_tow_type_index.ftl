
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="二级分类列表">
    <#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="goodsType">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">数据列表</a>
                        <a class="collapsed"  onclick="location.href='/admin/goodsTowType/insert'" style="float: right">
                            <i class="icon-plus2"></i>
                            新增分类
                        </a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr class="border-bottom-danger">
                                    <th>编号</th>
                                    <th>分类名称</th>
                                    <th>一级分类名称</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#if goodsTowTypeList??>
                                <#list goodsTowTypeList as myn>
                                    <tr class="border-top-primary">
                                        <td>${myn.id!}</td>
                                        <td>${myn.name!}</td>
                                        <td>${myn.goodsOneTypeName!}</td>
                                        <td>
                                            <div onclick="location.href='/admin/goodsTowType/update?id=${myn.id}'" style="color:#2196f3;float: left;margin-left: 10px;">
                                                <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改','update${myn.id!}')"></i>
                                            </div>
                                            <div onclick="deleteLayer('${basePath}/admin/goodsTowType/delete?id=${myn.id!}')" style="color:red;float: left;margin-left: 10px;">
                                                <i class="icon-cross2" id="delete${myn.id!}" onmousemove="layerTips('删除','delete${myn.id!}')"></i>
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



