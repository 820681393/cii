<!DOCTYPE html>
<html lang="en">
<head>
<#assign title="修改供应商">
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
                <form action="${basePath}/admin/supplierInfo/updateIng" method="post">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-heading bg-primary">
                                <h6 class="panel-title">
                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">修改供应商</a>
                                </h6>
                            </div>
                            <div id="accordion-styled-group3" class="panel-collapse">
                                <div class="panel-body">
                                    <div class="row col-xs-12" style="margin-top: 10px;">
                                        <input name="id" value="${sqlSupplierInfo.id!}" style="display: none">
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">供应商名称</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="name" value="${sqlSupplierInfo.name!}" class="form-control" placeholder="供应商名称">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">供应商电话</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="tel" value="${sqlSupplierInfo.tel!}" class="form-control" placeholder="供应商电话">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">供应商地址</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="address" value="${sqlSupplierInfo.address!}" class="form-control" placeholder="供应商地址">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">联系人名称</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="linkUser" value="${sqlSupplierInfo.linkUser!}" class="form-control" placeholder="联系人名称">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">微信号</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="wxNumber" value="${sqlSupplierInfo.wxNumber!}" class="form-control" placeholder="微信号">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">供应状态</label>
                                            <label class="col-xs-9">
                                                <select class="form-control" name="state" >
                                                    <option  value="1" <#if sqlSupplierInfo.state==1> selected</#if>>营业</option>
                                                    <option  value="2" <#if sqlSupplierInfo.state==2> selected</#if>>停业</option>
                                                </select>
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-12"><button type="submit" class="btn btn-primary" style="width: 100%;">修改</button></label>
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



