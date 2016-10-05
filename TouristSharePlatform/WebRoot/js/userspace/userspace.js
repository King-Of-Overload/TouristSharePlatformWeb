/**
 * @author Alan
 * 
 */
$(function(){//jQuery入口函数
	uParse(".text");//解析文章
	$('.mark').on('click',function(){//关注按钮点击事件
    if(sessionStorage.length==0){
      location.href="tripuser_loginPage.php";
    }
		var innerText=$(this).text();
    var userid=$('.hiddenUserId').attr('value');
    var clickStatus='';//状态
    if(innerText=='关注'){
      clickStatus='focus';
    }else if(innerText=='取消关注'){
      clickStatus='cancel';
    }
    $.ajax({
      url: 'tripuser_focusOrCancelOnSomeOne.do',
      type: 'POST',
      async:true,
      data: {clickStatus: clickStatus,userid:userid},
      success:function(response){
        if(response=='focusSuccess'){
          //更新UI
          $('.mark').html('取消关注');
          swal({title:'小源提示',text:'关注成功',confirmButtonText: 'はい(好)',imageUrl: 'images/dialog/smile.png',
                        imageWidth: 80,
                        imageHeight: 80});
        }else if(response=='cancelSuccess'){
           //更新UI
          $('.mark').html('关注');
          swal({title:'小源提示',text:'取消关注成功',confirmButtonText:'はい(好)',imageUrl:'images/dialog/smile.png',imageWidth:80,imageHeight:80});
        }else if(response=='error'){
          swal({title:'小源提示',text:'服务器傲娇了，请稍后再试',confirmButtonText: 'はい(好)',imageUrl: 'images/dialog/smile.png', imageWidth: 80,
              imageHeight: 80});
        }
      }
    });    
	});//关注按钮点击事件


  //发送私信点击事件
  $("#sendSecretWord").on('click', function(e) {
    var senderName=sessionStorage.username;//发送者名字
    var receiverId=$('.hiddenUserId').val();//接收者id
    var message=encodeURIComponent($('#chatMessage').val());//悄悄话
    if(validateMessage()){
          $.ajax({
      url:'tripuser_sendSecrectWord.do',
      type:'POST',
      async:true,
      data:{senderName:senderName,receiverId:receiverId,message:message},
      success:function(response){
        if(response=="success"){
          swal({title:'小源提示',text:'发送成功',confirmButtonText:'はい(好)',imageUrl:'images/dialog/smile.png',imageWidth:80,imageHeight:80});
        }else if(response=="error"){
          swal({title:'小源提示',text:'啊哦，悄悄话好像被服务器君吃掉了，请稍后再试',confirmButtonText: 'はい(好)',imageUrl: 'images/dialog/smile.png', imageWidth: 80,
              imageHeight: 80});
        }
      }
    });
    }
  });

  //模态框关闭事件
  $('#chatModal').on('hidden.bs.modal',function(){
    $('#chatMessage').val("");
   });

});//jQuery入口函数


function validateMessage(){
  var messageVal=$('#chatMessage').val();
  if(messageVal==''||messageVal==null){
    swal('小源提示','悄悄话没写哦','error');
    return false;
  }
  return true;
}