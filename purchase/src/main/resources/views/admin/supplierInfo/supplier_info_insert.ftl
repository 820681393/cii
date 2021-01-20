<!DOCTYPE html>
<html lang="en">
<head>
<#assign title="新增供应商">
<#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
    <#assign menuType="supplierInfo">
    <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <form action="${basePath}/admin/supplierInfo/insertIng" method="post">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-heading bg-primary">
                                <h6 class="panel-title">
                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">新增供应商</a>
                                </h6>
                            </div>
                            <div id="accordion-styled-group3" class="panel-collapse">
                                <div class="panel-body">
                                    <div class="row col-xs-12" style="margin-top: 10px;">
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">供应商名称</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="name" class="form-control" placeholder="供应商名称">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">联系人名称</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="linkUser" class="form-control" placeholder="联系人名称">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">微信号</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="wxNumber" class="form-control" placeholder="微信号">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">供应商电话</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="tel" class="form-control" placeholder="供应商电话">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">供应商地址</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="address" class="form-control" placeholder="供应商地址">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">供应状态</label>
                                            <label class="col-xs-9">
                                                <select class="form-control" name="state" >
                                                    <option  value="1">营业</option>
                                                    <option  value="2">停业</option>
                                                </select>
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">营业时间</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="businessTime" class="form-control" placeholder="营业时间">
                                            </label>
                                        </div>
<#--                                        <div class="col-xs-12" style="margin-top: 20px;">-->
<#--                                            <label class="col-xs-3">币种</label>-->
<#--                                            <label class="col-xs-9">-->
<#--                                                <select name="currencyType" class="form-control">-->
<#--                                                    <option value="CNY">CNY</option>-->
<#--                                                    <option value="USD">USD</option>-->
<#--                                                    <option value="PHP">PHP</option>-->
<#--                                                </select>-->
<#--                                            </label>-->
<#--                                        </div>-->
<#--                                        <div class="col-xs-12" style="margin-top: 20px;">-->
<#--                                            <label class="col-xs-3">今日汇率</label>-->
<#--                                            <label class="col-xs-9">-->
<#--                                                <input type="text" name="exchangeRate" class="form-control" placeholder="今日汇率">-->
<#--                                            </label>-->
<#--                                        </div>-->
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-12"><button type="submit" class="btn btn-primary" style="width: 100%;">新增</button></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3"></div>
                </form>
            <#include "../common/foot.ftl"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>



