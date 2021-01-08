
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="公告列表">
    <#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="noticeInfo">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">公告列表</a>
                        <a class="collapsed"  onclick="insertAjax()" style="float: right">
                            <i class="icon-plus2"></i>
                            新增公告
                        </a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                            <table class="table" >
                                <thead>
                                <tr class="border-bottom-danger">
                                    <th>标题</th>
                                    <th>公告类型</th>
                                    <th>发布人昵称</th>
                                    <th>结束时间</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if noticeInfoList??>
                                    <#list noticeInfoList as myn>
                                        <tr class="border-top-primary">
                                            <td>${myn.title!}</td>
                                            <td>
                                                <#if myn.type==1>
                                                    BOOS公告
                                                <#elseif myn.type==2>
                                                    采购公告
                                                <#elseif myn.type==3>
                                                    商户公告
                                                <#elseif myn.type==4>
                                                    财务公告
                                                </#if>
                                            </td>
                                            <td>${myn.nikeName!}</td>
                                            <td>${(myn.endTime!?string("yyyy-MM-dd"))}</td>
                                            <td>${(myn.createTime!?string("yyyy-MM-dd HH:mm:ss"))}</td>
                                            <td>
                                                <div onclick="updateAjax('${myn.id}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                    <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改','update${myn.id!}')"></i>
                                                </div>
                                                <div onclick="updateAjax('${myn.id}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                    <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改','update${myn.id!}')"></i>
                                                </div>
                                                <div onclick="deleteLayer('${basePath}/admin/noticeInfo/delete?id=${myn.id!}')" style="color:red;float: left;margin-left: 10px;">
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
<script>
    function insertAjax() {
        $.ajax({
            url:"/admin/noticeInfo/insert",
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
                    content: data,
                    area: ['500px', 'auto']
                });
            }
        })
    }
    function updateAjax(id) {
        $.ajax({
            url:"/admin/noticeInfo/update",
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
                    content: data,
                    area: ['500px', 'auto']
                });
            }
        })
    }

</script>
</html>



