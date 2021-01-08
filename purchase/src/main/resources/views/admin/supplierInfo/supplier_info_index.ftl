
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="供应商信息列表">
    <#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="supplierInfo">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="panel">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">时时汇率信息</a>
                        </h6>
                    </div>
                    <form action="/admin/supplierInfo/updateExchangeRate" method="post">
                        <div id="accordion-styled-group3" class="panel-collapse">
                            <div class="panel-body">
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-xs-3">
                                        <input type="text"  name="exchangeRate" value="${exchangeRate!}" class="form-control" placeholder="汇率">
                                    </div>
                                    <div class="col-xs-3">
                                        <button type="submit" class="btn btn-primary" style="width: 158px;display: inline-block">修改汇率</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">供应商库列表</a>
                        <a class="collapsed"  onclick="location.href='/admin/supplierInfo/insert'" style="float: right">
                            <i class="icon-plus2"></i>
                            新增供应商
                        </a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <table class="table fixed-table">
                            <thead>
                                <tr class="border-bottom-danger">
                                    <th>名称</th>
                                    <th>电话</th>
                                    <th>地址</th>
                                    <th>联系人名称</th>
                                    <th>微信号</th>
                                    <th>供应状态</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#if supplierInfoList??>
                                <#list supplierInfoList as myn>
                                    <tr class="border-top-primary">
                                        <td>${myn.name!}</td>
                                        <td>
<#--                                            ${myn.tel!}-->
                                            ${myn.tel?replace("/","<br>")}
                                        </td>
                                        <td>${myn.address!}</td>
                                        <td>${myn.linkUser!}</td>
                                        <td>${myn.wxNumber!}</td>
                                        <td>
                                            <#if myn.state==1>
                                                营业
                                            <#elseif myn.state==2>
                                                停业
                                            </#if>
                                        </td>
                                        <td>
                                            <div onclick="location.href='/admin/supplierInfo/update?id=${myn.id}'" style="color:#2196f3;float: left;margin-left: 10px;">
                                                <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改','update${myn.id!}')"></i>
                                            </div>
                                            <div onclick="getGoodsTypeAjax('${myn.id!}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                <i class="icon-plus2" id="insert${myn.id!}" onmousemove="layerTips('新增供应商品','update${myn.id!}')"></i>
                                            </div>
                                            <div id="detail2" onclick="getGoodsTypeInfoAjax('${myn.id!}')" style="color:blue;float: left;margin-left: 10px;">
                                                <i class="icon-eye" id="role${myn.id!}" onmousemove="layerTips('查看价格','role${myn.id!}')"></i>
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
            <div id="goodsTypeInfo">

            </div>
        </div>
    </div>
</div>
</body>
<script>
    function getGoodsTypeAjax(id) {
        $.ajax({
            url:"/admin/supplierInfo/getGoodsTypeAjax",
            data:{
                id:id
            },
            headers: {
                'httpType': "HTML",
            },
            type:"get",
            async:true,
            success:function (data) {
                $('#goodsTypeInfo').html(data);
            }
        })
    }
    function getGoodsTypeInfoAjax(id) {
        $.ajax({
            url:"/admin/supplierInfo/getGoodsTypeInfoAjax",
            data:{
                id:id
            },
            headers: {
                'httpType': "HTML",
            },
            type:"get",
            async:true,
            success:function (data) {
                $('#goodsTypeInfo').html(data);
            }
        })
    }
</script>
</html>



