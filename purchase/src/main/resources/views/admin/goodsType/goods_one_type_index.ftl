
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="一级分类列表">
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
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">商品分级设置</a>
                        <a class="collapsed"  onclick="insertOneAjax()" style="float: right">
                            <i class="icon-plus2"></i>
                            新增一级分类
                        </a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
<#--                        <div style="overflow-x: auto;   width:2000px;">-->
                            <table class="table" >
                                <thead>
                                <tr class="border-bottom-danger">
                                    <th>一级分类名称</th>
                                    <th>二级分类名称</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if goodsOneTypeList??>
                                    <#list goodsOneTypeList as myn>
                                        <tr class="border-top-primary">
                                            <td>
                                                <input data-id="${myn.id}" type="checkbox" class="state" name="state" <#if myn.state==1>checked</#if>/>
                                                <span class="text-primary">${myn.name!}（${myn.ename!}）： ${myn.id}</span>
                                            </td>
                                            <td></td>
                                            <td>
                                                <div onclick="deleteLayer('${basePath}/admin/goodsOneType/delete?id=${myn.id!}')" style="color:red;float: left;margin-left: 10px;">
                                                    <i class="icon-cross2" id="delete${myn.id!}" onmousemove="layerTips('删除一级分类','delete${myn.id!}')"></i>
                                                </div>
                                                <div onclick="updateOneAjax('${myn.id}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                    <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改一级分类','update${myn.id!}')"></i>
                                                </div>
                                                <div onclick="insertTowAjax('${myn.id!}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                    <i class="icon-plus2" id="insert${myn.id!}" onmousemove="layerTips('新增二级分类','update${myn.id!}')"></i>
                                                </div>
                                            </td>
                                        </tr>
                                        <#if goodsTowTypeList??>
                                            <#list goodsTowTypeList as myt>
                                                <#if myn.id==myt.goid>
                                                    <tr class="border-top-primary">
                                                        <td></td>
                                                        <td><span class="text-success">${myt.name!}（${myt.ename!}）： ${myn.id}</span></td>
                                                        <td>
                                                            <div onclick="deleteLayer('${basePath}/admin/goodsTowType/delete?id=${myt.id!}')" style="color:red;float: left;margin-left: 10px;">
                                                                <i class="icon-cross2" id="deleteTow${myt.id!}" onmousemove="layerTips('删除二级分类','deleteTow${myt.id!}')"></i>
                                                            </div>
                                                            <div onclick="updateTowAjax('${myt.id!}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                                <i class="icon-pencil3" id="updateTow${myt.id!}" onmousemove="layerTips('修改二级分类','updateTow${myt.id!}')"></i>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </#if>
                                            </#list>
                                        </#if>
                                    </#list>
                                </#if>
                                </tbody>
                            </table>
<#--                        </div>-->

                    </div>
                </div>
                <#include "../common/foot.ftl"/>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function insertOneAjax() {
        $.ajax({
            url:"/admin/goodsOneType/insert",
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
    function updateOneAjax(id) {
        $.ajax({
            url:"/admin/goodsOneType/update",
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

    function insertTowAjax(goid) {
        $.ajax({
            url:"/admin/goodsTowType/insert",
            data:{
                goid:goid
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
    function updateTowAjax(id) {
        $.ajax({
            url:"/admin/goodsTowType/update",
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

    $(".state").click(function(){
        var thisDom = $(this);
        var dataId = thisDom.attr("data-id");
        var state = 0;
        if(this.checked){
            state = 1;
        }
        $.ajax({
            url:"/admin/goodsOneType/updateGoodsOneType",
            data:{
                id:dataId,
                state:state
            },
            headers: {
                'httpType': "JSON",
            },
            type:"post",
            async:false,
            success:function (data) {
                if(data.statusCode==200){
                    // window.location.href = "/admin/goodsInfo/index";
                }else if(data.statusCode==203){
                    // layer.alert("权限不足")
                }
            }
        })
    });
</script>
</html>



