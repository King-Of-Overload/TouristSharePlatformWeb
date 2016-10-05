$(function(){//jQuery入口函数
	$('#etalage').etalage({
		thumb_image_width: 300,
		thumb_image_height: 400,
		source_image_width: 900,
		source_image_height: 1200,
		show_hint: true,
		click_callback: function(image_anchor, instance_id){
			alert('Callback example:\nYou clicked on an image with the anchor: "'+image_anchor+'"\n(in Etalage instance: "'+instance_id+'")');
		}
	});
    /*@start显示攻略内容*/
	uParse(".productDetail");


	initCollectionHref();//初始化收藏超链接
});//jQuery入口函数

//点击收藏超链接事件
function collectProduct(pid){
	var userid=sessionStorage.userid;
	$.ajax({
		url:'product_collectProduct.do',
		type:'POST',
		async:true,
		cache:false,
		data:{userid:userid,pid:pid},
		success:function(response){
			console.log('success');
			if(response=='success'){
				$('.m_link').html('<a href="javascript:void(0);">您已经收藏</a>');
			}else{
				console.log('内部错误');
			}
		},
		error:function(response){
			console.log(response);
		}
	});
}


//初始化收藏超链接
function initCollectionHref(){
	if(sessionStorage.length==0){
		$('.m_link').html('<a href="tripuser_loginPage.php">登录以后可以收藏哦</a>');
	}else{
		var userid=decodeURIComponent(sessionStorage.userid);
		var pid=$('.currentProductId').val();
		$.ajax({
			url:'product_checkProductCollectStatus.do',
			type:'POST',
			async:true,
			cache:false,
			data:{userid:userid,pid:pid},
			success:function(response){
				console.log('success');
				if(response=='colleced'){
					$('.m_link').html('<a href="javascript:void(0);">您已经收藏</a>');
				}else{
					var html;
					html='<a href="javascript:void(0);" onclick="collectProduct(\''+pid+'\')">点我收藏哦</a>';
					$('.m_link').html(html);
				}
			},
			error:function(response){
				console.log(response);
			}
		});
	}
}


