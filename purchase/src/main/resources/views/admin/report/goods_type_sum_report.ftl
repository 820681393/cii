
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="商品分类报表">
    <#include "../common/head.ftl"/>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="report">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="panel">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">查询条件</a>
                        </h6>
                    </div>
                    <form action="/admin/report/goodsTypeSumReport" method="post">
                        <div id="accordion-styled-group3" class="panel-collapse">
                            <div class="panel-body">
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-xs-3">
                                        <select name="goodsType" class="form-control">
                                            <option value="1" <#if goodsType??&&goodsType==1>selected="selected"</#if>>一级分类</option>
                                            <option value="2" <#if goodsType??&&goodsType==2>selected="selected"</#if>>二级分类</option>
                                        </select>
                                    </div>
                                    <div class="col-xs-3">
                                        <input id="create-time-start" name="createTimeStart" value="<#if goodsInfo.createTimeStart??>${goodsInfo.createTimeStart!?string("yyyy-MM-dd HH:mm:ss")}</#if>" type="text" class="form-control" placeholder="开始时间">
                                    </div>
                                    <div class="col-xs-3">
                                        <input id="create-time-end" name="createTimeEnd" value="<#if goodsInfo.createTimeEnd??>${goodsInfo.createTimeEnd!?string("yyyy-MM-dd HH:mm:ss")}</#if>" type="text" class="form-control" placeholder="结束时间">
                                    </div>
                                    <div class="col-xs-3">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="isSumYm"
                                                    <#if isSumYm??>
                                                        <#if (isSumYm?string ("true","false"))=="true">
                                                            checked="checked"
                                                        </#if>
                                                    </#if>
                                                >按月统计
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="margin-top: 10px;text-align: center">
                                    <button type="submit" class="btn btn-primary" style="width: 158px;display: inline-block">查询</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">数据列表</a>
<#--                        <a class="collapsed"  onclick="location.href='/admin/goodsInfo/insert'" style="float: right">-->
<#--                            <i class="icon-plus2"></i>-->
<#--                            新增商品-->
<#--                        </a>-->
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr class="border-bottom-danger">
                                    <th>分类名称</th>
                                    <th>总数量</th>
                                    <th>总价格</th>
                                    <th>日期</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#if sumGoodsInfo??>
                                <#list sumGoodsInfo as myn>
                                    <tr class="border-top-primary">
                                        <td>${myn.name!}</td>
                                        <td>${myn.totalNumber!}${myn.realUnit!}</td>
                                        <td>${myn.totalPrice!}</td>
                                        <td>
                                        <#if myn.ymDate??>
                                            ${myn.ymDate!}
                                            <#else >
                                            - -
                                        </#if>
                                        </td>
                                    </tr>
                                </#list>
                            </#if>
<#--                            <tr class="border-top-primary">-->
<#--                                <td colspan="15">-->
<#--                                    <#import "../common/pagebar.ftl" as pagebar>-->
<#--                                    <@pagebar.pagebar pageInfo=pageInfos actionUrl="/admin/report/goodsInfoReport"/>-->
<#--                                </td>-->
<#--                            </tr>-->
                            </tbody>
                        </table>
                    </div>
                </div>
                <#include "../common/foot.ftl"/>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    //执行一个laydate实例
    var laydate = layui.laydate;
    laydate.render({
        elem: '#create-time-start' //指定元素
    });
    laydate.render({
        elem: '#create-time-end' //指定元素
    });

</script>
</html>



