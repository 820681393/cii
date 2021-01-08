<div class="content">
                <div class="panel-heading bg-primary">
                    <h6 class="panel-title">
                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">${sqlSupplierInfo.name}供应商品信息</a>
                    </h6>
                </div>
                <div class="panel panel-flat">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr class="border-bottom-danger">
                                    <th>一级分类名称</th>
                                    <th>二级分类名称</th>
                                    <th>PISE价格</th>
                                    <th>RMB价格</th>
                                    <th>单位</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#if goodsOneTypeList??>
                                <#list goodsOneTypeList as myn>
                                    <#assign goodsOneyTypeFlag=0>
                                    <#if goodsToSupplierList??>
                                        <#list goodsToSupplierList as mygtsl>
                                            <#if mygtsl.gotid==myn.id>
                                                <#assign goodsOneyTypeFlag=1>
                                            </#if>
                                        </#list>
                                    </#if>
                                    <#if goodsOneyTypeFlag==1>
                                        <tr class="border-top-primary">
                                            <td><span class="text-primary">${myn.name!}（${myn.ename!}）</span></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <#if goodsTowTypeList??>
                                            <#list goodsTowTypeList as myt>
                                                <#if myn.id==myt.goid>
                                                    <#assign goodsTowTypeFlag=0>
                                                    <#if goodsToSupplierList??>
                                                        <#list goodsToSupplierList as mygtsl>
                                                            <#if mygtsl.gttid==myt.id>
                                                                <#assign goodsTowTypeFlag=1>
                                                            </#if>
                                                        </#list>
                                                    </#if>
                                                    <#if goodsTowTypeFlag==1>
                                                        <tr class="border-top-primary">
                                                            <td></td>
                                                            <td><span class="text-success">${myt.name!}（${myt.ename!}）</span></td>
                                                            <#assign flag=0>
                                                            <#if goodsToSupplierList??>
                                                                <#list goodsToSupplierList as mys>
                                                                    <#if myt.id==mys.gttid>
                                                                        <td>${mys.price!}PISE</td>
                                                                        <td>${mys.price!/exchangeRate!}RMB</td>
                                                                        <td>${mys.unit!}</td>
                                                                        <#assign flag=1>
                                                                    </#if>
                                                                </#list>
                                                            </#if>
                                                            <#if flag==0>
                                                                <td></td>
                                                                <td></td>
                                                                <td></td>
                                                            </#if>
                                                        </tr>
                                                    </#if>
                                                </#if>
                                            </#list>
                                        </#if>
                                    </#if>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
</html>



