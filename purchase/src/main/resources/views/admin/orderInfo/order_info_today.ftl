
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="当日分配采购单">
    <#include "../common/head.ftl"/>
    <style>
        .order-list{
            text-align: left;
            font-size: 13px;
        }
        .order-list li{
            background-color: white;
            border-radius: 5px;
            margin: 18px 0px;
        }
        .order-title{
            background-color: #e0e0e0;
            color: #000;
            text-align: left;
            vertical-align: bottom;
        }
        .pure-table {
            border-collapse: collapse;
            border-spacing: 0;
            empty-cells: show;
            border: 1px solid #cbcbcb;
            width: 100%;
            background-color: #FFFFFF;
        }

        .pure-table caption {
            color: #000;
            font: italic 85%/1 arial,sans-serif;
            padding: 1em 0;
            text-align: center;
        }

        .pure-table td,.pure-table th {
            border-left: 1px solid #cbcbcb;
            border-width: 0 0 0 1px;
            font-size: inherit;
            margin: 0;
            overflow: visible;
            padding: .5em 1em;
        }

        .pure-table thead {
            background-color: #e0e0e0;
            color: #000;
            text-align: left;
            vertical-align: bottom;
        }

        .pure-table td {
            background-color: transparent;
        }
        .pure-table-horizontal td,.pure-table-horizontal th {
            border-width: 0 0 1px 0;
            border-bottom: 1px solid #cbcbcb;
        }

        .pure-table-horizontal tbody>tr:last-child>td {
            border-bottom-width: 0;
        }
        table img{
            max-width: 38px;
            /*border-radius: 50%;*/
            margin-right: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="orderInfo">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="table-responsive">
                <#if orderInfoList??&&(orderInfoList?size>0)>
                    <table class="pure-table order-list">
                    <#list orderInfoList as myn>
                        <tr class="order-title">
                            <th>供应商</th>
                            <th>品名</th>
                            <th>单位</th>
                            <th>数量</th>
                            <th>参考单价</th>
                            <th>总价</th>
                        </tr>
                        <#if myn.orderInfoDetailList??>
                            <#list myn.orderInfoDetailList as odl>
                            <tr>
                                <td>
                                    ${odl.goodsInfo.supplierName}
                                </td>
                                <td>
                                    ${odl.goodsInfo.chName}
                                    (${odl.goodsInfo.enName})
                                    <#if odl.goodsInfo.imgUrl??>
                                        <img onclick="myImageOpen(this)" src="${aliyunOos!}${odl.goodsInfo.imgUrl!}"/>
                                    <#else >
                                        <img style="cursor: default" src="/statics/admin/assets/images/default_goods.jpg"/>
                                    </#if>
                                </td>
                                <td>
                                    <#if odl.unitType==1>
                                        ${odl.unit}
                                    <#else >
                                        ${odl.unitSe}
                                    </#if>
                                </td>
                                <td>${odl.number}</td>
                                <td>
                                    <#if odl.unitType==1>
                                        ${odl.price}
                                    <#else >
                                        ${odl.priceSe}
                                    </#if>
                                </td>
                                <td>${odl.totalPrice}</td>
                            </tr>
                            </#list>
                        </#if>
                            <tr style="border-top: 1px solid #cbcbcb;">
                                <td colspan="3">NO：${myn.orderNumber}</td>
                                <td colspan="3" style="text-align: right;border: 0px;">
                                    总数：${myn.sumNumber}
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" >时间：${myn.createTime!?string("yyyy-MM-dd HH:mm:ss")}</td>
                                <td colspan="3" style="text-align: right;border: 0px;">合计：${myn.sumPrice}</td>
                            </tr>
                            <tr style="border-bottom: 1px solid #cbcbcb;">
                                <td colspan="3">
                                    下单员：${myn.xuName}
                                </td>
                                <td colspan="3" style="text-align: right;border: 0px;">
                                    采购员：${myn.suName}
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5" style="padding-bottom: 50px;"></td>
                            </tr>
                    </#list>
                    </table>
                <#else >
                <div style="text-align: center">
                    <h3>当前暂无采购单</h3>
                </div>
                </#if>
                </div>
                <#include "../common/foot.ftl"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>



