
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="商品信息">
    <#include "../common/head.ftl"/>
    <style>
        .table-responsive .table img{
            max-width: 38px;
            /*border-radius: 50%;*/
            margin-right: 5px;
            cursor: pointer;
        }
        .panel-title a{
            margin-left: 15px;
        }
        .state{
            cursor: pointer;
        }
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
                <div class="panel">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">查询条件</a>
                        </h6>
                    </div>
<#--                    <form action="/admin/goodsInfo/index" method="post">-->
<#--                        <div id="accordion-styled-group3" class="panel-collapse">-->
<#--                            <div class="panel-body">-->
<#--                                <div class="row" style="margin-top: 10px;">-->
<#--                                    <div class="col-xs-3">-->
<#--                                        <select class="form-control" name="type">-->
<#--                                            <option value="">一级分类</option>-->
<#--                                        </select>-->
<#--                                    </div>-->
<#--                                </div>-->
<#--                                <div class="row" style="margin-top: 10px;text-align: center">-->
<#--                                    <button type="submit" class="btn btn-primary" style="width: 158px;display: inline-block">查询</button>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </form>-->
                </div>
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">商品库存信息列表</a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <#--                        <div style="overflow-x: auto;   width:2000px;">-->
                        <table class="table fixed-table" >
                            <thead>
                            <tr class="border-bottom-danger">
                                <th>单号</th>
                                <th>数量</th>
                                <th>类型</th>
                                <th>辅单位库存</th>
                                <th>安全库存</th>
                                <th>供应商</th>
                                <th>时间</th>
<#--                                <th>操作</th>-->
                            </tr>
                            </thead>
                            <tbody>
<#--                            <col style="width: 132px;" />-->
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
                                            <#if myn.imgUrl??>
                                                <img onclick="myImageOpen(this)" width="50px" src="${aliyunOos!}${myn.imgUrl!}"/>
                                            <#else >
                                                <img style="cursor: default" width="50px" src="/statics/admin/assets/images/default_goods.jpg"/>
                                            </#if>
                                            <br/>
                                            ${myn.chName!}
                                            <br/>
                                            ${myn.enName!}
                                        </td>
                                        <td>${myn.goName!}</td>
                                        <td>
                                            ${myn.stock!}(${myn.unitPrName!})
                                        </td>
                                        <td>
                                            ${myn.stockSe!}(${myn.unitPeName!})
                                        </td>
                                        <td></td>
                                        <td>
                                            <#if myn.supplierName??>
                                                ${myn.supplierName!}
                                            <#else >
                                                无
                                            </#if>
                                        </td>
                                        <td>${(myn.createTime!?string("yyyy-MM-dd HH:mm:ss"))?replace(" ","<br>")}</td>
<#--                                        <td>-->
<#--                                            <div onclick="location.href='/admin/goodsInfo/update?id=${myn.id}'" style="color:#2196f3;float: left;margin-left: 10px;">-->
<#--                                                <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改','update${myn.id!}')"></i>-->
<#--                                            </div>-->
<#--                                            <div onclick="deleteLayer('${basePath}/admin/goodsInfo/delete?id=${myn.id!}')" style="color:red;float: left;margin-left: 10px;">-->
<#--                                                <i class="icon-cross2" id="delete${myn.id!}" onmousemove="layerTips('删除','delete${myn.id!}')"></i>-->
<#--                                            </div>-->
<#--                                        </td>-->
                                    </tr>
                                </#list>
                            </#if>
                            <tr class="border-top-primary">
                                <td colspan="15">
                                    <#import "../common/pagebar.ftl" as pagebar>
                                    <@pagebar.pagebar pageInfo=pageInfos actionUrl="/admin/goodsInfo/index"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <#--                        </div>-->
                    </div>
                </div>
                <#include "../common/foot.ftl"/>
            </div>
        </div>
    </div>
</div>
</body>
<script>

</script>
</html>



