

<div class="content">
    <div class="panel-heading bg-primary">
        <h6 class="panel-title">
            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">${sqlRoleInfo.name!} 权限信息</a>
        </h6>
    </div>
    <div class="panel panel-flat">
        <div class="table-responsive">
            <table class="table">
                <tbody>
                <#if menuOneInfos??>
                    <#list menuOneInfos as myn>
                        <tr class="border-top-primary">
                            <td colspan="2">
                                <span class="text-primary">${myn.name!}</span>
                                <#assign flag=0>
                                <#if roleToMenuList??>
                                    <#list roleToMenuList as myr>
                                        <#if myn.id==myr.miid>
                                            <#assign flag=1>
                                        </#if>
                                    </#list>
                                </#if>
                                <div id="deleteMenu${myn.id}" onclick="deleteMenuAjax('${myn.id!}','${id!}')" style="color:red;float: left;margin-left: 10px;<#if flag==0> display: none</#if>">
                                    <i class="icon-cross2" id="delete${myn.id!}" onmousemove="layerTips('删除权限','delete${myn.id!}')"></i>
                                </div>
                                <div id="addMenu${myn.id}" onclick="addMenuAjax('${myn.id!}','${id!}')" style="color:#2196f3;float: left;margin-left: 10px;<#if flag==1> display: none</#if>">
                                    <i class="icon-plus2" id="update${myn.id!}" onmousemove="layerTips('新增权限','update${myn.id!}')"></i>
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr class="border-top-primary">
                            <td></td>
                            <td >
                                <div class="row">
                                <#if myn.menuInfoList??>
                                    <#list myn.menuInfoList as mym>
                                        <div class="col-xs-3">
                                            <span class="text-success" >${mym.name!}</span>
                                            <#assign flag=0>
                                            <#if roleToMenuList??>
                                                <#list roleToMenuList as myr>
                                                    <#if mym.id==myr.miid>
                                                        <#assign flag=1>
                                                    </#if>
                                                </#list>
                                            </#if>
                                            <div id="deleteMenu${mym.id}" onclick="deleteMenuAjax('${mym.id!}','${id!}')" style="color:red;float: left;<#if flag==0> display: none</#if>">
                                                <i class="icon-cross2" id="deleteMenu${mym.id!}" onmousemove="layerTips('删除权限','deleteMenu${mym.id!}')"></i>
                                            </div>
                                            <div id="addMenu${mym.id}" onclick="addMenuAjax('${mym.id!}','${id!}')" style="color:#2196f3;float: left;<#if flag==1> display: none</#if>">
                                                <i class="icon-plus2" id="saveMenu${mym.id!}" onmousemove="layerTips('新增权限','saveMenu${mym.id!}')"></i>
                                            </div>
                                        </div>
                                    </#list>
                                </#if>
                                </div>
                            </td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
    <#include "../common/foot.ftl"/>
</div>

<script>
    function addMenuAjax(miid,riid) {
        layer.load(1);
        $.ajax({
            url:"/admin/roleInfo/addMenuAjax",
            data:{
                miid:miid,
                riid:riid
            },
            headers: {
                'httpType': "JSON",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.closeAll('loading');
                if(data.statusCode==200){
                    $('#addMenu'+miid).hide();
                    $('#deleteMenu'+miid).show();
                    layer.msg('添加权限成功');
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }else{
                    layer.msg('添加权限失败');
                }
            }
        })
    }

    function deleteMenuAjax(miid,riid) {
        $.ajax({
            url:"/admin/roleInfo/deleteMenuAjax",
            data:{
                miid:miid,
                riid:riid
            },
            headers: {
                'httpType': "JSON",
            },
            type:"get",
            async:true,
            success:function (data) {
                layer.closeAll('loading');
                layer.closeAll('loading');
                if(data.statusCode==200){
                    $('#addMenu'+miid).show();
                    $('#deleteMenu'+miid).hide();
                    layer.msg('删除权限成功');
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }else{
                    layer.msg('删除权限失败');
                }
            }
        })
    }
</script>
</html>



