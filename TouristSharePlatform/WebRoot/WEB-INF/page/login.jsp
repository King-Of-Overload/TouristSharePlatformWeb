<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
    <meta charset="UTF-8">
    <title>芳草寻源-登录</title>
    <link href="${pageContext.request.contextPath}/libs/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/libs/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/blurStyle.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/libs/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/background-blur.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/velocity.min.js"></script>
    
<script src="${pageContext.request.contextPath}/js/base.js"></script>
<script src="${pageContext.request.contextPath}/js/login.js"></script>
<script type="text/javascript">
$(function(){
	$('body').backgroundBlur({
        imageURL : 'bgImage/361535899.jpg',
        blurAmount : 1,
        imageClass : 'bg-blur',
        duration: 5000, 
        endOpacity : 1
    }); 
});

</script>
<!-- 对话框特效 -->
<link rel="stylesheet" href="css/sweetalert2.css">
<script src="${pageContext.request.contextPath}/js/sweetalert2.js"></script>
<!-- 兼容IE -->
<script src="${pageContext.request.contextPath}/js/promise.js"></script>
</head>
<body class="container">
<div class="content">
<div class="header">
    <h1 class="logo-header"><a href="${pageContext.request.contextPath }/index.php">欢迎来到芳草寻源</a></h1>
</div>

    <form id="login">
        <h3>登录</h3>
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
        <p>没有账号？<a href="${pageContext.request.contextPath}/tripuser_registerPage.go">立即注册</a></p>
        <button type="button" class="submit btn btn-primary">确认</button>
    </form>
    </div>
</body>
</html>