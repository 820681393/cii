
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="库存盘点">
    <#include "../common/head.ftl"/>
    <style>
    </style>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="goodsInfo">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
<#--                <div class="panel">-->
<#--                    <div class="panel-heading bg-primary">-->
<#--                        <h6 class="panel-title">-->
<#--                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">查询条件</a>-->
<#--                        </h6>-->
<#--                    </div>-->
<#--                    <form action="/admin/goodsInfo/stockIndex" method="post">-->
<#--                        <div id="accordion-styled-group3" class="panel-collapse">-->
<#--                            <div class="panel-body">-->
<#--                                <div class="row" style="margin-top: 10px;">-->
<#--                                    <div class="col-xs-3">-->
<#--                                        <input type="text"  name="chName" value="${goodsInfo.chName!}"class="form-control" placeholder="中文名称">-->
<#--                                    </div>-->
<#--                                    <div class="col-xs-3">-->
<#--                                        <input type="text"  name="enName" value="${goodsInfo.enName!}"class="form-control" placeholder="英文名称">-->
<#--                                    </div>-->
<#--                                    <div class="col-xs-3">-->
<#--                                        <select class="form-control" name="goid">-->
<#--                                            <option value="">一级分类</option>-->
<#--                                            <#if goodsOneTypeList??>-->
<#--                                                <#list goodsOneTypeList as myn>-->
<#--                                                    <option value="${myn.id!}" <#if goodsInfo.goid??&&myn.id==goodsInfo.goid>selected</#if>>${myn.name!}</option>-->
<#--                                                </#list>-->
<#--                                            </#if>-->
<#--                                        </select>-->
<#--                                    </div>-->
<#--                                </div>-->
<#--                                <div class="row" style="margin-top: 10px;text-align: center">-->
<#--                                    <button type="submit" class="btn btn-primary" style="width: 158px;display: inline-block">查询</button>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </form>-->
<#--                </div>-->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel-heading bg-primary">
                            <h6 class="panel-title">
                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">盘点结果汇总</a>
                                <a class="collapsed"  onclick="insertCheckTask()" style="float: right">
                                    <i class="icon-plus2"></i>
                                    发起盘点
                                </a>
                            </h6>
                        </div>
                        <div class="panel panel-flat">
                            <div class="table-responsive">
                                <table class="table fixed-table" >
                                    <thead>
                                    <tr class="border-bottom-danger">
                                        <th>盘点单号</th>
                                        <th>盘点种类</th>
                                        <th>现有库存货值</th>
                                        <th>差值</th>
                                        <th>发起时间</th>
                                        <th>完成时间</th>
                                        <th>负责人</th>
                                        <th>备注</th>
                                        <th>状态</th>
                                        <th>查看差异表</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <col style="width: 128px" />
                                    <col style="width: 20%" />
                                    <col style="width: 20%" />
                                    <col style="width: 20%" />
                                    <col style="width: 20%" />
                                    <col style="width: 20%" />
                                    <col style="width: 20%" />
                                    <col style="width: 20%" />
                                    <col style="width: 20%" />
                                    <col style="width: 20%" />
                                    <#if pageInfos??>
                                        <#list pageInfos.list as myn>
                                            <tr class="border-top-primary">
                                                <td>
                                                    ${myn.orderNumber!}
                                                </td>
                                                <td>
                                                    ${myn.goName!}
                                                </td>
                                                <td>
                                                    ${myn.totalAmount!}
                                                </td>
                                                <td>
                                                    ${myn.totalDiffAmount!}
                                                </td>
                                                <td>
                                                    ${(myn.createTime!?string("yyyy-MM-dd HH:mm:ss"))?replace(" ","<br>")}
                                                </td>
                                                </td>
                                                <td>
                                                    <#if myn.finishTime??>
                                                        ${(myn.finishTime!?string("yyyy-MM-dd HH:mm:ss"))?replace(" ","<br>")}
                                                    </#if>
                                                </td>
                                                <td>
                                                    ${myn.adminName!}
                                                </td>
                                                <td>
                                                    ${myn.remark!}
                                                </td>
                                                <td>
                                                    <#if myn.state==1>
                                                        未处理
                                                    </#if>
                                                    <#if myn.state==2>
                                                        待确认
                                                    </#if>
                                                    <#if myn.state==3>
                                                        已完成
                                                    </#if>
                                                </td>
                                                <td>
                                                    <div onclick="showDiffInfo('${myn.id!}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                                        <i class="icon-eye" id="eye${myn.id!}" onmousemove="layerTips('查看差异表','eye${myn.id!}')"></i>
                                                    </div>
                                                </td>
                                            </tr>
                                        </#list>
                                    </#if>
                                    <tr class="border-top-primary">
                                        <td colspan="15">
                                            <#import "../common/pagebar.ftl" as pagebar>
                                            <@pagebar.pagebar pageInfo=pageInfos actionUrl="/admin/goodsInfo/stockIndex"/>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
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
<script>
    function insertCheckTask(){
        $.ajax({
            url:"/admin/goodsCheckTask/insert",
            data:{
            },
            headers: {
                'httpType': "HTML",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    anim: 1,
                    shadeClose: true,
                    content: data,
                    area:["430px"]
                });
            }
        })
    }
    function showDiffInfo(id){
        $.ajax({
            url:"/admin/goodsCheckTask/diffInfo",
            data:{
                id:id
            },
            headers: {
                'httpType': "HTML",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    anim: 1,
                    shadeClose: true,
                    content: data,
                    area:["1110px","572px"]
                });
            }
        })
    }
</script>
</html>



