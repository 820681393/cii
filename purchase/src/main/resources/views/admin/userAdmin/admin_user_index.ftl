<!DOCTYPE html>
<html lang="en">
<head>
<#assign title="后台账号管理">
<#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#if adminInfo.type?? && adminInfo.type==2>
            <#assign menuType="adminMerchantOrderInfo">
            <#else >
            <#assign menuType="adminUser">
        </#if>
    <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <#if adminInfo.type?? && adminInfo.type==2>
                    <#else >
                        <div class="panel">
                            <div class="panel-heading bg-primary">
                                <h6 class="panel-title">
                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">查询条件</a>
                                </h6>
                            </div>
                            <form action="/admin/adminUser/index" method="post">
                                <div id="accordion-styled-group3" class="panel-collapse">
                                    <div class="panel-body">
                                        <div class="row" style="margin-top: 10px;">
                                            <div class="col-xs-4">
                                                <button onclick="myUrlHref('/admin/adminUser/index?type=0')" type="button"  <#if adminInfo.type?? && adminInfo.type==0>class="btn btn-primary"<#else>class="btn btn-default"</#if>  style="width: 100%;">管理层</button>
                                            </div>
                                            <div class="col-xs-4">
                                                <button onclick="myUrlHref('/admin/adminUser/index?type=1')" type="button"  <#if adminInfo.type?? && adminInfo.type==1>class="btn btn-primary"<#else>class="btn btn-default"</#if>  style="width: 100%;">采购层</button>
                                            </div>
                                            <div class="col-xs-4" style="display: none">
                                                <button onclick="myUrlHref('/admin/adminUser/index?type=2')" type="button" <#if adminInfo.type?? && adminInfo.type==2>class="btn btn-primary"<#else>class="btn btn-default"</#if> style="width: 100%;">商户层</button>
                                            </div>
                                            <div class="col-xs-4">
                                                <button onclick="myUrlHref('/admin/adminUser/index?type=3')" type="button" <#if adminInfo.type?? && adminInfo.type==3>class="btn btn-primary"<#else>class="btn btn-default"</#if> style="width: 100%;">财务层</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                </#if>

                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">账户列表</a>
                        <a class="collapsed"  onclick="location.href='<#if adminInfo.type?? && adminInfo.type==2>${basePath}/admin/adminUser/merchantInsert<#else>${basePath}/admin/adminUser/insert</#if>'" style="float: right">
                            <i class="icon-plus2"></i>
                            新增后台账号
                        </a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <table class="table fixed-table">
                            <thead>
                            <tr class="border-bottom-danger">
                                <th>编号</th>
                                <th>用户名</th>
                                <th>用户昵称</th>
                                <th>用户状态</th>
                                <th>用户层级</th>
                                <th>手机号</th>
                                <th>微信号</th>
                                <th>微信昵称</th>
                                <th width="250px">操作</th>
                            </tr>
                            </thead>
                            <tbody>
<#--                            <col style="width: 20%" />-->
<#--                            <col style="width: 20%" />-->
<#--                            <col style="width: 20%" />-->
<#--                            <col style="width: 20%" />-->
<#--                            <col style="width: 20%" />-->
<#--                            <col style="width: 20%" />-->
<#--                            <col style="width: 20%" />-->
<#--                            <col style="width: 175px" />-->
                            <#if pageInfo.list??>
                                <#list pageInfo.list as myn>
                                <tr class="border-top-primary">
                                    <td>${myn.id!}</td>
                                    <td>${myn.userName!}</td>
                                    <td>${myn.nikeName!}</td>
                                    <td>
                                        <#if myn.state==1>
                                            <p style="color: green">正常用户</p>
                                        <#else>
                                            <p style="color: red">禁止登陆</p>
                                        </#if>
                                    </td>
                                    <td>
                                        <#if myn.type==0>
                                            管理层
                                        <#elseif myn.type==1>
                                            采购层
                                        <#elseif myn.type==2>
                                            商户层
                                        <#elseif myn.type==3>
                                            财务层
                                        </#if>
                                    </td>
                                    <td>${myn.tel!}</td>
                                    <td>${myn.wxName!}</td>
                                    <td>${myn.wxNikeName!}</td>
                                    <td>
                                        <div onclick="location.href='<#if adminInfo.type?? && adminInfo.type==2>${basePath}/admin/adminUser/merchantUpdate?id=${myn.id!}<#else>${basePath}/admin/adminUser/update?id=${myn.id!}</#if>'" style="color:#2196f3;float: left;margin-left: 10px;">
                                            <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改','update${myn.id!}')"></i>
                                        </div>
                                        <div id="detail2" onclick="getUserRoleAjax('${myn.id!}')" style="color:blue;float: left;margin-left: 10px;">
                                            <i class="icon-eye" id="role${myn.id!}" onmousemove="layerTips('查看角色','role${myn.id!}')"></i>
                                        </div>
                                        <#if myn.type==2>
                                            <div id="detail2" onclick="merchantToAdminAjax('${myn.id!}')" style="color:blue;float: left;margin-left: 10px;">
                                                <i class="icon-eye" id="eye${myn.id!}" onmousemove="layerTips('查看商户管理员','eye${myn.id!}')"></i>
                                            </div>
<#--                                            <div id="detail2" onclick="getMerchantInfo('${myn.id!}')" style="color:blue;float: left;margin-left: 10px;">-->
<#--                                                <i class="icon-gear" id="set${myn.id!}" onmousemove="layerTips('设置商户参数','set${myn.id!}')"></i>-->
<#--                                            </div>-->
                                            <div id="detail2" onclick="getMerchantAddressInfo('${myn.id!}')" style="color:blue;float: left;margin-left: 10px;">
                                                <i class="icon-eye" id="add${myn.id!}" onmousemove="layerTips('查看商户收货地址','add${myn.id!}')"></i>
                                            </div>
                                        </#if>
                                        <div onclick="deleteLayer('${basePath}/admin/adminUser/delete?id=${myn.id!}')" style="color:red;float: left;margin-left: 10px;">
                                            <i class="icon-cross2" id="delete${myn.id!}" onmousemove="layerTips('删除','delete${myn.id!}')"></i>
                                        </div>
                                    </td>
                                </tr>
                                </#list>
                            </#if>
                            <tr class="border-top-primary">
                                <td colspan="9">
                                    <#import "../common/pagebar.ftl" as pagebar>
                                    <@pagebar.pagebar pageInfo=pageInfo actionUrl="/admin/adminUser/index"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            <#include "../common/foot.ftl"/>
            </div>
            <div id="userRoleAjax">

            </div>
        </div>
    </div>
</div>
</body>
<script>
    function getUserRoleAjax(id) {
        $.ajax({
            url:"/admin/adminUser/getUserRoleAjax",
            data:{
                id:id
            },
            headers: {
                'httpType': "HTML",
            },
            type:"get",
            async:true,
            success:function (data) {
                $('#userRoleAjax').html(data);
            }
        })
    }
    function merchantToAdminAjax(id) {
        $.ajax({
            url:"/admin/merchantToAdmin/indexAjax",
            data:{
                miid:id
            },
            headers: {
                'httpType': "HTML",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.closeAll();
                $('#userRoleAjax').html(data);
            }
        })
    }
    function getMerchantInfo(id) {
        layer.closeAll();
        $.ajax({
            url:"/admin/merchantInfo/getMerchantInfo",
            data:{
                miid:id
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

    function getMerchantAddressInfo(id) {
        layer.closeAll();
        $.ajax({
            url:"/admin/merchantInfoAddress/index",
            data:{
                id:id
            },
            headers: {
                'httpType': "HTML",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.closeAll();
                $('#userRoleAjax').html(data);
            }
        })
    }
</script>
</html>



