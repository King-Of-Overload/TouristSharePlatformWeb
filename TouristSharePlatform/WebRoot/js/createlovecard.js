$(function(){//jQuery入口函数
	$('#act_deadline').DatePicker({//初始化日期选择器
		  startDate: moment(),
		  format: 'YYYY-MM-DD', //Display date format
		  modalMode:false
		});
});//jQuery入口函数
//表单验证
function checkForm(){
	var lovetitle=$('#act_title').val();//标题
	var lovedescription=$('#act_desc').val();//内容
	var pic=$('input[name=cover]').val();//图片
	var lovenumber=$('#act_post_num').val();//张数
	var deadline=$('#act_deadline').val();//截止日期
	var lovetags=$('#act_tags').val();//标签
	if(lovetitle==''||lovetitle==null){
		$("body").overhang({
      type: "error",
      message: "小源提示：标题不可以为空哦",
      closeConfirm: "true",
      duration:2
      });
		return false;
	}else if(lovedescription==''||lovedescription==null){
		$("body").overhang({
      type: "error",
      message: "小源提示：写点什么吧",
      closeConfirm: "true",
      duration:2
      });
		return false;
	}else if(pic==''||pic==null){
		$("body").overhang({
      type: "error",
      message: "小源提示：上传一张美美哒封面吧",
      closeConfirm: "true",
      duration:2
      });
		return false;
	}else if(lovenumber==''||lovenumber==null){
		$("body").overhang({
      type: "error",
      message: "小源提示：你打算寄几张呀",
      closeConfirm: "true",
      duration:2
      });
		return false;
	}else if(isNaN(lovenumber)){
		$("body").overhang({
      type: "error",
      message: "小源提示：数字格式不正确哦",
      closeConfirm: "true",
      duration:2
      });
		return false;
	}else if(lovetags==''||lovetags==null){
		$("body").overhang({
      type: "error",
      message: "小源提示：打点标签，方便别人找到你的活动 ",
      closeConfirm: "true",
      duration:2
      });
		return false;
	}
	return true;
}