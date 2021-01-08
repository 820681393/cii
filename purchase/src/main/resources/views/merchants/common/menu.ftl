
<!-- Main sidebar -->
<div class="sidebar sidebar-main">
    <div class="sidebar-content">
        <!-- Main navigation -->
        <div class="sidebar-category sidebar-category-visible">
            <div class="category-content no-padding">
                <ul class="navigation navigation-main navigation-accordion">
                    <!-- Main -->
                    <li <#if menuType=="home">class="active"</#if>><a href="${basePath}/merchants/index/index"><i class="icon-home4"></i> <span>系统公告</span></a></li>
                    <#if menuInfos??>
                        <#list menuInfos as mym>
                            <li  <#if menuType=="${mym.type!}">class="active"</#if> >
                                <a href="#"><i class="icon-stack2"></i> <span>${mym.name!}</span></a>
                                <ul>
                                    <#list mym.menuInfoList as myi>
                                        <li><a href="${basePath}${myi.url!}">${myi.name!}</a></li>
                                    </#list>
                                </ul>
                            </li>
                        </#list>
                    </#if>
                </ul>
            </div>
        </div>

    </div>
</div>