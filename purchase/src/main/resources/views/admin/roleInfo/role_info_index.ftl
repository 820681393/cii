<!DOCTYPE html>
<html lang="en">
<head>
<#assign title="角色管理">
<#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
    <#assign menuType="roleInfo">
    <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">岗位列表</a>
                        <a class="collapsed"  onclick="location.href='${basePath}/admin/roleInfo/insert'" style="float: right">
                            <i class="icon-plus2"></i>
                            新增岗位
                        </a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr class="border-bottom-danger">
                                    <th>编号</th>
                                    <th>角色名称</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#if roleInfoList??>
                                <#list roleInfoList as myn>
                                <tr class="border-top-primary">
                                    <td>${myn.id!}</td>
                                    <td>${myn.name!}</td>
                                    <td>${myn.createTime!?string("yyyy-MM-dd HH:mm:ss")}</td>
                                    <td>
                                        <div onclick="location.href='${basePath}/admin/roleInfo/update?id=${myn.id!}'" style="color:#2196f3;float: left;margin-left: 10px;">
                                            <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改','update${myn.id!}')"></i>
                                        </div>
                                        <div onclick="deleteLayer('${basePath}/admin/roleInfo/delete?id=${myn.id!}')" style="color:red;float: left;margin-left: 10px;">
                                            <i class="icon-cross2" id="delete${myn.id!}" onmousemove="layerTips('删除','delete${myn.id!}')"></i>
                                        </div>
                                        <div onclick="getEenuAjax('${myn.id!}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                            <i class="icon-eye" id="eye${myn.id!}" onmousemove="layerTips('查看权限','eye${myn.id!}')"></i>
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
            <div style="margin-top: 30px" id="menuAjax"></div>
        </div>
    </div>
</div>
</body>
<script>
    function getEenuAjax(id) {
        $.ajax({
            url:"/admin/roleInfo/getEenuAjax",
            data:{
                id:id
            },
            headers: {
                'httpType': "HTML",
            },
            type:"get",
            async:true,
            success:function (data) {
                $('#menuAjax').html(data);
            }
        })
    }

</script>
</html>



