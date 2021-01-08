<!DOCTYPE html>
<html lang="en">
<head>
<#assign title="角色新增">
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
                <form action="${basePath}/admin/roleInfo/insertIng" method="post">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-heading bg-primary">
                                <h6 class="panel-title">
                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">后台账号信息新增</a>
                                </h6>
                            </div>
                            <div id="accordion-styled-group3" class="panel-collapse">
                                <div class="panel-body">
                                    <div class="row col-xs-12" style="margin-top: 10px;">
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">角色名称</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="name" class="form-control" placeholder="角色名称">
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


