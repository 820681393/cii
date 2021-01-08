
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="后台菜单管理">
    <#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="menuInfo">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">权限列表</a>
                        <a class="collapsed"  onclick="insertAjax('0')" style="float: right">
                            <i class="icon-plus2"></i>
                            新增菜单
                        </a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <div style="overflow-x: auto;">
                            <table class="table">
                                <tbody>
                                <#if menuOneInfos??>
                                    <#list menuOneInfos as myn>
                                        <tr class="border-top-primary">
                                            <td>
                                                <span class="text-primary">${myn.name!}</span>
                                            </td>
                                            <td>
                                                <span class="text-primary">${myn.type!}</span>
                                            </td>
                                            <td>
                                                <span class="text-primary">${myn.url!}</span>
                                            </td>
                                            <td></td>
                                            <td>
                                                <div onclick="deleteLayer('${basePath}/admin/menuInfo/delete?id=${myn.id!}')" style="color:red;float: left;margin-left: 10px;">
                                                    <i class="icon-cross2" id="delete${myn.id!}" onmousemove="layerTips('删除','delete${myn.id!}')"></i>
                                                </div>
                                                <div onclick="updateAjax('${myn.id!}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                    <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改','update${myn.id!}')"></i>
                                                </div>
                                                <div onclick="insertAjax('${myn.id!}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                    <i class="icon-plus2" id="insert${myn.id!}" onmousemove="layerTips('新增二级菜单','insert${myn.id!}')"></i>
                                                </div>
                                            </td>
                                        </tr>
                                        <#if myn.menuInfoList??>
                                            <#list myn.menuInfoList as mym>
                                                <tr class="border-top-primary">
                                                    <td></td>
                                                    <td>
                                                        <span class="text-success">${mym.name!}</span>
                                                    </td>
                                                    <td>
                                                        <span class="text-success">${mym.type!}</span>
                                                    </td>
                                                    <td>
                                                        <span class="text-success">${mym.url!}</span>
                                                    </td>
                                                    <td>
                                                        <div onclick="deleteLayer('${basePath}/admin/menuInfo/delete?id=${mym.id!}')" style="color:red;float: left;margin-left: 10px;">
                                                            <i class="icon-cross2" id="delete2${mym.id!}" onmousemove="layerTips('删除','delete2${mym.id!}')"></i>
                                                        </div>
                                                        <div onclick="updateAjax('${mym.id!}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                            <i class="icon-pencil3" id="update2${myn.id!}" onmousemove="layerTips('修改','update2${myn.id!}')"></i>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </#list>
                                        </#if>
                                    </#list>
                                </#if>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
                <#include "../common/foot.ftl"/>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function insertAjax(miid) {
        $.ajax({
            url:"/admin/menuInfo/insertAjax",
            data:{
                miid:miid
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    anim: 1,
                    shadeClose: true,
                    content: data
                });
            }
        })
    }


    function updateAjax(id) {
        $.ajax({
            url:"/admin/menuInfo/updateAjax",
            data:{
                id:id
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    anim: 1,
                    shadeClose: true,
                    content: data
                });
            }
        })
    }

</script>
</html>



