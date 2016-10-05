<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head lang="UTF-8">
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<title>芳草寻源-寻源商城-发布商品</title>
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="css/base.css" rel="stylesheet">
<script src="libs/jquery.min.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script src="js/base.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<link href="css/publishproduct/publishproduct.css" rel="stylesheet">
<script src="js/publishproduct/publishproduct.js"></script>
<script src="js/jquery.nice-select.js"></script>
<script src="js/fastclick.js"></script>
<link href="css/nice-select.css" rel="stylesheet">
<script type="text/javascript" src="js/publishproduct/jquery.gradientify.js"></script>   
<!-- 省市联动组件相关 -->
 <link href="css/city-picker.css" rel="stylesheet">
 <script src="js/city-picker.data.js"></script>
<script src="js/city-picker.js"></script>
<!-- 顶层弹出层 Designed By Alan -->
<script src="libs/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/overhang.css" />
<script type="text/javascript" src="js/overhang.js"></script>                 
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="wrapper clearfix">
<div class="contentLeft">
	<div class="form_content">
    <form id="test" action="#" method="get">
    <fieldset>
        <legend>分类选择</legend>
        <div class="form-row">
            <div class="field-label"><label for="field1">选择主分类</label>:</div>
            <div class="field-widget">
               <select id="category" name="category" class="validate-selection" title="选择主分类">
               <s:if test="categoryList!=null">
               <s:iterator value="categoryList" var="cl">
               <option>${cl.pcategoryname}</option>
               </s:iterator>
               </s:if>
                </select>
            </div>
        </div>
        
        <div class="form-row">
            <div class="field-label"><label for="field2">选择次分类</label>:</div>
            <div class="field-widget">
            <select id="secondCategory" name="secondCategory" class="validate-selection" title="选择次分类">
               <s:if test="secondCategroyList!=null">
               <s:iterator value="secondCategroyList" var="sl">
               <option>${sl.pcsname}</option>
               </s:iterator>
               </s:if>
                </select>
            </div>
        </div>

        <div class="form-row">
        <div class="quality-bar clearfix">
        <div class="field-label"><label for="field2" class="quality-title">成色:</label></div>
    <ul class="quality-item">
                    <li data-id="100" class="select" quality="100">
                <span class="quality-text">全新</span>

            </li>
                    <li data-id="99" class="item2" quality="99">
                <span class="quality-text">99新</span>
            </li>
                    <li data-id="98" class="item3" quality="98">
                <span class="quality-text">98新</span>
            </li>
                    <li data-id="95" class="item4" quality="95">
                <span class="quality-text">95新</span>
            </li>
                    <li data-id="90" class="item5" quality="90">
                <span class="quality-text">90新</span>
            </li>
                    <li data-id="80" class="item6" quality="80">
                <span class="quality-text">80新及以下</span>
            </li>
            </ul>
</div>        
        </div>
        
    </fieldset>
    <fieldset>
        <legend>商品信息</legend>
                
       
        <div class="form-row">
            <div class="field-label"><label for="field4">产品名称</label>:</div>
            <div class="field-widget"><input name="productname" id="productname" class="required" title="输入产品名称"/></div>
        </div>
         <div class="form-row">
            <div class="field-label"><label for="field3">简要说明(不超过200字)</label>:</div>
            <div class="field-widget"><textarea class="required" maxlength="200" name="pdescription" id="pdescription"></textarea></div>
        </div>
        <div class="form-row">
        <div class="field-label"><label for="field3">价格</label>:</div>
            <div class="field-widget"> <div class="form-group field-goods-price">
<div class="input-wrap"><input type="text" id="shopprice" class="form-control" name="shopprice" value="单位(元)"></div>
</div></div>
        </div> 
        <div class="form-row">
        <div class="field-label"><label for="field3">参考价格</label>:</div>
        <div class="field-widget"><input type="text" id="marketprice" class="form-control" name="marketprice" value="单位(元)"></div>        
        </div>
        <div class="form-row">
         <!-- @start 省市联动组件 -->
                 <label for="distpicker">请选择所在城市：</label>
              <div id="distpicker">
			   <div class="form-group"  style="float: left;">
				<div style="position: relative;">
					<input id="city-picker3" class="form-control" readonly type="text" value="江苏省/扬州市/广陵区" data-toggle="city-picker">						
				</div>
			</div>
			
			<div class="field-widget"><div class="form-group">
				<button class="btn btn-warning" id="reset" type="button" style="margin-left: 20px;">重置</button>
			</div></div>
		</div>
                <!-- @end 省市联动组件 -->
        </div>
    </fieldset>  
    <fieldset>
        <legend class="optional">额外信息</legend>
      
        
        <div class="form-row">
            <div class="field-label"><label for="field9">手机号</label>:</div>
            <div class="field-label">
                <input name="contactnum" id="contactnum" class="optional" title="手机号" />		
            </div>
        </div>
        
    </fieldset>
    
    </form>
    </div>

</div>
<div class="contentRight">
<ul id="accordion" class="step-items ui-accordion ui-widget ui-helper-reset" role="tablist">
		<li class="step-item">
			<h3 class="step-title ui-accordion-header ui-state-default ui-accordion-header-active ui-state-active ui-corner-top ui-accordion-icons" role="tab" id="ui-id-1" aria-controls="ui-id-2" aria-selected="true" aria-expanded="true" tabindex="0"><span class="ui-accordion-header-icon ui-icon ui-icon-triangle-1-s"></span>
				<em>友情提示</em>
			</h3>
			<div class="step-content ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active" id="ui-id-2" aria-labelledby="ui-id-1" role="tabpanel" aria-hidden="false" style="display: block;">
				<ul>
					<li>
						<h4>1.匹配的类目</h4>
						<p>选择精准的类目可使买家更准确的找到您的商品；</p>
					</li>
					<li>
						<h4>2.简洁的标题</h4>
						<p>吸引人的标题是增加商品点击率的关键~标题尽量简明扼要，准确的描述出品牌、型号、版本、成色、附件、保修等信息，使买家一目了然。</p>
					</li>
					<li>
						<h4>3.准确的成色</h4>
						<p>选择正确的成色使得交易无纠纷</p>
					</li>
					<li>
						<h4>4.合理的定价</h4>
						<p>您可以再网上搜索该商品，参考其他卖家的商品，合理定价。</p>
					</li>
					<li>
						<h4>5.详细介绍描述清晰</h4>
						<p>您可添加图文并茂的商品介绍，也可以上传视频文件或是附件进行辅助说明</p>
					</li>
				</ul>
			</div>
		</li>
	</ul>
</div>

<div style="float: left;">
<fieldset style="background: url('images/publishproduct/center_bg2.gif');"><legend class="optional">详细产品描述</legend>
<!-- 富文本编辑器 -->
              <!-- 加载编辑器的容器 -->
    <script id="container" name="content" type="text/plain">
        这里写你的初始化内容
    </script>
        <!-- 配置文件 -->
    <script type="text/javascript" src="ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="ueditor.all.js"></script>
</fieldset>

    <button class="strategyBtn btn submit" style="margin-top: 20px;" id="submitProduct">提交商品信息</button>
</div>
</div>
<div class="footer">
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