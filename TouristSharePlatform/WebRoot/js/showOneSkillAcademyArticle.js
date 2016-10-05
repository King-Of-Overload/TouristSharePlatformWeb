/**
 * Created by Alan Lu on 2016/07/08.
 */
    var userid="";
    var commentId='';//判断id
    var commentName='';//评论人
    var parentLi='';
$(function(){//jQuery入口函数
	/*@start 大家在看鼠标移入事件*/
	$(".look .look-list li .pic-summary").css("display","none");
	$(".look .look-list li .pic-summary").eq(0).css("display","block");
	$(".look .look-list li").eq(0).attr("class","now");
	$(".look .look-list li").mouseover(function(){
		 $(".look .look-list li").attr("class","");
		 $(".look .look-list li .pic-summary").css("display","none");
		  $(this).attr("class","now");
		  $(this).children(".pic-summary").css("display","block");
		});
	/*@end大家在看鼠标移入事件*/

	 // 绑定表情
	$('.face-icon').SinaEmotion($('#commentContent'));
	
	uParse(".skillArticle-txt-wrap");//解析文章
	
	/*@start热门文章数字排序处理*/
	var txtList2=$(".txt-list2 li");
	txtList2[0].getElementsByTagName("em")[0].setAttribute("class","hot");
	txtList2[1].getElementsByTagName("em")[0].setAttribute("class","hot");
	txtList2[2].getElementsByTagName("em")[0].setAttribute("class","hot");
	for(var i=0;i<txtList2.length;i++){
		if(i>=3){
			txtList2[i].getElementsByTagName("em")[0].removeAttribute("class");
		}
		var li=txtList2[i];
		var emObject=li.getElementsByTagName("em")[0];
		var n=i+1;
		var textNode=document.createTextNode(n);
		emObject.appendChild(textNode);
	}

	goCheckCommentsHeaderAndName();//初始化头像与名字

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


    initialCommentsPartData();//初始化评论区数据
});//@end jQuery入口方法


//初始化评论区数据
function initialCommentsPartData(){
    var skillacademyId=$('.skillAcademyid').val();
    $.ajax({
        url:'retriveCommentsAndReplyData.do',
        type:'POST',
        async:true,
        cache:false,
        data:{topicId:skillacademyId},
        success:function(response){
            if(response!=null&&response!=''){
            console.log('获取成功');
            var html;
            var json=JSON.parse(response);
            console.log(json);
            $('.comment-num').html(json.length);
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
        $('.head-face p').text('<a href="tripuser_loginPage.php">评论前先登录吧</a>');
    }else{
        $('.messageHeaderImg').attr('src', decodeURIComponent(sessionStorage.headerImage));
        $('.head-face p').text(decodeURIComponent(sessionStorage.username));
    }
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
        var skillacademyId=$('.skillAcademyid').val();
	    var inputText = $('#commentContent').val();
        var topicType='skillacademy';//类型为skillacademy
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
        data:{userid:userid,topicid:skillacademyId,inputText:inputText,topicType:topicType},
        success:function(response){
            var data=eval('('+response+')');
            if(data.message=='success'){
                console.log('评论成功');
                var dateTime=data.dateTime;
                var commentId=data.commentId;
                var number=$('.comment-num').text();
                $('.comment-num').html(parseInt(number)+1);
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