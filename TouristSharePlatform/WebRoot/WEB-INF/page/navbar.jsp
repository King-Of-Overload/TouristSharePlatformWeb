<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
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
		
				<div class="menuOuter">
		<div class="menu-container">
        <div class="menu">
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.php">首页君</a></li>
                <li><a href="javascript:void(0);">攻略&&游记</a>
                <ul>
                      <h4>攻略&&游记</h4>
                      <li><a href="${pageContext.request.contextPath}/userstrategy_goToStrategyEdit.php" target="_blank">发表攻略</a></li>
                                <li><a href="${pageContext.request.contextPath}/officialstrategy_goToOfficialStrategy.php" target="_blank">官方攻略</a></li>
                                <li><a href="${pageContext.request.contextPath}/userstrategy_goToUserStrategy.php?page=1" target="_blank">攻略大全</a></li>
                                </ul></li>
                               
                <li><a href="javascript:void(0);">美丽の瞬间</a>
                    <ul>
                    <li><h4>美丽の瞬间</h4></li>
                    <li><a href="${pageContext.request.contextPath}/photo_goToUploadImage.php" target="_blank">创建相册</a></li>
                    <li><a href="${pageContext.request.contextPath}/photo_goToPhoto.php" target="_blank">精彩相册</a></li>
                    </ul>
                </li>
                <li><a href="javascript:void(0);">沙发客</a>
                    <ul>
                    <li><h4>沙发客</h4></li>
                                <li><a href="${pageContext.request.contextPath}/product_goToProductIndex.php" target="_blank">旅游装备</a></li>
                                <li><a href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademy.php" target="_blank">技法学院与素材</a></li>
                                <li><a href="${pageContext.request.contextPath}/lovecard_goToLoveCard.php?page=1" target="_blank">爱の明信片</a></li> 
                                <li><a href="${pageContext.request.contextPath}/FlipboardPageLayout/magazine.html" target="_blank">寻源周报</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    </div>
	</nav>

</body>
</html>