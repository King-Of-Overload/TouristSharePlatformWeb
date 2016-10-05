/**
 * Designed By Alan Lu
 * Twitter: @AlanBingo( ^_^ )
 */
jQuery(function(){//jQuery入口函数
	$('select:not(.ignore)').niceSelect();      
	  FastClick.attach(document.body);
      $("body").gradientify({
          gradients: [
              { start: [49,76,172], stop: [242,159,191] },
              { start: [255,103,69], stop: [240,154,241] },
              { start: [33,229,241], stop: [235,236,117] }
          ]
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
  	 var ue = UE.getEditor('container',{//实例化对象
 	 	autoHeight: true,
 	 	toolbars: [['anchor','undo','redo','bold','indent','snapscreen','italic','underline','strikethrough','subscript','superscript','|','formatmatch','selectall','preview','horizontal','time','date','unlink','|',
	 	            'inserttable','insertrow','insertcol','mergeright', 'mergedown', 'deleterow', 'deletecol','splittorows', 'splittocols', 'splittocells', 'deletecaption','inserttitle', 'mergecells', 'deletetable','|',
	 	           'fontfamily', 'fontsize', 'paragraph','|',
	 	          'simpleupload','insertvideo','attachment','link','emotion','spechars','searchreplace','|',
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
 		    ue.setContent('亲，请在此处键入商品的详细描述，为了更好的体现产品特性，请添加足够多的产品全身照');
 		    //获取html内容，返回: <p>hello</p>
 		    var html = ue.getContent();
 		    console.log(html);
 		    //获取纯文本内容，返回: hello
 		    var txt = ue.getContentTxt();
 		    console.log(txt);
 		});

   //一级分类点击内容改变事件
   $('#category').change(function(e){
    var categoryText=$(this).val();//选中一级分类名
    $.ajax({
      url: 'product_updateSecondCategory.do',
      type: 'POST',
      async: true,
      data: {categoryName:categoryText},
      success:function(response){
        var jsonString=JSON.parse(response);
        $('#secondCategory').find('option').remove();//清空二级分类下的值
        var secondCategorySelect=document.getElementById("secondCategory");
        for(var i=0;i<jsonString.length;i++){
          var singleJson=jsonString[i];
          var secondCid=singleJson.pcsid//二级id
          var secondCName=singleJson.pcsname;//二级分类名称
          var optionNode=document.createElement("option");//创建option节点
          var optionNodeText=document.createTextNode(secondCName);
          optionNode.appendChild(optionNodeText);
          //将新建的option节点放在select对象下
          secondCategorySelect.appendChild(optionNode);
        }
        $('#secondCategory').niceSelect('update'); //更新特效
      }
    });
   });

   //成色选项点选改变事件
   $('.quality-item').on('click', 'li', function(e) {
       $('.quality-item li').attr('class', '');
       $(this).addClass('select');
   });

   //商品提交按钮点击事件
   $('#submitProduct').on('click', function(e) {
    if(validateFormCorrentOrNot()){
      var secondCategoryName=$('#secondCategory').val();//二级分类名称
      var qualitychoice=null;//成色
      $('.quality-item li').each(function(index) {
       if($(this).hasClass('select')){
        qualitychoice=$(this).children('span').eq(0).text();
       } 
      });
    	var productname=$('#productname').val();//产品名
      var description=$('#pdescription').val();//简要说明
      var shopprice=$('#shopprice').val();//价格
      var marketprice=$('#marketprice').val();//市场价
      var cityName=null;//城市名，需要使用
      var itemList=document.getElementsByClassName("select-item");
      for(var i=0;i<itemList.length;i++){//获取城市名
        var item=itemList[i];
        var result=item.getAttribute("data-count");
        if(result=="city") cityName=item.innerText; 
      }
      var phonenum=$('#contactnum').val();//手机号码
      var htmlContent=ue.getContent();//html内容
      var plainText=ue.getContentTxt();//纯文本
      var username=decodeURIComponent(sessionStorage.username);
      $.ajax({
        url: 'product_saveProductData.do',
        timeout : 100000, //超时时间设置，单位毫秒
        type: 'POST',
        async:true,
        data: {username:username,secondCategoryName: secondCategoryName,qualitychoice:qualitychoice,productname:productname,description:description,shopprice:shopprice,
          marketprice:marketprice,cityName:cityName,phonenum:phonenum,htmlContent:htmlContent,plainText:plainText},
        success:function(response){
        	var json= eval('(' + response+ ')');
          if(json.result=='success'){
            console.log('产品id：'+json.pid);
            location.href='product_goToSingleProduct.php?pid='+json.pid;
          }else if(json.result=='error'){
            alert('服务器傲娇了');
          }
        },
        complete:function(XMLHttpRequest,status){
          if(status=='timeout'){
            $(this).abort();
            alert('网络超时');
          }
        }
      })
    };
   });
   
   
 //验证表单数据正确性
   function validateFormCorrentOrNot(){
   	
     if($('#productname').val()==''){
       $("body").overhang({
      type: "error",
      message: "产品名不能为空",
      closeConfirm: "true",
      duration:2
   });
       return false;
     }else if($('#pdescription').val()==''){
   	   $("body").overhang({
      type: "error",
      message: "产品简要说明不能为空",
      closeConfirm: "true",
      duration:2
   });
        return false;
     }else if($('#shopprice').val()==''||$('#marketprice').val()==''||$('#shopprice').val()=='单位(元)'||$('#marketprice').val()=='单位(元)'){
        $("body").overhang({
      type: "error",
      message: "价格或参考价不能为空",
      closeConfirm: "true",
      duration:2
   });
        return false;
     }else if(isNaN($('#shopprice').val())||isNaN($('#marketprice').val())){
      $("body").overhang({
      type: "error",
      message: "又调皮了，输入的是价格吗",
      closeConfirm: "true",
      duration:2
   });
      return false;
     }else if($('#contactnum').val()==''){
       $("body").overhang({
      type: "error",
      message: "手机号码不能为空",
      closeConfirm: "true",
      duration:2
   });
       return false;
     }else if(!(/^1[3|4|5|7|8]\d{9}$/).test($('#contactnum').val())){
    	 $("body").overhang({
    	      type: "error",
    	      message: "哥哥，恕小源愚钝，您这是手机号码吗",
    	      closeConfirm: "true",
    	      duration:2
    	   });
    	 return false;
     }else if(ue.hasContents()==false){
           $("body").overhang({
      type: "error",
      message: "产品详细介绍不能为空",
      closeConfirm: "true",
      duration:2
   });
           return false;
     }
     return true;
   }
});//jQuery入口函数



