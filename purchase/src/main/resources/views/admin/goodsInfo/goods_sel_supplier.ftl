<style>
    #supplierTable tbody tr:hover{
        background-color: #ccc;
        cursor:pointer;
    }
</style>
<div class="content-wrapper" style="padding: 0px;">
    <div class="content" style="padding: 0px;">
        <div class="col-md-12" style="padding: 0px;">
            <div class="panel" style="margin: 0px;width: 800px">
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">供应商列表</a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <table class="table" id="supplierTable">
                            <thead>
                            <tr class="border-bottom-danger">
                                <th>名称</th>
                                <th>电话</th>
                                <th>地址</th>
                                <th>联系人名称</th>
                                <th>微信号</th>
                                <th>供应状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if supplierInfoList??>
                                <#list supplierInfoList as myn>
                                    <tr id="${myn.id}" class="border-top-primary" <#if supplierId?? && supplierId==myn.id>style="background-color: #ffc8c8"</#if>>
                                        <td>${myn.name!}</td>
                                        <td>${myn.tel!}</td>
                                        <td>${myn.address!}</td>
                                        <td>${myn.linkUser!}</td>
                                        <td>${myn.wxNumber!}</td>
                                        <td>
                                            <#if myn.state==1>
                                                营业
                                            <#elseif myn.state==2>
                                                停业
                                            </#if>
                                        </td>
                                    </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#supplierTable tbody tr").unbind("click").click(function(){
        var siid = $(this).attr("id");
        $.ajax({
            url:"/admin/goodsInfo/updateGoodsInfo",
            data:{
                id:${goodsId},
                siid:siid
            },
            headers: {
                'httpType': "JSON",
            },
            type:"post",
            async:false,
            success:function (data) {
                if(data.statusCode==200){
                    window.location.href = "/admin/goodsInfo/index";
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }
            }
        })
    });
</script>