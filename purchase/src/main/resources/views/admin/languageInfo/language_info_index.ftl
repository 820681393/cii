
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="语言信息列表">
    <#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="languageInfo">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">语言列表</a>
                        <a class="collapsed"  onclick="insertAjax()" style="float: right">
                            <i class="icon-plus2"></i>
                            新增语言
                        </a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr class="border-bottom-danger">
                                <th>中文</th>
                                <th>英文</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if languageInfoList??>
                                <#list languageInfoList as myn>
                                    <tr class="border-top-primary">
                                        <td>${myn.cn!}</td>
                                        <td>${myn.en!}</td>
                                        <td>
                                            <div onclick="deleteLayer('${basePath}/admin/languageInfo/delete?id=${myn.id!}')" style="color:red;float: left;margin-left: 10px;">
                                                <i class="icon-cross2" id="deleteTow${myn.id!}" onmousemove="layerTips('删除语言信息','deleteTow${myn.id!}')"></i>
                                            </div>
                                            <div onclick="updateAjax('${myn.id!}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改语言信息','update${myn.id!}')"></i>
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
<script>
    function insertAjax() {
        $.ajax({
            url:"/admin/languageInfo/insert",
            data:{
            },
            headers: {
                'httpType': "HTML",
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
            url:"/admin/languageInfo/update",
            data:{
                id:id
            },
            headers: {
                'httpType': "HTML",
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



