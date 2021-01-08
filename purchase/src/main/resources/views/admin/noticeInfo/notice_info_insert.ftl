<div class="content-wrapper" style="padding: 0px;">
    <div class="content" style="padding: 0px;">
        <form action="${basePath}/admin/noticeInfo/insertIng" method="post">
            <div class="col-md-12" style="padding: 0px;">
                <div class="panel" style="margin: 0px;">
                    <div class="panel-heading bg-primary">
                        <h6 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion-styled" href="#accordion-styled-group3">新增</a>
                        </h6>
                    </div>
                    <div id="accordion-styled-group3" class="panel-collapse">
                        <div class="panel-body">
                            <div class="row col-xs-12" style="margin-top: 10px;padding: 0px;">
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-3" style="padding: 0px;">标题</label>
                                    <label class="col-xs-9" style="padding: 0px;">
                                        <input type="text" name="title" class="form-control" placeholder="标题">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-3" style="padding: 0px;">内容</label>
                                    <label class="col-xs-9" style="padding: 0px;">
                                        <input  name="content" class="form-control" placeholder="内容">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;">
                                    <label class="col-xs-3">公告类型</label>
                                    <label class="col-xs-9">
                                        <select class="form-control" name="type" >
                                            <option  value="1">BOOS公告</option>
                                            <option  value="2">采购公告</option>
                                            <option  value="3">商户公告</option>
                                            <option  value="4">财务公告</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-3" style="padding: 0px;">公告结束时间</label>
                                    <label class="col-xs-9" style="padding: 0px;">
                                        <input type="text" name="endTime" class="layui-input" id="endTime">
                                    </label>
                                </div>
                                <div class="col-xs-12" style="margin-top: 20px;padding: 0px;">
                                    <label class="col-xs-12" style="padding: 0px;"><button type="submit" class="btn btn-primary" style="width: 100%;">新增</button></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#endTime' //指定元素
        });
    });
</script>


