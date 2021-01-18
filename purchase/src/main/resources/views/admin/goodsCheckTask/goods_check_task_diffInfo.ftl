<style>
    .number,.numberSe{
        display: inline-block;
        margin-bottom: 3px;
        max-width: 80px;
        margin-right: 3px;
    }
</style>
<div class="content" style="padding: 0px;">
    <div class="col-md-12" style="padding: 0px;">
        <div class="panel" style="margin: 0px;">
            <div class="panel-heading bg-primary">
                <h6 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">差异表</a>
                </h6>
            </div>
            <div class="panel-collapse">
                <div class="panel-body">
                    <div class="col-md-12" style="padding: 0px;">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>商品名称</th>
                                <th>主单位</th>
                                <th>系统库存</th>
                                <th>盘点库存</th>
                                <th>辅单位</th>
                                <th>系统库存</th>
                                <th>盘点库存</th>
                                <th>货品差额</th>
                                <th>盘点员</th>
                                <th <#if goodsCheckTask.state!=2>style="display: none;"</#if>>修正</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if goodsCheckTaskDetailList??>
                                <#list goodsCheckTaskDetailList as myn>
                                    <tr id="${myn.id}" class="goods">
                                        <td>${myn.goodsName!}</td>
                                        <td>${myn.price!}/${myn.unit!}</td>
                                        <td>${myn.goodsStock!}</td>
                                        <td>
                                            <input type="number" class="number form-control" value="${myn.number!}" disabled>
                                        </td>
                                        <td>${myn.priceSe!}/${myn.unitSe!}</td>
                                        <td>${myn.goodsStockSe!}</td>
                                        <td>
                                            <input type="number" class="numberSe form-control" value="${myn.numberSe!}" disabled>
                                        </td>
                                        <td>
                                            <#if myn.diffAmount??>
                                                <#if myn.diffAmount !=0>
                                                    <span style="color: red">${myn.diffAmount!}</span>
                                                    <#else >
                                                        ${myn.diffAmount!}
                                                </#if>
                                            </#if>
                                        </td>
                                        <td>${goodsCheckTask.receiveAdminName!}</td>
                                        <td <#if goodsCheckTask.state!=2>style="display: none;"</#if>>
                                            <div onclick="ulockUpdate('${myn.id}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改','update${myn.id!}')"></i>
                                            </div>
                                        </td>
                                    </tr>
                                </#list>
                            </#if>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="3">系统库存货值：${goodsCheckTask.totalAmount!}</td>
                                    <td colspan="3">盘点存货值：${goodsCheckTask.realTotalAmount!}</td>
                                    <td colspan="4">差值：${goodsCheckTask.totalDiffAmount!}</td>
                                </tr>
                                <tr>
                                    <td colspan="3">负责人：${goodsCheckTask.adminName!}</td>
                                    <td colspan="3">盘点时间：
                                        <#if goodsCheckTask.checkTime??>
                                            ${(goodsCheckTask.checkTime!?string("yyyy-MM-dd HH:mm:ss"))}
                                        </#if>
                                    </td>
                                    <td colspan="4">完成时间：
                                        <#if goodsCheckTask.finishTime??>
                                            ${(goodsCheckTask.finishTime!?string("yyyy-MM-dd HH:mm:ss"))}
                                        </#if>
                                    </td>
                                </tr>
                                <#if goodsCheckTask.state==2>
                                    <tr>
                                        <td colspan="10">
                                            <input type="text" id="remark" class="form-control" placeholder="备注" value="${goodsCheckTask.remark!}" style="margin-bottom: 10px;">
                                            <button id="confirm" class="btn bg-blue btn-block">确认盘点结果</button>
                                        </td>
                                    </tr>
                                    <#else >
                                    <tr>
                                        <td colspan="10">
                                            备注：${goodsCheckTask.remark!}
                                        </td>
                                    </tr>
                                </#if>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function ulockUpdate(id){
        var adminId = ${userAdmin.id};
        if(adminId=="1"||adminId=="2"||adminId=="7"){
            $("#"+id).find(".number,.numberSe").removeAttr("disabled");
        }else{
            layer.msg("没有操作权限");
        }
    }

    $("#confirm").click(function(){
        var remark = $("#remark").val();
        layer.confirm('确认盘点结果', {
            btn: ['确定','取消']
        }, function(index){
            var goodsList = $(".goods");
            var goodsCheckTaskDetailList = [];
            for(var i = 0;i<goodsList.length;i++){
                var goods = goodsList.eq(i);
                var id = goods.attr("id");
                var number = goods.find(".number").val();
                var numberSe = goods.find(".numberSe").val();
                var goodsCheckTaskDetail = {};
                goodsCheckTaskDetail["id"] = id;
                goodsCheckTaskDetail["number"] = number;
                goodsCheckTaskDetail["numberSe"] = numberSe;
                goodsCheckTaskDetailList.push(goodsCheckTaskDetail);
            }
            var adminId = ${userAdmin.id};
            if(adminId=="1"||adminId=="2"||adminId=="7"){
                goodsCheckTaskDetailList = JSON.stringify(goodsCheckTaskDetailList);
            }else{
                goodsCheckTaskDetailList = JSON.stringify([]);
            }

            $.ajax({
                // headers必须添加，否则会报415错误
                headers: {
                    'Accept': 'application/json',
                    'httpType': "JSON",
                    'Content-Type': 'application/json'
                },
                type: 'POST',
                async:false,
                data: goodsCheckTaskDetailList,
                url: '/admin/goodsCheckTask/confirmGoodsCheckTask?id=${goodsCheckTask.id}&remark='+remark,
                success: function(data){
                    if(data.statusCode==200){
                        window.location.href = "/admin/goodsCheckTask/index";
                    }else{
                        layer.alert(data.message);
                    }
                }
            });
        });
    });
</script>