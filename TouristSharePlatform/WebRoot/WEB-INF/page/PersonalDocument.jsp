<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
    <title>芳草寻源-${visitUser.username}の资料</title>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<link href="css/personaldoc/main.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="libs/bootstrap.min.css" rel="stylesheet" />
<link href="libs/font-awesome.min.css" rel="stylesheet" />
<link href="css/base.css" rel="stylesheet" />
<script src="libs/jquery.min.js"></script>
<script src="js/base.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" rel="script" src="js/personaldoc/index.js"></script>
<script type="text/javascript" rel="script" src="js/personaldoc/script.js"></script>
 <!-- 对话框特效 -->
<link rel="stylesheet" href="css/sweetalert2.css">
<script src="js/sweetalert2.js"></script>
<!-- 省市联动组件相关 -->
 <link href="css/city-picker.css" rel="stylesheet">
 <script src="js/city-picker.data.js"></script>
<script src="js/city-picker.js"></script>
<script src="js/personaldoc/personaldoc.js"></script>
 <link href="css/user.css" rel="stylesheet">
 <!-- 推荐用户展示相关 -->
<link href="css/owl.carousel.css" rel="stylesheet">
<link href="css/owl.theme.css" rel="stylesheet">
<script src="js/owl.carousel.js"></script>
<script src="js/jquery-sinaEmotion-2.1.0.min.js"></script>
 <link href="css/jquery-sinaEmotion-2.1.0.min.css" rel="stylesheet"/>
</head>
<body>
<!--<object style="border:0px" type="text/x-scriptlet" data="head.html" width=100% height=500px> </object>-->
<%@ include file="navbar.jsp" %>
<div class="mycontainer">
<input type="hidden" value="${visitUser.userid}" class="spaceUserId"/>
<input type="hidden" value="${currentUser.userid}" class="spaceCurrentUserId"/>
<div class="top">
    <div class="myself-wrap">
        <div class="mywelcome">
            欢迎来到 ${visitUser.username} 的个人空间
        </div>
    </div>
    <div class="head">
        <div class="saying">
           <s:if test="visitUser.usignature!=null">
           <s:property value="visitUser.usignature"/>
           </s:if>
           <s:else>
                                  主人有点懒哦
           </s:else>
        </div>
        <div class="portrait">
            <span></span><img src="${visitUser.headerimage}">
        </div>
    </div>
</div>

<div class="content-nav">
    <ul class="nav">
        <li><a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=${visitUser.userid}&page=1&requestType=strategy"><img src="images/personaldoc/home.png"><br>首页</a></li>
        <li><a href="#m-info"><img src="images/personaldoc/info.png"><br>个人资料</a></li>
        <li><a href="#m-life"><img src="images/personaldoc/view.png"><br>路上风景</a></li>
        <li><a href="#m-diary"><img src="images/personaldoc/dairy.png"><br>更新攻略&&相册</a></li>
        <li><a href="#m-word"><img src="images/personaldoc/dia.png"><br>悄悄话</a></li>
    </ul>
</div>
<div class="mainbg1">
    <a name="m-info"></a>
    <div class="content-intro">
        <div class="intro-wrapper">
        <input type="hidden" value="${visitUser.userid}" class="hiddenUserid"/>
            <div class="content-title">个人资料
            <s:if test="visitUser.userid==currentUser.userid">
            <button class="btn btn-default" style="float: right;" data-toggle="modal" data-target="#newDocModal">修改资料</button>
            </s:if>
            </div>
            <div class="sidebottom">
                <a class="fir-bu" style="float: right;">基本信息</a>
                <table cellpadding="6">
                    <tr>
                        <td>昵称:</td>
                        <td class="tdUsername">${visitUser.username}</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>电子邮箱:</td>
                        <td class="tdEmail">${visitUser.useremail}</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td>性别: </td>
                        <td class="tdSex">
                        <s:if test="visitUser.sex!=null">
                         <s:property value="visitUser.sex"/>
                        </s:if>
                        <s:else>
                                                                                           未知
                        </s:else>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>联系电话:</td>
                        <td class="tdPhone">
                         <s:if test="visitUser.phone!=null">
                         <s:property value="visitUser.phone"/>
                        </s:if>
                        <s:else>
                                                                                           未知
                        </s:else>
                        </td>
                    </tr>
                    <tr>
                        <td class="sec-hob"></td>
                        <td class="sec-hob"></td>
                        <td></td>
                        <td></td>
                        <td>所在城市:</td>
                        <td class="tdCity">${visitUser.city.province.provincename}&nbsp;${visitUser.city.cityname}</td>
                    </tr>
                </table>
            </div>

        </div>
    </div>
</div>
    <!------------------------路上 风景------------------------>
<div class="mainbg2">
    <a name="m-life"></a>
    <div class="content-life">
        <div class="intro-wrapper">
            <div class="content-title">路上风景</div>
            <s:if test="viewRBean!=null">
            <div class="lifetime">
                <ul id="tag">
                <s:iterator value="viewRBean" var="vc">
                 <li>
                  <hr><br><span><s:property value="#vc.cityName"/></span>
                    </li>
                </s:iterator>
                </ul>
                <div id="tagContent">
                <s:iterator value="viewRBean" var="vb">
                <div class="lifecon">
                <s:iterator value="#vb.list" var="vbl">
                <s:if test="#vbl.type=='strategy'">
                 <ul class="life-pic">
                            <a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${vbl.sid}" target="_blank"><img src="${vbl.coverImage}" width="290px" height="200px"/></a><br>
                             <s:property value="#vbl.sname"/>
                        </ul>
                </s:if>
                <s:elseif test="#vbl.type=='album'">
                 <ul class="life-pic">
                            <a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${vbl.sid}" target="_blank"><img src="${vbl.coverImage}" width="290px" height="200px"/></a><br>
                            <s:property value="#vbl.sname"/>
                        </ul>
                </s:elseif>
                </s:iterator>
                    </div>
                </s:iterator>
                </div>
            </div>
             </s:if>
             <s:else>
               <div class="lifetime">
               <span>主人还没有去过其他地方呢</span>
               </div>
             </s:else>
        </div>
    </div>
</div>
    <!------------------------足迹------------------------>
<div class="mainbg3">
    <a name="m-diary"></a>
    <div class="content-diary">
        <div class="diary-wrapper">
            <div class="content-title">个人足迹</div>
            <div class="diary-main">
                     <s:if test="footList!=null">
            <s:iterator value="footList" var="fl">
                            <div class="diary-year">
                    <div class="years">
                        <a href="javascript:void(0);">${fl.year}<i></i></a>
                    </div>
                    <div class="diary-list">
                        <ul>
                        <s:iterator value="#fl.list" var="flist">
                        <s:if test="#flist.type=='strategy'">
                         <li class="cls highlight">
                                <p class="date"><s:date name="#flist.monthAndDay" format="MM月dd日"/></p>
                                <p class="intro"><a target="_blank" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${flist.pid}">${flist.name}</a></p>
                                <p class="version">&nbsp;</p>
                                <div class="more">
                                    <p>${flist.description}</p>
                                </div>
                            </li>
                        </s:if>
                        <s:elseif test="#flist.type=='album'">
                          <li class="cls">
                                <p class="date"><s:date name="#flist.monthAndDay" format="MM月dd日"/></p>
                                <p class="intro"><a target="_blank" href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${flist.pid}">${flist.name}</a></p>
                                <p class="version">&nbsp;</p>
                                <div class="more">
                                    <p>${flist.description}</p>
                                </div>
                            </li>
                        </s:elseif>
                        <s:elseif test="#flist.type=='skillacademy'">
                         <li class="cls">
                                <p class="date"><s:date name="#flist.monthAndDay" format="MM月dd日"/></p>
                                <p class="intro"><a target="_blank" href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=${flist.pid}">${flist.name}</a></p>
                                <p class="version">&nbsp;</p>
                                <div class="more">
                                    <p>${flist.description}</p>
                                </div>
                            </li>
                        </s:elseif>
                        </s:iterator>
                        </ul>
                    </div>
                </div>
            </s:iterator>
            </s:if>
            <s:else>
            <span>空空如也</span>
            </s:else>
            </div>
        </div>
    </div>
</div>
<!-- 悄悄话部分 -->
<s:if test="visitUser.userid==currentUser.userid">
<div class="mainbg4">
    <a name="m-word"></a>
    <div class="content-words">
        <div class="words-wrapper">
            <div class="content-title">悄悄话</div>
            <!-- 此处添加TODO: -->
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
            <li class="replies" role="presentation">
                <a href="#replies" aria-controls="replies" role="tab" data-toggle="tab">我的评论</a>
            </li>
             <li class="mycollection" role="presentation">
                <a href="#mycollection" aria-controls="mycollection" role="tab" data-toggle="tab">我的收藏</a>
            </li>
            <li class="myorder" role="presentation">
                <a href="#myorder" aria-controls="myorder" role="tab" data-toggle="tab">我的订单</a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="information">
                <div class="user-form">
                    <div class="userImg">
                        <img src="${visitUser.headerimage}" alt="用户头像"/>
                    </div>
                    <ul class="userInfo">
                        <li class="nickName">昵称:${visitUser.username}</li>
                        <li class="sex">性别:${visitUser.sex}</li>
                        <li class="phone">手机号:${visitUser.phone}</li>
                        <li class="email">邮箱号:${visitUser.useremail}</li>
                    </ul>
                </div>

            </div>
            <div role="tabpanel" class="tab-pane" id="relation">
                <h3 class="title-blue">我关注的人（<span class="focusNum">${focusCount}</span>）</h3>
                          <div class="list">
                <s:if test="focusUsers!=null">
                <s:iterator value="focusUsers" var="fu">
                       <a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=${fu.userid}&page=1&requestType=strategy" target="_blank">
                        <dl class="item">
                            <dt><img src="${fu.headerimage}" alt="头像"/></dt>
                        </dl>
                    </a>
                    </s:iterator>
                </s:if>
                 <s:else>
                 <span>空空如也</span>
                 </s:else>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="messages">
                <h3 class="title-blue">
                    未读通知(<span class="messageNum">${messages.size()}</span>) |
                    <button class="readAll btn btn-default btn-xs">全部标记为已读</button>
                    <button class="delete btn btn-default btn-xs">删除选中消息</button>
                </h3>
                <ul class="message-list list-group">
                <s:if test="messages!=null">
                <s:iterator value="messages" var="mg">
                <s:if test="#mg.isread==1">
                                 <li class="list-group-item">
                 <input type="checkbox" name="isRead" value="${mg.smessageid}"/>
                 <a href="javascript:void(0);" data-toggle="modal" data-target="#messageModal" class="leaveMessage" id="${mg.smessageid}">
        <span class="message-tag"><img alt="" src="${mg.sender.headerimage}" width="40px" height="40px" style="border-radius: 50%;"></span>
        <span class="message-content" id="${mg.message}"><s:property value="#mg.message.substring(0,15)+'……'"/></span>
        <span>留言者:</span>
        <span class="message-author">${mg.sender.username}</span>
         <span class="message-date pull-right">未读消息</span>
          </a>
        </li>
                </s:if>
                <s:elseif test="#mg.isread==0">
                                 <li class="list-group-item" style="opacity: 0.6;">
                 <input type="checkbox" name="isRead" value="${mg.smessageid}"/>
                 <a href="javascript:void(0);" data-toggle="modal" data-target="#messageModal" class="leaveMessage" id="${mg.smessageid}">
        <span class="message-tag"><img alt="" src="${mg.sender.headerimage}" width="40px" height="40px" style="border-radius: 50%;"></span>
        <span class="message-content" id="${mg.message}"><s:property value="#mg.message.substring(0,15)+'……'"/></span>
        <span>留言者:</span>
        <span class="message-author">${mg.sender.username}</span>
         <span class="message-date pull-right">已读消息</span>
          </a>
        </li>
                </s:elseif>
            </s:iterator>
                </s:if>
                <s:else>
                <li>没有留言</li>
                </s:else>
                </ul>
            </div>
            <div role="tabpanel" class="tab-pane" id="replies">
                <h3 class="title-blue">回复の消息（<span class="focusNum">12</span>）</h3>
                <ul>
                <li>
                <a href="" class="replyMessage" id="${mg.smessageid}">
        <span class="message-tag"><img alt="" src="${mg.sender.headerimage}" width="40px" height="40px" style="border-radius: 50%;"></span>
        <span class="message-content" id="${mg.message}"><s:property value="#mg.message.substring(0,15)+'……'"/></span>
        <span>留言者:</span>
        <span class="message-author">${mg.sender.username}</span>
          </a>
          </li>
          </ul>
            </div>
            
             <div role="tabpanel" class="tab-pane" id="mycollection">
                <h3 class="title-blue">我的收藏数目（<span class="collectNum">12</span>）</h3>
                <ul>
                <li>
                <a href="" class="replyMessage" id="${mg.smessageid}">
        <span class="message-content" id="${mg.message}">你收藏商品：</span>
        <span class="productName">产品名</span>
          </a>
          </li>
          </ul>
            </div>
            
            <div role="tabpanel" class="tab-pane" id="myorder">
                <h3 class="title-blue">我的订单（<span class="myorderNum">12</span>）</h3>
                <ul>
                <li>
                <a href="" class="orderMessage" id="">
        <span class="message-content" id="${mg.message}">订单号：</span>
        <span class="productName">&nbsp;您购买了:</span>
          </a>
           <span class="message-date pull-right"><button class="goPay btn btn-default">去付款</button></span>
          </li>
          </ul>
            </div>
        </div>
        <s:if test="visitUser.userid==currentUser.userid">
         <div class="userRecommendTitle" style="margin-top: 100px;">
        <h5 class="RecommendTitle">这些用户你可能感兴趣哦：</h5>
        </div>
        </s:if>
            <!-- 添加内容 -->
        </div>

    </div>
</div>
</s:if>
    <div class="totop"><a></a>
    </div>
    </div>
    <s:if test="visitUser.userid==currentUser.userid">
         <!--@start  推荐用户展示-->
    		<div class="demo testimonial-bg">
		        <div class="container mt50">
		            <div class="row">
		                <div class="col-md-12">
		                    <div id="testimonial-slider" class="owl-carousel">
		                    <s:if test="recommandUsers!=null">
		                    <s:iterator value="recommandUsers" var="rUser">
		                    <div class="testimonial">
		                            <div class="testimonial-profile">
		                                <a href="#"><img src="${rUser.headerimage}" alt=""></a>
		                            </div>
		                            <div class="testimonial-content">
		                                <h3 class="testimonial-title"><a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=${rUser.userid}&page=1&requestType=strategy" target="_blank">${rUser.username}</a></h3>
		                                <span class="testimonial-post">${rUser.sex}</span>
		                                <p class="testimonial-description">
		                                   <s:if test="#rUser.usignature==null||#rUser.usignature==''">
		                                                                                                           该用户有点懒哦，无个人签名
		                                   </s:if>
		                                   <s:else>
		                                   ${rUser.usignature}
		                                   </s:else>
		                                </p>
		                            </div>
		                        </div>
		                    </s:iterator>
		                    </s:if>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
    <!--@end  推荐用户展示-->
    </s:if>
    
    
    <!-- 模态框Modal -->
<div class="modal fade" id="orderModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">订单详情</h4>
            </div>
            <div class="modal-body">
                <label for="orderId" class="orderId">订单号：</label>
                  <label for="orderPName" class="orderPName">产品名：<a></a></label>
                   <label for="ordertime" class="orderTime">下单时间：</label>
                    <label for="address" class="orderAddress">地址：</label>
                    <label for="price" class="orderPrice">价格：</label>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
    
<!-- 模态框Modal -->
<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
             ………………
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">はい</button>
            </div>
        </div>
    </div>
</div>
    
                <!--模态框-->
<div class="modal fade" id="newDocModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="height:470px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="ModalLabel">个人信息设置</h3>
            </div>
            <div class="modal-body" style="height:340px;">
            <label for="nickName">昵称：</label>
                <textarea class="nickname form-control" rows="1" id="nickname"></textarea>
                <label for="usignature">个性签名：</label>
                <textarea class="usignature form-control" rows="1" id="usignature"></textarea>
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
                <label for="sex">性别：</label>
                <input type="radio" name="sex" checked="checked" value="男" id="sex"/>男
                <input type="radio" name="sex"  value="女" id="sex"/>女
                  <label for="phone" style="float:left;margin-top: 40px;">联系电话：</label>
                <textarea class="phone form-control" rows="1" id="phone"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="submit btn btn-primary" data-dismiss="modal" id="submitUserDoc">提交</button>
            </div>
        </div>
    </div>
</div>
    <div class="pfooter">
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