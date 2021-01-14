<script language="javascript" src="/statics/admin/assets/js/plugins/lodop/print.js"></script>
<style>
    .active{
        color: red!important;
    }
    .opt-btn{
        border: 1px solid;
        color: #107bff;
        font-size: 13px;
        border-radius: 5px;
        padding: 3px 5px;
    }
</style>
<div class="content" style="padding: 0px;">
    <div class="col-md-12" style="padding: 0px;">
        <div class="panel" style="margin: 0px;">
            <div class="panel-heading bg-primary">
                <h6 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">配送订单信息打印</a>
                </h6>
            </div>
            <div class="panel-collapse">
                <div class="panel-body">
                    <div class="col-md-12" style="padding: 0px;" id="print-content">
                        <table class="table">
                            <tr>
                                <td colspan="2" style="text-align: center">
                                    <h3>CII配送验收单</h3>
                                </td>
                            </tr>
                            <tr>
                                <td>商户名称：${merchantDeliverInfo.merchantName!}</td>
                                <td>单据编号：${merchantDeliverInfo.orderNumber!}</td>
                            </tr>
                        </table>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>品名</th>
                                    <th>单位</th>
                                    <th>单价</th>
                                    <th>数量</th>
<#--                                    <th>库存</th>-->
                                    <th>总价</th>
                                    <th>标记</th>
                                </tr>
                            </thead>
                            <tbody>
                                <#assign totalNumber=0>
                                <#if showMerchantOrderInfoDetailList??>
                                    <#list showMerchantOrderInfoDetailList as myn>
                                        <#if myn.state!=4>
                                            <#assign totalNumber+=myn.number>
                                        </#if>
                                        <tr mergeOrderDetailId="${myn.mergeOrderDetailId}" state="${myn.state}">
                                            <td>${myn.goodsName!}</td>
                                            <td>${myn.unit!}</td>
                                            <td>${myn.sellPrice!}</td>
                                            <td>${myn.number!}</td>
<#--                                            <td>${myn.goodsStock!}</td>-->
                                            <td>${myn.totalPrice!}</td>
                                            <td>
                                                <#if myn.state??>
                                                    <#if myn.state==1>
                                                        正常配送
                                                    </#if>
                                                    <#if myn.state==2>
                                                        临采配送
                                                    </#if>
                                                    <#if myn.state==3>
                                                        次日配送
                                                    </#if>
                                                    <#if myn.state==4>
                                                        取消配送
                                                    </#if>
                                                </#if>
                                            </td>
                                        </tr>
                                    </#list>
                                </#if>
                                <tr>
                                    <td colspan="3">合计：</td>
                                    <td>${totalNumber}</td>
                                    <td>${merchantDeliverInfo.realDeliverPrice}</td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="table">
                            <tr>
                                <td>收货地址：${merchantInfoAddress.address!} ${merchantInfoAddress.tel!} ${merchantInfoAddress.name!}</td>
                                <td>送货人：</td>
                                <td>客户验收人：</td>
                            </tr>
                        </table>
                    </div>
                    <button id="print" class="btn bg-blue btn-block">打印</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#print").unbind("click").click(function(){
        var state = ${merchantDeliverInfo.state};
        if(state==2){
            layer.confirm('确定打印配送单并出库商品吗？', {
                btn: ['确定','取消']
            }, function(index){
                $.ajax({
                    url:"/admin/merchantDeliverInfo/merchantDeliverPrintInfoConfirm",
                    data:{
                        id:${merchantDeliverInfo.id},
                    },
                    headers: {
                        'httpType': "JSON",
                    },
                    type:"POST",
                    async:false,
                    success:function (data) {
                        if(data.statusCode==200){
                            $("#print-content").print({
                                globalStyles:true,//是否包含父文档的样式，默认为true
                                mediaPrint:false,//是否包含media='print'的链接标签。会被globalStyles选项覆盖，默认为false
                                stylesheet:null,//外部样式表的URL地址，默认为null

                                noPrintSelector:".no-print",//不想打印的元素的jQuery选择器，默认为".no-print"
                                iframe:true,//是否使用一个iframe来替代打印表单的弹出窗口，true为在本页面进行打印，false就是说新开一个页面打印，默认为true
                                append:null,//将内容添加到打印内容的后面
                                prepend:null,//将内容添加到打印内容的前面，可以用来作为要打印内容
                                deferred:$.Deferred().done(function() {
                                    window.location.href = "/admin/merchantDeliverInfo/index";
                                })
                            });
                        }else{
                            layer.alert(data.message);
                        }
                    }
                });
            });
        }else{
            $("#print-content").print({
                globalStyles:true,//是否包含父文档的样式，默认为true
                mediaPrint:false,//是否包含media='print'的链接标签。会被globalStyles选项覆盖，默认为false
                stylesheet:null,//外部样式表的URL地址，默认为null

                noPrintSelector:".no-print",//不想打印的元素的jQuery选择器，默认为".no-print"
                iframe:true,//是否使用一个iframe来替代打印表单的弹出窗口，true为在本页面进行打印，false就是说新开一个页面打印，默认为true
                append:null,//将内容添加到打印内容的后面
                prepend:null,//将内容添加到打印内容的前面，可以用来作为要打印内容
                deferred:$.Deferred().done(function() {
                    layer.closeAll();
                })
            });
        }

    });
</script>