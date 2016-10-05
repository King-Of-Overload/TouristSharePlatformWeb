<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta charset="UTF-8">
<title>芳草寻源-技法学院-发表文章</title>
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="libs/highlight/styles/default.css" rel="stylesheet" />
<link href="css/base.css" rel="stylesheet">
<link href="css/skillAcademyEdit.css" rel="stylesheet">
<script src="libs/jquery.min.js"></script>
<script src="libs/bootstrap.min.js"></script>
 <script src="js/base.js"></script>
  <!-- 对话框特效 -->
<link rel="stylesheet" href="css/sweetalert2.css">
<script src="js/sweetalert2.js"></script>

<script src="js/skillAcademyEdit.js"></script>
<script src="js/skillAcademyEdit/jquery.tagsinput.js"></script>
<link href="css/skillAcademyEdit/jquery.tagsinput.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
	<div class="content">
		<div class="content-header">
			<img src="<s:property value='existUser.headerimage'/>" alt="" />
			<h1>
				<a href="${pageContent.request.contextPath}/tripuser_goToSpace.php?userid=<s:property value='existUser.userid'/>" class="nickName"><s:property value="existUser.username"/></a>
			</h1>
		</div>
		<div class="blogEdit">
			<div class="blog-title">
				<h3>技法学院の文章标题</h3>
				<div class="title input-group">
					<div class="input-group-btn">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="selectedType">投稿到</span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
						<s:iterator value="categoryList" var="cl">
							<li data-type="food"><a href="javascript:void(0)"><s:property value='#cl.skillcategoryname'/></a></li>
							</s:iterator>
						</ul>
					</div>
					<!-- /btn-group -->
					<input type="text" class="form-control" aria-label="..." id="skillName"/>
				</div>
				<!-- /input-group -->
			</div>

             <form action="userstrategy_submitStrategyContents.do" method="post">
              <!-- 富文本编辑器 -->
              <!-- 加载编辑器的容器 -->
    <script id="container" name="content" type="text/plain">
        这里写你的初始化内容
    </script>
    			
    </form>
    <!-- 配置文件 -->
    <script type="text/javascript" src="ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="ueditor.all.js"></script>
              <!-- @end -->
              <button class="strategyBtn btn submit" data-toggle="modal" data-target="#newStrategyModal">完成编辑</button>
		</div>   
	</div>


            <!--模态框-->
<div class="modal fade" id="newStrategyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="height:750px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="ModalLabel">技法学院の文章相关设置</h3>
            </div>
            <div class="modal-body" style="height:100px;">
             <!-- @start 标签选择 -->
                <div class="alBumLabel" style="float:left;margin-top: 10px;width:100%;">
                    <label for="l" style="float:left;">请选择热门标签(单选): 不合适？单击此处：<a href="javascript:void(0)" class="changeHotTags" style="color:#EE7AE9">换一批</a></label>
                <!-- @start 标签部分 -->
                <div class="pictures-categories" id="hotCategories">
	   <ul>
	   <s:iterator value="hotTags" var="ht">
	   <li>
	  <a id="<s:property value="#ht.skillhottagid"/>" href="javascript:void(0)"><s:property value="#ht.skillhottagname"/></a>
	</li>
	</s:iterator>
	</ul>
	</div>
                <!-- @end 标签部分 -->
                </div>
                 <!-- @end 标签选择 -->
                                <!-- @start 标签选择 -->
                <div class="alBumLabel" style="float:left;margin-top: 10px;width:100%;">
                    <label for="l" style="float:left;">相关话题(可多选):</label>
                <!-- @start 标签部分 -->
                <div class="pictures-categories" id="relatedTopic">
	   <ul>
	   <li>
	  <p>空空如也，添加一些吧</p>
	</li>
	</ul>
	</div>
                <!-- @end 标签部分 -->
                </div>
                 <!-- @end 标签选择 -->
                 <!-- @start 添加标签处 -->
                 <p><label>没有适合的话题？在下面添加吧(可输入多个，以英文逗号隔开)</label>
			<input id="tags_1" type="text" class="tags" value="" /></p>
			 <!-- @end 添加标签处 -->
			  <div class="modal-footer">
                <button type="button" class="submit btn btn-primary" data-dismiss="modal" id="submitSkill">提交</button>
            </div>
		</div>

            </div>
           
        </div>
    </div>


</body>
</html>