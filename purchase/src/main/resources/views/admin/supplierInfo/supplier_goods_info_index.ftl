<style>
    #updateInfo{
        border: 1px solid;
        border-radius: 5px;
        padding: 2px 8px;
    }
    #currencyType,#exchangeRate{
        display: inline-block;
        width: 80px;
        padding: 0px;
        height: 23px;
    }
</style>
<div class="content">
    <div class="panel-heading bg-primary">
        <h6 class="panel-title">
            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">${supplierInfo.name}：商品价格比对</a>
            <a class="collapsed"  onclick="insertGoodsInfoAjax()" style="float: right;">
                <i class="icon-plus2"></i>
                新增商品
            </a>
            <div class="collapsed"  style="float: right;margin-right: 12%">
                选择币种：
                <select id="currencyType" class="form-control">
                    <option value="CNY" <#if supplierInfo.currencyType??&&supplierInfo.currencyType=="CNY">selected</#if>>CNY</option>
                    <option value="USD" <#if supplierInfo.currencyType??&&supplierInfo.currencyType=="USD">selected</#if>>USD</option>
                    <option value="PHP" <#if supplierInfo.currencyType??&&supplierInfo.currencyType=="PHP">selected</#if>>PHP</option>
                </select>
                今日汇率：
                <input type="text" id="exchangeRate" value="${supplierInfo.exchangeRate!}"  class="form-control">
                <a class="btn btn-primary" id="updateInfo"><i class="icon-loop3" style="font-size: 12px;margin-right: 5px;"></i>更新</a>
            </div>
        </h6>
    </div>
    <div class="panel panel-flat">
        <div class="table-responsive">
            <table class="table fixed-table">
                <thead>
                    <tr class="border-bottom-danger">
                        <th>商品名称</th>
                        <th>单位</th>
                        <th>币种</th>
                        <th>
                            供应价格(${supplierInfo.currencyType!})
                        </th>
                        <th>
                            产地物流(${supplierInfo.currencyType!})
                        </th>
                        <th>
                            国际物流(${supplierInfo.currencyType!})
                        </th>
                        <th>通关费用(PHP)</th>
                        <th>本地物流(PHP)</th>
                        <th>供应价格(PHP)</th>
                        <th>采购价格(PHP)</th>
                        <th>差额</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                <col style="width: 115px" />
                <col style="width: 20%" />
                <col style="width: 20%" />
                <col style="width: 20%" />
                <col style="width: 20%" />
                <col style="width: 20%" />
                <col style="width: 20%" />
                <col style="width: 20%" />
                <col style="width: 20%" />
                <col style="width: 20%" />
                <col style="width: 20%" />
                <col style="width: 20%" />
                <#if goodsToSupplierList??>
                    <#list goodsToSupplierList as myn>
                        <tr>
                            <td>${myn.goodsName!}</td>
                            <td>${myn.unit!}</td>
                            <td>${myn.currencyType!}</td>
                            <td>${myn.originPrice!}</td>
                            <td>${myn.originLogFee!}</td>
                            <td>${myn.intLogFee!}</td>
                            <td>${myn.awbFee!}</td>
                            <td>${myn.localLogFee!}</td>
                            <td>${myn.supplierPrice!}</td>
                            <td>
                                <#if myn.unitType==1>
                                    ${myn.goodsInfo.price!}
                                </#if>
                                <#if myn.unitType==2>
                                    ${myn.goodsInfo.priceSe!}
                                </#if>
                            </td>
                            <td>

                                <#if myn.diifPrice gt 0>
                                        <span style="color:red;">${myn.diifPrice!}</span>
                                    <#else >
                                        <span style="color:green;">${myn.diifPrice!}</span>
                                </#if>
                            </td>
                            <td>
                                <div onclick="selGoodsSupplierAjax('${myn.id!}')" style="color:#2196f3;float: left;">
                                    <i class="icon-plus2" id="saveMenu${myn.id!}" onmousemove="layerTips('选择','saveMenu${myn.id!}')"></i>
                                </div>
                                <div onclick="deleteGoodsAjax('${myn.id!}')" style="color:red;float: left;">
                                    <i class="icon-cross2" id="del${myn.id!}" onmousemove="layerTips('删除','del${myn.id!}')"></i>
                                </div>
                            </td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    function insertGoodsInfoAjax(){
        $.ajax({
            url:"/admin/supplierInfo/insertGoodsInfoAjax",
            data:{
                id:${supplierInfo.id}
            },
            headers: {
                'httpType': "HTML",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    anim: 1,
                    shadeClose: true,
                    content: data,
                    area:["1100px","650px"]
                });
            }
        })
    }

    $("#updateInfo").click(function(){
        var currencyType = $("#currencyType").val();
        var exchangeRate = $("#exchangeRate").val();
        layer.load(1);
        $.ajax({
            url:"/admin/supplierInfo/updateIngAjax",
            data:{
                id:${supplierInfo.id},
                currencyType:currencyType,
                exchangeRate:exchangeRate
            },
            headers: {
                'httpType': "JSON",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.closeAll('loading');
                if(data.statusCode==200){
                    layer.msg('更新成功');
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }else{
                    layer.msg('更新失败');
                }
            }
        })
    });

    function selGoodsSupplierAjax(id) {
        layer.load(1);
        $.ajax({
            url:"/admin/supplierInfo/selGoodsSupplierAjax",
            data:{
                id:id,
            },
            headers: {
                'httpType': "JSON",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.closeAll('loading');
                if(data.statusCode==200){
                    layer.msg('操作成功');
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }else{
                    layer.msg('操作失败');
                }
            }
        })
    }

    function deleteGoodsAjax(id) {
        $.ajax({
            url:"/admin/supplierInfo/deleteGoodsAjax",
            data:{
                id:id,
            },
            headers: {
                'httpType': "JSON",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.closeAll('loading');
                if(data.statusCode==200){
                    $("#del"+id).parents("tr").remove();
                    layer.msg('删除成功');
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }else{
                    layer.msg('删除失败');
                }
            }
        })
    }
</script>



