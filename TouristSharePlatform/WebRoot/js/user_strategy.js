$(function(){//jQuery入口函数
	var scrtime;

    var $ul = $("#con ul");
    var liFirstHeight = $ul.find("li:first").height();//第一个li的高度
    $ul.css({ top: "-" + liFirstHeight - 20 + "px" });//利用css的top属性将第一个li隐藏在列表上方	 因li的上下padding:10px所以要-20

    $("#con").hover(function () {
        $ul.pause();//暂停动画
        clearInterval(scrtime);
    }, function () {
        $ul.resume();//恢复播放动画	
        scrtime = setInterval(function scrolllist() {
            //动画形式展现第一个li
            $ul.animate({ top: 0 + "px" }, 1500, function () {
                //动画完成时
                $ul.find("li:last").prependTo($ul);//将ul的最后一个剪切li插入为ul的第一个li
                liFirstHeight = $ul.find("li:first").height();//刚插入的li的高度
                $ul.css({ top: "-" + liFirstHeight - 20 + "px" });//利用css的top属性将刚插入的li隐藏在列表上方  因li的上下padding:10px所以要-20					
            });
        }, 3300);

    }).trigger("mouseleave");//通过trigger("mouseleave")函数来触发hover事件的第2个函数
	
//页面初始化
 var page=1;
   // getDocumentListSize(data);

    //搜索按钮点击后按照关键字与作者搜索
    $('.search').click(function(){
        var searchVal=$('.searchVal').val()||null;
        if(searchVal==null){
           // swal('提示','请先输入关键字，么么哒','error');
           swal({title:'提示',text:'别忘了输入关键字，么么哒',confirmButtonText: 'はい',imageUrl: 'images/dialog/smile.png',
                          imageWidth: 80,
                          imageHeight: 80});
           return;
        }
        location.href="userstrategy_goToUserStrategy.go?page=1&searchVal="+searchVal;
    });

    //按类型筛选
    $('.filterByType').on('click','a',function(){
        $('.filterByType span').removeClass('active');
        $(this).parent().addClass('active');
        var strategyTag=$(this).html();//选中标签的内容
        //获取时间类型
        var filterByTime=document.getElementsByClassName("filterByTime");
        var filterTimeSpan=filterByTime[0].getElementsByTagName("span");
        var timeChosed=null;
        var startTime=null;//开始时间
        var endTime=null;//结束时间
        for(var i=0;i<filterTimeSpan.length;i++){
            if($(filterTimeSpan[i]).hasClass('active')){
                timeChosed=filterTimeSpan[i].getAttribute("data-time");
            }
        }
        endTime=new Date().getTime();
        switch(timeChosed){
            case 'day':
            startTime=endTime-86400000;//天
            break;
            case 'week':
            startTime=endTime-604800000;//星期
            break;
            case 'month':
            startTime=endTime-86400000*30;//月
            break;
            default:
            startTime=null;
            endTime=null;
            break;
        }
        //本站推荐tag获取
        var recommandTag=null;
        var recommandClass=document.getElementsByClassName("recommand")[0];
        var recommandSpans=recommandClass.getElementsByTagName("span");
        for(var i=0;i<recommandSpans.length;i++){
            if($(recommandSpans[i]).hasClass('active')){
                var aTag=recommandSpans[i].getElementsByTagName("a")[0];
                recommandTag=aTag.innerHTML;
            }
        }
        location.href="userstrategy_goToUserStrategy.go?page="+page+"&strategyTag="+strategyTag+"&startTime="+startTime+"&endTime="+endTime+"&recommandTag="+recommandTag;
    });

    //按时间筛选
    $('.filterByTime').on('click','a',function(){
        $('.filterByTime span').removeClass('active');
        var timeChosed=$(this).parent().addClass('active').attr('data-time');
        var startTime=null;//开始时间
        var endTime=null;//结束时间
        var strategyTag=null;//选中标签的内容
        var filterByTypeClass=document.getElementsByClassName("filterByType")[0];
        var filterByTypeSpans=filterByTypeClass.getElementsByTagName("span");
        for(var i=0;i<filterByTypeSpans.length;i++){
            if($(filterByTypeSpans[i]).hasClass('active')){
                strategyTag=filterByTypeSpans[i].getElementsByTagName("a")[0].innerText;
            }
        }
        endTime=new Date().getTime();
         switch(timeChosed){
            case 'day':
            startTime=endTime-86400000;//天
            break;
            case 'week':
            startTime=endTime-604800000;//星期
            break;
            case 'month':
            startTime=endTime-86400000*30;//月
            break;
            default:
            startTime=null;
            endTime=null;
            break;
        }
        var recommandTag=null;
        var recommandClass=document.getElementsByClassName("recommand")[0];
        var recommandSpans=recommandClass.getElementsByTagName("span");
        for(var i=0;i<recommandSpans.length;i++){
            if($(recommandSpans[i]).hasClass('active')){
                var aTag=recommandSpans[i].getElementsByTagName("a")[0];
                recommandTag=aTag.innerHTML;
            }
        }
        location.href="userstrategy_goToUserStrategy.go?page="+page+"&strategyTag="+strategyTag+"&startTime="+startTime+"&endTime="+endTime+"&recommandTag="+recommandTag;
    });

   //按本站推荐筛选
    $('.recommand ').on('click', 'a', function() {
         $('.recommand span').removeClass('active');
        var recommandTag=$(this).parent().addClass('active').children('a').html();
         var strategyTag=null;//选中标签的内容
        var filterByTypeClass=document.getElementsByClassName("filterByType")[0];
        var filterByTypeSpans=filterByTypeClass.getElementsByTagName("span");
        for(var i=0;i<filterByTypeSpans.length;i++){
            if($(filterByTypeSpans[i]).hasClass('active')){
                strategyTag=filterByTypeSpans[i].getElementsByTagName("a")[0].innerText;
            }
        }
        //获取时间类型
        var filterByTime=document.getElementsByClassName("filterByTime");
        var filterTimeSpan=filterByTime[0].getElementsByTagName("span");
        var timeChosed=null;
        var startTime=null;//开始时间
        var endTime=null;//结束时间
        for(var i=0;i<filterTimeSpan.length;i++){
            if($(filterTimeSpan[i]).hasClass('active')){
                timeChosed=filterTimeSpan[i].getAttribute("data-time");
            }
        }
        endTime=new Date().getTime();
        switch(timeChosed){
            case 'day':
            startTime=endTime-86400000;//天
            break;
            case 'week':
            startTime=endTime-604800000;//星期
            break;
            case 'month':
            startTime=endTime-86400000*30;//月
            break;
            default:
            startTime=null;
            endTime=null;
            break;
        }
        location.href="userstrategy_goToUserStrategy.go?page="+page+"&strategyTag="+strategyTag+"&startTime="+startTime+"&endTime="+endTime+"&recommandTag="+recommandTag;
    });


    //分页
    $('.pagination').on('click','a',function(){
        page=$(this).attr('id');
         var strategyTag=null;//选中标签的内容
        var filterByTypeClass=document.getElementsByClassName("filterByType")[0];
        var filterByTypeSpans=filterByTypeClass.getElementsByTagName("span");
        for(var i=0;i<filterByTypeSpans.length;i++){
            if($(filterByTypeSpans[i]).hasClass('active')){
                strategyTag=filterByTypeSpans[i].getElementsByTagName("a")[0].innerText;
            }
        }
         //获取时间类型
        var filterByTime=document.getElementsByClassName("filterByTime");
        var filterTimeSpan=filterByTime[0].getElementsByTagName("span");
        var timeChosed=null;
        var startTime=null;//开始时间
        var endTime=null;//结束时间
        for(var i=0;i<filterTimeSpan.length;i++){
            if($(filterTimeSpan[i]).hasClass('active')){
                timeChosed=filterTimeSpan[i].getAttribute("data-time");
            }
        }
        endTime=new Date().getTime();
        switch(timeChosed){
            case 'day':
            startTime=endTime-86400000;//天
            break;
            case 'week':
            startTime=endTime-604800000;//星期
            break;
            case 'month':
            startTime=endTime-86400000*30;//月
            break;
            default:
            startTime=null;
            endTime=null;
            break;
        }
         var recommandTag=null;
        var recommandClass=document.getElementsByClassName("recommand")[0];
        var recommandSpans=recommandClass.getElementsByTagName("span");
        for(var i=0;i<recommandSpans.length;i++){
            if($(recommandSpans[i]).hasClass('active')){
                var aTag=recommandSpans[i].getElementsByTagName("a")[0];
                recommandTag=aTag.innerHTML;
            }
        }
        location.href="userstrategy_goToUserStrategy.go?page="+page+"&strategyTag="+strategyTag+"&startTime="+startTime+"&endTime="+endTime+"&recommandTag="+recommandTag;
    });
});//jQuery入口函数

//获取攻略列表
function getDocumentList(data){
    $('.strategy-list').empty();
    ajaxRequest('/getDocumentList',data,function(response){
        var strategyNum=response.length;
        if(strategyNum>0){
            for(var i=0;i<strategyNum;i++){
                $('.strategy-list').append($('#strategy-modal').html());
                var target=$('.strategy-list-item').eq(i);
                /*判空过滤*/
                var strategyId=response[i].id||0;
                var title=decodeURIComponent(response[i].title)||'无标题';
                var headImg=response[i].headImg||'../images/logo.png';
                var authorName=decodeURIComponent(response[i].author)||'admin'; 
                var time=transformDate(response[i].time.time);
                var watched=response[i].reader||0;
                var preview=decodeURIComponent(response[i].preview)||'空空如也';
                var author=response[i].author||null;

                /*赋值*/
                $(target).find('h3 a').html(title).attr('href','strategyInfo.html?id='+strategyId);
                $(target).find('.author-headImg a').attr('href','space.html?user='+author);
                $(target).find('.author-headImg img').attr('src',headImg);
                $(target).find('.authorName').html(authorName);
                $(target).find('.time').html(time);
                $(target).find('.watched').html(watched);
                $(target).find('.content-body').html(preview);

            }
        }else{
            $('.strategy-list').append('没有内容');
        }

    });
}

//获取攻略总页数
function getDocumentListSize(data){
    ajaxRequest('/getDocumentListSize',data,function(response){
        var pageNum=response.return;
        page(pageNum,data);
    });
}

//分页
function page(pageNum,data){
    var pageNo=parseInt(data.page);
    if(pageNum==1){
        $('.pagination').empty();
    }else{
        console.log(pageNum);
        $('.pagination').empty()
            .append("<li><a href='#' page='1'>首页</a></li>" +
            "<li class='active'><a href='#'  page='"+pageNo+"'>"+pageNo+"</a></li>" +
            "<li><a href='#' page='"+pageNum+"'>尾页</a></li>");
        if(pageNo!=1)
            $('.pagination li:first').after("<li><a href='#' class='prev' page='"+(pageNo-1)+"'>上一页</a></li>");
        if(pageNo!=pageNum)
            $('.pagination li:last').before("<li><a href='#' class='next' page='"+(pageNo+1)+"'>下一页</a></li>");
        if(pageNo>4){
            $('.pagination .active').before("<li><a href='#'>...</a></li>")
                .before("<li><a href='#' page='"+(pageNo-3)+"'>"+(pageNo-3)+"</a></li>")
                .before("<li><a href='#' page='"+(pageNo-2)+"'>"+(pageNo-2)+"</a></li>")
                .before("<li><a href='#' page='"+(pageNo-1)+"'>"+(pageNo-1)+"</a></li>");
        }else{
            for(var i=1;i<pageNo;i++){
                $('.pagination .active').before("<li><a href='#' page='"+i+"'>"+i+"</a></li>");
            }
        }
        if(pageNum-pageNo>3){
            $('.pagination .active').after("<li><a href='#'>...</a></li>")
                .after("<li><a href='#' page='"+(pageNum)+"'>"+(pageNum)+"</a></li>")
                .after("<li><a href='#' page='"+(pageNum-1)+"'>"+(pageNum-1)+"</a></li>")
                .after("<li><a href='#' page='"+(pageNum-2)+"'>"+(pageNum-2)+"</a></li>");
        }else{
            for(i=pageNum;i>pageNo;i--){
                $('.pagination .active').after("<li><a href='#' page='"+i+"'>"+i+"</a></li>");
            }
        }
    }
    getDocumentList(data);
}