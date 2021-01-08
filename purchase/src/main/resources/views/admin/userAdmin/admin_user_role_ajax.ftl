<div class="content">
    <div class="panel-heading bg-primary">
        <h6 class="panel-title">
            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">${sqlAdminInfo.nikeName!} 权限信息</a>
        </h6>
    </div>
    <div class="panel panel-flat">
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr class="border-bottom-danger">
                    <th>角色名称</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#if roleInfoList??>
                    <#list roleInfoList as myn>
                        <tr class="border-top-primary">
                            <td>${myn.name!}</td>
                            <td>${myn.createTime!?string("yyyy-MM-dd HH:mm:ss")}</td>
                            <td>
                                <div onclick="getEenuAjax('${myn.id!}')" style="color:#2196f3;float: left;margin-left: 10px;">
                                    <i class="icon-eye" id="eye${myn.id!}" onmousemove="layerTips('查看权限','eye${myn.id!}')"></i>
                                </div>
                                <#assign flag=0>
                                <#if adminToRoleList??>
                                    <#list adminToRoleList as myr>
                                        <#if myn.id==myr.riid>
                                            <#assign flag=1>
                                        </#if>
                                    </#list>
                                </#if>
                                <div id="deleteRole${myn.id}" onclick="deleteRoleAjax('${id!}','${myn.id!}')" style="color:red;float: left;margin-left: 10px;<#if flag==0> display: none</#if>">
                                    <i class="icon-cross2" id="deleteRole${myn.id!}" onmousemove="layerTips('删除角色','deleteRole${myn.id!}')"></i>
                                </div>
                                <div id="addRole${myn.id}" onclick="addRoleAjax('${id!}','${myn.id!}')" style="color:#2196f3;float: left;margin-left: 10px;<#if flag==1> display: none</#if>">
                                    <i class="icon-plus2" id="saveRole${myn.id!}" onmousemove="layerTips('新增角色','saveRole${myn.id!}')"></i>
                                </div>
                            </td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div style="margin-top: 30px" id="menuAjax"></div>
<script>
    function addRoleAjax(aiid,riid) {
        layer.load(1);
        $.ajax({
            url:"/admin/adminUser/addRoleAjax",
            data:{
                aiid:aiid,
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
                    $('#addRole'+riid).hide();
                    $('#deleteRole'+riid).show();
                    layer.msg('添加成功');
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }else{
                    layer.msg('添加失败');
                }
            }
        })
    }

    function deleteRoleAjax(aiid,riid) {
        $.ajax({
            url:"/admin/adminUser/deleteRoleAjax",
            data:{
                aiid:aiid,
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
                    $('#addRole'+riid).show();
                    $('#deleteRole'+riid).hide();
                    layer.msg('删除成功');
                }else if(data.statusCode==203){
                    layer.alert("权限不足")
                }else{
                    layer.msg('删除失败');
                }
            }
        })
    }

    function getEenuAjax(id) {
        $.ajax({
            url:"/admin/roleInfo/getEenuAjax",
            data:{
                id:id
            },
            headers: {
                'httpType': "HTML",
            },
            type:"get",
            async:true,
            success:function (data) {
                $('#menuAjax').html(data);
            }
        })
    }
</script>