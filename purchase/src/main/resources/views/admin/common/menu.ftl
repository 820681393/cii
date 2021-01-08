
<!-- Main sidebar -->
<div class="sidebar sidebar-main">
    <div class="sidebar-content">
        <!-- Main navigation -->
        <div class="sidebar-category sidebar-category-visible">
            <div class="category-content no-padding">
                <ul class="navigation navigation-main navigation-accordion">
                    <!-- Main -->
                    <li <#if menuType=="home">class="active"</#if>><a href="${basePath}/admin/index/index"><i class="icon-home4"></i> <span>系统公告</span></a></li>
                    <#if menuInfos??>
                        <#list menuInfos as mym>
                            <li  <#if menuType=="${mym.type!}">class="active"</#if> >
                                <a href="#"><i class="icon-stack2"></i> <span>${mym.name!}</span></a>
                                <ul>
                                    <#list mym.menuInfoList as myi>
                                        <li url="${basePath}${myi.url!}"><a href="${basePath}${myi.url!}">${myi.name!}</a></li>
                                    </#list>
                                </ul>
                            </li>
                        </#list>
                    </#if>

<#--                    <li  <#if menuType=="roleInfo">class="active"</#if> >-->
<#--                        <a href="#"><i class="icon-stack2"></i> <span>岗位权限设置</span></a>-->
<#--                        <ul>-->
<#--                            <li><a href="${basePath}/admin/roleInfo/index">岗位管理列表</a></li>-->
<#--                        </ul>-->
<#--                    </li>-->
<#--                    <li  <#if menuType=="menuInfo">class="active"</#if> >-->
<#--                        <a href="#"><i class="icon-stack2"></i> <span>系统功能管理</span></a>-->
<#--                        <ul>-->
<#--                            <li><a href="${basePath}/admin/menuInfo/index">功能菜单列表</a></li>-->
<#--                        </ul>-->
<#--                    </li>-->
<#--                    <li  <#if menuType=="carInfo">class="active"</#if> >-->
<#--                        <a href="#"><i class="icon-stack2"></i> <span>车辆管理</span></a>-->
<#--                        <ul>-->
<#--                            <li><a href="${basePath}/admin/carInfo/index">车辆列表</a></li>-->
<#--                        </ul>-->
<#--                    </li>-->
<#--                    <li  <#if menuType=="supplierInfo">class="active"</#if> >-->
<#--                        <a href="#"><i class="icon-stack2"></i> <span>供应商管理</span></a>-->
<#--                        <ul>-->
<#--                            <li><a href="${basePath}/admin/supplierInfo/index">供应商库管理</a></li>-->
<#--                        </ul>-->
<#--                    </li>-->
<#--                    <li  <#if menuType=="goodsType">class="active"</#if> >-->
<#--                        <a href="#"><i class="icon-stack2"></i> <span>商品层级管理</span></a>-->
<#--                        <ul>-->
<#--                            <li><a href="${basePath}/admin/goodsOneType/index">商品分级设置</a></li>-->
<#--                        </ul>-->
<#--                    </li>-->
<#--                    <li  <#if menuType=="goodsInfo">class="active"</#if> >-->
<#--                        <a href="#"><i class="icon-stack2"></i> <span>商品信息管理</span></a>-->
<#--                        <ul>-->
<#--                            <li><a href="${basePath}/admin/goodsInfo/index">商品信息列表</a></li>-->
<#--                            <li><a href="${basePath}/admin/unitInfo/index">商品单位列表</a></li>-->
<#--                        </ul>-->
<#--                    </li>-->
<#--                    <li  <#if menuType=="orderInfo">class="active"</#if> >-->
<#--                        <a href="#"><i class="icon-stack2"></i> <span>订单信息管理</span></a>-->
<#--                        <ul>-->
<#--                            <li><a href="${basePath}/admin/orderInfo/submitIndex">采购下单</a></li>-->
<#--                            <li><a href="${basePath}/admin/orderInfo/index?orderState=0">订单列表</a></li>-->
<#--                            <li><a href="${basePath}/admin/orderInfo/todayOrderInfo">当日采购单</a></li>-->
<#--                            <li><a href="${basePath}/admin/orderInfo/index?orderState=1">采购中订单</a></li>-->
<#--                            <li><a href="${basePath}/admin/orderInfo/index?orderState=2">回执中订单</a></li>-->
<#--                        </ul>-->
<#--                    </li>-->
<#--                    <li  <#if menuType=="report">class="active"</#if> >-->
<#--                        <a href="#"><i class="icon-stack2"></i> <span>月终报表查看</span></a>-->
<#--                        <ul>-->
<#--                            <li><a href="${basePath}/admin/report/orderInfoReport">采购单报表</a></li>-->
<#--                            <li><a href="${basePath}/admin/report/goodsTypeSumReport">商品分类报表</a></li>-->
<#--                            <li><a href="${basePath}/admin/report/goodsPriceTrend">商品价格走势</a></li>-->
<#--                            <li><a href="${basePath}/admin/report/supplierPriceTrend">供应商采购汇总</a></li>-->
<#--                        </ul>-->
<#--                    </li>-->
<#--                    <li  <#if menuType=="languageInfo">class="active"</#if> >-->
<#--                        <a href="#"><i class="icon-stack2"></i> <span>语言管理</span></a>-->
<#--                        <ul>-->
<#--                            <li><a href="${basePath}/admin/languageInfo/index">语言信息列表</a></li>-->
<#--                        </ul>-->
<#--                    </li>-->
<#--                    <li  <#if menuType=="merchantOrderInfo">class="active"</#if> >-->
<#--                        <a href="#"><i class="icon-stack2"></i> <span>商户管理</span></a>-->
<#--                        <ul>-->
<#--                            <li><a href="${basePath}/merchants/orderInfo/index">商户订单列表</a></li>-->
<#--                        </ul>-->
<#--                    </li>-->
                </ul>
            </div>
        </div>

    </div>
</div>
<script>
    var curUrl = window.location.href;
    $("li[url='"+curUrl+"']").addClass("active");
</script>