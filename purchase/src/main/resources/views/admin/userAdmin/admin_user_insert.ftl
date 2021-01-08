<!DOCTYPE html>
<html lang="en">
<head>
<#assign title="后台账号新增">
<#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
    <#assign menuType="adminUser">
    <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <form action="${basePath}/admin/adminUser/insertIng" method="post">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-heading bg-primary">
                                <h6 class="panel-title">
                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">账户基本信息</a>
                                </h6>
                            </div>
                            <div id="accordion-styled-group3" class="panel-collapse">
                                <div class="panel-body">
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
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">用户层级</label>
                                            <label class="col-xs-9">
                                                <select class="form-control" name="type" >
                                                    <option value="0">管理层</option>
                                                    <option value="1">采购层</option>
                                                    <option value="2">商户层</option>
                                                    <option value="3">财务层</option>
                                                </select>
                                            </label>
                                        </div>
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

<script>

    <#if msgInfo??>
    layer.msg("${msgInfo}");
    </#if>
</script>


