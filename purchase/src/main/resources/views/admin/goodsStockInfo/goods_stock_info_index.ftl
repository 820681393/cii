
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
        #goToOrder{
            position: fixed;
            right: 0px;
            bottom: 22px;
            background: #2196f3;
            color: #fff;
            padding: 13px 10px;
            border-top-left-radius: 8px;
            border-bottom-left-radius: 8px;
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
                    <form action="/admin/goodsInfo/stockIndex" method="post">
                        <div id="accordion-styled-group3" class="panel-collapse">
                            <div class="panel-body">
                                <div class="row" style="margin-top: 10px;">
                                    <div class="col-xs-3">
                                        <input type="text"  name="chName" value="${goodsInfo.chName!}"class="form-control" placeholder="中文名称">
                                    </div>
                                    <div class="col-xs-3">
                                        <input type="text"  name="enName" value="${goodsInfo.enName!}"class="form-control" placeholder="英文名称">
                                    </div>
                                    <div class="col-xs-3">
                                        <select class="form-control" name="goid">
                                            <option value="">一级分类</option>
                                            <#if goodsOneTypeList??>
                                                <#list goodsOneTypeList as myn>
                                                    <option value="${myn.id!}" <#if goodsInfo.goid??&&myn.id==goodsInfo.goid>selected</#if>>${myn.name!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>
                                    <#--                                    <div class="col-xs-3">-->
                                    <#--                                        <select class="form-control" name="gtid">-->
                                    <#--                                            <option value="">二级分类</option>-->
                                    <#--                                            <option value="-1">无</option>-->
                                    <#--                                            <#if goodsTowTypeList??>-->
                                    <#--                                                <#list goodsTowTypeList as myn>-->
                                    <#--                                                    <option value="${myn.id!}" <#if goodsInfo.gtid??&&myn.id==goodsInfo.gtid>selected</#if>>${myn.name!}</option>-->
                                    <#--                                                </#list>-->
                                    <#--                                            </#if>-->
                                    <#--                                        </select>-->
                                    <#--                                    </div>-->
                                </div>
                                <#--                                <div class="row" style="margin-top: 10px;">-->
                                <#--                                    <div class="col-xs-3">-->
                                <#--                                        <select class="form-control" name="siid">-->
                                <#--                                            <option value="">供应商</option>-->
                                <#--                                            <#if supplierInfoList??>-->
                                <#--                                                <#list supplierInfoList as myn>-->
                                <#--                                                    <option value="${myn.id!}" <#if goodsInfo.siid??&&myn.id==goodsInfo.siid>selected</#if>>${myn.name!}</option>-->
                                <#--                                                </#list>-->
                                <#--                                            </#if>-->
                                <#--                                        </select>-->
                                <#--                                    </div>-->
                                <#--                                </div>-->
                                <div class="row" style="margin-top: 10px;text-align: center">
                                    <button type="submit" class="btn btn-primary" style="width: 158px;display: inline-block">查询</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">商品库存信息列表</a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <table class="table fixed-table" >
                            <thead>
                            <tr class="border-bottom-danger">
                                <th>商品名称</th>
                                <th>一级分类</th>
                                <th>主单位库存</th>
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
                                        <td>
                                            <#if myn.safeStock??>
                                                <#if myn.stockSe lt myn.safeStock>
                                                    <span style="color: red"><i class="icon-alert"></i>${myn.safeStock!}</span>
                                                    <#else >
                                                        ${myn.safeStock!}
                                                </#if>
                                            </#if>
                                            <a class="collapsed" onclick="addToOrder('${myn.id!}')" style="color: #2196f3;">
                                                <i class="icon-plus2"></i>补货
                                            </a>
                                        </td>
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
                                    <@pagebar.pagebar pageInfo=pageInfos actionUrl="/admin/goodsInfo/stockIndex"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <a href="/admin/orderInfo/submitIndex" id="goToOrder">
                    <i class="icon-cart2"></i>
                    前往采购
                    <i class="icon-arrow-right15"></i>
                </a>
                <#include "../common/foot.ftl"/>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    var cacheOrder = localStorage.getItem("cacheOrder");
    var orderList = {};
    if(cacheOrder){
        localStorage.setItem("cacheOrder",JSON.stringify(orderList));
    }else{
        localStorage.setItem("cacheOrder",JSON.stringify(orderList));
    }

    function addToOrder(id){
        layer.msg("已加入采购列表");
        var readCacheOrder = JSON.parse(localStorage.getItem("cacheOrder"));
        readCacheOrder[id] = {"num":1,"unitType":2};
        localStorage.setItem("cacheOrder",JSON.stringify(readCacheOrder));
    }
</script>
</html>



