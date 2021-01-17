
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="商品信息">
    <#include "../common/head.ftl"/>
    <link href="/statics/admin/assets/css/order.css" rel="stylesheet" type="text/css">
    <script language="javascript" src="/statics/admin/assets/js/plugins/lodop/print.js"></script>
    <style>
        .table-responsive .table img{
            /*max-width: 38px;*/
            /*border-radius: 50%;*/
            margin-right: 5px;
            cursor: pointer;
        }
        .panel-title a{
            margin-left: 15px;
        }
        .state{
            cursor: pointer;
        }
        #goToOrder{
            position: fixed;
            right: 0px;
            bottom: 22px;
            background: #2196f3;
            color: #fff;
            padding: 13px 10px;
            border-top-left-radius: 8px;
            border-bottom-left-radius: 8px;
        }
        .pure-table {
            display: none;
        }
        .layui-layer-btn1{
            border-color: #e9928f!important;
            background-color: #dd8686!important;
            color: #fff!important;
        }
        .add-goods{
            color: #2196f3;
        }
    </style>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="goodsInfo">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="panel">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">查询条件</a>
                        </h6>
                    </div>
                    <form action="/admin/goodsInfo/stockIndex" method="post">
                        <div id="accordion-styled-group3" class="panel-collapse">
                            <div class="panel-body">
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-xs-3">
                                        <input type="text"  name="chName" value="${goodsInfo.chName!}"class="form-control" placeholder="中文名称">
                                    </div>
                                    <div class="col-xs-3">
                                        <input type="text"  name="enName" value="${goodsInfo.enName!}"class="form-control" placeholder="英文名称">
                                    </div>
                                    <div class="col-xs-3">
                                        <select class="form-control" name="goid">
                                            <option value="">一级分类</option>
                                            <#if goodsOneTypeList??>
                                                <#list goodsOneTypeList as myn>
                                                    <option value="${myn.id!}" <#if goodsInfo.goid??&&myn.id==goodsInfo.goid>selected</#if>>${myn.name!}</option>
                                                </#list>
                                            </#if>
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
                <div class="row">
                    <div class="col-lg-8">
                        <div class="panel-heading bg-primary">
                            <h6 class="panel-title">
                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">商品库存信息列表</a>
                            </h6>
                        </div>
                        <div class="panel panel-flat">
                            <div class="table-responsive">
                                <table class="table fixed-table" >
                                    <thead>
                                    <tr class="border-bottom-danger">
                                        <th>商品图片</th>
                                        <th>商品名称</th>
                                        <th>系统库存</th>
                                        <th>安全库存</th>
                                        <th>供应商</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <col style="width: 20%" />
                                    <col style="width: 20%" />
                                    <col style="width: 20%" />
                                    <col style="width: 20%" />
                                    <col style="width: 20%" />
                                    <#if pageInfos??>
                                        <#list pageInfos.list as myn>
                                            <tr class="border-top-primary">
                                                <td>
                                                    <#if myn.imgUrl??>
                                                        <img onclick="myImageOpen(this)" width="100px" src="${aliyunOos!}${myn.imgUrl!}"/>
                                                    <#else >
                                                        <img style="cursor: default" width="100px" src="/statics/admin/assets/images/default_goods.jpg"/>
                                                    </#if>
                                                </td>
                                                <td>
                                                    ${myn.chName!}
                                                    <br/>
                                                    ${myn.enName!}
                                                </td>
                                                <td>
                                                    ${myn.stock!}(${myn.unitPrName!})
                                                    <br/>
                                                    ${myn.stockSe!}(${myn.unitPeName!})
                                                </td>
                                                <td>
                                                    <#assign safeColor="green">
                                                    <#assign icon="icon-shield2">
                                                    <#if myn.safeStock??>
                                                        <#if myn.stock lt myn.safeStock>
                                                            <#assign safeColor="red">
                                                            <#assign icon="icon-alert">
                                                        </#if>
                                                    </#if>

                                                    <span style="color: ${safeColor}"><i class="${icon}"></i>${myn.safeStock!}(主)</span>
                                                    <a class="collapsed add-goods" id="${myn.id!}" unitType="1" price="${myn.price!}" unit="${myn.unitPrName!}"  goid="${myn.goid!}" gtid="${myn.gtid!}" name="${myn.chName!}" en_name="${myn.enName!}" supplier_name="${myn.supplierName!}" supplier_address="${myn.supplierAddress!}" siid="${myn.siid}">
                                                        <i class="icon-plus2"></i>补货
                                                    </a>
                                                    <br/>
                                                    <#assign safeColor="green">
                                                    <#assign icon="icon-shield2">
                                                    <#if myn.safeStockSe??>
                                                        <#if myn.stockSe lt myn.safeStockSe>
                                                            <#assign safeColor="red">
                                                            <#assign icon="icon-alert">
                                                        </#if>
                                                    </#if>
                                                    <span style="color: ${safeColor}"><i class="${icon}"></i>${myn.safeStockSe!}(辅)</span>
                                                    <a class="collapsed add-goods" id="${myn.id!}" unitType="2" price="${myn.priceSe!}" unit="${myn.unitPeName!}"  goid="${myn.goid!}" gtid="${myn.gtid!}" name="${myn.chName!}" en_name="${myn.enName!}" supplier_name="${myn.supplierName!}" supplier_address="${myn.supplierAddress!}" siid="${myn.siid}">
                                                        <i class="icon-plus2"></i>补货
                                                    </a>
                                                </td>
                                                <td>
                                                    <#if myn.supplierName??>
                                                        ${myn.supplierName!}
                                                    <#else >
                                                        无
                                                    </#if>
                                                </td>
                                            </tr>
                                        </#list>
                                    </#if>
                                    <tr class="border-top-primary">
                                        <td colspan="15">
                                            <#import "../common/pagebar.ftl" as pagebar>
                                            <@pagebar.pagebar pageInfo=pageInfos actionUrl="/admin/goodsInfo/stockIndex"/>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="panel panel-flat">
                            <div class="panel-heading">
                                <h6 class="panel-title">
                                    补货商品列表
                                    <a id="clear-order" style="float: right;font-size: 13px;">
                                        <i class="icon-bin"></i>
                                        全部清除
                                    </a>
                                </h6>
                                <hr/>
                                <div class="sel-order-ct">
                                    <ul class="order-list">
                                    </ul>
                                </div>
                                <div class="submit-ct">
                                    <p class="order-info">
                                        商品总数：<span id="total-goods">0</span>&nbsp&nbsp账单金额：<span id="total-price">0</span>P
                                    </p>
                                    <div class="col-xs-12">
                                        <label class="col-xs-6">
                                            <select id="sel-admin" class="form-control">
                                                <option value="">采购员</option>
                                                <#if adminInfoList??>
                                                    <#list adminInfoList as myn>
                                                        <option value="${myn.id}">${myn.nikeName}</option>
                                                    </#list>
                                                </#if>
                                            </select>
                                        </label>
                                        <label class="col-xs-6">
                                            <select id="sel-car" class="form-control">
                                                <option value="">车辆</option>
                                                <#if carInfoList??>
                                                    <#list carInfoList as myn>
                                                        <option value="${myn.id}">${myn.name}</option>
                                                    </#list>
                                                </#if>
                                            </select>
                                        </label>
                                    </div>
                                    <button id="submit-order" class="btn bg-blue btn-block">确认下单并打印</button>
                                    <#--                                    <button onclick="printPreview()" class="btn bg-blue btn-block">打印预览</button>-->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="print-content" class="pure-table">
                        <table style="
                                        border-collapse: collapse;
                                        border-spacing: 0;
                                        empty-cells: show;
                                        border: 1px solid #cbcbcb;
                                        width: 100%;text-align: center">
                            <thead>
                            <th colspan="5" style="text-align: center;font-size: 18px;">CII采购订单</th>
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
                <#include "../common/foot.ftl"/>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $("#clear-order").click(function(){
        $(".order-list").html("");
        sumGoodsTotalNumberAndPrice();
        localStorage.setItem("selOrderList",JSON.stringify([]));
    });
    loadSelOrderList();
    function loadSelOrderList(){
        var selOrderList = localStorage.getItem("selOrderList");
        if(selOrderList){
            try{
                var readSelOrderList = JSON.parse(selOrderList);
                for(var i = 0;i<readSelOrderList.length;i++){
                    var readSelOrder = readSelOrderList[i];
                    var id = readSelOrder.id;
                    var unitType = readSelOrder.unitType;
                    var price = readSelOrder.price;
                    var unit = readSelOrder.unit;
                    var name = readSelOrder.name;
                    var en_name = readSelOrder.en_name;
                    var supplier_name = readSelOrder.supplier_name;
                    var supplier_address = readSelOrder.supplier_address;
                    var siid = readSelOrder.siid;
                    var number = readSelOrder.number;
                    goodsAddToOrderList(id,unitType,price,unit,name,en_name,supplier_name,supplier_address,siid,number);
                }
                sumGoodsTotalNumberAndPrice();
            }catch (e){
                console.log(e);
            }
        }else{
            localStorage.setItem("selOrderList",JSON.stringify([]));
        }
    }

    function refreshSelOrderList(id,unitType,price,unit,name,en_name,supplier_name,supplier_address,siid,number){
        var selOrderList = localStorage.getItem("selOrderList");
        if(selOrderList){
            try{
                var readSelOrderList = JSON.parse(selOrderList);
                console.log(readSelOrderList);
                var existFlag = false;
                for(var i = 0;i<readSelOrderList.length;i++){
                    var readSelOrder = readSelOrderList[i];
                    var r_id = readSelOrder.id;
                    var r_unitType = readSelOrder.unitType;
                    if(id==r_id&&unitType==r_unitType){
                        existFlag = true;
                        //如果商品存在缓存列表 ，则修改商品信息
                        readSelOrder["number"] = number;
                        break;
                    }
                }
                //如果商品不存在缓存列表，则新增商品信息
                if(!existFlag){
                    var goodsInfo = {};
                    goodsInfo["id"] = id;
                    goodsInfo["unitType"] = unitType;
                    goodsInfo["price"] = price;
                    goodsInfo["unit"] = unit;
                    goodsInfo["name"] = name;
                    goodsInfo["en_name"] = en_name;
                    goodsInfo["supplier_name"] = supplier_name;
                    goodsInfo["supplier_address"] = supplier_address;
                    goodsInfo["siid"] = siid;
                    goodsInfo["number"] = number;
                    readSelOrderList.push(goodsInfo);
                }
                localStorage.setItem("selOrderList",JSON.stringify(readSelOrderList));//更新localStorage
            }catch (e){
                console.log(e);
            }
        }else{
            localStorage.setItem("selOrderList",JSON.stringify([]));
        }
    }

    function goodsAddToOrderList(id,unitType,price,unit,name,en_name,supplier_name,supplier_address,siid,defaultNumber){
        var goodsInfo = $(".order-list li[id='ol-"+id+"'][unitType='"+unitType+"']");
        //如果订单列表已存在该商品则商品数量加1，否则订单列表加入此商品
        if(goodsInfo.length!=0){
            var goodsNumberDom = goodsInfo.find(".goods-number");
            var goodsNumber = parseInt(goodsNumberDom.val());
            var curNum = goodsNumber+1;
            goodsNumberDom.val(curNum);
            refreshSelOrderList(id,unitType,price,unit,name,en_name,supplier_name,supplier_address,siid,curNum);
        }else{
            var olId = "ol-"+id;
            var goodsInfoHtml = `<li id="`+olId+`" gl-id="`+id+`" class="clearfix" price=`+price+` unit=`+unit+` unitType=`+unitType+` siid=`+siid+` supplier_name=`+supplier_name+` supplier_address=`+supplier_address+`>
                                            <ul>
                                                <li class="ol-left">
                                                    <span class="ch_name">`+name+`</span><br/>
                                                    <span class="en_name">`+en_name+`</span><br/>
                                                    <span class="red price_unit">
                                                        <span class="show-price">`+price+`</span>P/<span class="show-unit">`+unit+`</span>
                                                        </span><br/>
                                                        <span class="supplier_name">供应商：`+supplier_name+`</span>
                                                </li>
                                                <li class="ol-right">
                                                    <a href="javascript:void(0)" class="sub">-</a>
                                                    <input type="number" class="goods-number" value="`+defaultNumber+`"/>
                                                    <a href="javascript:void(0)" class="add">+</a>
                                                    <a href="javascript:void(0)" class="del">×</a>
                                                </li>
                                            </ul>
                                        </li>`;
            $(".order-list").append(goodsInfoHtml);
            refreshSelOrderList(id,unitType,price,unit,name,en_name,supplier_name,supplier_address,siid,defaultNumber);
            $("#"+olId+" .sub,.add,.del").unbind("click").click(function(){
                var sadDom = $(this);
                var parentDom = $(this).parents("li[gl-id]");
                var goodsNumberDom = parentDom.find(".goods-number");
                var goodsNumber = parseInt(goodsNumberDom.val());
                var type = sadDom.attr("class");
                if(type=="sub"){
                    goodsNumber--;
                    if(goodsNumber<=0){
                        goodsNumber = 0;
                    }
                }else if(type=="add"){
                    goodsNumber++;
                }else if(type=="del"){
                    goodsNumber = 0;
                }
                goodsNumberDom.val(goodsNumber);
                refreshSelOrderList(
                    parentDom.attr("gl-id"),parentDom.attr("unitType"),parentDom.attr("price"),
                    parentDom.attr("unit"),parentDom.attr("name"),parentDom.attr("en_name"),
                    parentDom.attr("supplier_name"),parentDom.attr("supplier_address"),parentDom.attr("siid"),
                    goodsNumber);
                if(type=="del"||goodsNumber==0){
                    parentDom.remove();
                }
                //重新计算选择商品的总价格、总数量
                sumGoodsTotalNumberAndPrice();
            });
            $("#"+olId+" .goods-number").unbind("input propertychange").bind('input propertychange',function () {
                var parentDom = $(this).parents("li[gl-id]");
                refreshSelOrderList(
                    parentDom.attr("gl-id"),parentDom.attr("unitType"),parentDom.attr("price"),
                    parentDom.attr("unit"),parentDom.attr("name"),parentDom.attr("en_name"),
                    parentDom.attr("supplier_name"),parentDom.attr("supplier_address"),parentDom.attr("siid"),
                    $(this).val());
                //重新计算选择商品的总价格、总数量
                sumGoodsTotalNumberAndPrice();
            });
        }
    }

    /**
     * 商品选择事件
     */
    $(".add-goods").unbind("click").click(function(){
        var goodsDom = $(this);
        var id = goodsDom.attr("id");
        var price = goodsDom.attr("price");
        if(!price){
            layer.msg("价格未设定");
            return;
        }
        var unit = goodsDom.attr("unit");
        var name = goodsDom.attr("name");
        var en_name = goodsDom.attr("en_name");
        var supplier_name = goodsDom.attr("supplier_name");
        var supplier_address = goodsDom.attr("supplier_address");
        var siid = goodsDom.attr("siid");

        var unitType = goodsDom.attr("unitType");
        goodsAddToOrderList(id,unitType,price,unit,name,en_name,supplier_name,supplier_address,siid,1);
        //重新计算选择商品的总价格、总数量
        sumGoodsTotalNumberAndPrice();
    });

    function sumGoodsTotalNumberAndPrice(){
        var totalNumber = 0;//选择商品的总数量
        var totalPrice = 0;//选择商品的总价格
        var orderList = $(".order-list li[price]");
        for(var i = 0;i<orderList.length;i++){
            var orderDom = orderList.eq(i);
            var unitType = orderDom.attr("unitType");
            var number = orderDom.find(".goods-number").val();
            number = parseInt(number);
            totalNumber+=number;

            var price = orderDom.attr("price");
            price = parseFloat(price);
            totalPrice+=(price*number);
        }
        $("#total-goods").text(totalNumber);
        $("#total-price").text(totalPrice);
    }

    /**
     * oiid 订单ID
     * siid 供应商ID
     * 获得选择商品列表JSONArray
     */
    function getGoodsArrList(oiid){
        var orderList = $(".order-list>li");
        var order_list_detail = [];
        //遍历选好的商品信息转换成JSONArray
        for(var i = 0;i < orderList.length;i++){
            var orderDom = orderList.eq(i);
            var goodsId = orderDom.attr("gl-id");
            var siid = orderDom.attr("siid");
            var unitType = orderDom.attr("unitType");
            var price = orderDom.attr("price");
            price = parseFloat(price);
            // var priceSe = orderDom.attr("price-se");
            // priceSe = parseFloat(priceSe);
            var unit = orderDom.attr("unit");
            // var unitSe = orderDom.attr("unit-se");

            var number = orderDom.find(".goods-number").val();
            number = parseInt(number);
            var totalPrice = (price*number);
            // if(unitType==2){
            //     totalPrice = (priceSe*number);
            // }

            var orderInfo = {};
            orderInfo["oiid"] = oiid;
            orderInfo["giid"] = goodsId;
            orderInfo["siid"] = siid;
            orderInfo["price"] = price;
            // orderInfo["priceSe"] = priceSe;
            orderInfo["unit"] = unit;
            // orderInfo["unitSe"] = unitSe;
            orderInfo["unitType"] = unitType;
            orderInfo["number"] = number;
            orderInfo["totalPrice"] = totalPrice;

            order_list_detail.push(orderInfo);
        }
        return order_list_detail;
    }

    $("#submit-order").click(function(){
        var orderList = $(".order-list>li");
        // var selSupplier = $("#sel-supplier").val();
        var selAdmin = $("#sel-admin").val();
        var selCar = $("#sel-car").val();
        if(orderList.length==0){
            layer.alert('请选择商品');
            return;
        }
        // if(!selSupplier){
        //     layer.alert('请选择供应商');
        //     return;
        // }
        if(!selAdmin){
            layer.alert('请选择采购员');
            return;
        }
        if(!selCar){
            layer.alert('请选择车辆');
            return;
        }

        var sum_price = $("#total-price").text();
        sum_price = parseFloat(sum_price);
        var sum_number = $("#total-goods").text();
        sum_number = parseInt(sum_number);
        // printOrder("2020/12/02","20201202A010",function(){
        //
        // });
        layer.confirm('订单确认', {
            btn: ['加入订单', '立即打印','返回修改']
        }, function(index){
            layer.close(index);
            submitOrderInfo(selCar,selAdmin,sum_price,sum_number,1,function(){
                window.location.href = "/admin/orderInfo/index";
            });
        }, function(index){
            layer.close(index);
            submitOrderInfo(selCar,selAdmin,sum_price,sum_number,2,function(createTime,orderNumber){
                printOrder(createTime,orderNumber,function(){
                    layer.close(index)
                    layer.load(2);
                    window.location.href = "/admin/orderInfo/submitIndex";
                });
            });
        });
    });

    function submitOrderInfo(selCar,selAdmin,sum_price,sum_number,state,callBack){
        $.ajax({
            url:"/admin/orderInfo/submitIndexIng",
            data:{
                ciid:selCar,
                suid:selAdmin,
                sumPrice:sum_price,
                sumNumber:sum_number,
                state:state
            },
            headers: {
                'httpType': "JSON",
            },
            type:"POST",
            async:false,
            success:function (data) {
                if(data.statusCode==200){
                    //订单创建成功，将创建订单详细商品数据
                    var orderId = data.data.id;
                    var createTime = data.data.createTime;
                    var orderNumber = data.data.orderNumber;
                    var goodsArrList = getGoodsArrList(orderId);
                    $.ajax({
                        // headers必须添加，否则会报415错误
                        headers: {
                            'Accept': 'application/json',
                            'httpType': "JSON",
                            'Content-Type': 'application/json'
                        },
                        type: 'POST',
                        async:false,
                        data: JSON.stringify(goodsArrList),
                        url: '/admin/orderInfo/submitOrderDetailIng',
                        success: function(data){
                            if(data.statusCode==200){
                                if(callBack){
                                    callBack(createTime,orderNumber);
                                }

                            }else if(data.statusCode==203){
                                layer.alert("权限不足")
                            }else{
                                layer.alert(data.message);
                            }
                        }
                    });
                }else{
                    layer.alert(data.message);
                }
            }
        })
    }

</script>
</html>



