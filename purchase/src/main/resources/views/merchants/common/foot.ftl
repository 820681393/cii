
<div class="footer text-muted">
    &copy; 2020-11-03 <a href="#">商户管理后台</a>
</div>
<script>
    window.onload = function(){
        <#if language=="en">
            <#if languageInfoSessionList??>
                <#list languageInfoSessionList as myl>
                    document.body.innerHTML = document.body.innerHTML.replace(/${myl.cn!}/g, "${myl.en!}");
                </#list>
            </#if>
        </#if>
    }
</script>