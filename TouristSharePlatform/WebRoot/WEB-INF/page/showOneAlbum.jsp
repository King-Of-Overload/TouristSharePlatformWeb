<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>芳草寻源-${photoUseralbumName}-相册详细</title>
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="libs/highlight/styles/default.css" rel="stylesheet" />
<script src="libs/jquery.min.js"></script>
<link href="css/base.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<script src="libs/bootstrap.min.js"></script>
<script src="libs/EpicEditor/js/epiceditor.min.js"></script>
<script src="js/base.js"></script>
<script src="js/modernizr.min.js"></script>
<script src="js/stopExecutionOnTimeout.js?t=1"></script>
<script src="js/showOneAlbum/showOneAlbum.js"></script>
<link href="css/showOneAlbum.css" rel="stylesheet">
<!-- 瀑布流依赖组件 -->
<script src="js/showOneAlbum/jquery.gallerybox.js"></script>
<script src="js/showOneAlbum/pinterest_grid.js"></script>
<link href="css/gallerybox.css" rel="stylesheet">
<!-- 分享部分实现 -->
<link href="css/smohan.pop&share.css" rel="stylesheet"/>
<script src="js/smohan.pop&share.js"></script>
<script type="text/javascript">
//瀑布流插件
$(function(){
	$("#gallery-wrapper").pinterest_grid({
		no_columns: 4,
        padding_x: 10,
        padding_y: 10,
        margin_bottom: 50,
        single_column_breakpoint: 700
	});
	
});
</script>
<script type="text/javascript">
//图片画廊插件
$(function(){
	$(".gallerybox").gallerybox({
		bgColor:"#FFCCCC",
		bgOpacity:0.85
	});
});
</script>
</head>
<body>
 <%@ include file="navbar.jsp" %>
 
 <div class="notes-view-header" id="J-notes-view-header">
<div class="notes-cover-pic" id="J-notes-cover-pic">
<img alt="" id="notes-cover-img" src="<s:property value='indexImage'/>" width="600" height="450" style="width: 1899px; height: 1424.25px; margin-left: -949.5px; margin-top: -712.125px;">
</div>
<div class="bg-mask"></div>
<div class="main-center clearfix">
<div class="public-side-toolbar" id="J_public-side-toolbar">
<div class="side-toolbar-wrapper">
<div class="side-toolbar-bg clearfix">
<a href="${pageContext.request.contextPath}/photo_goToPowerPointView.go?albumId=<s:property value='currentAlbumId'/>" id="J-recommendWrap3" class="aside-btn side-recommend" title="幻灯片浏览">
<img alt="幻灯片浏览" src="images/showOneAlbum/powerpointview.png" width="30px" height="30px" class="powerPointView">
</a>
<a href="${pageContext.request.contextPath}/photo_downloadPackageImgs.go?albumId=<s:property value='currentAlbumId'/>" id="J-recommendWrap3" class="aside-btn side-recommend" title="打包下载" target="_blank">
<img alt="幻灯片浏览" src="images/showOneAlbum/package.png" width="40px" height="40px" class="powerPointView">
</a>
<a href="javascript:void(0)" id="J-favoriteWrap3" class="aside-btn side-favorite" title="分享">
<img alt="分享" src="images/shareBtn.png" width="40px" height="40px" class="share">
</a>
</div>
</div>
</div>
<div class="note-header-main">
<div class="notes-hd">
<h1><strong title="<s:property value='photoUseralbumName'/>" style="color: blue"><s:property value='photoUseralbumName'/></strong>
</h1>
</div>
<div class="users-info-container">
<p class="users-info clearfix">
<a class="avatar" href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='photoUserId'/>&page=1&requestType=strategy" target="_blank">
<img src="<s:property value='photoUserHeadImage'/>" title="<s:property value='photoUserName'/>" alt="<s:property value='photoUserName'/>"/>
</a>
<a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='photoUserId'/>&page=1&requestType=strategy" class="uname"><s:property value='photoUserName'/></a>
</p>
</div>
</div>
</div>
</div>

<!-- @start 瀑布流组件 -->
		<section id="gallery-wrapper" class="wrapper">
		<s:iterator value="singleUserPhotos" var="sup">
			<article class="white-panel"><img src='<s:property value='#sup.photourl'/>' class="thumbnail gallerybox"></article>
			</s:iterator>
        </section>

</body>
</html>