/**
 * Created by Alan Lu on 2016/07/08.
 */
    var userid="";
    var commentId='';//判断id
    var commentName='';//评论人
    var parentLi='';
$(function(a){//jQuery入口函数
	/*@start显示攻略内容*/
	/*uParse(".strategyInfo-body");*/
	/*@end显示攻略内容*/
    $('.share').SmohanPopLayer({ 
    Shade : true, //是否显示遮罩层
    Event:'click', //触发事件
    Content : 'Share', //内容DIV ID 
    Title : '分享攻略到各大社区' //显示标题 
    });
    
	$('.face-icon').SinaEmotion($('#commentContent')); // 绑定表情

    /*@start 返回底部初始化*/
                $body = window.opera ? document.compatMode == "CSS1Compat" ? a("html") :a("body") :a("html,body");
            a("#shang").mouseover(function() {
                up();
            }).mouseout(function() {
                clearTimeout(fq);
            }).click(function() {
                TweenMax.to(window, 1.5, {
                    scrollTo:0,
                    ease:Expo.easeInOut,
                    y:0
                });
            });


        /*
        *点击返回顶部的“下”字时，以1秒是速度滚动到id为#footerbar处。
        * window, 1 为1秒，这里可以随意设置。
        * ease:Expo.easeInOut为速度的曲线缓动，这里支持jquery的ease函数。
        */    
            a("#xia").mouseover(function() {
                dn();
            }).mouseout(function() {
                clearTimeout(fq);
            }).click(function() {
                TweenMax.to(window, 1, {
                    scrollTo:"#footerbar",
                    ease:Expo.easeInOut
                });
            });


        /*
        *绑定返回顶部的“评”字的id"#comt"，以1.5秒的速度滚动到“#pinglun”评论区域。这里可以绑定多个id。
        * window, 1.5 为1.5秒，这里可以随意设置。
        * offsetY:30 为30像素的偏移量。
        */
            $("#comt").click(function() {
                TweenMax.to(window, 1.5, {
                    scrollTo:{
                        y:"#myComment",
                        offsetY:30
                    },
                    ease:Back.easeOut.config(1.9),
                    y:0
                });
            });
    /*@end返回顶部初始化*/

    
    goCheckCommentsHeaderAndName();//初始化评论区头像与名字

    goCheckClickZanStatus();//检查点赞情况

     //赞点击事件
    $('#upLi').on('click', function(e) {
        if(sessionStorage.length!=0){
            var userid=decodeURIComponent(sessionStorage.userid);
            var strategyId=$('.currentStrategyId').val();
            var operateType='';
            var agreeNum=$(this).text();
            console.log(agreeNum);
            if($('.up').parent().hasClass('active')){
                //取消赞
                operateType='cancel';
                agreeNum=parseInt(agreeNum)-1;
            }else{
                //点赞
                operateType='zan';
                agreeNum=parseInt(agreeNum)+1;
            }    
            $.ajax({
                url:'userstrategy_clickOrCancelZan.do',
                type:'POST',
                async:true,
                cache:false,
                data:{userid:userid,strategyId:strategyId,type:'strategy',operateType:operateType},
                success:function(response){
                    if(response=='isAgreed'){
                        console.log('点赞成功');
                        $('.up').parent().css('color','green').addClass('active');
                        $('.up').text(agreeNum);
                    }else if(response=='disagree'){
                        console.log('取消关注成功');
                         $('.up').parent().css('color','#999');
                         $('.up').parent().removeClass('active');
                         $('.up').text(agreeNum);
                    }
                },
                error:function(e){
                    console.log(e);
                }
            });
           
        }else{
            swal({
            title: '小源提示',
            text: '喜欢作者吗，想要点赞需要先登录哦，是否登录',
            type: 'question',
            showCancelButton: true,
            confirmButtonText: '去登录',
            cancelButtonText: '再看看',
               }).then(function(isConfirm) {
              if (isConfirm === true) {
                location.href="tripuser_loginPage.php";
              } else if (isConfirm === false) {
                console.log('用户点击了取消');
              } 
         }); 
        }
    });


    //回复超链接的监听按钮
    $('#info-show ul').on('click', 'a', function(e) {
        if(sessionStorage.length>0){
            userid=decodeURIComponent(sessionStorage.userid);//用户id
            commentId=$(this).attr('id');//判断对象id
            commentName=$(this).parent().parent().children('p').eq(0).text();//评论人
            console.log(commentName);
            console.log(commentId);
            $(this).parent().next().children('.reply_comment_body').toggle();
            parentLi=$(this).parent().parent().parent();
             //表情按钮
            $('.replyface').bind({//次级输入框弹出事件
            click: function(event){
                if(! $('#sinaEmotion').is(':visible')){
                    $(this).sinaEmotion();
                    event.stopPropagation();
                }
            }
        });
        }else{
            swal({
            title: '小源提示',
            text: '回复前需要登录哦，是否登录',
            type: 'question',
            showCancelButton: true,
            confirmButtonText: '去登录',
            cancelButtonText: '再看看',
               }).then(function(isConfirm) {
              if (isConfirm === true) {
                location.href="tripuser_loginPage.php";
              } else if (isConfirm === false) {
                console.log('用户点击了取消');
              } 
         }); 
        }
    });



    //初始化评论区数据
    initialCommentsPartData();


    //导出为word文档点击事件
    $('.exportWordDoc').click(function(event) {
        $(".strategyInfo-body").wordExport();
    });


	
});//jQuery入口函数

//初始化评论区数据
function initialCommentsPartData(){
    var strategyId=$('.currentStrategyId').val();
    $.ajax({
        url:'retriveCommentsAndReplyData.do',
        type:'POST',
        async:true,
        cache:false,
        data:{topicId:strategyId},
        success:function(response){
            if(response!=null&&response!=''){
            console.log('获取成功');
            var html;
            var json=JSON.parse(response);
            console.log(json);
            $('.strategyInfo-comments h3 span').html(json.length);//更新评论数
            if(json.length>0){
                for(var i=0;i<json.length;i++){
                var commentObject=json[i];//评论对象object
                var headerImage=commentObject.headerImage;//头像
                var fromUserName=commentObject.fromUserName;//评论者姓名
                var topicid=commentObject.topicid;//评论对象id(攻略id或技法id)
                var commentcontent=commentObject.commentcontent;//评论内容
                var commentdate=commentObject.commentdate;//评论时间
                var commentid=commentObject.commentid;//评论id
                var replyList=commentObject.replyList;//回复集合数组
                html='<li>';
                html += '<div class="head-face">';
                html += '<img src="'+headerImage+'"/>';
                html += '</div>';
                html += '<div class="reply-cont">';
                html += '<p class="username">'+fromUserName+'</p>';
                html += '<p class="comment-body">'+AnalyticEmotion(commentObject.commentcontent)+'</p>';
                html += '<p class="comment-footer">'+commentdate+'<a href="javascript:void(0);" id="'+commentid+'">  回复</a></p><form><div class="reply_comment_body"><input class="reply_content" type="text" size="70" /><input class="replyface" type="button" value="表情" /><input class="submit" type="button" value="发送" onclick="submitReply()"/></div></form>';
                html += '</div>';
                if(replyList.length>0){
                    for(var j=0;j<replyList.length;j++){
                        var replyObject=replyList[j];
                        var replyid=replyObject.replyid;//回复id
                        var replycontent=replyObject.replycontent;//回复内容
                        var replydate=replyObject.replydate;//回复日期
                        var replyName=replyObject.replyName;//回复姓名
                        html+='<div class="reply-cont">';
                        html+='<p class="username">'+replyName+' 回复本帖</p>';
                        html+='<p class="comment-body">'+AnalyticEmotion(replyObject.replycontent);
                        html+='</p><p class="comment-footer">'+replydate;
                        html+='</p></div>';
                    }
                }
                html+='</li>';
                $('#info-show ul').append(html).parseEmotion();
            }
            }
            }
        },
        error:function(response){
            console.log('内部错误');
        }
    });
}

//回复点击提交按钮事件
function submitReply(){
    var replyContent="";
    var $parentLi=$(parentLi);
    var content=parentLi.find('.reply_content').val();
    if(content==null||content==''){
        alert('回复内容不能为空');
        return;
    }
    replyContent=content;
    console.log(replyContent);
    $.ajax({
        url:'submitReply.do',
        type:'POST',
        async:true,
        cache:false,
        data:{userid:userid,replyContent:replyContent,commentId:commentId},
        success:function(response){
            console.log('回复成功');
            var data=eval('('+response+')');
            if(data.message='success'){
                parentLi.append('<div class="reply-cont"><p class="username">'+decodeURIComponent(sessionStorage.username)+' 回复本帖</p>'
            +'<p class="comment-body">'+AnalyticEmotion(replyContent)+'</p><p class="comment-footer">'+data.dateTime+'</p></div>');
            }else if(data.message=='error'){
                console.log('服务器出错');
            }
        },
        error:function(response){
            console.log('内部错误');
            console.log(response);
        }
    });
}



//初始化头像与名字
function goCheckCommentsHeaderAndName(){
    if(sessionStorage.length==0){
        $('.messageHeaderImg').attr('src', 'images/headerImages/commentHeader.jpg');
        $('.head-face p').text('评论前先登录吧');
    }else{
        $('.messageHeaderImg').attr('src', decodeURIComponent(sessionStorage.headerImage));
        $('.head-face p').text(decodeURIComponent(sessionStorage.username));
    }
}


//检查点赞情况
function  goCheckClickZanStatus(){
    if(sessionStorage.length!=0){
        var userid=decodeURIComponent(sessionStorage.userid);
        var strategyId=$('.currentStrategyId').val();
        $.ajax({
            url:'userstrategy_checkClickZan.do',
            type:'POST',
            async:true,
            cache:false,
            data:{userid:userid,strategyId:strategyId,type:'strategy'},
            success:function(response){
                console.log('接收成功:');
                if(response=='isclicked'){
                    console.log('已点赞');
                    $('.up').parent().css('color','green').addClass('active');
                }else if(response=='notclick'){
                    console.log('没点赞');
                    $('.up').parent().css('color','#999');
                }
            },
            error:function(response){
                console.log('发生错误:'+response);
            }
        });
    }
}


// 下面的 "up()", 20是鼠标悬停时的位移速度，如需更慢，则设置为40或更高。
        function up() {
            $wd = $(window);
            $wd.scrollTop($wd.scrollTop() - 1);
            fq = setTimeout("up()", 20);
        }

        function dn() {
            $wd = $(window);
            $wd.scrollTop($wd.scrollTop() + 1);
            fq = setTimeout("dn()", 20);
        }

//提交评论按钮点击触发
function out(){
    if(sessionStorage.length==0){
              swal({
            title: '小源提示',
            text: '想要留言吗，先登录吧',
            type: 'question',
            showCancelButton: true,
            confirmButtonText: '去登录',
            cancelButtonText: '再看看',
               }).then(function(isConfirm) {
              if (isConfirm === true) {
                location.href="tripuser_loginPage.php";
              } else if (isConfirm === false) {
                console.log('用户点击了取消');
              } 
         }); 
    }else{
        var userid=decodeURIComponent(sessionStorage.userid); 
        var strategyId=$('.currentStrategyId').val();
	    var inputText = $('#commentContent').val();
        var topicType='strategy';
        if(inputText==''||inputText==null){
         swal({title:'小源提示',text:'评论内容不能为空哦',confirmButtonText: 'はい',imageUrl: 'images/dialog/smile.png',
                          imageWidth: 80,
                          imageHeight: 80});
        return;
    }
    $.ajax({
        url:'saveComments.do',
        type:'POST',
        async:true,
        cache:false,
        data:{userid:userid,topicid:strategyId,inputText:inputText,topicType:topicType},
        success:function(response){
            var data=eval('('+response+')');
            if(data.message=='success'){
                console.log('评论成功');
                var dateTime=data.dateTime;
                var commentId=data.commentId;
                var number=$('.strategyInfo-comments h3 span').text();
                $('.strategyInfo-comments h3 span').html(parseInt(number)+1);
                $('#info-show ul').append(reply(AnalyticEmotion(inputText),dateTime,commentId));

            }else if(data.message=='error'){
                alert('服务器傲娇了，请稍后再试');
            }
        },
        error:function(response){
            console.log('评论失败');
            alert('服务器傲娇了,请稍后再试');
        }
    });
    }
}

function reply(content,dateTime,commentId){
	var imgSrc=decodeURIComponent(sessionStorage.headerImage);
	var username=decodeURIComponent(sessionStorage.username);
	var html;
	html  = '<li>';
	html += '<div class="head-face">';
	html += '<img src="'+imgSrc+'"/>';
	html += '</div>';
	html += '<div class="reply-cont">';
	html += '<p class="username">'+username+'</p>';
	html += '<p class="comment-body">'+content+'</p>';
	html += '<p class="comment-footer">'+dateTime+'<a href="javascript:void(0);" id="'+commentId+'">  回复</a></p><form><div class="reply_comment_body"><input class="reply_content" type="text" size="70" /><input class="replyface" type="button" value="表情" /><input class="submit" type="button" value="发送" onclick="submitReply()"/></div></form>';
	html += '</div>';
	html += '</li>';
	return html;
}







