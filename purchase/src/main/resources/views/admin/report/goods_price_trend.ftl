
<!DOCTYPE html>
<html lang="en">
<head>
    <#assign title="商品价格走势">
    <#include "../common/head.ftl"/>
    <script src="https://code.highcharts.com.cn/highcharts/highcharts.js"></script>
    <script src="https://code.highcharts.com.cn/highcharts/modules/exporting.js"></script>
    <script src="https://code.highcharts.com.cn/highcharts/modules/oldie.js"></script>
    <script src="https://code.highcharts.com.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
</head>
<body>
<#include "../common/top.ftl"/>
<div class="page-container">

    <div class="page-content">
        <#assign menuType="report">
        <#include "../common/menu.ftl"/>
        <div class="content-wrapper">
            <div class="content">
                <div id="container" style="height:400px"></div>
                <#include "../common/foot.ftl"/>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    //执行一个laydate实例
    var laydate = layui.laydate;
    laydate.render({
        elem: '#create-time-start' //指定元素
    });
    laydate.render({
        elem: '#create-time-end' //指定元素
    });
    $.ajax({
        url:"/admin/report/goodsPriceTrendData",
        data:{
            // id:dataId,
            // unitType:unitType
        },
        headers: {
            'httpType': "JSON",
        },
        type:"post",
        async:false,
        success:function (data) {
            if(data.statusCode==200){
                var xtext = data.data.xtext;
                var seriesData = data.data.seriesDataArr;
                var chart = new Highcharts.Chart({
                    chart: {
                        renderTo: 'container',
                        type: 'spline'//选择不同的图表，只需更改具体的值就可以了。详情可以参考：https://www.runoob.com/highcharts/highcharts-setting-detail.html
                    },
                    title: {
                        text: ''
                    },
                    xAxis: {
                        categories: xtext
                    },
                    yAxis: {
                        min:0,
                        title: {
                            text: '价格'
                        },
                    },
                    series: seriesData
                });
            }else if(data.statusCode==203){
                layer.alert("权限不足")
            }
        }
    })
</script>
</html>



