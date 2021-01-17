<div class="content-wrapper" style="padding: 0px;">
    <div class="content" style="padding: 0px;">
        <form action="${basePath}/admin/goodsCheckTask/insertIng" method="post">
            <div class="col-md-12" style="padding: 0px;">
                <div class="panel" style="margin: 0px;">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">发起盘点</a>
                        </h6>
                    </div>
                    <div id="accordion-styled-group3" class="panel-collapse">
                        <div class="panel-body">
                            <div class="row col-xs-12" style="margin-top: 10px;padding: 0px;">
                                <div class="col-xs-12" style="margin-top: 20px;">
                                    <label class="col-xs-3">盘点种类</label>
                                    <label class="col-xs-9">
                                        <select name="goid" class="form-control">
                                            <#if goodsOneTypeList??>
                                                <#list goodsOneTypeList as myn>
                                                    <option value="${myn.id!}">${myn.name!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;">
                                    <label class="col-xs-3">盘点员</label>
                                    <label class="col-xs-9">
                                        <select name="receiveAiid" class="form-control">
                                            <#if pdAdminInfoList??>
                                                <#list pdAdminInfoList as myn>
                                                    <option value="${myn.id!}">${myn.nikeName!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-12" style="padding: 0px;"><button type="submit" class="btn btn-primary" style="width: 100%;">确定</button></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>




