<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>芳草寻源-某人的明信片空间-爱の明信片</title>
<link href="libs/bootstrap.min.css" rel="stylesheet">
    <link href="libs/font-awesome.min.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <script src="libs/jquery.min.js"></script>
    <script src="js/base.js"></script>
    <script src="libs/bootstrap.min.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <link href="css/lovecarduserspace.css" rel="stylesheet">
    <script type="text/javascript" src="js/lovecarduserspace.js"></script>
    <script type="text/javascript" src="js/lovecardindex/lovecardindex.js"></script>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div>
<div class="love-container">
<input type="hidden" value="${existUser.userid}" class="ownerId"/>
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
    		<div class="bd">
    		<div class="yui3-g">
    		<div class="yui3-u" id="main2">
    		<div class="bgbox">
    		<h1>${existUser.username}的明信片</h1>
    		<div class="peoplenav">
    		<ul class="nav nav-tabs" role="tablist">
            <li class="information active" role="presentation">
                <a href="#information" aria-controls="information" role="tab" data-toggle="tab">某某の动态</a>
            </li>
            <li class="relation" role="presentation">
                <a href="#activity" aria-controls="activity" role="tab" data-toggle="tab">某某参与の活动</a>
            </li>
            <li class="messages" role="presentation">
                <a href="#organize" aria-controls="organize" role="tab" data-toggle="tab">某某发起の</a>
            </li>
        </ul>
         <div class="tab-content">
          <div role="tabpanel" class="tab-pane active" id="information">
          <div>
          <dl class="cardnoticebox">
          <s:if test="advertiseList!=null">
          <s:iterator value="advertiseList" var="adverlist">
          <dd class="clearfix">
          <div class="fr cnbimg">
          <a href="${pageContext.request.contextPath}/lovecard_goToLoveCardEvent.php?loveid=${adverlist.loveid}" target="_blank">
          <img alt="${adverlist.lovetitle}" src="${adverlist.lovepic}">
          </a>
          </div>
          <div>
          <span>${adverlist.user.username}</span>
          &nbsp;发起了&nbsp;
          <a href="${pageContext.request.contextPath}/lovecard_goToLoveCardEvent.php?loveid=${adverlist.loveid}" target="_blank">${adverlist.lovetitle}</a>
          &nbsp;活动&nbsp;
          </div>
          <ul class="cnbtext">
          <li>预寄：${adverlist.lovenumber}张明信片</li>
          <li>截止：<s:date name="#adverlist.lovedeadline" format="yyyy年MM月dd日 HH:mm"/></li>
          </ul>          
          </dd>
          </s:iterator>
          </s:if>
          <s:else>
          <dd>空空如也</dd>
          </s:else>          
          </dl>
          </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="activity">
            <div class="mt30">
            <s:if test="participateList!=null">
            <s:iterator value="participateList" var="par">
            <div class="clearfix cardbox">
							<div class="fl boximg">
								<a href="${pageContext.request.contextPath}/lovecard_goToLoveCardEvent.php?loveid=${par.loveid}">
								<img src="${par.lovepic}" alt="${par.lovetitle}">
								</a>
							</div>
							
							<ul class="fl boxtext">
								<li class="hl"><a href="${pageContext.request.contextPath}/lovecard_goToLoveCardEvent.php?loveid=${par.loveid}">${par.lovetitle}</a></li>
								<li class="keyword"><span>${par.lovetags}</span></li>
								<li class="gray"><span>截止：</span><span><s:date name="#par.lovedeadline" format="yyyy-MM-dd HH:mm"/></span></li>
								<li class="gray"><span>发起：</span><span>${par.user.username}</span></li>
								<li class="last gray "><span class="boxlast">${par.commentNumber}条留言<span class="gray2">&nbsp;&nbsp;|&nbsp;&nbsp;</span>${par.joinNumber}人参与</span></li>
							</ul>
						</div>
            </s:iterator>
            </s:if>
            <s:else>
                                               空空如也
            </s:else>
            </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="organize">
            <div class="mt30">
            <s:if test="advertiseList!=null">
            <s:iterator value="advertiseList" var="adList">
            <div class="clearfix cardbox">
							<div class="fl boximg">
								<a href="${pageContext.request.contextPath}/lovecard_goToLoveCardEvent.php?loveid=${adList.loveid}">
								<img src="${adList.lovepic}" alt="${adList.lovetitle}">
								</a>
							</div>
							
							<ul class="fl boxtext">
								<li class="hl"><a href="${pageContext.request.contextPath}/lovecard_goToLoveCardEvent.php?loveid=${par.loveid}">${adList.lovetitle}</a></li>
								<li class="keyword"><span>${adList.lovetags}</span></li>
								<li class="gray"><span>截止：</span><span><s:date name="#adList.lovedeadline" format="yyyy-MM-dd HH:mm"/></span></li>
								<li class="gray"><span>发起：</span><span>${adList.user.username}</span></li>
								<li class="last gray "><span class="boxlast">${adList.commentNumber}条留言<span class="gray2">&nbsp;&nbsp;|&nbsp;&nbsp;</span>${adList.joinNumber}人参与</span></li>
							</ul>
						</div>
            </s:iterator>
            </s:if>
            <s:else>
                                                空空如也
            </s:else>
					</div>         
            </div>
         </div>	
    		</div>    		
    		</div>
    		</div>
    		<div class="yui3-u" id="sider">
	<div class="content">
		<div class="idbox clearfix">
			<div class="ibimg">
			    <img src="${existUser.headerimage}" alt="${existUser.username}" width="50" height="50">
				</div>
			<ul class="ibtext">
				<li class="name"><span>${existUser.username}</span></li>
				<li class="follow">
					<a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=${existUser.userid}&page=1&requestType=strategy" target="_blank">用户主页</a> 
					</li>
				<li>个人宣言：
				<s:if test="existUser.usignature!=null">
				${existUser.usignature}
				</s:if>
				<s:else>
				楼主有点懒哦，没有写个人宣言
				</s:else>
				</li></ul>
		</div>
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