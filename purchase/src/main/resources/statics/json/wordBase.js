
var layer;
//初始化layer
layui.use('layer', function(){
     layer = layui.layer;
});
function myUrlOpen(url){
    window.open(url);
}
function myUrlHref(url){
    window.location.href = url;
}
function deleteLayer(url) {
    layer.confirm('您确定要删除此信息？(Are you sure you want to delete this information?)', {
        btn: ['确定(yes)', '取消(no)']
    }, function(index, layero){
        layer.close(index)
        layer.load(2);
        window.location.href=url;
    }, function(index){
    });
}
function myImageOpen(obj) {
    event.stopPropagation();
    // layer.closeAll();
    // var imgUrl = new Image();
    // imgUrl.src = url;
    // var width,height;
    // imgUrl.onload = function() {
    //     width = 600;
    //     height = (600/imgUrl.width)*imgUrl.height;
    //     layer.open({
    //         type: 1,
    //         title: false,
    //         offset:['100px', (document.body.offsetWidth - 260 - width) / 2 + 'px'],
    //         closeBtn: 0,
    //         area: [width + 'px', height + 'px'],
    //         // skin: 'layui-layer-nobg', //没有背景色
    //         closeBtn: 1,
    //         resize: false,
    //         shade: false,
    //         content: '<img id=logo src="'+ url +'" style="width: '+ width + 'px;height: ' + height + 'px">'
    //         //content: url
    //     });
    //
    // }
    // var url = $(obj).data('href');
    var url = $(obj).attr("src");
    var thisimg = $(obj);
    var img = "<img src='" + url + "'  />";
    var setting = {
        type: 1,
        title: false,
        closeBtn: 1,
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,
        shade: 0.6, //遮罩透明度
        content: img
    }

    var windowH = $(window).height();
    var windowW = $(window).width();

    getImageWidth(url,function(w,h){
        //console.log("win:"+windowH+","+windowW);
        //console.log("img:"+h+","+w);
        // 调整图片大小
        if(w>windowW || h>windowH){
            if(w>windowW && h>windowH){
                w = thisimg .width()*3;
                h = thisimg .height()*3;
                setting.area = [w+"px",h+"px"];
            }else if(w>windowW){
                setting.area = [windowW+"px",windowW*0.5/w*h+"px"];
            }else{
                setting.area = [w+"px",windowH+"px"];
            }
        }else{
            setting.area = [w+"px",h+"px"];
        }
        // 设置layer
        layer.open(setting);
    });

}
function getImageWidth(url,callback) {
    var img = new Image();
    img.src = url;

    // 如果图片被缓存，则直接返回缓存数据
    if (img.complete) {
        callback(img.width, img.height);
    } else {
        // 完全加载完毕的事件
        img.onload = function () {
            callback(img.width, img.height);
        }
    }
}
//信息提示框
function layerTips(content,id) {
    $("#"+id).attr("title",content).css("cursor","pointer");
    // layer.tips("<font color='#03a9f4'>"+content+"</font>", '#'+id,{
    //     tips: [1, '#ffffff']
    // });musa
}


/**
 * 弹出加载层
 * @returns {boolean}
 */
function myLoad(){
    layer.load(2);
    return true;
}

function getIpLoginUserInfo(uid){
    $.ajax({
        url:"/admin/userInfo/loginIPAjax",
        data:{
            id:uid
        },
        type:"get",
        async:true,
        success:function (data) {
            $('#getIpLoginUserInfo').html(data);
        }
    })
}

function showImg(url) {
    var img = "<img src='" + url + "' />";
    layer.open({
        type: 1,
        shade: false,
        title: false, //不显示标题
        area: ['auto', 'auto'],
        area: [img.width + 'px', img.height + 'px'],
        content: img, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
        cancel: function () {
            //layer.msg('图片查看结束！', { time: 5000, icon: 6 });
        }
    });
}

function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}

