$(document).ready(function () {
    /* @start 初始化瀑布流控件*/
	$("#gallery-wrapper").pinterest_grid({
		no_columns: 4,
        padding_x: 10,
        padding_y: 10,
        margin_bottom: 50,
        single_column_breakpoint: 700
	});
    var $slider = $('.slider'), $slideBGs = $('.slide__bg'), diff = 0, curSlide = 0, numOfSlides = $('.slide').length - 1, animating = false, animTime = 500, autoSlideTimeout, autoSlideDelay = 6000, $pagination = $('.slider-pagi');
    function createBullets() {
        for (var i = 0; i < numOfSlides + 1; i++) {
            if (window.CP.shouldStopExecution(1)) {
                break;
            }
            var $li = $('<li class=\'slider-pagi__elem\'></li>');
            $li.addClass('slider-pagi__elem-' + i).data('page', i);
            if (!i)
                $li.addClass('active');
            $pagination.append($li);
        }
        window.CP.exitedLoop(1);
    };
    createBullets();
    function manageControls() {
        $('.slider-control').removeClass('inactive');
        if (!curSlide)
            $('.slider-control.left').addClass('inactive');
        if (curSlide === numOfSlides)
            $('.slider-control.right').addClass('inactive');
    }
    ;
    function autoSlide() {
        autoSlideTimeout = setTimeout(function () {
            curSlide++;
            if (curSlide > numOfSlides)
                curSlide = 0;
            changeSlides();
        }, autoSlideDelay);
    }
    ;
    autoSlide();
    function changeSlides(instant) {
        if (!instant) {
            animating = true;
            manageControls();
            $slider.addClass('animating');
            $slider.css('top');
            $('.slide').removeClass('active');
            $('.slide-' + curSlide).addClass('active');
            setTimeout(function () {
                $slider.removeClass('animating');
                animating = false;
            }, animTime);
        }
        window.clearTimeout(autoSlideTimeout);
        $('.slider-pagi__elem').removeClass('active');
        $('.slider-pagi__elem-' + curSlide).addClass('active');
        $slider.css('transform', 'translate3d(' + -curSlide * 100 + '%,0,0)');
        $slideBGs.css('transform', 'translate3d(' + curSlide * 50 + '%,0,0)');
        diff = 0;
        autoSlide();
    }
    function navigateLeft() {
        if (animating)
            return;
        if (curSlide > 0)
            curSlide--;
        changeSlides();
    }
    function navigateRight() {
        if (animating)
            return;
        if (curSlide < numOfSlides)
            curSlide++;
        changeSlides();
    }
    $(document).on('mousedown touchstart', '.slider', function (e) {
        if (animating)
            return;
        window.clearTimeout(autoSlideTimeout);
        var startX = e.pageX || e.originalEvent.touches[0].pageX, winW = $(window).width();
        diff = 0;
        $(document).on('mousemove touchmove', function (e) {
            var x = e.pageX || e.originalEvent.touches[0].pageX;
            diff = (startX - x) / winW * 70;
            if (!curSlide && diff < 0 || curSlide === numOfSlides && diff > 0)
                diff /= 2;
            $slider.css('transform', 'translate3d(' + (-curSlide * 100 - diff) + '%,0,0)');
            $slideBGs.css('transform', 'translate3d(' + (curSlide * 50 + diff / 2) + '%,0,0)');
        });
    });
    $(document).on('mouseup touchend', function (e) {
        $(document).off('mousemove touchmove');
        if (animating)
            return;
        if (!diff) {
            changeSlides(true);
            return;
        }
        if (diff > -8 && diff < 8) {
            changeSlides();
            return;
        }
        if (diff <= -8) {
            navigateLeft();
        }
        if (diff >= 8) {
            navigateRight();
        }
    });
    $(document).on('click', '.slider-control', function () {
        if ($(this).hasClass('left')) {
            navigateLeft();
        } else {
            navigateRight();
        }
    });
    $(document).on('click', '.slider-pagi__elem', function () {
        curSlide = $(this).data('page');
        changeSlides();
    });
   /* @end 初始化瀑布流控件*/

    
  //  getHotTags();/*相册tag处理*/
    /*@start 点击tag处理 jQuery*/
    $(".pictures-categories ul").on('click', 'a', function() {//本站推荐部分
        $(".pictures-categories ul a").removeClass("pictures-active");
        $(this).addClass('pictures-active');
    });
    $(".tags-categories ul a").bind('click',function(event) {
        $(".tags-categories ul a").removeClass("tags-active");
        $(this).addClass('tags-active');
    });
    /* @end 点击tag处理*/
});






/*@start 获取热门标签*/
    function getHotTags(){
    var tagsArray=new Array();
    var xmlHttp=getXmlHttpRequest();
    xmlHttp.open("POST","photo_getAllHotTags.do");
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xmlHttp.send(null);
    xmlHttp.onreadystatechange=function(){
        if(xmlHttp.readyState==4&&xmlHttp.status==200){
            var result=eval('('+xmlHttp.responseText+')');//获取jsonarray
            for(var i=0;i<result.length;i++){
                var jsonObject=result[i];
                var tagName=jsonObject.tagname;//标签名
                tagsArray[i]=tagName;
            }

    var tagsCategories=document.getElementsByClassName("tags-categories");//获取热门标签div
    tagsCategories[0].innerHTML="";
    var ulTag=document.createElement("ul");
    for(var i=0;i<tagsArray.length;i++){
        var tagName=tagsArray[i];
        var liTag=document.createElement("li");
        var aTag=document.createElement("a");//a标签
        aTag.setAttribute("name",tagName);
        aTag.setAttribute("href","#");
        var textNode=document.createTextNode(tagName);
        aTag.appendChild(textNode);
        liTag.appendChild(aTag);//在li下面加入a标签
        ulTag.appendChild(liTag);//将li加入ul下面
    }
    ulTag.childNodes[0].childNodes[0].setAttribute("class","tags-active");
        tagsCategories[0].appendChild(ulTag);

    };
   }
}
/* @end 获取热门标签*/



/*@start 获取xmlhttp 对象*/
function getXmlHttpRequest(){
  try{
    return new XMLHttpRequest();
  }catch(e){
    try{
        return new ActiveXObject("Msxml2.XMLHTTP");
    }catch(e){
        try{
            return new ActiveXObject("Microsoft.XMLHTTP");
        }catch(e){
            alert("大哥，您用的是什么浏览器啊");
        }
    }
  }
  return null;
}
/* @end 获取xmlhttp 对象*/
