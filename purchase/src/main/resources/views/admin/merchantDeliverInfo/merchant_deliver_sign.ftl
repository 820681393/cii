<div class="content-wrapper" style="padding: 0px;">
    <div class="content" style="padding: 0px;">
        <form action="${basePath}/admin/merchantDeliverInfo/merchantDeliverSignIng" method="post" enctype="multipart/form-data">
            <div class="col-md-12" style="padding: 0px;">
                <div class="panel" style="margin: 0px;">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">配送单签收</a>
                        </h6>
                    </div>
                    <div id="accordion-styled-group3" class="panel-collapse">
                        <div class="panel-body">
                            <div class="row col-xs-12" style="margin-top: 10px;">
                                <input name="id" value="${merchantDeliverInfo.id!}" style="display: none">
                                <input name="state" value="4" style="display: none">
                                <div class="col-xs-12" style="margin-top: 20px;">
                                    <label class="col-xs-3">回执单据</label>
                                    <label class="col-xs-9">
                                        <input type="file" name="file" class="form-control" placeholder="回执单据">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;">
                                    <label class="col-xs-12"><button type="submit" class="btn btn-primary" style="width: 100%;">结算</button></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>




