$(function(){
  initialMyPostCardButton();
});
//发起寄片活动按钮点击事件
function createLoveCard(){
	if(sessionStorage.length==0){
		swal({
          title: '小源提示',
          text: '想要发起寄片活动？先登录吧',
          type: 'question',
          showCancelButton: true,
          confirmButtonText: '去登录',
          cancelButtonText: '再看看',
             }).then(function(isConfirm) {
            if (isConfirm === true) {
              location.href="tripuser_loginPage.php";
            } else if (isConfirm === false) {
              console.log('用户点击了取消');
            } 
       });
	}else{
    location.href="lovecard_goToCreateLoveCard.php";
  }
}


function initialMyPostCardButton(){
  if(sessionStorage.length==0){
    $('.lovenav .myPostCard').attr('href','javascript:void(0);');
    $('.lovenav .myPostCard').attr('onclick','myPostCard()');
  }else{
    $('.lovenav .myPostCard').attr('href','lovecard_goToUserPostCardSpace.php?userid='+sessionStorage.userid);
  }
}


function myPostCard(){
  swal({
          title: '小源提示',
          text: '回家吗？先登录吧',
          type: 'question',
          showCancelButton: true,
          confirmButtonText: '去登录',
          cancelButtonText: '再看看',
             }).then(function(isConfirm) {
            if (isConfirm === true) {
              location.href="tripuser_loginPage.php";
            } else if (isConfirm === false) {
              console.log('用户点击了取消');
            } 
       });
}