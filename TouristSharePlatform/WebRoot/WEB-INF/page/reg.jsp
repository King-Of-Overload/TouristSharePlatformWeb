<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
    <meta charset="UTF-8">
    <title>芳草寻源-注册</title>
    <link href="libs/bootstrap.min.css" rel="stylesheet">
    <link href="libs/font-awesome.min.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <link href="css/blurStyle.css" rel="stylesheet">
    <link href="css/reg.css" rel="stylesheet">
    <script src="libs/jquery.min.js"></script>
    <script type="text/javascript" src="js/background-blur.min.js"></script>
    <script src="js/velocity.min.js"></script>
    <!-- 对话框 -->
    <script type="text/javascript" src="js/jquery-confirm.js"></script>
    <script type="text/javascript" src="js/jquery.sticky.min.js"></script>
    <script type="text/javascript">
    $(function(){
    	$('body').backgroundBlur({
            imageURL : 'bgImage/33959218.jpg',
            blurAmount : 1,
            imageClass : 'bg-blur',
            duration: 5000, 
            endOpacity : 1
        }); 
    });

    	
    	

    </script>
<!-- <script src="js/base.js"></script> -->
<script src="js/reg.js"></script>
<!-- 对话框特效 -->
<link rel="stylesheet" href="css/sweetalert2.css">
<script src="js/sweetalert2.js"></script>
<!-- 兼容IE -->
<script src="js/promise.js"></script>
</head>
<body class="container">
      
<div class="content">
<div class="header">
    <h1 class="logo-header"><a href="${pageContext.request.contextPath }/index.php">欢迎来到芳草寻源</a></h1>
</div>
    <form id="reg">
        <h3>注册</h3>
        <strong>
        <font style="font-weight:bold; color:#FF0000;">
	        <s:actionmessage/>
	        <s:actionerror/>
        </font>
        </strong>
        <div class="input-group">
            <span class="input-group-addon"><i class="icon-user"></i></span>
            <input type="text" name="username" class="form-control" placeholder="用户名"/>
        </div>
        <div class="input-group">
            <span class="input-group-addon"><i class="icon-lock"></i></span>
            <input type="password" name="password" class="form-control" placeholder="密码"/>
        </div>
        <div class="input-group">
            <span class="input-group-addon"><i class="icon-lock"></i></span>
            <input type="password" name="confirmPass" class="form-control" placeholder="确认密码"/>
        </div>
        <div class="input-group">
            <span class="input-group-addon"><i class="icon-mail-forward"></i></span>
            <input type="email" name="email" class="form-control" placeholder="邮箱"/>
        </div>
        <p>已经有账号？<a href="${pageContext.request.contextPath}/tripuser_loginPage.go">立即登陆</a></p>
        <button type="button" class="submit btn btn-primary">确认</button>
    </form> 
</div>
</body>
</html>