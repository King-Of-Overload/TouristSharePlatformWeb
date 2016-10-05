<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
    <!DOCTYPE html>
    <html>
    <head>
    <link href="images/favicon.ico" rel="icon" type="image/x-icon" />
    <title>芳草寻源-爱の明信片</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="description" content="爱の明信片" />
    <link href="libs/bootstrap.min.css" rel="stylesheet">
    <link href="libs/font-awesome.min.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <script src="libs/jquery.min.js"></script>
    <script src="js/base.js"></script>
    <script src="libs/bootstrap.min.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <link rel="stylesheet" type="text/css" href="css/lovecard/lovecard.css">
     <!-- 对话框特效 -->
    <link rel="stylesheet" href="css/sweetalert2.css">
    <script src="js/sweetalert2.js"></script>
    <script type="text/javascript" src="js/lovecardindex/lovecardindex.js"></script>
    </head>
    <body>
    <%@ include file="navbar.jsp"%>
    <div>
    <!-- <embed style="FILTER: alpha(opacity=0,style=3)" src="music/bg.mp3" width="0" height="0" type="audio/mpeg" loop="true" autostart="true"></embed> -->
    <!--网页主内容-->
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
    		<!--@start 内容部分-->
    		 <div class="bd">
    			<div class="yui3-g">
    				<div class="yui3-u" id="sider2">
    					<div class="lcontent">
    						<ul class="siderlist">
    							<li>
    								<a href="" class="hover">全部活动
    								<span class="gray2">(${postCards.list.size()})</span>
    								</a>
    							</li>
    						</ul>
    					</div>
    				</div>
    				<div class="yui3-u" id="main">
    					<div class="cardboxbg">
    					<s:if test="postCards!=null">
    					<s:iterator value="postCards.list" var="lovecd">
    					<div class="clearfix cardbox">
    							<div class="fl boximg">
    								<a href="javascript:void(0);">
    									<img src="${lovecd.lovepic}" alt="">
    								</a>
    							</div>
    							<ul class="fl boxtext">
    								<li class="h1 word_break">
    									<a href="${pageContext.request.contextPath}/lovecard_goToLoveCardEvent.php?loveid=${lovecd.loveid}">${lovecd.lovetitle}</a>
    								</li>
    								<li class="keyword word_break">
    									<span>${lovecd.lovetags}</span>
    								</li>
    								<li class="gray">
    									<span>截止:</span>
    									<span><s:date name="#lovecd.lovedeadline" format="yyyy年MM月dd日"/></span>
    								</li>
    								<li class="gray">
    									<span>发起：</span>
    									<span>
    										<a href="${pageContext.request.contextPath}/lovecard_goToUserPostCardSpace.php?userid=${lovecd.user.userid}" target="_blank">${lovecd.user.username}</a>
    									</span>
    								</li>
    								<li class="last gray">
    									<span class="boxlast">0条留言
    									<span class="gray2">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
    									0人参与
    									</span>
    								</li>
    							</ul>
    						</div>
    					</s:iterator>
    					</s:if>
    					<s:else>
    					<div class="clearfix cardbox">
    					<span>空空如也</span>    					
    					</div>
    					</s:else>		
    					</div>
    					<div class="page">
    						<div class="pagebox">
    						<s:if test="postCards.page!=1">
    						<a href="${pageContext.request.contextPath}/lovecard_goToLoveCard.php?page=${postCards.page-1}" class="pStart">《上一页</a>
    						</s:if>
    						<s:iterator begin="1" end="postCards.totalPage" var="p">
    						<s:if test="#p==postCards.page">
    						<span class="pageon">${p}</span>
    						</s:if>
    						<s:else>
    						<a href="${pageContext.request.contextPath}/lovecard_goToLoveCard.php?page=${p}">${p}</a>
    						</s:else>
    						</s:iterator>
    							<s:if test="postCards.page!=postCards.totalPage">
    							<a href="${pageContext.request.contextPath}/lovecard_goToLoveCard.php?page=${postCards.page+1}" class="pageEnd">下一页》</a>
    							</s:if>
    						</div>
    					</div>
    				</div>
    				<div class="yui3-u" id="sider">
    					<div class="lcontent">
    						<div class="sidermb40 sendboxbg">
    							<div class="sidermb20">
    								<a href="javascript:void(0);" class="yui3-button" onclick="createLoveCard()">+发起寄片活动</a>
    							</div>
    							<p>
    								送出一段祝福，收获一份期待，
    								<br>
    								在这里
    								<br>
    								有
    								<span class="orange">${postCardNumber}</span>
    								张来自各地的明信片正在寄出
    								<br>
    								有
    								<span class="orange">${commentNumber}</span>
    								条留言是送给寄信人的祝福。
    								<br>
    								爱の明信片，分享快乐，分享爱，我们爱明信片。
    							</p>
    						</div>
    						<div class="sidermb40 sendboxbg">
    							<h3>最新留言…………</h3>
    							<div>
    							<s:iterator value="commentList" var="cList">
    							<div class="clearfix sbox">
    									<div class="sboximg fl">
    										<img src="${cList.user.headerimage}" width="50" height="50">
    									</div>
    									<ul class="sboxtext">
    										<li>${cList.user.username}</li>
    										<li class="ct">${cList.lovecommentcontent}</li>
    										<li class="gray2 line_h">
    											来自：
    											<a href="${pageContext.request.contextPath}/lovecard_goToLoveCardEvent.php?loveid=${cList.postCard.loveid}" target="_blank">${cList.postCard.lovetitle}</a>
    										</li>
    									</ul>
    								</div>
    							</s:iterator>
    							</div>
    						</div>
    						<div class="sidermb40 sendboxbg">
    							<h3>正在寄出…………</h3>
    							<s:if test="sendNowList!=null&&sendNowList.size()>0">
    							<s:iterator value="sendNowList" var="sl">
    							<ul class="sendbox">
    								<li>
    								<s:iterator value="#sl.users" var="u" status="i">
    								<s:if test="#i.index==#u.size()">
    								<a href="${pageContext.request.contextPath}/lovecard_goToUserPostCardSpace.php?userid=${u.userid}" target="_blank">${u.username}</a>
    								</s:if>
    								<s:else>
    								<a href="${pageContext.request.contextPath}/lovecard_goToUserPostCardSpace.php?userid=${u.userid}" target="_blank">${u.username}</a>、
    								</s:else>
    								</s:iterator>
    							     &nbsp;等共
    								<a href="javascript:void(0);">${sl.personNumber}位</a>&nbsp;即将收到&nbsp;<a href="${pageContext.request.contextPath}/lovecard_goToUserPostCardSpace.php?userid=${sl.orginizerId}" target="_blank">${sl.orginizerName}</a>&nbsp;寄来的明信片
    								</li>
    								<li class="gray2">
    									来自：
    									<a href="${pageContext.request.contextPath}/lovecard_goToLoveCardEvent.php?loveid=${sl.loveId}" class="gray2">${sl.title}</a>
    								</li>
    							</ul>
    							</s:iterator>
    							</s:if>
    							<s:else>
    						      <span>空空如也</span>
    							</s:else>
    						</div>
    					</div>
    				</div>
    			</div>
    		</div>
    		<!--@end 内容部分-->
    	</div>
    </div>
    <!--背景图片部分-->
    <div class="bg_image" style="background-image:url('images/club/postcard/bg.jpg'); opacity:0.8; filter:alpha(opacity=80);"></div>
    </body>
    </html>