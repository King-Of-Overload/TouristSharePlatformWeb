<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<title>芳草寻源-爱の明信片-寄片活动创建</title>
<meta name="description" content="爱の明信片" />
    <link href="libs/bootstrap.min.css" rel="stylesheet">
    <link href="libs/font-awesome.min.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <script src="libs/jquery.min.js"></script>
    <script src="js/base.js"></script>
    <script src="libs/bootstrap.min.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <link rel="stylesheet" type="text/css" href="css/createlovecard/createlovecard.css">
    <script type="text/javascript" src="js/createlovecard.js"></script>
     <!-- 对话框特效 -->
    <link rel="stylesheet" href="css/sweetalert2.css">
    <script src="js/sweetalert2.js"></script>
    <link rel="stylesheet" type="text/css" href="css/createlovecard/calendar.css">
    <script src="js/moment.min.js"></script>
    <script src="js/es6.js"></script>
    <script src="js/zh-cn.js"></script>
    <!-- 顶层弹出层 Designed By Alan -->
<script src="libs/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/overhang.css" />
<script type="text/javascript" src="js/overhang.js"></script>     
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
    					<li><a href="javascript:void(0);" onclick="ddw()">我的明信片</a></li>
    				</ul>
    			</div>
    		</div>
    		<!--@end 头部logo及导航-->
    		<div class="bd" style="background: #fff;">
    		<h1 style="margin-left:20px;padding-top:20px;">创建活动</h1>
    		<div class="yui3-g" style="clear: both;">
    		<div class="yui3-u" id="main2">
    		<form action="lovecard_submitLoveCard.do" method="post" enctype="multipart/form-data" id="act_form" onsubmit="return checkForm()">
    		<div class="clearfix">
    		<label class="typetitle">标题(必填):</label>    
    		<ul class="typecontent">
    		<li>
    		<input type="text" name="lovetitle" maxlength="30" class="typetext" id="act_title"/>
    		</li>
    		<li>
    		<span class="red">
    		<em class="hidden">空不可以</em>
    		</span>
    		</li>
    		</ul>
    		<br>		
    		</div>
    		<div class="clearfix">
    		   <label class="typetitle">
    		   内容(必填):
    		   </label>
    		   <ul class="typecontent">
    		   <li>
    		   <textarea class="typearea" name="lovedescription" id="act_desc"></textarea>
    		   </li>
    		   <li>
    		   <span class="gray">回信地址可放在内容里面哦……</span>
    		   <br>
    		   </li>
    		   </ul> 		
    		</div>
    		<div class="clearfix">
    		<label class="typetitle">
    		Show图(选填):
    		</label>
    		<ul class="typecontent">
    		<li>
    		<input type="file" name="cover" class="typetext"/>
    		</li>
    		<li>
    		<span class="gray">你可以上传一张封面图片，让你的活动更具有吸引力哦</span>
    		</li>    		
    		</ul>  
    		<br>  		
    		</div>
    		<div class="clearfix">
						<label class="typetitle">
							寄出数量(必填)：
						</label>
						<ul class="typecontent">
							<li><input type="text" name="lovenumber" class="typetext2" id="act_post_num">&nbsp;张</li>
							<li><span class="red"><em class="hidden">你打算寄几张呀</em>&nbsp;</span></li>
						</ul>
					</div>
					<div class="clearfix">
						<label class="typetitle">
							截止时间(必填)：
						</label>
						<ul class="typecontent">
							<li><input type="text" name="lovedeadline" placeholder="Date picker" class="typetext2" id="act_deadline"/></li>
							<li><span class="red"><em class="hidden">明确下活动最终的时间哦</em>&nbsp;</span></li>
						</ul>
					</div>
					<div class="clearfix">
						<label class="typetitle">
							标签(必填)：
						</label>
						<ul class="typecontent">
							<li><input type="text" name="lovetags" value="" class="typetext" id="act_tags"></li>
							<li><span class="gray">目的地，明信片类型，活动属性等等，若有多个，可用“，”或空格分隔，最多10个</span><br><span class="red"><em class="hidden">打点标签，方便别人找到你的活动</em>&nbsp;</span></li>
						</ul>
					</div>
					<div class="clearfix mt30 mb60">
						<label class="typetitle"></label>
						<ul class="typecontent">
							<li><input type="submit" value="开始创建" class="yui3-button"></li>
						</ul>
					</div>
    		</form>
    		</div>
    		<div class="yui3-u" id="sider">
					<div class="content gray" style="padding-right:20px;">
						<h3>分享明信片活动规则</h3>
						<p>自活动发布之日就开始了哦</p>
						<p>在众多留言中，选择你喜欢的留言，点击"寄给他"，留言者的地址将会被添加到地址簿中，你可以在地址管理里看到他们的地址。</p>
						<p>你只能看到与你预寄片数同等的人的地址，并且每次选择，都不可回撤，所以，珍惜每次机会，和留言者好好交流吧~</p>
						<p>有问题，有想法，给提提建议吧，邮箱 nvshenxiaoyuan@163.com</p>
					</div>
				</div>
    		
    		</div>
    		</div>
</div>
</div>
 <!--背景图片部分-->
    <div class="bg_image" style="background-image:url('images/club/postcard/bg.jpg'); opacity:0.8; filter:alpha(opacity=80);"></div>
</body>
</html>