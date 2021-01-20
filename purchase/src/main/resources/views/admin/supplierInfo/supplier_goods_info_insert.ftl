<link href="/statics/admin/assets/css/order.css" rel="stylesheet" type="text/css">
<style>
    .goods-list .goods-img{
        height: 68px;
    }
    .price-unit{
        position: absolute;
        bottom: 0px;
    }
    .goods-list .goods-ct{
        position: relative;
        height: 190px;
        padding: 10px 7px;
        border: 1px solid #ececec;
        font-size: 12px;
    }
    .add-goods{
        color: #03a9f4;
    }
    .sel-order-ct .order-list input{
        width: 58px;
        border: 1px solid #b5b3b5;
        border-radius: 3px;
        height: 20px;
        text-align: center;
        margin-bottom: 5px;
        margin-right: 5px;
    }
</style>
<div class="page-container">

    <div class="page-content">
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
                                </div>
                                <div class="form-group clearfix">
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
                                                    <div  class="col-lg-2 goods-ct"  id="${myn.id!}" unit-type="${myn.unitType!}" price="<#if myn.price??>${myn.price!}<#else >0</#if>" unit="${myn.unitPrName!}" price-se="<#if myn.priceSe??>${myn.priceSe!}<#else >0</#if>" unit-se="${myn.unitPeName!}" goid="${myn.goid!}" gtid="${myn.gtid!}" name="${myn.chName!}" en_name="${myn.enName!}" supplier_name="${myn.supplierName!}" supplier_address="${myn.supplierAddress!}" siid="${myn.siid}" stock="${myn.stock!}" stock-se="${myn.stockSe!}" class="hidden">
                                                        <div class="goods-img" style="background-image: url('${aliyunOos!}${myn.imgUrl!}')">
<#--                                                            <#if myn.imgUrl??>-->
<#--                                                                <img src="${aliyunOos!}${myn.imgUrl!}" οnerrοr="this.οnerrοr='';src='/statics/admin/assets/images/default_goods.jpg'"/>-->
<#--                                                            <#else>-->
<#--                                                                <img src="/statics/admin/assets/images/default_goods.jpg"/>-->
<#--                                                            </#if>-->
                                                        </div>
                                                        <p class="goods-info">
                                                            <span class="name">${myn.chName!}<br/>${myn.enName!}</span>
                                                            <br/>
                                                            <span class="price-unit red">
                                                                    <a class="collapsed add-goods" id="${myn.id!}" unitType="1" price="${myn.price!}" unit="${myn.unitPrName!}"  goid="${myn.goid!}" gtid="${myn.gtid!}" name="${myn.chName!}" en_name="${myn.enName!}" supplier_name="${myn.supplierName!}" supplier_address="${myn.supplierAddress!}" siid="${myn.siid}">
                                                                        <i class="icon-plus2"></i>${myn.unitPrName!}(主)
                                                                    </a>
                                                                    <br/>
                                                                    <a class="collapsed add-goods" id="${myn.id!}" unitType="2" price="${myn.priceSe!}" unit="${myn.unitPeName!}"  goid="${myn.goid!}" gtid="${myn.gtid!}" name="${myn.chName!}" en_name="${myn.enName!}" supplier_name="${myn.supplierName!}" supplier_address="${myn.supplierAddress!}" siid="${myn.siid}">
                                                                        <i class="icon-plus2"></i>${myn.unitPeName!}(辅)
                                                                    </a>
                                                            </span>
                                                        </p>
                                                    </div>
                                                </#if>
                                            </#list>
                                            </div>
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
                                    新增列表
                                    <a id="clear-order" style="float: right;font-size: 13px;">
                                        <i class="icon-bin"></i>
                                        全部清除
                                    </a>
                                </h6>
                                <hr/>
                                <div class="sel-order-ct" style="height: 468px;">
                                    <ul class="order-list">
                                    </ul>
                                </div>
                                <div class="submit-ct">
<#--                                    <p class="order-info">-->
<#--                                        商品总数：<span id="total-goods">0</span>&nbsp&nbsp账单金额：<span id="total-price">0</span>P-->
<#--                                    </p>-->
                                    <br/>
                                    <button id="submit-order" class="btn bg-blue btn-block">确定新增</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    eventInit();

    $(".add-goods").click(function(){
        var goodsDom = $(this);
        var id = goodsDom.attr("id");
        var price = goodsDom.attr("price");
        var unit = goodsDom.attr("unit");
        var name = goodsDom.attr("name");
        var en_name = goodsDom.attr("en_name");
        var unitType = goodsDom.attr("unitType");
        var goodsInfo = $(".order-list li[id='ol-"+id+"'][unitType='"+unitType+"']");
        if(goodsInfo.length!=0){
            return;
        }
        var olId = "ol-"+id;
        var goodsInfoHtml = `<li id="`+olId+`" gl-id="`+id+`" class="clearfix" price=`+price+` unit=`+unit+` unitType=`+unitType+`>
                                            <ul>
                                                <li class="ol-left" style="margin-bottom: 8px;">
                                                    <span class="ch_name">`+name+`</span><br/>
                                                    <span class="en_name">`+en_name+`</span><br/>
                                                    <span class="show-unit red">单位：`+unit+`</span>
                                                </li>
                                                <li class="ol-left">
                                                    供应价格：<input type="number" class="originPrice"/>
                                                    产地物流：<input type="number" class="originLogFee"/>
                                                    国际物流：<input type="number" class="intLogFee"/>
                                                    通关费用：<input type="number" class="awbFee"/>
                                                    本地物流：<input type="number" class="localLogFee"/>
                                                </li>
                                                <a href="javascript:void(0)" class="del" style="position: absolute;right: 30px;">×</a>
                                            </ul>
                                        </li>`;
        $(".order-list").append(goodsInfoHtml);
        $(".del").unbind("click").click(function(){
           $(this).parents("li").remove();
        });
    });



    $("#clear-order").click(function(){
        $(".order-list").html("");
    });
    /**
     * 获得选择商品列表JSONArray
     */
    function getGoodsArrList(){
        var orderList = $(".order-list>li");
        var order_list_detail = [];
        //遍历选好的商品信息转换成JSONArray
        for(var i = 0;i < orderList.length;i++){
            var orderDom = orderList.eq(i);
            var siid = ${supplierInfo.id};
            var unit = orderDom.attr("unit");
            var currencyType = "${supplierInfo.currencyType}";
            var originPrice = orderDom.find(".originPrice").val();
            var originLogFee = orderDom.find(".originLogFee").val();
            var intLogFee = orderDom.find(".intLogFee").val();
            var awbFee = orderDom.find(".awbFee").val();
            var localLogFee = orderDom.find(".localLogFee").val();
            var goodsName = orderDom.find(".ch_name").text()+"<br/>"+orderDom.find(".en_name").text();
            var giid = orderDom.attr("gl-id");
            var unitType = orderDom.attr("unitType");

            var orderInfo = {};
            orderInfo["siid"] = siid;
            orderInfo["unit"] = unit;
            orderInfo["currencyType"] = currencyType;
            orderInfo["originPrice"] = originPrice;
            orderInfo["originLogFee"] = originLogFee;
            orderInfo["intLogFee"] = intLogFee;
            orderInfo["awbFee"] = awbFee;
            orderInfo["localLogFee"] = localLogFee;
            orderInfo["goodsName"] = goodsName;
            orderInfo["giid"] = giid;
            orderInfo["unitType"] = unitType;

            order_list_detail.push(orderInfo);
        }
        return order_list_detail;
    }


    /**
     * 事件初始化
     */
    function eventInit(){

        $("#submit-order").click(function(){
            var orderList = $(".order-list>li");
            if(orderList.length==0){
                layer.alert('请选择商品');
                return;
            }
            layer.confirm('确定新增吗？', {
                btn: ['确定', '取消']
            }, function(index){
                layer.close(index);
                var goodsArrList = getGoodsArrList();
                console.log(goodsArrList);
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
                    url: '/admin/supplierInfo/insertGoodsInfoAjaxIng',
                    success: function(data){
                        if(data.statusCode==200){
                            window.location.href = "/admin/supplierInfo/index";
                        }else{
                            layer.alert(data.message);
                        }
                    }
                });
            });

        });

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


    // function sumGoodsTotalNumberAndPrice(){
    //     var totalNumber = 0;//选择商品的总数量
    //     var totalPrice = 0;//选择商品的总价格
    //     var orderList = $(".order-list li[price]");
    //     for(var i = 0;i<orderList.length;i++){
    //         var orderDom = orderList.eq(i);
    //         var unitType = orderDom.attr("unit-type");
    //         var number = orderDom.find(".goods-number").val();
    //         number = parseInt(number);
    //         totalNumber+=number;
    //
    //         var price = orderDom.attr("price");
    //         price = parseFloat(price);
    //         totalPrice+=(price*number);
    //     }
    //     $("#total-goods").text(totalNumber);
    //     $("#total-price").text(totalPrice);
    // }
</script>



