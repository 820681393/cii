<div class="content" style="padding: 0px;">
    <div class="col-md-12" style="padding: 0px;">
        <div class="panel" style="margin: 0px;">
            <div class="panel-heading bg-primary">
                <h6 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">${goodsStockInfo.typeName!}信息</a>
                </h6>
            </div>
            <div class="panel-collapse">
                <div class="panel-body">
                    <div class="col-md-4" style="padding: 0px;border-right: 1px solid #ccc">
                        <table class="table">
                            <tbody>
                                <tr>
                                    <td style="border-top: 0px;">单号：</td>
                                    <td style="border-top: 0px;">${goodsStockInfo.orderNumber!}</td>
                                </tr>
                                <tr>
                                    <td>数量：</td>
                                    <td>${goodsStockInfo.stockNumber!}</td>
                                </tr>
                                <tr>
                                    <td>总价：</td>
                                    <td>${goodsStockInfo.totalPrice!}</td>
                                </tr>
                                <tr>
                                    <td>类型：</td>
                                    <td>${goodsStockInfo.typeName!}</td>
                                </tr>
                                <tr>
                                    <td>时间：</td>
                                    <td>${goodsStockInfo.createTime!?string("yyyy-MM-dd HH:mm:ss")}</td>
                                </tr>
                                <tr>
                                    <td>操作人：</td>
                                    <td>${goodsStockInfo.adminName!}</td>
                                </tr>
                                <tr>
                                    <td>备注：</td>
                                    <td>${goodsStockInfo.remark!}</td>
                                </tr>
                                <tr>
                                    <td>单据：</td>
                                    <td>
                                        <#if goodsStockInfo.image??>
                                            <img style="max-width: 100px;max-height: 135px;cursor: pointer;" onclick="myImageOpen(this)" src="${aliyunOos!}${goodsStockInfo.image!}" οnerrοr="this.οnerrοr='';src='/statics/admin/assets/images/default_goods.jpg'"/>
                                            <#else >
                                            无
                                        </#if>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-8" style="padding: 0px;border-left: 1px solid #ccc;position: relative;left: -1px;">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>品名</th>
                                    <th>单位</th>
                                    <th>单价</th>
<#--                                    <th><#if goodsStockInfo.type==1>入库</#if><#if goodsStockInfo.type==2>出库</#if><#if goodsStockInfo.type==3>盘点</#if>前数量</th>-->
                                    <th><#if goodsStockInfo.type==1>入库</#if><#if goodsStockInfo.type==2>出库</#if><#if goodsStockInfo.type==3>盘点</#if>数量</th>
                                    <th>总价</th>
                                </tr>
                            </thead>
                            <tbody>
                                <#if goodsStockInfoDetailList??>
                                    <#list goodsStockInfoDetailList as myn>
                                        <tr>
                                            <td>${myn.goodsName!}</td>
                                            <td>${myn.unit!}</td>
                                            <td>${myn.price!}</td>
<#--                                            <td>${myn.beforeNumber!}</td>-->
                                            <td>${myn.number!}</td>
                                            <td>${myn.totalPrice!}</td>
                                        </tr>
                                    </#list>
                                </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>