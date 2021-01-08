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
<#--                                    style="display: none;"-->
                                    <th >实采单价</th>
                                    <th>实采单位</th>
                                    <th>实采数量</th>
                                    <th>实采总价</th>
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
                                        <td>
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
                                                <select disabled class="unit-type-sel" data-id="${myn.id}" <#if orderInfo.state==3>disabled</#if>>
                                                    <option value="1" selected="selected">主</option>
                                                    <option value="2">辅</option>
                                                </select>
                                            <#else >
                                            <span class="unit">${myn.unitSe!}</span>
                                                <select class="unit-type-sel" data-id="${myn.id}" <#if orderInfo.state==3>disabled</#if>>
                                                    <option value="1">主</option>
                                                    <option value="2" selected="selected">辅</option>
                                                </select>
                                            </#if>
                                        </td>
                                        <td >
                                            <#if orderInfo.state==2>
<#--                                                <#if myn.unitType==1>${myn.price}<#else>${myn.priceSe}</#if>-->
                                                <input type="number" class="realPrice" value="0">
                                                <#else>
                                                    ${myn.realPrice!}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if orderInfo.state==2>
<#--                                                <input type="text" class="realUnit" value="${myn.unit}">-->
                                                <select class="realUnit">
                                                    <option value="无">单位</option>
                                                    <#if unitInfoList??>
                                                        <#list unitInfoList as uil>
<#--                                                            <#if myn.unit??&&myn.unit==uil.name!>selected="selected"</#if>-->
                                                            <option value="${uil.name!}" >${uil.name!}</option>
                                                        </#list>
                                                    </#if>
                                                </select>
                                                <#else>
                                                ${myn.realUnit!}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if orderInfo.state==2>
<#--                                                ${myn.number}-->
                                                <input type="number" class="realNumber" value="0">
                                                <#else>
                                                ${myn.realNumber!}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if orderInfo.state==2>
<#--                                                ${myn.totalPrice}-->
                                                <input type="number" class="realTotalPrice" value="0">
                                            <#else>
                                                ${myn.realTotalPrice!}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if orderInfo.state==2>
                                                <select class="state-sel">
                                                    <option value="">Y/N</option>
                                                    <option value="Y">Y</option>
                                                    <option value="N">N</option>
                                                </select>
                                            <#else >
                                                ${myn.state!}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if orderInfo.state==2>
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
                            <#if orderInfo.state==2>
                                <div style="margin: 10px 0px">
                                    回执图片：
                                    <input id="file" type="file" name="file" class="form-control"  placeholder="回执图片" multiple style="width: 200px;display: inline-block">
                                </div>
                                <button id="submit-receipt" class="btn bg-blue btn-block">完成录入</button>
                                <#else >
                                    <div id="order-summary">
                                        <table class="table">
                                            <tr>
                                                <td>本次采购金额：${totalRealPrice}</td>
                                                <td colspan="2">票据数量：${orderInfoImagesList?size}</td>
                                            </tr>
                                            <tr>
                                                <td>下单员：${orderInfo.xuName}</td>
                                                <td>采购员：${orderInfo.suName}</td>
                                                <td>回执员：${orderInfo.huName}</td>
                                            </tr>
                                            <tr>
                                                <td>采购时间：${orderInfo.createTime!?string("yyyy-MM-dd HH:mm:ss")}</td>
                                                <td colspan="2">回执时间：${orderInfo.successTime!?string("yyyy-MM-dd HH:mm:ss")}</td>
                                            </tr>
                                        </table>
                                    </div>
                                    <hr>
                                <#if orderInfoImagesList??>
                                    <#list orderInfoImagesList as myn>
                                        <div class="img-ct">
                                            <img src="${aliyunOos}${myn.imageName}" onclick="myImageOpen(this)">
                                        </div>
                                    </#list>
                                </#if>
                            </#if>

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
        targetDom.find(".realTotalPrice").val(realPrice*realNumber);
    }
    $('.realPrice,.realNumber').bind('input propertychange', function() {
        sumTotalPrice($(this).parents("tr"));
    });
    $('.realTotalPrice').bind('input propertychange', function() {
        var sumTotalPrice = $(this).val();
        var realNumber = $(this).parents("tr").find(".realNumber").val();
        sumTotalPrice = parseFloat(sumTotalPrice);
        realNumber = parseFloat(realNumber);
        $(this).parents("tr").find(".realPrice").val(sumTotalPrice/realNumber);
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
            var oidObj = {};
            oidObj["id"] = id;
            oidObj["realPrice"] = realPrice
            oidObj["realUnit"] = realUnit;
            oidObj["realNumber"] = realNumber;
            oidObj["realTotalPrice"] = realTotalPrice;
            oidObj["state"] = state;
            oidObj["remark"] = remark;
            oidObj["unitType"] = unitType;
            orderInfoDetails.push(oidObj);
        }
        return orderInfoDetails;
    }

    $("#submit-receipt").unbind("click").click(function(){
        var file = $("#file");
        if(file.val()){
            var files = $("#file");
            var formData = new FormData();
            for (var i = 0;i<files[0].files.length;i++){
                formData.append("file", files[0].files[i]);
            }
            $.ajax({
                url:"/admin/orderInfo/receiptOrderInfo?id=${orderInfo.id}",
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
                            url: '/admin/orderInfo/receiptOrderDetail',
                            success: function(data){
                                if(data.statusCode==200){
                                    window.location.href = "/admin/orderInfo/index";
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
        }else{
            layer.alert("请上传回执图片");
        }
    });
</script>