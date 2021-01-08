<!DOCTYPE html>
<html lang="en">
<head>
<#assign title="商户后台账号新增">
<#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
    <#assign menuType="adminMerchantOrderInfo">
    <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-heading bg-primary">
                                <h6 class="panel-title">
                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">商户后台账号信息新增</a>
                                </h6>
                            </div>
                            <div id="accordion-styled-group3" class="panel-collapse">
                                <div class="panel-body">
                                    <form id="adminInfo">
                                    <div class="row col-xs-12" style="margin-top: 10px;">
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">后台用户名</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="userName" class="form-control" placeholder="后台用户名">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">后台用户密码</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="passWord" class="form-control" placeholder="后台用户密码">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">后台用户昵称</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="nikeName" class="form-control" placeholder="后台用户昵称">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">手机号</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="tel" class="form-control" placeholder="手机号">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">微信号</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="wxName" class="form-control" placeholder="微信号">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">微信昵称</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="wxNikeName" class="form-control" placeholder="微信昵称">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;display: none">
                                            <label class="col-xs-3">用户层级</label>
                                            <label class="col-xs-9">
                                                <select class="form-control" name="type" >
<#--                                                    <option value="0">管理层</option>-->
<#--                                                    <option value="1">采购层</option>-->
                                                    <option value="2">商户层</option>
<#--                                                    <option value="3">财务层</option>-->
                                                </select>
                                            </label>
                                        </div>
                                    </div>
                                    </form>
                                    <form id="merchantInfo">
                                        <div class="row col-xs-12" style="margin-top: 10px;">
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3" style="">负责人</label>
                                            <label class="col-xs-9" style="">
                                                <input type="text" name="contactName" value="" class="form-control" placeholder="负责人">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">结算方式</label>
                                            <label class="col-xs-9">
                                                <select class="form-control" name="settlementMethod" >
                                                    <option value="1">现金结算</option>
                                                    <option value="2">按周结算</option>
                                                </select>
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">回扣方式</label>
                                            <label class="col-xs-9">
                                                <select class="form-control" name="rebateMethod" >
                                                    <option  value="1">无回扣</option>
                                                    <option  value="2">数值回扣</option>
                                                    <option  value="3">百分比回扣</option>
                                                </select>
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3" style="">数值回扣</label>
                                            <label class="col-xs-9" style="">
                                                <input type="text" name="rebateNumber" value="" class="form-control" placeholder="数值回扣">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3" style="">百分比回扣</label>
                                            <label class="col-xs-9" style="">
                                                <input type="text" name="rebatePercentage" value="" class="form-control" placeholder="百分比回扣(1%=0.01)">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3" style="">商户属性</label>
                                            <label class="col-xs-9" style="">
                                                <input type="text" name="merchantAttribute" value="" class="form-control" placeholder="商户属性">
                                            </label>
                                        </div>
                                        </div>
                                    </form>
                                    <div class="col-xs-12" style="margin-top: 20px;">
                                        <label class="col-xs-12" style=""><button  id="insertBtn" class="btn btn-primary" style="width: 100%;">新增</button></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            <#include "../common/foot.ftl"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<script>
    $("#insertBtn").click(function(){
        var adminInfoParam = $('#adminInfo').serialize();
        var merchantInfoParam = $('#merchantInfo').serialize();
        // var adminInfoParam = {};
        // var merchantInfoParam = {};
        // $.each(adminInfo, function() {
        //     adminInfoParam[this.name] = this.value;
        // });
        // $.each(merchantInfo, function() {
        //     merchantInfoParam[this.name] = this.value;
        // });
        // console.log(adminInfoParam);
        // console.log(merchantInfoParam);
        $.ajax({
            url: "/admin/adminUser/insertIngAjax",
            type: "get",
            headers: {
                'httpType': "JSON",
            },
            contentType: false,
            processData: false,
            async: true,
            dataType:"json",
            data:adminInfoParam,
            success: function (data) {
                var adminInfo = data.data;
                console.log(adminInfo);
                if (data.statusCode == 200) {
                    $.ajax({
                        url: "/admin/merchantInfo/saveMerchantInfoAjax?aiid="+adminInfo.id,
                        type: "get",
                        headers: {
                            'httpType': "JSON",
                        },
                        contentType: false,
                        processData: false,
                        async: true,
                        dataType:"json",
                        data:merchantInfoParam,
                        success: function (data) {
                            if (data.statusCode == 200) {
                                window.location.href = "/admin/adminUser/index?type=2";
                            }
                        }
                    });
                }
            }
        });
    });
</script>


