package alan.share.skillacademy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import alan.share.skillacademy.model.SkillAcademy;
import alan.share.skillacademy.model.SkillAcademyCategory;
import alan.share.skillacademy.model.SkillAcademyHotTag;
import alan.share.skillacademy.model.SkillAcademySecondTag;
import alan.share.skillacademy.service.SkillAcademyService;
import alan.share.user.model.TripUser;
import alan.share.user.service.TripUserService;
import alan.share.utils.CookieUtil;
import alan.share.utils.DomUtil;
import alan.share.utils.PageBean;
import alan.share.utils.RelatedTopicBean;
import alan.share.utils.SkillBannerBean;
import alan.share.utils.SkillHotTagBean;
import alan.share.utils.UUIDUtils;
import net.sf.json.JSONArray;
/**
 * 技法学院action
 * @author Alan
 *
 */
public class SkillAcademyAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = -818441238317780508L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private SkillAcademyService academyService;
	private TripUserService userService;
	
	
	public TripUserService getUserService() {
		return userService;
	}

	public void setUserService(TripUserService userService) {
		this.userService = userService;
	}

	public SkillAcademyService getAcademyService() {
		return academyService;
	}

	public void setAcademyService(SkillAcademyService academyService) {
		this.academyService = academyService;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	
	/**
	 * 跳转到技法学院页面
	 * @return
	 */
	public String goToSkillAcademy(){
		try {
			this.encodingReqAndRes();
			//分类精华数据
			List<SkillAcademyCategory> categoryList=academyService.findAllCategory();
			//轮播图数据
			List<SkillAcademy> bannerList=academyService.findBannerAcademyInfo();
			List<SkillBannerBean> bannerInfos=academyService.constructBannerInfo(bannerList);
			//精华技巧数据
			List<SkillAcademy> essenceAcademy=academyService.findAllEssenceSkillAcademy();
			List<SkillAcademy> showEssenceAcademy=new LinkedList<>();
			int essenceLen=0;
			if(essenceAcademy.size()>4){essenceLen=4;}else{essenceLen=essenceAcademy.size();}
			for(int i=0;i<essenceLen;i++){
				SkillAcademy a=essenceAcademy.get(i);
				a.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(a.getSkillcontent()));
				showEssenceAcademy.add(a);
			}
			//用户精选数据
			List<SkillAcademy> specialChosenSkill=academyService.rangeAcademyByDateAndTempClick();
			List<SkillAcademy> showSpecialChoseSkill=new LinkedList<>();
			int specialLen=0;
			if(specialChosenSkill.size()>4){specialLen=4;}else{specialLen=specialChosenSkill.size();}
			for(int i=0;i<specialLen;i++){
				SkillAcademy a=specialChosenSkill.get(i);
				a.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(a.getSkillcontent()));
				showSpecialChoseSkill.add(a);
			}
			ValueStack stack=ServletActionContext.getContext().getValueStack();
			stack.set("categoryList", categoryList);//分类精华数据
			stack.set("bannerInfos", bannerInfos);//轮播图数据
			stack.set("essenceAcademy", showEssenceAcademy);//精华技巧数据
			stack.set("specialChosenSkill", showSpecialChoseSkill);//用户精选数据
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "goToSkillAcademy";
	}
	
	/**
	 * 跳转到技法学院文章编辑界面
	 * @return
	 */
	public String goToSkillAcademyEdit(){
		try {
			this.encodingReqAndRes();
			String currentUserId=CookieUtil.readCookieReturnId(request);
			if(null==currentUserId||("").equals(currentUserId)){
				return "loginPage";//返回登陆界面
			}
			TripUser existUser=userService.findUserByUserId(currentUserId);//查出用户
			List<SkillAcademyCategory> categoryList=academyService.findAllCategory();
			ValueStack stack=ServletActionContext.getContext().getValueStack();
			stack.set("categoryList", categoryList);
			stack.set("existUser",existUser);
			List<SkillAcademyHotTag> hotTags=academyService.findAllAcademyHotTags();
			stack.set("hotTags", hotTags);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "goToSkillAcademyEdit";
	}
	
	/**
	 * 技法学院换一批按钮ajax访问
	 * @return
	 */
	public String changeHotTags(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			List<SkillAcademyHotTag> hotTags=academyService.findAllAcademyHotTags();
			int length=hotTags.size();
			int realLength=0;
			StringBuilder builder=new StringBuilder();
			builder.append("[");
			if(length<=26){
				realLength=length;
			}else{realLength=26;}
			for(int i=0;i<realLength;i++){
				SkillAcademyHotTag tag=hotTags.get(i);
				builder.append("{");
				builder.append("'tagName':");
				builder.append("'"+tag.getSkillhottagname()+"'");
				builder.append(",");
				builder.append("'tagId':");
				builder.append("'"+tag.getSkillhottagid()+"'");
				builder.append("},");
			}
			builder.deleteCharAt(builder.lastIndexOf(","));
			builder.append("]");
			System.out.println(builder.toString());
			out.print(builder.toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return NONE;
	}
	
	/**
	 * 点击热门标签后加载二级相关话题
	 * @return
	 */
	public String linkRelatedSecondTag(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			int tagId=Integer.parseInt(request.getParameter("tagId"));//tagid
				List<SkillAcademySecondTag> results=academyService.findSecondTagsByHotTagId(tagId);
				if(results!=null){
					JSONArray jsonArray=academyService.constructJsonArrayFromSecondTagList(results);
					System.out.println(jsonArray.toString());
					out.print(jsonArray.toString());
				}else{
					out.print("{'tagName':'empty'}");
				}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return NONE;
	}
	
	/**
	 * 保存技法学院文章信息
	 * @return
	 */
	public String saveSkillAcademy(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String academyCategory=request.getParameter("tougao");//主分类名
			String skillName=request.getParameter("skillName");//文章标题
			int hotLabelContentId=Integer.parseInt(request.getParameter("hotLabelContentId"));//热门标题
			String chosenListStr=request.getParameter("chosenListStr");//用户选择的热门标签的相关话题数组需要处理,可能为空
			String userDefineRelatedTags=request.getParameter("userDefineRelatedTags");//用户自定义相关话题，需要处理，可能为空 格式：  ;伊芙妹妹  
			String htmlContent=request.getParameter("htmlContent");//带格式的文章主体内容
			String plainText=request.getParameter("plainText");//不带格式的文章内容
			String u=request.getParameter("username");
			String username=URLDecoder.decode(u, "utf-8");//作者
			TripUser author=userService.findByUserName(username);//作者
			ArrayList<String> finalTopicStringArray=new ArrayList<>();//最终标签
			String[]  chosenTopicTags=null;
			String[] userDefinedTopicTags=null;
			if(userDefineRelatedTags.equals("")||userDefineRelatedTags==null){//系统原有话题
				String[] temp=chosenListStr.split(";");
				for(int i=0;i<temp.length;i++){
					finalTopicStringArray.add(temp[i]);
				}
			}else if(chosenListStr.equals("")||chosenListStr==null){//用户自添加，需要插入话题表
			userDefinedTopicTags=userDefineRelatedTags.split(";");//用户自定义
				String[] result=academyService.updateSkillAcademySecondTag(userDefinedTopicTags,hotLabelContentId);
				for(int i=0;i<result.length;i++){
					finalTopicStringArray.add(result[i]);
				}
			}else if(!userDefineRelatedTags.equals("")&&!chosenListStr.equals("")){//两个都不空
				chosenTopicTags=chosenListStr.split(";");
				userDefinedTopicTags=userDefineRelatedTags.split(";");//用户自定义
				String[] result=academyService.updateSkillAcademySecondTag(userDefinedTopicTags,hotLabelContentId);
				for(int i=0;i<chosenTopicTags.length;i++) {finalTopicStringArray.add(chosenTopicTags[i]);}
				for(int j=0;j<result.length;j++){finalTopicStringArray.add(result[j]);}
			}
			String skillId=UUIDUtils.getUUID();
			Boolean result=academyService.saveUserSkillAcademy(academyCategory,skillId,skillName,htmlContent,plainText,author,finalTopicStringArray);
			if(result==true){
				out.print("{'skillId':'"+skillId+"','message':'success'}");
			}else{
				out.print("{'skillId':'"+skillId+"','message':'error'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 点击主分类跳转到所有文章显示界面
	 * @return
	 */
	public String goToSkillAcademyList(){
		try {
			this.encodingReqAndRes();
			int categoryId=Integer.parseInt(request.getParameter("categoryId"));
			int page=Integer.parseInt(request.getParameter("page"));//当前所在页码
			//读取分类标题
			SkillAcademyCategory singleCategory=academyService.findCategoryById(categoryId);
			//根据分类id查询所有文章带分页
			PageBean<SkillAcademy> allArticles=academyService.findSkillAcademyByCIdWithPagenation(page,singleCategory);
			for(int i=0;i<allArticles.getList().size();i++){
				allArticles.getList().get(i).setCoverImage(DomUtil.getSingleImageFromHtmlDocument(allArticles.getList().get(i).getSkillcontent()));
			}
			//读取所有分类
			List<SkillAcademyCategory> categories=academyService.findAllCategory();
			//读取主分类的热门标签
			List<SkillAcademyHotTag> hotTags=academyService.findAllAcademyHotTags();
			String[] randomNum={"tagc1","tagc2","tagc5"};
			List<SkillHotTagBean> hotTagBean=new LinkedList<>();//热门标签集合
			SkillHotTagBean bean=null;
			for (SkillAcademyHotTag t : hotTags) {
				bean=new SkillHotTagBean();
				int random=(int)(1+Math.random()*3);
				bean.setHotTagId(t.getSkillhottagid());
				bean.setTagName(t.getSkillhottagname());
				bean.setRandomClassName(randomNum[random-1]);
				hotTagBean.add(bean);
			}
			//热门文章
			List<SkillAcademy> hotArticles=academyService.rangeHotAcademyByClickedNum();
			List<SkillAcademy> showHotArticles=new LinkedList<>();//热门文章数据
			if(hotArticles.size()>10){
				for(int i=0;i<10;i++){
					showHotArticles.add(hotArticles.get(i));
				}
			}else{
				showHotArticles=hotArticles;
			}
			//热点推荐
			List<SkillAcademy> hotAcademy=academyService.findSkillAcademyExceptOneById(categoryId);
			List<SkillBannerBean> hotBean=new LinkedList<>();//热点推荐数据
			SkillBannerBean bannerBean=null;
			if(hotAcademy.size()>4){
				for(int i=0;i<4;i++){
					bannerBean=new SkillBannerBean();
					SkillAcademy a=hotAcademy.get(i);
					bannerBean.setSkillId(a.getSkilid());
					bannerBean.setBannerTitle(a.getSkilltitle());
					bannerBean.setBannerImage(DomUtil.getSingleImageFromHtmlDocument(a.getSkillcontent()));
					hotBean.add(bannerBean);
				}
			}else{
				for (SkillAcademy a : hotAcademy) {
					bannerBean=new SkillBannerBean();
					bannerBean.setSkillId(a.getSkilid());
					bannerBean.setBannerTitle(a.getSkilltitle());
					bannerBean.setBannerImage(DomUtil.getSingleImageFromHtmlDocument(a.getSkillcontent()));
					hotBean.add(bannerBean);
				}
			}
			ValueStack stack=ServletActionContext.getContext().getValueStack();
			stack.set("singleCategory", singleCategory);//点击的主分类对象数据
			stack.set("categories", categories);//所有主分类数据
			stack.set("hotTags", hotTagBean);//热门标签数据
			stack.set("hotArticles", showHotArticles);//热门文章排行数据
			stack.set("hotBean", hotBean);//热点数据
			stack.set("allArticles", allArticles);//文章数据
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToSkillList";
	}
	
	/**
	 * 点击热门标签显示关于该热门tag的文章
	 * @return
	 */
	public String clickHotTagsToSkillAcademyList(){
		try {
			this.encodingReqAndRes();
			int hotTagId=Integer.parseInt(request.getParameter("hotTagId"));//热门标签id
			SkillAcademyHotTag hotTag=academyService.findHotTagById(hotTagId);//查询热门标签对象
			//读取热门标签相关文章
			List<SkillAcademy> allArticles=academyService.findSkillAcademyByHotTag(hotTag);
			for(int i=0;i<allArticles.size();i++){
				SkillAcademy a=allArticles.get(i);
				a.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(a.getSkillcontent()));
			}
			//读取所有分类
			List<SkillAcademyCategory> categories=academyService.findAllCategory();
			//读取热门标签相关话题集合
			List<SkillAcademySecondTag> secondTags=academyService.findSecondTagsByHotTagId(hotTagId);//相关二级话题
			String[] randomNum={"tagc1","tagc2","tagc5"};
			List<SkillHotTagBean> hotTagBean=new LinkedList<>();//热门标签集合
			SkillHotTagBean bean=null;
			for (SkillAcademySecondTag t : secondTags) {
				bean=new SkillHotTagBean();
				int random=(int)(1+Math.random()*3);
				bean.setHotTagId(t.getSkillsecondid());
				bean.setTagName(t.getSkillsecondname());
				bean.setRandomClassName(randomNum[random-1]);
				hotTagBean.add(bean);
			}
			//读取热点推荐部分文章数据
			List<SkillAcademy> hotAcademy=academyService.rangeAcademyByDateAndTempClick();
			List<SkillAcademy> showHotAcademy=new LinkedList<>();
			int length=0;
			if(hotAcademy.size()>4){length=4;}else{length=hotAcademy.size();}
			for(int i=0;i<length;i++){
				SkillAcademy a=hotAcademy.get(i);
				a.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(a.getSkillcontent()));
				showHotAcademy.add(a);
			}
			//热门文章排行
			List<SkillAcademy> hotArticles=academyService.rangeAcademyByDateAndTempClick();
			List<SkillAcademy> showHotArticles=new LinkedList<>();
			int hotLen=0;
			if(hotArticles.size()>10){hotLen=10;}else{hotLen=hotArticles.size();}
			for(int i=0;i<hotLen;i++){
				SkillAcademy a=hotArticles.get(i);
				showHotArticles.add(a);
			}
			ValueStack stack=ServletActionContext.getContext().getValueStack();
			stack.set("categories", categories);//所有分类条目显示
			stack.set("tagTitle", hotTag.getSkillhottagname());//大标题显示点击的热门标签名
			stack.set("secondTags", hotTagBean);//相关二级话题
			stack.set("hotAcademy", showHotAcademy);//热点推荐数据
			stack.set("hotArticles", showHotArticles);//热门文章排行数据
			stack.set("allArticles", allArticles);//文章数据
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTohottagSkillList";
	}
	
	public String clickRelatedTagToSkillAcademyList(){
		try {
			this.encodingReqAndRes();
			int secondTagId=Integer.parseInt(request.getParameter("secondTagId"));
			SkillAcademySecondTag secondTag=academyService.findSecondTagBySecondId(secondTagId);
			int hotTagId=secondTag.getHotTag().getSkillhottagid();
			//读取热门标签相关文章
			List<SkillAcademy> allArticles=new LinkedList<>();
			Set<SkillAcademy> set=secondTag.getAcademies();
			Iterator<SkillAcademy> iterator=set.iterator();
			while(iterator.hasNext()){
				SkillAcademy a=iterator.next();
				a.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(a.getSkillcontent()));
				allArticles.add(a);
			}
			//读取所有分类
			List<SkillAcademyCategory> categories=academyService.findAllCategory();
			//读取热门标签相关话题集合
			List<SkillAcademySecondTag> secondTags=academyService.findSecondTagsByHotTagId(hotTagId);//相关二级话题
			String[] randomNum={"tagc1","tagc2","tagc5"};
			List<SkillHotTagBean> hotTagBean=new LinkedList<>();//热门标签集合
			SkillHotTagBean bean=null;
			for (SkillAcademySecondTag t : secondTags) {
				bean=new SkillHotTagBean();
				int random=(int)(1+Math.random()*3);
				bean.setHotTagId(t.getSkillsecondid());
				bean.setTagName(t.getSkillsecondname());
				bean.setRandomClassName(randomNum[random-1]);
				hotTagBean.add(bean);
			}
			//读取热点推荐部分文章数据
			List<SkillAcademy> hotAcademy=academyService.rangeAcademyByDateAndTempClick();
			List<SkillAcademy> showHotAcademy=new LinkedList<>();
			int length=0;
			if(hotAcademy.size()>4){length=4;}else{length=hotAcademy.size();}
			for(int i=0;i<length;i++){
				SkillAcademy a=hotAcademy.get(i);
				a.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(a.getSkillcontent()));
				showHotAcademy.add(a);
			}
			//热门文章排行
			List<SkillAcademy> hotArticles=academyService.rangeAcademyByDateAndTempClick();
			List<SkillAcademy> showHotArticles=new LinkedList<>();
			int hotLen=0;
			if(hotArticles.size()>10){hotLen=10;}else{hotLen=hotArticles.size();}
			for(int i=0;i<hotLen;i++){
				SkillAcademy a=hotArticles.get(i);
				showHotArticles.add(a);
			}
			ValueStack stack=ServletActionContext.getContext().getValueStack();
			stack.set("categories", categories);//所有分类条目显示
			stack.set("tagTitle", secondTag.getSkillsecondname());//大标题显示点击的热门标签名
			stack.set("secondTags", hotTagBean);//相关二级话题
			stack.set("hotAcademy", showHotAcademy);//热点推荐数据
			stack.set("hotArticles", showHotArticles);//热门文章排行数据
			stack.set("allArticles", allArticles);//文章数据
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "goTohottagSkillList";
	}
	
	/**
	 * 点击跳转到技法文章详情界面
	 * @return
	 */
	public String goToShowOneSkillAcademyArticle(){
		try {
			this.encodingReqAndRes();
			String skillId=request.getParameter("skillId");
			SkillAcademy academy=academyService.findSkillAcademyBySkillId(skillId);//找出该文章对象
			Boolean result=academyService.updateSkillAcademyClickedInfo(academy);//更新点击信息
			//热门文章区域数据查找
			List<SkillAcademy> allHotAcademy=academyService.rangeHotAcademyByClickedNum();
			List<SkillAcademy> showHotAcademy=new LinkedList<>();
			for(int i=0;i<8;i++){
				showHotAcademy.add(allHotAcademy.get(i));
			}
			//大家在看数据查找(6个)
			List<SkillAcademy> allEveryOneLookAcademy=academyService.rangeAcademyByDateAndTempClick();
			List<SkillBannerBean> showEveryOneAcademy=new LinkedList<>();
			SkillBannerBean bean=null;
			int length=0;
			if(allEveryOneLookAcademy.size()>6){
				length=6;
			}else{
				length=allEveryOneLookAcademy.size();
			}
			for(int q=0;q<length;q++){
				SkillAcademy a=allEveryOneLookAcademy.get(q);
				bean=new SkillBannerBean();
				bean.setSkillId(a.getSkilid());
				bean.setBannerTitle(a.getSkilltitle());
				bean.setBannerDescription(a.getSkillplaintext().substring(0, 60)+"……");
				String htmlContent=a.getSkillcontent();
				Document docHtml=Jsoup.parse(htmlContent);
				Elements imgs=docHtml.getElementsByTag("img");
				int count=0;
				for (Element element : imgs) {
					if(count<1){
						String imgSrc=element.attr("src");
						bean.setBannerImage(imgSrc);
						count++;
					}
				}
			showEveryOneAcademy.add(bean);
			}
			//相关热门话题
			Set<SkillAcademySecondTag> secondTags=academy.getSecondTags();
			List<RelatedTopicBean> relatedTopic=new ArrayList<>();
			RelatedTopicBean topicBean=null;
			String[] randomNum={"tagc1","tagc2","tagc5"};
			for (SkillAcademySecondTag sast : secondTags) {
				topicBean=new RelatedTopicBean();
				int random=(int)(1+Math.random()*3);
				topicBean.setRandomNum(randomNum[random-1]);
				topicBean.setTopicName(sast.getSkillsecondname());
				topicBean.setTopicId(sast.getSkillsecondid());
				relatedTopic.add(topicBean);
			}
			//轮播图数据
			List<SkillAcademy> bannerList=academyService.findBannerAcademyInfo();
			List<SkillBannerBean> bannerInfos=academyService.constructBannerInfo(bannerList);
			List<SkillBannerBean> showbanners=new LinkedList<>();//相关阅读图片区数据
			if(bannerInfos.size()>4){
				for(int i=0;i<4;i++){
					showbanners.add(bannerInfos.get(i));
				}
			}else{
				showbanners=bannerInfos;
			}
			//相关阅读文字区域
			List<SkillAcademy> relatedReading=academyService.findSkillAcademyByCategory(academy.getCategory());
			List<SkillAcademy> showRelatedRead=new LinkedList<>();//相关阅读底部文字数据
			if(relatedReading.size()>9){
				for(int i=0;i<9;i++){
					showRelatedRead.add(relatedReading.get(i));
				}
			}else{
				showRelatedRead=relatedReading;
			}
			if(result==true){
				ValueStack stack=ServletActionContext.getContext().getValueStack();
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				academy.setSkilldate(format.parse(format.format(academy.getSkilldate())));
				stack.set("userSkillArticle", academy);//用户文章信息显示
				stack.set("hotAcademy", showHotAcademy);//热门文章数据
				stack.set("everyOneLookAcademy", showEveryOneAcademy);//大家在看
				stack.set("relatedTopic", relatedTopic);//相关热门话题数据
				stack.set("showbanners", showbanners);//相关阅读图片区数据
				stack.set("relatedRead", showRelatedRead);//底部相关阅读文字区数据
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToShowOneSkillAcademyArticle";
	}

	


	
	
	//request与response编码
	private void encodingReqAndRes() throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
	}
	

}
