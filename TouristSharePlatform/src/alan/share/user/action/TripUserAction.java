package alan.share.user.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import alan.share.index.service.IndexService;
import alan.share.officialstrategy.model.City;
import alan.share.officialstrategy.service.OfficialStrategyService;
import alan.share.photo.model.UserAlbums;
import alan.share.photo.model.UserPhotos;
import alan.share.photo.service.PhotoService;
import alan.share.product.model.OrderItem;
import alan.share.product.model.UserCollections;
import alan.share.product.service.ProductService;
import alan.share.skillacademy.model.SkillAcademy;
import alan.share.skillacademy.service.SkillAcademyService;
import alan.share.sort.SortPersonalFoot;
import alan.share.sort.SortPersonalFootDetailList;
import alan.share.sort.SortSpaceBean;
import alan.share.user.model.Comments;
import alan.share.user.model.PayAttention;
import alan.share.user.model.Reply;
import alan.share.user.model.SecrectMessage;
import alan.share.user.model.TripUser;
import alan.share.user.model.Visitor;
import alan.share.user.service.TripUserService;
import alan.share.userstrategy.model.UserStrategy;
import alan.share.userstrategy.service.UserStrategyService;
import alan.share.utilbean.PersonalFoot;
import alan.share.utilbean.PersonalFootDetail;
import alan.share.utils.CookieUtil;
import alan.share.utils.DomUtil;
import alan.share.utils.ImageBean;
import alan.share.utils.ImageCliper;
import alan.share.utils.PageBean;
import alan.share.utils.SingleViewOnRoad;
import alan.share.utils.SpaceBean;
import alan.share.utils.UserSpacePhoto;
import alan.share.utils.ViewOnRoadBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
/**
 * 用户模块Action类，实现模型驱动
 * @author Alan
 *
 */
public class TripUserAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ModelDriven<TripUser>{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private TripUserService userService;
	private PhotoService photoService;
	private UserStrategyService strategyService;
	private SkillAcademyService academyService;
	private OfficialStrategyService osStrategyService;
	private ProductService productService;
	private IndexService indexService;
	
	private TripUser personalInfo;//用户个人信息
	
	private File avatar_file;//上传头像IO文件
	

	//模型驱动
	@Override
	public TripUser getModel() {
		return personalInfo;
	}
	
	
	

	public ProductService getProductService() {
		return productService;
	}




	public void setProductService(ProductService productService) {
		this.productService = productService;
	}




	public IndexService getIndexService() {
		return indexService;
	}




	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}




	public OfficialStrategyService getOsStrategyService() {
		return osStrategyService;
	}




	public void setOsStrategyService(OfficialStrategyService osStrategyService) {
		this.osStrategyService = osStrategyService;
	}




	public File getAvatar_file() {
		return avatar_file;
	}

	public SkillAcademyService getAcademyService() {
		return academyService;
	}




	public void setAcademyService(SkillAcademyService academyService) {
		this.academyService = academyService;
	}




	public UserStrategyService getStrategyService() {
		return strategyService;
	}




	public void setStrategyService(UserStrategyService strategyService) {
		this.strategyService = strategyService;
	}




	public PhotoService getPhotoService() {
		return photoService;
	}




	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}




	public void setAvatar_file(File avatar_file) {
		this.avatar_file = avatar_file;
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



	
	public void setUserService(TripUserService userService) {
		this.userService = userService;
	}
	
	public TripUserService getUserService() {
		return userService;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	
	/**
	 * 编码
	 * @throws UnsupportedEncodingException
	 */
	private void encodingReqAndRes() throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	}


	
	
	
	//跳转到注册页面执行的action
	public String registerPage(){
		return "registerPage";
	}
	
	//验证用户名是否存在
	public String findByName() throws IOException{
		encodingReqAndRes();
		String username=request.getParameter("username");
		TripUser existUser=userService.findByUserName(username);

		//判断
		if(existUser!=null){
			//查询到该用户，用户已经存在
			response.getWriter().print("ReRegister");
		}else{
			response.getWriter().print("OKRegister");
		}
		return NONE;
	}
	
	
	public String regist() throws Exception{
		encodingReqAndRes();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		TripUser user=new TripUser();
		user.setUsername(username);
		user.setUserpassword(password);
		user.setUseremail(email);
		user.setUserstate(0);
		user.setSex("未知");
		user.setIsmaster(1);
		user.setHeaderimage("images/headerImages/default.jpg");
		City c=osStrategyService.getCity("未知");
		user.setCity(c);
		userService.save(user);
		response.getWriter().print("RegistSuccess");
		return NONE;
	}
	
	/**
	 * 用户账户激活
	 * @return
	 */
	public String active(){
		String code=request.getParameter("code");
		TripUser existUser=userService.findByActiveCode(code);
		if(existUser==null){//激活码错误
			this.addActionMessage("激活失败：激活码与原先不一致");
		}else{
			//激活成功
			//修改用户状态
			existUser.setUserstate(1);
			existUser.setUseractivecode(null);
			userService.update(existUser);//更新数据
			this.addActionMessage("恭喜您激活成功，登录后好好享受吧！");
		}
		return "msg";
	}
	
	/**
	 * 跳转到登录界面
	 * @return
	 */
	public String loginPage(){
		return "loginPage";
	}
	
	/**
	 * 用户登录
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception{
		encodingReqAndRes();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		TripUser existUser=userService.findByUserNameAndPassword(username,password);
		if(existUser==null){
			response.getWriter().print("{'message':'loginERROR'}");
			return NONE;
		}else if(existUser.getUserstate()==0){
			response.getWriter().print("{'message':'notActived'}");
			return NONE;
		}else{
			//登录成功
			//存入cookie
			CookieUtil.saveCookie(existUser, response);
			String userCookie=CookieUtil.userCookie;
			existUser.setUsercookievalue(userCookie);
			userService.update(existUser);
			//将用户信息存入session中
			ServletActionContext.getRequest().getSession().setAttribute("sessionUser", existUser);
			response.getWriter().print("{'message':'"+existUser.getHeaderimage()+"','userid':'"+existUser.getUserid()+"'}");
			return NONE;
		}
	}
	
	/**
	 * 退出登录
	 * @return
	 * @throws IOException 
	 */
	public String quit() throws IOException{
		encodingReqAndRes();
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		CookieUtil.clearCookie(response);//清除cookie
		PrintWriter out=response.getWriter();
		try{
			out.print("{'message':'quitSUCCESS'}");
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	
	/**
	 * 跳转到用户空间
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String goToSpace(){
		try {
			this.encodingReqAndRes();
			ValueStack stack=ActionContext.getContext().getValueStack();
			String requestType=request.getParameter("requestType");//主页:index 相册:album 技法学院:skillacademy
			String currentUserid=CookieUtil.readCookieReturnId(request);
			TripUser currentUser=userService.findUserByUserId(currentUserid);
			//读取粉丝数
			int fansCount=userService.findCountPayAttentionByFollowId(currentUser.getUserid());
			List<PayAttention> fansList=userService.findPayAttentionListByUserId(currentUser.getUserid());
			//读取关注数
			List<PayAttention> focusList=userService.findFocusStatus(currentUser.getUserid());
			List<TripUser> focusUsers=new ArrayList<>();
			int focusCount=0;
			if(focusList!=null){
		      focusCount=focusList.size();
		      for(int i=0;i<focusList.size();i++){
		    	  String focusId=focusList.get(i).getFollowid();
		    	  TripUser focusUser=userService.findUserByUserId(focusId);
		    	  focusUsers.add(focusUser);
		      }
			}
			if(requestType.equals("index")){
				//读取用户动态
				List<SpaceBean> freshThings=userService.findUserFreshThings(currentUser,focusUsers);
				stack.set("freshThings", freshThings);//动态集合数据
			}else if(requestType.equals("album")){
				List<UserAlbums> albumList=photoService.findUserAlbums(currentUser);//相册集合
				List<SpaceBean> albums=new ArrayList<>();
				for (UserAlbums u : albumList) {
					SpaceBean bean=new SpaceBean();
					bean.setClickedNum(u.getClickednum());
					List<UserPhotos> photos=photoService.findUserPhotosByAlbumId(u.getAlbumid());
					if(photos!=null&&photos.size()>0){
						bean.setCoverImage(photos.get(0).getPhotourl());
					}
					bean.setDescription(u.getAlbumdescription());
					bean.setId(u.getAlbumid());
					bean.setTime(u.getUploadtime());
					bean.setTitle(u.getAlbumname());
					bean.setType("album");
					bean.setUser(currentUser);
					albums.add(bean);
				}
				SortSpaceBean sort=new SortSpaceBean();
				Collections.sort(albums, sort);
				Collections.reverse(albums);
				stack.set("albums", albums);
			}else if(requestType.equals("skillacademy")){
				List<SkillAcademy> academyList=academyService.findSkillAcademyByTripUser(currentUser);
				List<SpaceBean> academies=null;
				if(academyList!=null&&academyList.size()>0){
					academies=new ArrayList<>();
					for (SkillAcademy s : academyList) {
						SpaceBean bean=new SpaceBean();
						bean.setClickedNum(s.getClickednum());
						int cnumber=indexService.findCountCommentsNumberByTopicId(s.getSkilid());
						bean.setCommentNum(cnumber);
						bean.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(s.getSkillcontent()));
						bean.setDescription(s.getSkillplaintext().substring(0, 300)+"……");
						bean.setId(s.getSkilid());
						bean.setTime(s.getSkilldate());
						bean.setTitle(s.getSkilltitle());
						bean.setType("skillacademy");
						bean.setUser(s.getUser());
						academies.add(bean);
					}
				}
				stack.set("academies", academies);//技法学院文章数据
			}
			stack.set("currentUser", currentUser);
			stack.set("fansCount", fansCount);//粉丝数
			stack.set("fansList", fansList);//粉丝集合
			stack.set("focusCount", focusCount);//关注数
			stack.set("focusUsers", focusUsers);//关注集合
			stack.set("requestType", requestType);//请求类型
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "space";
	}
	
	/**
	 * 跳转到用户详细信息界面
	 * @throws IOException 
	 */
	public String goToUser() throws IOException{
		encodingReqAndRes();
		TripUser existUser=(TripUser) request.getSession().getAttribute("sessionUser");
		if(existUser!=null){
			personalInfo=userService.findByUserName(existUser.getUsername());
			JSONObject jObject=userService.parseUserToJSONObject(personalInfo);
			System.out.println(jObject.toString());
			response.getWriter().print(jObject);
			return "user";
		}else{
			response.getWriter().print("<span class='errorOut'>验证信息已经过期，请重新登录</span>");
			return "loginPage";
		}

	}
	
	
	/**
	 * 头像上传
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("deprecation")
	public String uploadHeaderImage() throws UnsupportedEncodingException{
		this.encodingReqAndRes();
		String username=request.getParameter("avatar-username");//用户名
		username=URLDecoder.decode(username, "UTF-8");
		InputStream headerImageStream=null;
		OutputStream os=null;
		PrintWriter out=null;
		try{
			out=response.getWriter();
		String imageClipParameter=request.getParameter("avatar_data");// {"x":287.4148351648352,"y":99.06318681318683,"height":428,"width":428,"rotate":0}
		TripUser existUser=userService.findByUserName(username);
	       // 创建汉语拼音处理类  
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
        // 输出设置，大小写，音标方式  
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
		String headerName=PinyinHelper.toHanyuPinyinString(username, defaultFormat, "");
		ImageBean imgBean=userService.parseImageFromJSON(imageClipParameter);
		headerImageStream=ImageCliper.cutImage(avatar_file, imgBean.getxLength(),imgBean.getyLength(),imgBean.getWidth(),imgBean.getHeight());
		String rootPath=request.getRealPath("/images/headerImages");
		File destFile=new File(rootPath,headerName+".jpg");
		String path=destFile.getPath();//\TouristSharePlatform\images\headerImages\tangyurou.jpg
		String headerFileName=path.substring(path.lastIndexOf('\\')+1);
		System.out.println(headerFileName);
		os=new FileOutputStream(destFile);
		byte[] b=new byte[1024];
		int length=0;
		while((length=headerImageStream.read(b))>0){
			os.write(b, 0, length);
		}
		//更新数据库
		Boolean result=userService.updateUserHeaderImage(existUser,"images/headerImages/"+headerFileName);
		if(result==true){
			String returnPath="images/headerImages/"+headerFileName;
			out.print(" {\"result\":\""+returnPath+"\"}");//"恭喜您，头像上传成功，请刷新后查看"
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
				try {
					if(headerImageStream!=null) headerImageStream.close();
					if(os!=null) os.close();
					if(out!=null) out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return NONE;
	}
	
	/**
	 * 跳转到用户的空间(非用户空间)
	 * @return
	 */
	public String goToUserSpace(){
		try {
			this.encodingReqAndRes();
			ValueStack stack=ActionContext.getContext().getValueStack();
			String requestType=request.getParameter("requestType");//请求类型  strategy album skillacademy
			String userid=request.getParameter("userid");
			int page=Integer.parseInt(request.getParameter("page"));
			String currentIdentity="";
			String focusStatus="关注";
			TripUser existUser=userService.findUserByUserId(userid);//用户信息
			//添加访客记录
			String visitUserId=CookieUtil.readCookieReturnId(request);//当前访问者的用户名
			if(!existUser.getUserid().equals(visitUserId)&&visitUserId!=null){
				TripUser currentUser=userService.findUserByUserId(visitUserId);//查出当前的登录用户
				userService.saveUserVisitRecord(currentUser,existUser.getUserid());//保存访问记录
				currentIdentity="visitor";
			}else if(existUser.getUserid().equals(visitUserId)){
				currentIdentity="owner";
			}else{currentIdentity="visitor";}
			int fansNumber=userService.findCountPayAttentionByFollowId(userid);//查询粉丝数
			//最新相册查询
			List<UserAlbums> albums=photoService.findUserAlbums(existUser);
			List<UserSpacePhoto> showAlbums=new ArrayList<>();//最新相册数据
			int length=0;
			if(albums!=null&&albums.size()>0){
				if(albums.size()>12){
					length=12;
				}else{
					length=albums.size();
				}
				UserSpacePhoto p=null;
				for(int i=0;i<length;i++){
					UserAlbums album=albums.get(i);
					List<UserPhotos> photoList=photoService.findUserPhotosByAlbumId(album.getAlbumid());
					if(photoList!=null&&photoList.size()>0){
						p=new UserSpacePhoto();
						p.setAlbumid(album.getAlbumid());
						p.setPhotourl(photoList.get(0).getPhotourl());
						showAlbums.add(p);
					}
				}
			}
			//读取最近访问记录
			List<Visitor> visitors=userService.findVisitorsByVisitorId(existUser.getUserid());
			List<Visitor> vs=new ArrayList<>();
			if(visitors!=null){
				int vLen=0;
				if(visitors.size()>8){
					vLen=8;
				}else{
					vLen=visitors.size();
				}
				for(int i=0;i<vLen;i++){
					Visitor v=visitors.get(i);
					vs.add(v);
				}
			}
			//查看关注记录
			List<PayAttention> focusList=userService.findFocusStatus(visitUserId);
			if(focusList!=null&&focusList.size()>0){
				for (PayAttention p : focusList) {
					if(p.getFollowid().equals(existUser.getUserid())){//关注了该用户
						focusStatus="";
						focusStatus="已关注";
					}
				}
			}else{focusStatus="";focusStatus="关注";}
			if(requestType.equals("strategy")){
				//读取攻略信息
				PageBean<UserStrategy> stratiges=strategyService.findUserStrategyByTripUserWithPagination(page,existUser);
				stack.set("stratiges", stratiges);//右侧攻略数据
			}else if(requestType.equals("album")){
				PageBean<UserAlbums> albumList=photoService.findUserAlbumsByTripUserWithPagination(page,existUser);
				List<UserAlbums> as=albumList.getList();
				if(as!=null&&as.size()>0){
					for (UserAlbums u : as) {
						List<UserPhotos> plist=photoService.findUserPhotosByAlbumId(u.getAlbumid());
						if(plist!=null&&plist.size()>0){
							u.setCoverImage(plist.get(0).getPhotourl());
						}
					}
				}
				stack.set("albumList", albumList);//相册数据
			}else if(requestType.equals("skillacademy")){
				PageBean<SkillAcademy> academiesList=academyService.findSkillAcademyByTripUserWithPagination(page,existUser);
				List<SkillAcademy> acaList=academiesList.getList();
				if(acaList!=null&&acaList.size()>0){
					for (SkillAcademy s : acaList) {
						s.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(s.getSkillcontent()));
						if(s.getSkillcontent().length()>500){
							s.setSkillcontent(DomUtil.subStringHTML(s.getSkillcontent(), 500, "…………"));
						}	
					}
				}
				stack.set("academiesList", academiesList);//技法学院数据
			}
			stack.set("existUser", existUser);//用户信息
			stack.set("fansNumber", fansNumber);//粉丝数
			stack.set("showAlbums", showAlbums);//左侧相册数据
			stack.set("visitors", vs);//最近访问
			stack.set("currentIdentity", currentIdentity);//当前访问用户身份
			stack.set("focusStatus", focusStatus);//关注状态
			stack.set("requestType", requestType);//请求类型
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToUserSpace";
	}
	
	/**
	 * 跳转到用户资料界面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String goToPersonalDoc(){
		try {
			this.encodingReqAndRes();
			String userid=request.getParameter("userid");
			String currentUserId=CookieUtil.readCookieReturnId(request);
			if(null==currentUserId||("").equals(currentUserId)){
				return "loginPage";
			}
			TripUser currentUser=userService.findUserByUserId(currentUserId);
			TripUser visitUser=userService.findUserByUserId(userid);
			//路上风景数据
			List<UserStrategy> strategies=strategyService.findUserStrategyByTripUser(visitUser);//该用户的所有攻略
			List<UserAlbums> albums=photoService.findUserAlbums(visitUser);//该用户所有的相册
			List<ViewOnRoadBean> viewRBean=new LinkedList<>();
			ViewOnRoadBean bean=null;
			int flag1=1;//0 有重复 1有重复
			int flag2=1;
			if(strategies!=null&&strategies.size()>0){
			for(UserStrategy s:strategies){
				String cityName=s.getCity().getCityname();
				if(viewRBean.size()>0){
					flag1=1;
					for(int i=0;i<viewRBean.size();i++){
						String cName=viewRBean.get(i).getCityName();
						if(cName.equals(cityName)){
							List<SingleViewOnRoad> so=viewRBean.get(i).getList();
							SingleViewOnRoad or=new SingleViewOnRoad();
							or.setContent(s.getUstrategycontent());
							or.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(s.getUstrategycontent()));
							or.setDescription(s.getUstrategyplaintext().substring(0, 10));
							or.setPlainText(s.getUstrategyplaintext());
							or.setSid(s.getUstrategyid());
							or.setSname(s.getUstrategyname());
							or.setType("strategy");
							Calendar c=Calendar.getInstance();
							c.setTime(s.getUstrategydate());
							DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							or.setDate(format.parse(format.format(c.getTime())));
							so.add(or);
							flag1=0;
							//viewRBean.get(i).setList(so);
						}
					}
					if(flag1==1){
						bean=new ViewOnRoadBean();//创建单个城市的对象，其中包含城市名与攻略相册数组
						bean.setCityName(cityName);
						List<SingleViewOnRoad> sr=new ArrayList<>();
						SingleViewOnRoad or=new SingleViewOnRoad();
						or.setContent(s.getUstrategycontent());
						or.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(s.getUstrategycontent()));
						or.setDescription(s.getUstrategyplaintext().substring(0, 10));
						or.setPlainText(s.getUstrategyplaintext());
						or.setSid(s.getUstrategyid());
						or.setSname(s.getUstrategyname());
						or.setType("strategy");
						Calendar c=Calendar.getInstance();
						c.setTime(s.getUstrategydate());
						DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						or.setDate(format.parse(format.format(c.getTime())));
						sr.add(or);
						bean.setList(sr);
						viewRBean.add(bean);
					}
				}else{
					bean=new ViewOnRoadBean();//创建单个城市的对象，其中包含城市名与攻略相册数组
					bean.setCityName(cityName);
					List<SingleViewOnRoad> sr=new ArrayList<>();
					SingleViewOnRoad or=new SingleViewOnRoad();
					or.setContent(s.getUstrategycontent());
					or.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(s.getUstrategycontent()));
					or.setDescription(s.getUstrategyplaintext().substring(0, 10));
					or.setPlainText(s.getUstrategyplaintext());
					or.setSid(s.getUstrategyid());
					or.setSname(s.getUstrategyname());
					or.setType("strategy");
					sr.add(or);
					bean.setList(sr);
					viewRBean.add(bean);
				}
			}
			}
			if(albums!=null&&albums.size()>0){
			for(UserAlbums album:albums){
				String cityName=album.getCity().getCityname();
				if(viewRBean.size()>0){
					flag2=1;
					for(int i=0;i<viewRBean.size();i++){
						List<UserPhotos> photos=photoService.findUserPhotosByAlbumId(album.getAlbumid());
						if(null!=photos&&photos.size()>0){
							String cName=viewRBean.get(i).getCityName();
							if(cName.equals(cityName)){
								List<SingleViewOnRoad> so=viewRBean.get(i).getList();
								SingleViewOnRoad or=new SingleViewOnRoad();
								or.setDescription(album.getAlbumdescription());
								or.setSid(album.getAlbumid());
								or.setSname(album.getAlbumname());
								or.setCoverImage(photos.get(0).getPhotourl());
								or.setType("album");
								Calendar c=Calendar.getInstance();
								c.setTime(album.getUploadtime());
								DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								or.setDate(format.parse(format.format(c.getTime())));
								so.add(or);
								flag2=0;
								//viewRBean.get(i).setList(so);
							}
						}
					}
					if(flag2==1){
						List<UserPhotos> photos=photoService.findUserPhotosByAlbumId(album.getAlbumid());
						if(null!=photos&&photos.size()>0){
							bean=new ViewOnRoadBean();
							bean.setCityName(cityName);
							List<SingleViewOnRoad> sr=new ArrayList<>();
							SingleViewOnRoad or=new SingleViewOnRoad();
							Calendar c=Calendar.getInstance();
							c.setTime(album.getUploadtime());
							or.setCoverImage(photos.get(0).getPhotourl());
							DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							or.setDate(format.parse(format.format(c.getTime())));
							or.setDescription(album.getAlbumdescription());
							or.setSid(album.getAlbumid());
							or.setSname(album.getAlbumname());
							or.setType("album");
							sr.add(or);
							bean.setList(sr);
							viewRBean.add(bean);
						}
					}
				}else{
					List<UserPhotos> photos=photoService.findUserPhotosByAlbumId(album.getAlbumid());
					if(null!=photos&&photos.size()>0){
						bean=new ViewOnRoadBean();
						bean.setCityName(cityName);
						List<SingleViewOnRoad> sr=new ArrayList<>();
						SingleViewOnRoad or=new SingleViewOnRoad();
						Calendar c=Calendar.getInstance();
						c.setTime(album.getUploadtime());
						or.setCoverImage(photos.get(0).getPhotourl());
						DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						or.setDate(format.parse(format.format(c.getTime())));
						or.setDescription(album.getAlbumdescription());
						or.setSid(album.getAlbumid());
						or.setSname(album.getAlbumname());
						or.setType("album");
						sr.add(or);
						bean.setList(sr);
						viewRBean.add(bean);
					}
				}
			}
			}
			//个人足迹数据
			List<SkillAcademy> academies=academyService.findSkillAcademyByTripUser(visitUser);//技法学院数据
			List<PersonalFoot> footList=new ArrayList<>();//最终数据
			PersonalFoot foot=null;
			DateFormat format1=new SimpleDateFormat("yyyy");
			DateFormat format2=new SimpleDateFormat("MM-dd");
			int flagstrategy=1;
			//用户攻略处理
			if(strategies!=null&&strategies.size()>0){
			for(UserStrategy s:strategies){
				String year=format1.format(s.getUstrategydate());
				if(footList.size()>0){
					flagstrategy=1;
					for(int i=0;i<footList.size();i++){
						String existYeay=footList.get(i).getYear();
						if(existYeay.equals(year)){
							List<PersonalFootDetail> existList=footList.get(i).getList();
							PersonalFootDetail d=new PersonalFootDetail();
							d.setDescription(s.getUstrategyplaintext().substring(0, 50));
							d.setMonthAndDay(format2.format(s.getUstrategydate()));
							d.setName(s.getUstrategyname());
							d.setPid(s.getUstrategyid());
							d.setType("strategy");
							existList.add(d);
							flagstrategy=0;
						}
					}
					if(flagstrategy==1){
						foot=new PersonalFoot();
						foot.setYear(year);
						List<PersonalFootDetail> details=new ArrayList<>();
						PersonalFootDetail d=new PersonalFootDetail();
						d.setDescription(s.getUstrategyplaintext().substring(0, 50)+"……");
						d.setMonthAndDay(format2.format(s.getUstrategydate()));
						d.setName(s.getUstrategyname());
						d.setPid(s.getUstrategyid());
						d.setType("strategy");
						details.add(d);
						foot.setList(details);
						footList.add(foot);
					}
				}else{
					foot=new PersonalFoot();
					foot.setYear(year);
					List<PersonalFootDetail> details=new ArrayList<>();
					PersonalFootDetail d=new PersonalFootDetail();
					d.setDescription(s.getUstrategyplaintext().substring(0, 50)+"……");
					d.setMonthAndDay(format2.format(s.getUstrategydate()));
					d.setName(s.getUstrategyname());
					d.setPid(s.getUstrategyid());
					d.setType("strategy");
					details.add(d);
					foot.setList(details);
					footList.add(foot);
				}
			}
			}
			//用户相册处理
			int flagPhoto=1;
			if(albums!=null&&albums.size()>0){
			for(UserAlbums album:albums){
				String year=format1.format(album.getUploadtime());
				if(footList.size()>0){
					flagPhoto=1;
					for(int i=0;i<footList.size();i++){
						String existYear=footList.get(i).getYear();
						if(existYear.equals(year)){
							List<UserPhotos> photos=photoService.findUserPhotosByAlbumId(album.getAlbumid());
							if(null!=photos&&photos.size()>0){
								List<PersonalFootDetail> existList=footList.get(i).getList();
								PersonalFootDetail d=new PersonalFootDetail();
								d.setDescription(album.getAlbumdescription());
								d.setMonthAndDay(format2.format(album.getUploadtime()));
								d.setName(album.getAlbumname());
								d.setPid(album.getAlbumid());
								d.setType("album");
								existList.add(d);
								flagPhoto=0;
							}
						}
					}
					if(flagPhoto==1){
						List<UserPhotos> photos=photoService.findUserPhotosByAlbumId(album.getAlbumid());
						if(null!=photos&&photos.size()>0){
							foot=new PersonalFoot();
							foot.setYear(year);
							List<PersonalFootDetail> details=new ArrayList<>();
							PersonalFootDetail d=new PersonalFootDetail();
							d.setDescription(album.getAlbumdescription());
							d.setMonthAndDay(format2.format(album.getUploadtime()));
							d.setName(album.getAlbumname());
							d.setPid(album.getAlbumid());
							d.setType("album");
							details.add(d);
							foot.setList(details);
							footList.add(foot);
						}
					}
				}else{
					List<UserPhotos> photos=photoService.findUserPhotosByAlbumId(album.getAlbumid());
					if(null!=photos&&photos.size()>0){
						foot=new PersonalFoot();
						foot.setYear(year);
						List<PersonalFootDetail> details=new ArrayList<>();
						PersonalFootDetail d=new PersonalFootDetail();
						d.setDescription(album.getAlbumdescription());
						d.setMonthAndDay(format2.format(album.getUploadtime()));
						d.setName(album.getAlbumname());
						d.setPid(album.getAlbumid());
						d.setType("album");
						details.add(d);
						foot.setList(details);
						footList.add(foot);
					}
				}
			}
			}
			//用户技法文章处理
			int flagSkillAcademy=1;
			if(academies!=null&&academies.size()>0){
			for(SkillAcademy academy:academies){
				String year=format1.format(academy.getSkilldate());
				if(footList.size()>0){
					flagSkillAcademy=1;
					for(int i=0;i<footList.size();i++){
						String existYear=footList.get(i).getYear();
						if(existYear.equals(year)){
							List<PersonalFootDetail> existList=footList.get(i).getList();
							PersonalFootDetail d=new PersonalFootDetail();
							d.setDescription(academy.getSkillplaintext().substring(0, 50)+"……");
							d.setMonthAndDay(format2.format(academy.getSkilldate()));
							d.setName(academy.getSkilltitle());
							d.setPid(academy.getSkilid());
							d.setType("skillacademy");
							existList.add(d);
							flagstrategy=0;
						}
					}
					if(flagSkillAcademy==1){
						foot=new PersonalFoot();
						foot.setYear(year);
						List<PersonalFootDetail> details=new ArrayList<>();
						PersonalFootDetail d=new PersonalFootDetail();
						d.setDescription(academy.getSkillplaintext().substring(0, 50)+"……");
						d.setMonthAndDay(format2.format(academy.getSkilldate()));
						d.setName(academy.getSkilltitle());
						d.setPid(academy.getSkilid());
						d.setType("skillacademy");
						details.add(d);
						foot.setList(details);
						footList.add(foot);
					}
				}else{
					foot=new PersonalFoot();
					foot.setYear(year);
					List<PersonalFootDetail> details=new ArrayList<>();
					PersonalFootDetail d=new PersonalFootDetail();
					d.setDescription(academy.getSkillplaintext().substring(0, 50)+"……");
					d.setMonthAndDay(format2.format(academy.getSkilldate()));
					d.setName(academy.getSkilltitle());
					d.setPid(academy.getSkilid());
					d.setType("skillacademy");
					details.add(d);
					foot.setList(details);
					footList.add(foot);
				}
			}
			}
			//排序数据
			//首先排序内部文章序列
			for(int i=0;i<footList.size();i++){
				List<PersonalFootDetail> list=footList.get(i).getList();
				SortPersonalFootDetailList sort=new SortPersonalFootDetailList();
				Collections.sort(list, sort);
				Collections.reverse(list);
			}
			//接着排序总的年份
			SortPersonalFoot sortFoot=new SortPersonalFoot();
			Collections.sort(footList, sortFoot);
			Collections.reverse(footList);
			//当前用户关注的人
			//读取关注数
			List<PayAttention> focusList=userService.findFocusStatus(visitUser.getUserid());
			List<TripUser> focusUsers=new ArrayList<>();
			int focusCount=0;
			if(focusList!=null){
		      focusCount=focusList.size();
		      for(int i=0;i<focusList.size();i++){
		    	  String focusId=focusList.get(i).getFollowid();
		    	  TripUser focusUser=userService.findUserByUserId(focusId);
		    	  focusUsers.add(focusUser);
		      }
			}
			//读取所有留言
			List<SecrectMessage> messages=userService.findMessageByReceiver(visitUser);
			ValueStack stack=ActionContext.getContext().getValueStack();
			if(currentUser.getUserid().equals(visitUser.getUserid())){
				List<TripUser> users=userService.findUsersByCity(currentUser.getCity());
				for(int i=users.size()-1;i>=0;i--){
					if(users.get(i).getUserid().equals(currentUser.getUserid())){
						users.remove(users.get(i));
					}
				}
				users.subList(0, 4);
				stack.set("recommandUsers", users);//推荐用户
			}
			stack.set("currentUser", currentUser);
			stack.set("visitUser", visitUser);
			stack.set("viewRBean", viewRBean);
			stack.set("footList", footList);//年份轴数据
			stack.set("focusCount", focusCount);//关注人个数
			stack.set("focusUsers", focusUsers);//关注列表
			stack.set("messages", messages);//所有留言
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToPersonalDoc";
	}
	
	/**
	 * 阅读悄悄话根据id将本条消息状态置为已读
	 * @return
	 */
	public String readMessage(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String messageid=request.getParameter("messageid");
			if(null!=messageid&&!("").equals(messageid)){
				SecrectMessage existMessage=userService.findSecretMessageByMid(messageid);
				existMessage.setIsread(0);
				Boolean result=userService.updateSecretMessage(existMessage);
				if(result==true){
					out.print("success");
				}else{
					out.print("error");
				}
			}else{
				out.print("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 批量删除悄悄话消息
	 * @return
	 */
	public String removeMessage(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String json=request.getParameter("jsonArray");//[{"id":"50365fa8959f404682389a6ea00199ca"},{"id":"ea4d6d383cb544f796e280d037a15840"},{"id":"eb182b757df44ee5906716f5d2065e75"}]
			System.out.println(json);
			JSONArray jsonArray=JSONArray.fromString(json);
			ArrayList<String> messageIds=new ArrayList<>();
			for(int i=0;i<jsonArray.length();i++){
				JSONObject object=jsonArray.getJSONObject(i);
				String messageid=object.optString("id");
				messageIds.add(messageid);
				System.out.println(messageid);
			}
			Boolean result=userService.removeMessages(messageIds);
			if(result==true){
				out.print("success");
			}else{
				out.print("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 将消息全部标记为已读
	 * @return
	 */
	public String markMessageList(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String json=request.getParameter("jsonArray");
			System.out.println(json.toString());
			JSONArray jsonArray=JSONArray.fromString(json);
			Boolean result=false;
			for(int i=0;i<jsonArray.length();i++){
				JSONObject object=jsonArray.getJSONObject(i);
				String messageid=object.optString("id");
				SecrectMessage existMessage=userService.findSecretMessageByMid(messageid);
				existMessage.setIsread(0);
				result=userService.updateSecretMessage(existMessage);
			}
			if(result==true){
				out.print("success");
			}else{
				out.print("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("error");
		}finally{
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	
	/**
	 * 修改用户信息
	 * @return
	 */
	public String updateUserDocument(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String nickname=request.getParameter("nickname");
			String usignature=request.getParameter("usignature");
			String sex=request.getParameter("sex");
			String phone=request.getParameter("phone");
			String cityName=request.getParameter("cityName").trim();
			String userid=request.getParameter("userid");
			TripUser existUser=userService.findUserByUserId(userid);
			existUser.setUsername(nickname);
			existUser.setSex(sex);
			existUser.setPhone(phone);
			City c=osStrategyService.getCity(cityName);
			existUser.setCity(c);
			existUser.setUsignature(usignature);
			userService.update(existUser);
			JSONArray jArray=userService.enstoreTripUserToJSONArray(existUser);
			System.out.println(jArray.toString());
			out.print(jArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 发送悄悄话
	 * @return
	 */
	public String sendSecrectWord(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String senderName=URLDecoder.decode(request.getParameter("senderName"),"utf-8");//发送者昵称
			String receiverId=request.getParameter("receiverId");//接收者id
			String secretMessage=URLDecoder.decode(request.getParameter("message"), "utf-8");//悄悄话
			if(null!=senderName&&!("").equals(senderName)&&null!=receiverId&&!("").equals(receiverId)){
				TripUser sender=userService.findByUserName(senderName);//发送者
				TripUser receiver=userService.findUserByUserId(receiverId);//接收者
				if(null!=sender&&null!=receiver){
					Boolean result=userService.saveUserSecretWord(sender,receiver,secretMessage);
					if(result==true){
						out.print("success");
					}else{
						out.print("error");
					}
				}
			}else{
				out.print("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 获取未读消息数
	 * @return
	 */
	public String getUnreadMessageSize(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String username=URLDecoder.decode(request.getParameter("username"), "utf-8");
			TripUser currentUser=userService.findByUserName(username);
			int number=userService.findCountUnReadMessageSize(currentUser);
			out.print(number);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	
	/**
	 * 关注或者取消关注某人
	 * @return
	 */
	public String focusOrCancelOnSomeOne(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String result="";
			String clickStatus=request.getParameter("clickStatus");
			String userid=request.getParameter("userid");//被关注的用户id
			String currentUserId=CookieUtil.readCookieReturnId(request);//当前操作用户
			if(clickStatus.equals("focus")){
				result=userService.saveUserFocusOrCancelStatus(currentUserId,userid,"focus");
			}else if(clickStatus.equals("cancel")){
				result=userService.saveUserFocusOrCancelStatus(currentUserId,userid,"cancel");
			}
			if(result!=null&&!result.equals("")){
				if(result.equals("focusSuccess")){
					out.print("focusSuccess");
				}else if(result.equals("cancelSuccess")){
					out.print("cancelSuccess");
				}
			}else{
				out.print("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 获取回复消息
	 * @return
	 */
	public String acquireReplyData(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=request.getParameter("userid");
			TripUser fromUser=userService.findUserByUserId(userid);
			List<Comments> commentList=indexService.findCommentsListByFromUser(fromUser);//别人给我的回复
			if(null!=commentList&&!commentList.isEmpty()){
				for(Comments c:commentList){
					List<Reply> replies=indexService.findReplyListByComment(c);
					if(null!=replies&&!replies.isEmpty()){
						c.setReplies(replies);
					}
				}
			}
			JSONArray myReplyArray=indexService.enstoreCommentListToJSONArray(commentList);//别人给我的回复array
			List<Comments> realList=indexService.addRelatedComments(userid);//别人对我的评论
			JSONArray commentArray=new JSONArray();//别人对我的评价array
			if(null!=realList&&!realList.isEmpty()){
				JSONObject o=null;
				SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH.mm.ss");
				for(Comments c:realList){
					o=new JSONObject();
					o.put("commentid", c.getCommentid());
					o.put("topicid", c.getTopicid());
					o.put("topictype", c.getTopictype());
					o.put("commentcontent", c.getCommentcontent());
					o.put("commentName", c.getFromuser().getUsername());
					o.put("headerImage", c.getFromuser().getHeaderimage());
					o.put("commentdate", format.format(c.getCommentdate()));
					commentArray.put(o);
				}
			}
			JSONObject results=new JSONObject();
			results.put("myCommentsList", realList);
			results.put("myReplyList", myReplyArray);
			out.print(results.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 获取收藏记录
	 * @return
	 */
	public String acquireCollectionData(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=request.getParameter("userid");
			TripUser existUser=userService.findUserByUserId(userid);
			List<UserCollections> collections=userService.findUserCollectionsByTripUser(existUser);
			JSONArray array=userService.enstoreUserCollectionsToJSONArray(collections);
			out.print(array.toString());
			System.out.println(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 获取订单信息
	 * @return
	 */
	public String acquireMyOrderData(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=request.getParameter("userid");
			List<OrderItem> items=productService.findOrdersByUserId(userid);
			JSONArray array=productService.enstoreOrderItemListToJSONArray(items);
			out.print(array.toString());
			System.out.println(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 获取未读消息
	 * @return
	 */
	public String acquireUnReadMessage(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=request.getParameter("userid");
			List<SecrectMessage> messages=userService.findUnReadMessageByUserId(userid);
			JSONArray array=userService.enstoreSecrectMessageToJSONArray(messages);
			out.print(array);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	
	
	/**
	 * 检查登录状态
	 * @return
	 */
	public String checkLoginStatus(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userCookieValue=request.getParameter("userCookieValue");
			String loginStatus=request.getParameter("loginStatus");
			TripUser existUser=userService.findByUserCookieValue(userCookieValue);
			if(existUser!=null){
			if(loginStatus!=null){
				request.getSession().setAttribute("sessionUser", existUser);
				return NONE;
			}
			request.getSession().setAttribute("sessionUser", existUser);
				out.print("{'message':'SUCCESS','username':'"+existUser.getUsername()+"','headerImage':'"+existUser.getHeaderimage()+"','userid':'"+existUser.getUserid()+"'}");
			}else{
				out.print("{'message':'ERROR'}");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return NONE;
	}

}