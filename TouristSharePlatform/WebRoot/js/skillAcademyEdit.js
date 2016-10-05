$(function(){//jQuery入口
	/*@start投稿下拉菜单监听*/
	$(".title").on('click','li',function(){
	 				var categoryName=$(this).html();
	 				$(".selectedType").html(categoryName);
	 				var aN=$(".selectedType a").html();
	 });
	/*@end投稿下拉菜单监听*/

	/*@start 编辑器初始化*/
	 var ue = UE.getEditor('container',{//实例化对象
	 	autoHeight: true,
	 	toolbars: [['anchor','undo','redo','bold','indent','snapscreen','italic','underline','strikethrough','subscript','superscript','|','formatmatch','selectall','preview','horizontal','time','date','unlink','|',
	 	            'inserttable','insertrow','insertcol','mergeright', 'mergedown', 'deleterow', 'deletecol','splittorows', 'splittocols', 'splittocells', 'deletecaption','inserttitle', 'mergecells', 'deletetable','|',
	 	           'fontfamily', 'fontsize', 'paragraph','|',
	 	          'simpleupload','insertvideo','attachment','link','emotion','spechars','searchreplace','|',
	 	         'justifyleft','justifyright','justifycenter','justifyjustify', 'forecolor', 'backcolor','insertorderedlist', 'insertunorderedlist', 'fullscreen','|'],
	 	         ['rowspacingtop','rowspacingbottom','pagebreak','imageleft','imageright','imagecenter','autotypeset','touppercase','tolowercase','background','template','music','drafts','charts']],
	 	initialFrameHeight:500,
	 	autoHeightEnabled: false,
	 	autoFloatEnabled:true,
	 	scaleEnabled:true
	 });
	 //设置编辑器的内容
	 ue.ready(function() {
		    //设置编辑器的内容
		    ue.setContent('亲，请在此键入内容，注意排版哦，小源在上方为您准备了许多快速排版按钮，您也不希望自己的文章 写出来不漂亮吧( ^_^ )');
		    //获取html内容，返回: <p>hello</p>
		    var html = ue.getContent();
		    console.log(html);
		    //获取纯文本内容，返回: hello
		    var txt = ue.getContentTxt();
		    console.log(txt);
		});
	/*@end 编辑器初始化*/

    /*@start 点击tag处理 jQuery*/
	 $("#hotCategories ul a").eq(0).addClass("pictures-active");//第一项选中
    $("#hotCategories ul").on('click', 'a', function() {//本站推荐部分
	$("#hotCategories ul a").removeClass("pictures-active");
	$(this).addClass("pictures-active");
	var tagId=$(this).attr("id");
    $.ajax({
        url:'skillacademy_linkRelatedSecondTag.do',
        type:'POST',
        async:true,
        cache:false,
        data:{'tagId':tagId},
        success:function(response){
        	var data= eval('(' + response+ ')');
        	var hotUl=document.getElementById("relatedTopic").getElementsByTagName("ul")[0];
        	hotUl.innerHTML="";
        	if(data.tagName=="empty"){
        		var p=document.createElement("p");
        		var textNode=document.createTextNode("空空如也，添加一些吧");
        		p.appendChild(textNode);
        		hotUl.appendChild(p);
        	}else{

            	for(var i=0;i<data.length;i++){
            		var tagName=data[i].tagName;
            		var liEle=document.createElement("li");
            		var aEle=document.createElement("a");
            		aEle.setAttribute("id", data[i].tagId);
            		aEle.setAttribute("href", "javascript:void(0)");
            		var textNode=document.createTextNode(tagName);
            		aEle.appendChild(textNode);
            		liEle.setAttribute("id", data[i].tagId);
            		liEle.appendChild(aEle);
            		hotUl.appendChild(liEle);
            	}	
        	}
        },
        error:function(response){
            console.log(response);
        }
    });
});
/*@end 点击tag处理 jQuery*/
    
    /*@start 关联相关话题的点击监听事件*/
    $("#relatedTopic ul a").eq(0).addClass("pictures-active");//第一项选中
    $("#relatedTopic ul").on('click', 'a', function() {
    	if($(this).hasClass("pictures-active")){
    		$(this).removeClass("pictures-active");
    	}else{
    		$(this).addClass('pictures-active');
    	}	
    });
    /*@end 关联相关话题的点击监听事件*/

/*@start tag编辑初始化*/
$('#tags_1').tagsInput({
    width: 'auto',
    onChange: function (elem, elem_tags) {
        var languages = ['php', 'ruby', 'javascript'];
        $('.tag', elem_tags).each(function () {
            if ($(this).text().search(new RegExp('\\b(' + languages.join('|') + ')\\b')) >= 0)
                $(this).css('background-color', 'yellow');
        });
    }
});

/*@end tag编辑初始化*/

/*@start 换一批按钮点击事件*/
$(".changeHotTags").on("click",function(){
    $.ajax({
        url:'skillacademy_changeHotTags.do',
        type:'POST',
        async:true,
        cache:false,
        success:function(response){
        	var data= eval('(' + response+ ')');
        	var hotUl=document.getElementById("hotCategories").getElementsByTagName("ul")[0];
        	hotUl.innerHTML="";
        	for(var i=0;i<data.length;i++){
        		var tagName=data[i].tagName;
        		var liEle=document.createElement("li");
        		var aEle=document.createElement("a");
        		aEle.setAttribute("id", data[i].tagId);
        		aEle.setAttribute("href", "javascript:void(0)");
        		var textNode=document.createTextNode(tagName);
        		aEle.appendChild(textNode);
        		liEle.appendChild(aEle);
        		hotUl.appendChild(liEle);
        	}
        },
        error:function(response){
            console.log(response);
        }
    });
});
/*@start 换一批按钮点击事件*/

/*@start 提交按钮监听事件*/
$("#submitSkill").click(function(){
	var tougao="";//投稿内容,需要判断是否为空，需要使用
	if(!ue.hasContents()){//判断内容是否为空		
  		alert("文章内容不能为空");
  		return;
  	}
	if(typeof(tougao)!=undefined){//投稿内容
	     tougao=$(".selectedType a").html();
	  	}else{
	  		alert("投稿内容不能为空");
	  		return;
	  	}
	var skillName=document.getElementById("skillName");//文章标题
  	if(skillName.value==''){//判断文章标题是否填写
  		alert("文章标题不能为空");
  		return;
  	}
  	//读取热门标签
  	var hotDiv=document.getElementById("hotCategories");
  	var hotLabelContent=hotDiv.getElementsByClassName("pictures-active")[0];
  	var hotLabelContentId=hotLabelContent.getAttribute("id");
	//获取相关话题标签
	var chosenList=new Array();//相关话题数组
	var chosenListStr="";//最后处理好的相关话题字符串
	var userDefineRelatedTags="";//用户添加的tag
	var count=0;
	var relatedDiv=document.getElementById("relatedTopic");
	var realatedList=relatedDiv.getElementsByClassName("pictures-active");//数据库里原有的相关话题
		var tagsinputDiv=document.getElementsByClassName("tagsinput")[0];
		var tagsSpan=tagsinputDiv.getElementsByClassName("tag");//用户自定义标签
		
		//两个都空
		if(realatedList.length==0&&tagsSpan.length==0){
			alert("相关话题或自定义标签不得为空");
			return;
		}
		if(realatedList.length!=0&&tagsSpan.length!=0){//两个都不为空
			//处理原有相关话题
			for(var i=0;i<realatedList.length;i++){
			var relateTag=realatedList[i];
			if(relateTag.hasAttribute("class")){
				chosenList[count]=relateTag.innerText;
				count++;
			}
		}
    	//处理数组集合
    	for(var i=0;i<chosenList.length;i++){
    		var choseOne=chosenList[i];
    		chosenListStr+=choseOne+";";
    	}
    	chosenListStr=chosenListStr.substring(0,chosenListStr.length-1);//标签集合,需要使用
    	//用户自定义数据处理
    	for(var i=0;i<tagsSpan.length;i++){
				var tagSpan=tagsSpan[i];
				var subSpanContent=Trim(tagSpan.getElementsByTagName("span")[0].innerText,"g");
				userDefineRelatedTags+=subSpanContent+";";
			}
		userDefineRelatedTags=userDefineRelatedTags.substring(0, userDefineRelatedTags.length-1);
		}else{
	  if(realatedList.length!=0){//相关标签选择不为空
		for(var i=0;i<realatedList.length;i++){
			var relateTag=realatedList[i];
			if(relateTag.hasAttribute("class")){
				chosenList[count]=relateTag.innerText;
				count++;
			}
		}
    	//处理数组集合
    	for(var i=0;i<chosenList.length;i++){
    		var choseOne=chosenList[i];
    		chosenListStr+=choseOne+";";
    	}
    	chosenListStr=chosenListStr.substring(0,chosenListStr.length-1);//标签集合,需要使用
	}else{
		//TODO:读取自定义标签
			for(var i=0;i<tagsSpan.length;i++){
				var tagSpan=tagsSpan[i];
				var subSpanContent=Trim(tagSpan.getElementsByTagName("span")[0].innerText,"g");
				userDefineRelatedTags+=subSpanContent+";";
			}
			userDefineRelatedTags=userDefineRelatedTags.substring(0, userDefineRelatedTags.length-1);

	}
	}
	var htmlContent=ue.getContent();//html内容
  	var plainText=ue.getContentTxt();//纯文本
  	var username=sessionStorage.username//在客户端需要解密
    $.ajax({//异步请求
        url:'skillacademy_saveSkillAcademy.do',
        type:'POST',
        async:true,
        cache:false,
        data:{'tougao':tougao,'skillName':skillName.value,'hotLabelContentId':hotLabelContentId,'userDefineRelatedTags':userDefineRelatedTags,'htmlContent':htmlContent,'plainText':plainText,'username':username,'chosenListStr':chosenListStr},
        success:function(response){
        	var data= eval('(' + response+ ')');
        	//TODO:跳转到刚刚上传的文章界面
        	  swal({title:'恭喜小主',text:'文章发表成功',type:'success',confirmButtonText: 'はい'}).then(function(isConfirm) {
              	  if (isConfirm) {
              		   location.href='skillacademy_goToShowOneSkillAcademyArticle.go?skillId='+data.skillId;
             		  }
             		});
        },
        error:function(response){
            console.log(response);
        }
    });
  	
});
/*@end 提交按钮监听事件*/

});//jQuery入口
//去除字符串空格
function Trim(str,is_global)
{
    var result;
    result = str.replace(/(^\s+)|(\s+$)/g,"");
    if(is_global.toLowerCase()=="g")
    {
        result = result.replace(/\s/g,"");
     }
    return result;
}

/*@start 新增标签的添删改查*/
function onAddTag(tag) {
    alert("Added a tag: " + tag);
}
function onRemoveTag(tag) {
    alert("Removed a tag: " + tag);
}

function onChangeTag(input, tag) {
    alert("Changed a tag: " + tag);
}
/*@end 新增标签的添删改查*/