<div class="content">
    <div class="panel-heading bg-primary">
        <h6 class="panel-title">
            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">${sqlAdminInfo.nikeName!} 商户下单员</a>
            <a class="collapsed"  onclick="insertMerchantToAdminAjax('${miid!}')" style="float: right">
                <i class="icon-plus2"></i>
                新增商户管理员
            </a>
            <a class="collapsed"  onclick="insertMerchantToAdminAjax('${miid!}')" style="float: right">
                <i class="icon-plus2"></i>
                新增商户下单员
            </a>
        </h6>
    </div>
    <div class="panel panel-flat">
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr class="border-bottom-danger">
                    <th>名称</th>
                    <th>联系电话</th>
                    <th>微信号码</th>
                    <th>飞机号码</th>
                    <th>类型</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#if merchantUserInfoList??>
                    <#list merchantUserInfoList as myn>
                        <tr class="border-top-primary">
                            <td>${myn.name!}</td>
                            <td>${myn.tel!}</td>
                            <td>${myn.wxNumber!}</td>
                            <td>${myn.tgNumber!}</td>
                            <td>
                                <#if myn.type==1>
                                    下单人
                                 <#elseif myn.type==2>
                                    配送人
                                </#if>
                            </td>
                            <td>
                                <div  onclick="deleteMerchantToAdminAjax('${myn.id!}')" style="color:red;float: left;margin-left: 10px;">
                                    <i class="icon-cross2" id="deleteMerchantToAdminAjax${myn.id!}" onmousemove="layerTips('删除商户管理员','deleteMerchantToAdminAjax${myn.id!}')"></i>
                                </div>
                                <div onclick="updateMerchantToAdminAjax('${myn.id!}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                    <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改','update${myn.id!}')"></i>
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
<div style="margin-top: 30px" id="menuAjax"></div>
<script>
    function deleteMerchantToAdminAjax(id) {
        layer.load(1);
        $.ajax({
            url:"/admin/merchantToAdmin/deleteMerchantToAdminAjax",
            data:{
                id:id
            },
            headers: {
                'httpType': "JSON",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.closeAll();
                if(data.statusCode==200){
                    merchantToAdminAjax('${miid!}');
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }
            }
        })
    }
    function insertMerchantToAdminAjax(id) {
        $.ajax({
            url:"/admin/merchantToAdmin/insertMerchantToAdminAjax",
            data:{
                miid:id
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
                    area: ['500px', 'auto']
                });
            }
        })
    }

    function updateMerchantToAdminAjax(id) {
        $.ajax({
            url:"/admin/merchantToAdmin/updateMerchantToAdminAjax",
            data:{
                id:id
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
                    area: ['500px', 'auto']
                });
            }
        })
    }

</script>