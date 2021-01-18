<div class="content-wrapper" style="padding: 0px;">
    <div class="content" style="padding: 0px;">
        <form action="${basePath}/admin/goodsInfo/updateSafeStockIng" method="post">
            <div class="col-md-12" style="padding: 0px;">
                <div class="panel" style="margin: 0px;">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">修改商品安全库存</a>
                        </h6>
                    </div>
                    <div id="accordion-styled-group3" class="panel-collapse">
                        <div class="panel-body">
                            <input name="id" value="${goodsInfo.id}" style="display: none">
                            <div class="row col-xs-12" style="margin-top: 10px;padding: 0px;">
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <input type="text" name="safeStock" class="form-control" value="${goodsInfo.safeStock!}" placeholder="安全库存(主)" style="display: inline-block;width: 85%;">
                                    ${goodsInfo.unitPrName!}
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <input type="text" name="safeStockSe" class="form-control" value="${goodsInfo.safeStockSe!}" placeholder="安全库存(辅)" style="display: inline-block;width: 85%;">
                                    ${goodsInfo.unitPeName!}
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-12" style="padding: 0px;"><button type="submit" class="btn btn-primary" style="width: 100%;">修改</button></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>




