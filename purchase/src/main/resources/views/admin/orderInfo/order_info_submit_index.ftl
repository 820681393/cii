
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="商品信息">
    <#include "../common/head.ftl"/>
    <link href="/statics/admin/assets/css/order.css" rel="stylesheet" type="text/css">
<#--    <!-- 控件跨浏览器调用类库&ndash;&gt;-->
<#--    <script language="javascript" src="/statics/admin/assets/js/plugins/lodop/LodopFuncs.js"></script>-->
<#--    <!-- 调用LODOP控件代码&ndash;&gt;-->
<#--    <object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>-->
<#--        <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="install_lodop.exe"></embed>-->
<#--    </object>-->
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
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="orderInfo">
        <#include "../common/menu.ftl"/>
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
                                    <div class="col-lg-3">
                                        <button id="search-history-order" class="btn bg-blue btn-block">历史订单</button>
                                    </div>
                                </div>
                                <div class="form-group clearfix">
                                    <div class="col-lg-3">
                                        <select id="sel-show-mode" class="form-control">
                                            <option value="img-show">图标显示</option>
                                            <option value="txt-show">文字显示</option>
                                        </select>
                                    </div>
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
                                            <div id="img-show" style="display: none">
                                            <#list goodsInfoList as myn>
                                                <#assign index++>
                                                <#if myn.siid??>
<#--                                                    <#if (index+3)%4==0>-->
<#--                                                        <div class="col-lg-12" style="border-bottom: 1px solid #e0dbdb;">-->
<#--                                                    </#if>-->
                                                            <div class="col-lg-3 goods-ct"  id="${myn.id!}" unit-type="${myn.unitType!}" price="<#if myn.price??>${myn.price!}<#else >0</#if>" unit="${myn.unitPrName!}" price-se="<#if myn.priceSe??>${myn.priceSe!}<#else >0</#if>" unit-se="${myn.unitPeName!}" goid="${myn.goid!}" gtid="${myn.gtid!}" name="${myn.chName!}" en_name="${myn.enName!}" supplier_name="${myn.supplierName!}" supplier_address="${myn.supplierAddress!}" siid="${myn.siid}" class="hidden">
                                                                <span class="sel-number">0</span>
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
                                                                        <#if myn.unitType==1>
                                                                            <#if myn.price??>${myn.price!}<#else >0</#if>P/
                                                                            ${myn.unitPrName!}(主)
                                                                        <#else >
                                                                            <#if myn.priceSe??>${myn.priceSe!}<#else >0</#if>P/
                                                                            ${myn.unitPeName!}(辅)
                                                                        </#if>
                                                                    </span>
                                                                    <br/>
                                                                    <span class="supplier_name">供应商：${myn.supplierName!}</span>
                                                                </p>
                                                            </div>
<#--                                                    <#if index%4==0>-->
<#--                                                        </div>-->
<#--                                                    </#if>-->
                                                </#if>
                                            </#list>
                                            </div>
                                            <div id="txt-show" class="table-responsive" style="display: none">
                                            <table class="table">
                                                <thead>
                                                    <tr class="border-bottom-danger">
                                                        <th>编号</th>
                                                        <th>名称</th>
                                                        <th>单位</th>
                                                        <th>价格</th>
                                                        <th>供应商</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <#if goodsInfoList??>
                                                        <#list goodsInfoList as myn>
                                                            <tr class="goods-ct"  id="${myn.id!}" unit-type="${myn.unitType!}" price="<#if myn.price??>${myn.price!}<#else >0</#if>" unit="${myn.unitPrName!}" price-se="<#if myn.priceSe??>${myn.priceSe!}<#else >0</#if>" unit-se="${myn.unitPeName!}" goid="${myn.goid!}" gtid="${myn.gtid!}" name="${myn.chName!}" en_name="${myn.enName!}" supplier_name="${myn.supplierName!}" supplier_address="${myn.supplierAddress!}" siid="${myn.siid}" class="hidden">
                                                                <td><span class="sel-number">0</span>${myn.code!}</td>
                                                                <td>${myn.chName!}<br/>(${myn.enName!})</td>
                                                                <td>
                                                                    <#if myn.unitType==1>
                                                                        ${myn.unitPrName!}(主)
                                                                    <#else >
                                                                        ${myn.unitPeName!}(辅)
                                                                    </#if>
                                                                </td>
                                                                <td>
                                                                    <#if myn.unitType==1>
                                                                        <#if myn.price??>${myn.price!}<#else >0</#if>
                                                                    <#else >
                                                                        <#if myn.priceSe??>${myn.priceSe!}<#else >0</#if>
                                                                    </#if>
                                                                </td>
                                                                <td>
                                                                    ${myn.supplierName!}
                                                                </td>
                                                            </tr>
                                                        </#list>
                                                    </#if>
                                                </tbody>
                                            </table>
                                            </div>
                                        </#if>
<#--                                        <ul class="goods-list clearfix">-->
<#--                                            <#if goodsInfoList??>-->
<#--                                                <#list goodsInfoList as myn>-->
<#--                                                    <#if myn.siid??>-->
<#--                                                    <li id="${myn.id!}" unit-type="${myn.unitType!}" price="${myn.price!}" unit="${myn.unitPrName!}" price-se="${myn.priceSe!}" unit-se="${myn.unitPeName!}" goid="${myn.goid!}" gtid="${myn.gtid!}" name="${myn.chName!}" en_name="${myn.enName!}" supplier_name="${myn.supplierName!}" supplier_address="${myn.supplierAddress!}" siid="${myn.siid}" class="hidden">-->
<#--                                                        <span class="sel-number">0</span>-->
<#--                                                        <div class="goods-img">-->
<#--                                                            <#if myn.imgUrl??>-->
<#--                                                                <img src="${aliyunOos!}${myn.imgUrl!}" οnerrοr="this.οnerrοr='';src='/statics/admin/assets/images/default_goods.jpg'"/>-->
<#--                                                            <#else>-->
<#--                                                                <img src="/statics/admin/assets/images/default_goods.jpg"/>-->
<#--                                                            </#if>-->
<#--                                                        </div>-->
<#--                                                        <p class="goods-info">-->
<#--                                                            <span class="name">${myn.chName!}<br/>${myn.enName!}</span>-->
<#--                                                            <br/>-->
<#--                                                            <span class="price-unit red">-->
<#--                                                                <#if myn.unitType==1>-->
<#--                                                                        ${myn.price!}P/-->
<#--                                                                        ${myn.unitPrName!}(主)-->
<#--                                                                    <#else >-->
<#--                                                                        ${myn.priceSe!}P/-->
<#--                                                                        ${myn.unitPeName!}(辅)-->
<#--                                                                </#if>-->
<#--                                                            </span>-->
<#--                                                            <br/>-->
<#--                                                            <span class="supplier_name">供应商：${myn.supplierName!}</span>-->
<#--                                                        </p>-->
<#--                                                    </li>-->
<#--                                                    </#if>-->
<#--                                                </#list>-->
<#--                                            </#if>-->
<#--                                        </ul>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="panel panel-flat">
                            <div class="panel-heading">
                                <h6 class="panel-title">
                                    下单列表
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
<#--                                        <label class="col-xs-4">-->
<#--                                            <select id="sel-supplier" class="form-control">-->
<#--                                                <option value="">供应商</option>-->
<#--                                                <#if supplierInfoList??>-->
<#--                                                    <#list supplierInfoList as myn>-->
<#--                                                        <option value="${myn.id}">${myn.name}</option>-->
<#--                                                    </#list>-->
<#--                                                </#if>-->
<#--                                            </select>-->
<#--                                        </label>-->
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
<script>
    eventInit();

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
                area:  ['500px']   // 长，宽
            });
            $("#order-list-history tbody>tr").unbind("click").click(function(){
                var oiid = $(this).attr("id");
                $.ajax({
                    url:"/admin/orderInfo/orderInfoDetailByOiid?oiid="+oiid,
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
                                    $(".goods-list #"+giid).click();
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

    $("#clear-order").click(function(){
        $(".order-list").html("");
        $(".sel-number").text(0);
        sumGoodsTotalNumberAndPrice();
        localStorage.setItem("cacheOrder",JSON.stringify({}));
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
            priceSe = parseFloat(priceSe);
            var unit = orderDom.attr("unit");
            var unitSe = orderDom.attr("unit-se");

            var number = orderDom.find(".goods-number").val();
            number = parseInt(number);
            var totalPrice = (price*number);
            if(unitType==2){
                totalPrice = (priceSe*number);
            }

            var orderInfo = {};
            orderInfo["oiid"] = oiid;
            orderInfo["giid"] = goodsId;
            orderInfo["siid"] = siid;
            orderInfo["price"] = price;
            orderInfo["priceSe"] = priceSe;
            orderInfo["unit"] = unit;
            orderInfo["unitSe"] = unitSe;
            orderInfo["unitType"] = unitType;
            orderInfo["number"] = number;
            orderInfo["totalPrice"] = totalPrice;

            order_list_detail.push(orderInfo);
        }
        return order_list_detail;
    }


    /**
     * 事件初始化
     */
    function eventInit(){
        $("#sel-show-mode").change(function(){
           var mode = $(this).val();
           $("#img-show,#txt-show").hide();
           $("#"+mode).show();
        });
        $("#sel-show-mode").change();
        $("#print-order").click(function(){
            jQuery('#printContent').print()
        });
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

                                    // layer.confirm('下单成功，是否立即打印？', {
                                    //     btn: ['是', '否']
                                    // }, function(index){
                                    //     printOrder(createTime,orderNumber,function(){
                                    //         $.ajax({
                                    //             url:"/admin/orderInfo/updateOrderInfo",
                                    //             data:{
                                    //                 id:orderId,
                                    //                 state:2
                                    //             },
                                    //             type:"post",
                                    //             async:false,
                                    //             success:function (data) {
                                    //                 if(data.statusCode==200){
                                    //                     layer.close(index)
                                    //                     layer.load(2);
                                    //                     window.location.href = "/admin/orderInfo/submitIndex";
                                    //                 }
                                    //             }
                                    //         })
                                    //     });
                                    // },function(index){
                                    //     layer.close(index)
                                    //     layer.load(2);
                                    //     window.location.href = "/admin/orderInfo/index";
                                    // });
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
            // if(value=="all"){
            //     $(".tab-goods-type li").removeClass("hidden").addClass("show");
            // }else{
                $(".tab-goods-type li[goid='all']").removeClass("hidden").show();
                $(".tab-goods-type li[goid='"+value+"']").removeClass("hidden").show();
            // }

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

            var showPrice = price;
            var showUnit = unit;
            if(unitType==2){
                showPrice = priceSe;
                showUnit = unitSe;
            }
            var selNumber = goodsDom.find(".sel-number").text();
            selNumber = parseInt(selNumber);
            selNumber++;
            $(".goods-ct[id='"+id+"']").find(".sel-number").text(selNumber);

            var readCacheOrder = JSON.parse(localStorage.getItem("cacheOrder"));
            readCacheOrder[id] = {"num":selNumber,"unitType":unitType};
            localStorage.setItem("cacheOrder",JSON.stringify(readCacheOrder));

            var goodsInfo = $(".order-list li[id='ol-"+id+"']");
            //如果订单列表已存在该商品则商品数量加1，否则订单列表加入此商品
            if(goodsInfo.length!=0){
                var goodsNumberDom = goodsInfo.find(".goods-number");
                goodsNumberDom.val(selNumber);
            }else{
                var olId = "ol-"+id;
                var goodsInfoHtml = `<li id="`+olId+`" gl-id="`+id+`" class="clearfix" price=`+price+` unit=`+unit+` price-se=`+priceSe+` unit-se=`+unitSe+` unit-type=`+unitType+` siid=`+siid+` supplier_name=`+supplier_name+` supplier_address=`+supplier_address+`>
                                            <ul>
                                                <li class="ol-left">
                                                    <span class="ch_name">`+name+`</span><br/>
                                                    <span class="en_name">`+en_name+`</span><br/>
                                                    <span class="red price_unit">
                                                        <span class="show-price">`+showPrice+`</span>P/<span class="show-unit">`+showUnit+`</span>
                                                         <select class="unit-sel">`;
                if(unitType==1){
                    goodsInfoHtml += `<option value="1" selected=selected>主</option>
                                                                                  <option value="2">辅</option>`;
                }else{
                    goodsInfoHtml += `<option value="1">主</option>
                                                                                  <option value="2" selected=selected>辅</option>`;
                }

                goodsInfoHtml+=`</select>
                                                    </span><br/>
                                                    <span class="supplier_name">供应商：`+supplier_name+`</span>
                                                </li>
                                                <li class="ol-right">
                                                    <a href="javascript:void(0)" class="sub">-</a>
                                                    <input type="number" class="goods-number" value="1"/>
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
                    var glId = parentDom.attr("gl-id");
                    var poId = glId.replace("ol-","");
                    $(".goods-ct[id='"+glId+"']").find(".sel-number").text(goodsNumber);
                    goodsNumberDom.val(goodsNumber);
                    var newCacheOrder = JSON.parse(localStorage.getItem("cacheOrder"));
                    newCacheOrder[poId]["num"] = goodsNumber;
                    localStorage.setItem("cacheOrder",JSON.stringify(newCacheOrder));
                    // console.log(JSON.parse(localStorage.getItem("cacheOrder")));
                    if(type=="del"||goodsNumber==0){
                        parentDom.remove();
                        delete newCacheOrder[poId];
                        localStorage.setItem("cacheOrder",JSON.stringify(newCacheOrder));
                    }
                    //重新计算选择商品的总价格、总数量
                    sumGoodsTotalNumberAndPrice();
                });
                $("#"+olId+" .goods-number").unbind("input propertychange").bind('input propertychange',function () {
                    var sadDom = $(this);
                    var parentDom = $(this).parents("li[gl-id]");
                    var goodsNumber = parseInt(sadDom.val());
                    if(isNaN(NaN)){
                        goodsNumber = 0;
                        sadDom.val(0);
                    }
                    var glId = parentDom.attr("gl-id");
                    var poId = glId.replace("ol-","");
                    $(".goods-ct[id='"+glId+"']").find(".sel-number").text(goodsNumber);
                    var newCacheOrder = JSON.parse(localStorage.getItem("cacheOrder"));
                    newCacheOrder[poId]["num"] = goodsNumber;
                    localStorage.setItem("cacheOrder",JSON.stringify(newCacheOrder));

                    //重新计算选择商品的总价格、总数量
                    sumGoodsTotalNumberAndPrice();
                });
                $("#"+olId+" .unit-sel").unbind("change").change(function(){
                    var sadDom = $(this);
                    var parentDom = $(this).parents("li[gl-id]");
                    var unitType = sadDom.val();
                    if(unitType==1){
                        parentDom.find(".show-price").text(parentDom.attr("price"));
                        parentDom.find(".show-unit").text(parentDom.attr("unit"));
                    }else{
                        parentDom.find(".show-price").text(parentDom.attr("price-se"));
                        parentDom.find(".show-unit").text(parentDom.attr("unit-se"));
                    }
                    parentDom.attr("unit-type",unitType);
                    sumGoodsTotalNumberAndPrice();
                });
            }
            //重新计算选择商品的总价格、总数量
            sumGoodsTotalNumberAndPrice();
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
            if(unitType==2){
                price = orderDom.attr("price-se");
            }
            price = parseFloat(price);
            totalPrice+=(price*number);
        }
        $("#total-goods").text(totalNumber);
        $("#total-price").text(totalPrice);
    }
</script>
<script language="javascript" type="text/javascript">
    //---------------------------lodop小票打印 start-------------------------------
    // var LODOP; //声明为全局变量
    // function printPreview(){
    //     //创建小票打印页
    //     CreatePrintPage();
    //     //打印预览
    //     LODOP.PREVIEW();
    // }
    // /**
    //  * 样例函数,服务器确认订单后执行
    //  */
    // function printOrder(orderTime,orderNo) {
    //     //创建小票打印页
    //     CreatePrintPage(orderTime,orderNo);
    //     //开始打印
    //     LODOP.PRINT();
    // };
    // function CreatePrintPage(orderTime,orderNo) {
    //     //json 创建模拟服务器响应的订单信息对象
    //     var title = "采购订单信息"
    //     var adminName = $("#sel-admin option:selected").text();
    //     var carName = $("#sel-car option:selected").text();
    //     var totalGoods = $("#total-goods").text();
    //     var totalPrice = $("#total-price").text();
    //     if(!orderTime){
    //         orderTime = new Date().toLocaleString();
    //         orderTime = orderTime.substring(0,10);
    //     }
    //     if(!orderNo){
    //         orderNo = new Date().getTime();
    //     }
    //     var orderListDom = $(".order-list>li");
    //     var hPos=10,//小票上边距
    //     pageWidth=580,//小票宽度
    //     rowHeight=15,//小票行距
    //     //获取控件对象
    //     LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
    //     //初始化
    //     LODOP.PRINT_INIT("打印采购订单");
    //     //添加小票标题文本
    //     LODOP.ADD_PRINT_TEXT(hPos,50,pageWidth,rowHeight,title);
    //     //上边距往下移
    //     hPos+=rowHeight;
    //
    //     LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"下单人:");
    //     LODOP.ADD_PRINT_TEXT(hPos,50,pageWidth,rowHeight,adminName);
    //     // hPos+=rowHeight;
    //     LODOP.ADD_PRINT_TEXT(hPos,110,pageWidth,rowHeight,"车辆:");
    //     LODOP.ADD_PRINT_TEXT(hPos,140,pageWidth,rowHeight,carName);
    //     hPos+=rowHeight;
    //     LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"下单时间:");
    //     LODOP.ADD_PRINT_TEXT(hPos,60,pageWidth,rowHeight,orderTime);
    //     hPos+=rowHeight;
    //     LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"订单编号:");
    //     LODOP.ADD_PRINT_TEXT(hPos,60,pageWidth,rowHeight,orderNo);
    //     hPos+=rowHeight;
    //     LODOP.ADD_PRINT_LINE(hPos,2, hPos, pageWidth,2, 1);
    //     hPos+=5;
    //     LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,"品名");
    //     LODOP.ADD_PRINT_TEXT(hPos,70,pageWidth,rowHeight,"单价");
    //     LODOP.ADD_PRINT_TEXT(hPos,110,pageWidth,rowHeight,"数量");
    //     LODOP.ADD_PRINT_TEXT(hPos,140,pageWidth,rowHeight,"小计");
    //     hPos+=rowHeight;
    //     //遍历商品数组
    //     for(var i = 0;i<orderListDom.length;i++){
    //         var orderDom = orderListDom.eq(i);
    //         var price = orderDom.attr("price");
    //         price = parseFloat(price);
    //         var unit = orderDom.attr("unit");
    //         var number = orderDom.find(".goods-number").val();
    //         number = parseInt(number);
    //
    //         var ch_name = orderDom.find(".ch_name").text();
    //         var en_name = orderDom.find(".en_name").text();
    //         // var price_unit = orderDom.find(".price_unit").text();
    //         var supplier_name = orderDom.find(".supplier_name").text();
    //         var goodsFullName = ch_name+"("+supplier_name.replace("供应商：","")+")";
    //         var price_unit = price+unit;
    //         if(goodsFullName.length<4){
    //             LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,goodsFullName);
    //         }else {
    //             //商品名字过长,其他字段需要换行
    //             LODOP.ADD_PRINT_TEXT(hPos,1,pageWidth,rowHeight,goodsFullName);
    //             hPos+=rowHeight;
    //         }
    //         LODOP.ADD_PRINT_TEXT(hPos,70,pageWidth,rowHeight,price);
    //         LODOP.ADD_PRINT_TEXT(hPos,115,pageWidth,rowHeight,number);
    //         LODOP.ADD_PRINT_TEXT(hPos,140,pageWidth,rowHeight,price*number);
    //         hPos+=rowHeight;
    //     }
    //     //商品遍历打印完毕,空一行
    //     hPos+=rowHeight;
    //     //合计
    //     LODOP.ADD_PRINT_TEXT(hPos,80,pageWidth,rowHeight,"合计:"+totalGoods);
    //     LODOP.ADD_PRINT_TEXT(hPos,130,pageWidth,rowHeight,"P"+totalPrice);
    //
    //     hPos+=rowHeight;
    //     LODOP.ADD_PRINT_TEXT(hPos,2,pageWidth,rowHeight,(new Date()).toLocaleDateString()+" "+(new Date()).toLocaleTimeString())
    //     //初始化打印页的规格
    //     LODOP.SET_PRINT_PAGESIZE(3,pageWidth,45,title);
    //
    // };
    //---------------------------lodop小票打印 end---------------------------------

    //---------------------------jQuery A4打印 start-------------------------------
    function printOrder(orderTime,orderNo,callBack) {
        var orderList = $(".order-list>li");
        var orderTbodyTr = "";
        var orderTfoot = "";
        var orderPrintformatArr = [];
        var selAdmin = $("#sel-admin option:selected").text();
        for (var i = 0;i<orderList.length;i++){
            var orderDom = orderList.eq(i);
            var ch_name = orderDom.find(".ch_name").text();
            var en_name = orderDom.find(".en_name").text();
            var siid = orderDom.attr("siid");
            var supplier_name = orderDom.attr("supplier_name");
            var supplier_address = orderDom.attr("supplier_address");
            var unitType = orderDom.attr("unit-type");
            var unit = orderDom.attr("unit");
            var price = orderDom.attr("price");
            if(unitType==2){
                price = orderDom.attr("price-se");
                unit = orderDom.attr("unit-se");
            }
            price = parseFloat(price);
            var goods_number = orderDom.find(".goods-number").val();
            goods_number = parseInt(goods_number);

            var total_price = (price*goods_number);
            var sol = {};
            sol["name"] = ch_name+"("+en_name+")";
            sol["price"] = price;
            sol["unit"] = unit;
            sol["goods_number"] = goods_number;
            var isAdd = true;
            for (var j = 0;j<orderPrintformatArr.length;j++){
                var opfObj = orderPrintformatArr[j];
                if(opfObj["siid"]==siid){
                    opfObj["sOrderList"].push(sol);
                    isAdd = false;
                    break;
                }
            }
            if(isAdd){
                var orderObj = {};
                orderObj["siid"] = siid;
                orderObj["supplier_name"] = supplier_name;
                orderObj["supplier_address"] = supplier_address;
                var sOrderList = [];
                sOrderList.push(sol);
                orderObj["sOrderList"] = sOrderList;
                orderPrintformatArr.push(orderObj);
            }
        }
        console.log(orderPrintformatArr);
        for (var i = 0;i<orderPrintformatArr.length;i++){
            var opfObj = orderPrintformatArr[i];
            var supplier_name = opfObj["supplier_name"];
            var supplier_address = opfObj["supplier_address"];
            var sOrderList = opfObj["sOrderList"];
            orderTbodyTr += `<tr><td colspan="5" style="padding: 10px;font-weight: 600;text-align: left;">供应商名称：`+supplier_name+`<br/>地址：`+supplier_address+`</td></tr>`;
            orderTbodyTr += `<tr style="border-bottom: 1px solid #cbcbcb;">
                                    <td style="padding: 10px;font-weight: 600;">商品名称</td>
                                    <td style="padding: 10px;font-weight: 600;">参考价格</td>
                                    <td style="padding: 10px;font-weight: 600;">单位</td>
                                    <td style="padding: 10px;font-weight: 600;">数量</td>
                                    <td style="padding: 10px;font-weight: 600;">采购情况</td>
                                 </tr>`;
            for (var j = 0;j<sOrderList.length;j++){
                var sOrder = sOrderList[j];
                var name = sOrder["name"];
                var price = sOrder["price"];
                var unit = sOrder["unit"];
                var goods_number = sOrder["goods_number"];
                orderTbodyTr += `<tr style="border-bottom: 1px solid #cbcbcb;">
                                    <td style="padding: 10px;">`+name+`</td>
                                    <td style="padding: 10px;">`+price+`</td>
                                    <td style="padding: 10px;">`+unit+`</td>
                                    <td style="padding: 10px;">`+goods_number+`</td>
                                    <td style="padding: 10px;"><input type="checkbox"/></td>
                                 </tr>`;
            }
        }
        orderTfoot = `<tr>
                            <td style="padding: 10px;font-weight: 600;">编号</td>
                            <td style="padding: 10px;font-weight: 600;">时间</td>
                            <td style="padding: 10px;font-weight: 600;">下单员</td>
                            <td style="padding: 10px;font-weight: 600;">采购员</td>
                            <td style="padding: 10px;font-weight: 600;">总价格</td>
                      </tr>`;
        orderTfoot += `<tr>
                            <td style="padding: 10px;">`+orderNo+`</td>
                            <td style="padding: 10px;">`+orderTime+`</td>
                            <td style="padding: 10px;">`+selAdmin+`</td>
                            <td style="padding: 10px;">`+selAdmin+`</td>
                            <td style="padding: 10px;">`+$("#total-price").text()+`</td>
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

    var cacheOrder = localStorage.getItem("cacheOrder");
    var orderList = {};
    if(cacheOrder){
        try{
            var readCacheOrder = JSON.parse(cacheOrder);
            for(var o in readCacheOrder){
                var goodsNum = readCacheOrder[o]["num"];
                // console.log(goodsNum);
                if(goodsNum>0){
                    for(var r = 0;r<goodsNum;r++){
                        $(".goods-list #"+o).click();
                    }
                }
            }
        }catch (e){
            console.log(e);
        }
    }else{
        localStorage.setItem("cacheOrder",JSON.stringify(orderList));
    }
    //---------------------------jQuery A4打印 end---------------------------------
</script>
</body>
</html>



