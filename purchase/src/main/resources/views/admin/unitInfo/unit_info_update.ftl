<div class="content-wrapper" style="padding: 0px;">
    <div class="content" style="padding: 0px;">
        <form action="${basePath}/admin/unitInfo/updateIng" method="post">
            <div class="col-md-12" style="padding: 0px;">
                <div class="panel" style="margin: 0px;">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">修改单位</a>
                        </h6>
                    </div>
                    <div id="accordion-styled-group3" class="panel-collapse">
                        <div class="panel-body">
                            <input name="id" value="${unitInfo.id}" style="display: none">
                            <div class="row col-xs-12" style="margin-top: 10px;padding: 0px;">
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-3" style="padding: 0px;">单位名称</label>
                                    <label class="col-xs-9" style="padding: 0px;">
                                        <input type="text" name="name" value="${unitInfo.name!}" class="form-control" placeholder="单位名称">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-3" style="padding: 0px;">单位类型</label>
                                    <label class="col-xs-9" style="padding: 0px;">
                                        <select name="type" class="form-control">
                                            <option value="1" <#if unitInfo.type??&&unitInfo.type==1>selected="selected"</#if>>主单位</option>
                                            <option value="2" <#if unitInfo.type??&&unitInfo.type==2>selected="selected"</#if>>辅单位</option>
                                        </select>
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