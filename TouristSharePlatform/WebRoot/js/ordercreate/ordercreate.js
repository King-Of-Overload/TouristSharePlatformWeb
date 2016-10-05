$(function(){//jQuery入口函数
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

	//提交按钮点击事件
	$('#submitAddress').on('click',function(e) {
		var paddress=null;
		var provinceName=null;
		var cityName=null;
		var districtName=null;
		var itemList=document.getElementsByClassName("select-item");
    	var cityName=null;//城市名，需要使用
    	for(var i=0;i<itemList.length;i++){//获取城市名
    		var item=itemList[i];
    		var result=item.getAttribute("data-count");
    		if(result=="province") provinceName=item.innerText;
    		if(result=="city") cityName=item.innerText; 
    		if(result=="district") districtName=item.innerText;
    	}
    	paddress=provinceName+cityName+districtName;
    	var detailAddress=$('#pAddress').val();//详细地址
    	var buyerName=$('#buyerName').val();//收货人姓名
    	var cellphone=$('#cellphone').val();//收货人手机
    	var userid=$('.currentUser').val();
		if(validateForm(detailAddress,buyerName,cellphone)){
			paddress=paddress+detailAddress;//完整地址
			$.ajax({
				url: 'product_asyncObtainAddress.do',
				type: 'POST',
				async:true,
				data: {paddress:paddress,buyerName:buyerName,cellphone:cellphone,userid:userid},
				success:function(response){
							$('#address-list').empty();
							var addressListUl=$('#address-list');//ul结点
					if(response!=null&&response!=''){
						var jsonStr=JSON.parse(response);
						refreshAddressList(jsonStr,addressListUl);
					}
				}
			});
		}
	});

	$('#address').change(function(e) {
		//var $selectedvalue = $("input[name='rl$tt']:checked").val();
		var addressid=$("input[name='address']:checked").val();//地址Id
		$.ajax({
			url: 'product_registerAddressId.do',
			type: 'POST',
			async:true,
			data: {addressid: addressid},
			success:function(response){
				var jsonStr=JSON.parse(response);
				var singlejson=jsonStr[0];
				var addressText=singlejson.address;
				var personalinfo=singlejson.addressname+singlejson.phone;
				$('#J_AddrConfirm').html(addressText);
				$('#J_AddrNameConfirm').html(personalinfo);
			}
		});
	});
	
});//jQuery入口函数

function deleteAddress(addressid){
	console.log(addressid);
	$.ajax({
			url: 'product_deleteAddress.do',
			type: 'POST',
			async:true,
			data: {addressid: addressid},
			success:function(response){
				if(response!=null&&response!=''){
					var jsonStr=JSON.parse(response);
					$('#address-list').empty();
					var addressListUl=$('#address-list');//ul结点
					refreshAddressList(jsonStr,addressListUl);
					x0p({title:'小源提示',text:'删除成功',type:'ok'});
				}else{
					x0p('小源提示', '天了噜，服务器傲娇了');
				}
			}
		});
}


function refreshAddressList(jsonStr,addressListUl){
						for(var i=0;i<jsonStr.length;i++){
							var singlejson=jsonStr[i];
							var liNode=document.createElement("li");//创建ul节点
							liNode.setAttribute("class", "J_Addr J_MakePoint clearfix  J_DefaultAddr");
							var divNode=document.createElement("div");//创建div节点
							divNode.setAttribute("class","address-info");
							var inputNode=document.createElement("input");//创建input标签
							inputNode.setAttribute("name", "address");
							inputNode.setAttribute("class","J_MakePoint");
							inputNode.setAttribute("type","radio");
							inputNode.setAttribute("value",singlejson.addressid);
							inputNode.setAttribute("id","address");
							var labelNode=document.createElement("label");//创建label节点
							labelNode.setAttribute("for","addrId_674944241");
							labelNode.setAttribute("class","user-address");
							//var labelText=document.createTextNode(singlejson.address+"("+singlejson.addressname+"收)"+"<em class=\"userphone\">"+singlejson.phone+"</em>&nbsp;&nbsp;<a href=\"product_deleteAddress.do?addressid="+singlejson.addressid+"\"><em>删除</em></a>");
							//labelNode.appendChild(labelText);
							var labelNodejQuery=$(labelNode);
							labelNodejQuery.html(singlejson.address+"("+singlejson.addressname+"收)"+"<em class=\"userphone\">"+singlejson.phone+"</em>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"deleteAddress("+singlejson.addressid+")\"><em>删除</em></a>");
							var lDOMLabel=$(labelNodejQuery)[0];
							divNode.appendChild(inputNode);
							//divNode.appendChild(labelNode);
							divNode.appendChild(lDOMLabel);
							liNode.appendChild(divNode);
							addressListUl[0].appendChild(liNode);
						}
						$("input[name='address']").eq(0).attr('checked', true);
}



function validateForm(detailAddress,buyerName,cellphone){
	if(detailAddress==''){
	x0p('小源提示', '详细地址不能为空');
	return false;	
	}else if(buyerName==''){
		x0p('小源提示','收货人姓名不能为空');
		return false;
	}else if(cellphone==''){
		x0p('小源提示','联系电话不能为空');
		return false;
	}
	return true;
}