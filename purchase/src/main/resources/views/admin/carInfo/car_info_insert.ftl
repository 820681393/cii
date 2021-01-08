<!DOCTYPE html>
<html lang="en">
<head>
<#assign title="新增车辆">
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
                <form action="${basePath}/admin/carInfo/insertIng" method="post" enctype="multipart/form-data">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-heading bg-primary">
                                <h6 class="panel-title">
                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">新增车辆</a>
                                </h6>
                            </div>
                            <div id="accordion-styled-group3" class="panel-collapse">
                                <div class="panel-body">
                                    <div class="row col-xs-12" style="margin-top: 10px;">
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">车辆名称</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="name" class="form-control" placeholder="车辆名称">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">车主</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="owner" class="form-control" placeholder="车主">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">使用人员</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="useName" class="form-control" placeholder="使用人员">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">车牌号码</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="number" class="form-control" placeholder="车牌号码">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">车辆图片</label>
                                            <label class="col-xs-9">
                                                <input type="file" name="image1" class="form-control" placeholder="车辆图片">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">车辆OR图片</label>
                                            <label class="col-xs-9">
                                                <input type="file" name="carOrImage1" class="form-control" placeholder="车辆OR图片">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">车辆CR图片</label>
                                            <label class="col-xs-9">
                                                <input type="file" name="carCrImage1" class="form-control" placeholder="车辆CR图片">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">车辆OR时间</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="carOrTime" class="form-control" placeholder="车辆OR时间">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">车辆CR时间</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="carCrTime" class="form-control" placeholder="车辆CR时间">
                                            </label>
                                        </div>

                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">限行日期</label>
                                            <label class="col-xs-9">
                                                <input type="text" name="limitDay" class="form-control" placeholder="限行日期周一到周日（1,2,3 为周一周二周三限行）">
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">车辆状态</label>
                                            <label class="col-xs-9">
                                                <select class="form-control" name="state" >
                                                    <option  value="1">可用</option>
                                                    <option  value="2">不可用</option>
                                                    <option  value="3">维修中</option>
                                                    <option  value="4">行驶中</option>
                                                </select>
                                            </label>
                                        </div>
                                        <div class="col-xs-12" style="margin-top: 20px;">
                                            <label class="col-xs-3">车辆类型</label>
                                            <label class="col-xs-9">
                                                <select class="form-control" name="type" >
                                                    <option  value="1">正常车辆</option>
                                                    <option  value="2">无限使用</option>
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


