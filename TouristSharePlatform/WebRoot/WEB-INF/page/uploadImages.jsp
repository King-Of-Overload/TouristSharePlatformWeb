<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta charset="UTF-8">
<title>芳草寻源-相册上传</title>
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="css/uploadImage.css" rel="stylesheet">
<link href="css/base.css" rel="stylesheet">
 <script src="libs/jquery.min.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script type="text/javascript" src="js/zyupload-1.0.0.js"></script>
<link href="css/zyupload-1.0.0.css" rel="stylesheet">
 <script type="text/javascript" src="js/base.js"></script>
 <!-- 对话框特效 -->
<link rel="stylesheet" href="css/sweetalert2.css">
<script src="js/sweetalert2.js"></script>
<script type="text/javascript" src="js/uploadImage.js"></script>
 <script type="text/javascript">
 window.unload=function(){
 alert("退出将丢失编辑内容");
 };
 $(function(){
 	/*@start 上传组件初始化*/
  // 初始化Web Uploader

/*@end 上传组件初始化*/

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

/*@start 点击tag处理 jQuery*/
$(".pictures-categories ul").on('click', 'a', function() {//本站推荐部分
	if( $(this).hasClass("pictures-active")){
		$(this).removeClass("pictures-active");
	}else{
		$(this).addClass('pictures-active');
	}	
});

    getUserAlbums();//获取相册
    
    /**
     *创建相册按钮监听事件 
     */
    var createAlbumBtn=document.getElementById("createAlbum");
    createAlbumBtn.onclick=function(){
    	var albumName=document.getElementById("alBumName").value;//相册名称
    	var albumDescription=document.getElementById("alBumDescription").value;//相册说明
    	var itemList=document.getElementsByClassName("select-item");
    	var cityName=null;
    	for(var i=0;i<itemList.length;i++){//获取城市名
    		var item=itemList[i];
    		var result=item.getAttribute("data-count");
    		if(result=="city") cityName=item.innerText; 
    	}
    	//获取标签
    	var chosenList=new Array();
    	var count=0;
    	var aList=document.getElementsByClassName("pictures-active");
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
    	chosenListStr=chosenListStr.substring(0,chosenListStr.length-1);
    	addUserAlbum(albumName,albumDescription,cityName,chosenListStr);//保存相册信息
    };


/*下拉菜单监听*/
$(".title").on('click','li',function(){
 				var albumName=$(this).html();
 				$(".selectedType").html(albumName);
 				var aN=$(".selectedType a").html();
 				var xmlHttp=createXmlHttpRequest();
 				xmlHttp.open("POST","photo_listenDropDown.do",true);
 				xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
 				xmlHttp.send("albumName="+aN);
 				});
 				
 				/*@start 上传按钮监听*/
 				$(".upload_btn").click(function(){
 				var a=$(".selectedType a").html();
 				//if()
 				});
 				/*@end 上传按钮监听*/
 				

 });
 

 
 
 /**
  * 保存新建相册信息
  * @param {Object} albumName 相册名
  * @param {Object} albumDescription 相册说明
  * @param {Object} cityName 城市名
  * @param {Object} chosenList 标签
  */
 function addUserAlbum(albumName,albumDescription,cityName,chosenListStr){
 	var xmlHttp=createXmlHttpRequest();
 	var username=decodeURIComponent(sessionStorage.username);
 	xmlHttp.open("POST","photo_saveUserAlbum.do",true);
 	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
 	xmlHttp.send("username="+username+"&albumName="+albumName+"&albumDescription="+albumDescription+"&cityName="+cityName+"&chosenListStr="+chosenListStr);
 	xmlHttp.onreadystatechange=function(){//回调函数
 		if(xmlHttp.readyState==4&&xmlHttp.status==200){
 			var result=xmlHttp.responseText;
 			if(result=="savesuccess"){
 				swal({title:'恭喜小主',text:'相册已经创建成功',type:'success',confirmButtonText: '臣妾知道了'});
 				getUserAlbums();//获取相册
 			}else if(result=="saveerror"){
 				 swal('创建失败','服务器傲娇了，请稍后再试','error');
 			}
 		}
 	};
 }
 
 /**
  * 获取用户相册
  */
 function getUserAlbums(){
 	 	var xmlHttp=createXmlHttpRequest();
 	var rowDownUl=document.getElementsByClassName("dropdown-menu")[0];
 	xmlHttp.open("POST","photo_getUserAlbums.do",true);
 	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
 	var userName=decodeURIComponent(sessionStorage.username);//username
 	xmlHttp.send("username="+userName);
 	xmlHttp.onreadystatechange=function(){
 		if(xmlHttp.readyState==4&&xmlHttp.status==200){
 			if(xmlHttp.responseText!=""){
 			    rowDownUl.innerHTML="";
 				var result=eval('('+xmlHttp.responseText+')');//获取jsonarray
 				for(var i=0;i<result.length;i++){
 					var singleAlbum=result[i];
 					var downLi=document.createElement("li");
 					var downA=document.createElement("a");
 					var aTextNode=document.createTextNode(singleAlbum.albumname);
 					downA.appendChild(aTextNode);
 					downLi.appendChild(downA);
 					rowDownUl.appendChild(downLi);
 				}
 				
 			}else{
 				rowDownUl.innerHTML="<li><a href='#'>当前无相册</a></li>";
 			}
 		}
 	};
 }
 
 
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
 
 </script>
 <!-- 省市联动组件相关 -->
 <link href="css/city-picker.css" rel="stylesheet">
 <script src="js/city-picker.data.js"></script>
<script src="js/city-picker.js"></script>
</head>
<body>
   <%@ include file="navbar.jsp"%>
   <div class="content">
                   <div class="title input-group">
   					<div class="input-group-btn">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="selectedType">请选择相册</span><span class="caret"></span>
							<span><button class="chat btn btn-default" data-toggle="modal" data-target="#newAlbumModal">点击此处新建相册</button></span>
							
						</button>
						<ul class="dropdown-menu">
							<li data-type="food"><a href="#">美食</a></li>
							<li data-type="travel"><a href="#">旅行</a></li>
							<li data-type="clothes"><a href="#">衣服搭配</a></li>
							<li data-type="learn"><a href="#">学习</a></li>
							<li data-type="other"><a href="#">其他</a></li>
						</ul>
					</div>
					</div>
              <div id="zyupload" class="zyupload"></div> 
            
            </div>
            
            
            <!--模态框-->
<div class="modal fade" id="newAlbumModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="height:610px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="ModalLabel">新建相册</h3>
            </div>
            <div class="modal-body" style="height:460px;">
            <label for="alBumName">相册名称：</label>
                <textarea class="alBumName form-control" rows="1" id="alBumName"></textarea>
                <label for="alBumName">相册描述：</label>
                <textarea class="alBumDescription form-control" rows="3" id="alBumDescription"></textarea>
                <!-- @start 省市联动组件 -->
                 <label for="distpicker">请选择相关城市：</label>
              <div id="distpicker">
			   <div class="form-group"  style="float: left;">
				<div style="position: relative;">
					<input id="city-picker3" class="form-control" readonly type="text" value="江苏省/常州市/溧阳市" data-toggle="city-picker">						
				</div>
			</div>
			<div class="form-group">
				<button class="btn btn-warning" id="reset" type="button" style="margin-left: 10px;">重置</button>
			</div>
		</div>
                <!-- @end 省市联动组件 -->
                <!-- @start 标签选择 -->
                <div class="alBumLabel" style="float:left;margin-top: 10px;width:100%;">
                    <label for="l" style="float:left;">请选择标签(可多选):</label>
                <!-- @start 标签部分 -->
                <div class="pictures-categories" id="l">
	   <ul>
	   <s:iterator value="albumTags" var="at">
	   <li>
	  <a id="pictures-hotest" href="#"><s:property value="#at.tagname"/></a>
	</li>
	</s:iterator>
	</ul>
	</div>
                <!-- @end 标签部分 -->
                </div>
                 <!-- @end 标签选择 -->
            </div>
            <div class="modal-footer">
                <button type="button" class="submit btn btn-primary" data-dismiss="modal" id="createAlbum">点击创建</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>