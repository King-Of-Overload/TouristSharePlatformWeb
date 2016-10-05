$(function(){//jQuery入口函数
	initialParticipateButton();//初始化参加按钮状态
});//jQuery入口函数

//初始化参加按钮状态
function initialParticipateButton(){
	if(sessionStorage.length==0){
		$('.joinevent').empty();
		var html;
		html='<span id="enter_act_span">';
		html+='<a href="javascript:void(0);" class="yui3-button" onclick="iWannaJoin()">我要参加</a>';
		html+='</span>';
		$('.joinevent').html(html);
	}else{
		var ownerid=$('.postCardOwnerId').val();
		var userid=decodeURIComponent(sessionStorage.userid);
		var loveid=$('.lovePostCardId').val();
		if(ownerid==userid){
			$('.joinevent').empty();
		}else{
			$.ajax({
			url:'lovecard_checkUserJoinStatus.do',
			type:'POST',
			cache:false,
			async:true,
			data:{userid:userid,loveid:loveid},
			success:function(response){
				console.log('判断成功');
				if(response=='hasJoined'){
					$('.joinevent').empty();
					var html;
					html='<span id="enter_act_span">';
					html+='我已参加</span>';
					$('.joinevent').html(html);
				}else if(response=='notJoin'){
					html='<span id="enter_act_span">';
		            html+='<a href="javascript:void(0);" class="yui3-button" onclick="iWannaJoin()">我要参加</a>';
		            html+='</span>';
		            $('.joinevent').html(html);
				}
			},
			error:function(response){
				console.log(response);
			}
		});
		}
	}
}

//我要参加按钮点击事件
function iWannaJoin(){
	if(sessionStorage.length==0){
		swal({
          title: '小源提示',
          text: '想要参加？先登录吧',
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
		var loveid=$('.lovePostCardId').val();
		$.ajax({
			url:'lovecard_saveParticipateStatus.do',
			type:'POST',
			async:true,
			cache:false,
			data:{userid:userid,loveid:loveid},
			success:function(response){
				console.log('参加成功');
				if(response=='success'){
					$('.joinevent').empty();
					var html;
					html='<span id="enter_act_span">';
					html+='我已参加</span>';
					$('.joinevent').html(html);
					console.log('DOM修改完成');
				}
			},
			error:function(response){
				console.log(response);
			}
		});
	}
}

//移动到文本框时检查用户登录状态
function checkCommentUserStatus(){
	if(sessionStorage.length==0){
		$('.typearea2').blur();
			swal({
          title: '小源提示',
          text: '想要留言吗？赶紧去登录吧',
          type: 'question',
          showCancelButton: true,
          confirmButtonText: '去登录',
          cancelButtonText: '再看看',
             }).then(function(isConfirm) {
            if (isConfirm === true) {
              location.href="tripuser_loginPage.php";
            }else if (isConfirm === false) {
              console.log('用户点击了取消');
            } 
       });	
	}
}

//提交评论
function submitComment(){
	if(sessionStorage.length==0){
		swal({
          title: '小源提示',
          text: '想要发表留言吗？赶紧去登录吧',
          type: 'question',
          showCancelButton: true,
          confirmButtonText: '去登录',
          cancelButtonText: '再看看',
             }).then(function(isConfirm) {
            if (isConfirm === true) {
              location.href="tripuser_loginPage.php";
            }else if (isConfirm === false) {
              console.log('用户点击了取消');
            } 
       });
	}else{
		beginSaveCommentTransaction();
	}
}

//开始存储留言事务与修改DOM结构
function beginSaveCommentTransaction(){
	var userid=decodeURIComponent(sessionStorage.userid);
		var username=decodeURIComponent(sessionStorage.username);
		var headerImage=decodeURIComponent(sessionStorage.headerImage);
		var commentcontent=$('.typearea2').val();
		var address=$('#addr_input').val();
		var postcode=$('#code_input').val();
		var receivername=$('#user_input').val();
		var currentNumber=parseInt($('.listbox .list').length);
		var loveid=$('.lovePostCardId').val();
		var ownerid=$('.postCardOwnerId').val();
		if(checkComment(commentcontent,address,postcode,receivername)){
			$.ajax({
				url:'lovecard_saveLovePostCardComment.do',
				type:'POST',
				async:true,
				cache:false,
				data:{loveid:loveid,userid:userid,commentcontent:commentcontent,address:address,postcode:postcode,receivername:receivername},
				success:function(response){
					console.log('发送成功');
					var html;
					html='<div class="list clearfix">';
					html+='<div class="limg">';
					html+='<a href="lovecard_goToUserPostCardSpace.php?userid='+userid+'" target="_blank">';
					html+='<img src="'+headerImage+'" alt="'+username+'" width="50" height="50"></a></div>';
					html+='<ul class="ltext">';
					html+='<li>';
					html+='<span class="gray2 fr">'+(currentNumber+1)+'#</span>';
					html+='<a href="lovecard_goToUserPostCardSpace.php?userid='+userid+'" target="_blank">'+username+'</a></li>';
					html+='<li class="content">'+commentcontent+'</li>';
					html+='<li class="gray2 last">';
					html+='<span>'+response+'</span>';
					html+='<a href="#msgbox" onclick="replyComment(\''+username+'\')" class="gray2">&nbsp;回复</a>';
					if(ownerid==userid){
						html+='<a class="yui3-button fr" onclick="chooseHimOrHer(\''+userid+'\',this)">选择他/她</a>';
					}
					html+='</li></ul></div>';
					if(currentNumber==0){
						$('.listbox .page').remove();
						$('#cmt_text').val('');
						$('#addr_input').val('');
						$('#code_input').val('');
						$('#user_input').val('');
						$('.listbox').append(html);
					}else{
						$('#cmt_text').val('');
						$('#addr_input').val('');
						$('#code_input').val('');
						$('#user_input').val('');
						$('.listbox').append(html);
					}
				},
				error:function(response){
					console.log(response);
				}
			});
		}
}

//选择他/她点击事件
function chooseHimOrHer(commentUserId,a){
	var resendNumber=parseInt($('.resendNumber').val());
	var currenNum=parseInt($('.choseNum').text());
	if(currenNum>=resendNumber){
		$("body").overhang({
      type: "error",
      message: "小源提示：预寄数量已经满了哦",
      closeConfirm: "true",
      duration:2
      });
		return;
	}
	var loveid=$('.lovePostCardId').val();
	console.log(loveid);
	console.log(commentUserId);
	var userpic=$(a).parent('.gray2').parent('.ltext').prev('.limg').children('a').eq(0).children('img').attr('src');
	var name=$(a).parent('.gray2').parent('.ltext').children('li').eq(0).children('a').text();
	$.ajax({
		url:'lovecard_chooseHimOrHer.do',
		type:'POST',
		cache:false,
		async:true,
		data:{loveid:loveid,userid:commentUserId},
		success:function(response){
			console.log('修改成功');
			if(response=='success'){
				$(a).parent('.gray2').append('<span id="enter_act_span">他/她已被选中</span>');
	            $(a).remove();
	   if($('#sider .idlistbox .idlist').length==0){
		$('#sider .idlistbox').empty();
		var html;
		html='<h3 id="choose_box">这些人被楼主选中:<a href="lovecard_showAllChosenUser.do?loveid='+loveid+'">共<span class="choseNum">1</span>人</a></h3>';
		html+='<div class="idlist clearfix">';
		html+='<div class="ilimg">';
		html+='<a href="lovecard_goToUserPostCardSpace.php?userid='+commentUserId+'" target="_blank">';
		html+='<img src="'+userpic+'" alt="'+name+'" width="50" height="50"></a>';
		html+='</div>';
		html+='<ul class="iltext">';
		html+='<li class="nick_txt">';
		html+='<a href="lovecard_goToUserPostCardSpace.php?userid='+commentUserId+'" target="_blank">'+name+'</a></li>';
		html+='<li><span class="gray">(&nbsp;已签&nbsp;)</span></li>';
		html+='</ul></div>';
		html+='<div style="clear: both;" id="post_num_div">';
		html+='<br></div>';
		$('#sider .idlistbox').append(html);
	}else{
		var html;
		html='<div class="idlist clearfix">';
		html+='<div class="ilimg">';
		html+='<a href="lovecard_goToUserPostCardSpace.php?userid='+commentUserId+'" target="_blank">';
		html+='<img src="'+userpic+'" alt="'+name+'" width="50" height="50"></a>';
		html+='</div>';
		html+='<ul class="iltext">';
		html+='<li class="nick_txt">';
		html+='<a href="lovecard_goToUserPostCardSpace.php?userid='+commentUserId+'" target="_blank">'+name+'</a></li>';
		html+='<li><span class="gray">(&nbsp;已签&nbsp;)</span></li>';
		html+='</ul></div>';
		html+='<div style="clear: both;" id="post_num_div">';
		html+='<br></div>';
		$('#sider .idlistbox').append(html);
		$('#choose_box a .choseNum').text(parseInt($('#choose_box a .choseNum').text())+1);
	}
	$('.carddetailtext .gray2').text('(已确'+$('#choose_box a .choseNum').text()+'认)');
			}else if(response=='error'){
				alert('服务器傲娇了，请稍后再试');
			}
		},
		error:function(response){
			console.log(response);
		}
	});
}

//回复超链接
function replyComment(username){
	$('#cmt_text').val('回复@'+username);
	$('#addr_input').val('回复');
	$('#code_input').val('回复');
	$('#user_input').val('回复');
}

//检查评论填写情况
function checkComment(commentcontent,address,postcode,receivername){
	if(commentcontent==''||commentcontent==null){
		$("body").overhang({
      type: "error",
      message: "小源提示：留言内容不能为空哦",
      closeConfirm: "true",
      duration:2
      });
		return false;
	}else if(address==''||address==null){
		$("body").overhang({
      type: "error",
      message: "小源提示：地址不可以为空哦",
      closeConfirm: "true",
      duration:2
      });
		return false;
	}else if(postcode==''||postcode==null){
		$("body").overhang({
      type: "error",
      message: "小源提示：邮编不可以为空哦",
      closeConfirm: "true",
      duration:2
      });
		return false;
	}else if(receivername==''||receivername==null){
		$("body").overhang({
      type: "error",
      message: "小源提示：收件人不可以为空哦",
      closeConfirm: "true",
      duration:2
      });
		return false;
	}
	return true;
}