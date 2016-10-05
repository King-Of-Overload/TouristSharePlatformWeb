			$(function(){
				// 初始化插件
				$("#zyupload").zyUpload({
					width            :   "1024px",                 // 宽度
					height           :   "768px",                 // 宽度
					itemWidth        :   "140px",                 // 文件项的宽度
					itemHeight       :   "115px",                 // 文件项的高度
					url              :   "/TouristSharePlatform/UploadImageServlet",  // 上传文件的路径
					fileType         :   ["jpg","png","gif","jpeg","bmp"],// 上传文件的类型
					fileSize         :   51200000,                // 上传文件的大小
					multiple         :   true,                    // 是否可以多个文件上传
					dragDrop         :   true,                    // 是否可以拖动上传文件
					tailor           :   false,                    // 是否可以裁剪图片
					del              :   true,                    // 是否可以删除文件
					finishDel        :   false,  				  // 是否在上传文件完成后删除预览
					/* 外部获得的回调接口 */
					onSelect: function(selectFiles, allFiles){    // 选择文件的回调方法  selectFile:当前选中的文件  allFiles:还没上传的全部文件
						console.info("当前选择了以下文件：");
						console.info(selectFiles);
					},
					onDelete: function(file, files){              // 删除一个文件的回调方法 file:当前删除的文件  files:删除之后的文件
						console.info("当前删除了此文件：");
						console.info(file.name);
					},
					onSuccess: function(file, response){          // 文件上传成功的回调方法
						        swal({title:'恭喜小主',text:'照片上传成功',type:'success',confirmButtonText: '保存相册'}).then(function(isConfirm) {
                                	  if (isConfirm) {
                  var albumName=$(".selectedType a").html();//相册名
                  var username=decodeURIComponent(sessionStorage.username);//用户名
                  
                 $.ajax({
                url:'photo_uploadImages.do',
                type:'POST',
                async:true,
                cache:false,
                data:{
                	username:username,
                	albumName:albumName
                },
                success:function(response){
                	var data= eval('('+response+')');
                	if(data.message=="savesuccess"){
                		swal('恭喜','数据保存成功','success');
                	}else if(data.message=="saveerror"){
                		swal('啊哦','服务器傲娇了，请稍后再试','error');
                		}
                },
                error:function(response){
                    console.log(response);
                }
            });
                                		  }
                                		});
					},
					onFailure: function(file, response){          // 文件上传失败的回调方法
						console.info("此文件上传失败：");
						console.info(file.name);
					},
					onComplete: function(response){           	  // 上传完成的回调方法
						console.info("文件上传完成");
						console.info(response);
					}
				});
				
			});