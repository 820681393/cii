
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="商户订单列表">
    <#include "../common/head.ftl"/>
    <style>
        .table-responsive .table img{
            max-width: 38px;
            /*border-radius: 50%;*/
            margin-right: 5px;
            cursor: pointer;
        }
        .merge{
            background-color: #bde2ff;
        }
        tr[flag]{
            cursor: pointer;
        }
    </style>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="merchantOrderInfo">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="panel" >
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">查询条件</a>
                        </h6>
                    </div>
                    <form action="/merchants/orderInfo/merchantOrderIndex" method="post">
                        <div id="accordion-styled-group3" class="panel-collapse">
                            <div class="panel-body">
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-xs-6">
                                        <input type="text"  name="orderNumber" value="${orderInfo.orderNumber!}"class="form-control" placeholder="订单号">
                                    </div>
                                    <div class="col-xs-6">
                                        <select class="form-control" name="state">
                                            <option value="">订单状态</option>
                                            <option value="1" <#if orderInfo.state?? &&orderInfo.state==1>selected="selected"</#if>>待处理</option>
                                            <option value="2" <#if orderInfo.state?? &&orderInfo.state==2>selected="selected"</#if>>已受理</option>
                                            <option value="3" <#if orderInfo.state?? &&orderInfo.state==3>selected="selected"</#if>>待结算</option>
                                            <option value="4" <#if orderInfo.state?? &&orderInfo.state==4>selected="selected"</#if>>已完成</option>
                                        </select>
                                    </div>
<#--                                    <div class="col-xs-3">-->
<#--                                        <select class="form-control" name="billType">-->
<#--                                            <option value="">结算方式</option>-->
<#--                                            <option value="1" <#if orderInfo.billType?? &&orderInfo.billType==1>selected="selected"</#if>>现金</option>-->
<#--                                            <option value="2" <#if orderInfo.billType?? &&orderInfo.billType==2>selected="selected"</#if>>周结算</option>-->
<#--                                        </select>-->
<#--                                    </div>-->
                                </div>
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-xs-6">
                                        <input id="create-time-start" name="createTimeStart" value="<#if orderInfo.createTimeStart??>${orderInfo.createTimeStart!?string("yyyy-MM-dd HH:mm:ss")}</#if>" type="text" class="form-control" placeholder="开始时间">
                                    </div>
                                    <div class="col-xs-6">
                                        <input id="create-time-end" name="createTimeEnd" value="<#if orderInfo.createTimeEnd??>${c.createTimeEnd!?string("yyyy-MM-dd HH:mm:ss")}</#if>" type="text" class="form-control" placeholder="结束时间">
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
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">
                            订单列表
                        </a>
<#--                        <a class="collapsed" id="mergeBtn" flag="false" onclick="mergeOrderInfo()" style="float: right;margin-left: 15px;">-->
<#--                            <i class="icon-merge"></i>-->
<#--                            <span>合并到采购单</span>-->
<#--                        </a>-->
<#--                        <a class="collapsed" id="mergeConfirm" onclick="mergeOrderInfoConfirm()" style="float: right;display: none">-->
<#--                            <i class="icon-checkmark4"></i>-->
<#--                            确定合并-->
<#--                        </a>-->
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                            <table class="table" >
                                <thead>
                                <tr class="border-bottom-danger">
                                    <th>订单号</th>
                                    <th>商品数量</th>
                                    <th>商品总价</th>
<#--                                    <th>商户名称</th>-->
<#--                                    <th>商户负责人</th>-->
<#--                                    <th>结算方式</th>-->
<#--                                    <th>收货人姓名</th>-->
<#--                                    <th>收货人手机号</th>-->
<#--                                    <th>收货人地址</th>-->
                                    <th>订单状态</th>
<#--                                    <th>所属采购单号</th>-->
<#--                                    <th>受理员</th>-->
<#--                                    <th>回执员</th>-->
<#--                                    <th>结算员</th>-->
                                    <th>创建时间</th>
                                    <th>查看详细</th>
                                </tr>
                                </thead>
<#--                                <col style="width: 130px" />-->
<#--                                <col style="width: 20%" />-->
<#--                                <col style="width: 20%" />-->
<#--                                <col style="width: 20%" />-->
<#--                                <col style="width: 20%" />-->
<#--                                <col style="width: 20%" />-->
<#--                                <col style="width: 20%" />-->
<#--                                <col style="width: 20%" />-->
<#--                                <col style="width: 20%" />-->
                                <tbody>
                                <#if pageInfos??>
                                    <#list pageInfos.list as myn>
                                        <tr class="border-top-primary" state="${myn.state}" id="${myn.id}" ciid="${myn.ciid!}" carName="${myn.carName!}">
                                            <td>${myn.orderNumber!}</td>
                                            <td>${myn.sumNumber!}</td>
                                            <td>${myn.sumPrice!}</td>
<#--                                            <td>${myn.miName!}</td>-->
<#--                                            <td>${myn.entrustName!}</td>-->
<#--                                            <td>-->
<#--                                                <#if myn.billType==1>-->
<#--                                                    现金-->
<#--                                                </#if>-->
<#--                                                <#if myn.billType==2>-->
<#--                                                    周结算-->
<#--                                                </#if>-->
<#--                                            </td>-->
<#--                                            <td>${myn.receiveName!}</td>-->
<#--                                            <td>${myn.receiveTel!}</td>-->
<#--                                            <td>${myn.receiveAddress!}</td>-->
                                            <td>
                                                <#if myn.state==1>
                                                    待处理
                                                </#if>
                                                <#if myn.state==2>
                                                    已受理
                                                </#if>
                                                <#if myn.state==3>
                                                    待结算
                                                </#if>
                                                <#if myn.state==4>
                                                    已结算
                                                </#if>
                                            </td>
<#--                                            <td>${myn.oiOrderNumber!}</td>-->
<#--                                            <td>${myn.suName!}</td>-->
<#--                                            <td>${myn.huName!}</td>-->
<#--                                            <td>${myn.juName!}</td>-->
                                            <td>${(myn.createTime!?string("yyyy-MM-dd HH:mm:ss"))?replace(" ","<br>")}</td>
                                            <td>
                                                <div onclick="queryOrderDetailAjax('${myn.id}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                    <i class="icon-eye" id="query${myn.id!}" onmousemove="layerTips('查看','query${myn.id!}')"></i>
                                                </div>
                                            </td>
                                        </tr>
                                    </#list>
                                </#if>
                                <tr class="border-top-primary">
                                    <td colspan="15">
                                        <#import "../common/pagebar.ftl" as pagebar>
                                        <@pagebar.pagebar pageInfo=pageInfos actionUrl="/merchants/orderInfo/merchantOrderIndex"/>
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

    function queryOrderDetailAjax(id) {
        var popW = "95%";
        if(IsPC()){
            popW = "588px";
        }
        $.ajax({
            url:"/merchants/orderInfo/onOrderInfoDetail",
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
                    area:  [popW,"546px"]   // 长，宽
                });
            }
        })
    }

</script>
</html>



