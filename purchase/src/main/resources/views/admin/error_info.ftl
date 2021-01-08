
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="错误页面">
    <#include "common/head.ftl"/>
</head>
<body>
<div class="page-container">
    <div class="page-content">
        <div class="content-wrapper" style="height: 100%;">
            <div class="content" style="height: 100%">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-flat">
                            <div class="tabbable tab-content-bordered" style="height: 100%">
                                <ul class="nav nav-tabs nav-tabs-highlight nav-justified" id="tableInfos">
                                    <li class="active" name="table" ><a href="#bordered-justified-tab1" data-toggle="tab">错误页</a></li>
                                </ul>
                                <div class="tab-content" style="height: 100%" id="tableValues">
                                    <div class="tab-pane has-padding active" name="tableValue" id="bordered-justified-tab1">
                                        <div class="container-fluid text-center" style="margin-top: 20%;height: 855px;">
                                            <h1 class="error-title offline-title">${code!}</h1>
                                            <h5 class="error-title offline-title">${info!}</h5>
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
