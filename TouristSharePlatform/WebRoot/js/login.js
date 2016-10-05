/**
 * Created by Alan
 */
$(function(){
	/*---------@start登录时间过期，重新登录--------*/
		var error=document.getElementsByClassName("errorOut");
    if(error!=null){
        sessionStorage.clear();
    }
	/*---------@end--------*/
    $('.submit').click(function(){
        var usernameVal=$('input[name=username]').val();
        var passwordVal=$('input[name=password]').val();
        if(check()){
            $.ajax({
                url:'tripuser_login.do',
                type:'POST',
                async:true,
                cache:false,
                data:{
                	username:usernameVal,
                	password:passwordVal
                },
                success:function(response){
                	var data= eval('(' + response+ ')');
                	if(data.message=="loginERROR"){
                		 swal('出门在外可别忘记回家的路','用户名或密码错误','error');
                	}else if(data.message=="notActived"){
                		swal('宝宝不开心','宝宝让你去激活，你不听','error');
                		}else{
                			try{
                                window.sessionStorage.setItem('username',encodeURIComponent($('input[name=username]').val()));
                                window.sessionStorage.setItem('headerImage',encodeURIComponent(data.message));
                                window.sessionStorage.setItem('userid',encodeURIComponent(data.userid));
                               // alert("恭喜您，登录成功");
                                swal({title:'登录成功',text:'欢迎来到芳草寻源',type:'success',confirmButtonText: '前往寻缘首页'}).then(function(isConfirm) {
                                	  if (isConfirm) {
                                		   location.href='index.php';
                                		  }
                                		});
                             
                        	}catch(e){
                        		throw e;
                        		console.log(e.description);
                            }
                		}
                },
                
                error:function(response){
                    console.log(response);
                }
            });
        }
    });


});
function check(){//合法性校验
    var usernameVal=$('input[name=username]').val();
    var passwordVal=$('input[name=password]').val();
    if(!usernameVal||!passwordVal){
        swal('帅哥美女看这里','用户名或密码不得为空','error');
        return false;
    }
    if(!/^[\w\u4e00-\u9fa5]*$/.test(usernameVal)){
        swal('帅哥美女看这里','用户名格式不正确','error');
        return false;
    }
    if(!/^[\w]*$/.test(passwordVal)){
        swal('帅哥美女看这里','密码格式不正确','error');
        return false;
    }
    return true;
}
