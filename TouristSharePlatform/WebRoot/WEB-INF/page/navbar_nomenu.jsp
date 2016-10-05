<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<!-- 动态超链接效果 -->
<script src="js/anchorHoverEffect.js"></script>
<link rel="stylesheet" href="css/anchorHoverEffect.css">
<script src="js/navbar.js"></script>
<script src="js/jbase64.js"></script>
<link href="css/animate.css" rel='stylesheet' type='text/css'/> 
<!-- 对话框特效 -->
<link rel="stylesheet" href="css/sweetalert2.css">
<script src="js/sweetalert2.js"></script>
<!-- 兼容IE -->
<script src="js/promise.js"></script>
<!-- 菜单相关 -->
<script src="js/megamenu.js"></script> 
<style type="text/css">.menuOuter{background-color: #f0f0f0;}</style>
<link rel="stylesheet" href="css/menustyle.css">
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top " id="navigation">
		<div class="container ">
			<div class="navbar-header">
				<!--网站Logo-->
				<a class="navbar-brand" href="${pageContext.request.contextPath}/index.php" style="padding: 0px;"><img src="images/weblogo.png" width="52px" height="52px" style="float:left;padding-top: 3px;"><div style="float: left;margin-top: 15px;"><font>芳草寻源旅游共享平台</font></div></a>
			</div>
			<ul class="nav navbar-nav navbar-right user-info">
				<li><a href="${pageContext.request.contextPath}/tripuser_loginPage.go">登录</a></li>
				<li><a href="${pageContext.request.contextPath}/tripuser_registerPage.do">注册</a></li>
			</ul>
			<form class="navbar-form navbar-right" method="post" action="${pageContext.request.contextPath}/goToSearch.php" target="_blank" onsubmit="return toVaild()">
				<div class="form-group">
					<input type="text" class="form-control" name="searchKey"/>
				</div>
				<button type="submit" class="btn btn-default">搜索</button>
			</form>
		</div>
	</nav>
</body>
</html>