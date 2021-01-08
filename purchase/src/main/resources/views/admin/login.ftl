
<!DOCTYPE html>
<html lang="en">
<head>
<#assign title="用户登陆">
<#include "common/head.ftl"/>

</head>

<body>

<!-- Page container -->
<div class="page-container login-container">

    <!-- Page content -->
    <div class="page-content" style="background-image: url('/statics/background.jpg');background-repeat: inherit;background-size: cover">

        <!-- Main content -->
        <div class="content-wrapper">

            <!-- Content area -->
            <div class="content">

                <!-- Advanced login -->
                <form action="/admin/login/loginIng" method="POST" enctype="multipart/form-data">
                    <div class="panel panel-body login-form">
                        <div class="text-center">
                            <div class="icon-object border-slate-300 text-slate-300">
                                <img src="/statics/logo.png" style="width: 78px;height: 78px;">
                            </div>
                            <h5 class="content-group">登陆你的账号</h5>
                        </div>

                        <div class="form-group has-feedback has-feedback-left">
                            <input type="text" name="userName" value="" class="form-control" placeholder="用户名">
                            <div class="form-control-feedback">
                                <i class="icon-user text-muted"></i>
                            </div>
                        </div>

                        <div class="form-group has-feedback has-feedback-left">
                            <input type="password" name="passWord" value="" class="form-control" placeholder="密码">
                            <div class="form-control-feedback">
                                <i class="icon-lock2 text-muted"></i>
                            </div>
                        </div>
                        <div class="form-group has-feedback has-feedback-left">
                            <select class="form-control" name="language" >
                                <option  value="cn">中文</option>
                                <option  value="en">English</option>
                            </select>
                            <div class="form-control-feedback">
                                <i class="icon-menu2"></i>
                            </div>
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn bg-blue btn-block">登陆<i class="icon-arrow-right14 position-right"></i></button>
                        </div>
                    </div>
                </form>
                <!-- /advanced login -->
            </div>
            <!-- /content area -->
        </div>
        <!-- /main content -->
    </div>
    <!-- /page content -->
</div>
<!-- /page container -->

</body>
<script>
    window.onload = function(){
        console.log(123456);
        <#if language??>
            <#if language=="en">
                <#if languageInfoSessionList??>
                    <#list languageInfoSessionList as myl>
                        document.body.innerHTML = document.body.innerHTML.replace(/${myl.cn!}/g, "${myl.en!}");
                    </#list>
                </#if>
            </#if>
        </#if>

    }
</script>
</html>
