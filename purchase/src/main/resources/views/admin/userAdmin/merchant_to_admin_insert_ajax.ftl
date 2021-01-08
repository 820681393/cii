<div class="content-wrapper" style="padding: 0px;">
    <div class="content" style="padding: 0px;">
        <form >
            <div class="col-md-12" style="padding: 0px;">
                <div class="panel" style="margin: 0px;">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">新增${sqlAdminInfo.nikeName!}管理员</a>
                        </h6>
                    </div>
                    <input type="text" id="aiid"  value="${sqlAdminInfo.id!}" style="display: none" class="form-control" placeholder="名称">
                    <div id="accordion-styled-group3" class="panel-collapse">
                        <div class="panel-body">
                            <div class="row col-xs-12" style="margin-top: 10px;padding: 0px;">
                                <div class="col-xs-12" style="margin-top: 20px;">
                                    <label class="col-xs-3" style="">名称</label>
                                    <label class="col-xs-9" style="">
                                        <input type="text" id="name"  class="form-control" placeholder="名称">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;">
                                    <label class="col-xs-3" style="">联系电话</label>
                                    <label class="col-xs-9" style="">
                                        <input type="text" id="tel"  class="form-control" placeholder="联系电话">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;">
                                    <label class="col-xs-3" style="">微信号码</label>
                                    <label class="col-xs-9" style="">
                                        <input type="text" id="wxNumber"  class="form-control" placeholder="微信号码">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;">
                                    <label class="col-xs-3" style="">飞机号码</label>
                                    <label class="col-xs-9" style="">
                                        <input type="text" id="tgNumber"  class="form-control" placeholder="飞机号码">
                                    </label>
                                </div>
<#--                                <div class="col-xs-12" style="margin-top: 20px;">-->
<#--                                    <label class="col-xs-3">类型</label>-->
<#--                                    <label class="col-xs-9">-->
<#--                                        <select class="form-control" id="type" name="type">-->
<#--                                            <option  value="1">下单人</option>-->
<#--                                            <option  value="2">配送人</option>-->
<#--                                        </select>-->
<#--                                    </label>-->
<#--                                </div>-->
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-12" style="padding: 0px;"><button type="button" onclick="insertMerchantToAdminIngAjax()" class="btn btn-primary" style="width: 100%;">新增</button></label>
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
    function insertMerchantToAdminIngAjax() {
        var aiid=$('#aiid').val();
        var type=$('#type').val();
        var name=$('#name').val();
        var tel=$('#tel').val();
        var wxNumber=$('#wxNumber').val();
        var tgNumber=$('#tgNumber').val();
        layer.load(1);
        $.ajax({
            url:"/admin/merchantToAdmin/insertMerchantToAdminIngAjax",
            data:{
                aiid:aiid,
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
                    merchantToAdminAjax(aiid);
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }
            }
        })
    }
</script>


