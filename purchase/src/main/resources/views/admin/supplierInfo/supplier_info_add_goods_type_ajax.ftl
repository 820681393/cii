<div class="content-wrapper" style="padding: 0px;">
    <div class="content" style="padding: 0px;">
        <form action="" >
            <div class="col-md-12" style="padding: 0px;">
                <div class="panel" style="margin: 0px;">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">新增分类二级分类</a>
                        </h6>
                    </div>
                    <div id="accordion-styled-group3" class="panel-collapse">
                        <div class="panel-body">
                            <input name="gotid" id="gotid" value="${gotid}" style="display: none">
                            <input name="gttid" id="gttid" value="${gttid}" style="display: none">
                            <input name="siid" id="siid" value="${siid}" style="display: none">
                            <div class="row col-xs-12" style="margin-top: 10px;padding: 0px;">
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-3" style="padding: 0px;">价格</label>
                                    <label class="col-xs-9" style="padding: 0px;">
                                        <input type="text" name="price" id="price" class="form-control" placeholder="price">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-3" style="padding: 0px;">单位</label>
                                    <label class="col-xs-9" style="padding: 0px;">
                                        <input type="text" name="unit" id="unit" class="form-control" placeholder="unit">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-12" style="padding: 0px;"><button type="button" onclick="addGoodsTypeIngAjax()" class="btn btn-primary" style="width: 100%;">新增</button></label>
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
    function addGoodsTypeIngAjax() {
        var gotid=$('#gotid').val();
        var gttid=$('#gttid').val();
        var siid=$('#siid').val();
        var price=$('#price').val();
        var unit=$('#unit').val();
        $.ajax({
            url:"/admin/supplierInfo/addGoodsTypeIngAjax",
            data:{
                gotid:gotid,
                gttid:gttid,
                siid:siid,
                price:price,
                unit:unit
            },
            headers: {
                'httpType': "JSON",
            },
            type:"get",
            async:true,
            success:function (data) {
                if(data.statusCode==200){
                    getGoodsTypeAjax(siid);
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }else{
                    layer.msg("新增失败")
                }
            }
        })
    }
</script>
