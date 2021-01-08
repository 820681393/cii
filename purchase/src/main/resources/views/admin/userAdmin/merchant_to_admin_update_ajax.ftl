<div class="content-wrapper" style="padding: 0px;">
    <div class="content" style="padding: 0px;">
        <form >
            <div class="col-md-12" style="padding: 0px;">
                <div class="panel" style="margin: 0px;">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">修改${sqlAdminInfo.nikeName!}管理员</a>
                        </h6>
                    </div>
                    <input type="text" id="id"  value="${sqlMerchantUserInfo.id!}" style="display: none" class="form-control" placeholder="名称">
                    <div id="accordion-styled-group3" class="panel-collapse">
                        <div class="panel-body">
                            <div class="row col-xs-12" style="margin-top: 10px;padding: 0px;">
                                <div class="col-xs-12" style="margin-top: 20px;">
                                    <label class="col-xs-3" style="">名称</label>
                                    <label class="col-xs-9" style="">
                                        <input type="text" id="name" value="${sqlMerchantUserInfo.name!}" class="form-control" placeholder="名称">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;">
                                    <label class="col-xs-3" style="">联系电话</label>
                                    <label class="col-xs-9" style="">
                                        <input type="text" id="tel" value="${sqlMerchantUserInfo.tel!}" class="form-control" placeholder="联系电话">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;">
                                    <label class="col-xs-3" style="">微信号码</label>
                                    <label class="col-xs-9" style="">
                                        <input type="text" id="wxNumber" value="${sqlMerchantUserInfo.wxNumber!}"  class="form-control" placeholder="微信号码">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;">
                                    <label class="col-xs-3" style="">飞机号码</label>
                                    <label class="col-xs-9" style="">
                                        <input type="text" id="tgNumber" value="${sqlMerchantUserInfo.tgNumber!}" class="form-control" placeholder="飞机号码">
                                    </label>
                                </div>
<#--                                <div class="col-xs-12" style="margin-top: 20px;">-->
<#--                                    <label class="col-xs-3">类型</label>-->
<#--                                    <label class="col-xs-9">-->
<#--                                        <select class="form-control" id="type" name="type">-->
<#--                                            <option <#if sqlMerchantUserInfo.type?? && sqlMerchantUserInfo.type==1>selected</#if> value="1">下单人</option>-->
<#--                                            <option <#if sqlMerchantUserInfo.type?? && sqlMerchantUserInfo.type==2>selected</#if> value="2">配送人</option>-->
<#--                                        </select>-->
<#--                                    </label>-->
<#--                                </div>-->
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-12" style="padding: 0px;"><button type="button" onclick="updateMerchantToAdminIngAjax()" class="btn btn-primary" style="width: 100%;">修改</button></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    function updateMerchantToAdminIngAjax() {
        var id=$('#id').val();
        var type=$('#type').val();
        var name=$('#name').val();
        var tel=$('#tel').val();
        var wxNumber=$('#wxNumber').val();
        var tgNumber=$('#tgNumber').val();
        layer.load(1);
        $.ajax({
            url:"/admin/merchantToAdmin/updateMerchantToAdminIngAjax",
            data:{
                id:id,
                name:name,
                tel:tel,
                wxNumber:wxNumber,
                tgNumber:tgNumber,
                type:type
            },
            headers: {
                'httpType': "JSON",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.closeAll();
                if(data.statusCode==200){
                    merchantToAdminAjax("${sqlMerchantUserInfo.aiid!}");
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }
            }
        })
    }
</script>


