<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
    <meta charset="UTF-8">
    <title>芳草寻源-${singleStrategy.ustrategyname}-攻略详情</title>
    <link href="libs/bootstrap.min.css" rel="stylesheet">
    <link href="libs/font-awesome.min.css" rel="stylesheet">
    <link href="libs/highlight/styles/default.css" rel="stylesheet"/>
    <link href="css/base.css" rel="stylesheet">
    <link href="css/strategyInfo.css" rel="stylesheet">
    <script src="libs/jquery.min.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script src="js/base.js"></script>
<script src="ueditor.parse.js"></script>
<script src="js/strategyInfo.js"></script>
<link href="css/smohan.pop&share.css" rel="stylesheet"/>
<script src="js/smohan.pop&share.js"></script>
<link href="css/sinaFaceAndEffec.css" rel="stylesheet">
<script src="js/commentMain.js"></script>
<script src="js/sinaFaceAndEffec.js"></script>
<link href="css/commentMain.css" rel="stylesheet">
 <!-- 对话框特效 -->
<link rel="stylesheet" href="css/sweetalert2.css">
<script src="js/sweetalert2.js"></script>
<!-- 返回上页效果 -->
<script src="js/TweenMax.min.js"></script>                  
<script src="js/ScrollToPlugin.min.js"></script> 
<script src="js/jquery-sinaEmotion-2.1.0.min.js"></script>
 <link href="css/jquery-sinaEmotion-2.1.0.min.css" rel="stylesheet"/>
 <script type="text/javascript" src="js/FileSaver.js"></script>
<script type="text/javascript" src="js/jquery.wordexport.js"></script>
</head>
<body class="background-gray">
<%@ include file="navbar.jsp" %>
 <div class="notes-view-header" id="J-notes-view-header">
<div class="notes-cover-pic" id="J-notes-cover-pic">  <!-- -712.125 -->
<img alt="日照灯塔" id="notes-cover-img" src="<s:property value='imgSrc'/>" width="600" height="450" style="width: 1899px; height: 1424.25px; margin-left: -949.5px; margin-top: -400px;"/>
</div>
<div class="bg-mask"></div>
<div class="main-center clearfix">
<div class="public-side-toolbar" id="J_public-side-toolbar">
<div class="side-toolbar-wrapper">
<div class="side-toolbar-bg clearfix">
<span class="aside-btn share-bar bdshare_t" pb-id="lv4756759">
<span class="side-share-btn">
<img alt="分享" src="images/shareBtn.png" width="60px" height="60px" class="share" title="分享">
<span style="margin-left: 15px;"></span>
</span>
<a href="${pageContext.request.contextPath}/userstrategy_downloadStrategyImgs.go?strategyId=${singleStrategy.ustrategyid}" target="_blank" title="下载攻略相册集">
<span class="side-downCollections-btn">
<img alt="下载攻略相册集" src="images/showOneAlbum/package.png" width="60px" height="60px" class="share">
<span style="margin-left: 15px;"></span>
</span>
</a>
<span class="side-exportDoc-btn">
<img alt="导出攻略" src="images/showOneAlbum/exportword.png" width="60px" height="60px" class="exportWordDoc" title="导出攻略">
<span style="margin-left: 15px;"></span>
</span>
</span>
</div>
</div>
</div>
<div class="note-header-main">
<div class="notes-hd">
<h2><strong title="" style="color: #adff2f"><s:property value="singleStrategy.ustrategyname"/></strong></h2>
</div>
<div class="users-info-container">
<p class="users-info clearfix">
<a class="avatar" href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='singleStrategy.tripUser.userid'/>&page=1&requestType=strategy" target="_blank">
<img src="<s:property value='singleStrategy.tripUser.headerimage'/>" title="" alt="荷风絮雨No1"/>
</a>
<a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='singleStrategy.tripUser.userid'/>&page=1&requestType=strategy" class="uname">作者：<s:property value="singleStrategy.tripUser.username"/></a>
</p>
</div>
</div>
</div>
</div>
<div class="content">
<input type="hidden" class="currentStrategyId" value="${singleStrategy.ustrategyid}"/>
    <ol class="breadcrumb" style="background-color: #8DEEEE">
        <li>攻略</li>
        <li>
        <s:iterator value="singleStrategy.tags" var="stags">
        <s:iterator value="#stags" var="sg">
        <s:property value="#sg.ustrategytagname"/>&
        </s:iterator>
        </s:iterator>
        </li>
        <li>一天以内</li>
    </ol>
    <div class="strategyInfo-header">
        <h2><span class="title"><font color="#7ec0ee"><s:property value="singleStrategy.ustrategyname"/></font></span><small>by <a class="author" href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='singleStrategy.tripUser.userid'/>&page=1&requestType=strategy"><font color="#EE9A00"><s:property value="singleStrategy.tripUser.username"/></font></a></small></h2>
        <ul class="content-info">
            <li><i class="icon-time"></i><span class="date"><s:date name="singleStrategy.ustrategydate" format="yyyy-MM-dd" /></span></li>
            <li><i class="icon-eye-open"></i><span class="watched"><s:property value="singleStrategy.uclickednum"/></span></li>
            <li style="cursor:pointer;" id="upLi"><i class="icon-thumbs-up"></i><span class="up"><s:property value="singleStrategy.ulikecount"/></span></li>
            <li><i class=" icon-comment"></i><span class="comment">等待</span></li>
        </ul>
    </div>
    <div class="strategyInfo-body">
    <s:property value="singleStrategy.ustrategycontent" escape="false"/>
    </div>

   <div class="strategyInfo-comments">
                   		      <h3>评论(<span class="comment">99</span>)</h3>

           <div class="myComment" id="myComment">

        		<div id="content" style="width: 700px; height: auto;margin-left:100px">

			<div class="wrap">
				<div class="comment">
					<div class="head-face">
						<img src="<s:property value='singleStrategy.tripUser.headerimage'/>" class="messageHeaderImg"/>
						<p><s:property value="singleStrategy.tripUser.username"/></p>
					</div>
					<div class="messageContent">
						<div class="cont-box">
							<textarea class="text" placeholder="请输入..." id="commentContent"></textarea>
						</div>
						<div class="tools-box">
							<div class="operator-box-btn"><span class="face-icon"  >☺</span><span class="img-icon">▧</span></div>
							<div class="submit-btn"><input type="button" onClick="out()"value="提交评论" /></div>
						</div>
					</div>
				</div>
				<div id="info-show">
					<ul></ul>
				</div>
			</div>

            <nav>
            <ul class="pagination">
                
            </ul>
        </nav>
    </div> 

</div>


           <!--  <div class="adaptArea">
                <pre><span></span><p>&nbsp;</p></pre>
                <textarea maxlength="300"  placeholder="亲，评论几句吧~" required></textarea>
            </div>
            <p>
                <input type="button" class="submit btn btn-default" value="提交"/>
                <small class="limit">最多输入300字</small>
            </p> -->
        </div>
<!--评论modal-->
<div id="commentModal">
    <li class="list-group-item">
        <div>
            <a class="observer" href="#">author</a>
            <span class="comment-time">2015-6-1</span>
        </div>
        <p>这都被你发现了</p>
        <div class="reply"><a href="#myComment">回复</a></div>
    </li>
</div>
</div>

<!--模态框-->
<div class="modal fade" id="replyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="ModalLabel">回复<span class="aim"></span>的话</h3>
            </div>
            <div class="modal-body">
                <textarea class="chatMessage form-control" rows="5" id="replyMessage"></textarea>
            </div>
            <div class="modal-footer">
               <button type="button" class="btn btn-default" id="facemodal">表情</button>
                <button type="button" class="submit btn btn-primary" data-dismiss="modal" id="sendReplyWord">发送</button>
            </div>
        </div>
    </div>
</div>

<div id="shangxia">
  <div id="shang"></div>

  <div id="comt"></div>
  <div id="xia"></div>
</div>     


      
       <div class="sfooter" id="footerbar">
	<div class="container">
		<ul class="footer_nav">
		  <li><a href="${pageContext.request.contextPath}/index.php">芳草寻源首页</a></li>
		  <li><a href="${pageContext.request.contextPath}/officialstrategy_goToOfficialStrategy.php">官方攻略</a></li>
		  <li><a href="${pageContext.request.contextPath}/userstrategy_goToUserStrategy.php?page=1">攻略游记大全</a></li>
		  <li><a href="${pageContext.request.contextPath}/photo_goToPhoto.php">精美相册</a></li>
		  <li><a href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademy.php">技法学院与素材</a></li>
		  <li><a href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademy.php">旅游装备</a></li>
		  <li><a href="${pageContext.request.contextPath}/downloadPage/download.html">芳草寻源客户端</a></li>
		</ul>
		<p class="copy">Copyright &copy; 2016.芳草寻源小组倾情出品 </p>
   <p class="copy">Copyright &copy; 2016.Team of the beauty-from-the-Fragrant-grass All rights reserved</p>
   <p class="copy">Copyright &copy; 2016. 芳香 のある 源 すべての権利が確保した</p>
	</div>
</div>
</body>
</html>