
<#--<!DOCTYPE html>-->
<#--<html lang="en">-->
<#--<head>-->
<#--    <#if mode==1>-->
<#--        <#assign title="商品入库">-->
<#--    </#if>-->
<#--    <#if mode==2>-->
<#--        <#assign title="商品出库">-->
<#--    </#if>-->
<#--    <#if mode==3>-->
<#--        <#assign title="库存盘点">-->
<#--    </#if>-->
<#--    <#include "../common/head.ftl"/>-->
    <link href="/statics/admin/assets/css/order.css" rel="stylesheet" type="text/css">
<#--    <script language="javascript" src="/statics/admin/assets/js/plugins/lodop/print.js"></script>-->
<#--    <style>-->
<#--        .pure-table {-->
<#--            display: none;-->
<#--        }-->
<#--        .layui-layer-btn1{-->
<#--            border-color: #e9928f!important;-->
<#--            background-color: #dd8686!important;-->
<#--            color: #fff!important;-->
<#--        }-->
<#--    </style>-->
<#--</head>-->
<#--<body>-->
<#--<#include "../common/top.ftl"/>-->
<div class="page-container">

    <div class="page-content">
<#--        <#assign menuType="goodsInfo">-->
<#--        <#include "../common/menu.ftl"/>-->
        <div class="content-wrapper">
            <div class="content">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="panel panel-flat">
                            <div class="panel-body">
                                <div class="form-group clearfix">
                                    <div class="col-lg-6">
                                        <input id="search-order-text" type="text" class="form-control" placeholder="输入商品名称搜索"/>
                                    </div>
                                    <div class="col-lg-3">
                                        <button id="search-order-btn" class="btn bg-blue btn-block">搜索</button>
                                    </div>
<#--                                    <div class="col-lg-3">-->
<#--                                        <button id="search-history-order" class="btn bg-blue btn-block">历史订单</button>-->
<#--                                    </div>-->
                                </div>
                                <div class="form-group clearfix">
<#--                                    <div class="col-lg-3">-->
<#--                                        <select id="sel-show-mode" class="form-control">-->
<#--                                            <option value="img-show">图标显示</option>-->
<#--                                            <option value="txt-show">文字显示</option>-->
<#--                                        </select>-->
<#--                                    </div>-->
                                    <div class="col-lg-3">
                                        <select id="goid" class="form-control">
                                            <option value="all">选择分类</option>
                                            <#if goodsOneTypeList??>
                                                <#list goodsOneTypeList as myn>
                                                    <option value="${myn.id!}">${myn.name!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>
                                    <div class="col-lg-6">
                                        <ul class="tab-goods-type clearfix">
                                            <li id="all" goid="all">全部</li>
                                            <#if goodsTowTypeList??>
                                                <#list goodsTowTypeList as myn>
                                                    <li id="${myn.id!}" goid="${myn.goid!}" >${myn.name!}</li>
                                                </#list>
                                            </#if>
                                        </ul>
                                    </div>
                                </div>
                                <hr/>
                                <div class="form-group">
                                    <div class="col-lg-12 goods-list">
                                        <#assign index=0>
                                        <#if goodsInfoList??>
                                            <div id="img-show" >
                                            <#list goodsInfoList as myn>
                                                <#assign index++>
                                                <#if myn.siid??>
<#--                                                    <#if (index+3)%4==0>-->
<#--                                                        <div class="col-lg-12" style="border-bottom: 1px solid #e0dbdb;">-->
<#--                                                    </#if>-->
                                                            <div style="height: 280px;" class="col-lg-3 goods-ct"  id="${myn.id!}" unit-type="${myn.unitType!}" price="<#if myn.price??>${myn.price!}<#else >0</#if>" unit="${myn.unitPrName!}" price-se="<#if myn.priceSe??>${myn.priceSe!}<#else >0</#if>" unit-se="${myn.unitPeName!}" goid="${myn.goid!}" gtid="${myn.gtid!}" name="${myn.chName!}" en_name="${myn.enName!}" supplier_name="${myn.supplierName!}" supplier_address="${myn.supplierAddress!}" siid="${myn.siid}" stock="${myn.stock!}" stock-se="${myn.stockSe!}" class="hidden">
<#--                                                                <span class="sel-number">0</span>-->
<#--                                                                style="background-image: url('${aliyunOos!}${myn.imgUrl!}'),url('/statics/admin/assets/images/default_goods.jpg')"-->
                                                                <div class="goods-img" >
                                                                    <#if myn.imgUrl??>
                                                                        <img src="${aliyunOos!}${myn.imgUrl!}" οnerrοr="this.οnerrοr='';src='/statics/admin/assets/images/default_goods.jpg'"/>
                                                                    <#else>
                                                                        <img src="/statics/admin/assets/images/default_goods.jpg"/>
                                                                    </#if>
                                                                </div>
                                                                <p class="goods-info">
                                                                    <span class="name">${myn.chName!}<br/>${myn.enName!}</span>
                                                                    <br/>
                                                                    <span class="price-unit red">
<#--                                                                        <#if myn.unitType==1>-->
                                                                            <#if myn.price??>${myn.price!}<#else >0</#if>P/
                                                                            ${myn.unitPrName!}(主)<br/>库存：${myn.stock!}
                                                                            <br/>
<#--                                                                        <#else >-->
                                                                            <#if myn.priceSe??>${myn.priceSe!}<#else >0</#if>P/
                                                                            ${myn.unitPeName!}(辅)<br/>库存：${myn.stockSe!}
<#--                                                                        </#if>-->
                                                                    </span>
<#--                                                                    <br/>-->
<#--                                                                    <span class="supplier_name">供应商：${myn.supplierName!}</span>-->
                                                                </p>
                                                            </div>
<#--                                                    <#if index%4==0>-->
<#--                                                        </div>-->
<#--                                                    </#if>-->
                                                </#if>
                                            </#list>
                                            </div>
<#--                                            <div id="txt-show" class="table-responsive" style="display: none">-->
<#--                                            <table class="table">-->
<#--                                                <thead>-->
<#--                                                    <tr class="border-bottom-danger">-->
<#--                                                        <th>编号</th>-->
<#--                                                        <th>名称</th>-->
<#--                                                        <th>单位</th>-->
<#--                                                        <th>价格</th>-->
<#--                                                        <th>供应商</th>-->
<#--                                                    </tr>-->
<#--                                                </thead>-->
<#--                                                <tbody>-->
<#--                                                    <#if goodsInfoList??>-->
<#--                                                        <#list goodsInfoList as myn>-->
<#--                                                            <tr class="goods-ct"  id="${myn.id!}" unit-type="${myn.unitType!}" price="<#if myn.price??>${myn.price!}<#else >0</#if>" unit="${myn.unitPrName!}" price-se="<#if myn.priceSe??>${myn.priceSe!}<#else >0</#if>" unit-se="${myn.unitPeName!}" goid="${myn.goid!}" gtid="${myn.gtid!}" name="${myn.chName!}" en_name="${myn.enName!}" supplier_name="${myn.supplierName!}" supplier_address="${myn.supplierAddress!}" siid="${myn.siid}" class="hidden">-->
<#--                                                                <td>-->
<#--&lt;#&ndash;                                                                    <span class="sel-number">0</span>&ndash;&gt;-->
<#--                                                                    ${myn.code!}-->
<#--                                                                </td>-->
<#--                                                                <td>${myn.chName!}<br/>(${myn.enName!})</td>-->
<#--                                                                <td>-->
<#--                                                                    <#if myn.unitType==1>-->
<#--                                                                        ${myn.unitPrName!}(主)-->
<#--                                                                    <#else >-->
<#--                                                                        ${myn.unitPeName!}(辅)-->
<#--                                                                    </#if>-->
<#--                                                                </td>-->
<#--                                                                <td>-->
<#--                                                                    <#if myn.unitType==1>-->
<#--                                                                        <#if myn.price??>${myn.price!}<#else >0</#if>-->
<#--                                                                    <#else >-->
<#--                                                                        <#if myn.priceSe??>${myn.priceSe!}<#else >0</#if>-->
<#--                                                                    </#if>-->
<#--                                                                </td>-->
<#--                                                                <td>-->
<#--                                                                    ${myn.supplierName!}-->
<#--                                                                </td>-->
<#--                                                            </tr>-->
<#--                                                        </#list>-->
<#--                                                    </#if>-->
<#--                                                </tbody>-->
<#--                                            </table>-->
<#--                                            </div>-->
                                        </#if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="panel panel-flat">
                            <div class="panel-heading">
                                <h6 class="panel-title">
                                    <#if mode==1>入库列表</#if>
                                    <#if mode==2>出库列表</#if>
                                    <#if mode==3>盘点列表</#if>
                                    <a id="clear-order" style="float: right;font-size: 13px;">
                                        <i class="icon-bin"></i>
                                        全部清除
                                    </a>
                                </h6>
                                <hr/>
                                <div class="sel-order-ct" style="height: 300px;">
                                    <ul class="order-list">
                                    </ul>
                                </div>
                                <div class="submit-ct">
                                    <p class="order-info">
                                        商品总数：<span id="total-goods">0</span>&nbsp&nbsp账单金额：<span id="total-price">0</span>P
                                    </p>
                                    <label class="col-xs-12">
                                        <select id="typeName" class="form-control">
                                            <#if mode==1>
                                                <option value="手动入库">手动入库</option>
                                            </#if>
                                            <#if mode==2>
                                                <option value="手动出库">手动出库</option>
                                                <option value="商品损耗">商品损耗</option>
                                            </#if>
                                            <#if mode==3>
                                                <option value="库存盘点">库存盘点</option>
                                            </#if>
                                        </select>
                                    </label>
                                    <label class="col-xs-12">
                                        <input type="file"  id="file"  class="form-control" placeholder="单据图片">
                                    </label>
                                    <label class="col-xs-12">
                                        <textarea id="remark" name="remark" class="form-control" placeholder="备注"></textarea>
                                    </label>
                                    <button id="submit-order" class="btn bg-blue btn-block">确定<#if mode==1>入库</#if><#if mode==2>出库</#if><#if mode==3>盘点</#if></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
<#--                <#include "../common/foot.ftl"/>-->
            </div>
        </div>
    </div>
</div>
<script>
    eventInit();

    $("#clear-order").click(function(){
        $(".order-list").html("");
        // $(".sel-number").text(0);
        sumGoodsTotalNumberAndPrice();
    });
    /**
     * oiid 商品库存记录id
     * 获得选择商品列表JSONArray
     */
    function getGoodsArrList(gsiid){
        var orderList = $(".order-list>li");
        var order_list_detail = [];
        //遍历选好的商品信息转换成JSONArray
        for(var i = 0;i < orderList.length;i++){
            var orderDom = orderList.eq(i);
            var giid = orderDom.attr("gl-id");
            var unitType = orderDom.attr("unit-type");
            var price = orderDom.attr("price");
            price = parseFloat(price);
            var priceSe = orderDom.attr("price-se");
            priceSe = parseFloat(priceSe);
            var unit = orderDom.attr("unit");

            var number = orderDom.find(".goods-number").val();
            number = parseInt(number);
            var totalPrice = (price*number);

            var goodsName = orderDom.find(".name").html();

            var orderInfo = {};
            orderInfo["gsiid"] = gsiid;
            orderInfo["giid"] = giid;
            orderInfo["price"] = price;
            orderInfo["unit"] = unit;
            orderInfo["unitType"] = unitType;
            orderInfo["number"] = number;
            orderInfo["goodsName"] = goodsName;
            orderInfo["type"] = ${mode!};
            orderInfo["totalPrice"] = totalPrice;

            order_list_detail.push(orderInfo);
        }
        return order_list_detail;
    }


    /**
     * 事件初始化
     */
    function eventInit(){
        // $("#sel-show-mode").change(function(){
        //    var mode = $(this).val();
        //    $("#img-show,#txt-show").hide();
        //    $("#"+mode).show();
        // });
        // $("#sel-show-mode").change();

        $("#submit-order").click(function(){
            var orderList = $(".order-list>li");
            var type = <#if mode==1||mode==2||mode==3>${mode}</#if>;
            var typeName = $("#typeName").val();
            if(orderList.length==0){
                layer.alert('请选择商品');
                return;
            }
            var file = $("#file");
            // if(!file.val()){
            //     layer.msg("请选择单据图片");
            //     return;
            // }

            var sum_price = $("#total-price").text();
            sum_price = parseFloat(sum_price);
            var sum_number = $("#total-goods").text();
            sum_number = parseInt(sum_number);
            var tipsText = "<#if mode==1>入库</#if><#if mode==2>出库</#if><#if mode==3>盘点</#if>";
            layer.confirm('确定'+tipsText+'吗？', {
                btn: ['确定', '取消']
            }, function(index){
                layer.close(index);
                submitOrderInfo(sum_number,sum_price,type,typeName,file,function(){
                    window.location.href = "/admin/goodsStockInfo/index?type="+type;
                });
            });

        });

        function submitOrderInfo(stock_number,total_price,type,typeName,file,callBack){
            var remark = $("#remark").val();
            var formData = new FormData();
            formData.append("file",file[0].files[0]);
            formData.append("stockNumber",stock_number);
            formData.append("totalPrice",total_price);
            formData.append("type",type);
            formData.append("typeName",typeName);
            formData.append("remark",remark);
            $.ajax({
                url:"/admin/goodsStockInfo/stockInputIng",
                data:formData,
                headers: {
                    'httpType': "JSON",
                },
                type:"POST",
                async:false,
                contentType: false,
                processData: false,
                success:function (data) {
                    if(data.statusCode==200){
                        //订单创建成功，将创建订单详细商品数据
                        var gsiid = data.data.id;
                        var goodsArrList = getGoodsArrList(gsiid);
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
                            url: '/admin/goodsStockInfo/submitGoodsStockInfoDetailIng?mode=${mode}',
                            success: function(data){
                                if(data.statusCode==200){
                                    if(callBack){
                                        callBack();
                                    }
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

        goodsSelEvent();

        /**
         * 根据商品名称搜索
         */
        $("#search-order-btn").click(function (){
            var goodsName = $("#search-order-text").val();
            $("#goid option[value='all']:selected");
            $("#goid").change();
            if(goodsName){
                $(".goods-list .goods-ct").addClass("hidden");
                $(".goods-list .goods-ct[name*='"+goodsName+"']").removeClass("hidden").show();
                $(".goods-list .goods-ct[en_name*='"+goodsName+"']").removeClass("hidden").show();
            }else{
                $(".goods-list .goods-ct").removeClass("hidden").show();
            }
        });

        /**
         * 二级分类点击事件
         */
        $(".tab-goods-type li").click(function(){
            var id = $(this).attr("id");
            $(".tab-goods-type li").removeClass("sel");
            $(this).addClass("sel");
            $(".goods-list .goods-ct").addClass("hidden");
            if(id=="all"){
                var goid = $("#goid option:selected").val();
                if(goid=="all"){
                    $(".goods-list .goods-ct").removeClass("hidden").show();
                }else{
                    $(".goods-list .goods-ct[goid='"+goid+"']").removeClass("hidden").show();
                }

            }else{
                $(".goods-list .goods-ct[gtid='"+id+"']").removeClass("hidden").show();
            }
        });
        /**
         * 一级分类选择事件
         */
        $("#goid").change(function(){
            var value = $("#goid option:selected").val();
            $(".tab-goods-type li").removeClass("sel").addClass("hidden");
            $(".tab-goods-type li[goid='all']").removeClass("hidden").show();
            $(".tab-goods-type li[goid='"+value+"']").removeClass("hidden").show();
            $(".tab-goods-type li[class!='hidden']:first").click();
        });
        $("#goid").change();
    }

    function goodsSelEvent(){
        /**
         * 商品选择事件
         */
        $(".goods-list .goods-ct").unbind("click").click(function(){
            var goodsDom = $(this);
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
            var stock = goodsDom.attr("stock");
            var stockSe = goodsDom.attr("stock-se");

            // var showPrice = price;
            // var showUnit = unit;
            // if(unitType==2){
            //     showPrice = priceSe;
            //     showUnit = unitSe;
            // }
            // var selNumber = goodsDom.find(".sel-number").text();
            // selNumber = parseInt(selNumber);
            // selNumber++;
            var index = layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                anim: 1,
                shadeClose: true,
                content: `<table id="sel-goods-norms" class="table">
                            <thead>
                                <tr><th>品名</th><th>单位</th><th>单价</th><th>现有库存</th><th><#if mode==1>入库</#if><#if mode==2>出库</#if><#if mode==3>盘点</#if>数量</th></tr>
                            </thead>
                            <tbody>
                                <tr id="`+id+`" unit-type="1" price="`+price+`">
                                    <td class="name">`+name+"<br/>"+en_name+`</td>
                                    <td class="unit">`+unit+`(主)</td>
                                    <td class="price"><input type="number" class="form-control" <#if mode==2>value="`+price+`" disabled</#if>/></td>
                                    <td class="stock">`+stock+`</td>
                                    <td><input type="number" style="width:88px;" class="stock-number form-control" /></td>
                                </tr>
                                <tr id="`+id+`" unit-type="2" price="`+priceSe+`">
                                    <td class="name">`+name+"<br/>"+en_name+`</td>
                                    <td class="unit">`+unitSe+`(辅)</td>
                                    <td class="price"><input type="number" class="form-control" <#if mode==2>value="`+priceSe+`" disabled</#if>/></td>
                                    <td class="stock">`+stockSe+`</td>
                                    <td><input type="number" style="width:88px;" class="stock-number form-control"/></td>
                                </tr>
                            </tbody>
                            <tfoot><tr><td colspan="5"><button id="confirm-sel" class="btn bg-blue btn-block">确认</button></td></tr></tfoot>
                          </table>`,
                area:  ["700px"]   // 长，宽
            });
            $("#confirm-sel").unbind("click").click(function(){
                var selNormsList = $("#sel-goods-norms>tbody>tr");
                for(var i = 0;i<selNormsList.length;i++){
                    var selNorms = selNormsList.eq(i);
                    var id = selNorms.attr("id");
                    var unit_type = selNorms.attr("unit-type");
                    var price = selNorms.find(".price>input").val();
                    var name = selNorms.find(".name").html();
                    var unit = selNorms.find(".unit").text();
                    var stock = selNorms.find(".stock").text();
                    var stockNumber = selNorms.find(".stock-number").val();
                    if(stockNumber&&stockNumber>0&&price&&price>0){
                        var goodsInfo = $(".order-list li[id='ol-"+id+"'][unit-type='"+unit_type+"']");
                        if(goodsInfo.length!=0){
                            var goodsNumberDom = goodsInfo.find(".goods-number");
                            var goodsNumber = parseInt(goodsNumberDom.val());
                            var curNum = goodsNumber+parseInt(stockNumber);
                            goodsNumberDom.val(curNum);
                        }else{
                            var olId = "ol-"+id;
                            var goodsInfoHtml = `<li id="`+olId+`" gl-id="`+id+`" class="clearfix" price=`+price+` unit=`+unit+` unit-type=`+unit_type+`>
                                            <ul>
                                                <li class="ol-left">
                                                    <span class="name">`+name+`</span><br/>
                                                    <span class="red price_unit">
                                                        <span class="show-price">`+price+`</span>P/<span class="show-unit">`+unit+`</span>
                                                        </span><br/>
                                                </li>
                                                <li class="ol-right">
                                                    <a href="javascript:void(0)" class="sub">-</a>
                                                    <input type="number" class="goods-number" value="`+stockNumber+`"/>
                                                    <a href="javascript:void(0)" class="add">+</a>
                                                    <a href="javascript:void(0)" class="del">×</a>
                                                </li>
                                            </ul>
                                        </li>`;
                            $(".order-list").append(goodsInfoHtml);
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
                                if(type=="del"||goodsNumber==0){
                                    parentDom.remove();
                                }
                                //重新计算选择商品的总价格、总数量
                                sumGoodsTotalNumberAndPrice();
                            });
                            $("#"+olId+" .goods-number").unbind("input propertychange").bind('input propertychange',function () {
                                //重新计算选择商品的总价格、总数量
                                sumGoodsTotalNumberAndPrice();
                            });
                        }
                    }
                }
                // 重新计算选择商品的总价格、总数量
                sumGoodsTotalNumberAndPrice();
                layer.close(index);
            });


//             var goodsInfo = $(".order-list li[id='ol-"+id+"']");
//             //如果订单列表已存在该商品则商品数量加1，否则订单列表加入此商品
//             if(goodsInfo.length!=0){
//                 var goodsNumberDom = goodsInfo.find(".goods-number");
//                 goodsNumberDom.val(selNumber);
//             }else{
//                 var olId = "ol-"+id;
//                 var goodsInfoHtml = `<li id="`+olId+`" gl-id="`+id+`" class="clearfix" price=`+price+` unit=`+unit+` price-se=`+priceSe+` unit-se=`+unitSe+` unit-type=`+unitType+` siid=`+siid+` supplier_name=`+supplier_name+` supplier_address=`+supplier_address+`>
//                                             <ul>
//                                                 <li class="ol-left">
//                                                     <span class="ch_name">`+name+`</span><br/>
//                                                     <span class="en_name">`+en_name+`</span><br/>
//                                                     <span class="red price_unit">
//                                                         <span class="show-price">`+showPrice+`</span>P/<span class="show-unit">`+showUnit+`</span>
//                                                         </span><br/>
// <!--                                                    <span class="supplier_name">供应商：`+supplier_name+`</span>-->
//                                                 </li>
//                                                 <li class="ol-right">
//                                                     <a href="javascript:void(0)" class="sub">-</a>
//                                                     <input type="number" class="goods-number" value="1"/>
//                                                     <a href="javascript:void(0)" class="add">+</a>
//                                                     <a href="javascript:void(0)" class="del">×</a>
//                                                 </li>
//                                             </ul>
//                                         </li>`;
//                 $(".order-list").append(goodsInfoHtml);
//                 $("#"+olId+" .sub,.add,.del").unbind("click").click(function(){
//                     var sadDom = $(this);
//                     var parentDom = $(this).parents("li[gl-id]");
//                     var goodsNumberDom = parentDom.find(".goods-number");
//                     var goodsNumber = parseInt(goodsNumberDom.val());
//                     var type = sadDom.attr("class");
//                     if(type=="sub"){
//                         goodsNumber--;
//                         if(goodsNumber<=0){
//                             goodsNumber = 0;
//                         }
//                     }else if(type=="add"){
//                         goodsNumber++;
//                     }else if(type=="del"){
//                         goodsNumber = 0;
//                     }
//                     goodsNumberDom.val(goodsNumber);
//                     if(type=="del"||goodsNumber==0){
//                         parentDom.remove();
//                     }
//                     //重新计算选择商品的总价格、总数量
//                     sumGoodsTotalNumberAndPrice();
//                 });
//                 $("#"+olId+" .goods-number").unbind("input propertychange").bind('input propertychange',function () {
//                     //重新计算选择商品的总价格、总数量
//                     sumGoodsTotalNumberAndPrice();
//                 });
//             }
            //重新计算选择商品的总价格、总数量
            // sumGoodsTotalNumberAndPrice();
        });
    }

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
            // if(unitType==2){
            //     price = orderDom.attr("price-se");
            // }
            price = parseFloat(price);
            totalPrice+=(price*number);
        }
        $("#total-goods").text(totalNumber);
        $("#total-price").text(totalPrice);
    }
</script>
<#--</body>-->
<#--</html>-->



