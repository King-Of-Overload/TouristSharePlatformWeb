<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
	<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
		<title>みなさえわ芳草寻源欢迎您</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/welcome.css" />
		<link rel="stylesheet" href="libs/font-awesome.min.css" />
		<link rel="stylesheet" href="libs/font-awesome.css" />
	</head>
	<body class="loading">
		<div id="wrapper">
			<div id="bg"></div>
			<div id="overlay"></div>
			<div id="main">

				<!-- Header -->
					<header id="header">
						<h1>欢迎来到芳草寻源</h1>
						<p>人生，就是一场旅行，在旅行过程中随时记录精彩，把握精彩，让我们出发吧！</p>
						<nav>
							<ul>
								<li><a href="${pageContext.request.contextPath }/index.php" class="icon icon-leaf">进入首页</a></li>
								<li><a href="${pageContext.request.contextPath }/tripuser_loginPage.php" class="icon  icon-user-md">登录</a></li>
								<li><a href="${pageContext.request.contextPath }/tripuser_registerPage.php" class="icon icon-user">加入我们</a></li>
							</ul>
						</nav>
					</header>

				<!-- Footer -->
					<footer id="footer">
						<span class="copyright">&copy; 版权所有：<font>芳草寻源小组</font></span>
					</footer>

			</div>
		</div>
		<script>
			window.onload = function() { document.body.className = ''; }
			window.ontouchmove = function() { return false; }
			window.onorientationchange = function() { document.body.scrollTop = 0; }
		</script>
	</body>
</html>