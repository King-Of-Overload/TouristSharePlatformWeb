package alan.share.photo.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import alan.share.photo.dao.PhotoDao;
import alan.share.photo.model.AlbumTags;
import alan.share.photo.model.DisplayUserAlbums;
import alan.share.photo.model.UserAlbums;
import alan.share.photo.model.UserPhotos;
import alan.share.photo.service.PhotoService;
import alan.share.user.model.TripUser;
import alan.share.user.service.TripUserService;
import alan.share.utils.CookieUtil;
import alan.share.utils.ZipImagesUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
/**
 * 相册action类
 * @author Alan
 *
 */

public class PhotoAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private PhotoService photoService;
	private TripUserService tripUserService;
	
	 
	

	
	public TripUserService getTripUserService() {
		return tripUserService;
	}
	public void setTripUserService(TripUserService tripUserService) {
		this.tripUserService = tripUserService;
	}
	public PhotoService getPhotoService() {
		return photoService;
	}
	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
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
	
	/**
	 * 跳转到相册列表显示界面
	 * @return
	 * @throws IOException
	 */
	public String goToPhoto() throws IOException{
		this.encodingReqAndRes();
		List<AlbumTags> albumTags=photoService.findAllHotTags();//读取所有标签
		//处理轮播图
		List<UserAlbums> banners=photoService.findAlbumsByType("issense");
		List<DisplayUserAlbums> bannerList=new ArrayList<>();
		DisplayUserAlbums dam=null;
		int length=0;
		if(banners.size()>5){length=5;}else{length=banners.size();}
		for(int i=0;i<length;i++){
			dam=new DisplayUserAlbums();
			UserAlbums album=banners.get(i);
			List<UserPhotos> photos=photoService.findUserPhotosByAlbumId(album.getAlbumid());
			if(photos!=null){
				String photoUrl=photos.get(0).getPhotourl();
				dam.setAlbumid(album.getAlbumid());
				dam.setAlbumname(album.getAlbumname());
				dam.setAlbumdescription(album.getAlbumdescription());
				dam.setImageUrl(photoUrl);
				bannerList.add(dam);
			}
			
		}
		ValueStack stack=ActionContext.getContext().getValueStack();
		stack.set("albumTags", albumTags);
		//处理显示相册区域
		List<UserAlbums> albums=photoService.findAllAlbums();
		List<DisplayUserAlbums> albumObjects=new ArrayList<>();
		DisplayUserAlbums dAlbum=null;
		for(int i=0;i<albums.size();i++){
			dAlbum=new DisplayUserAlbums();
			UserAlbums album=albums.get(i);
			List<UserPhotos> photos=photoService.findUserPhotosByAlbumId(album.getAlbumid());
			if(photos!=null){
				String photoUrl=photos.get(0).getPhotourl();
				dAlbum.setAlbumid(album.getAlbumid());
				dAlbum.setAlbumname(album.getAlbumname());
				dAlbum.setAlbumdescription(album.getAlbumdescription());
				dAlbum.setImageUrl(photoUrl);
				albumObjects.add(dAlbum);
			}
			
		}
		stack.set("allAlbums", albumObjects);
		stack.set("type", 0);
		stack.set("tag", "hot");
		stack.set("bannerList", bannerList);
		return "goToPhoto";
	}
	/**
	 * 带tag条件查找所有相册并显示
	 * @return
	 */
	public String goToPhotoWithCondition(){
		try {
			this.encodingReqAndRes();
			//处理轮播图
			List<UserAlbums> banners=photoService.findAlbumsByType("issense");
			List<DisplayUserAlbums> bannerList=new ArrayList<>();
			DisplayUserAlbums dam=null;
			int length=0;
			if(banners.size()>5){length=5;}else{length=banners.size();}
			for(int i=0;i<length;i++){
				dam=new DisplayUserAlbums();
				UserAlbums album=banners.get(i);
				List<UserPhotos> photos=photoService.findUserPhotosByAlbumId(album.getAlbumid());
				if(photos!=null){
					String photoUrl=photos.get(0).getPhotourl();
					dam.setAlbumid(album.getAlbumid());
					dam.setAlbumname(album.getAlbumname());
					dam.setAlbumdescription(album.getAlbumdescription());
					dam.setImageUrl(photoUrl);
					bannerList.add(dam);
				}
				
			}
			List<AlbumTags> albumTags=photoService.findAllHotTags();//读取所有标签
			ValueStack stack=ActionContext.getContext().getValueStack();
			String type=request.getParameter("type");//可能为空
			String tag=request.getParameter("tag");//可能为空
			List<UserAlbums> albums=null;
			if(null!=type&&!("").equals(type)){
				albums=photoService.findAlbumsByType(type);
			}else if(null!=tag&&!("").equals(tag)){
				int tempTag=Integer.parseInt(tag);
				if(tempTag==0){
					albums=photoService.findAllAlbums();
				}else{
					albums=photoService.findAlbumsByTag(Integer.parseInt(tag));
				}
			}
			List<DisplayUserAlbums> albumObjects=new ArrayList<>();
			DisplayUserAlbums dAlbum=null;
			for(int i=0;i<albums.size();i++){
				dAlbum=new DisplayUserAlbums();
				UserAlbums album=albums.get(i);
				List<UserPhotos> photos=photoService.findUserPhotosByAlbumId(album.getAlbumid());
				if(photos!=null){
					String photoUrl=photos.get(0).getPhotourl();
					dAlbum.setAlbumid(album.getAlbumid());
					dAlbum.setAlbumname(album.getAlbumname());
					dAlbum.setAlbumdescription(album.getAlbumdescription());
					dAlbum.setImageUrl(photoUrl);
					albumObjects.add(dAlbum);
				}
				
			}
			stack.set("allAlbums", albumObjects);
			stack.set("type", type);
			stack.set("tag", tag);
			stack.set("albumTags", albumTags);
			stack.set("bannerList", bannerList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return"goToPhoto";
	}
	
	
	
	/**
	 * 显示相册当中的所有图片
	 * @return
	 */
	public String goToShowOneAlbum(){
		try{
		String albumId=request.getParameter("albumid");
		UserAlbums album=photoService.findAlbumByAlbumId(albumId);
		album.setClickednum(album.getClickednum()+1);
		photoService.updateUserAlbum(album);
		List<UserPhotos> photos=photoService.findUserPhotosByAlbumId(albumId);
		String photoUserId="";
		String photoUserName="";
		String photoUserHeadImage="";
		String photoUseralbumName="";
		String indexImage="";
		photoUserId=album.getTripUser().getUserid();
		photoUserName=album.getTripUser().getUsername();
		photoUserHeadImage=album.getTripUser().getHeaderimage();
		photoUseralbumName=album.getAlbumname();
		if(photos!=null&&photos.size()>0){
			indexImage=photos.get(0).getPhotourl();
		}
		ValueStack stack=ServletActionContext.getContext().getValueStack();
		stack.set("photoUserId", photoUserId);
		stack.set("photoUserName", photoUserName);
		stack.set("photoUserHeadImage", photoUserHeadImage);
		stack.set("photoUseralbumName", photoUseralbumName);
		stack.set("singleUserPhotos", photos);
		stack.set("indexImage",indexImage);
		stack.set("currentAlbumId",albumId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goToShowOneAlbum";
	}
	
	/**
	 * 幻灯片页面获取图片
	 * @return
	 */
	public String asyncGetAlbumPhotos(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String albumId=request.getParameter("albumId");
			List<UserPhotos> photos=photoService.findUserPhotosByAlbumId(albumId);
			JSONArray jsonArray=photoService.instoreJsonFormPhotoList(photos);
			System.out.println(jsonArray.toString());
			out.print(jsonArray);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 跳转到幻灯片浏览页面
	 * @return
	 */
	public String goToPowerPointView(){
		String albumId=request.getParameter("albumId");
		ValueStack stack=ActionContext.getContext().getValueStack();
		stack.set("powerPointAlbumId", albumId);
		return "goToPowerPointView";
	}
	
	/*获取所有的热门标签,@forMobile*/
	public String getAllHotTags(){
		PrintWriter out=null;
		try{
			this.encodingReqAndRes();
			out=response.getWriter();
			List<AlbumTags> albumTags=photoService.findAllHotTags();
			JSONArray jsonArray=photoService.parseTagListToJSON(albumTags);
			System.out.println(jsonArray.toString());
			out.print(jsonArray.toString());
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	
	public String goToUploadImage() throws Exception{
		String userid=CookieUtil.readCookieReturnId(request);
		if(null==userid&&!("").equals(userid)){
			return "loginPage";
		}
		List<AlbumTags> albumTags=photoService.findAllHotTags();
		ValueStack stack=ActionContext.getContext().getValueStack();
		stack.set("albumTags", albumTags);
		return "goToUploadImage";
	}
	
	/**
	 * 获取用户的相册
	 * @return
	 */
	public String getUserAlbums(){
		PrintWriter out=null;
		try{
		this.encodingReqAndRes();
		String username=request.getParameter("username");
		System.out.println(username);
			out=response.getWriter();
		if(!"".equals(username)){
			//查出用户的相册信息
			TripUser existUser=tripUserService.findByUserName(username);
			List<UserAlbums> userAlbums=photoService.findUserAlbums(existUser);
			if(userAlbums!=null){
				JSONArray jsonArray=photoService.makeAlbumListToJSONArray(userAlbums);
				System.out.println(jsonArray.toString());
				out.print(jsonArray.toString());
			}else{
				System.out.println(userAlbums);
				out.print("");
			}
		}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	  return NONE;
	}
	
	/**
	 * 保存用户新建相册信息
	 * @return
	 */
	public String saveUserAlbum(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String albumName=request.getParameter("albumName");//相册名
			String albumDescription=request.getParameter("albumDescription");//相册描述
			String cityName=request.getParameter("cityName");//城市名
			String userName=request.getParameter("username");//用户名
			String chosenStr=request.getParameter("chosenListStr");//相关标签
			System.out.println(request.getParameter("chosenListStr"));
			Boolean result=photoService.saveUserAlbumInfo(albumName,albumDescription,cityName,userName,chosenStr);
			if(result==true){
				out.print("savesuccess");
			}else{
				out.print("saveerror");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return NONE;
	}
	
	/**
	 * 保存相册
	 * @return
	 */
	public String uploadImages(){
		PrintWriter out=null;
		try{
			this.encodingReqAndRes();
			out=response.getWriter();
			String userName=request.getParameter("username");
			String albumName=request.getParameter("albumName");
			UserAlbums album=photoService.findAlbumByAlbumName(albumName);
			TripUser existUser=tripUserService.findByUserName(userName);
			String userId=existUser.getUserid();//userid
			List<String> imageNames=getAlbumImagesName(userId,albumName);
			boolean result=photoService.savePhotosInAlbums(imageNames,album);
			if(result==true){
				out.print("{'message':'savesuccess','id':'"+userId+"'}");
			}else{
				out.print("{'message':'saveerror'}");
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			if(out!=null) out.close();
		}
            return NONE;
	}
	/**
	 * 打包下载相册图片
	 * @return
	 */
	public String downloadPackageImgs(){
		ZipOutputStream zos=null;//压缩输出流
		File[] files=null;//文件集合
		File picDir=null;//图片目录
		try {
			this.encodingReqAndRes();
			String albumid=request.getParameter("albumId");//相册id
			List<UserPhotos> imageList=photoService.findUserPhotosByAlbumId(albumid);
			if(null!=imageList&&!imageList.isEmpty()){
				//创建文件夹
				files=new File[imageList.size()];
				String path=request.getServletContext().getRealPath("/imagesTemp/"+albumid);
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
					InputStream inputStream=new FileInputStream(new File(request.getServletContext().getRealPath("/")+imageList.get(i).getPhotourl()));//获取图片的输入流
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
	 * 下拉相册名变化触发
	 * @return
	 */
	public String listenDropDown(){
		String albumName=request.getParameter("albumName");
		System.out.println(albumName);
		request.getSession().setAttribute("albumName", albumName);
		return NONE;
	}
	
	
	/**
	 * 找出相册当中的照片路径
	 * @param userId
	 * @param albumName
	 * @return
	 */
	private List<String> getAlbumImagesName(String userId,String albumName){
		List<String> imageList=new ArrayList<>();
		
		String userImagePath = request.getServletContext().getRealPath("/")+"useralbums/"+userId+"/"+albumName;//用户相册根目录
		File rootFile=new File(userImagePath);
		File[] userFiles=rootFile.listFiles();
		for(int i=0;i<userFiles.length;i++){
			String singleImage=userFiles[i].getPath();
			int position=singleImage.lastIndexOf('\\');
			String realImage=singleImage.substring(position+1);
			imageList.add("useralbums/"+userId+"/"+albumName+"/"+realImage);
		}
		return imageList;
	}
	

	
	
	/**
     * 
     * <p class="detail">
     * 功能：重新命名文件
     * </p>
     * @author wangsheng
     * @date 2014年9月25日 
     * @return
     */
    private String getFileNameNew(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return fmt.format(new Date());
    }
	
	
	
	//request与response编码
	private void encodingReqAndRes() throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
	}
	

}
