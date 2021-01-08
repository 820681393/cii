
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
        <#assign menuType="adminMerchantOrderInfo">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="panel" >
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">查询条件</a>
                        </h6>
                    </div>
                    <form action="/merchants/orderInfo/index" method="post">
                        <div id="accordion-styled-group3" class="panel-collapse">
                            <div class="panel-body">
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-xs-3">
                                        <input type="text"  name="orderNumber" value="${orderInfo.orderNumber!}"class="form-control" placeholder="订单号">
                                    </div>
                                    <div class="col-xs-3">
                                        <select class="form-control" name="billType">
                                            <option value="">结算方式</option>
                                            <option value="1" <#if orderInfo.billType?? &&orderInfo.billType==1>selected="selected"</#if>>现金</option>
                                            <option value="2" <#if orderInfo.billType?? &&orderInfo.billType==2>selected="selected"</#if>>周结算</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-xs-3">
                                        <input id="create-time-start" name="createTimeStart" value="<#if orderInfo.createTimeStart??>${orderInfo.createTimeStart!?string("yyyy-MM-dd HH:mm:ss")}</#if>" type="text" class="form-control" placeholder="开始时间">
                                    </div>
                                    <div class="col-xs-3">
                                        <input id="create-time-end" name="createTimeEnd" value="<#if orderInfo.createTimeEnd??>${c.createTimeEnd!?string("yyyy-MM-dd HH:mm:ss")}</#if>" type="text" class="form-control" placeholder="结束时间">
                                    </div>
                                    <div class="col-xs-3">
                                        <select class="form-control" name="state">
                                            <option value="">订单状态</option>
                                            <option value="1" <#if orderInfo.state?? &&orderInfo.state==1>selected="selected"</#if>>待处理</option>
                                            <option value="2" <#if orderInfo.state?? &&orderInfo.state==2>selected="selected"</#if>>已受理</option>
                                            <option value="3" <#if orderInfo.state?? &&orderInfo.state==3>selected="selected"</#if>>待结算</option>
                                            <option value="4" <#if orderInfo.state?? &&orderInfo.state==4>selected="selected"</#if>>已完成</option>
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
                            订单列表
                        </a>
                        <a class="collapsed" id="mergeBtn" flag="false" onclick="mergeOrderInfo()" style="float: right;margin-left: 15px;">
                            <i class="icon-merge"></i>
                            <span>合并到采购单</span>
                        </a>
                        <a class="collapsed" id="mergeConfirm" onclick="mergeOrderInfoConfirm()" style="float: right;display: none">
                            <i class="icon-checkmark4"></i>
                            确定合并
                        </a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                            <table class="table fixed-table" >
                                <thead>
                                <tr class="border-bottom-danger">
                                    <th>订单号</th>
                                    <th>商品总价</th>
                                    <th>商户名称</th>
                                    <th>商户负责人</th>
                                    <th>结算方式</th>
<#--                                    <th>收货人姓名</th>-->
<#--                                    <th>收货人手机号</th>-->
<#--                                    <th>收货人地址</th>-->
                                    <th>订单状态</th>
                                    <th>所属采购单号</th>
<#--                                    <th>受理员</th>-->
<#--                                    <th>回执员</th>-->
<#--                                    <th>结算员</th>-->
                                    <th>创建时间</th>
                                    <th>回执/查看</th>
                                </tr>
                                </thead>
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
                                        <tr class="border-top-primary" state="${myn.state}" id="${myn.id}" ciid="${myn.ciid!}" carName="${myn.carName!}">
                                            <td>${myn.orderNumber!}</td>
                                            <td>${myn.sumPrice!}</td>
                                            <td>${myn.miName!}</td>
                                            <td>${myn.entrustName!}</td>
                                            <td>
                                                <#if myn.billType==1>
                                                    现金
                                                </#if>
                                                <#if myn.billType==2>
                                                    周结算
                                                </#if>
                                            </td>
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
                                            <td>${myn.oiOrderNumber!}</td>
<#--                                            <td>${myn.suName!}</td>-->
<#--                                            <td>${myn.huName!}</td>-->
<#--                                            <td>${myn.juName!}</td>-->
                                            <td>${(myn.createTime!?string("yyyy-MM-dd HH:mm:ss"))?replace(" ","<br>")}</td>
                                            <td>
                                                <div onclick="updateOrderDetailAjax('${myn.id}','${myn.orderNumber}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                    <i class="<#if myn.state==2>icon-pencil5<#else>icon-eye</#if>" id="update${myn.id!}" onmousemove="layerTips('<#if myn.state==1>回执<#else>查看</#if>','update${myn.id!}')" id="update${myn.id!}','update${myn.id!}')"></i>
                                                </div>
                                            </td>
                                        </tr>
                                    </#list>
                                </#if>
                                <tr class="border-top-primary">
                                    <td colspan="15">
                                        <#import "../common/pagebar.ftl" as pagebar>
                                        <@pagebar.pagebar pageInfo=pageInfos actionUrl="/merchants/orderInfo/index"/>
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
    function mergeOrderInfo(){
        if($("tr[state=1]").length<1){
            layer.msg("暂无可合并的订单");
            return;
        }
        var mergeBtn = $("#mergeBtn");
        if(mergeBtn.attr("flag")=="false"){
            mergeBtn.find("span").text("取消合并");
            mergeBtn.attr("flag","true");
            layer.msg("请选择需要合并的商户订单");
            $("tr[state=1]").addClass("merge").attr("flag","true");
            $("#mergeConfirm").show();
            $("tr[state=1]").unbind("click").click(function(){
                var flag = $(this).attr("flag");
                if(flag=="true"){
                    $(this).attr("flag","false").removeClass("merge");
                }else{
                    $(this).attr("flag","true").addClass("merge");
                }
            });
        }else{
            mergeBtn.find("span").text("合并到采购单");
            mergeBtn.attr("flag","false");
            layer.msg("取消合并");
            $("tr[state=1]").removeClass("merge").removeAttr("flag");
            $("#mergeConfirm").hide();
            $("tr[state=1]").unbind("click");
        }
    }


    function mergeOrderInfoConfirm(){
        var mergeOrderList = $("tr[flag=true]");
        // if(mergeOrderList.length<2){
        //     layer.alert("必须选择2个或以上订单进行合并");
        //     return;
        // }
        layer.open({
            type: 1,
            skin: 'layui-layer-demo', //样式类名
            closeBtn: 0, //不显示关闭按钮
            anim: 2,
            shadeClose: true, //开启遮罩关闭
            content: `<div id="merge-sel" style="text-align: center;">
                        <input id="orderNumber" type="text" class="form-control" placeholder="请输入要合并的采购单号">
                        <div style="margin: 15px 0px;text-align: center">
                            <button id="confirmMerge" class="btn btn-primary" style="width: 158px;display: inline-block">确定</button>
                        </div>
                    </div>`,
            area:  ['500px']
        });
        $("#confirmMerge").unbind("click").click(function(){
            var orderNumber = $("#orderNumber").val();
            if(!orderNumber){
                layer.alert("请输入要合并的采购单号");
                return
            }
            var orderIds = "";
            for(var i = 0;i<mergeOrderList.length;i++){
                var mergeOrder = mergeOrderList.eq(i);
                var id = mergeOrder.attr("id");
                orderIds += orderIds==""?id:","+id;
            }
            console.log(orderIds);
            $.ajax({
                url:"/merchants/orderInfo/orderInfoMerge",
                data:{
                    orderIds:orderIds,
                    orderNumber:orderNumber
                },
                headers: {
                    'httpType': "JSON",
                },
                type:"POST",
                async:false,
                success:function (data) {
                    if(data.statusCode==200){
                        window.location.href = "/merchants/orderInfo/index";
                    }else if(data.statusCode==203){
                        layer.alert("权限不足")
                    }else{
                        layer.alert(data.message);
                    }
                }
            });
        });
    }

    function updateOrderDetailAjax(id) {
        $.ajax({
            url:"/merchants/orderInfo/receiptUpdate",
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
                    area:  ['90%','588px']   // 长，宽
                });
            }
        })
    }

</script>
</html>



