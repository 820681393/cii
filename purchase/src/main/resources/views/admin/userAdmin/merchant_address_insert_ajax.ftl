<form action="" method="post">
    <div class="col-md-12" style="padding: 0px;">
        <div class="panel" style="margin: 0px;">
            <div class="panel-heading bg-primary">
                <h6 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">新增${sqlAdminInfo.nikeName!}收货地址</a>
                </h6>
            </div>
            <div id="accordion-styled-group3" class="panel-collapse">
                <div class="panel-body">
                    <input id="miid" value="${sqlAdminInfo.id!}" style="display: none">
                    <div class="row col-xs-12" style="margin-top: 10px;">
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3" style="">店级</label>
                            <label class="col-xs-9" style="">
                                <input type="text" id="level"  class="form-control" placeholder="店级">
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3" style="">商家收货人</label>
                            <label class="col-xs-9" style="">
                                <input type="text" id="name"  class="form-control" placeholder="商家收货人">
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3" style="">商家收货地址</label>
                            <label class="col-xs-9" style="">
                                <input type="text" id="address"  class="form-control" placeholder="商家收货地址">
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3" style="">商家收货电话</label>
                            <label class="col-xs-9" style="">
                                <input type="text" id="tel"  class="form-control" placeholder="商家收货电话">
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3" style="">商家门市图片</label>
                            <label class="col-xs-9" style="">
                                <input type="file"  id="file"  class="form-control" placeholder="商家门市图片">
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-12" style=""><button type="button" onclick="submitAddressInsert()" class="btn btn-primary" style="width: 100%;">新增</button></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<script>
    function submitAddressInsert(){
        var file = $("#file");
        if(!file.val()){
            layer.msg("请选择导入文件");
            return;
        }
        var miid = $("#miid").val();
        var name = $("#name").val();
        var address = $("#address").val();
        var tel = $("#tel").val();
        var level = $("#level").val();
        var formData = new FormData();
        formData.append("file",file[0].files[0]);
        formData.append("miid",miid);
        formData.append("name",name);
        formData.append("address",address);
        formData.append("tel",tel);
        formData.append("level",level);
        layer.load(2);
        $.ajax({
            url:"/admin/merchantInfoAddress/insertIng",
            type:"post",
            data: formData,
            headers: {
                'httpType': "JSON",
            },
            contentType: false,
            processData: false,
            async:false,
            success: function(data) {
                if(data.statusCode==200){
                    layer.alert("新增成功")
                    getMerchantAddressInfo(miid);
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }else{
                    layer.alert(data.message)
                }
            }
        });
    }
</script>