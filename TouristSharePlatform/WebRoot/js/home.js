/**
 * Created by ALan Lu on 2015/10/17.
 */
$(function(){
	/*摄影导航条*/
	$(".title-list li a").on('mouseover', function(event) {

		var culi = this;
		// 如果已经是当前标签，则返回
		if (culi && $(culi).parent().hasClass('active')) {
			return false;
		}
		var li = $(culi).parent();
		li.parent().children('li').removeClass('active');
		//显示触发事件的标签及内容区
		li.addClass('active');
		var tab = $("#" + li.data('tab'));
		tab.parent().children().removeClass('active');
		tab.addClass('active');
	});
	/*导航风信子部分*/
    $('#navigations').animate({ top: '-305px' }, 800);
	
	$('#navigations').hover(
		function(){ $(this).animate({ top: '0px' }, 1300);  },
		function(){ $(this).animate({ top: '-305px' }, 900 ); }
	);
	
	/*资讯轮播图*/
	$('#ca-container').contentcarousel({
	    // speed for the sliding animation
	    sliderSpeed: 500,
	    // easing for the sliding animation
	    sliderEasing: 'easeOutExpo',
	    // speed for the item animation (open / close)
	    itemSpeed : 500,
	    // easing for the item animation (open / close)
	    itemEasing  : 'easeOutExpo',
	    // number of items to scroll at a time
	    scroll: 1 
	});
	/*@start超链接初始化*/
	$(".navbar-left a").anchorHoverEffect({type: 'flip'}); 
	$(".username").anchorHoverEffect({type: 'borderBottom'});  
	$(".out").anchorHoverEffect({type: 'borderBottom'});
	$(".menu a").anchorHoverEffect({type: 'roller3d'});
	$(".a1 .a2").anchorHoverEffect({type: 'flip'});
	/*@end超链接初始化*/
	/*----------@start 轮播图 --------------- */
	$("#slide_fade").cxSlide({
		events:"mouseover",
		type:"fade",  /*可更换值  “x” 或 “y” 或 “fade” 或 “toggle” x滚动记得改样式*/
		speed:300
	});
	/*----------@end 轮播图 --------------- */
	
	initialAlbumNavWords();
});


function initialAlbumNavWords(){
	var xmlHttp=createXMLHttpRequest();
	xmlHttp.open('POST','getHottestTopFourAlbumTags.do',true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlHttp.send(null);
	xmlHttp.onreadystatechange=function(){
		if(xmlHttp.readyState==4&&xmlHttp.status==200){
			var result=xmlHttp.responseText;
			var tagNames=result.split(',');
			$('.albumOne').text(tagNames[0]);
			$('.albumTwo').text(tagNames[1]);
			$('.albumThree').text(tagNames[2]);
			$('.albumFour').text(tagNames[3]);
		}
	};
}


function createXMLHttpRequest(){
	try{
		return new XMLHttpRequest();
	}catch(e){
		try{
			return new ActiveXObject("Msxml2.XMLHTTP");
		}catch(e){
			try{
				return new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e){
				alert('大哥，您用的是什么浏览器啊');
				throw e;
			}
		}
	}
}

