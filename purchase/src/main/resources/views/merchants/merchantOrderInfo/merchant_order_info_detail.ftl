<link href="/statics/admin/assets/css/order.css" rel="stylesheet" type="text/css">
<script language="javascript" src="/statics/admin/assets/js/plugins/lodop/print.js"></script>
<style>
    .pure-table {
        display: none;
    }
    .layui-layer-btn1{
        border-color: #e9928f!important;
        background-color: #dd8686!important;
        color: #fff!important;
    }
</style>
<div class="content-wrapper" style="padding: 0px;">
    <div class="content" style="padding: 0px;">
        <div class="col-md-12" style="padding: 0px;">
            <div id="moid" class="panel" style="margin: 0px;width: 100%">
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">订单信息：${orderInfo.orderNumber}</a>
                    </h6>
                </div>
                <div class="panel-collapse">
                    <div class="panel-body" style="padding: 0px;">
                            <div class="col-xs-12">
                                <div class="panel panel-flat">
                                    <div class="panel-heading">
                                        <h6 class="panel-title">
                                            订单列表
                                        </h6>
                                        <hr>
                                        <div class="sel-order-ct">
                                            <ul class="order-list">
                                                <#if orderInfoDetails??>
                                                    <#list orderInfoDetails as myn>
                                                    <li id="ol-${myn.giid!}" gl-id="${myn.giid!}" class="clearfix" price="${myn.price!}" unit="${myn.unit!}" price-se="${myn.priceSe!}" unit-se="${myn.unitSe!}" unit-type="${myn.unitType!}" siid="${myn.siid!}" supplier_name="${myn.supplierName!}" supplier_address="${myn.supplierAddress!}">
                                                            <ul>
                                                                <li class="ol-left">
                                                                    <#if myn.goodsInfo??>
                                                                        <span class="ch_name">${myn.goodsInfo.chName}</span><br>
                                                                        <span class="en_name">${myn.goodsInfo.enName}</span><br>
                                                                    </#if>
                                                                    <span class="red price_unit">
                                                                        <span class="show-price">
                                                                            <#if myn.unitType==1>
                                                                                    ${myn.price}
                                                                                <#else >
                                                                                    ${myn.priceSe}
                                                                            </#if>
                                                                        </span>P/
                                                                        <span class="show-unit">
                                                                            <#if myn.unitType==1>
                                                                                ${myn.unit}
                                                                            <#else >
                                                                                ${myn.unitSe}
                                                                            </#if>
                                                                        </span>
<#--                                                                        <select class="unit-sel">-->
<#--                                                                            <option value="1" <#if myn.unitType==1>selected="selected"</#if>>主</option>-->
<#--                                                                            <option value="2" <#if myn.unitType==2>selected="selected"</#if>>辅</option>-->
<#--                                                                        </select>-->
                                                                    </span>
<#--                                                                    <br>-->
<#--                                                                    <span class="supplier_name">供应商：${myn.supplierName}</span>-->
                                                                </li>
                                                                <li class="ol-right">
<#--                                                                    <a href="javascript:void(0)" class="sub">-</a>-->
                                                                    <input disabled type="number" class="goods-number" value="${myn.number}">
<#--                                                                    <a href="javascript:void(0)" class="add">+</a>-->
<#--                                                                    <a href="javascript:void(0)" class="del">×</a>-->
                                                                </li>
                                                            </ul>
                                                        </li>
                                                    </#list>
                                                </#if>
                                            </ul>
                                        </div>
                                        <div class="submit-ct">
                                            <p class="order-info">
                                                商品总数：<span id="total-goods">${orderInfo.sumNumber}</span>&nbsp;&nbsp;账单金额：<span id="total-price">${orderInfo.sumPrice}</span>P
                                            </p>
<#--                                            <div class="col-xs-12">-->
<#--                                                <label class="col-xs-6">-->
<#--                                                    <select id="sel-admin" class="form-control">-->
<#--                                                        <option value="">采购员</option>-->
<#--                                                        <#if adminInfoList??>-->
<#--                                                            <#list adminInfoList as myn>-->
<#--                                                                <option value="${myn.id}" <#if myn.id==orderInfo.suid>selected="selected"</#if> >${myn.nikeName}</option>-->
<#--                                                            </#list>-->
<#--                                                        </#if>-->
<#--                                                    </select>-->
<#--                                                </label>-->
<#--                                                <label class="col-xs-6">-->
<#--                                                    <select id="sel-car" class="form-control">-->
<#--                                                        <option value="">车辆</option>-->
<#--                                                        <#if carInfoList??>-->
<#--                                                            <#list carInfoList as myn>-->
<#--                                                                <#if (myn.id==orderInfo.ciid)||myn.state==1>-->
<#--                                                                    <option value="${myn.id}" <#if myn.id==orderInfo.ciid>selected="selected"</#if> >${myn.name}</option>-->
<#--                                                                </#if>-->
<#--                                                            </#list>-->
<#--                                                        </#if>-->
<#--                                                    </select>-->
<#--                                                </label>-->
<#--                                            </div>-->
<#--                                            <button id="submit-order" class="btn bg-blue btn-block">确认并打印</button>-->
                                        </div>
                                    </div>
                                </div>
                            </div>
<#--                        <div id="print-content" class="pure-table">-->
<#--                            <table  style="-->
<#--                                            border-collapse: collapse;-->
<#--                                            border-spacing: 0;-->
<#--                                            empty-cells: show;-->
<#--                                            border: 1px solid #cbcbcb;-->
<#--                                            width: 100%;text-align: center">-->
<#--                                <thead>-->
<#--                                <th colspan="5" style="text-align: center;font-size: 18px;">CII采购订单</th>-->
<#--                                </thead>-->
<#--                                <tbody>-->
<#--                                </tbody>-->
<#--                            </table>-->
<#--                            <table style="-->
<#--                                    position: fixed;-->
<#--                                    bottom: 0;-->
<#--                                    border-collapse: collapse;-->
<#--                                    border-spacing: 0;-->
<#--                                    empty-cells: show;-->
<#--                                    border-top: 1px solid #cbcbcb;-->
<#--                                    width: 100%;-->
<#--                                    ">-->
<#--                                <tfoot style="border-top: 1px solid #cbcbcb;">-->
<#--                                </tfoot>-->
<#--                            </table>-->
<#--                        </div>-->

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    // /**
    //  * oiid 订单ID
    //  * siid 供应商ID
    //  * 获得选择商品列表JSONArray
    //  */
    // function getGoodsArrList(oiid){
    //     var orderList = $(".order-list>li");
    //     var order_list_detail = [];
    //     //遍历选好的商品信息转换成JSONArray
    //     for(var i = 0;i < orderList.length;i++){
    //         var orderDom = orderList.eq(i);
    //         var goodsId = orderDom.attr("gl-id");
    //         var siid = orderDom.attr("siid");
    //         var unitType = orderDom.attr("unit-type");
    //         var price = orderDom.attr("price");
    //         price = parseFloat(price);
    //         var priceSe = orderDom.attr("price-se");
    //         priceSe = parseFloat(priceSe);
    //         var unit = orderDom.attr("unit");
    //         var unitSe = orderDom.attr("unit-se");
    //
    //         var number = orderDom.find(".goods-number").val();
    //         number = parseInt(number);
    //         var totalPrice = (price*number);
    //         if(unitType==2){
    //             totalPrice = (priceSe*number);
    //         }
    //
    //         var orderInfo = {};
    //         orderInfo["oiid"] = oiid;
    //         orderInfo["giid"] = goodsId;
    //         orderInfo["siid"] = siid;
    //         orderInfo["price"] = price;
    //         orderInfo["priceSe"] = priceSe;
    //         orderInfo["unit"] = unit;
    //         orderInfo["unitSe"] = unitSe;
    //         orderInfo["unitType"] = unitType;
    //         orderInfo["number"] = number;
    //         orderInfo["totalPrice"] = totalPrice;
    //
    //         order_list_detail.push(orderInfo);
    //     }
    //     return order_list_detail;
    // }

    <#--$("#submit-order").click(function(){-->
    <#--    var orderList = $(".order-list>li");-->
    <#--    var selAdmin = $("#sel-admin").val();-->
    <#--    var selCar = $("#sel-car").val();-->
    <#--    if(orderList.length==0){-->
    <#--        layer.alert('请选择商品');-->
    <#--        return;-->
    <#--    }-->
    <#--    if(!selAdmin){-->
    <#--        layer.alert('请选择采购员');-->
    <#--        return;-->
    <#--    }-->
    <#--    if(!selCar){-->
    <#--        layer.alert('请选择车辆');-->
    <#--        return;-->
    <#--    }-->
    <#--    var sum_price = $("#total-price").text();-->
    <#--    sum_price = parseFloat(sum_price);-->
    <#--    var sum_number = $("#total-goods").text();-->
    <#--    sum_number = parseInt(sum_number);-->
    <#--    if(selCar==${orderInfo.ciid}){-->
    <#--        layer.confirm('该车辆已派出，是否使用该车辆？', {-->
    <#--            btn: ['是', '否']-->
    <#--        }, function(index) {-->
    <#--            layer.close(index);-->
    <#--            layer.confirm('订单确认', {-->
    <#--                btn: ['确认订单', '打印订单','返回修改']-->
    <#--            }, function(index){-->
    <#--                layer.close(index);-->
    <#--                submitOrderInfo(selCar,selAdmin,sum_price,sum_number,1,function(){-->
    <#--                    window.location.href = "/admin/orderInfo/index";-->
    <#--                });-->
    <#--            }, function(index){-->
    <#--                layer.close(index);-->
    <#--                submitOrderInfo(selCar,selAdmin,sum_price,sum_number,2,function(createTime,orderNumber){-->
    <#--                    printOrder(createTime,orderNumber,function(){-->
    <#--                        layer.close(index)-->
    <#--                        layer.load(2);-->
    <#--                        window.location.href = "/admin/orderInfo/index";-->
    <#--                    });-->
    <#--                });-->
    <#--            });-->
    <#--        });-->
    <#--    }else{-->
    <#--        layer.confirm('订单确认', {-->
    <#--            btn: ['确认订单', '打印订单','返回修改']-->
    <#--        }, function(index){-->
    <#--            layer.close(index);-->
    <#--            submitOrderInfo(selCar,selAdmin,sum_price,sum_number,1,function(){-->
    <#--                window.location.href = "/admin/orderInfo/index";-->
    <#--            });-->
    <#--        }, function(index){-->
    <#--            layer.close(index);-->
    <#--            submitOrderInfo(selCar,selAdmin,sum_price,sum_number,2,function(createTime,orderNumber){-->
    <#--                printOrder(createTime,orderNumber,function(){-->
    <#--                    layer.close(index)-->
    <#--                    layer.load(2);-->
    <#--                    window.location.href = "/admin/orderInfo/index";-->
    <#--                });-->
    <#--            });-->
    <#--        });-->
    <#--    }-->
    <#--});-->

    <#--function submitOrderInfo(selCar,selAdmin,sum_price,sum_number,state,callBack){-->
    <#--    $.ajax({-->
    <#--        url:"/admin/orderInfo/orderInfoUpdateIng?id=${orderInfo.id}",-->
    <#--        data:{-->
    <#--            ciid:selCar,-->
    <#--            suid:selAdmin,-->
    <#--            sumPrice:sum_price,-->
    <#--            sumNumber:sum_number,-->
    <#--            state:state-->
    <#--        },-->
    <#--        headers: {-->
    <#--            'httpType': "JSON",-->
    <#--        },-->
    <#--        type:"POST",-->
    <#--        async:false,-->
    <#--        success:function (data) {-->
    <#--            if(data.statusCode==200){-->
    <#--                //订单创建成功，将创建订单详细商品数据-->
    <#--                var orderId = data.data.id;-->
    <#--                var createTime = data.data.createTime;-->
    <#--                var orderNumber = data.data.orderNumber;-->
    <#--                var goodsArrList = getGoodsArrList(orderId);-->
    <#--                $.ajax({-->
    <#--                    // headers必须添加，否则会报415错误-->
    <#--                    headers: {-->
    <#--                        'Accept': 'application/json',-->
    <#--                        'httpType': "JSON",-->
    <#--                        'Content-Type': 'application/json'-->
    <#--                    },-->
    <#--                    type: 'POST',-->
    <#--                    async:false,-->
    <#--                    data: JSON.stringify(goodsArrList),-->
    <#--                    url: '/admin/orderInfo/orderDetailUpdateIng?oiid=${orderInfo.id}',-->
    <#--                    success: function(data){-->
    <#--                        if(data.statusCode==200){-->
    <#--                            if(callBack){-->
    <#--                                callBack(createTime,orderNumber);-->
    <#--                            }-->
    <#--                            // printOrder(createTime,orderNumber,function(){-->
    <#--                            //     window.location.href = "/admin/orderInfo/index";-->
    <#--                            // });-->
    <#--                        }else if(data.statusCode==203){-->
    <#--                            layer.alert("权限不足")-->
    <#--                        }else{-->
    <#--                            layer.alert(data.message);-->
    <#--                        }-->
    <#--                    }-->
    <#--                });-->
    <#--            }else if(data.statusCode==203){-->
    <#--                layer.alert("权限不足")-->
    <#--            }else{-->
    <#--                layer.alert(data.message);-->
    <#--            }-->
    <#--        }-->
    <#--    })-->
    <#--}-->

    <#--function sumGoodsTotalNumberAndPrice(){-->
    <#--    var totalNumber = 0;//选择商品的总数量-->
    <#--    var totalPrice = 0;//选择商品的总价格-->
    <#--    var orderList = $(".order-list li[price]");-->
    <#--    for(var i = 0;i<orderList.length;i++){-->
    <#--        var orderDom = orderList.eq(i);-->
    <#--        var unitType = orderDom.attr("unit-type");-->
    <#--        var number = orderDom.find(".goods-number").val();-->
    <#--        number = parseInt(number);-->
    <#--        totalNumber+=number;-->

    <#--        var price = orderDom.attr("price");-->
    <#--        if(unitType==2){-->
    <#--            price = orderDom.attr("price-se");-->
    <#--        }-->
    <#--        price = parseFloat(price);-->
    <#--        totalPrice+=(price*number);-->
    <#--    }-->
    <#--    $("#total-goods").text(totalNumber);-->
    <#--    $("#total-price").text(totalPrice);-->
    <#--}-->

    <#--$(".order-list .sub,.add,.del").unbind("click").click(function(){-->
    <#--    var sadDom = $(this);-->
    <#--    var parentDom = $(this).parents("li[gl-id]");-->
    <#--    var goodsNumberDom = parentDom.find(".goods-number");-->
    <#--    var goodsNumber = parseInt(goodsNumberDom.val());-->
    <#--    var type = sadDom.attr("class");-->
    <#--    if(type=="sub"){-->
    <#--        goodsNumber--;-->
    <#--        if(goodsNumber<=0){-->
    <#--            goodsNumber = 0;-->
    <#--        }-->
    <#--    }else if(type=="add"){-->
    <#--        goodsNumber++;-->
    <#--    }else if(type=="del"){-->
    <#--        goodsNumber = 0;-->
    <#--    }-->
    <#--    var glId = parentDom.attr("gl-id");-->
    <#--    goodsNumberDom.val(goodsNumber);-->
    <#--    if(type=="del"){-->
    <#--        parentDom.remove();-->
    <#--    }-->
    <#--    //重新计算选择商品的总价格、总数量-->
    <#--    sumGoodsTotalNumberAndPrice();-->
    <#--});-->

    <#--$(".order-list .unit-sel").unbind("change").change(function(){-->
    <#--    var sadDom = $(this);-->
    <#--    var parentDom = $(this).parents("li[gl-id]");-->
    <#--    var unitType = sadDom.val();-->
    <#--    if(unitType==1){-->
    <#--        parentDom.find(".show-price").text(parentDom.attr("price"));-->
    <#--        parentDom.find(".show-unit").text(parentDom.attr("unit"));-->
    <#--    }else{-->
    <#--        parentDom.find(".show-price").text(parentDom.attr("price-se"));-->
    <#--        parentDom.find(".show-unit").text(parentDom.attr("unit-se"));-->
    <#--    }-->
    <#--    parentDom.attr("unit-type",unitType);-->
    <#--    sumGoodsTotalNumberAndPrice();-->
    <#--});-->

    <#--function printOrder(orderTime,orderNo,callBack) {-->
    <#--    var orderList = $(".order-list>li");-->
    <#--    var orderTbodyTr = "";-->
    <#--    var orderTfoot = "";-->
    <#--    var orderPrintformatArr = [];-->
    <#--    var selAdmin = $("#sel-admin option:selected").text();-->
    <#--    for (var i = 0;i<orderList.length;i++){-->
    <#--        var orderDom = orderList.eq(i);-->
    <#--        var ch_name = orderDom.find(".ch_name").text();-->
    <#--        var en_name = orderDom.find(".en_name").text();-->
    <#--        var siid = orderDom.attr("siid");-->
    <#--        var supplier_name = orderDom.attr("supplier_name");-->
    <#--        var supplier_address = orderDom.attr("supplier_address");-->
    <#--        var unitType = orderDom.attr("unit-type");-->
    <#--        var unit = orderDom.attr("unit");-->
    <#--        var price = orderDom.attr("price");-->
    <#--        if(unitType==2){-->
    <#--            price = orderDom.attr("price-se");-->
    <#--            unit = orderDom.attr("unit-se");-->
    <#--        }-->
    <#--        price = parseFloat(price);-->
    <#--        var goods_number = orderDom.find(".goods-number").val();-->
    <#--        goods_number = parseInt(goods_number);-->

    <#--        var total_price = (price*goods_number);-->
    <#--        var sol = {};-->
    <#--        sol["name"] = ch_name+"("+en_name+")";-->
    <#--        sol["price"] = price;-->
    <#--        sol["unit"] = unit;-->
    <#--        sol["goods_number"] = goods_number;-->
    <#--        var isAdd = true;-->
    <#--        for (var j = 0;j<orderPrintformatArr.length;j++){-->
    <#--            var opfObj = orderPrintformatArr[j];-->
    <#--            if(opfObj["siid"]==siid){-->
    <#--                opfObj["sOrderList"].push(sol);-->
    <#--                isAdd = false;-->
    <#--                break;-->
    <#--            }-->
    <#--        }-->
    <#--        if(isAdd){-->
    <#--            var orderObj = {};-->
    <#--            orderObj["siid"] = siid;-->
    <#--            orderObj["supplier_name"] = supplier_name;-->
    <#--            orderObj["supplier_address"] = supplier_address;-->
    <#--            var sOrderList = [];-->
    <#--            sOrderList.push(sol);-->
    <#--            orderObj["sOrderList"] = sOrderList;-->
    <#--            orderPrintformatArr.push(orderObj);-->
    <#--        }-->
    <#--    }-->
    <#--    console.log(orderPrintformatArr);-->
    <#--    for (var i = 0;i<orderPrintformatArr.length;i++){-->
    <#--        var opfObj = orderPrintformatArr[i];-->
    <#--        var supplier_name = opfObj["supplier_name"];-->
    <#--        var supplier_address = opfObj["supplier_address"];-->
    <#--        var sOrderList = opfObj["sOrderList"];-->
    <#--        orderTbodyTr += `<tr><td colspan="5" style="padding: 10px;font-weight: 600;text-align: left;">供应商名称：`+supplier_name+`<br/>地址：`+supplier_address+`</td></tr>`;-->
    <#--        orderTbodyTr += `<tr style="border-bottom: 1px solid #cbcbcb;">-->
    <#--                                <td style="padding: 10px;font-weight: 600;">商品名称</td>-->
    <#--                                <td style="padding: 10px;font-weight: 600;">参考价格</td>-->
    <#--                                <td style="padding: 10px;font-weight: 600;">单位</td>-->
    <#--                                <td style="padding: 10px;font-weight: 600;">数量</td>-->
    <#--                                <td style="padding: 10px;font-weight: 600;">采购情况</td>-->
    <#--                             </tr>`;-->
    <#--        for (var j = 0;j<sOrderList.length;j++){-->
    <#--            var sOrder = sOrderList[j];-->
    <#--            var name = sOrder["name"];-->
    <#--            var price = sOrder["price"];-->
    <#--            var unit = sOrder["unit"];-->
    <#--            var goods_number = sOrder["goods_number"];-->
    <#--            orderTbodyTr += `<tr style="border-bottom: 1px solid #cbcbcb;">-->
    <#--                                <td style="padding: 10px;">`+name+`</td>-->
    <#--                                <td style="padding: 10px;">`+price+`</td>-->
    <#--                                <td style="padding: 10px;">`+unit+`</td>-->
    <#--                                <td style="padding: 10px;">`+goods_number+`</td>-->
    <#--                                <td style="padding: 10px;"><input type="checkbox"/></td>-->
    <#--                             </tr>`;-->
    <#--        }-->
    <#--    }-->
    <#--    orderTfoot = `<tr>-->
    <#--                        <td style="padding: 10px;font-weight: 600;">编号</td>-->
    <#--                        <td style="padding: 10px;font-weight: 600;">时间</td>-->
    <#--                        <td style="padding: 10px;font-weight: 600;">下单员</td>-->
    <#--                        <td style="padding: 10px;font-weight: 600;">采购员</td>-->
    <#--                        <td style="padding: 10px;font-weight: 600;">总价格</td>-->
    <#--                  </tr>`;-->
    <#--    orderTfoot += `<tr>-->
    <#--                        <td style="padding: 10px;">`+orderNo+`</td>-->
    <#--                        <td style="padding: 10px;">`+orderTime+`</td>-->
    <#--                        <td style="padding: 10px;">`+selAdmin+`</td>-->
    <#--                        <td style="padding: 10px;">`+selAdmin+`</td>-->
    <#--                        <td style="padding: 10px;">`+$("#total-price").text()+`</td>-->
    <#--                  </tr>`;-->
    <#--    $("#print-content tbody").html(orderTbodyTr);-->
    <#--    $("#print-content tfoot").html(orderTfoot);-->
    <#--    $("#print-content").print({-->
    <#--        globalStyles:false,//是否包含父文档的样式，默认为true-->
    <#--        mediaPrint:false,//是否包含media='print'的链接标签。会被globalStyles选项覆盖，默认为false-->
    <#--        stylesheet:null,//外部样式表的URL地址，默认为null-->

    <#--        noPrintSelector:".no-print",//不想打印的元素的jQuery选择器，默认为".no-print"-->
    <#--        iframe:true,//是否使用一个iframe来替代打印表单的弹出窗口，true为在本页面进行打印，false就是说新开一个页面打印，默认为true-->
    <#--        append:null,//将内容添加到打印内容的后面-->
    <#--        prepend:null,//将内容添加到打印内容的前面，可以用来作为要打印内容-->
    <#--        deferred:$.Deferred().done(function() {-->
    <#--            if(callBack){-->
    <#--                callBack();-->
    <#--            }-->
    <#--        })-->
    <#--    });-->
    <#--};-->
    if(IsPC()){
        $("#moid").css("width",$(".layui-layer-content").width()-18);
    }else{
        $("#moid").css("width","100%");
    }
</script>