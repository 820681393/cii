
<div class="content-wrapper" style="padding: 0px;">
    <div class="content" style="padding: 0px;">
        <form action="${basePath}/admin/menuInfo/updateIng" method="post">
            <div class="col-md-12" style="padding: 0px;">
                <div class="panel" style="margin: 0px;">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">修改菜单</a>
                        </h6>
                    </div>
                    <div id="accordion-styled-group3" class="panel-collapse">
                        <div class="panel-body">
                            <div class="row col-xs-12" style="margin-top: 10px;padding: 0px;">
                                <input type="text" name="id" style="display: none" value="${sqlMenuInfo.id!}">
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-3" style="padding: 0px;">菜单名称</label>
                                    <label class="col-xs-9" style="padding: 0px;">
                                        <input type="text" name="name" value="${sqlMenuInfo.name!}" class="form-control" placeholder="菜单名称">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-3" style="padding: 0px;">菜单类型</label>
                                    <label class="col-xs-9" style="padding: 0px;">
                                        <input type="text" name="type" value="${sqlMenuInfo.type!}" class="form-control" placeholder="菜单类型">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-3" style="padding: 0px;">菜单地址</label>
                                    <label class="col-xs-9" style="padding: 0px;">
                                        <input type="text" name="url" value="${sqlMenuInfo.url!}" class="form-control" placeholder="菜单地址">
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



