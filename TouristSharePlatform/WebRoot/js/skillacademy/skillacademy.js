$(function(){
	$(".con ul li").hover(function(){
		$(this).find(".txt").stop().animate({height:"198px"},400);
		$(this).find(".txt h3").stop().animate({paddingTop:"60px"},400);
	},function(){
		$(this).find(".txt").stop().animate({height:"60px"},400);
		$(this).find(".txt h3").stop().animate({paddingTop:"0px"},400);
	})
});