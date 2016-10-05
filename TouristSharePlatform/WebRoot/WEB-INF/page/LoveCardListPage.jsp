<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>芳草寻源-${lovetitle}-明信片</title>
<link href="libs/bootstrap.min.css" rel="stylesheet">
    <link href="libs/font-awesome.min.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <script src="libs/jquery.min.js"></script>
    <script src="js/base.js"></script>
    <script src="libs/bootstrap.min.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="js/lovecardindex/lovecardindex.js"></script>
    <style type="text/css">
        .bg_image {
    z-index: -1;
    position: fixed;
    top: 0;
    height: 100%;
    width: 100%;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
    background-position: 50%;
    min-height: 500px;
}
.love-container{
	width: 1000px;
	margin: auto;
	margin-top: 145px;
}
.hd{
	margin-bottom: 30px;
}
.clearfix:after{
	visibility: hidden;
	display: block;
	font-size: 0;
	content: '';
	clear: both;
	height: 0;
}
.fl{
	float: left;
}
.hd h1{
	width: 200px;
}
.hd h1, .hd .lovenav, .hd .lovenav li {
    margin: 0;
    padding: 0;
    float: left;
    list-style: none;
}
a:link{
	color: #3197CB;
	text-decoration: none;
}
.hd .lovenav{
	width: 800px;
	margin-top: 15px;
}
.hd .lovenav li {
    margin-right: 15px;
    background-color: rgba(0, 0, 0, 0.5);
    border-radius: 3px;
    padding: 0 15px;
}
.hd .lovenav li a {
    font-size: 14px;
    line-height: 30px;
    color: #f1f1f1;
}
.bd {
    min-height: 420px;
}
.yui3-g {
    letter-spacing: -0.31em;
    word-spacing: -0.43em;
}
#detailmain {
    width: 640px;
}

.yui3-u {
    display: inline-block;
    zoom: 1;
    letter-spacing: normal;
    word-spacing: normal;
    vertical-align: top;
}
.idlistbox {
    margin-bottom: 1px;
}
.idlistbox {
    background: #fff;
    padding: 20px;
}

.bd h1 {
    margin: 0;
    padding: 0;
    font-size: 25px;
    margin-bottom: 30px;
}
.idlistbox .last {
    margin-bottom: 0;
}
.idlist {
    margin-bottom: 10px;
    float: left;
    width: 50%;
}

.idlist .ilimg, .idlist .ilimg img, .idlist .iltext {
    float: left;
}

a:link {
    color: #3197CB;
    text-decoration: none;
}
body fieldset, img {
    border: 0;
    max-height: 210px;
}
.iltext {
    list-style: none;
    margin: 0;
    padding: 0;
    margin-left: 10px;
}
.idlistbox .last li {
    margin-bottom: 0;
}
.iltext li {
    line-height: 1;
    margin-bottom: 15px;
    font-size: 12px;
    
}

#sider {
    width: 360px;
}
#sider .content {
    margin-left: 50px;
    margin-top: 0px;
}
.sendboxbg, .bgbox {
    background: #fff;
    padding: 20px;
}
#sider p {
    line-height: 1.8;
}
p, fieldset, table, pre {
    margin-bottom: 1em;
}
.gray {
margin-bottom:2px;
    color: #666;
}
    </style>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div>
<div class="love-container">
<!--@start 头部logo及导航-->
    		<div class="hd clearfix">
    			<div class="fl">
    				<h1>
    					<a href="${pageContext.request.contextPath}/lovecard_goToLoveCard.php?page=1">
    						<img src="images/club/postcard/logo.png" alt="我们爱明信片">
    					</a>
    				</h1>
    				<ul class="lovenav">
    					<li><a href="${pageContext.request.contextPath}/lovecard_goToLoveCard.php?page=1">全部活动</a></li>
    					<li><a href="#" class="myPostCard">我的明信片</a></li>
    				</ul>
    			</div>
    		</div>
    		 <!--@end 头部logo及导航-->
    		 
    		<!-- @start 内容部分 -->
    		<div class="bd">
			<div class="yui3-g">
				<div class="yui3-u" id="detailmain">
					<div class="idlistbox clearfix">
					<h1>${lovetitle} 的选中人</h1>
					<s:if test="comments!=null&&comments.size()>0">
					<s:iterator value="comments" var="c">
					<div class="idlist clearfix">
							<div class="ilimg">
							<a href="${pageContext.request.contextPath}/lovecard_goToUserPostCardSpace.php?userid=${c.user.userid}" target="_blank">
								<img src="${c.user.headerimage}" alt="${c.user.username}" width="50" height="50"></a>
							</div>
							<ul class="iltext">
								<li><a href="${pageContext.request.contextPath}/lovecard_goToUserPostCardSpace.php?userid=${c.user.userid}" target="_blank">${c.user.username}</a></li>
								<li class="gray">地址：${c.loveaddress}</li>
								<li class="gray">邮编：${c.lovepostcode}</li>
								<li class="gray">收信人：${c.lovereceivername}</li>
							</ul>
						</div>
					</s:iterator>
					</s:if>
					</div>
				</div>
				
				<div class="yui3-u" id="sider">
					<div class="content bgbox">
						<p><a href="${pageContext.request.contextPath}/lovecard_goToLoveCardEvent.php?loveid=${loveid}">&gt; 回"${lovetitle}"页面</a></p>
					</div>
				</div>
			</div>
		</div>
    		<!-- @end 内容部分 -->
    		
</div>
</div>
<div class="bg_image" style="background-image:url('images/club/postcard/bg.jpg'); opacity:0.8; filter:alpha(opacity=80);"></div>
</body>
</html>