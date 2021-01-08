
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
                    <form action="/admin/goodsInfo/index" method="post">
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
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">商品信息列表</a>
                        <a class="collapsed"  onclick="location.href='/admin/goodsInfo/insert'" style="float: right">
                            <i class="icon-plus2"></i>
                            新增商品
                        </a>
                        <a class="collapsed"  onclick="location.href='/admin/goodsInfo/getGoodsInfoExcel'" style="float: right">
                            <i class="icon-file-download"></i>
                            导出
                        </a>
                        <a class="collapsed"  onclick="location.href='/admin/goodsInfo/getGoodsInfoExcelTemple'" style="float: right">
                            <i class="icon-file-download"></i>
                            导出模板
                        </a>
                        <a class="collapsed"  onclick="javascript:importGoodsInfoExcel();" style="float: right">
                            <i class="icon-file-plus"></i>
                            导入
                        </a>
                        <input id="file" name="file" type="file" style="float: right;width: 165px;font-size: 13px;"/>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
<#--                        <div style="overflow-x: auto;   width:2000px;">-->
                            <table class="table fixed-table" >
                                <thead>
                                <tr class="border-bottom-danger">
                                    <th>商品名称</th>
                                    <th>一级分类</th>
                                    <th>采购成本</th>
                                    <th>批发上下架</th>
                                    <th>批发价格</th>
                                    <th>零售上下架</th>
                                    <th>零售价格</th>
                                    <th>供应商</th>
                                    <th>时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <col style="width: 132px;" />
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
                                                <#if myn.unitType??>
                                                    <#if myn.unitType==1>
                                                        ${myn.price!}
                                                    <#else >
                                                        ${myn.priceSe!}
                                                    </#if>
                                                </#if>
                                            </td>
<#--                                            <td>-->
<#--                                                <#if myn.unitType??>-->
<#--                                                    <#if myn.unitType==1>-->
<#--                                                        ${myn.unitPrName!}-->
<#--                                                        <select class="unit-type-sel" data-id="${myn.id}" style="float: right;">-->
<#--                                                            <option value="1" selected="selected">主</option>-->
<#--                                                            <option value="2">辅</option>-->
<#--                                                        </select>-->
<#--                                                    <#else >-->
<#--                                                        ${myn.unitPeName!}-->
<#--                                                        <select class="unit-type-sel" data-id="${myn.id}" style="float: right;">-->
<#--                                                            <option value="1">主</option>-->
<#--                                                            <option value="2" selected="selected">辅</option>-->
<#--                                                        </select>-->
<#--                                                    </#if>-->
<#--                                                </#if>-->
<#--                                            </td>-->
                                            <td>
                                                <input data-id="${myn.id}" type="checkbox" class="state" name="state" <#if myn.state==1>checked</#if>/>
                                            </td>
                                            <td>
                                                <#if myn.unitType??>
                                                    <#if myn.unitType==1>
                                                        ${myn.tradePrice!}
                                                    <#else >
                                                        ${myn.tradePriceSe!}
                                                    </#if>
                                                </#if>
                                            </td>
                                            <td>
                                                <input data-id="${myn.id}" type="checkbox" class="retailState" name="retailState" <#if myn.retailState==1>checked</#if>/>
                                            </td>
                                            <td>
                                                <#if myn.unitType??>
                                                    <#if myn.unitType==1>
                                                        ${myn.retailPrice!}
                                                    <#else >
                                                        ${myn.retailPriceSe!}
                                                    </#if>
                                                </#if>
                                            </td>
                                            <td>
                                                <a href="javascript:openSupplierList('${myn.id}','${myn.siid!}');">
                                                    <#if myn.supplierName??>
                                                        ${myn.supplierName!}
                                                    <#else >
                                                        选择供应商
                                                    </#if>
                                                </a>
                                            </td>
                                            <td>${(myn.createTime!?string("yyyy-MM-dd HH:mm:ss"))?replace(" ","<br>")}</td>
                                            <td>
                                                <div onclick="location.href='/admin/goodsInfo/update?id=${myn.id}'" style="color:#2196f3;float: left;margin-left: 10px;">
                                                    <i class="icon-pencil3" id="update${myn.id!}" onmousemove="layerTips('修改','update${myn.id!}')"></i>
                                                </div>
                                                <div onclick="deleteLayer('${basePath}/admin/goodsInfo/delete?id=${myn.id!}')" style="color:red;float: left;margin-left: 10px;">
                                                    <i class="icon-cross2" id="delete${myn.id!}" onmousemove="layerTips('删除','delete${myn.id!}')"></i>
                                                </div>
                                            </td>
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

    function importGoodsInfoExcel(){
        var file = $("#file");
        if(!file.val()){
            layer.msg("请选择导入文件");
            return;
        }
        var formData = new FormData();
        formData.append('file', file[0].files[0]);
        $.ajax({
            url:"/admin/goodsInfo/importGoodsInfoExcel",
            type:"post",
            headers: {
                'httpType': "JSON",
            },
            data: formData,
            contentType: false,
            processData: false,
            async:false,
            success: function(data) {
                console.log(data);
                if(data.statusCode==200){
                    layer.alert("导入成功");
                    window.location.href = "/admin/goodsInfo/index";
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }else{
                    layer.alert("导入失败")
                }
            },
            error:function(data) {
                layer.alert("导入失败")
            }
        });
    }

    function openSupplierList(id,siid){
        $.ajax({
            url:"/admin/goodsInfo/selSupplier",
            data:{
                supplierId:siid,
                goodsId:id
            },
            type:"get",
            async:true,
            headers: {
                'httpType': "HTML",
            },
            success:function (data) {
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    anim: 1,
                    shadeClose: true,
                    content: data,
                    area:  ['800px']   // 长，宽
                });
            }
        })
    }

    $(".unit-type-sel").change(function(){
       var thisDom = $(this);
       var dataId = thisDom.attr("data-id");
       var unitType = thisDom.val();
        $.ajax({
            url:"/admin/goodsInfo/updateGoodsInfo",
            data:{
                id:dataId,
                unitType:unitType
            },
            headers: {
                'httpType': "JSON",
            },
            type:"post",
            async:false,
            success:function (data) {
                if(data.statusCode==200){
                    window.location.href = "/admin/goodsInfo/index";
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }
            }
        })
    });

    $(".state").click(function(){
        var thisDom = $(this);
        var dataId = thisDom.attr("data-id");
        var state = 0;
        if(this.checked){
            state = 1;
        }
        $.ajax({
            url:"/admin/goodsInfo/updateGoodsInfo",
            data:{
                id:dataId,
                state:state
            },
            headers: {
                'httpType': "JSON",
            },
            type:"post",
            async:false,
            success:function (data) {
                if(data.statusCode==200){
                    // window.location.href = "/admin/goodsInfo/index";
                }else if(data.statusCode==203){
                    // layer.alert("权限不足")
                }
            }
        })
    });

    $(".retailState").click(function(){
        var thisDom = $(this);
        var dataId = thisDom.attr("data-id");
        var retailState = 0;
        if(this.checked){
            retailState = 1;
        }
        $.ajax({
            url:"/admin/goodsInfo/updateGoodsInfo",
            data:{
                id:dataId,
                retailState:retailState
            },
            headers: {
                'httpType': "JSON",
            },
            type:"post",
            async:false,
            success:function (data) {
                if(data.statusCode==200){
                    // window.location.href = "/admin/goodsInfo/index";
                }else if(data.statusCode==203){
                    // layer.alert("权限不足")
                }
            }
        })
    });
</script>
</html>



