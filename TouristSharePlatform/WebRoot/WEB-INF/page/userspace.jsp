<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
    <!Doctype html>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta charset="utf-8">
<title>芳草寻源-用户空间</title>
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="libs/highlight/styles/default.css" rel="stylesheet" />
<link href="css/base.css" rel="stylesheet">
<script src="libs/jquery.min.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script src="js/base.js"></script>
<link href="css/userspace.css" rel="stylesheet">
    <!-- 对话框特效 -->
<link rel="stylesheet" href="css/sweetalert2.css">
<script src="js/sweetalert2.js"></script>
<!--[if lt IE 9]>
<script src="js/modernizr.min.js"></script>
<![endif]-->
<!-- 文章解析区核心代码，不要随意修改，需要修改请咨询@Alan -->
<script src="ueditor.parse.js"></script>
<script src="js/userspace/userspace.js"></script>
</head>
<body>
<%@ include file="navbar.jsp" %>
 <div class="mainContent">
    <aside>
      <div class="avatar">
        <a href="javascript:void(0);" style="background-image: url('<s:property value='existUser.headerimage'/>');"><span><s:property value='existUser.username'/></span></a>
      </div>
      <section class="topspaceinfo">
        <h1><s:property value='existUser.username'/>的个人空间</h1>
        <input type="hidden" value="<s:property value='existUser.userid'/>" class="hiddenUserId"/>
        <p>于千万人之中，我遇见了我所遇见的人....</p>
      </section>
      <div class="userinfo"> 
      <p> <div class="button-tool" style="width: 300px;">
        <s:if test="currentIdentity=='visitor'">
                    <s:if test="focusStatus=='关注'">
                     <button class="mark btn btn-primary" id="focus">关注</button>
                    </s:if>
                    <s:elseif test="focusStatus=='已关注'">
                   <button class="mark btn btn-info" id="focus">取消关注</button>
                    </s:elseif>
                        <button class="chat btn btn-default" data-toggle="modal" data-target="#chatModal">私信</button>
                        
                    </s:if>
                <a href="${pageContext.request.contextPath}/tripuser_goToPersonalDoc.php?userid=${existUser.userid}"><button class="lookSetting btn btn-default" id="goToSetting">查看资料</button></a>
                    </div> </p>
                      
        <p class="q-fans">粉丝：<a href="javascript:void(0)"><s:property value='fansNumber'/></a>
        </p> 
        <p class="btns"><a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='existUser.userid'/>&page=1&requestType=skillacademy">技法</a><a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='existUser.userid'/>&page=1&requestType=album">相册</a><a href="/" target="_blank">装备</a></p>   
      </div>
      <section class="newpic">
         <h2>最新相册</h2>
         <ul>
         <s:if test="showAlbums!=null">
         <s:iterator value="showAlbums" var="sa">
           <li><a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.go?albumid=<s:property value='#sa.albumid'/>"><img src="<s:property value='#sa.photourl'/>"></a></li>
           </s:iterator>
         </s:if>
         <s:else><p>空空如也</p></s:else>
         </ul>
      </section>
    </aside>

    <div class="blogitem">
    <s:if test="requestType=='strategy'">
          <s:if test="stratiges.list!=null">
    <s:iterator value="stratiges.list" var="st">
  <article>
        <h2 class="title"><a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=<s:property value='#st.ustrategyid'/>"><s:property value='#st.ustrategyname'/></a></h2>
        <ul class="text">
        <s:property value="#st.ustrategycontent" escapeHtml="false"/>
        </ul>
        <div class="textfoot">
          <a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=<s:property value='#st.ustrategyid'/>">阅读全文</a>
        </div>
</article>
      </s:iterator>
    </s:if>
    <s:else>
                  空空如也
    </s:else>
    <div class="pages">
    <s:if test="stratiges.page!=1">
    <a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='existUser.userid'/>&page=<s:property value='stratiges.page-1'/>&requestType=strategy" class="next">&lt;&lt;上一页</a>
    </s:if>
    <s:iterator begin="1" end="stratiges.totalPage" var="i">
    <s:if test="stratiges.page!=#i">
      <a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='existUser.userid'/>&page=<s:property value='#i'/>&requestType=strategy" hidefocus=""><s:property value='#i'/></a>
    </s:if>
    <s:else>
       <span><s:property value="#i"/></span>
    </s:else>
    </s:iterator>
    <s:if test="stratiges.page!=stratiges.totalPage">
    <a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='existUser.userid'/>&page=<s:property value='stratiges.page+1'/>&requestType=strategy" class="next">下一页&gt;&gt;</a>
    </s:if>
    </div>
    </s:if>
    <s:elseif test="requestType=='album'">
    <s:if test="albumList.list!=null">
    <s:iterator value="albumList.list" var="alist">
     <article>
        <h2 class="title"><a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.go?albumid=<s:property value='#alist.albumid'/>"><s:property value='#alist.albumname'/></a></h2>
        <ul class="text">
        <li><s:property value="#alist.albumdescription" escapeHtml="false"/></li>
        <li><img alt="pic" src="<s:property value='#alist.coverImage'/>"></li>
        </ul>
        <div class="textfoot">
          <a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.go?albumid=<s:property value='#alist.albumid'/>">查看所有照片</a>
        </div>
</article>

    </s:iterator>
    </s:if>
    <s:else>
               空空如也
    </s:else>
    <div class="pages">
    <s:if test="albumList.page!=1">
    <a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='existUser.userid'/>&page=<s:property value='albumList.page-1'/>&requestType=album" class="next">&lt;&lt;上一页</a>
    </s:if>
    <s:iterator begin="1" end="albumList.totalPage" var="i">
    <s:if test="albumList.page!=#i">
      <a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='existUser.userid'/>&page=<s:property value='#i'/>&requestType=album" hidefocus=""><s:property value='#i'/></a>
    </s:if>
    <s:else>
       <span><s:property value="#i"/></span>
    </s:else>
    </s:iterator>
    <s:if test="albumList.page!=albumList.totalPage">
    <a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='existUser.userid'/>&page=<s:property value='albumList.page+1'/>&requestType=album" class="next">下一页&gt;&gt;</a>
    </s:if>
    </div>
    </s:elseif>
    <s:elseif test="requestType=='skillacademy'">
    <s:if test="academiesList.list!=null">
    <s:iterator value="academiesList.list" var="acList">
        <article>
        <h2 class="title"><a href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#acList.skilid'/>"><s:property value='#acList.skilltitle'/></a></h2>
        <ul class="text">
        <s:property value="#acList.skillcontent" escapeHtml="false"/>
        </ul>
        <div class="textfoot">
          <a href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#acList.skilid'/>">查看全文</a>
        </div>
</article>
    </s:iterator>
    </s:if>
    <s:else>
               空空如也
    </s:else>
        <div class="pages">
    <s:if test="academiesList.page!=1">
    <a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='existUser.userid'/>&page=<s:property value='academiesList.page-1'/>&requestType=skillacademy" class="next">&lt;&lt;上一页</a>
    </s:if>
    <s:iterator begin="1" end="academiesList.totalPage" var="i">
    <s:if test="academiesList.page!=#i">
      <a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='existUser.userid'/>&page=<s:property value='#i'/>&requestType=skillacademy" hidefocus=""><s:property value='#i'/></a>
    </s:if>
    <s:else>
       <span><s:property value="#i"/></span>
    </s:else>
    </s:iterator>
    <s:if test="albumList.page!=albumList.totalPage">
    <a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='existUser.userid'/>&page=<s:property value='albumList.page+1'/>&requestType=album" class="next">下一页&gt;&gt;</a>
    </s:if>
    </div>
    </s:elseif>
 </div>
 </div>
<footer>
   <div class="footavatar">
     <img src="<s:property value='existUser.headerimage'/>" class="footphoto">
     <ul class="footinfo">
       <p class="fname"><a href="javascript:void(0)"><s:property value='existUser.username'/></a></p>
       <p class="finfo">性别：<s:property value="existUser.sex"/> 联系方式：<s:property value="existUser.phone"/></p>
       <p>现居：<s:property value="existUser.province"/><s:property value="existUser.city"/>市</p></ul>
   </div>
   <section class="visitor">
     <h2>最近访客</h2>
      <ul>
      <s:if test="visitors!=null">
      <s:iterator value="visitors" var="v">
      <li><a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='#v.user.userid'/>&page=1"><img src="<s:property value='#v.user.headerimage'/>"></a></li>
      </s:iterator>
      </s:if>
      <s:else>
                   空空如也
      </s:else>
      </ul>
   </section>
   <div class="Copyright">
     <ul>
       <li><a href="${pageContext.request.contextPath}/officialstrategy_goToOfficialStrategy.php">官方攻略</a></li>
       <li><a href="${pageContext.request.contextPath}/userstrategy_goToUserStrategy.php">攻略大全</a></li>
       <li><a href="${pageContext.request.contextPath}/photo_goToPhoto.php">精彩相册</a></li>
       <li><a href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademy.php">技法学院</a></li>
     </ul>
     <p>Design by team of the beauty from the Fragrant grass</p>
   </div>
 </footer>
 <div style="text-align:center;">
<p>&copy Copyright芳草寻源小组倾情出品</p>
</div>


<!--模态框-->
<div class="modal fade" id="chatModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="ModalLabel">发送给<span class="aim">${existUser.username}</span>的悄悄话</h3>
            </div>
            <div class="modal-body">
                <textarea class="chatMessage form-control" rows="5" id="chatMessage"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="submit btn btn-primary" data-dismiss="modal" id="sendSecretWord">发送</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>