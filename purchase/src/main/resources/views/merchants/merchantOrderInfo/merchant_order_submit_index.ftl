
<!DOCTYPE html>
<html class="js cssanimations">
<head>
    <link href="/statics/merchants/assets/css/order.css" rel="stylesheet">
    <#assign title="商户点单">
    <#include "../common/head.ftl"/>
    <style>
        .notice-info{
            position: fixed;
            top: 45px;
            background: #eee;
            width: 100%;
            color: #fff;
            padding: 6px 0px;
            font-size: 13px;
            z-index: 9;
        }
        .search-ct{
            position: fixed;
            width: 100%;
            z-index: 9;
            background: #eee;
            padding: 5px;
            top: 71px;
        }
        .search-ct input{
            display: inline-block;
            float: left;
            width: 70%;
        }
        .search-ct button{
            display: inline-block;
            float: left;
            width: 30%;
        }
        .swiper-container{
            padding-top: 118px;
        }
    </style>
</head>
<body style="">
<#include "../common/top.ftl"/>
<div class="page-container">
    <div class="page-content">
        <#assign menuType="merchantOrderInfo">
        <#include "../common/menu.ftl"/>
<#--        <div class="header">-->
<#--            <div class="leftlogo"></div>-->
<#--            <div class="righttitle">-->
<#--                <h1>xxx酒店</h1>-->
<#--                <h2>海淀区交大东路186号永大中心5号楼A口3层</h2>-->
<#--            </div>-->
<#--            <div class="bulletin"><span class="bulletin-title"></span>公告信息公告信息公告信息公告信息公告信息</div>-->
<#--        </div>-->
        <div class="box notice-info" id="notice-info">
            <div class="t_news">
                <b><i class="icon-volume-medium"></i>最新公告：</b>
                <ul class="news_li">
                    <#if noticeInfoList??>
                        <#list noticeInfoList as myn>
                            <li><a>${myn.content!}</a></li>
                        </#list>
                    </#if>
                </ul>
                <ul class="swap"></ul>
            </div>
        </div>
        <div class="search-ct" >
            <input id="search-order-text" type="text" class="form-control" placeholder="输入商品名称搜索" />
            <button id="search-history-order" class="btn bg-blue btn-block">历史订单</button>
        </div>
        <div class="swiper-container">
            <!--<ul class="swiper-container-ul" style="">
                <li class="swiper-container-ul-li actives">商品</li>
                <li class="swiper-container-ul-li">店铺</li>
              </ul>-->
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <div class="content" style="padding: 0px;font-size: 12px;">
                        <div id="left" class="left" style="top:117px;">
                            <ul>
                                <#assign gindex = 0>
                                <#if goodsOneTypeList??>
                                    <#list goodsOneTypeList as myn>
                                        <li id="${myn.id}" <#if gindex==0>class="active"</#if>>${myn.name}</li>
                                        <#assign gindex++>
                                    </#list>
                                </#if>
                            </ul>
                        </div>
                        <div id="right" class="right" style="">
                            <ul>
                                <#if goodsOneTypeList??>
                                    <#list goodsOneTypeList as got>
                                        <li>
                                            <div class="class-title">${got.name}</div>
                                             <#list goodsInfoList as myn>
                                                 <#if myn.goid==got.id>
                                                     <div class="item goods-ct" id="${myn.id!}" unit-type="${myn.unitType!}" price="<#if myn.tradePrice??>${myn.tradePrice!}<#else >0</#if>" unit="${myn.unitPrName!}" price-se="<#if myn.tradePriceSe??>${myn.tradePriceSe!}<#else >0</#if>" unit-se="${myn.unitPeName!}" goid="${myn.goid!}" gtid="${myn.gtid!}" name="${myn.chName!}" en_name="${myn.enName!}" supplier_name="${myn.supplierName!}" supplier_address="${myn.supplierAddress!}" siid="${myn.siid}">
                                                         <div class="item-left">
                                                             <div class="item-img">
                                                                 <#if myn.imgUrl??>
                                                                     <img onclick="myImageOpen(this)" src="${aliyunOos!}${myn.imgUrl!}" οnerrοr="this.οnerrοr='';src='/statics/admin/assets/images/default_goods.jpg'"/>
                                                                 <#else>
                                                                     <img src="/statics/admin/assets/images/default_goods.jpg"/>
                                                                 </#if>
                                                             </div>
                                                         </div>
                                                         <div class="item-right">
                                                             <div class="title">${myn.chName!}<br/>${myn.enName!}</div>
                                                             <div class="price">
                                                                 <#if myn.unitType==1>
                                                                     <span class="show-price"><#if myn.tradePrice??>${myn.tradePrice!}<#else >0</#if></span>P/<span class="show-unit">${myn.unitPrName!}</span>
<#--                                                                     ${myn.unitPrName!}(主)-->
                                                                 <#else >
                                                                     <span class="show-price"><#if myn.tradePriceSe??>${myn.tradePriceSe!}<#else >0</#if></span>P/<span class="show-unit">${myn.unitPeName!}</span>
<#--                                                                     ${myn.unitPeName!}(辅)-->
                                                                 </#if>
                                                                 <select class="unit-sel">
                                                                     <option value="1" <#if myn.unitType==1>selected=selected</#if>>主</option>
                                                                     <option value="2" <#if myn.unitType==2>selected=selected</#if>>辅</option>
                                                                 </select>
                                                             </div>
                                                         </div>
                                                         <div class="item-ico">
                                                             <i class="icon-minus-circle2 sub" type="sub"></i>
                                                             <input type="number" class="goods-number">
                                                             <i class="icon-plus-circle2 add" type="add"></i>
                                                         </div>
                                                     </div>
                                                 </#if>
                                             </#list>
                                        </li>
                                    </#list>
                                </#if>
                                <li><div class="class-title">&nbsp;</div></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="mask"></div>
        <div class="popup">
            <div class="uptitle"> <span>已选商品</span>
                <div class="tb">
                    <a id="clear-order"><i class="icon-bin"></i>清空</a>
                </div>
            </div>
            <div class="uplist">
                <ul class="order-list">
                </ul>
            </div>
            <div class="order-sel">
                <div class="order-sel-admin">
                    下单人：
                    <select id="mi_admin_id" style="margin-right: 20px;">
                        <#if merchantUserInfoList??>
                            <#list merchantUserInfoList as myn>
                                <#if myn.type==1>
                                    <option value="${myn.id}">${myn.name}</option>
                                </#if>
                            </#list>
                        </#if>
                    </select>
                </div>
                <br/>
                <div id="mi_address_id" class="address-info" <#if (merchantInfoAddresses?size>0)>ad-id="${merchantInfoAddresses[0].id}"</#if>>
                    <div class="address-info-ico">
                        <#if merchantInfoAddresses??>
                            <#if (merchantInfoAddresses?size>0)>
                                <#if merchantInfoAddresses[0].image??>
                                    <img onclick="myImageOpen(this)" width="50px" src="${aliyunOos!}${merchantInfoAddresses[0].image!}"/>
                                <#else>
                                    <i class="icon-location4"></i>
                                </#if>
                            </#if>
                            <#else>
                                <i class="icon-location4"></i>
                        </#if>
                    </div>
                    <div class="address-info-txt">
                        <#if merchantInfoAddresses??>
                            <#if (merchantInfoAddresses?size>0)>
                                <span id="s-name">${merchantInfoAddresses[0].name}</span>
                                &nbsp;
                                <span id="s-tel">${merchantInfoAddresses[0].tel}</span>
                                <p id="s-address">${merchantInfoAddresses[0].address}</p>
                                <#else >
<#--                                    <a href="/merchants/merchantInfoAddress/index" style="position: relative;top: 5px;">添加收获地址</a>-->
                                    暂无收货地址，请联系CII配置
                            </#if>
                        </#if>
                    </div>
                    <div class="address-info-ico" style="float: right;margin-right: 0px;">
                        <i class="icon-arrow-right13"></i>
                    </div>
                </div>
            </div>
        </div>
        <div class="shop">
            <div class="shopico">
                <i class="icon-cart2"></i>
                <div class="numspan"><span id="total-goods">0</span></div>
            </div>
            <div class="shopprice">
                总金额:<span id="total-price">0</span>P
                <span>
                    <#if merchantInfo.settlementMethod??>
                        <#if merchantInfo.settlementMethod==1>
                            现金结算
                        </#if>
                        <#if merchantInfo.settlementMethod==2>
                            按周结算
                        </#if>
                        <#if merchantInfo.settlementMethod==3>
                            按月结算
                        </#if>
                        <#else >
                        无结算方式
                    </#if>
                </span>
            </div>
            <div class="shopbut" id="submitOrderBtn">提交订单</div>
        </div>
    </div>
    <#include "../common/foot.ftl"/>
</div>
<script type="text/javascript">
    var display =$('.sidebar').css('display');
    var popWidth = "95%";
    if(display!="none"){
        $(".mask").width($(".mask").outerWidth(true)-260);
        $(".popup").width($(".popup").outerWidth(true)-260);
        $(".shop").width($(".shop").outerWidth(true)-260);
        $(".search-ct").width($(".shop").outerWidth(true)-260);
        $(".item-ico").css("right","-60px").css("margin-top","20px").css("float","left");
        popWidth = "388px";
    }

    var spStyle = ` position: fixed;
                        top: 0px;
                        z-index: 999;
                        width: 100%;`;
    $(".header-highlight").attr("style",spStyle);
    $(".sidebar-main").attr("style","position: relative;top: 46px;");

    $('#search-order-text').bind('input propertychange', function() {
        window.scroll(0, 0);
        var goodsName = $(this).val();
        if(goodsName){
            $(".goods-ct,.class-title").addClass("hidden");
            $(".goods-ct[name*='"+goodsName+"']").removeClass("hidden").show();
            $(".goods-ct[en_name*='"+goodsName+"']").removeClass("hidden").show();
        }else{
            $(".goods-ct,.class-title").removeClass("hidden").show();
        }
    });

    $("#search-history-order").click(function(){
        <#if orderInfoListTop??&&(orderInfoListTop?size>0) >
        var index = layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            anim: 1,
            shadeClose: true,
            content: `<table id="order-list-history" class="table">
                            <thead>
                                <tr><th>订单号</th><th>商品总价</th></tr>
                            </thead>
                            <tbody>
                            <#list orderInfoListTop as myn>
                                <tr id="${myn.id}">
                                    <td>${myn.orderNumber}</td>
                                    <td>${myn.sumPrice}</td>
                                </tr>
                            </#list>
                            </tbody>
                          </table>`,
            area:  [popWidth]   // 长，宽
        });
        $("#order-list-history tbody>tr").unbind("click").click(function(){
            var oiid = $(this).attr("id");
            $.ajax({
                url:"/merchants/orderInfo/orderInfoDetailByOiid?oiid="+oiid,
                type:"post",
                headers: {
                    'httpType': "JSON",
                },
                contentType: false,
                processData: false,
                async:false,
                success: function(data) {
                    var orderData = data.data;
                    if(data.statusCode==200){
                        for(var i = 0;i<orderData.length;i++){
                            var giid = orderData[i].giid;
                            var number = orderData[i].number;
                            for(var n = 0;n<number;n++){
                                $("#right ul #"+giid).find(".add").click();
                            }
                        }
                        layer.close(index);
                    }
                }
            });
        });
        <#else>
        layer.alert("暂无订单");
        </#if>
    });

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
            var unitType = orderDom.attr("unit-type");
            var price = orderDom.attr("price");
            price = parseFloat(price);
            var priceSe = orderDom.attr("price-se");
            var unit = orderDom.attr("unit");
            var unitSe = orderDom.attr("unit-se");
            var number = orderDom.find(".goods-number").val();
            number = parseInt(number);
            var totalPrice = (price*number);

            if(unitType==2){
                price = parseFloat(priceSe);
                unit = unitSe;
                totalPrice = (priceSe*number);
            }



            var orderInfo = {};
            orderInfo["moiid"] = oiid;
            orderInfo["giid"] = goodsId;
            orderInfo["siid"] = siid;
            orderInfo["price"] = price;
            orderInfo["unit"] = unit;
            orderInfo["unitType"] = unitType;
            orderInfo["number"] = number;
            orderInfo["totalPrice"] = totalPrice;
            order_list_detail.push(orderInfo);
        }
        return order_list_detail;
    }

    $("#submitOrderBtn").unbind("click").click(function(event){
        event.stopPropagation();//阻止冒泡事件
        var orderList = $(".order-list li");
        var mi_admin_id = $("#mi_admin_id").val();

        var mi_address_id = $("#mi_address_id").attr("ad-id");

        var receiveName = $("#mi_address_id").find("#s-name").text();
        var receiveTel = $("#mi_address_id").find("#s-tel").text();
        var receiveAddress = $("#mi_address_id").find("#s-address").text();

        if(orderList.length==0){
            layer.msg("请选择商品");
            return;
        }
        if(!mi_address_id){
            layer.msg("请选择收货地址");
            return;
        }
        var sum_price = $("#total-price").text();
        sum_price = parseFloat(sum_price);
        var sum_number = $("#total-goods").text();
        sum_number = parseInt(sum_number);

        var maskDisplay =$('.mask').css('display');
        if(maskDisplay=="none"){
            layer.msg("再次确认订单");
            $('.shop').click();
            return;
        }
        layer.confirm('订单确认', {
            btn: ['立即提交', '返回修改']
        }, function(index){
            submitOrderInfo(mi_admin_id,mi_address_id,receiveName,receiveTel,receiveAddress,sum_price,sum_number,function(){
                layer.close(index)
                layer.load(2);
                window.location.href = "/merchants/orderInfo/submitIndex";
            });
        });
    });

    function submitOrderInfo(mi_admin_id,mi_address_id,receiveName,receiveTel,receiveAddress,sum_price,sum_number,callBack){
        $.ajax({
            url: "/merchants/orderInfo/submitIndexIng",
            data: {
                miAdminId: mi_admin_id,
                miaid: mi_address_id,
                receiveName: receiveName,
                receiveTel: receiveTel,
                receiveAddress:receiveAddress,
                sumPrice:sum_price,
                sumNumber:sum_number
            },
            headers: {
                'httpType': "JSON",
            },
            type: "POST",
            async: false,
            success: function (data) {
                if (data.statusCode == 200) {
                    //订单创建成功，将创建订单详细商品数据
                    var orderId = data.data.id;
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
                        url: '/merchants/orderInfo/submitOrderDetailIng',
                        success: function(data){
                            if(data.statusCode==200){
                                if(callBack){
                                    callBack();
                                }
                            }else if(data.statusCode==203){
                                layer.alert("权限不足")
                            }else{
                                layer.alert(data.message);
                            }
                        }
                    });
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }
            }
        });
    }

    $(".address-info").unbind("click").click(function(){
        <#if merchantInfoAddresses??>
        <#if (merchantInfoAddresses?size>0)>
        var addressIndex = layer.open({
            type: 1,
            title:"选择收货地址",
            // skin: 'layui-layer-rim', //加上边框
            area: [popWidth], //宽高
            content: `<#if merchantInfoAddresses??>
                    <div id="address-list">
                    <#list merchantInfoAddresses as myn>
                        <div class="address-info" id="${myn.id}">
                            <div class="address-info-ico">
                                <#if myn.image??>
                                    <img onclick="myImageOpen(this)" width="50px" src="${aliyunOos!}${myn.image!}"/>
                                    <#else >
                                    <i class="icon-location4"></i>
                                </#if>
                            </div>
                            <div class="address-info-txt">
                                <span id="s-name">${myn.name}</span>
                                &nbsp;
                                <span id="s-tel">${myn.tel}</span>
                                <p id="s-address">${myn.address}</p>
                            </div>
                            <div class="address-info-ico" style="float: right;margin-right: 0px;">
                                <i class="icon-arrow-right13"></i>
                            </div>
                        </div>
                    </#list>
                    </div>
                </#if>`
        });
        $("#address-list .address-info").unbind("click").click(function(){
            var thisDom = $(this);
            var id = thisDom.attr("id");
            var receiveName = thisDom.find("#s-name").text();
            var receiveTel = thisDom.find("#s-tel").text();
            var receiveAddress = thisDom.find("#s-address").text();
            var image = thisDom.find("img").attr("src");
            $("#mi_address_id").attr("ad-id",id);
            $("#mi_address_id").find("#s-name").text(receiveName);
            $("#mi_address_id").find("#s-tel").text(receiveTel);
            $("#mi_address_id").find("#s-address").text(receiveAddress);
            if(image){
                $("#mi_address_id").find("img").attr("src",image);
            }
            layer.close(addressIndex);
        });
        </#if>
        </#if>
    });

    $(".goods-ct .add,.sub").unbind("click").click(function(){
        try{
            var thisDom = $(this);
            var goodsDom = thisDom.parents(".goods-ct");
            var id = goodsDom.attr("id");
            var price = goodsDom.attr("price");
            var unit = goodsDom.attr("unit");
            var name = goodsDom.attr("name");
            var en_name = goodsDom.attr("en_name");
            var supplier_name = goodsDom.attr("supplier_name");
            var supplier_address = goodsDom.attr("supplier_address");
            var siid = goodsDom.attr("siid");

            var unitType = goodsDom.attr("unit-type");
            var priceSe = goodsDom.attr("price-se");
            var unitSe = goodsDom.attr("unit-se");

            var showPrice = price;
            var showUnit = unit;
            if(unitType==2){
                showPrice = priceSe;
                showUnit = unitSe;
            }

            var goodsNumberDom = goodsDom.find(".goods-number");
            var goodsNumber = goodsNumberDom.val();
            if(!goodsNumber){
                goodsNumber = 0;
            }
            goodsNumber = parseInt(goodsNumber);
            var type = thisDom.attr("type");
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
            var imgUrl = goodsDom.find("img").attr("src");
            var goodsInfo = $(".order-list li[id='ol-"+id+"']");
            if(goodsInfo.length!=0){
                goodsInfo.find(".goods-number").val(goodsNumber);
                if(goodsNumber==0){
                    goodsInfo.remove();
                }
            }else if(goodsNumber>0){
                var olId = "ol-"+id;
                var goodsInfoHtml = `<li id="`+olId+`" gl-id="`+id+`" class="clearfix" price=`+price+` unit=`+unit+` price-se=`+priceSe+` unit-se=`+unitSe+` unit-type=`+unitType+` siid=`+siid+` supplier_name=`+supplier_name+` supplier_address=`+supplier_address+`>
                                    <div class="uppic">
                                        <img src="`+imgUrl+`">
                                    </div>
                                    <div class="listtitle">
                                        <div class="title">`+name+`<br/>`+en_name+`</div>
                                        <div class="price"><span class="show-price">`+showPrice+`</span>P/<span class="show-unit">`+showUnit+`</span>
                                        <select class="unit-sel">`;
                                        if(unitType==1){
                                            goodsInfoHtml += `<option value="1" selected=selected>主</option>
                                                                                                          <option value="2">辅</option>`;
                                        }else{
                                            goodsInfoHtml += `<option value="1">主</option>
                                                                                                          <option value="2" selected=selected>辅</option>`;
                                        }
                                    goodsInfoHtml+=`</select>`
                                    goodsInfoHtml+=`</div></div>
                                    <div class="item-ico">
                                        <i class="icon-minus-circle2 sub o-sub" type="sub"></i>
                                        <input type="number" class="goods-number" value="1">
                                        <i class="icon-plus-circle2 add o-add" type="add"></i>
                                    </div>
                                </li>`;
                $(".order-list").append(goodsInfoHtml);
                $(".order-list #"+olId+" .o-sub,.o-add").unbind("click").click(function(){
                    var sadDom = $(this);
                    var parentDom = $(this).parents("li[gl-id]");
                    var goodsNumberDom = parentDom.find(".goods-number");
                    var goodsNumber = parseInt(goodsNumberDom.val());
                    var type = sadDom.attr("type");
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
                    var glId = parentDom.attr("gl-id");
                    $(".goods-ct[id='"+glId+"']").find(".goods-number").val(goodsNumber);
                    goodsNumberDom.val(goodsNumber);
                    if(goodsNumber==0){
                        parentDom.remove();
                    }
                    //重新计算选择商品的总价格、总数量
                    sumGoodsTotalNumberAndPrice();
                });
                $(".order-list #"+olId+" .unit-sel").unbind("change").change(function(){
                    var sadDom = $(this);
                    var parentDom = sadDom.parents("li[gl-id]");
                    var goodsDom = $(".right #"+parentDom.attr("gl-id"));
                    var unitType = sadDom.val();
                    var price = parentDom.attr("price");
                    var unit = parentDom.attr("unit");
                    if(unitType==2){
                        price = parentDom.attr("price-se");
                        unit = parentDom.attr("unit-se");
                    }
                    parentDom.find(".show-price").text(price);
                    parentDom.find(".show-unit").text(unit);
                    parentDom.attr("unit-type",unitType);
                    if(goodsDom){
                        goodsDom.find(".show-price").text(price);
                        goodsDom.find(".show-unit").text(unit);
                        goodsDom.attr("unit-type",unitType);
                        goodsDom.find(".unit-sel").val(unitType);
                    }
                    sumGoodsTotalNumberAndPrice();
                });
            }
            //重新计算选择商品的总价格、总数量
            sumGoodsTotalNumberAndPrice();
        }catch (e) {
            console.log(e);
        }
    });

    $(".goods-ct .unit-sel").unbind("change").change(function(){
        var sadDom = $(this);
        var parentDom = sadDom.parents(".goods-ct");
        var orderDom = $("li[gl-id='"+parentDom.attr("id")+"']");
        var unitType = sadDom.val();
        var price = parentDom.attr("price");
        var unit = parentDom.attr("unit");
        if(unitType==2){
            price = parentDom.attr("price-se");
            unit = parentDom.attr("unit-se");
        }
        parentDom.find(".show-price").text(price);
        parentDom.find(".show-unit").text(unit);
        parentDom.attr("unit-type",unitType);
        if(orderDom){
            orderDom.find(".show-price").text(price);
            orderDom.find(".show-unit").text(unit);
            orderDom.attr("unit-type",unitType);
            orderDom.find(".unit-sel").val(unitType);
        }
        sumGoodsTotalNumberAndPrice();
    });

    $("#clear-order").unbind("click").click(function(){
        $(".order-list").html("");
        $(".goods-number").val("");
        sumGoodsTotalNumberAndPrice();
    });

    function sumGoodsTotalNumberAndPrice(){
        var totalNumber = 0;//选择商品的总数量
        var totalPrice = 0;//选择商品的总价格
        var orderList = $(".order-list li[price]");
        for(var i = 0;i<orderList.length;i++){
            var orderDom = orderList.eq(i);
            var unitType = orderDom.attr("unit-type");
            var number = orderDom.find(".goods-number").val();
            number = parseInt(number);
            totalNumber+=number;

            var price = orderDom.attr("price");
            if(unitType==2){
                price = orderDom.attr("price-se");
            }
            price = parseFloat(price);
            totalPrice+=(price*number);
        }
        $("#total-goods").text(totalNumber);
        $("#total-price").text(totalPrice);
    }


    $('.content').css('height',$('.right').height());
    $('.left ul li').eq(0).addClass('active');
    $(window).scroll(function(){
        if($(window).scrollTop() >= 150){
            $('.swiper-container-ul').css('position','fixed');
            $('.left').css('position','fixed');
            $('.right').css('margin-left',$('.left').width());
        }else {
            $('.swiper-container-ul').css('position','');
            $('.left').css('position','');
            $('.right').css('margin-left','');
        };
        //滚动到标杆位置,左侧导航加active
        $('.right ul li').each(function(){
            var target = parseInt($(this).offset().top-$(window).scrollTop()-130);
            //alert(target);
            var i = $(this).index();
            if (target<=0) {
                $('.left ul li').removeClass('active');
                $('.left ul li').eq(i).addClass('active');
            }
        });
    });
    $('.left ul li').click(function(){
        var i = $(this).index('.left ul li');
        $('body, html').animate({scrollTop:$('.right ul li').eq(i).offset().top-117},500);
    });
    //购物车点击
    $('.shop').click(function(){
        $('.mask').show();
        var maskHeight = $(window).height();
        var uplisHeight = ((maskHeight/2)-146)+58;
        $(".popup .uplist").css("height",uplisHeight);
        $('.popup').css("bottom","50px");

    });
    $('.mask').click(function(){
        $('.mask').hide();
        var maskHeight = $(window).height();
        $('.popup').css("bottom",((-maskHeight/2)-58)+"px");
    });


    function b(){
        t = parseInt(x.css('top'));
        y.css('top','19px');
        x.animate({top: t - 19 + 'px'},'slow');	//19为每个li的高度
        if(Math.abs(t) == h-19){ //19为每个li的高度
            y.animate({top:'0px'},'slow');
            z=x;
            x=y;
            y=z;
        }
        setTimeout(b,3000);//滚动间隔时间 现在是3秒
    }

    $('.swap').html($('.news_li').html());
    x = $('.news_li');
    y = $('.swap');
    h = $('.news_li li').length * 19; //19为每个li的高度
    setTimeout(b,3000);//滚动间隔时间 现在是3秒
</script>
</body>
</html>