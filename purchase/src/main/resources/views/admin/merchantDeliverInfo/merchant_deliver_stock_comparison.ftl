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
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">配送订单库存比对</a>
                </h6>
            </div>
            <div class="panel-collapse">
                <div class="panel-body">
                    <div class="col-md-12" style="padding: 0px;">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>品名</th>
                                    <th>单位</th>
                                    <th>单价</th>
                                    <th>数量</th>
                                    <th>库存</th>
                                    <th>总价</th>
                                    <th>标记</th>
                                </tr>
                            </thead>
                            <tbody>
                                <#if showMerchantOrderInfoDetailList??>
                                    <#list showMerchantOrderInfoDetailList as myn>
                                        <tr <#if (myn.goodsStock lt myn.number)
                                            &&(myn.state==0)>style="color:red;"</#if> mergeOrderDetailId="${myn.mergeOrderDetailId}" state="${myn.state}">
                                            <td>${myn.goodsName!}</td>
                                            <td>${myn.unit!}</td>
                                            <td>${myn.sellPrice!}</td>
                                            <td>${myn.number!}</td>
                                            <td>${myn.goodsStock!}</td>
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
                                                <#if (myn.goodsStock lt myn.number)&&(myn.state==0)>
                                                    <a href="javaScript:void(0)" state="2" class="opt-btn">临采配送</a>
                                                    <a href="javaScript:void(0)" state="3" class="opt-btn">次日配送</a>
                                                    <a href="javaScript:void(0)" state="4" class="opt-btn">取消配送</a>
                                                </#if>
                                                <#if (myn.state==2)>
                                                    <a href="javaScript:void(0)" state="4" class="opt-btn">取消配送</a>
                                                </#if>
                                            </td>
                                        </tr>
                                    </#list>
                                </#if>
                            </tbody>
                            <#if merchantDeliverInfo.state!=4>
                                <tfoot>
                                <tr><td colspan="7"><button id="confirm" class="btn bg-blue btn-block">确认</button></td></tr>
                                </tfoot>
                            </#if>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(".opt-btn").unbind("click").click(function(){
        var thisDom = $(this);
        var state = thisDom.attr("state");
        var parentDom = thisDom.parents("tr");
        parentDom.find("a").removeClass("active");
        thisDom.addClass("active");
        parentDom.attr("state",state);
    });

    $("#confirm").unbind("click").click(function(){
        var goodsList = $("tbody tr[mergeorderdetailid]");
        var optStr = "";
        for(var i = 0;i<goodsList.length;i++){
            var goods = goodsList.eq(i);
            var mergeOrderDetailId = goods.attr("mergeOrderDetailId");
            var state = goods.attr("state");
            if(state==0){
                state = 1;
            }
            optStr += ";"+mergeOrderDetailId+":"+state;
        }
        optStr = optStr.substr(1);
        console.log(optStr);
        $.ajax({
            url:"/admin/merchantDeliverInfo/merchantDeliverInfoConfirm",
            data:{
                id:${merchantDeliverInfo.id},
                optStr:optStr
            },
            headers: {
                'httpType': "JSON",
            },
            type:"POST",
            async:false,
            success:function (data) {
                if(data.statusCode==200){
                    window.location.href = "/admin/merchantDeliverInfo/index";
                }else{
                    layer.alert(data.message);
                }
            }
        });
    });
</script>