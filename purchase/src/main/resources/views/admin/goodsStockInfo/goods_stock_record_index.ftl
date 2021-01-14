
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="商品信息">
    <#include "../common/head.ftl"/>
    <style>
        .table-responsive .table img{
            max-width: 38px;
            /*border-radius: 50%;*/
            margin-right: 5px;
            cursor: pointer;
        }
        .panel-title a{
            margin-left: 15px;
        }
        .state{
            cursor: pointer;
        }
    </style>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="goodsInfo">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
<#--                <div class="panel">-->
<#--                    <div class="panel-heading bg-primary">-->
<#--                        <h6 class="panel-title">-->
<#--                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">查询条件</a>-->
<#--                        </h6>-->
<#--                    </div>-->
<#--                    <form action="/admin/goodsInfo/index" method="post">-->
<#--                        <div id="accordion-styled-group3" class="panel-collapse">-->
<#--                            <div class="panel-body">-->
<#--                                <div class="row" style="margin-top: 10px;">-->
<#--                                    <div class="col-xs-3">-->
<#--                                        <select class="form-control" name="type">-->
<#--                                            <option value="">一级分类</option>-->
<#--                                        </select>-->
<#--                                    </div>-->
<#--                                </div>-->
<#--                                <div class="row" style="margin-top: 10px;text-align: center">-->
<#--                                    <button type="submit" class="btn btn-primary" style="width: 158px;display: inline-block">查询</button>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </form>-->
<#--                </div>-->
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">
                            商品<#if goodsStockInfo.type==1>入库</#if><#if goodsStockInfo.type==2>出库</#if><#if goodsStockInfo.type==3>盘点</#if>信息列表
                        </a>
                        <#if goodsStockInfo.type==1>
                            <a class="collapsed"  onclick="location.href='/admin/goodsStockInfo/stockInput?mode=1'" style="float: right">
                                <i class="icon-plus2"></i>
                                新增入库
                            </a>
                        </#if>
                        <#if goodsStockInfo.type==2>
                            <a class="collapsed"  onclick="location.href='/admin/goodsStockInfo/stockInput?mode=2'" style="float: right">
                                <i class="icon-plus2"></i>
                                新增出库
                            </a>
                        </#if>
                        <#if goodsStockInfo.type==3>
                            <a class="collapsed"  onclick="location.href='/admin/goodsStockInfo/stockInput?mode=3'" style="float: right">
                                <i class="icon-plus2"></i>
                                新增盘点
                            </a>
                        </#if>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <table class="table fixed-table" >
                            <thead>
                            <tr class="border-bottom-danger">
                                <th>单号</th>
                                <th>数量</th>
                                <th>总价</th>
                                <th>类型</th>
                                <th>时间</th>
                                <th>操作人</th>
                                <th>备注</th>
                                <th>查看明细</th>
                            </tr>
                            </thead>
                            <tbody>
                            <col style="width: 20%" />
                            <col style="width: 20%" />
                            <col style="width: 20%" />
                            <col style="width: 20%" />
                            <col style="width: 20%" />
                            <col style="width: 20%" />
                            <col style="width: 20%" />
                            <col style="width: 20%" />
                            <#if pageInfos??>
                                <#list pageInfos.list as myn>
                                    <tr class="border-top-primary">
                                        <td>${myn.orderNumber!}</td>
                                        <td>${myn.stockNumber!}</td>
                                        <td>${myn.totalPrice!}</td>
                                        <td>${myn.typeName!}</td>
                                        <td>${(myn.createTime!?string("yyyy-MM-dd HH:mm:ss"))?replace(" ","<br>")}</td>
                                        <td>${myn.adminName!}</td>
                                        <td>${myn.remark!}</td>
                                        <td>
                                            <div onclick="goodsStockDetailAjax('${myn.id}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                <i class="icon-eye" id="update${myn.id!}" onmousemove="layerTips('修改','update${myn.id!}')"></i>
                                            </div>
                                        </td>
                                    </tr>
                                </#list>
                            </#if>
                            <tr class="border-top-primary">
                                <td colspan="15">
                                    <#import "../common/pagebar.ftl" as pagebar>
                                    <@pagebar.pagebar pageInfo=pageInfos actionUrl="/admin/goodsStockInfo/index"/>
                                </td>
                            </tr>
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
    function goodsStockDetailAjax(gsiid) {
        $.ajax({
            url:"/admin/goodsStockInfo/goodsStockDetail",
            data:{
                id:gsiid
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
                    area:["988px","572px"]
                });
            }
        })
    }
</script>
</html>



