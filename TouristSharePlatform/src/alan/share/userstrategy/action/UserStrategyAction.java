package alan.share.userstrategy.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import alan.share.officialstrategy.model.City;
import alan.share.officialstrategy.service.OfficialStrategyService;
import alan.share.photo.model.UserPhotos;
import alan.share.photo.service.PhotoService;
import alan.share.user.model.Comments;
import alan.share.user.model.TripUser;
import alan.share.user.service.TripUserService;
import alan.share.userstrategy.model.DisplayUserStrategy;
import alan.share.userstrategy.model.UserStrategy;
import alan.share.userstrategy.model.UserStrategyTag;
import alan.share.userstrategy.service.UserStrategyService;
import alan.share.utils.CookieUtil;
import alan.share.utils.DomUtil;
import alan.share.utils.PageBean;
import alan.share.utils.StringUtil;
import alan.share.utils.ZipImagesUtil;

/**
 * 用户游记攻略Action类
 * @author Alan
 *
 */
public class UserStrategyAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private UserStrategyService userStrategyService;
	private TripUserService tripUserService;
	private PhotoService photoService;
	private OfficialStrategyService officialService;
	
	
	

	public OfficialStrategyService getOfficialService() {
		return officialService;
	}

	public void setOfficialService(OfficialStrategyService officialService) {
		this.officialService = officialService;
	}

	public PhotoService getPhotoService() {
		return photoService;
	}

	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}

	public TripUserService getTripUserService() {
		return tripUserService;
	}

	public void setTripUserService(TripUserService tripUserService) {
		this.tripUserService = tripUserService;
	}

	public UserStrategyService getUserStrategyService() {
		return userStrategyService;
	}

	public void setUserStrategyService(UserStrategyService userStrategyService) {
		this.userStrategyService = userStrategyService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	//跳转到用户游记攻略页面
	public String goToUserStrategy() throws Exception {
		try {
			this.encodingReqAndRes();
			PageBean<UserStrategy> strategies=null;
			int page=Integer.parseInt(request.getParameter("page"));//主数据，一直存在
			String searchVal=request.getParameter("searchVal");//搜索关键字，根据用户名与文章内容,可能为空
			String strategyTag=request.getParameter("strategyTag");//文章分类tag，可能为全部，需要判断
			String startTime=request.getParameter("startTime");//开始时间,可能为null，表示全部
			String endTime=request.getParameter("endTime");//结束时间,可能为空，表示全部
			String recommandTag=request.getParameter("recommandTag");//本站推荐，可能为全部
			if(searchVal==null&&strategyTag==null&&startTime==null&&endTime==null&&recommandTag==null){//除page外参数为空
				strategies=userStrategyService.findAllUserStrategyByPagination(page);//要显示的数据
			}else{
				if(searchVal!=null){//纯粹使用搜索功能
					strategies=userStrategyService.findUserStrategyBySearchValueWithPagination(searchVal,page);
				}else{//其他，tag部分
					if(strategyTag.equals("全部")&&startTime.equals("null")&&endTime.equals("null")&&recommandTag.equals("全部")){
						strategies=userStrategyService.findAllUserStrategyByPagination(page);//要显示的数据	
					}else{
						//多条件搜索
						strategies=userStrategyService.findUserStrategyByMutipleConditionWithPagination(page,strategyTag,startTime,endTime,recommandTag);
					}
				}
			}
			//判断时间过滤条件
			Long startT=0L;
			Long endT=0L;
			String timeChosed="";
			System.out.println(startTime!=null&&endTime!=null);
			if((!"null".equals(startTime)&&!"null".equals(endTime))&&(startTime!=null&&endTime!=null)){
				startT=Long.parseLong(startTime);
				endT=Long.parseLong(endTime);
			}
			if(endT-startT==86400000){
				timeChosed="day";
			}else if(endT-startT==604800000){
				timeChosed="week";
			}else if(endT-startT==86400000*30){
				timeChosed="month";
			}else{
				timeChosed="all";
			}
			List<UserStrategy> list=strategies.getList();
			//List<UserStrategy> strategies=userStrategyService.findAllUserStrategy();
			List<DisplayUserStrategy> displayStrategy=new ArrayList<>();
			DisplayUserStrategy us=null;
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					UserStrategy u=list.get(i);
					us=new DisplayUserStrategy();
					us.setUstrategyid(u.getUstrategyid());
					us.setUstrategyname(u.getUstrategyname());
					String ustrategyContent=u.getUstrategycontent();//HTML文档
					Document docHtml=Jsoup.parse(ustrategyContent);
					Elements imgs=docHtml.getElementsByTag("img");//获取所有image标签
					List<String> imgList=new ArrayList<>();
					int count=0;
					for(Element e:imgs){
						if(count<3){
							String src=e.attr("src");
							imgList.add(src);
							count++;
						}
					}
					us.setImagesList(imgList);
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					us.setUstrategydate(format.parse(format.format(u.getUstrategydate())));
					us.setUclickednum(u.getUclickednum());
					us.setUlikecount(u.getUlikecount());
					us.setUstrategycoverstory(u.getUstrategycoverstory());
					us.setTripUser(u.getTripUser());
					us.setCity(u.getCity());
					String plainText=u.getUstrategyplaintext();
					String realPlainText="";
					if(plainText.length()<369){
						realPlainText=plainText;
					}else{
						realPlainText=plainText.substring(0, 369)+"…………";
					}
					us.setUstrategyplaintext(realPlainText);
					Iterator<UserStrategyTag> iterator=u.getTags().iterator();
					UserStrategyTag t=iterator.next();
					us.setTag(t.getUstrategytagname());
					displayStrategy.add(us);
				}
			}
			List<UserStrategyTag> tags=userStrategyService.findAllUserStrategyTags();
			//查找大家正在说
			List<Comments> comments=userStrategyService.findAllComments();
			ValueStack stack=ActionContext.getContext().getValueStack();
			stack.set("displayStrategies", displayStrategy);
			stack.set("tags", tags);
			stack.set("page", strategies.getPage());
			stack.set("totalPage", strategies.getTotalPage());
			stack.set("strategyTag", strategyTag);//类型
			stack.set("timeChosed",timeChosed);//时间类型
			stack.set("recommandTag", recommandTag);//本站推荐
			stack.set("comments", comments);//大家正在说
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return SUCCESS;
	}
	
	//跳转到写攻略界面
	public String goToStrategyEdit(){
		try {
			this.encodingReqAndRes();
			String userid=CookieUtil.readCookieReturnId(request);
			if(null==userid||("").equals(userid)){
				return "loginPage";
			}
			List<UserStrategyTag> strategyTag=userStrategyService.findAllUserStrategyTags();
			ValueStack stack=ActionContext.getContext().getValueStack();
			TripUser existUser=tripUserService.findUserByUserId(userid);
			stack.set("userStrategyTags", strategyTag);
			stack.set("existUser", existUser);
		} catch (Exception e) {
		}
		return "goToStrategyEdit";
	}
	
	/**
	 * 保存攻略
	 * @return
	 */
	public String saveUserStrategy(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userName=request.getParameter("username");
			String tougao=request.getParameter("tougao");//需要判空
			String strategyName=request.getParameter("strategyName");//攻略标题
			String strategyContent=request.getParameter("strategyContent");//html内容
			String coveryStory=request.getParameter("coveryStory");//简介
			String cityName=request.getParameter("cityName");//城市名
			String tags=request.getParameter("tags");//标签
			String plainText=request.getParameter("plainText");//纯文本
			TripUser existUser=tripUserService.findByUserName(userName);
			City city=officialService.getCity(cityName);
			String id=StringUtil.getMD5(strategyName);
			Boolean result=userStrategyService.saveUserStrategy(existUser,tougao,strategyName,strategyContent,coveryStory,city,tags,plainText,id);
			if(result==true){
				out.print("{'message':'saveSuccess',id:'"+id+"'}");
			}else{
				out.print("{'message':'saveError'}");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	
	/**
	 * 跳转到查看攻略详细页面
	 * @return
	 */
	public String goToStrategyInfo(){
		try {
			this.encodingReqAndRes();
			String uStrategyId=request.getParameter("ustrategyid");
			UserStrategy existStrategy=userStrategyService.findStrategyByStrategyId(uStrategyId);
			String htmlContent=existStrategy.getUstrategycontent();
			Document htmlDoc=Jsoup.parse(htmlContent);
			Elements imgs=htmlDoc.getElementsByTag("img");
			String imgSrc=imgs.get(0).attr("src");
			
			ValueStack stack=ActionContext.getContext().getValueStack();
			stack.set("singleStrategy", existStrategy);
			stack.set("imgSrc", imgSrc);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "goToStrategyInfo";
	}
	
	/**
	 * 批量下载攻略中的图片
	 * @return
	 */
	public String downloadStrategyImgs(){
		ZipOutputStream zos=null;//压缩输出流
		File[] files=null;//文件集合
		File picDir=null;//图片目录
		try {
			this.encodingReqAndRes();
			String strategyId=request.getParameter("strategyId");//相册id
			UserStrategy strategy=userStrategyService.findStrategyByStrategyId(strategyId);
			List<String> imageList=DomUtil.getAllImageListFromHtmlDoc(strategy.getUstrategycontent());
			if(null!=imageList&&!imageList.isEmpty()){
				//创建文件夹
				files=new File[imageList.size()];
				String path=request.getServletContext().getRealPath("/imagesTemp/"+strategyId);
				path=path.replace("/", File.separator);
				//在创建的文件夹中放入图片
				String filePath=path;
				picDir=new File(filePath);//创建根目录文件夹文件对象
				if(!picDir.exists()){
					picDir.mkdir();//创建根文件夹
				}
				for(int i=0;i<imageList.size();i++){
					Calendar nowtime=new GregorianCalendar();
					//定义图片文件
					String picFile = String.format("%04d%02d%02d%02d%02d%02d%03d.jpg", nowtime.get(Calendar.YEAR),
	                        nowtime.get(Calendar.MONTH)+1,nowtime.get(Calendar.DATE),nowtime.get(Calendar.HOUR),
	                        nowtime.get(Calendar.MINUTE),nowtime.get(Calendar.SECOND), 
	                        nowtime.get(Calendar.MILLISECOND));
					System.out.println(nowtime.get(Calendar.MONTH+1));
					FileOutputStream fos;//单个图片文件输出流对象
					File pictureFile=new File(filePath+File.separator+picFile);//创建单个图片文件对象
					if(!pictureFile.exists()){
						pictureFile.createNewFile();
					}
					fos=new FileOutputStream(pictureFile);//单个图片文件输出流
					//http://localhost:8080/TouristSharePlatform/userStrategyImages/images/20160907/1473240467131055349.jpg
					String imgStr=imageList.get(i);//这个地方有问题
					System.out.println(request.getServletContext().getRealPath("/")+imgStr);
					InputStream inputStream=new FileInputStream(new File(request.getServletContext().getRealPath("/")+imgStr));//获取图片的输入流
					byte[] buffer=new byte[1024];
					int length=0;
					while((length=inputStream.read(buffer))!=-1){
						fos.write(buffer, 0, length);//将图片输入流写出到输出流中
					}
					 response.setContentType("multipart/form-data");
					//response.setContentType("APPLICATION/OCTET-STREAM");
					response.setHeader("Content-Disposition","attachment; filename="+ZipImagesUtil.getZipFilename());
					files[i]=pictureFile;//将临时文件下的单个图片文件对象添加到数组中
					fos.flush();
					fos.close();
					inputStream.close();
				}
				zos=new ZipOutputStream(response.getOutputStream());//获取response对象的输出流
			}else{
				return NONE;
			}
		} catch (Exception e) {
			System.out.println("image is error========>"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				ZipImagesUtil.zipFile(files, "", zos, picDir);
				zos.flush();
				zos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return NONE;
	}
	
	/**
	 * 点赞或者取消点赞
	 * @return
	 */
	public String clickOrCancelZan(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=request.getParameter("userid");
			String strategyId=request.getParameter("strategyId");
			String type=request.getParameter("type");
			String operateType=request.getParameter("operateType");
			String result=userStrategyService.clickOrCancelZan(userid,strategyId,type,operateType);
			out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 检查点赞情况
	 * @return
	 */
	public String checkClickZan(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=request.getParameter("userid");
			String strategyId=request.getParameter("strategyId");
			String type=request.getParameter("type");
			int result=userStrategyService.checkClickZanStatus(userid,strategyId,type);
			if(result==0){
				out.print("isclicked");
			}else if(result==1){
				out.print("notclick");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	
	//request与response编码
	private void encodingReqAndRes() throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
	}
	
	
	
}
