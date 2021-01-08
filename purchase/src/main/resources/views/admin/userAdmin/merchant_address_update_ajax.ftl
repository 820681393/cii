<form action="" method="post">
    <div class="col-md-12" style="padding: 0px;">
        <div class="panel" style="margin: 0px;">
            <div class="panel-heading bg-primary">
                <h6 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">修改${sqlAdminInfo.nikeName!}收货地址</a>
                </h6>
            </div>
            <div id="accordion-styled-group3" class="panel-collapse">
                <div class="panel-body">
                    <input id="id" value="${sqlMerchantInfoAddress.id!}" style="display: none">
                    <div class="row col-xs-12" style="margin-top: 10px;">
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3" style="">店级</label>
                            <label class="col-xs-9" style="">
                                <input type="text" id="level" value="${sqlMerchantInfoAddress.level!}"  class="form-control" placeholder="店级">
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3" style="">商家电话</label>
                            <label class="col-xs-9" style="">
                                <input type="text" id="name" value="${sqlMerchantInfoAddress.name!}"  class="form-control" placeholder="商家收货人">
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3" style="">商家收货地址</label>
                            <label class="col-xs-9" style="">
                                <input type="text" id="address" value="${sqlMerchantInfoAddress.address!}"  class="form-control" placeholder="商家收货地址">
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3" style="">商家收货电话</label>
                            <label class="col-xs-9" style="">
                                <input type="text" id="tel" value="${sqlMerchantInfoAddress.tel!}"  class="form-control" placeholder="商家收货电话">
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3" style="">商家门市图片</label>
                            <label class="col-xs-9" style="">
                                <input type="file"  id="file"  class="form-control" placeholder="商家门市图片">
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-12" style=""><button type="button" onclick="submitAddressUpdate()" class="btn btn-primary" style="width: 100%;">修改</button></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<script>
    function submitAddressUpdate(){
        var file = $("#file");
        var name = $("#name").val();
        var address = $("#address").val();
        var tel = $("#tel").val();
        var level = $("#level").val();
        var id = $("#id").val();
        var formData = new FormData();
        formData.append("file",file[0].files[0]);
        formData.append("name",name);
        formData.append("address",address);
        formData.append("tel",tel);
        formData.append("level",level);
        formData.append("id",id);
        layer.load(2);
        $.ajax({
            url:"/admin/merchantInfoAddress/updateIng",
            type:"post",
            data: formData,
            headers: {
                'httpType': "JSON",
            },
            contentType: false,
            processData: false,
            async:false,
            success: function(data) {
                layer.closeAll();
                if(data.statusCode==200){
                    layer.alert("修改成功")
                    getMerchantAddressInfo("${sqlAdminInfo.id!}");
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }else{
                    layer.alert(data.message)
                }
            }
        });
    }
</script>