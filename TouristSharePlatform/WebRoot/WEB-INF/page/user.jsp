<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
    <meta charset="UTF-8">
    <title>芳草寻源-个人中心</title>
    <link href="libs/bootstrap.min.css" rel="stylesheet">
    <link href="libs/font-awesome.min.css" rel="stylesheet">
    <link href="libs/highlight/styles/default.css" rel="stylesheet"/>
    <link href="css/base.css" rel="stylesheet">
    <link href="css/user.css" rel="stylesheet">
    <script src="libs/jquery.min.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script src="js/base.js"></script>
<script src="js/user.js"></script>

<!-- 推荐用户展示相关 -->
<link href="css/owl.carousel.css" rel="stylesheet">
<link href="css/owl.theme.css" rel="stylesheet">
<script src="js/owl.carousel.js"></script>
</head>
<body>
<%@ include file="navbar_nomenu.jsp" %>
<div class="content">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li class="information active" role="presentation">
                <a href="#information" aria-controls="information" role="tab" data-toggle="tab">我的资料</a>
            </li>
            <li class="relation" role="presentation">
                <a href="#relation" aria-controls="relation" role="tab" data-toggle="tab">我的关系</a>
            </li>
            <li class="messages" role="presentation">
                <a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">我的消息</a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="information">
                <div class="user-form">
                    <div class="userImg">
                        <img src="<s:property value='model.headerimage'/>" alt="用户头像"/>
                    </div>
                    <ul class="userInfo">
                        <li class="nickName">昵称:<s:property value="model.username"/></li>
                        <li class="sex">性别:<s:property value="model.sex"/></li>
                        <li class="phone">手机号:<s:property value="model.phone"/></li>
                        <li class="email">邮箱号:<s:property value="model.useremail"/></li>
                    </ul>
                </div>

            </div>
            <div role="tabpanel" class="tab-pane" id="relation">
                <h3 class="title-blue">我关注的人（<span class="focusNum">0</span>）</h3>
                <ul class="focus-list list-group">
                </ul>
            </div>
            <div role="tabpanel" class="tab-pane" id="messages">
                <h3 class="title-blue">
                    未读通知(<span class="messageNum">0</span>) |
                    <button class="readAll btn btn-default btn-xs">全部标记为已读</button>
                    <button class="delete btn btn-default btn-xs">删除选中消息</button>
                </h3>
                <ul class="message-list list-group">
    
    <li class="list-group-item" data-toggle="modal" data-target="#messageModal">
        <input type="checkbox" name="isRead" />
        <span class="message-tag"></span>
        <span class="message-content">对不起，系统出错</span>
        <span class="message-author"></span>
        <span class="message-date pull-right"></span>
    </li>

                </ul>
            </div>
        </div>
        
        <div class="userRecommendTitle">
        <h5 class="RecommendTitle">这些用户你可能感兴趣哦：</h5>
        </div>
    <!--@start  推荐用户展示-->
    		<div class="demo testimonial-bg">
		        <div class="container mt50">
		            <div class="row">
		                <div class="col-md-12">
		                    <div id="testimonial-slider" class="owl-carousel">
		                        <div class="testimonial">
		                            <div class="testimonial-profile">
		                                <a href="#"><img src="images/headerImages/tangyurou/1.jpg" alt=""></a>
		                            </div>
		                            <div class="testimonial-content">
		                                <h3 class="testimonial-title"><a href="#">林月如</a></h3>
		                                <span class="testimonial-post">女</span>
		                                <p class="testimonial-description">
		                                我是月如我怕谁，擅长摄影，爱旅游，爱生活。
		                                </p>
		                                <ul class="social-links">
		                                     <li><a href="#" class="focuson"><img src="images/guanzhu.png" width="30px" height="30px" class="focusonImg" title="关注"/></a></li>
		                                    <li><a href="#" class="youji"><img src="images/youji.png" width="30px" height="30px" class="youjiImg" title="游记"/></a></li>
		                                    <li><a href="#" class="xiangce"><img src="images/xiangce.png" width="30px" height="30px" class="xiangceImg" title="相册"/></a></li>
		                                </ul>
		                            </div>
		                        </div>

		                        <div class="testimonial">
		                            <div class="testimonial-profile">
		                                <a href="#"><img src="images/headerImages/tangyurou/2.jpg" alt=""></a>
		                            </div>
		                            <div class="testimonial-content">
		                                <h3 class="testimonial-title"><a href="#">阿奴</a></h3>
		                                <span class="testimonial-post">女</span>
		                                <p class="testimonial-description">
		                                    阿奴就是我，谁敢惹老娘，老娘让你尝尝厉害
		                                </p>
		                                <ul class="social-links">
		                                     <li><a href="#" class="focuson"><img src="images/guanzhu.png" width="30px" height="30px" class="focusonImg" title="关注"/></a></li>
		                                    <li><a href="#" class="youji"><img src="images/youji.png" width="30px" height="30px" class="youjiImg" title="游记"/></a></li>
		                                    <li><a href="#" class="xiangce"><img src="images/xiangce.png" width="30px" height="30px" class="xiangceImg" title="相册"/></a></li>
		                                </ul>
		                            </div>
		                        </div>

		                        <div class="testimonial">
		                            <div class="testimonial-profile">
		                                <a href="#"><img src="images/headerImages/tangyurou/3.jpg" alt=""></a>
		                            </div>
		                            <div class="testimonial-content">
		                                <h3 class="testimonial-title"><a href="#">铃原爱蜜莉</a></h3>
		                                <span class="testimonial-post">女</span>
		                                <p class="testimonial-description">
		                                    拍片与生活，我还是喜欢拍片，我是蜜莉娅，爱好摄影，喜好小清新
		                                </p>
		                                <ul class="social-links">
		                                    <li><a href="#" class="focuson"><img src="images/guanzhu.png" width="30px" height="30px" class="focusonImg" title="关注"/></a></li>
		                                    <li><a href="#" class="youji"><img src="images/youji.png" width="30px" height="30px" class="youjiImg" title="游记"/></a></li>
		                                    <li><a href="#" class="xiangce"><img src="images/xiangce.png" width="30px" height="30px" class="xiangceImg" title="相册"/></a></li>

		                                </ul>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
    <!--@end  推荐用户展示-->
        </div>


<!--消息列表模板-->
<ul id="message-template" style="display:none;">
    <li class="list-group-item" data-toggle="modal" data-target="#messageModal">
        <input type="checkbox" name="isRead" />
        <span class="message-tag"></span>
        <span class="message-content">对不起，系统出错</span>
        <span class="message-author"></span>
        <span class="message-date pull-right"></span>
    </li>
</ul>

<!--关注列表模板-->
<ul id="marked-template" style="display:none;">
    <li class="list-group-item" data-toggle="modal" data-target="#markModal">
    </li>
</ul>



<!-- 模态框Modal -->
<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>


</body>
</html>