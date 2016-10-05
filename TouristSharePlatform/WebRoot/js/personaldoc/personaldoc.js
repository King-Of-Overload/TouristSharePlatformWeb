var orderJSON='';
$(function(){//jQuery入口函数
	 $("#testimonial-slider").owlCarousel({
	        items:2,
	        itemsDesktop:[1000,2],
	        itemsDesktopSmall:[979,2],
	        itemsTablet:[767,1],
	        pagination: true,
	        autoPlay:true
	    });
	 //未读消息初始化
	 changeUnReadMessageNumber();
	 
	 /*关注按钮的hover事件*/
	 $(".focuson").hover(function(){
	 	$(this).find('.focusonImg').attr('src', 'images/guanzhuhover.png');
	 },function(){
		 $(this).find('.focusonImg').attr("src","images/guanzhu.png");
	 });

	/*进入对方游记按钮*/
	$(".youji").hover(function(){
		$(this).find('.youjiImg').attr("src","images/youjihover.png");
	},function(){
		$(this).find('.youjiImg').attr("src","images/youji.png");
	});
	/*进入对方相册按钮*/
	$(".xiangce").hover(function(){
		$(this).find('.xiangceImg').attr("src","images/xiangcehover.png");
	},function(){
		$(this).find('.xiangceImg').attr("src","images/xiangce.png");
	});
	/*@start 省市联动初始化*/
	'use strict';
	var $citypicker1 = $('#city-picker1');

	$citypicker1.citypicker();

	var $citypicker2 = $('#city-picker2');

	$citypicker2.citypicker({
	    province: '江苏省',
	    city: '扬州市',
	    district: '广陵区'
	});

	var $citypicker3 = $('#city-picker3');

	$('#reset').click(function () {
	    $citypicker3.citypicker('reset');
	});
	/*@end 省市联动初始化*/

	//修改个人信息按钮点击事件
	$('#submitUserDoc').click(function(e) {
    var usignature=$('#usignature').val();
	var nickname=$('#nickname').val();
	var sex=$('input[name="sex"]:checked').val();
	var phone=$('#phone').val();
	var cityName=null;//城市名，需要使用
	var userid=$('.hiddenUserid').val();
	var itemList=document.getElementsByClassName("select-item");
    	for(var i=0;i<itemList.length;i++){//获取城市名
    		var item=itemList[i];
    		var result=item.getAttribute("data-count");
    		if(result=="city") cityName=item.innerText; 
    	}
		if(validateForm(nickname,phone,usignature)){
			$.ajax({
				url:'tripuser_updateUserDocument.do',
				type:"POST",
				async:true,
				cache:false,
				data:{nickname:nickname,sex:sex,phone:phone,cityName:cityName,userid:userid,usignature:usignature},
				success:function(response){
					var json=JSON.parse(response);
					if(json!=null&&json!=''){
						for(var i=0;i<json.length;i++){
							$('.mywelcome').html('欢迎来到'+json[i].username+'的个人空间');
							$('.saying').html(json[i].usignature);
							$('.tdUsername').html(json[i].username);
							$('.tdEmail').html(json[i].useremail);
							$('.tdSex').html(json[i].sex);
							$('.tdPhone').html(json[i].phone);
							$('.tdCity').html(json[i].city);
						}
						swal({title:'恭喜',text:'信息修改成功',type:'success',confirmButtonText: '知道了'});
					}else{
						swal('小源提示','服务器傲娇了，请稍后再试','error');
					}
				},
				error:function(response){
					console.log(response);
				}
			});
		}
	});

//模态框打开事件
 $('#newDocModal').on('shown.bs.modal',function(e) {
 	$('#nickname').val($('.tdUsername').html());
 });
	 //模态框关闭事件
  $('#newDocModal').on('hidden.bs.modal',function(){
  	
   });


  //留言区每一条留言被点击事件
  $('.leaveMessage').on('click', function(e) {
  	var senderName=$(this).find('.message-author').text();
  	var senderContent=$(this).find('.message-content').attr('id');
  	var messageid=$(this).attr('id');
  	$('#messageModal #myModalLabel').text(senderName+'发送给您的悄悄话');
  	$('#messageModal .modal-body').text(senderContent);
  	console.log(messageid);
  	//TODO:此处需要异步访问服务器修改消息状态
  	$.ajax({
  		url:'tripuser_readMessage.do',
  		type:'POST',
  		async:true,
  		cache:false,
  		data:{messageid:messageid},
  		success:function(response){
  			if(response=='success'){
  				console.log('success');
  			}else if(response=='error'){
  				console.log('error');
  			}
  		},
  		error:function(response){
  			console.log(response);
  		}
  	});
  	$(this).find('.message-date').text('已读消息');
  	$(this).parent('li').css('opacity','0.6');
  	changeUnReadMessageNumber();
  });

  //留言区模态框关闭事件
  $('#messageModal').on('hidden.bs.modal',function(e) {
  	$('#messageModal #myModalLabel').text('');
  	$('#messageModal .modal-body').text('');
  });


  //删除选中消息按钮点击事件
  $('.delete').click(function(event) {
  	var selectArray=new Array();
  	var messageLi=$('.message-list li');
  	for(var i=0;i<messageLi.length;i++){
  		var isSelected=messageLi.eq(i).find('input').prop('checked');
  		if(isSelected){
  			selectArray.push({id:messageLi.eq(i).find('.leaveMessage').attr('id')});
  		}
  	}
  	removeMessage(selectArray);
  });

   //全部标记为已读按钮功能
  $('.readAll').click(function(e){
  	var messageLi=$('.message-list li');
  	var messageArray=new Array();
  	for(var i=0;i<messageLi.length;i++){
  		messageArray.push({id:messageLi.eq(i).find('.leaveMessage').attr('id')});
  	}
  	var jsonData=[];
  	for(var i=0;i<messageArray.length;i++){
  		jsonData[i]=messageArray[i];
  	}
  	var data=JSON.stringify(jsonData);
  	console.log(data);
  	//TODO:ajax批量标记消息为已读
  	$.ajax({
  		url:'tripuser_markMessageList.do',
  		type:'POST',
  		async:true,
  		cache:false,
  		data:{jsonArray:data},
  		success:function(response){
  			if(response=='success'){
  				console.log('success');
            for(var i=0;i<messageLi.length;i++){
  		       $('.message-list li').eq(i).find('.message-date').text('已读消息');
  	        }
  	        $('.message-list li').css('opacity','0.6');
  	        $('.messageNum').text('0');
  			}else if(response=='error'){
  				console.log('error');
  			} 
  		},
  		error:function(response){
  			console.log('error');
  		}
  	});
  });

  acquireReplyData();//获取回复消息

  acquireMyCollectionData();//获取收藏信息

  acquireMyOrderData();//获取订单信息
});//jQuery入口函数

//订单项每一项点击事件
function openOrderDialog(oid){
	var myOid='';
	var myPName='';
	var time='';
	var address='';
	var price='';
	for(var i=0;i<orderJSON.length;i++){
		if(orderJSON[i].oid==oid){
			var order=orderJSON[i];
			myOid=order.oid;
			myPName=order.product.pname;
			time=order.otime;
			address=order.paddress;
			price=order.oprice;
		}
	}
	$('#orderModal .orderId').html('订单号：'+myOid);
	$('#orderModal .orderPName').html('产品名：'+myPName);
	$('#orderModal .orderTime').html('下单时间：'+time);
	$('#orderModal .orderAddress').html('地址：'+address);
	$('#orderModal .orderPrice').html('价格：￥'+price);
	$('#orderModal').modal();
}

//获取订单信息
function acquireMyOrderData(){
	var userid=$('.spaceUserId').val();//空间主人id
	var spaceCurrentUserId=$('.spaceCurrentUserId').val();//当前登录用户id
	if(userid==spaceCurrentUserId){
		$.ajax({
			url:'tripuser_acquireMyOrderData.do',
			type:'POST',
			async:true,
			cache:false,
			data:{userid:userid},
			success:function(response){
				console.log(response);
				var json=JSON.parse(response);
				orderJSON=json;
				if(json.length>0){
					$('#myorder ul').empty();
					for(var i=0;i<json.length;i++){
						var order=json[i];
						var oid=order.oid;//订单号
						var ostate=order.ostate;//订单状态
						var productObject=order.product;
						var pid=productObject.pid;//商品id
						var pname=productObject.pname;//产品名称
						var html;
						html='<li><a href="javascript:void(0);" class="orderMessage" onclick="openOrderDialog(\''+oid+'\')">';
						html+='<span class="message-content">订单号：'+oid+'</span>';
						html+='<span class="orderName">&nbsp;您购买了:'+pname+'</span>';
						html+=' </a>';
						if(ostate==0){//已付款
							html+='<span class="message-date pull-right"><form action="product_goToPayFromUser.do?oid='+oid+'" target="_blank"><button class="goPay btn btn-default">去付款</button></form></span>';
						}else if(ostate==1){//未付款
							html+='<span class="message-date pull-right">已付款</span>';
						}
						html+='</li>';
						$('#myorder ul').append(html);
						$('#myorder h3 .myorderNum').text(json.length);
					}
				}else{
					$('#myorder h3 .myorderNum').text(json.length);
					$('#myorder ul').html('<li>您当前没有订单哦</li>');
				}
			},
			error:function(){
				console.log(response);
			}
		});
	}
}

//获取收藏信息
function acquireMyCollectionData(){
	var userid=$('.spaceUserId').val();//空间主人id
	var spaceCurrentUserId=$('.spaceCurrentUserId').val();//当前登录用户id
	if(userid==spaceCurrentUserId){
		$.ajax({
			url:'tripuser_acquireCollectionData.do',
			type:'POST',
			async:true,
			cache:false,
			data:{userid:userid},
			success:function(response){
				console.log('success');
				var json=JSON.parse(response);
				$('#mycollection h3 .collectNum').text(parseInt(json.length));
				$('#mycollection ul').empty();
				if(json.length>0){
					for(var i=0;i<json.length;i++){
						var collection=json[i];
						var pid=collection.pid;
						var pname=collection.productName;
						var html;
						html='<li><a href="product_goToSingleProduct.php?pid='+pid+'" class="singleCollection">';
						html+='<span class="message-content">你收藏了商品:</span>';
						html+='<span class="productName">'+pname+'</span>';
						html+='</a></li>';
						$('#mycollection ul').append(html);
					}
				}else{
					$('#mycollection ul').html('<li>没有收藏记录哦</li>');
				}
			},
			error:function(response){
				console.log(response);
			}
		});
	}
}

//获取回复消息
function acquireReplyData(){
		var userid=$('.spaceUserId').val();//空间的主人id
		var spaceCurrentUserId=$('.spaceCurrentUserId').val();//当前登录用户id
		if(userid==spaceCurrentUserId){//主人有权获得信息
			$.ajax({
				url:'tripuser_acquireReplyData.do',
				type:'POST',
				async:true,
				cache:false,
				data:{userid:userid},
				success:function(response){
					var json=JSON.parse(response);
					$('#replies ul').empty();
					var myReplyList=json.myReplyList;//回复集合array
					var myCommentsList=json.myCommentsList;//评论array
					if(myReplyList.length>0||myCommentsList.length>0){
						for(var i=0;i<myCommentsList.length;i++){
							var html;
							var comment=myCommentsList[i];//评论
							var topicid=comment.topicid;//评论对象id
							var topictype=comment.topictype;//评论兑现类型 strategy skillacademy
							var commentcontent=comment.commentcontent;//评论内容
							var commentName=comment.commentName;//评论人姓名
							var commentdate=comment.commentdate;//评论时间
							var headerImage=comment.headerImage;//头像
							html='<li>';
							if(topictype=='strategy'){
								html+='<a href="userstrategy_goToStrategyInfo.go?ustrategyid='+topicid+'" class="replyMessage" target="_blank">';
							}else if(topictype=='skillacademy'){
								html+='<a href="skillacademy_goToShowOneSkillAcademyArticle.go?skillId='+topicid+'" class="replyMessage" target="_blank">';
							}
							html+='<span class="message-tag">';
							html+='<img src="'+headerImage+'" width="40px" height="40px" style="border-radius: 50%;"></span>';
							html+='<span class="message-content">';
							html+=commentcontent+'</span>';
							html+='<span>可爱的他/她给你评论哦:</span>';
							html+='<span class="message-author">'+commentName+'</span></a></li>';
							$('#replies ul').append(html).parseEmotion();
						}
						//处理回复数据
						for(var j=0;j<myReplyList.length;j++){
							var html;
							var c=myReplyList[j];
							var topicid=c.topicid;//评论对象id
							var commentcontent=c.commentcontent;//父评论对象评论内容
							var topictype=c.topictype;//评论对象类型
							var replyListArray=c.replyList;//回复jsonArray
							if(replyListArray.length>0){
								for(var k=0;k<replyListArray.length;k++){
									var reply=replyListArray[k];//回复对象
									var replycontent=reply.replycontent;//回复内容
									var replydate=reply.replydate;//回复日期
									var replyName=reply.replyName;//回复人姓名
									var headerImage=reply.headerImage;//头像
									html='<li>';
									if(topictype=='strategy'){
										html+='<a href="userstrategy_goToStrategyInfo.go?ustrategyid='+topicid+'" class="replyMessage" target="_blank">';
									}else if(topictype=='skillacademy'){
										html+='<a href="skillacademy_goToShowOneSkillAcademyArticle.go?skillId='+topicid+'" class="replyMessage" target="_blank">';
									}
									html+='<span class="message-tag">';
									html+='<img src="'+headerImage+'" width="40px" height="40px" style="border-radius: 50%;"></span>';
									html+='<span class="message-content">';
									html+='你的评论:'+commentcontent+'</span>';
									html+='<span>他/她回复了你:'+replyName+'</span>';
									html+=' <span class="message-author">'+replycontent+'</span></a></li>';
									$('#replies ul').append(html).parseEmotion();
								}
							}
						}
						$('#replies .focusNum').text(parseInt(myReplyList.length)+parseInt(myCommentsList.length));
					}else{
						$('#replies .focusNum').text('0');
						$('#replies ul').empty();
						$('#replies ul').html('<li>当前没有回复哦</li>');
					}
				},
				error:function(response){
					console.log('内部错误');
				}
			});
		}
}



function changeUnReadMessageNumber(){
     var number=0;
	 var messageLi=$('.message-list li');
  	for(var i=0;i<messageLi.length;i++){
  		var statusText=$('.message-list li').eq(i).find('.message-date').text();
  		if(statusText=='未读消息'){
  			number=parseInt(number)+1;
  		}
  	}
  	$('.messageNum').text(number);
}


//批量删除消息
function removeMessage(selectArray){
	console.log(selectArray);
	if(selectArray.length==0){
		return;
	}
	var jsonData=[];
	for(var i=0;i<selectArray.length;i++){
		jsonData[i]=selectArray[i];
	}
	var data=JSON.stringify(jsonData);
	console.log(data);
	//TODO:此处ajax请求后台服务器删除消息
	$.ajax({
		url:'tripuser_removeMessage.do',
		type:'POST',
		async:true,
		cache:false,
		data:{jsonArray:data},
		success:function(response){
			if(response=='success'){
				console.log('success');
				var messageLi=$('.message-list li');
	            var length=messageLi.length;
	            for(var i=0;i<length;i++){
		           var isSelected=messageLi.eq(i).find('input').prop('checked');
		            if(isSelected){
			           messageLi.eq(i).remove();
		              }
	}
	var newMessageLi=$('.message-list li');
	if(newMessageLi.length==0){
		$('.message-list').html('<li>您当前没有新消息</li>');
	}
	changeUnReadMessageNumber();
			}else if(response=='error'){
				console.log('error');
			}
		},
		error:function(response){
			console.log(response);
		}
	});
	
}

function validateForm(nickname,phone,usignature){
	if(nickname==null||nickname==''){
		swal('小源提示','昵称不能为空','error');
		return false;
	}else if(phone==null||phone==''){
		swal('小源提示','联系电话不能为空','error');
		return false;
	}else if(usignature==null||usignature==''){
		swal('小源提示','个性签名不能为空','error');
		return false;
	}
	return true;
}