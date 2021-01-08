<!DOCTYPE html>
<html lang="en">
<head>
<#assign title="后台账号修改">
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
                <form action="${basePath}/admin/adminUser/updateIng" method="post">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-heading bg-primary">
                                <h6 class="panel-title">
                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">后台账号信息修改</a>
                                </h6>
                            </div>
                            <div id="accordion-styled-group3" class="panel-collapse">
                                <div class="panel-body">
                                    <div class="row col-xs-12" style="margin-top: 10px;">
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">后台用户名</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="userName" value="${adminUser.userName!}"  class="form-control" placeholder="后台用户名">
                                                <input type="text" name="id" value="${adminUser.id!}" class="form-control" style="display: none">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">后台用户密码</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="passWord" value="${adminUser.passWord!}"  class="form-control" placeholder="后台用户密码">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">后台用户昵称</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="nikeName" value="${adminUser.nikeName!}"  class="form-control" placeholder="后台用户昵称">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">后台用户状态</label>
                                            <label class="col-xs-9">
                                                <select class="form-control" name="state" >
                                                    <option <#if adminUser.state?? && adminUser.state==1>selected</#if> value="1">正常账户</option>
                                                    <option <#if adminUser.state?? && adminUser.state==2>selected</#if> value="2">禁止登陆</option>
                                                </select>
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">手机号</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="tel" value="${adminUser.tel!}" class="form-control" placeholder="手机号">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">微信号</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="wxName" value="${adminUser.wxName!}" class="form-control" placeholder="微信号">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">微信昵称</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="wxNikeName" value="${adminUser.wxNikeName!}" class="form-control" placeholder="微信昵称">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">用户层级</label>
                                            <label class="col-xs-9">
                                                <select class="form-control" name="type" >
                                                    <option <#if adminUser.type?? && adminUser.state==0>selected</#if> value="0">管理层</option>
                                                    <option <#if adminUser.type?? && adminUser.state==1>selected</#if> value="1">采购层</option>
                                                    <option <#if adminUser.type?? && adminUser.state==2>selected</#if> value="2">商户层</option>
                                                    <option <#if adminUser.type?? && adminUser.state==3>selected</#if> value="3">财务层</option>
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



