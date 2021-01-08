<div class="content">
    <div class="panel-heading bg-primary">
        <h6 class="panel-title">
            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">${sqlAdminInfo.nikeName!} 商户收货地址列表</a>
            <a class="collapsed"  onclick="insertMerchantAddressAjax('${sqlAdminInfo.id!}')" style="float: right">
                <i class="icon-plus2"></i>
                新增商户收货地址
            </a>
        </h6>
    </div>
    <div class="panel panel-flat">
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr class="border-bottom-danger">
                    <th>店级</th>
                    <th>商家收货人</th>
                    <th>商家收货地址</th>
                    <th>商家收货电话</th>
                    <th>商家门市图片</th>
                </tr>
                </thead>
                <tbody>
                <#if merchantInfoAddressList??>
                    <#list merchantInfoAddressList as myn>
                        <tr class="border-top-primary">
                            <td>${myn.level!}</td>
                            <td>${myn.name!}</td>
                            <td>${myn.address!}</td>
                            <td>${myn.tel!}</td>
                            <td>
                                <#if myn.image??>
                                    <img onclick="myImageOpen(this)" width="50px" src="${aliyunOosUrl!}${myn.image!}"/>
                                <#else >
                                    <img style="cursor: default" width="50px" src="/statics/admin/assets/images/default_goods.jpg"/>
                                </#if>
                            </td>
                            <td>
                                <div onclick="updateMerchantAddressAjax('${myn.id!}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                    <i class="icon-pencil3" id="update1${myn.id!}" onmousemove="layerTips('修改','update1${myn.id!}')"></i>
                                </div>
                                <div onclick="deleteMerchantAddressAjax('${myn.id!}')" style="color:red;float: left;margin-left: 10px;">
                                    <i class="icon-cross2" id="delete1${myn.id!}" onmousemove="layerTips('删除','delete1${myn.id!}')"></i>
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
    function deleteMerchantAddressAjax(id) {
        layer.load(1);
        $.ajax({
            url:"/admin/merchantInfoAddress/delete",
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
                    layer.alert(data.message);
                    getMerchantAddressInfo('${sqlAdminInfo.id!}');
                }else if(data.statusCode==203){
                    layer.alert(data.message);
                }
            }
        })
    }
    function insertMerchantAddressAjax(id) {
        $.ajax({
            url:"/admin/merchantInfoAddress/insert",
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

    function updateMerchantAddressAjax(id) {
        $.ajax({
            url:"/admin/merchantInfoAddress/update",
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