<div class="content">
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">添加${sqlSupplierInfo.name}供应商品信息</a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr class="border-bottom-danger">
                                    <th>一级分类名称</th>
                                    <th>二级分类名称</th>
                                    <th>PISE价格</th>
                                    <th>RMB价格</th>
                                    <th>单位</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#if goodsOneTypeList??>
                                <#list goodsOneTypeList as myn>
                                    <tr class="border-top-primary">
                                        <td><span class="text-primary">${myn.name!}（${myn.ename!}）</span></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>
                                            <div onclick="deleteGoodsOneTypeAjax('${myn.id!}','${sqlSupplierInfo.id!}')" style="color:red;float: left;margin-left: 10px;">
                                                <i class="icon-cross2" id="delete${myn.id!}" onmousemove="layerTips('删除分类所有商品价格','delete${myn.id!}')"></i>
                                            </div>
                                        </td>
                                    </tr>
                                    <#if goodsTowTypeList??>
                                        <#list goodsTowTypeList as myt>
                                            <#if myn.id==myt.goid>
                                                <tr class="border-top-primary">
                                                    <td></td>
                                                    <td><span class="text-success">${myt.name!}（${myt.ename!}）</span></td>
                                                    <#assign flag=0>
                                                    <#if goodsToSupplierList??>
                                                        <#list goodsToSupplierList as mys>
                                                            <#if myt.id==mys.gttid>
                                                                <td>${mys.price!}PISE</td>
                                                                <td>${mys.price!/exchangeRate!}RMB</td>
                                                                <td>${mys.unit!}</td>
                                                                <#assign flag=1>
                                                            </#if>
                                                        </#list>
                                                    </#if>
                                                    <#if flag==0>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                    </#if>
                                                    <td>
                                                        <div onclick="deleteGoodsTypeAjax('${myn.id!}','${myt.id!}','${sqlSupplierInfo.id!}')" style="color:red;float: left;margin-left: 10px;">
                                                            <i class="icon-cross2" id="deleteTow${myt.id!}" onmousemove="layerTips('删除价格','deleteTow${myt.id!}')"></i>
                                                        </div>
                                                        <div onclick="addGoodsTypeAjax('${myn.id!}','${myt.id!}','${sqlSupplierInfo.id!}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                            <i class="icon-plus2" id="add${myn.id!}" onmousemove="layerTips('新增价格','add${myn.id!}')"></i>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </#if>
                                        </#list>
                                    </#if>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
<script>
    function addGoodsTypeAjax(gotid,gttid,siid) {
        $.ajax({
            url:"/admin/supplierInfo/addGoodsTypeAjax",
            data:{
                gotid:gotid,
                gttid:gttid,
                siid:siid
            },
            headers: {
                'httpType': "HTML",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    anim: 1,
                    shadeClose: true,
                    content: data
                });
            }
        })
    }
    function deleteGoodsTypeAjax(gotid,gttid,siid) {
        $.ajax({
            url:"/admin/supplierInfo/deleteGoodsTypeAjax",
            data:{
                gotid:gotid,
                gttid:gttid,
                siid:siid
            },
            headers: {
                'httpType': "JSON",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.closeAll();
                if(data.statusCode==200){
                    getGoodsTypeAjax(siid);
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }else{
                    layer.msg("删除成功")
                }
            }
        })
    }

    function deleteGoodsOneTypeAjax(gotid,siid) {
        $.ajax({
            url:"/admin/supplierInfo/deleteGoodsOneTypeAjax",
            data:{
                gotid:gotid,
                siid:siid
            },
            headers: {
                'httpType': "JSON",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.closeAll();
                if(data.statusCode==200){
                    getGoodsTypeAjax(siid);
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }else{
                    layer.msg("删除成功")
                }
            }
        })
    }
</script>
</html>



