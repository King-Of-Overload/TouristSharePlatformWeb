/**
 * Created by Administrator on 2015/10/20.
 */

$(function(){
	
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
	 var ue = UE.getEditor('container',{//实例化对象
	 	autoHeight: true,
	 	toolbars: [['anchor','undo','redo','bold','indent','snapscreen','italic','underline','strikethrough','subscript','superscript','|','formatmatch','selectall','preview','horizontal','time','date','unlink','|',
	 	            'inserttable','insertrow','insertcol','mergeright', 'mergedown', 'deleterow', 'deletecol','splittorows', 'splittocols', 'splittocells', 'deletecaption','inserttitle', 'mergecells', 'deletetable','|',
	 	           'fontfamily', 'fontsize', 'paragraph','|',
	 	          'simpleupload','link','emotion','spechars','searchreplace','|',
	 	         'justifyleft','justifyright','justifycenter','justifyjustify', 'forecolor', 'backcolor','insertorderedlist', 'insertunorderedlist', 'fullscreen','|'],
	 	         ['rowspacingtop','rowspacingbottom','pagebreak','imageleft','imageright','imagecenter','autotypeset','touppercase','tolowercase','background','template','music','drafts','charts']],
	 	initialFrameHeight:500,
	 	autoHeightEnabled: false,
	 	autoFloatEnabled:true,
	 	scaleEnabled:true
	 });
	 //设置编辑器的内容
	 ue.ready(function() {
		    //设置编辑器的内容
		    ue.setContent('亲，请在此键入内容，注意排版哦，小源在上方为您准备了许多快速排版按钮，您也不希望自己的文章 写出来不漂亮吧( ^_^ )');
		    //获取html内容，返回: <p>hello</p>
		    var html = ue.getContent();
		    console.log(html);
		    //获取纯文本内容，返回: hello
		    var txt = ue.getContentTxt();
		    console.log(txt);
		});
		
				/*@start 点击tag处理 jQuery*/
$(".pictures-categories ul").on('click', 'a', function() {//本站推荐部分
	if( $(this).hasClass("pictures-active")){
		$(this).removeClass("pictures-active");
	}else{
		$(this).addClass('pictures-active');
	}	
});
/*@end 点击tag处理 jQuery*/

/*下拉菜单监听*/
$(".title").on('click','li',function(){
 				var albumName=$(this).html();
 				$(".selectedType").html(albumName);
 				var aN=$(".selectedType a").html();
 });
 
 
 /*@start 上传攻略*/
  var submitStrategy=document.getElementById("submitStrategy");//上传按钮
  submitStrategy.onclick=function(){
  	var tougao="";//投稿内容,需要判断是否为空，需要使用
  	if(!ue.hasContents()){//判断内容是否为空
  		alert("攻略内容不能为空");
  		return;
  	}
  	if(typeof(tougao)!=undefined){//投稿内容
     tougao=$(".selectedType a").html();
  	}
  	var strategyName=document.getElementById("strategyName");
  	if(strategyName.value==''){//判断攻略标题是否填写
  		alert("攻略标题不能为空");
  		return;
  	}
  	var coverstory=document.getElementById("coverstory");
  	if(coverstory.value==''){//简介判空
  		alert("简介不能为空");
  		return;
  	}
  	var itemList=document.getElementsByClassName("select-item");
    	var cityName=null;//城市名，需要使用
    	for(var i=0;i<itemList.length;i++){//获取城市名
    		var item=itemList[i];
    		var result=item.getAttribute("data-count");
    		if(result=="city") cityName=item.innerText; 
    	}
    	    	//获取标签
    	var chosenList=new Array();
    	var count=0;
    	var aList=document.getElementsByClassName("pictures-active");
    	if(aList.length==0){
    		alert("标签不能为空");
    		return;
    	}
    	for(var i=0;i<aList.length;i++){
    		var aTag=aList[i];
    		if(aTag.hasAttribute("class")){
    			chosenList[count]=aTag.innerText;
    			count++;
    		}
    	}
    	//处理数组集合
    	var chosenListStr="";
    	for(var i=0;i<chosenList.length;i++){
    		var choseOne=chosenList[i];
    		chosenListStr+=choseOne+";";
    	}
    	chosenListStr=chosenListStr.substring(0,chosenListStr.length-1);//标签集合,需要使用
  	var htmlContent=ue.getContent();//html内容
  	var plainText=ue.getContentTxt();//纯文本
  	var username=decodeURIComponent(sessionStorage.username);
    $.ajax({
        url:'userstrategy_saveUserStrategy.do',
        type:'POST',
        async:true,
        cache:false,
        data:{
        	username:username,
        	tougao:tougao,
        	strategyName:strategyName.value,
        	coveryStory:coverstory.value,
        	cityName:cityName,
        	tags:chosenListStr,
        	plainText:plainText,
        	strategyContent:htmlContent
        },
        success:function(response){
			try{
        var data= eval('('+response+')');
  				if(data.message=="saveSuccess"){
           swal({title:'恭喜',text:'攻略游记保存成功',confirmButtonText: '(知道了)はい',imageUrl: 'images/dialog/smile.png',
                        imageWidth: 80,
                        imageHeight: 80}).then(function(isConfirm) {
                      if (isConfirm) {
                         location.href='userstrategy_goToStrategyInfo.go?ustrategyid='+data.id;
                        }
                      });
  			}else if(data.message=="saveError"){
  				swal('保存失败','服务器傲娇了，请稍后再试','error');
  			}
  			}catch(e){
  				swal('保存失败','未知错误，请稍后再试','error');
  			}
        },
        
        error:function(response){
            console.log(response);
        }
    });
  };
  /*@start 上传攻略*/
});


 function createXmlHttpRequest(){
 	try{
 		return new XMLHttpRequest();
 	}catch(e){
 		try{
 			return new ActiveXObject("Msxml2.XMLHTTP");
 		}catch(e){
 			try{
 				return new ActiveXObject("Microsoft.XMLHTTP");
 			}catch(e){
 				alert("大哥，您用的是什么浏览器啊");
 			}
 		}
 	}
 }
 /*@start 攻略游记上传*/

   // if(sessionStorage.username&&sessionStorage.password){
//        markdown();
//        $('.nickName').html(decodeURIComponent(sessionStorage.username));
//        $('.title').on('click','a',function(){
//            $('.selectedType').html($(this).html());
//            $('.title li').removeClass('active');
//            $(this).parent().addClass('active');
//        });
//    }else{
//        location.href='login.html';
//    }









