<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>芳草寻源-活动详情</title>
<link href="libs/bootstrap.min.css" rel="stylesheet">
    <link href="libs/font-awesome.min.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <script src="libs/jquery.min.js"></script>
    <script src="js/base.js"></script>
    <script src="libs/bootstrap.min.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <link rel="stylesheet" href="css/lovecardevent/lovecardevent.css">
    <script type="text/javascript" src="js/lovecardevent/lovecardevent.js"></script>
    <!-- 对话框特效 -->
    <link rel="stylesheet" href="css/sweetalert2.css">
    <script src="js/sweetalert2.js"></script>
        <!-- 顶层弹出层 Designed By Alan -->
    <script src="libs/jquery-ui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/overhang.css" />
    <script type="text/javascript" src="js/overhang.js"></script> 
    <script type="text/javascript" src="js/lovecardindex/lovecardindex.js"></script>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div>
<div class="love-container">
<input type="hidden" value="${postCard.loveid}" class="lovePostCardId"/>
<input type="hidden" value="${postCard.user.userid}" class="postCardOwnerId"/>
<input type="hidden" value="${postCard.lovenumber}" class="resendNumber"/>
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
    		    <div class="yui3-u" id="detailmain">
    		    <div class="clearfix carddetailbox">
    		    <div class="carddetailimg fl">
    		    <img alt="${postCard.lovetitle}" src="${postCard.lovepic}">
    		    </div>
    		    <div class="fl" style="max-width:340px;">
						<h1>${postCard.lovetitle}</h1>
						<ul class="carddetailtext">
							<li><span class="gray">开始：</span><span><s:date name="postCard.lovestarttime" format="yyyy年MM月dd日"/></span></li>
							<li><span class="gray">截止：</span><span><s:date name="postCard.lovedeadline" format="yyyy年MM月dd日"/></span></li>
							<li><span class="gray">标签：</span><span>${postCard.lovetags}</span></li>
							<li><span class="gray">发起：</span><span><a href="${pageContext.request.contextPath}/lovecard_goToUserPostCardSpace.php?userid=${postCard.user.userid}" target="_blank">${postCard.user.username}</a></span></li>
							<li><span class="gray">预寄：</span><span>${postCard.lovenumber}张</span><span class="gray2">（已确认<s:property value="participates.size()"/>人）</span></li>
							<li class="joinevent">
							<span id="enter_act_span">
								我已参加</span>						
							</li>
						</ul>
						</div>
    		     
    		    </div>
    		    <div class="detailinfo">
						<h3>楼主说 · · · · · ·</h3>
						<p>${postCard.lovecontent}</p>
					</div>
					<div class="listbox">
					<h3 style="margin:0 0 20px 20px;"><a href="#msgbox" class="yui3-button fr">我要留言</a>大家的留言 · · · · · ·</h3>
					<s:if test="loveComments!=null&&loveComments.size()!=0">
					<s:iterator value="loveComments" var="lc" status="i">
					<div class="list clearfix ">
							<div class="limg">
							<a href="${pageContext.request.contextPath}/lovecard_goToUserPostCardSpace.php?userid=${lc.user.userid}" target="_blank">
								<img src="${lc.user.headerimage}" alt="${lc.user.username}" width="50" height="50">
									</a>
							</div>
							<ul class="ltext">
								<li><span class="gray2 fr"><s:property value="#i.index+1"/>#</span><a href="${pageContext.request.contextPath}/lovecard_goToUserPostCardSpace.php?userid=${lc.user.userid}" target="_blank">${lc.user.username}</a></li>
								<li class="content">${lc.lovecommentcontent}</li>
								<li class="gray2 last">
									<span><s:date format="yyyy-MM-dd HH:mm:ss" name="#lc.lovedate"/></span> 
									<a href="#msgbox" onclick="replyComment('${lc.user.username}')" class="gray2">回复</a>
									<s:if test="postCard.user.userid==currentUser.userid">
									<s:if test="#lc.isJoinIn==0">
									<s:if test="#lc.isChosen==0">
									<span id="enter_act_span">他/她已被选中</span>
									</s:if>
									<s:elseif test="#lc.isChosen==1">
									<a class="yui3-button fr" onclick="chooseHimOrHer('${lc.user.userid}',this)">选择他/她</a>
									</s:elseif>
									</s:if> 
									</s:if>
									</li>
							</ul>
						</div>
					</s:iterator>
					</s:if>
					<s:else>
					<div class="page">
					<span class="gray">赶快来参加活动吧，有更多的精美明信片在等着你哦！~亲</span>
					</div>
					</s:else>
					</div>
					<div class="messagebox" id="msgbox">
					<h3>大家的留言 · · · · · ·</h3>
					<form action="javascript:void(0);" method="post" id="cmtForm">
						<input type="hidden" name="postAddr" value="" id="postAddr">
						<input type="hidden" name="postcode" value="" id="postcode">
						<input type="hidden" name="receiver" value="" id="receiver">
						<input type="hidden" name="cmId" id="reply_user">
						<div style="height:20px;"><span class="red" id="show_mes"></span></div>
						<div>
						<ul class="inputbox">
							<li>
								<textarea class="typearea2 gray" name="content" id="cmt_text" onfocus="checkCommentUserStatus()">想说的话</textarea>
							</li>
							<li id="p_address_box">
								<input type="text" class="textinputl gray" value="收件地址" id="addr_input"> <input type="text" class="textinputs gray" value="邮编" id="code_input"> <input type="text" class="textinputs gray" value="收件人名称" id="user_input">
							</li>
							<li class="inputbutton"><span class="gray2">你的地址不会对外公布，只有在楼主选择你为收件人的时候，才会对他显示。</span><a href="javascript:void(0);" onclick="submitComment()" class="yui3-button">发表</a></li>
						</ul>
						</div>
					</form>
					</div>
    		    </div>	
    		    <div class="yui3-u" id="sider">
					<div class="content">
						<div class="idlistbox clearfix">
						<h3 id="choose_box">这些人被楼主选中 :<a href="lovecard_showAllChosenUser.do?loveid=${postCard.loveid}">共<span class="choseNum"><s:property value="participates.size()"/></span>人</a></h3>
						<s:if test="participates!=null">
						<s:iterator value="participates" var="pp">
						<div class="idlist clearfix">
								<div class="ilimg">
								<a href="${pageContext.request.contextPath}/lovecard_goToUserPostCardSpace.php?userid=${pp.user.userid}" target="_blank">
									<img src="${pp.user.headerimage}" alt="${pp.user.username}" width="50" height="50"></a>
								</div>
								<ul class="iltext">
									<li class="nick_txt"><a href="${pageContext.request.contextPath}/lovecard_goToUserPostCardSpace.php?userid=${pp.user.userid}" target="_blank">${pp.user.username}</a></li>
									<li><span class="gray">(&nbsp;已签&nbsp;)</span></li>
								</ul>
							</div>
							<div style="clear: both;" id="post_num_div">
							<br>
							</div>
						</s:iterator>
						</s:if>
						<s:else>
						<div style="clear: both;" id="post_num_div">
							<span class="gray" style="line-height:22px;">可能楼主还在纠结中？<br>快用你们的热情把楼主从楼上轰下来吧</span>
							</div>
						</s:else>
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