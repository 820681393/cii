
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="采购单报表">
    <#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="report">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="panel">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">查询条件</a>
                        </h6>
                    </div>
                    <form action="/admin/report/orderInfoReport" method="post">
                        <div id="accordion-styled-group3" class="panel-collapse">
                            <div class="panel-body">
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-xs-3">
                                        <input type="text"  name="orderNumber" value="${orderInfo.orderNumber!}" class="form-control" placeholder="订单号">
                                    </div>
                                    <div class="col-xs-3">
                                        <input id="create-time-start" name="createTimeStart" value="<#if orderInfo.createTimeStart??>${orderInfo.createTimeStart!?string("yyyy-MM-dd HH:mm:ss")}</#if>" type="text" class="form-control" placeholder="开始时间">
                                    </div>
                                    <div class="col-xs-3">
                                        <input id="create-time-end" name="createTimeEnd" value="<#if orderInfo.createTimeEnd??>${orderInfo.createTimeEnd!?string("yyyy-MM-dd HH:mm:ss")}</#if>" type="text" class="form-control" placeholder="结束时间">
                                    </div>
                                </div>
                                <div class="row" style="margin-top: 10px;text-align: center">
                                    <button type="submit" class="btn btn-primary" style="width: 158px;display: inline-block">查询</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">数据列表</a>
<#--                        <a class="collapsed"  onclick="location.href='/admin/goodsInfo/insert'" style="float: right">-->
<#--                            <i class="icon-plus2"></i>-->
<#--                            新增商品-->
<#--                        </a>-->
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr class="border-bottom-danger">
                                    <th>订单号</th>
                                    <th>下单员</th>
                                    <th>采购员</th>
                                    <th>回执员</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#if pageInfos??>
                                <#list pageInfos.list as myn>
                                    <tr class="border-top-primary">
                                        <td>${myn.orderNumber!}</td>
                                        <td>${myn.xuName!}</td>
                                        <td>${myn.suName!}</td>
                                        <td>${myn.huName!}</td>
                                        <td>${myn.createTime!?string("yyyy-MM-dd HH:mm:ss")}</td>
                                        <td>
                                            <div onclick="updateOrderDetailAjax('${myn.id}','${myn.orderNumber}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                <i class="icon-eye" id="update${myn.id!}" onmousemove="layerTips('订单详情','update${myn.id!}')"></i>
                                            </div>
                                        </td>
                                    </tr>
                                </#list>
                            </#if>
                            <tr class="border-top-primary">
                                <td colspan="15">
                                    <#import "../common/pagebar.ftl" as pagebar>
                                    <@pagebar.pagebar pageInfo=pageInfos actionUrl="/admin/report/orderInfoReport"/>
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
    //执行一个laydate实例
    var laydate = layui.laydate;
    laydate.render({
        elem: '#create-time-start' //指定元素
    });
    laydate.render({
        elem: '#create-time-end' //指定元素
    });
    function updateOrderDetailAjax(id) {
        $.ajax({
            url:"/admin/orderInfo/receiptUpdate",
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
                    area:  ['1160px']   // 长，宽
                });
            }
        })
    }

</script>
</html>



