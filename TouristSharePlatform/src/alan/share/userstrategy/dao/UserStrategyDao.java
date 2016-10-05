package alan.share.userstrategy.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import alan.share.user.model.ClickZan;
import alan.share.user.model.Comments;
import alan.share.user.model.TripUser;
import alan.share.userstrategy.model.UserStrategy;
import alan.share.userstrategy.model.UserStrategyTag;
import alan.share.utils.DomUtil;
import alan.share.utils.PageHibernateCallback;
@Repository
public class UserStrategyDao extends HibernateDaoSupport{

	/**
	 * 查找所有攻略
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserStrategyTag> findAllUserStrategyTags() {
		List<UserStrategyTag> tagLists=new ArrayList<>();
		try{
			String hql="FROM UserStrategyTag ORDER BY ustrategyclickednum desc";
			List<UserStrategyTag> list=this.getHibernateTemplate().find(hql);
			if(list!=null) tagLists=list; 
		}catch(Exception e){
			e.printStackTrace();
		}
		return tagLists;
	}

	/**
	 * 保存用户攻略
	 * @param userStrategy 攻略对象
	 * @return
	 */
	@Transactional
	public Boolean saveUserStrategy(UserStrategy userStrategy) {
		Boolean result=false;
		try {
			this.getHibernateTemplate().save(userStrategy);
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据标签名查询标签对象
	 * @param tags
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set<UserStrategyTag> queryUserStrategyTagsByName(String[] tagArray,String tougao) {
		String hql="FROM UserStrategyTag WHERE ustrategytagname=?";
		Set<UserStrategyTag> set=new HashSet<>();
		for(int i=0;i<tagArray.length;i++){
			String singleTag=tagArray[i];
			List<UserStrategyTag> list=this.getHibernateTemplate().find(hql,singleTag);
			if(list.size()>0&&list!=null) set.add(list.get(0));
		}
		if(!"".equals(tougao)){
			int count=0;
			for(int i=0;i<tagArray.length;i++){
				if(tagArray[i].equals(tougao)) count+=1;
			}
			if(count==0){
				List<UserStrategyTag> single=this.getHibernateTemplate().find(hql,tougao);
				set.add(single.get(0));
			}
		}
		
		return set;
	}

	/**
	 * 查找所有攻略
	 * @return
	 */
	@SuppressWarnings({"unchecked" })
	public List<UserStrategy> findAllUserStrategy() {
		String hql="FROM UserStrategy ORDER BY uclickednum desc";
		try {
			List<UserStrategy> list=this.getHibernateTemplate().find(hql);
			if(list!=null&&list.size()>0){
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据id查找攻略
	 * @param uStrategyId 攻略id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public UserStrategy findStrategyByStrategyId(String uStrategyId) {
		String hql="FROM UserStrategy WHERE ustrategyid=?";
		UserStrategy us=null;
		try {
			List<UserStrategy> list=this.getHibernateTemplate().find(hql,uStrategyId);
			if(list.size()>0&&list!=null){
				us=list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return us;
	}

	/**
	 * 统计游记攻略的个数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountUserStrategy() {
		String hql="SELECT COUNT(*) FROM UserStrategy";
		int result=0;
		try {
			List<Long> list=this.getHibernateTemplate().find(hql);
			if(list.size()>0&&list!=null){
				result=list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 带分页的查询所有的攻略游记
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<UserStrategy> findAllUserStrategyByPagination(int begin, int limit) {
		String hql="FROM UserStrategy";
		List<UserStrategy> results=null;
		try {
			List<UserStrategy> list=this.getHibernateTemplate().execute(new PageHibernateCallback<UserStrategy>(hql, null, begin, limit));
			if(list.size()>0&&list!=null){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据关键字找出相关的文章有多少篇
	 * @param searchVal
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountUserStrategyBySearchValue(String searchVal) {
		String hql="SELECT COUNT(*) FROM UserStrategy WHERE ustrategyname LIKE '%"+searchVal+"%' OR ustrategyplaintext LIKE '%"+searchVal+"%' OR tripUser.username LIKE '%"+searchVal+"%'";
		int result=0;
		try {
			List<Long> list=this.getHibernateTemplate().find(hql);
			if(list.size()>0&&list!=null){
				result=list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 
	 * @param begin
	 * @param limit
	 * @param searchVal
	 * @return
	 */
	public List<UserStrategy> findUserStrategyBySearchValueWithPagination(int begin, int limit, String searchVal) {
		String hql="FROM UserStrategy WHERE ustrategyname LIKE '%"+searchVal+"%' OR ustrategyplaintext LIKE '%"+searchVal+"%' OR tripUser.username LIKE '%"+searchVal+"%'";
		List<UserStrategy> results=null;
		try {
			List<UserStrategy> list=this.getHibernateTemplate().execute(new PageHibernateCallback<UserStrategy>(hql, null, begin, limit));
			if(list.size()>0&&list!=null){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据传入的标签数组查询标签对象集合
	 * @param tags
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<UserStrategyTag> findUserStrategyTagByNames(String[] tags) {
		String hql="FROM UserStrategyTag WHERE ustrategytagname=?";
		Set<UserStrategyTag> results=new HashSet<>();
		UserStrategyTag tag=null;
		try {
			if(tags.length>0){
				for(int i=0;i<tags.length;i++){
					List<UserStrategyTag> list=this.getHibernateTemplate().find(hql,tags[i]);
					if(list!=null&&list.size()>0){
						tag=list.get(0);
						results.add(tag);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}
	//TODO:需要添加评论功能
	/**
	 * 多条件统计文章的篇数
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param set 标签set集合
	 * @param recommandTag 三种情况：全部 精品用户(用户 ismaster) 优质精华(isessence)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountUserStrategyByMutapleCondition(String startDate, String endDate, Set<UserStrategyTag> set,
			String recommandTag) {
//		StringBuilder hql=new StringBuilder();
//		int result=0;
//		hql.append("SELECT COUNT(*) FROM UserStrategy WHERE 1=1");
//		if(!startDate.equals("")&&!endDate.equals("")){//判断startDate与endDate是否为空
//			hql.append("ustrategydate BETWEEN "+startDate+" AND "+endDate);
//		}
//		if(set!=null){
//			hql.append(" AND tags="+set);
//		}
//		if(recommandTag.equals("精品用户")){
//			hql.append(" AND tripUser.ismaster=0");
//		}else if(recommandTag.equals("优质精华")){
//			hql.append(" AND isesense=0");
//		}
		StringBuilder sql=new StringBuilder();
		int result=0;
		sql.append("SELECT COUNT(*) FROM TripUser tu,UserStrategy u,UserStrategyAndTags us,UserStrategyTag ut WHERE u.userid=tu.userid AND u.ustrategyid=us.ustrategyid AND us.ustrategytagid=ut.ustrategytagid");
		if(!startDate.equals("")&&!endDate.equals("")){//判断startDate与endDate是否为空
			sql.append(" AND u.ustrategydate BETWEEN '"+startDate+"' AND '"+endDate+"'");
		}
		if(set!=null){
			Iterator<UserStrategyTag> iterator=set.iterator();
			while(iterator.hasNext()){
				UserStrategyTag tag=iterator.next();
				sql.append(" AND ut.ustrategytagname='"+tag.getUstrategytagname()+"'");
			}
		}
		if(recommandTag.equals("精品用户")){
			sql.append(" AND tu.ismaster=0");
		}else if(recommandTag.equals("优质精华")){
			sql.append(" AND u.isesense=0");
		}
		try {
			System.out.println(sql.toString());
			List<Integer> list=this.getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery query=session.createSQLQuery(sql.toString());
					return query.list();
				}
			});
			//List<Long> list=this.getHibernateTemplate().find(hql.toString());
			if(list.size()>0&&list!=null){
				result=list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	//TODO:需要添加评论功能
	/**
	 * 有条件的查询攻略游记的数据集合
	 * @param begin 开始位置
	 * @param limit 每页显示记录数
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param set 标签数据集合
	 * @param recommandTag 推荐类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserStrategy> findUserStrategyByMutipleConditionWithPagination(int begin, int limit, String startDate,
			String endDate, Set<UserStrategyTag> set, String recommandTag) {
		StringBuilder sql=new StringBuilder();
		List<UserStrategy> results=null;
		sql.append("SELECT distinct u.ustrategyid,u.ustrategyname,u.ustrategydate,u.ustrategycoverstory,tu.username,u.ustrategyplaintext,u.uclickednum,u.ulikecount,u.ustrategycontent,ut.ustrategytagname FROM TripUser tu,UserStrategy u,UserStrategyAndTags us,UserStrategyTag ut WHERE u.userid=tu.userid AND u.ustrategyid=us.ustrategyid AND us.ustrategytagid=ut.ustrategytagid");
		if(!startDate.equals("")&&!endDate.equals("")){//判断startDate与endDate是否为空
			sql.append(" AND u.ustrategydate BETWEEN '"+startDate+"' AND '"+endDate+"'");
		}
		if(set!=null){
			Iterator<UserStrategyTag> iterator=set.iterator();
			while(iterator.hasNext()){
				UserStrategyTag tag=iterator.next();
				sql.append(" AND ut.ustrategytagname='"+tag.getUstrategytagname()+"'");
			}
		}
		if(recommandTag.equals("精品用户")){
			sql.append(" AND tu.ismaster=0");
		}else if(recommandTag.equals("优质精华")){
			sql.append(" AND u.isesense=0");
		}
		try {
			System.out.println(sql.toString());
			List tempList=this.getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery query=session.createSQLQuery(sql.toString());
					return query.list();
				}
			});
			List<UserStrategy> list=new ArrayList<>();
			UserStrategy s=null;
			//参数表：u.ustrategyid,u.ustrategyname,u.ustrategydate,u.ustrategycoverstory,tu.username,u.ustrategyplaintext,u.uclickednum,u.ulikecount,u.ustrategycontent,,ut.ustrategytagname
			for(int i=0;i<tempList.size();i++){
				s=new UserStrategy();
				Object[] object=(Object[]) tempList.get(i);
				s.setUstrategyid((String)object[0]);
				s.setUstrategyname((String)object[1]);
				s.setUstrategydate((Date)object[2]);
				s.setUstrategycoverstory((String)object[3]);
				TripUser user=new TripUser();
				user.setUsername((String)object[4]);
				s.setTripUser(user);
				s.setUstrategyplaintext((String)object[5]);
				s.setUclickednum((Integer)object[6]);
				s.setUlikecount((Integer)object[7]);
				s.setUstrategycontent((String)object[8]);
				Set<UserStrategyTag> t=new HashSet<>();
				UserStrategyTag ut=new UserStrategyTag();
				ut.setUstrategytagname((String)object[9]);
				t.add(ut);
				s.setTags(t);
				list.add(s);
			}
			//List<UserStrategy> list=this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql.toString(), null, begin, limit));
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据用户对象查询相关攻略有几篇
	 * @param existUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountUserStrategyByTripUser(TripUser existUser) {
		String hql="SELECT COUNT(*) FROM UserStrategy WHERE tripUser=?";
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
	 * 带分页的查询相关用户的攻略文章
	 * @param begin
	 * @param limit
	 * @param existUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserStrategy> findUserStrategyByTripUserWithPagination(int begin, int limit, TripUser existUser) {
		String hql="FROM UserStrategy WHERE tripUser=?";
		List<UserStrategy> results=null;
		try {
			List<UserStrategy> list=this.getHibernateTemplate().executeFind(new PageHibernateCallback<UserStrategy>(hql, new Object[]{existUser}, begin, limit));
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					UserStrategy s=list.get(i);
					s.setUstrategycontent(DomUtil.subStringHTML(s.getUstrategycontent(), 800,"……"));
				}
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据用户对象查询相关攻略
	 * @param currentUser 当前用户对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserStrategy> findUserStrategyByTripUser(TripUser currentUser) {
		String hql="FROM UserStrategy WHERE tripUser=?";
		List<UserStrategy> results=null;
		try {
			List<UserStrategy> list=this.getHibernateTemplate().find(hql,currentUser);
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据标签名查找单个攻略标签对象
	 * @param tagName 标签名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public UserStrategyTag findUserStrategyTagBySingleName(String tagName) {
		String hql="FROM UserStrategyTag WHERE ustrategytagname=?";
		UserStrategyTag result=null;
		try {
			List<UserStrategyTag> list=this.getHibernateTemplate().find(hql,tagName);
			if(list!=null&&list.size()>0){
				result=list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 检查点赞情况
	 * @param userid
	 * @param id
	 * @param type strategy album skillacademy
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int checkClickZanStatus(String userid, String id, String type) {
		String hql="FROM ClickZan WHERE userid=? AND topicid=? AND topictype=?";
		int result=0;
		try {
			List<ClickZan> list=this.getHibernateTemplate().find(hql,userid,id,type);
			if(list!=null&&list.size()>0){
				result=0;
			}else{
				result=1;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 点赞或者是取消赞
	 * @param userid
	 * @param strategyId
	 * @param type
	 * @param operateType
	 * @return
	 */
	@Transactional
	public String clickOrCancelZan(String userid, String strategyId, String type, String operateType) {
		String result=null;
		try {
			if(operateType.equals("zan")){
				ClickZan zan=new ClickZan();
				zan.setTopicid(strategyId);
				zan.setTopictype(type);
				zan.setUserid(userid);
				if(type.equals("strategy")){
					UserStrategy existStrategy=this.findStrategyByStrategyId(strategyId);
					existStrategy.setUlikecount(existStrategy.getUlikecount()+1);
					this.getHibernateTemplate().update(existStrategy);
				}
				this.getHibernateTemplate().save(zan);
				result="isAgreed";
			}else if(operateType.equals("cancel")){
				ClickZan existZan=this.findClickZanByMutipleCondition(userid,strategyId,type);
				if(type.equals("strategy")){
					UserStrategy existStrategy=this.findStrategyByStrategyId(strategyId);
					existStrategy.setUlikecount(existStrategy.getUlikecount()-1);
					this.getHibernateTemplate().update(existStrategy);
				}
				this.getHibernateTemplate().delete(existZan);
				result="disagree";
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public ClickZan findClickZanByMutipleCondition(String userid,String strategyId,String type){
		String hql="FROM ClickZan WHERE userid=? AND topicid=? AND topictype=?";
		ClickZan result=null;
		try {
			List<ClickZan> list=this.getHibernateTemplate().find(hql,userid,strategyId,type);
			if(list!=null&&list.size()>0){
				result=list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 找出所有评论
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Comments> findAllComments() {
		String hql="FROM Comments ORDER BY commentdate DESC";
		List<Comments> results=new ArrayList<>();
		try {
			List<Comments> list=this.getHibernateTemplate().find(hql);
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

}
