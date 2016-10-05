/**
 * Created by Administrator on 2015/10/20.
 */
$(function(){
    $('.submit').click(function(){
        var userRegForm={};
        userRegForm.usernameVal=$('input[name=username]').val();
        userRegForm.passwordVal=$('input[name=password]').val();
        userRegForm.confirmPassVal=$('input[name=confirmPass]').val();
        userRegForm.emailVal=$('input[name=email]').val();
        if(check(userRegForm)){//非空与合法性校验
            /*var username=encodeURIComponent(formObj.usernameVal);
            var password=encodeURIComponent(formObj.passwordVal);
            register(username,password);*/
            checkUsername(userRegForm);//检测用户名是否已经被注册
        }
    });
});

/*表单校验*/
function check(userRegForm){
    var usernameVal=userRegForm.usernameVal;
    var passwordVal=userRegForm.passwordVal;
    var confirmPassVal=userRegForm.confirmPassVal;
    var emailVal=userRegForm.emailVal;
    if(!usernameVal||!passwordVal||!confirmPassVal||!emailVal){
        swal('帅哥美女看这里','用户名，密码，邮箱不得为空','error');
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
    if(!/^[\w]*$/.test(confirmPassVal)){//test方法可以用于正则表达式
        return false;
    }
    if(confirmPassVal!=passwordVal){
        swal('帅哥美女看这里','密码不一致','error');
        return false;
    }
    if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(emailVal)){
    	swal('帅哥美女看这里','邮箱格式不正确','error');
    }
    return true;
}

//检测用户名是否可用
function checkUsername(userRegForm){
   // var username=encodeURIComponent(userRegForm.usernameVal);
	var u=userRegForm.usernameVal;
    var password=userRegForm.passwordVal;
    var email=userRegForm.emailVal;
    $.ajax({
    	type:"POST",
    	cache:false,
    	async:true,
    	url:"tripuser_findByName.do",
    	data:{
    		username:u
    	},
       success:function(response){
    	   try{
    	   if(response=="ReRegister"){
    		   swal('客官不可以','芳草寻源的天下可不允许重名哟','error');
    		   return false;
    	   }else if(response=="OKRegister"){
    		   register(u, password, email);
    	   }
    	   }catch(e){
    		   throw e;
    		   console.log(e.description);
    	   }
       }
    });
}

function register(u,password,email){//注册该用户
    var data={
        "username":u,
        "password":password,
        "email":email
    };
    $.ajax({
    	type:"POST",
    	cache:false,
    	async:true,
    	url:"tripuser_regist.do",
    	data:data,
        success:function(response){
    	   if(response=="RegistSuccess"){
    		   //alert("注册成功，请尽快去邮箱激活账号");
    		  swal({title:'注册成功',text:'欢迎成为芳草寻源的一员，请尽快去邮箱激活账号',type:'success'}).then(function(isConfirm) {
            	  if (isConfirm) {
            		  location.href='tripuser_loginPage.go';
           		  }
           		});
   	         
    	   }
       }
    });
}