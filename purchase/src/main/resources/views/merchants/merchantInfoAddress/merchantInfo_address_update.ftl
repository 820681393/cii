<!DOCTYPE html>
<html lang="en">
<head>
<#assign title="修改商户收货地址">
<#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
    <#assign menuType="carInfo">
    <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <form action="${basePath}/merchants/merchantInfoAddress/updateIng" method="post">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-heading bg-primary">
                                <h6 class="panel-title">
                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">修改收货地址</a>
                                </h6>
                            </div>
                            <div id="accordion-styled-group3" class="panel-collapse">
                                <div class="panel-body">
                                    <div class="row col-xs-12" style="margin-top: 10px;">
                                        <input name="id" value="${sqlMerchantInfoAddress.id!}" style="display: none">
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">商家收货人</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="name" value="${sqlMerchantInfoAddress.name!}" class="form-control" placeholder="商家收货人">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">商家收货地址</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="address" value="${sqlMerchantInfoAddress.address!}" class="form-control" placeholder="商家收货地址">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">商家收货电话</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="tel" value="${sqlMerchantInfoAddress.tel!}" class="form-control" placeholder="商家收货电话">
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


