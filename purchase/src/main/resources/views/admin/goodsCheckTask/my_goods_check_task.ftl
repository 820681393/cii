
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="库存盘点">
    <#include "../common/head.ftl"/>
    <style>
        .number,.numberSe{
            display: inline-block;
            margin-bottom: 3px;
            max-width: 80px;
            margin-right: 3px;
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
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <#if goodsCheckTaskList??>
                            <#list goodsCheckTaskList as myn>
                                <table id="${myn.id}" class="table fixed-table" style="font-size: 12px">
                                    <thead>
                                        <tr class="border-bottom-danger">
                                            <th colspan="2">盘点单号：${myn.orderNumber!}</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <col style="width: 50px;" />
                                    <col style="width: 20%" />
                                        <#list myn.goodsCheckTaskDetailList as gtl>
                                            <tr id="${gtl.id}" class="goods">
                                                <td>
                                                    ${gtl.goodsName!}
                                                </td>
                                                <td>
                                                    <input type="number" class="number form-control"  placeholder="${gtl.unit!}">${gtl.unit!}
                                                    <br/>
                                                    <input type="number" class="numberSe form-control" placeholder="${gtl.unitSe!}">${gtl.unitSe!}
                                                </td>
                                            </tr>
                                        </#list>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td colspan="2">
                                                发起时间：${(myn.createTime!?string("yyyy-MM-dd HH:mm:ss"))}
                                                <a class="btn btn-primary confirm-check" to="${myn.id}">盘点完成</a>
                                            </td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </#list>
                        </#if>
                    </div>
                </div>
                <#include "../common/foot.ftl"/>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(".confirm-check").click(function(){
        var to = $(this).attr("to");
        layer.confirm('确定提交盘点数吗？', {
            btn: ['确定','取消']
        }, function(index){
            var goodsList = $("#"+to).find(".goods");
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
            console.log(goodsCheckTaskDetailList);
            $.ajax({
                // headers必须添加，否则会报415错误
                headers: {
                    'Accept': 'application/json',
                    'httpType': "JSON",
                    'Content-Type': 'application/json'
                },
                type: 'POST',
                async:false,
                data: JSON.stringify(goodsCheckTaskDetailList),
                url: '/admin/goodsCheckTask/submitMyCheckTask?id='+to,
                success: function(data){
                    if(data.statusCode==200){
                        window.location.href = "/admin/goodsCheckTask/myCheckTask";
                    }else{
                        layer.alert(data.message);
                    }
                }
            });
        });
    });
</script>
</html>



