package alan.share.index.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import alan.share.index.service.IndexService;
import alan.share.officialstrategy.model.OfficialStrategy;
import alan.share.officialstrategy.service.OfficialStrategyService;
import alan.share.photo.model.AlbumTags;
import alan.share.photo.model.UserAlbums;
import alan.share.photo.model.UserPhotos;
import alan.share.photo.service.PhotoService;
import alan.share.product.model.Product;
import alan.share.product.service.ProductService;
import alan.share.skillacademy.model.SkillAcademy;
import alan.share.skillacademy.service.SkillAcademyService;
import alan.share.user.model.Comments;
import alan.share.user.model.Reply;
import alan.share.userstrategy.model.UserStrategy;
import alan.share.userstrategy.model.UserStrategyTag;
import alan.share.userstrategy.service.UserStrategyService;
import alan.share.utilbean.index.IndexAlbumBean;
import alan.share.utilbean.index.IndexBannerBean;
import alan.share.utilbean.index.IndexProductBean;
import alan.share.utils.DomUtil;
import alan.share.utils.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class IndexAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private SkillAcademyService academyService;
	private IndexService indexService;
	private UserStrategyService strategyService;
	private PhotoService photoService;
	private ProductService productService;
	private OfficialStrategyService oStrategyService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String searchKey;//搜索关键字
	
	
	public OfficialStrategyService getoStrategyService() {
		return oStrategyService;
	}

	public void setoStrategyService(OfficialStrategyService oStrategyService) {
		this.oStrategyService = oStrategyService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
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

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public SkillAcademyService getAcademyService() {
		return academyService;
	}

	public void setAcademyService(SkillAcademyService academyService) {
		this.academyService = academyService;
	}
	

	public IndexService getIndexService() {
		return indexService;
	}

	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}

	/**
	 * 跳转到首页
	 */
	@Override
	public String execute() throws Exception {
		try {
			//初始化滚动推荐区
			List<IndexBannerBean> beanList=indexService.initialIndexBannerList();
			//攻略游记数据
			UserStrategyTag foodTag=strategyService.findUserStrategyTagByTagName("美食");
			UserStrategyTag travelTag=strategyService.findUserStrategyTagByTagName("旅行");
			UserStrategyTag clothesTag=strategyService.findUserStrategyTagByTagName("衣服搭配");
			UserStrategyTag studyTag=strategyService.findUserStrategyTagByTagName("学习");
			List<UserStrategy> foodList=indexService.extractListFromUserStrategyTagSet(foodTag.getStrategies());
			List<UserStrategy> travelList=indexService.extractListFromUserStrategyTagSet(travelTag.getStrategies());
			List<UserStrategy> clothesList=indexService.extractListFromUserStrategyTagSet(clothesTag.getStrategies());
			List<UserStrategy> studyList=indexService.extractListFromUserStrategyTagSet(studyTag.getStrategies());
			//相册数据
			List<AlbumTags> hotTags=photoService.findConditionAlHotTags();//点击率降序
			List<AlbumTags> newHotTags=hotTags.subList(0, 4);
			List<IndexAlbumBean> albumBeans=new LinkedList<>();
			IndexAlbumBean albumBean=null;
			for(int i=0;i<newHotTags.size();i++){
				System.out.println(newHotTags.size());
				AlbumTags t=newHotTags.get(i);
				albumBean=new IndexAlbumBean();
				albumBean.setName(t.getTagname());
				List<UserAlbums> albums=new ArrayList<>();
				Set<UserAlbums> albumSet=t.getAlbums();
				Iterator<UserAlbums> iterator=albumSet.iterator();
				while(iterator.hasNext()){
					UserAlbums album=iterator.next();
					List<UserPhotos> photos=photoService.findUserPhotosByAlbumId(album.getAlbumid());
					if(null!=photos){
						album.setCoverImage(photos.get(0).getPhotourl());
						albums.add(album);
					}
				}
				int length=0;
				if(albums.size()>5){
					length=5;
				}else{
					length=albums.size();
				}
				albums.subList(0, length);
				albumBean.setAlbums(albums);
				albumBeans.add(albumBean);
			}
			//初始化摄影技巧与素材部分
			List<SkillAcademy> academies=academyService.rangeAcademyByDateAndTempClick();
			List<SkillAcademy> academyList=new ArrayList<>();//技法学院数据
			if(academies!=null&&academies.size()>0){
				int length=0;
				if(academies.size()>8){
					length=8;
				}else{
					length=academies.size();
				}
				for(int i=0;i<length;i++){
					academies.get(i).setCoverImage(DomUtil.getSingleImageFromHtmlDocument(academies.get(i).getSkillcontent()));
					academyList.add(academies.get(i));
				}
			}
			//旅游装备数据初始化
			List<Product> clothePList=productService.findPrductByCategory(productService.findProductCategoryByCName("旅行鞋袜"));
			List<Product> campPList=productService.findPrductByCategory(productService.findProductCategoryByCName("登山攀岩装备"));
			List<Product> elecPList=productService.findPrductByCategory(productService.findProductCategoryByCName("手表/眼镜/仪器/数码"));
			indexService.setProductListCoverImage(clothePList);
			indexService.setProductListCoverImage(campPList);
			indexService.setProductListCoverImage(elecPList);
			List<Product> a=clothePList.subList(0, 6);
			List<Product> b=campPList.subList(0, 6);
			List<Product> c=elecPList.subList(0, 6);
			ValueStack stack=ActionContext.getContext().getValueStack();
			stack.set("beanList", beanList);//滚动图数据
			stack.set("academyList", academyList);//技法学院数据
			//攻略数据
			stack.set("foodList", foodList);
			stack.set("travelList", travelList);
			stack.set("clothesList", clothesList);
			stack.set("studyList", studyList);
			stack.set("albumBeans", albumBeans);//相册数据
			//旅游装备数据
			stack.set("clothePList", a);
			stack.set("campPList", b);
			stack.set("elecPList", c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getHottestTopFourAlbumTags(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			List<AlbumTags> hotTags=photoService.findAllHotTags();//点击率降序
			hotTags.subList(0, 4);
			StringBuilder builder=new StringBuilder();
			for(AlbumTags t:hotTags){
				builder.append(t.getTagname()+",");
			}
			builder.deleteCharAt(builder.length()-1);
			out.print(builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	/**
	 * 顶部搜索栏执行action方法
	 * @return
	 */
	public String goToSearch(){
		try {
			this.encodingReqAndRes();
			System.out.println(searchKey);
			String searchKey=this.searchKey;
			List<UserAlbums> albums=photoService.findUserAlbumsBySearchValue(searchKey);//获取旅游相册
			List<SkillAcademy> academies=academyService.findSkillAcademyBySearchValue(searchKey);//技法数据
			List<OfficialStrategy> ostrategies=oStrategyService.findOStrategyBySearchValue(searchKey);//官方攻略
			List<OfficialStrategy> recommandList=oStrategyService.findAllOStrategyDesc();
			List<OfficialStrategy> recommands=new LinkedList<>();
			for(int i=0;i<5;i++){
				recommands.add(recommandList.get(i));
			}
			ValueStack stack=ActionContext.getContext().getValueStack();
			stack.set("albums", albums);//相册数据
			stack.set("academies", academies);//技法学院数据
			stack.set("ostrategies", ostrategies);//官方攻略数据
			stack.set("searchKey", searchKey);
			stack.set("recommands", recommands);//推荐数据
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToSearch";
	}
	
	public String acquireUserStrategyAsync(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String searchKey=request.getParameter("searchKey");//搜索关键字
			int page=Integer.parseInt(request.getParameter("page"));//当前页数
			PageBean<UserStrategy> strategies=strategyService.findUserStrategyBySearchValueWithPagination(searchKey, page);
			if(null!=strategies.getList()&&strategies.getList().size()>0){
				for(UserStrategy s:strategies.getList()){
					s.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(s.getUstrategycontent()));
				}
			}
			JSONObject jsonObject=indexService.enstoreUserStrategyPageBeanListToJSONArray(strategies);
			out.print(jsonObject.toString());
			System.out.println(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 保存评论
	 * @return
	 */
	public String saveComments(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=request.getParameter("userid");
			String topicid=request.getParameter("topicid");//评论的对象id 可能为strategy与skillacademy
			String content=request.getParameter("inputText");//内容
			String topicType=request.getParameter("topicType");//类型 strategy skillacademy
			Comments comment=indexService.saveComments(userid,topicid,content,topicType);
			if(null!=comment){
				DateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH.mm.ss");
				out.print("{'message':'success','dateTime':'"+format.format(comment.getCommentdate())+"','commentId':'"+comment.getCommentid()+"'}");
			}else{
				out.print("{'message':'error'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 提交回复
	 * @return
	 */
	public String submitReply(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=request.getParameter("userid");
			String replyContent=request.getParameter("replyContent");
			String commentId=request.getParameter("commentId");
			Reply reply=indexService.saveReply(userid,replyContent,commentId);
			DateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH.mm.ss");
			if(null!=reply){
				out.print("{'message':'success','dateTime':'"+format.format(reply.getReplydate())+"'}");
			}else{
				out.print("{'message':'error'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 获取评论数据
	 * @return
	 */
	public String retriveCommentsAndReplyData(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String topicId=request.getParameter("topicId");
			List<Comments> commentList=indexService.findCommentsListByTopicId(topicId);
			JSONArray jsonArray=indexService.enstoreCommentListToJSONArray(commentList);
			out.print(jsonArray.toString());
			System.out.println(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
