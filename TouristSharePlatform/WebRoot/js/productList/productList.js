$(function(){//jQuery入口函数
	//下拉框改变事件
	$(".sortSelect").change(function(e) {
		var sorttext=$(this).find('option:checked').val();
		$.ajax({
			url: 'product_redirectAjax.do',
			type: 'POST',
			async:true,
			success:function(response){
				if(response=='success'){
					var categoryid=$(".categoryid").val();
		    var secondcid=$(".secondcid").val();
		    var searchValue=$(".searchVal").val();
		    console.log(categoryid);
		    console.log(secondcid);
			location.href="product_goToProductList.do?page=1&quality=all&categoryid="+categoryid+"&secondid="+secondcid+"&sort="+sorttext+"&pricerange=all&searchValue="+searchValue;
				}
			},
			complete:function(XMLHttpRequest,status){
             if(status=='timeout'){
             $(this).abort();
              alert('网络超时');
          }
        }
		});
	});
});//jQuery入口函数