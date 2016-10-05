/**
 * Created by Administrator on 2015/11/5.
 */

$(function(){
	var userName=decodeURIComponent(sessionStorage.username);
    var headerimage=decodeURIComponent(sessionStorage.headerImage);
	 $('.nickName').html(userName);
     $('.mm-menu__image img').attr('src', headerimage);
	 $('.mm-menu__title').html(userName+'温馨の个人空间');
	 $('.avatar-username').val(sessionStorage.username);
	 var submitResult=false;
	 $(".avatar-save").on('click',function(){
		 submitResult=true;
	 });
 	 //模态框关闭时触发
 	 $('#avatar-modal').on('hide.bs.modal',function(){
 		 if(submitResult==true){
 			swal({title:'天了噜',text:'头像上传成功，刷新即可查看',confirmButtonText: 'はい',imageUrl: 'images/dialog/smile.png',
 		    	  imageWidth: 80,
 		    	  imageHeight: 80});
 			submitResult=false;
 		 }
 	 });

     getUnReadMessage();
});

//获取未读的消息
function getUnReadMessage(){
    var userid=decodeURIComponent(sessionStorage.userid);
    $.ajax({
        url:'tripuser_acquireUnReadMessage.do',
        type:'POST',
        cache:false,
        async:true,
        data:{userid:userid},
        success:function(response){
            console.log('推送成功');
            var json=JSON.parse(response);
            if(json.length>0){
                for(var i=0;i<json.length;i++){
                    var messgae=json[i];
                    $.toast({
                   heading: '有悄悄话哟',
                   text: [
                   message.senderName+'对你说', 
                  '内容:'+message.content],
                   icon: 'info',
                   position:'top-right'
                       });
                }
            }
        },
        error:function(response){
            console.log(response);
        }
    });
}




//获取任意用户消息
function otherUserInfo(aimUser){
    var data={
        aimUser:aimUser
    };
    ajaxHeader('/otherUserInfo',data,function(response){
        var userName=decodeURIComponent(response.username);
        var headImgPath=response.headImg||'../images/headImg.jpg';

        $('.nickName').html(userName);
        $('.headImg img').attr('src',headImgPath);
    });
}

//获取攻略列表
function getDocumentList(aimUser){
    $('.timeLine').empty();
    var data={
        author:aimUser
    };
    ajaxRequest('/getDocumentList',data,function(response){
        var strategyNum=response.length;
        console.log(strategyNum);
        if(strategyNum>0){
            for(var i=0;i<strategyNum;i++){
                $('.timeLine').append($('#timeLine').html());
                var target=$('.timeLine>li').eq(i);
                /*判空过滤*/
                var strategyId=response[i].id||0;
                var title=decodeURIComponent(response[i].title)||'无标题';
                var date=new Date(response[i].time.time)||new Date();
                var year=date.getFullYear();
                var month=date.getMonth()+1;
                var day=date.getDate();
                var watched=response[i].reader||0;
                var preview=decodeURIComponent(response[i].preview)||'空空如也';

                /*赋值*/
                $('.timeLine-date dt').html(month+'/'+day);
                $('.timeLine-date dd').html(year);
                $(target).find('h3 a').html(title).attr('href','strategyInfo.html?id='+strategyId);
                $(target).find('.watched').html(watched);
                $(target).find('.timeLine-intro').html(preview);
            }
        }else{
            /*$('.timeLine').append($('#timeLine').html());
            $('.timeLine li')*/
        }

    });
}


//判断是否关注
function isMarked(aimUser){
    var data={
        aimUser:aimUser
    };
    ajaxHeader('/isMarked',data,function(response){
        if(response.return){   //已关注
            $('.mark').html('取消关注');
        }else{                 //未关注
            $('.mark').html('关注');
        }
    });
}

//关注
function mark(aimUser){
    var data={
        aimUser:aimUser
    };
    ajaxHeader('/mark',data,function(){
        $('.mark').html('取消关注');
        getMarkedMeList(aimUser);
    });
}

//取消关注
function unMark(aimUser){
    var data={
        aimUser:aimUser
    };
    ajaxHeader('/unMark',data,function(){
        $('.mark').html('关注');
        getMarkedMeList(aimUser);
    });
}

//发送信息
function sendMessage(aim,message,preview){
    var data={
        aim:aim,
        message:message,
        preview:preview.length
    };
    ajaxHeader('/sendMessage',data,function(response){
        alert('发送成功');
    });
}

//关注其他用户列表
function getMarkedList(aimUser){
    var data={
        username:aimUser
    };
    ajaxRequest('/getMarkedList',data,function(response){
        $('.focus dt').html(response.length);
        $('.focusNum').html(response.length);
        $('.focus-box .list').empty();
        for(var i=0;i<response.length;i++){
            $('.focus-box .list').append($('#list').html());
            var target=$('.focus-box .list>a').eq(i);
            var name=decodeURIComponent(response[i].to);
            var headImgPath=response[i].img||'../images/logo.png';

            $(target).attr('href','space.html?user='+name);
            $(target).find('img').attr('src',headImgPath);
            $(target).find('.fansName').html(name);
        }
    });
}

//被其他用户关注列表
function getMarkedMeList(aimUser){
    var data={
        username:aimUser
    };
    ajaxRequest('/getMarkedMeList',data,function(response){
        $('.fans dt').html(response.length);
        $('.fansNum').html(response.length);
        $('.fans-box .list').empty();
        for(var i=0;i<response.length;i++){
            $('.fans-box .list').append($('#list').html());
            var target=$('.fans-box .list>a').eq(i);
            var name=decodeURIComponent(response[i].from);
            var headImgPath=response[i].img||'../images/logo.png';

            $(target).attr('href','space.html?user='+name);
            $(target).find('img').attr('src',headImgPath);
            $(target).find('.fansName').html(name);
        }
    });
}