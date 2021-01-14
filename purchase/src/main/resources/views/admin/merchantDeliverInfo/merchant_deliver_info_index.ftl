
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="商户配送单列表">
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
        <#assign menuType="merchantDeliverInfo">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="panel" >
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">查询条件</a>
                        </h6>
                    </div>
                    <form action="/admin/merchantDeliverInfo/index" method="post">
                        <div id="accordion-styled-group3" class="panel-collapse">
                            <div class="panel-body">
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-xs-3">
                                        <input type="text"  name="orderNumber" value="${merchantDeliverInfo.orderNumber!}"class="form-control" placeholder="订单号">
                                    </div>
                                    <div class="col-xs-3">
                                        <select class="form-control" name="settleType">
                                            <option value="">结算方式</option>
                                            <option value="1" <#if merchantDeliverInfo.settleType?? &&merchantDeliverInfo.settleType==1>selected="selected"</#if>>现金</option>
                                            <option value="2" <#if merchantDeliverInfo.settleType?? &&merchantDeliverInfo.settleType==2>selected="selected"</#if>>周结算</option>
                                            <option value="3" <#if merchantDeliverInfo.settleType?? &&merchantDeliverInfo.settleType==3>selected="selected"</#if>>月结算</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-xs-3">
                                        <input id="create-time-start" name="createTimeStart" value="<#if merchantDeliverInfo.createTimeStart??>${merchantDeliverInfo.createTimeStart!?string("yyyy-MM-dd HH:mm:ss")}</#if>" type="text" class="form-control" placeholder="开始时间">
                                    </div>
                                    <div class="col-xs-3">
                                        <input id="create-time-end" name="createTimeEnd" value="<#if merchantDeliverInfo.createTimeEnd??>${merchantDeliverInfo.createTimeEnd!?string("yyyy-MM-dd HH:mm:ss")}</#if>" type="text" class="form-control" placeholder="结束时间">
                                    </div>
                                    <div class="col-xs-3">
                                        <select class="form-control" name="state">
                                            <option value="">订单状态</option>
                                            <option value="1" <#if merchantDeliverInfo.state?? &&merchantDeliverInfo.state==1>selected="selected"</#if>>未处理</option>
                                            <option value="2" <#if merchantDeliverInfo.state?? &&merchantDeliverInfo.state==2>selected="selected"</#if>>待配送</option>
                                            <option value="3" <#if merchantDeliverInfo.state?? &&merchantDeliverInfo.state==3>selected="selected"</#if>>未结算</option>
                                            <option value="4" <#if merchantDeliverInfo.state?? &&merchantDeliverInfo.state==4>selected="selected"</#if>>已结算</option>
                                        </select>
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
                            配送订单列表
                        </a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                            <table class="table fixed-table" >
                                <thead>
                                <tr class="border-bottom-danger">
                                    <th>商户名称</th>
                                    <th>配送单号</th>
                                    <th>订单金额</th>
                                    <th>查看库存</th>
                                    <th>配送差额</th>
                                    <th>实际配送金额</th>
                                    <th>打印配送单</th>
                                    <th>结算方式</th>
                                    <th>签收状态</th>
                                    <th>时间</th>
                                </tr>
                                </thead>
                                <col style="width: 130px" />
                                <col style="width: 130px" />
                                <col style="width: 20%" />
                                <col style="width: 20%" />
                                <col style="width: 20%" />
                                <col style="width: 20%" />
                                <col style="width: 20%" />
                                <col style="width: 20%" />
                                <col style="width: 20%" />
                                <col style="width: 20%" />
                                <tbody>
                                <#if pageInfos??>
                                    <#list pageInfos.list as myn>
                                        <tr class="border-top-primary">
                                            <td>${myn.merchantName!}</td>
                                            <td>${myn.orderNumber!}</td>
                                            <td>${myn.sumPrice!}</td>
                                            <td>
                                                <a onclick="merchantDeliverStockComparison('${myn.id!}')" style="color:blue;float: left;margin-left: 10px;">
                                                    <i class="<#if myn.state==1>icon-pencil3<#else >icon-eye</#if>" id="cp${myn.id!}" onmousemove="layerTips('查看库存','cp${myn.id!}')"></i>
                                                </a>
                                            </td>
                                            <td>${myn.deliverPriceDiff!}</td>
                                            <td>${myn.realDeliverPrice!}</td>
                                            <td>
                                                <a onclick="
                                                <#if myn.state==1>
                                                        layer.alert('请先确定库存')
                                                </#if>
                                                <#if myn.state==2||myn.state==3>
                                                        merchantDeliverPrintInfo('${myn.id!}')
                                                </#if>
                                                <#if myn.state==4>
                                                        layer.alert('订单已结算，不可打印')
                                                </#if>" style="color:blue;float: left;margin-left: 10px;">
                                                    <i class="icon-printer2" id="role${myn.id!}" onmousemove="layerTips('打印配送单','role${myn.id!}')"></i>
                                                </a>
                                            </td>
                                            <td>
                                                <#if myn.settleType==1>
                                                    现金
                                                </#if>
                                                <#if myn.settleType==2>
                                                    周结算
                                                </#if>
                                                <#if myn.settleType==3>
                                                    月结算
                                                </#if>
                                            </td>
                                            <td>
                                                <#if myn.state==1>
                                                    未处理
                                                </#if>
                                                <#if myn.state==2>
                                                    待配送
                                                </#if>
                                                <#if myn.state==3>
                                                    未结算
                                                    <a onclick="merchantDeliverSign('${myn.id!}')" style="color:blue;">
                                                        <i class="icon-check" id="ck${myn.id!}" onmousemove="layerTips('签收回执','ck${myn.id!}')"></i>
                                                    </a>
                                                </#if>
                                                <#if myn.state==4>
                                                    已结算
                                                    <#if myn.image??>
                                                        <img onclick="myImageOpen(this)" width="50px" src="${aliyunOos!}${myn.image!}"/>
                                                    </#if>
                                                </#if>
                                            </td>
                                            <td>${(myn.createTime!?string("yyyy-MM-dd HH:mm:ss"))?replace(" ","<br>")}</td>
                                        </tr>
                                    </#list>
                                </#if>
                                <tr class="border-top-primary">
                                    <td colspan="15">
                                        <#import "../common/pagebar.ftl" as pagebar>
                                        <@pagebar.pagebar pageInfo=pageInfos actionUrl="/admin/merchantDeliverInfo/index"/>
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

    function merchantDeliverStockComparison(id){
        $.ajax({
            url:"/admin/merchantDeliverInfo/merchantDeliverStockComparison",
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
                    area:["988px","572px"]
                });
            }
        })
    }

    function merchantDeliverPrintInfo(id){
        $.ajax({
            url:"/admin/merchantDeliverInfo/merchantDeliverPrintInfo",
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
                    area:["988px","572px"]
                });
            }
        })
    }

    function merchantDeliverSign(id){
        $.ajax({
            url:"/admin/merchantDeliverInfo/merchantDeliverSign",
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
                    area:["500px"]
                });
            }
        })
    }
</script>
</html>



