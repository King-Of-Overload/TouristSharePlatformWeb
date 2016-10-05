package alan.share.skillacademy.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import alan.share.skillacademy.model.SkillAcademy;
import alan.share.skillacademy.model.SkillAcademyCategory;
import alan.share.skillacademy.model.SkillAcademyHotTag;
import alan.share.skillacademy.model.SkillAcademySecondTag;
import alan.share.user.model.TripUser;
import alan.share.utils.DomUtil;
import alan.share.utils.PageHibernateCallback;

public class SkillAcademyDao extends HibernateDaoSupport{

	/**
	 * 查询所有技法分类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillAcademyCategory> findAllCategory() {
		String hql="FROM SkillAcademyCategory";
		List<SkillAcademyCategory> results=new LinkedList<>();
		try {
			List<SkillAcademyCategory> list=this.getHibernateTemplate().find(hql);
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * 查出部分热门标签
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillAcademyHotTag> findAllAcademyHotTags() {
		String hql="FROM SkillAcademyHotTag ORDER BY NEWID()";
		List<SkillAcademyHotTag> results=new ArrayList<>();
		try {
			List<SkillAcademyHotTag> list=this.getHibernateTemplate().find(hql);
			if(list.size()>0&&list!=null){
				if(list.size()>20){
					for(int i=0;i<20;i++){
						results.add(list.get(i));
					}
				}
				results=list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * 根据热门标签id查询相关话题
	 * @param tagId 标签id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillAcademySecondTag> findSecondTagsByHotTag(SkillAcademyHotTag hotTag) {
		String hql="FROM SkillAcademySecondTag WHERE hotTag=?";
		List<SkillAcademySecondTag> results=null;
		try {
			List<SkillAcademySecondTag> list=this.getHibernateTemplate().find(hql,hotTag);
			if(list.size()>0&&list!=null){
				results=list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * 查询热门标签
	 * @param tagId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SkillAcademyHotTag findHotTagByTagId(int tagId) {
		String hql="FROM SkillAcademyHotTag WHERE skillhottagid=?";
		try {
			List<SkillAcademyHotTag> list=this.getHibernateTemplate().find(hql,tagId);
			return list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据热门标签查找出所有话题
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillAcademySecondTag> findAllAcademySecondTagById(int tagId) {
		SkillAcademyHotTag tag=this.findHotTagByTagId(tagId);
		String hql="FROM SkillAcademySecondTag WHERE hotTag=?";
		List<SkillAcademySecondTag> results=null;
		try {
			List<SkillAcademySecondTag> list=this.getHibernateTemplate().find(hql,tag);
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * 将用户自己添加的相关话题插入到数据库中
	 * @param newTag 标签集合
	 * @return
	 */
	public Boolean insertUserDefinedTagIntoSkillAcademySecondTag(ArrayList<String> newTag,SkillAcademyHotTag hotTag) {
		Boolean result=false;
		SkillAcademySecondTag secondTag=null;
		try {
			for(int i=0;i<newTag.size();i++){
				secondTag=new SkillAcademySecondTag();
				secondTag.setSkillsecondname(newTag.get(i));
				secondTag.setHotTag(hotTag);
				secondTag.setSkillsecondclickednum(0);
				secondTag.setAcademies(new HashSet<>());
				this.getHibernateTemplate().save(secondTag);
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据用户选择的话题找出所有话题对象
	 * @param finalTopicStringArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillAcademySecondTag> findSecondTagsByTagNameArray(ArrayList<String> finalTopicStringArray) {
		String hql="FROM SkillAcademySecondTag WHERE skillsecondname=?";
		List<SkillAcademySecondTag> results=new ArrayList<>();
		SkillAcademySecondTag tag=null;
		try {
			for(int i=0;i<finalTopicStringArray.size();i++){
				tag=new SkillAcademySecondTag();
				List<SkillAcademySecondTag> list=this.getHibernateTemplate().find(hql,finalTopicStringArray.get(i));
				if(list.size()>0&&list!=null){
					tag=list.get(0);
					results.add(tag);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * 保存用户技法文章
	 * @param academy
	 * @return
	 */
	public Boolean saveUserSkillAcademy(SkillAcademy academy) {
		Boolean result=false;
		try {
			this.getHibernateTemplate().save(academy);
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据主分类名找出主分类对象
	 * @param academyCategory
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SkillAcademyCategory findMainCategoryByName(String academyCategory) {
		String hql="FROM SkillAcademyCategory WHERE skillcategoryname=?";
		SkillAcademyCategory result=null;
		try {
			List<SkillAcademyCategory> list=this.getHibernateTemplate().find(hql,academyCategory);
			if(list.size()>0){
				result=list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询技法学院轮播图中需要显示的数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillAcademy> findBannerAcademyInfo() {
		List<SkillAcademy> results=new ArrayList<>();
		try {
			int categoryNum=this.findAllCategory().size();
			for(int i=1;i<=categoryNum;i++){
				SkillAcademyCategory c=this.findCategoryById(i);
				String hql="FROM SkillAcademy WHERE category=? AND clickednum=(SELECT MAX(clickednum) FROM SkillAcademy WHERE category=?)";
				List<SkillAcademy> list=this.getHibernateTemplate().find(hql,c,c);
				if(list!=null&&list.size()>0){
					results.add(list.get(0));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * 根据id查询主分类对象
	 * @param i
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SkillAcademyCategory findCategoryById(int i) {
		String hql="FROM SkillAcademyCategory WHERE skillcategoryid=?";
		SkillAcademyCategory c=null;
		try {
			c=new SkillAcademyCategory();
			List<SkillAcademyCategory> list=this.getHibernateTemplate().find(hql,i);
			if(list.size()>0){
				c=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return c;
	}

	/**
	 * 查询某一篇技法学院文章对象
	 * @param skillId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SkillAcademy findSkillAcademyBySkillId(String skillId) {
		String hql="FROM SkillAcademy WHERE skilid=?";
		SkillAcademy academy=null;
		try {
			List<SkillAcademy> list=this.getHibernateTemplate().find(hql,skillId);
			if(list!=null&&list.size()>0){
				academy=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return academy;
	}

	/**
	 * 点击技法学院某篇文章后更新点击量数据
	 * @param updatedAcademy
	 * @return
	 */
	public Boolean updateSkillAcademyClickedInfo(SkillAcademy updatedAcademy) {
		Boolean result=false;
		try {
			this.getHibernateTemplate().update(updatedAcademy);
			result=true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据点击量查出技法学院文章降序排序
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillAcademy> rangeHotAcademyByClickedNum() {
		String hql="FROM SkillAcademy ORDER BY clickednum DESC";
		List<SkillAcademy> results=new LinkedList<>();
		try {
			List<SkillAcademy> list=this.getHibernateTemplate().find(hql);
			if(list.size()>0&&list!=null){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据临时点击量(2小时内由触发器更新),最近更新日期排序文章
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillAcademy> rangeAcademyByDateAndTempClick() {
		String hql="FROM SkillAcademy WHERE skillnumupdatetime IS NOT NULL ORDER BY skillnumupdatetime DESC,skilltempclickednum DESC";
		List<SkillAcademy> results=new LinkedList<>();
		try {
			List<SkillAcademy> list=this.getHibernateTemplate().find(hql);
			if(list.size()>0&&list!=null){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * dao层通过主分类查询技法学院文章集合
	 * @param category 主分类对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillAcademy> findSkillAcademyByCategory(SkillAcademyCategory category) {
		String hql="FROM SkillAcademy WHERE category=? ORDER BY clickednum";
		List<SkillAcademy> results=null;
		try {
			List<SkillAcademy> list=this.getHibernateTemplate().find(hql,category);
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 找出不是c分类的所有文章
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillAcademy> findSkillAcademyExceptOneById(SkillAcademyCategory c) {
		String hql="FROM SkillAcademy WHERE category<>?";
		List<SkillAcademy> results=null;
		try {
			List<SkillAcademy> list=this.getHibernateTemplate().find(hql,c);
			if(list.size()>0&&list!=null){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据主分类统计主分类下有多少篇文章
	 * @param categoryId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountSkillAcademyByCid(SkillAcademyCategory category) {
		String hql="SELECT COUNT(*) FROM SkillAcademy WHERE category=?";
		int totalCount=0;
		try {
			List<Long> list=this.getHibernateTemplate().find(hql,category);
			if(list!=null&&list.size()>0){
				totalCount=list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return totalCount;
	}

	/**
	 * 根据分类对象带分页的查询技法学院的文章
	 * @param category
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<SkillAcademy> findSkillAcademyByCIdWithPagenation(SkillAcademyCategory category, int begin, int limit) {
		String hql="FROM SkillAcademy WHERE category=?";
		List<SkillAcademy> results=null;
		try {
			List<SkillAcademy> list=this.getHibernateTemplate().execute(new PageHibernateCallback<SkillAcademy>(hql, new Object[]{category}, begin,limit));
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据热门标签id查询标签对象
	 * @param hotTagId 热门标签id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SkillAcademyHotTag findHotTagById(int hotTagId) {
		String hql="FROM SkillAcademyHotTag WHERE skillhottagid=?";
		SkillAcademyHotTag result=null;
		try {
			List<SkillAcademyHotTag> list=this.getHibernateTemplate().find(hql,hotTagId);
			if(list!=null&&list.size()>0){
				result=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据二级话题id查找二级话题对象
	 * @param secondTagId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SkillAcademySecondTag findSecondTagsBySecondTagId(int secondTagId) {
		String hql="FROM SkillAcademySecondTag WHERE skillsecondid=?";
		SkillAcademySecondTag result=null;
		try {
			List<SkillAcademySecondTag> list=this.getHibernateTemplate().find(hql,secondTagId);
			if(list.size()>0&&list!=null){
				result=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 查出所有精华技法文章，并降序排序
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillAcademy> findAllessenceSkillAcademy() {
		String hql="FROM SkillAcademy WHERE isessence=0 ORDER BY skilltempclickednum";
		List<SkillAcademy> results=null;
		try {
			List<SkillAcademy> list=this.getHibernateTemplate().find(hql);
			if(list.size()>0&&list!=null){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据用户对象那个查询技法学院文章
	 * @param currentUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillAcademy> findUserSkillAcademyByTripUser(TripUser currentUser) {
		String hql="FROM SkillAcademy WHERE user=?";
		List<SkillAcademy> results=null;
		try {
			List<SkillAcademy> list=this.getHibernateTemplate().find(hql,currentUser);
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据用户对象统计技法学院文章个数
	 * @param existUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountSkillAcademyByTripUser(TripUser existUser) {
		String hql="SELECT COUNT(*) FROM SkillAcademy WHERE user=?";
		int result=0;
		try {
			List<Long> list=this.getHibernateTemplate().find(hql,existUser);
			if(list!=null&&list.size()>0){
				result=list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据用户对象带分页的查询发表的技法文章
	 * @param existUser
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<SkillAcademy> findSkillAcademyByTripUserWithPagination(TripUser existUser, int begin, int limit) {
		String hql="FROM SkillAcademy WHERE user=? ORDER BY skilldate DESC";
		List<SkillAcademy> results=null;
		try {
			List<SkillAcademy> list=this.getHibernateTemplate().execute(new PageHibernateCallback<SkillAcademy>(hql, new Object[]{existUser}, begin, limit));
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据关键字查询技法学院文章
	 * @param searchKey 关键字
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillAcademy> findSkillAcademyBySearchValue(String searchKey) {
		String hql="FROM SkillAcademy WHERE skilltitle LIKE '%"+searchKey+"%' OR skillplaintext LIKE '%"+searchKey+"%' "
				+ "OR user.username=? OR category.skillcategoryname LIKE '%"+searchKey+"%'";
		List<SkillAcademy> results=null;
		try {
			List<SkillAcademy> list=this.getHibernateTemplate().find(hql,searchKey);
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					list.get(i).setCoverImage(DomUtil.getSingleImageFromHtmlDocument(list.get(i).getSkillcontent()));
				}
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据关键字查找技法学院文章
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillAcademy> findRelatedProductsBlogs() {
		String hql="FROM SkillAcademy WHERE skilltitle LIKE '%佳能%' OR skilltitle LIKE '%摄影%' OR skillplaintext LIKE '%摄影%' OR "
				+ "skilltitle LIKE '%器材%' OR skillplaintext LIKE '%器材%'";
		List<SkillAcademy> results=new ArrayList<>();
		try {
			List<SkillAcademy> list=this.getHibernateTemplate().find(hql);
			if(list!=null&&!list.isEmpty()){
				for(SkillAcademy a:list){
					a.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(a.getSkillcontent()));
				}
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}
	
}
