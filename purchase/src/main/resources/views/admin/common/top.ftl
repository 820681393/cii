
<!-- Main navbar -->
<div class="navbar navbar-default header-highlight">
    <div class="navbar-header">
        <a class="navbar-brand"><img src="${basePath}/statics/admin/assets/images/logo_light.png" alt=""></a>
        <ul class="nav navbar-nav visible-xs-block">
            <li><a data-toggle="collapse" data-target="#navbar-mobile"><i class="icon-tree5"></i></a></li>
            <li><a class="sidebar-mobile-main-toggle"><i class="icon-paragraph-justify3"></i></a></li>

        </ul>
    </div>

    <div class="navbar-collapse collapse" id="navbar-mobile">
        <ul class="nav navbar-nav">
            <li><a class="sidebar-control sidebar-main-toggle hidden-xs"><i class="icon-paragraph-justify3"></i></a></li>
            <li><a ><span style="color: red">今日日期：${dateTime?string("yyyy-MM-dd")}&nbsp;周${week!}</span></a></li>
            <li><a >
                    <span style="color: red">限行车辆：&nbsp;&nbsp;
                        <#if carInfoNewList?? && carInfoNewList?size gt 0>
                            <#list carInfoNewList as myc>
                                ${myc.name!}&nbsp;&nbsp;
                            </#list>
                        <#else>
                            暂无限行车辆
                        </#if>
                    </span>
                </a>
            </li>
            <li style="float: right" onclick="myUrlHref('/admin/login/out')"><a ><span style="color: red">退出登录</span></a></li>
            <li style="float: right" ><a  ><span style="color: red">登录用户：${userAdmin.userName!}</span></a></li>
        </ul>
    </div>
</div>
<!-- /main navbar -->