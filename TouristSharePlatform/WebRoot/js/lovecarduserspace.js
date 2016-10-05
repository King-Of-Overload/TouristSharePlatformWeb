/**
 * @author Alan Lu
 */
$(function(){//jQuery入口函数
	initialNavTabs();
});//jQuery入口函数

//初始化tab文字
function initialNavTabs(){
	var ownerId=$('.ownerId').val();
	var userid=decodeURIComponent(sessionStorage.userid);
	if(ownerId==userid){
		$('.information a').text('我の动态');
		$('.relation a').text('我参与の活动');
		$('.messages a').text('我发起の活动');
	}else{
		var username=$('.ibtext li span').text();
		$('.information a').text(username+'の动态');
		$('.relation a').text(username+'参与の活动');
		$('.messages a').text(username+'发起の活动');
	}
}