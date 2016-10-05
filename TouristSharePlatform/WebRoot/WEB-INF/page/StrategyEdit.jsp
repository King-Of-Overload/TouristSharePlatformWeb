<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta charset="UTF-8">
<title>芳草寻源-${existUser.username}写攻略</title>
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="libs/highlight/styles/default.css" rel="stylesheet" />
<link href="css/base.css" rel="stylesheet">
<link href="css/strategyEdit.css" rel="stylesheet">
<script src="libs/jquery.min.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script src="libs/EpicEditor/js/epiceditor.min.js"></script>
 <script src="js/base.js"></script>
<script src="js/strategyEdit.js"></script>
<link rel="stylesheet" href="libs/EpicEditor/themes/base/epiceditor.css">
<link rel="stylesheet" href="libs/EpicEditor/themes/preview/github.css">
<link rel="stylesheet" href="libs/EpicEditor/themes/editor/epic-light.css">
 <!-- 对话框特效 -->
<link rel="stylesheet" href="css/sweetalert2.css">
<script src="js/sweetalert2.js"></script>
<!-- 省市联动组件相关 -->
 <link href="css/city-picker.css" rel="stylesheet">
 <script src="js/city-picker.data.js"></script>
<script src="js/city-picker.js"></script>
<script src="js/ordercreate/ordercreate.js"></script>
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
				<h3>攻略&&游记标题</h3>
				<div class="title input-group">
					<div class="input-group-btn">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="selectedType">投稿到</span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li data-type="food"><a href="javascript:void(0);">美食</a></li>
							<li data-type="travel"><a href="javascript:void(0);">旅行</a></li>
							<li data-type="clothes"><a href="javascript:void(0);">衣服搭配</a></li>
							<li data-type="learn"><a href="javascript:void(0);">学习</a></li>
							<li data-type="other"><a href="javascript:void(0);">其他</a></li>
						</ul>
					</div>
					<!-- /btn-group -->
					<input type="text" class="form-control" aria-label="..." id="strategyName"/>
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
        <div class="modal-content" style="height:700px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="ModalLabel">游记&&攻略设置</h3>
            </div>
            <div class="modal-body" style="height:460px;">
            <label for="alBumName">攻略游记简介：</label>
                <textarea class="alBumName form-control" rows="1" id="coverstory"></textarea>
                <!-- @start 省市联动组件 -->
                 <label for="distpicker">请选择相关城市：</label>
              <div id="distpicker">
			   <div class="form-group"  style="float: left;">
				<div style="position: relative;">
					<input id="city-picker3" class="form-control" readonly type="text" value="江苏省/扬州市/广陵区" data-toggle="city-picker">						
				</div>
			</div>
			<div class="form-group">
				<button class="btn btn-warning" id="reset" type="button" style="margin-left: 10px;">重置</button>
			</div>
		</div>
                <!-- @end 省市联动组件 -->
                <!-- @start 标签选择 -->
                <div class="alBumLabel" style="float:left;margin-top: 10px;width:100%;">
                    <label for="l" style="float:left;">请选择标签(可多选):</label>
                <!-- @start 标签部分 -->
                <div class="pictures-categories" id="l">
	   <ul>
	   <s:iterator value="userStrategyTags" var="ust">
	   <li>
	  <a id="pictures-hotest" href="javascript:void(0);"><s:property value="#ust.ustrategytagname"/></a>
	</li>
	</s:iterator>
	</ul>
	</div>
                <!-- @end 标签部分 -->
                </div>
                 <!-- @end 标签选择 -->
            </div>
            <div class="modal-footer">
                <button type="button" class="submit btn btn-primary" data-dismiss="modal" id="submitStrategy">提交</button>
            </div>
        </div>
    </div>
</div>


</body>
</html>