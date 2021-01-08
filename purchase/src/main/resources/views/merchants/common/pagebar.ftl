<#macro pagebar pageInfo actionUrl>
<ul class="pagination pagination-separated pagination-rounded" style="float: right;margin-bottom: 10px;">
    <li ><a  onclick="gpcPagebarSearch(1)">首页</a></li>
    <li ><a  onclick="gpcPagebarSearch(${pageInfo.pageNum -1})">上一页</a></li>
    <li><a  >【${pageInfo.pageNum}/${pageInfo.pages}】</a></li>
    <li ><a  onclick="gpcPagebarSearch(${pageInfo.pageNum +1})">下一页</a></li>
    <li ><a  onclick="gpcPagebarSearch(${pageInfo.pages})">尾页</a></li>
</ul>
<form id="gpcPagebarForm" style="display:none;" action="${actionUrl}" method="POST">
    <input type="hidden" name="pageNum" id="pageBarPageNum" value="${pageInfo.pageNum}"/>
    <input type="hidden" name="pageSize" id="pageBarPageSize"  value="${pageInfo.pageSize}"/>
    <#list RequestParameters?keys as key>
        <input type="hidden" name="${key}" id="pageBarPageSize"  value="${RequestParameters[key]!}"/>
    </#list>
</form>
<script type="text/javascript">
    function gpcPagebarSearch(pageBarPageNum) {
        if(pageBarPageNum < 1 || pageBarPageNum == null) {
            pageBarPageNum = 1;
        }
        document.getElementById("pageBarPageNum").value = pageBarPageNum;
        document.getElementById("gpcPagebarForm").submit();
    }
</script>
</#macro>