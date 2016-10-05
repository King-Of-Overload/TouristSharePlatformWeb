/**
 * @author Alan Lu
 * @beta 1.0
 * @description javascript for searchindex
 */
$(function(){//jQuery入口函数
   	$("#pic_list_1").cxScroll();
   	$("#academy-list").cxScroll();
   	var searchKey=$('.searchKey').val();
   	var page=1;
   	getUserStrategyData(searchKey,page);

});//jQuery入口函数

//分页页码点击事件
function listenPageClick(page){
	var searchKey=$('.searchKey').val();
	console.log('分页按钮点击后关键字:'+searchKey);
   	console.log('分页按钮点击后页码:'+page);
   	getUserStrategyData(searchKey,page);
}

//异步IO获取攻略数据
function getUserStrategyData(searchKey,page){
	console.log('接收到参数:'+searchKey||null);
	$.ajax({
		url:'acquireUserStrategyAsync.do',
		type:'POST',
		async:true,
		cache:false,
		data:{searchKey:searchKey,page:page},
		dataType:'json',
		success:function(response){
			var jsonData=response;
			console.log('已成功接收到数据');
			console.log('成功戻る');
			console.log('接收到的数据:'+response);
			console.log('开始解析');
			var totalPage=response.totalPage;//总页数
			console.log('总页数:'+totalPage);
			var limit=response.limit;//每页记录数
			console.log('每页记录数:'+limit);
			var page=response.page;//当前页码
			console.log('当前页码:'+page);
			var totalCount=response.totalCount;//总记录数
			console.log('总记录数'+totalCount);
			var strategyListArray=response.list;//攻略数据
			console.log('攻略数据长度'+strategyListArray.length);
			console.log('攻略数据:'+strategyListArray);
			console.log('开始修改DOM结构');
			if(strategyListArray.length==0||strategyListArray==null){
				$('.strategy span[class=more]').text('全部0篇游记&&攻略');
			}else{
                $('.strategy span[class=more]').text('全部'+totalCount+'篇游记&&攻略');
			}
			if(strategyListArray.length==0||strategyListArray==null){
				$('.pagelist-wrapper').empty();
				$('#J-wonderNotesWrap').empty();
				$('#J-wonderNotesWrap').append('<span>没有找到相关攻略游记哦</span>');
			}else{
				$('#J-wonderNotesWrap').empty();
				for(var i=0;i<strategyListArray.length;i++){
					var singleStrategy=strategyListArray[i];
					var uclickednum=singleStrategy.uclickednum;//点击量
					var commentNum=singleStrategy.commentNum;//评论数
					var ustrategyplaintext=singleStrategy.ustrategyplaintext;//描述
					var ustrategyname=singleStrategy.ustrategyname;//攻略名
					var coverImage=singleStrategy.coverImage;//封面
					var ustrategyid=singleStrategy.ustrategyid;//攻略id
					var isesense=singleStrategy.isesense;//是否精华
					console.log('点击量:'+uclickednum);
					console.log('评论数:'+commentNum);
					console.log('攻略名:'+ustrategyname);
					console.log('封面:'+coverImage);
					console.log('攻略id:'+ustrategyid);
					var htmlStr='<div class="notes-box"><div class="notes-pic"><a target="_blank" href=userstrategy_goToStrategyInfo.go?ustrategyid='
						+ustrategyid+' class="strategyHref">'
						+'<img alt="'+ustrategyname+'" src="'+coverImage+'" width="215" height="145"></a></div>'
						if(isesense==0){
							htmlStr=htmlStr+'<div class="notes-status"><span class="praised-icon icon-praised" title="">精</span></div>';
						}
						htmlStr=htmlStr+'<div class="notes-info"><a id="notes-title" target="_blank" title="" href="userstrategy_goToStrategyInfo.go?ustrategyid='+ustrategyid+'">'
						+ustrategyname+'</a>'
						+'<span class="comment-count"><i class="globel-iconfont icon-comment"></i>'+commentNum+'</span>'
						+'<span class="view-cont"><i class="globel-iconfont icon-eye-open"></i>'+uclickednum+'</span>'
						+'<p class="notes-content">'+ustrategyplaintext+'</p></div></div>';
						$('#J-wonderNotesWrap').append(htmlStr);
				}
				console.log('修改文章DOM结构完毕');
				console.log('开始修改分页DOM结构');
				var pageHtmlStr='<nav><ul class="pagination">';
				if(page!=1){
					pageHtmlStr=pageHtmlStr+'<li><a href="javascript:void(0);" id="1" onclick="listenPageClick(1)">首页</a></li>';
					pageHtmlStr=pageHtmlStr+'<li><a href="javascript:void(0);" id="'+parseInt(page-1)+'" onclick="listenPageClick('+parseInt(page-1)+')">上一页</a></li>';
				}
				if(page!=totalPage){
					pageHtmlStr=pageHtmlStr+'<li><a href="javascript:void(0);" id="'+parseInt(page+1)+'" onclick="listenPageClick('+parseInt(page+1)+')">下一页</a></li>'
					+'<li><a href="javascript:void(0);" id="'+totalPage+'" onclick="listenPageClick('+totalPage+')">尾页</a></li>';
				}
				pageHtmlStr=pageHtmlStr+'<li><span>当前页数/总页数：'+page+'/'+totalPage+'</span></li>'
				+'</ul></nav>';
				$('.pagelist-wrapper').empty();
				$('.pagelist-wrapper').append(pageHtmlStr);
			}
		},
		error:function(response){
			console.log('error');
			console.log('发生错误');
			console.log('エラーが発生');
		}
	});
}