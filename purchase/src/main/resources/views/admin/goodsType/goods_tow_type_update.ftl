<div class="content-wrapper" style="padding: 0px;">
    <div class="content" style="padding: 0px;">
        <form action="${basePath}/admin/goodsTowType/updateIng" method="post">
            <div class="col-md-12" style="padding: 0px;">
                <div class="panel" style="margin: 0px;">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">修改分类二级分类</a>
                        </h6>
                    </div>
                    <div id="accordion-styled-group3" class="panel-collapse">
                        <div class="panel-body">
                            <input name="id" value="${goodsTowType.id!}" style="display: none">
                            <div class="row col-xs-12" style="margin-top: 10px;padding: 0px;">
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-3" style="padding: 0px;">中文名称</label>
                                    <label class="col-xs-9" style="padding: 0px;">
                                        <input type="text" name="name" value="${goodsTowType.name!}" class="form-control" placeholder="类别中文名称">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-3" style="padding: 0px;">英文名称</label>
                                    <label class="col-xs-9" style="padding: 0px;">
                                        <input type="text" name="ename" value="${goodsTowType.ename!}" class="form-control" placeholder="类别英文名称">
                                    </label>
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