

<form action="/admin/merchantInfo/saveMerchantInfo" method="post">
    <div class="col-md-12" style="padding: 0px;">
        <div class="panel" style="margin: 0px;">
            <div class="panel-heading bg-primary">
                <h6 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">设置${sqlAdminInfo.nikeName!}商户信息</a>
                </h6>
            </div>
            <div id="accordion-styled-group3" class="panel-collapse">
                <div class="panel-body">
                    <input name="id" value="${sqlMerchantInfo.id!}" style="display: none">
                    <input name="aiid" value="${sqlAdminInfo.id!}" style="display: none">
                    <div class="row col-xs-12" style="margin-top: 10px;">
<#--                        <div class="col-xs-12" style="margin-top: 20px;">-->
<#--                            <label class="col-xs-3" style="">商家电话</label>-->
<#--                            <label class="col-xs-9" style="">-->
<#--                                <input type="text" name="phone" value="${sqlMerchantInfo.phone!}" class="form-control" placeholder="商家电话">-->
<#--                            </label>-->
<#--                        </div>-->
<#--                        <div class="col-xs-12" style="margin-top: 20px;">-->
<#--                            <label class="col-xs-3" style="">商家地址</label>-->
<#--                            <label class="col-xs-9" style="">-->
<#--                                <input type="text" name="address" value="${sqlMerchantInfo.address!}" class="form-control" placeholder="商家地址">-->
<#--                            </label>-->
<#--                        </div>-->
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3" style="">负责人</label>
                            <label class="col-xs-9" style="">
                                <input type="text" name="contactName" value="${sqlMerchantInfo.contactName!}" class="form-control" placeholder="负责人">
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3">结算方式</label>
                            <label class="col-xs-9">
                                <select class="form-control" name="settlementMethod" >
                                    <option <#if sqlMerchantInfo.settlementMethod?? && sqlMerchantInfo.settlementMethod==1>selected</#if> value="1">现金结算</option>
                                    <option <#if sqlMerchantInfo.settlementMethod?? && sqlMerchantInfo.settlementMethod==2>selected</#if> value="2">按周结算</option>
                                </select>
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3">回扣方式</label>
                            <label class="col-xs-9">
                                <select class="form-control" name="rebateMethod" >
                                    <option <#if sqlMerchantInfo.rebateMethod?? && sqlMerchantInfo.rebateMethod==1>selected</#if> value="1">无回扣</option>
                                    <option <#if sqlMerchantInfo.rebateMethod?? && sqlMerchantInfo.rebateMethod==2>selected</#if> value="2">数值回扣</option>
                                    <option <#if sqlMerchantInfo.rebateMethod?? && sqlMerchantInfo.rebateMethod==3>selected</#if> value="3">百分比回扣</option>
                                </select>
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3" style="">数值回扣</label>
                            <label class="col-xs-9" style="">
                                <input type="text" name="rebateNumber" value="${sqlMerchantInfo.rebateNumber!}" class="form-control" placeholder="数值回扣">
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3" style="">百分比回扣</label>
                            <label class="col-xs-9" style="">
                                <input type="text" name="rebatePercentage" value="${sqlMerchantInfo.rebatePercentage!}" class="form-control" placeholder="百分比回扣(1%=0.01)">
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-3" style="">商户属性</label>
                            <label class="col-xs-9" style="">
                                <input type="text" name="merchantAttribute" value="${sqlMerchantInfo.merchantAttribute!}" class="form-control" placeholder="商户属性">
                            </label>
                        </div>
                        <div class="col-xs-12" style="margin-top: 20px;">
                            <label class="col-xs-12" style=""><button type="submit"  class="btn btn-primary" style="width: 100%;">保存</button></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

