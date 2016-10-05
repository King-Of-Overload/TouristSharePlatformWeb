/**
 * Created by Alan on 2016/7/18.
 */


$(function(){//jQuery入口函数
	
	console.log("快快加入芳草寻源的大家庭吧，用你的洪荒之力使我们这个大家庭更加完美吧，么么哒");
//    判断当前用户已登录
	var u=getcookie("cookieUser");
	if(typeof(u)=='undefined'){
		 $('.user-info').html('<li><a  href="tripuser_loginPage.go" class="a1">登录</a></li><li><a href="tripuser_registerPage.do" class="a2">注册</a></li>');
		 return;
	}
	u=u.replace('\"','')+"==";
	if(sessionStorage.length==0){
		
	    $.ajax({
	        url:'tripuser_checkLoginStatus.do',
	        type:'POST',
	        async:true,
	        data:{userCookieValue:u},
	        cache:false,
	        success:function(response){
	        	var data= eval('(' + response+ ')');
	        	if(data.message=="SUCCESS"){
	        		var username=encodeURIComponent(data.username);
	        		var userHeadImg=encodeURIComponent(data.headerImage);
	        		var userid=encodeURIComponent(data.userid);
	        		sessionStorage.username=username;
	        		sessionStorage.headerImage=userHeadImg;
	        		sessionStorage.userid=userid;
	        	    	var username=decodeURIComponent(sessionStorage.username);
	        	    	var userHeadImg=decodeURIComponent(sessionStorage.headerImage);
	        	    	var userid=decodeURIComponent(sessionStorage.userid);
	        	        $('.user-info')
	        	            .html('<li><a href="tripuser_goToPersonalDoc.php?userid='+userid+'#m-word" target="_blank"><img src=\"'+userHeadImg+'"><span class="badge">99</span></a>' +
	        	            '</li><li style="margin-top:6px;"><a href="tripuser_goToSpace.php?requestType=index" class="username" target="_blank"><font color="blue">'+username+'</font></a></li><li class="logout" style="margin-top:6px;"><a href="javascript:void(0);" class="out" style="color:#337ab7">退出</a></li>	')
	        	            .css('position','relative');     
                        getUnreadMessageSize();//获取未读消息数
	        	       
	        	}else if(data.message=="ERROR"){
		               clearCookie("cookieUser");
		               sessionStorage.clear();
	               location.href='tripuser_loginPage.php';
	        	}
	        },
	        error:function(response){
	            console.log(response);
	        }
	    });
	}else{
		$.ajax({
			 url:'tripuser_checkLoginStatus.do',
		        type:'POST',
		        async:true,
		        data:{'userCookieValue':u,'loginStatus':'sessionexist'},
		        cache:false    
		});
    	var username=decodeURIComponent(sessionStorage.username);
    	var userHeadImg=decodeURIComponent(sessionStorage.headerImage);
    	var userid=decodeURIComponent(sessionStorage.userid);
        $('.user-info')
            .html('<li><a href="tripuser_goToPersonalDoc.php?userid='+userid+'#m-word" target="_blank"><img src=\"'+userHeadImg+'"><span class="badge">99</span></a>' +
            '</li><li style="margin-top:6px;"><a href="tripuser_goToSpace.php?requestType=index" class="username" target="_blank"><font color="blue">'+username+'</font></a></li><li class="logout" style="margin-top:6px;"><a href="javascript:void(0);" class="out" style="color:#337ab7">退出</a></li>	')
            .css('position','relative');
          getUnreadMessageSize();//获取未读消息数
	}


    //退出清除当前用户信息
    $('.logout').click(function(){
        $.ajax({
            url:'tripuser_quit.do',
            type:'POST',
            async:true,
            cache:false,
            success:function(response){
            	var data= eval('(' + response+ ')');
            	if(data.message=="quitSUCCESS"){
                    sessionStorage.clear();
            		//alert("用户名或密码错误");
                    swal({title:'恭喜',text:'您已经成功安全退出',confirmButtonText: 'はい',imageUrl: 'images/dialog/smile.png',
                    	  imageWidth: 80,
                    	  imageHeight: 80}).then(function(isConfirm) {
                  	  if (isConfirm) {
                  		   location.href='index.php';
                  		  }
                  		});
            	}else{
            		  swal({title:'啊哦',text:'服务器傲娇了，请稍后再试',type:'error',confirmButtonText: 'はい'}).then(function(isConfirm) {
                      	  if (isConfirm) {
                      		   location.href='index.php';
                      		  }
                      		});
            	}
            },
            error:function(response){
                console.log(response);
            }
        });
    });
    
    

});//jQuery入口函数

//验证搜索框信息是否为空
function toVaild(){
    var searchVal=$('input[name="searchKey"]').val();
    if(searchVal==''){
        alert("搜索关键字不能为空");
        return false;
    }else{
        return true;
    }
}

//获取未读消息数
function getUnreadMessageSize(){
    var username=decodeURIComponent(sessionStorage.username);
    $.ajax({
        url:'tripuser_getUnreadMessageSize.do',
        type:'POST',
        async:true,
        cache:false,
        data:{username:username},
        success:function(response){
            var messageNum=parseInt(response)||0;
            if(messageNum<=0){
                $('.user-info .badge').empty();
            }else{
                $('.user-info .badge').html(messageNum);
            }
        },
        error:function(response){console.log(response);}
    });
}

//function getcookie(cookieName)  
//{  
//    var cookieValue = document.cookie;  
//    var cookieStartAt = cookieValue.indexOf(""+cookieName+"=");  
//    if(cookieStartAt==-1)  
//    {  
//        cookieStartAt = cookieValue.indexOf(cookieName+"=");  
//    }  
//    if(cookieStartAt==-1)  
//    {  
//        cookieValue = null;  
//    }  
//    else  
//    {  
//        cookieStartAt = cookieValue.indexOf("=",cookieStartAt)+1;  
//        cookieEndAt = cookieValue.indexOf(";",cookieStartAt);  
//        if(cookieEndAt==-1)  
//        {  
//            cookieEndAt = cookieValue.length;  
//        }  
//        cookieValue = unescape(cookieValue.substring(cookieStartAt,cookieEndAt));//解码latin-1  
//    }  
//    return cookieValue;  
//}  

function getcookie(objname){
	var arrstr = document.cookie.split("; ");
	for(var i = 0;i < arrstr.length;i ++){
	var temp = arrstr[i].split("=");
	if(temp[0] == objname) return unescape(temp[1]);
	}
	}

//清除cookie  
function clearCookie(name) {  
    setCookie(name, "", -1);  
}  

//设置cookie
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}


//转换时间
function transformDate(time){
    var date=new Date(time)||new Date();
    var year=date.getFullYear();
    var month=date.getMonth()+1;
    var day=date.getDate();
    var hour=date.getHours();
    var minute=date.getMinutes();
    return year+'-'+month+'-'+day+'　'+hour+':'+minute;
}

//获取url中的数据
function getQueryString(name){
    var reg=new RegExp("(^|&)"+name+"=([^&]*)($|&)","i");
    var str=document.location.search.substr(1).match(reg);
    if(str) return str[2];
    return null;
}
