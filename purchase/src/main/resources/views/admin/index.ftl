
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="采购管理后台">
    <#include "common/head.ftl"/>
</head>
<body>
<#include "common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
    <#assign menuType="indexPage">
    <#include "common/menu.ftl"/>
        <div class="content-wrapper" style="height: 100%;">

            <div class="content" style="height: 100%">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-flat">
                            <div class="tabbable tab-content-bordered" style="height: 100%">
                                <ul class="nav nav-tabs nav-tabs-highlight nav-justified" id="tableInfos">
                                    <li class="active" name="table" ><a href="#bordered-justified-tab1" data-toggle="tab">首页</a></li>
                                </ul>
                                <div class="tab-content" style="height: 100%" id="tableValues">
                                    <div style="margin-top: 50px;margin-left: 50px;margin-right: 50px;">
                                        <div class="panel panel-primary panel-bordered" style="margin-top: 20px;">
                                            <div class="panel-heading">
                                                <h6 class="panel-title">平台公告</h6>
                                            </div>
                                            <div class="panel-body">
                                                <div class="panel-body">
                                                    <ul class="media-list chat-list chat-list-inverse content-group">
                                                        <#if noticeInfoList??>
                                                            <#list noticeInfoList as myn>
                                                                <li class="media">
                                                                    <div class="media-left">
                                                                        <a>
                                                                            <img src="/statics/admin/assets/images/pnotify/info.png" class="img-circle" alt="">
                                                                        </a>
                                                                    </div>
                                                                    <div class="media-body">
                                                                        <div class="media-content">
                                                                            ${myn.content!}
                                                                            <br/>发布人 ：${myn.nikeName!}
                                                                            <br/>发布时间：${myn.createTime?string("yyyy-MM-dd")!}
                                                                        </div>
                                                                    </div>
                                                                </li>
                                                            </#list>
                                                        </#if>
                                                    </ul>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <#include "common/foot.ftl"/>
            </div>
        </div>
    </div>
</div>


</body>
</html>
