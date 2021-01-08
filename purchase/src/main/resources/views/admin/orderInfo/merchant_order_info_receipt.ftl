<script language="javascript" src="/statics/admin/assets/js/plugins/lodop/print.js"></script>
<style>
    .goods-info input{
        width: 58px;
        border: 1px solid #b5b3b5;
        border-radius: 3px;
        height: 20px;
        text-align: center;
    }
    .img-ct{
        display: inline-block;
        padding: 5px;
        cursor: pointer;
    }
    .img-ct img{
        max-width: 100px;
        cursor: pointer;
    }
    .pure-table {
        display: none;
    }
</style>
<#assign totalRealPrice=0>
    <div class="content" style="padding: 0px;">
        <div class="col-md-12" style="padding: 0px;">
            <div class="panel" style="margin: 0px;width: 100%;font-size: 13px;">
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">订单信息：${orderInfo.orderNumber}</a>
                    </h6>
                </div>
                <div class="panel-collapse">
                    <div class="panel-body">
                        <table class="table">
                            <thead>
                                <tr class="border-bottom-danger">
                                    <th>供应商</th>
                                    <th>名称</th>
                                    <th>参考价格</th>
                                    <th>单位</th>
                                    <th>实采单价</th>
                                    <th>实采单位</th>
                                    <th>实采数量</th>
                                    <th>实采总价</th>
                                    <th>利润率</th>
                                    <th>额外费用</th>
                                    <th>零售价</th>
                                    <th>是否完成(Y/N)</th>
                                    <th>备注</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#if orderInfoDetails??>
                                <#list orderInfoDetails as myn>
                                    <#if myn.realTotalPrice??>
                                        <#assign totalRealPrice = totalRealPrice + myn.realTotalPrice>
                                    </#if>
                                    <tr id="${myn.id}" price="${myn.price!}" price-se="${myn.priceSe!}" unit="${myn.unit!}" unit-se="${myn.unitSe!}" unit-type="${myn.unitType!}" class="border-top-primary goods-info">
                                        <td>
                                            ${myn.supplierName}
                                        </td>
                                        <td class="goods-name">
                                            <#if myn.goodsInfo??>
                                                ${myn.goodsInfo.chName}
                                                <br/>
                                                (${myn.goodsInfo.enName})
                                            </#if>
                                        </td>
                                        <td>
                                            <#if myn.unitType==1>
                                                <span class="price">${myn.price!}<span>
                                            <#else >
                                                <span class="price">${myn.priceSe!}<span>
                                            </#if>
                                        </td>
                                        <td>
                                            <#if myn.unitType==1>
                                                <span class="unit">${myn.unit!}</span>
                                                <select class="unit-type-sel" data-id="${myn.id}" <#if orderInfo.state==3||orderInfo.state==4>disabled</#if>>
                                                    <option value="1" selected="selected">主</option>
                                                    <option value="2">辅</option>
                                                </select>
                                            <#else >
                                            <span class="unit">${myn.unitSe!}</span>
                                                <select class="unit-type-sel" data-id="${myn.id}" <#if orderInfo.state==3||orderInfo.state==4>disabled</#if>>
                                                    <option value="1">主</option>
                                                    <option value="2" selected="selected">辅</option>
                                                </select>
                                            </#if>
                                        </td>
                                        <td>
                                            <#if orderInfo.state==1||orderInfo.state==2>
                                                <input type="number" class="realPrice" value="<#if myn.unitType==1>${myn.price}<#else>${myn.priceSe}</#if>">
                                                <#else>
                                                    ${myn.realPrice!}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if orderInfo.state==1||orderInfo.state==2>
<#--                                                <input type="text" class="realUnit" value="${myn.unit}">-->
                                                <select class="realUnit">
                                                    <#if unitInfoList??>
                                                        <#list unitInfoList as uil>
                                                            <option value="${uil.name!}" <#if myn.unit??&&myn.unit==uil.name!>selected="selected"</#if>>${uil.name!}</option>
                                                        </#list>
                                                    </#if>
                                                </select>
                                                <#else>
                                                ${myn.realUnit!}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if orderInfo.state==1||orderInfo.state==2>
                                                <input type="number" class="realNumber" value="${myn.number}">
                                                <#else>
                                                ${myn.realNumber!}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if orderInfo.state==1||orderInfo.state==2>
                                                <input type="number" class="realTotalPrice" value="${myn.totalPrice}">
                                            <#else>
                                                ${myn.realTotalPrice!}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if orderInfo.state==1||orderInfo.state==2>
                                                <input type="number" class="percentage" value="${myn.percentage!}" disabled>
                                            <#else>
                                                ${myn.percentage!}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if orderInfo.state==1||orderInfo.state==2>
                                                <input type="number" class="extraCosts" value="${myn.extraCosts!}" disabled>
                                            <#else>
                                                ${myn.extraCosts!}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if orderInfo.state==1||orderInfo.state==2>
                                                <input type="number" class="retailPrice" value="${myn.retailPrice!}" disabled>
                                            <#else>
                                                ${myn.retailPrice!}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if orderInfo.state==1||orderInfo.state==2>
                                                <select class="state-sel">
                                                    <option value="Y">Y</option>
                                                    <option value="N">N</option>
                                                </select>
                                            <#else >
                                                ${myn.state!}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if orderInfo.state==1||orderInfo.state==2>
                                                <textarea class="remark"></textarea>
                                            <#else >
                                                ${myn.remark!}
                                            </#if>
                                        </td>
                                    </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
                        <div style="margin: 10px 0px">
                            <table class="table">
                                <tr>
                                    <td>
                                        回扣方式：${rebateType!}
                                    </td>
                                    <td>
                                        回扣金额：${rebateAmount!}
                                    </td>
                                </tr>
                                <tr>
                                    <td>收货人姓名：${orderInfo.receiveName!}</td>
                                    <td>收货人电话：${orderInfo.receiveTel!}</td>
                                    <td>收货地址：${orderInfo.receiveAddress!}</td>
                                </tr>
                            </table>
                        </div>
                            <#if orderInfo.state==2>
                                <div style="margin: 10px 0px">
                                    回执图片：
                                    <input id="file" type="file" name="file" class="form-control"  placeholder="回执图片" multiple style="width: 200px;display: inline-block">
                                </div>
                                <div style="text-align: center;">
                                    <button id="submit-receipt" class="btn bg-blue" style="width: 35%">完成录入</button>
                                    <button id="print-order" class="btn bg-blue" style="width: 35%">打印结账单</button>
                                </div>
                            </#if>
                            <#if orderInfo.state==3||orderInfo.state==4>
<#--                                <div id="order-summary">-->
<#--                                    <table class="table">-->
<#--                                        <tr>-->
<#--                                            <td>本次采购金额：${totalRealPrice}</td>-->
<#--                                            <td colspan="2">票据数量：${orderInfoImagesList?size}</td>-->
<#--                                        </tr>-->
<#--                                        <tr>-->
<#--                                            <td>下单员：${orderInfo.xuName}</td>-->
<#--                                            <td>采购员：${orderInfo.suName}</td>-->
<#--                                            <td>回执员：${orderInfo.huName}</td>-->
<#--                                        </tr>-->
<#--                                        <tr>-->
<#--                                            <td>采购时间：${orderInfo.createTime!?string("yyyy-MM-dd HH:mm:ss")}</td>-->
<#--                                            <td colspan="2">回执时间：${orderInfo.successTime!?string("yyyy-MM-dd HH:mm:ss")}</td>-->
<#--                                        </tr>-->
<#--                                    </table>-->
<#--                                </div>-->
                                <hr>
                                <#if orderInfoImagesList??>
                                    <#list orderInfoImagesList as myn>
                                        <div class="img-ct">
                                            <img src="${aliyunOos}${myn.imageName}" onclick="myImageOpen(this)">
                                        </div>
                                    </#list>
                                </#if>
                            </#if>
                        <div id="print-content" class="pure-table">
                            <table  style="
                                            border-collapse: collapse;
                                            border-spacing: 0;
                                            empty-cells: show;
                                            border: 1px solid #cbcbcb;
                                            width: 100%;text-align: center">
                                <thead>
                                <th colspan="5" style="text-align: center;font-size: 18px;">结账单</th>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                            <table style="
                                    position: fixed;
                                    bottom: 0;
                                    border-collapse: collapse;
                                    border-spacing: 0;
                                    empty-cells: show;
                                    border-top: 1px solid #cbcbcb;
                                    width: 100%;
                                    ">
                                <tfoot style="border-top: 1px solid #cbcbcb;">
                                </tfoot>
                            </table>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script>
    function sumTotalPrice(targetDom){
        var realPrice = targetDom.find(".realPrice").val();
        var realNumber = targetDom.find(".realNumber").val();
        realPrice = parseFloat(realPrice);
        realNumber = parseFloat(realNumber);
        var realTotalPrice = realPrice*realNumber;
        targetDom.find(".realTotalPrice").val(realTotalPrice);
        var retailPrice = Number(realTotalPrice);
        var percentage = targetDom.find(".percentage").val();
        var extraCosts = targetDom.find(".extraCosts").val();
        if(percentage&&percentage>0){
            percentage = Number(percentage);
            retailPrice = retailPrice+(retailPrice*percentage);
        }
        if(extraCosts&&extraCosts>0){
            extraCosts = Number(extraCosts);
            retailPrice = retailPrice+extraCosts;
        }
        retailPrice = retailPrice.toFixed(2);
        targetDom.find(".retailPrice").val(retailPrice);

    }
    $('.realPrice,.realNumber').bind('input propertychange', function() {
        sumTotalPrice($(this).parents("tr"));
    });
    $(".state-sel").change(function(){
        var parentDom = $(this).parents("tr");
        var state = $(this).val();
        if(state=="N"){
            parentDom.find(".realPrice,.realNumber,.realTotalPrice").val(0);
            sumTotalPrice(parentDom);
        }
    });
    $(".unit-type-sel").change(function() {
        var thisDom = $(this);
        var dataId = thisDom.attr("data-id");
        var unitType = thisDom.val();
        var parentDom = $("#"+dataId);
        var price = parentDom.attr("price");
        var priceSe = parentDom.attr("price-se");
        var unit = parentDom.attr("unit");
        var unitSe = parentDom.attr("unit-se");
        if(unitType==1){
            parentDom.find(".price").text(price);
            parentDom.find(".unit").text(unit);
            parentDom.find(".realPrice").val(price);
            parentDom.find(".realUnit").val(unit);
        }else{
            parentDom.find(".price").text(priceSe);
            parentDom.find(".unit").text(unitSe);
            parentDom.find(".realPrice").val(priceSe);
            parentDom.find(".realUnit").val(unitSe);
        }
        sumTotalPrice(parentDom);
    });

    function getOrderDetailArr(){
        var orderInfoDetails = [];
        var goodsInfos = $(".goods-info");
        for(var i = 0;i<goodsInfos.length;i++){
            var giDom = goodsInfos.eq(i);
            var id = giDom.attr("id");
            var realPrice = giDom.find(".realPrice").val();
            var realUnit = giDom.find(".realUnit").val();
            var realNumber = giDom.find(".realNumber").val();
            var realTotalPrice = giDom.find(".realTotalPrice").val();
            var state = giDom.find(".state-sel").val();
            var remark = giDom.find(".remark").val();
            var unitType = giDom.find(".unit-type-sel").val();
            var retailPrice = giDom.find(".retailPrice").val();
            var oidObj = {};
            oidObj["id"] = id;
            oidObj["realPrice"] = realPrice
            oidObj["realUnit"] = realUnit;
            oidObj["realNumber"] = realNumber;
            oidObj["realTotalPrice"] = realTotalPrice;
            oidObj["state"] = state;
            oidObj["remark"] = remark;
            oidObj["unitType"] = unitType;
            oidObj["retailPrice"] = retailPrice;
            orderInfoDetails.push(oidObj);
        }
        return orderInfoDetails;
    }

    $("#submit-receipt").unbind("click").click(function(){
        var file = $("#file");
        if(!file.val()) {
            layer.alert("请上传回执图片");
            return;
        }
        layer.confirm('确定回执录入信息吗？', {
            btn: ['确定', '取消']
        }, function(index){
            submitReceipt();
        });

    });
    $("#print-order").unbind("click").click(function(){
        printOrder("orderTime","orderNo",function(){
        })
    });


    function printOrder(orderTime,orderNo,callBack) {
        var goodsInfos = $(".goods-info");
        var orderTbodyTr = `<tr style="border-bottom: 1px solid #cbcbcb;">
                                    <td style="padding: 10px;font-weight: 600;">商品名称</td>
                                    <td style="padding: 10px;font-weight: 600;">单价</td>
                                    <td style="padding: 10px;font-weight: 600;">单位</td>
                                    <td style="padding: 10px;font-weight: 600;">数量</td>
                                    <td style="padding: 10px;font-weight: 600;">零售价</td>
                                 </tr>`;
        var orderTfoot = "";

        for (var i = 0;i<goodsInfos.length;i++){
            var opfObj = goodsInfos.eq(i);
            var stateSel = opfObj.find(".state-sel").val();
            if(stateSel=="N"){
                continue;
            }
            var goodsName = opfObj.find(".goods-name").html();
            var realPrice = opfObj.find(".realPrice").val();
            var realUnit = opfObj.find(".realUnit").val();
            var realNumber = opfObj.find(".realNumber").val();
            var retailPrice = opfObj.find(".retailPrice").val();
            orderTbodyTr += `<tr style="border-bottom: 1px solid #cbcbcb;">
                                    <td style="padding: 10px;font-weight: 600;">`+goodsName+`</td>
                                    <td style="padding: 10px;font-weight: 600;">`+realPrice+`</td>
                                    <td style="padding: 10px;font-weight: 600;">`+realUnit+`</td>
                                    <td style="padding: 10px;font-weight: 600;">`+realNumber+`</td>
                                    <td style="padding: 10px;font-weight: 600;">`+retailPrice+`</td>
                                 </tr>`;
        }
        orderTfoot = `<tr>
                            <td style="padding: 10px;font-weight: 600;">收货人姓名</td>
                            <td style="padding: 10px;font-weight: 600;">收货人电话</td>
                            <td style="padding: 10px;font-weight: 600;">收货地址</td>
                      </tr>`;
        orderTfoot += `<tr>
                            <td style="padding: 10px;">${orderInfo.receiveName!}</td>
                            <td style="padding: 10px;">${orderInfo.receiveTel!}</td>
                            <td style="padding: 10px;">${orderInfo.receiveAddress!}</td>
                      </tr>`;
        $("#print-content tbody").html(orderTbodyTr);
        $("#print-content tfoot").html(orderTfoot);
        $("#print-content").print({
            globalStyles:false,//是否包含父文档的样式，默认为true
            mediaPrint:false,//是否包含media='print'的链接标签。会被globalStyles选项覆盖，默认为false
            stylesheet:null,//外部样式表的URL地址，默认为null

            noPrintSelector:".no-print",//不想打印的元素的jQuery选择器，默认为".no-print"
            iframe:true,//是否使用一个iframe来替代打印表单的弹出窗口，true为在本页面进行打印，false就是说新开一个页面打印，默认为true
            append:null,//将内容添加到打印内容的后面
            prepend:null,//将内容添加到打印内容的前面，可以用来作为要打印内容
            deferred:$.Deferred().done(function() {
                if(callBack){
                    callBack();
                }
            })
        });
    };

    function submitReceipt(){
        var files = $("#file");
        var formData = new FormData();
        for (var i = 0;i<files[0].files.length;i++){
            formData.append("file", files[0].files[i]);
        }
        $.ajax({
            url:"/merchants/orderInfo/receiptOrderInfo?id=${orderInfo.id}",
            type:"post",
            data: formData,
            headers: {
                'httpType': "JSON",
            },
            contentType: false,
            processData: false,
            async:false,
            success: function(data) {
                console.log(data);
                if(data.statusCode==200){
                    var orderDetailArr = getOrderDetailArr();
                    $.ajax({
                        // headers必须添加，否则会报415错误
                        headers: {
                            'Accept': 'application/json',
                            'httpType': "JSON",
                            'Content-Type': 'application/json'
                        },
                        type: 'POST',
                        async:false,
                        data: JSON.stringify(orderDetailArr),
                        url: '/merchants/orderInfo/receiptOrderDetail',
                        success: function(data){
                            if(data.statusCode==200){
                                window.location.href = "/merchants/orderInfo/index";
                            }else if(data.statusCode==203){
                                layer.alert("权限不足")
                            }else{
                                layer.alert(data.message);
                            }
                        }
                    });
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }else{
                    layer.alert("录入失败")
                }
            },
            error:function(data) {
                layer.alert("录入失败")
            }
        });
    }
</script>