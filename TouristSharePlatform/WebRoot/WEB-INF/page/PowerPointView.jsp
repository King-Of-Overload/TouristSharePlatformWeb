<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="zh">
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>芳草寻源-相册-幻灯片浏览</title>
	<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="libs/highlight/styles/default.css" rel="stylesheet" />
<script src="libs/jquery.min.js"></script>
<link href="css/base.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<script src="libs/bootstrap.min.js"></script>
<script src="js/base.js"></script>
	<link rel="stylesheet" type="text/css" href="css/polaroid-gallery.css"/>
	<!--[if IE]>
		<script src="js/html5shiv.js"></script>
	<![endif]-->
<script type="text/javascript" src="js/imagePointView/polaroid-gallery.js"></script>
	<script>
	    window.onload = function () {
	        var albumId=document.getElementById("currentAlbumId").value;
	        new polaroidGallery(albumId);
	    }
	</script>
</head>
<%@ include file="navbar.jsp" %>
<input type="hidden" value="<s:property value='powerPointAlbumId'/>" id="currentAlbumId"/>
<body class="fullscreen">
	<div id="gallery" class="fullscreen"></div>
	<div id="nav" class="powerPointnavbar">
	    <button id="preview" class="powerButton">&lt; 前一张</button>
	    <button id="next" class="powerButton">下一张 &gt;</button>
	</div>
	
	

</body>
</html>