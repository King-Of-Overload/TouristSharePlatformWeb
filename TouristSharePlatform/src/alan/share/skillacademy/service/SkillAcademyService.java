package alan.share.skillacademy.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import alan.share.skillacademy.dao.SkillAcademyDao;
import alan.share.skillacademy.model.SkillAcademy;
import alan.share.skillacademy.model.SkillAcademyCategory;
import alan.share.skillacademy.model.SkillAcademyHotTag;
import alan.share.skillacademy.model.SkillAcademySecondTag;
import alan.share.user.model.TripUser;
import alan.share.utils.PageBean;
import alan.share.utils.SkillBannerBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SkillAcademyService {
	private SkillAcademyDao academyDao;

	public SkillAcademyDao getAcademyDao() {
		return academyDao;
	}

	public void setAcademyDao(SkillAcademyDao academyDao) {
		this.academyDao = academyDao;
	}

	/**
	 * 查出所有的技法分类
	 * @return
	 */
	public List<SkillAcademyCategory> findAllCategory() {
		return academyDao.findAllCategory();
	}

	/**
	 * 查找出相关的热门标签
	 * @return
	 */
	public List<SkillAcademyHotTag> findAllAcademyHotTags() {
		return academyDao.findAllAcademyHotTags();
	}

	/**
	 * 根据热门标签id查询相关话题
	 * @param tagId 标签id
	 * @return
	 */
	public List<SkillAcademySecondTag> findSecondTagsByHotTagId(int tagId) {
		SkillAcademyHotTag hotTag=academyDao.findHotTagByTagId(tagId);
		return academyDao.findSecondTagsByHotTag(hotTag);
	}
	
	/**
	 * 将话题标签封装成jsonarray
	 * @param list 话题标签集合
	 * @return
	 */
	public JSONArray constructJsonArrayFromSecondTagList(List<SkillAcademySecondTag> list){
		JSONArray jsonArray=new JSONArray();
		JSONObject jObject=null;
		try {
			for(int i=0;i<list.size();i++){
				jObject=new JSONObject();
				SkillAcademySecondTag tag=list.get(i);
				jObject.put("tagId", tag.getSkillsecondid());
				jObject.put("tagName", tag.getSkillsecondname());
				jsonArray.put(jObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(jObject!=null) jObject=null;
		}
		return jsonArray;
	}

	/**
	 * 将用户自己添加的话题插入到数据库当中
	 * @param userDefinedTopicTags
	 * @return
	 */
	public String[] updateSkillAcademySecondTag(String[] userDefinedTopicTags,int hotLabelContentId) {
		String[] result=null;
		List<SkillAcademySecondTag> hotTags=academyDao.findAllAcademySecondTagById(hotLabelContentId);
		if(hotTags!=null){
		SkillAcademyHotTag hotTag=hotTags.get(0).getHotTag();
		//String[] newTag=new String[1000];
		ArrayList<String> newTag=new ArrayList<>();
		int dataLength=userDefinedTopicTags.length;//用户自定义标签的长度
		for(int q=0;q<dataLength;q++){
			for(int i=0;i<hotTags.size();i++){
				if(!hotTags.get(i).getSkillsecondname().equals(userDefinedTopicTags[q])){
					//newTag[length]=userDefinedTopicTags[i];
					if(!newTag.contains(userDefinedTopicTags[q]))
					newTag.add(userDefinedTopicTags[q]);
				}
			}
		}

		if(newTag.size()!=0){//没有重复的
//			ArrayList<String> t=new ArrayList<>();
//			for(int i=0;i<userDefinedTopicTags.length;i++){
//				t.add(i, userDefinedTopicTags[i]);
//			}
			academyDao.insertUserDefinedTagIntoSkillAcademySecondTag(newTag,hotTag);//插入操作
			result=(String[])newTag.toArray(new String[newTag.size()]);
		}
		
		}else{
		SkillAcademyHotTag hotTag=academyDao.findHotTagByTagId(hotLabelContentId);
		ArrayList<String> tempTags=new ArrayList<>();
		for(int i=0;i<userDefinedTopicTags.length;i++){
			tempTags.add(i, userDefinedTopicTags[i]);
		}
		academyDao.insertUserDefinedTagIntoSkillAcademySecondTag(tempTags,hotTag);//插入操作
		result=userDefinedTopicTags;
		}
		return result;
	}

	/**
	 * 将用户发表的文章插入数据库
	 * @param skillId 文章id
	 * @param skillName 文章标题
	 * @param htmlContent 带格式正文
	 * @param plainText 不带格式正文
	 * @param author 作者
	 * @param finalTopicStringArray 相关话题
	 * @return
	 */
	public Boolean saveUserSkillAcademy(String academyCategory,String skillId, String skillName, String htmlContent, String plainText,
			TripUser author, ArrayList<String> finalTopicStringArray) {
		Boolean result=false;
		SkillAcademy academy=new SkillAcademy();
		try{
		academy.setSkilid(skillId);
		academy.setUser(author);
		academy.setSkilltitle(skillName);
		academy.setSkillplaintext(plainText);
		academy.setSkillcontent(htmlContent);
		academy.setClickednum(0);
		academy.setIsessence(1);
		academy.setSkilltempclickednum(0);
		academy.setCategory(academyDao.findMainCategoryByName(academyCategory));
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		academy.setSkilldate(format.parse(format.format(new Date())));
		List<SkillAcademySecondTag> tags=academyDao.findSecondTagsByTagNameArray(finalTopicStringArray);
		Set<SkillAcademySecondTag> tagSet=new HashSet<>();
		for(int i=0;i<tags.size();i++){
			tagSet.add(tags.get(i));
		}
		academy.setSecondTags(tagSet);
		result=academyDao.saveUserSkillAcademy(academy);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询轮播图中显示的数据
	 * @return
	 */
	public List<SkillAcademy> findBannerAcademyInfo() {
		return academyDao.findBannerAcademyInfo();
	}

	/**
	 * 对查询出来的轮播图数据二次封装
	 * @param bannerList 轮播图数据
	 * @return
	 */
	public List<SkillBannerBean> constructBannerInfo(List<SkillAcademy> bannerList) {
		List<SkillBannerBean> results=new ArrayList<>();
		SkillBannerBean bean=null;
		SkillAcademy academy=null;
		for(int i=0;i<bannerList.size();i++){
			bean=new SkillBannerBean();
			academy=bannerList.get(i);
			bean.setSkillId(academy.getSkilid());
			bean.setBannerTitle(academy.getSkilltitle());
			bean.setBannerDescription(academy.getSkillplaintext().substring(0, 100)+"…………");
			String htmlContent=academy.getSkillcontent();//html文档
			Document docHtml=Jsoup.parse(htmlContent);
			Elements imgs=docHtml.getElementsByTag("img");
			int count=0;
			for (Element e : imgs) {
				if(count<1){
					String src=e.attr("src");
					bean.setBannerImage(src);
					count++;
				}
			}
			results.add(bean);
		}
		return results;
	}

	/**
	 * 找到点击的技法学院某一篇文章的相关信息
	 * @param skillId 技法学院文章id
	 * @return
	 */
	public SkillAcademy findSkillAcademyBySkillId(String skillId) {
		return academyDao.findSkillAcademyBySkillId(skillId);
	}

	/**
	 * 点击某一个技法学院文章之后更新点击量等信息
	 * @param academy
	 * @return
	 * @throws ParseException 
	 */
	public Boolean updateSkillAcademyClickedInfo(SkillAcademy academy) throws ParseException {
		SkillAcademy updatedAcademy=new SkillAcademy();
		academy.setClickednum(academy.getClickednum()+1);
		academy.setSkilltempclickednum(academy.getSkilltempclickednum()+1);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		academy.setSkillnumupdatetime(format.parse(format.format(new Date())));
		updatedAcademy=academy;
		return academyDao.updateSkillAcademyClickedInfo(updatedAcademy);
	}

	/**
	 * 查找出所有文章并按照点击量排序
	 * @return
	 */
	public List<SkillAcademy> rangeHotAcademyByClickedNum() {
		return academyDao.rangeHotAcademyByClickedNum();
	}

	/**
	 * 查询大家在看的数据,根据临时点击量字段与最近查看日期来排序
	 * @return
	 */
	public List<SkillAcademy> rangeAcademyByDateAndTempClick() {
		return academyDao.rangeAcademyByDateAndTempClick();
	}

	/**
	 * 根据主分类查询相关文章
	 * @param category
	 * @return
	 */
	public List<SkillAcademy> findSkillAcademyByCategory(SkillAcademyCategory category) {
		return academyDao.findSkillAcademyByCategory(category);
	}

	/**
	 * 
	 * @param categoryId
	 * @return
	 */
	public SkillAcademyCategory findCategoryById(int categoryId) {
		return academyDao.findCategoryById(categoryId);
	}

	/**
	 * 找出不包括某分类的技法学院文章
	 * @param categoryId
	 * @return
	 */
	public List<SkillAcademy> findSkillAcademyExceptOneById(int categoryId) {
		SkillAcademyCategory c=academyDao.findCategoryById(categoryId);
		return academyDao.findSkillAcademyExceptOneById(c);
	}

	/**
	 * 根据主分类带有分页的查询分类下的技法文章
	 * @param categoryId 分类id
	 * @param page 当前页码
	 * @param category 分类对象
	 * @return
	 */
	public PageBean<SkillAcademy> findSkillAcademyByCIdWithPagenation(int page,SkillAcademyCategory category) {
		PageBean<SkillAcademy> pageBean=new PageBean<>();
		pageBean.setPage(page);//设置当前页码
		int limit=8;
		pageBean.setLimit(limit);//设置每页显示的记录数
		//设置总记录数
		int totalCount=0;
		totalCount=academyDao.findCountSkillAcademyByCid(category);//总记录数
		pageBean.setTotalCount(totalCount);//设置总记录数
		//总页数设置
		int totalPage=0;
		if(totalCount<=pageBean.getLimit()){
			totalPage=1;
		}else{
			if(totalCount%limit==0){
				totalPage=totalCount%limit;
			}else{
				totalPage=totalCount%limit+1;
			}
		}
		pageBean.setTotalPage(totalPage);//设置总页数
		//查询每页显示的数据集合
		int begin=(page-1)*limit;//从哪一个数据开始向下查询
		List<SkillAcademy> list=academyDao.findSkillAcademyByCIdWithPagenation(category,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 根据热门标签查询热门标签对象
	 * @param hotTagId
	 * @return
	 */
	public SkillAcademyHotTag findHotTagById(int hotTagId) {
		return academyDao.findHotTagById(hotTagId);
	}

	/**
	 * 根据热门标签对象查询相关的文章集合
	 * @param hotTag 热门标签对象
	 * @return
	 */
	public List<SkillAcademy> findSkillAcademyByHotTag(SkillAcademyHotTag hotTag) {
		 List<SkillAcademySecondTag> tags=academyDao.findSecondTagsByHotTag(hotTag);
		 List<SkillAcademy> list=new LinkedList<>();
		 for(int i=0;i<tags.size();i++){
			 Set<SkillAcademy> set=tags.get(i).getAcademies();
			 Iterator<SkillAcademy> iterator=set.iterator();
			 while(iterator.hasNext()){
				 SkillAcademy a=iterator.next();
				 if(!list.contains(a)){
					 list.add(a);
				 }
			 }
		 }
		return list;
	}

	/**
	 * 根据二级话题查找出一级热门标签对象
	 * @param secondTagId
	 * @return
	 */
	public SkillAcademySecondTag findSecondTagBySecondId(int secondTagId) {
		SkillAcademySecondTag secondTag=academyDao.findSecondTagsBySecondTagId(secondTagId);
		return secondTag;
	}

	/**
	 * 找出所有技法学院中的精华文章,并按点击量排序
	 * @return
	 */
	public List<SkillAcademy> findAllEssenceSkillAcademy() {
		List<SkillAcademy> results=academyDao.findAllessenceSkillAcademy();
		return results;
	}

	/**
	 * 根据当前用户对象查询技法学院文章
	 * @param currentUser 当前用户对象
	 * @return
	 */
	public List<SkillAcademy> findSkillAcademyByTripUser(TripUser currentUser) {
		return academyDao.findUserSkillAcademyByTripUser(currentUser);
	}

	/**
	 * 根据用户对象带分页查询技法文章
	 * @param page
	 * @param existUser
	 * @return
	 */
	public PageBean<SkillAcademy> findSkillAcademyByTripUserWithPagination(int page, TripUser existUser) {
		PageBean<SkillAcademy> pageBean=new PageBean<>();
		pageBean.setPage(page);//设置当前页码
		int limit=3;
		pageBean.setLimit(limit);//设置每页显示的记录数
		//设置总记录数
		int totalCount=0;
		totalCount=academyDao.findCountSkillAcademyByTripUser(existUser);//总记录数
		pageBean.setTotalCount(totalCount);//设置总记录数
		//总页数设置
		int totalPage=0;
		if(totalCount<=pageBean.getLimit()){
			totalPage=1;
		}else{
			if(totalCount%limit==0){
				totalPage=totalCount%limit;
			}else{
				totalPage=totalCount%limit+1;
			}
		}
		pageBean.setTotalPage(totalPage);//设置总页数
		//查询每页显示的数据集合
		int begin=(page-1)*limit;//从哪一个数据开始向下查询
		List<SkillAcademy> list=academyDao.findSkillAcademyByTripUserWithPagination(existUser,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 根据关键字查询相关技法学院文章
	 * @param searchKey
	 * @return
	 */
	public List<SkillAcademy> findSkillAcademyBySearchValue(String searchKey) {
		return academyDao.findSkillAcademyBySearchValue(searchKey);
	}

	/**
	 * 根据关键字查找相关技法学院相关文章
	 * @return
	 */
	public List<SkillAcademy> findRelatedProductsBlogs() {
		return academyDao.findRelatedProductsBlogs();
	}
	
	
}
