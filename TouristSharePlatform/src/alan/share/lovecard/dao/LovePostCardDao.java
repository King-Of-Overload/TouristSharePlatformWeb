package alan.share.lovecard.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import alan.share.lovecard.model.EventParticipate;
import alan.share.lovecard.model.LovePostCard;
import alan.share.lovecard.model.LovePostCardComment;
import alan.share.user.model.TripUser;
import alan.share.utils.PageHibernateCallback;

public class LovePostCardDao extends HibernateDaoSupport{

	/**
	 * 保存明信片对象
	 * @param card
	 * @return
	 */
	@Transactional
	public LovePostCard saveLovePostCard(LovePostCard card) {
		try {
			this.getHibernateTemplate().save(card);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return card;
	}

	/**
	 * 统计有多少个明信片活动
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountLovePostCards() {
		String hql="SELECT COUNT(*) FROM LovePostCard";
		int result=0;
		try {
			List<Long> list=this.getHibernateTemplate().find(hql);
			if(list!=null&&!list.isEmpty()){
				result=list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 带分页查询明信片集合
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<LovePostCard> findAllLovePostCardsWithPagination(int begin, int limit) {
		String hql="FROM LovePostCard ORDER BY lovedeadline";
		List<LovePostCard> results=new ArrayList<>();
		try {
			List<LovePostCard> list=this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, null, begin, limit));
			if(list!=null&&!list.isEmpty()){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据活动id查找明信片活动对象
	 * @param loveid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public LovePostCard findLovePostCardByLoveId(String loveid) {
		String hql="FROM LovePostCard WHERE loveid=?";
		LovePostCard result=new LovePostCard();
		try {
			List<LovePostCard> list=this.getHibernateTemplate().find(hql,loveid);
			if(list!=null&&!list.isEmpty()){
				result=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 检查用户参加活动状态
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String checkUserJoinStatus(TripUser user,LovePostCard card) {
		String hql="FROM EventParticipate WHERE card=? AND user=?";
		String result="";
		try {
			List<EventParticipate> list=this.getHibernateTemplate().find(hql,card,user);
			if(list!=null&&!list.isEmpty()){
				result="hasJoined";
			}else{
				result="notJoin";
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 保存参加对象
	 * @param p
	 * @return
	 */
	@Transactional
	public Boolean saveEventParticipate(EventParticipate p) {
		Boolean result=false;
		try {
			this.getHibernateTemplate().save(p);
			result=true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据明信片活动对象查询留言集合
	 * @param card
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LovePostCardComment> findCommentsByLovePostCard(LovePostCard card) {
		String hql="FROM LovePostCardComment WHERE postCard=? ORDER BY lovedate";
		List<LovePostCardComment> results=new ArrayList<>();
		try {
			List<LovePostCardComment> list=this.getHibernateTemplate().find(hql,card);
			if(list!=null&&!list.isEmpty()){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 保存用户活动详情留言对象
	 * @param comment
	 * @return
	 */
	@Transactional
	public LovePostCardComment saveLovePostCardComment(LovePostCardComment comment) {
		try {
			this.getHibernateTemplate().save(comment);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return comment;
	}

	/**
	 * 统计留言个数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountComments() {
		String hql="SELECT COUNT(*) FROM LovePostCardComment";
		int result=0;
		try {
			List<Long> list=this.getHibernateTemplate().find(hql);
			if(list!=null&&!list.isEmpty()){
				result=list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 查找所有留言对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LovePostCardComment> findAllPostCardComments() {
		String hql="FROM LovePostCardComment ORDER BY lovedate DESC";
		List<LovePostCardComment> results=new ArrayList<>();
		try {
			List<LovePostCardComment> list=this.getHibernateTemplate().find(hql);
			if(list!=null&&!list.isEmpty()){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据用户对象查找发布活动
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LovePostCard> findAllPostCardByUser(TripUser user) {
		String hql="FROM LovePostCard WHERE user=?";
		List<LovePostCard> results=new ArrayList<>();
		try {
			List<LovePostCard> list=this.getHibernateTemplate().find(hql,user);
			if(list!=null&&!list.isEmpty()){
				for(LovePostCard card:list){
					int commentNumber=this.findCountCommentsByPostCard(card);
					card.setCommentNumber(commentNumber);
					int joinNumber=this.findCountParticipateNumByPostCard(card);
					card.setJoinNumber(joinNumber);
				}
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据明信片活动对象统计参与人数
	 * @param card
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountParticipateNumByPostCard(LovePostCard card) {
		String hql="SELECT COUNT(*) FROM EventParticipate WHERE card=?";
		int result=0;
		try {
			List<Long> list=this.getHibernateTemplate().find(hql,card);
			if(list!=null&&!list.isEmpty()){
				result=list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据明信片对象统计留言个数
	 * @param card
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountCommentsByPostCard(LovePostCard card) {
		String hql="SELECT COUNT(*) FROM LovePostCardComment WHERE postCard=?";
		int result=0;
		try {
			List<Long> list=this.getHibernateTemplate().find(hql,card);
			if(list!=null&&!list.isEmpty()){
				result=list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据用户对象查找参加的活动
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EventParticipate> findLovePostCardFromJoinListByUser(TripUser user) {
		String hql="FROM EventParticipate WHERE user=?";
		List<EventParticipate> results=new ArrayList<>();
		try {
			List<EventParticipate> list=this.getHibernateTemplate().find(hql,user);
			if(list!=null&&!list.isEmpty()){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据用户对象与明信片对象查找参加集合
	 * @param user
	 * @param postCard
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EventParticipate> findUserJoinOrNotByUserAndPostCard(TripUser user, LovePostCard postCard) {
		String hql="FROM EventParticipate WHERE user=? AND card=?";
		List<EventParticipate> results=new ArrayList<>();
		try {
			List<EventParticipate> list=this.getHibernateTemplate().find(hql,user,postCard);
			if(list!=null&&!list.isEmpty()){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据明信片对象查询被选中的集合
	 * @param card
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EventParticipate> findEventParticipatesIsChosenByCard(LovePostCard card) {
		String hql="FROM EventParticipate WHERE card=? AND ischosen=0";
		List<EventParticipate> results=new ArrayList<>();
		try {
			List<EventParticipate> list=this.getHibernateTemplate().find(hql,card);
			if(list!=null&&!list.isEmpty()){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据用户对象与明信片对象查找活动参与对象
	 * @param user
	 * @param card
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public EventParticipate findEventParticipateByUserAndPostCard(TripUser user, LovePostCard card) {
		String hql="FROM EventParticipate WHERE user=? AND card=?";
		EventParticipate result=new EventParticipate();
		try {
			List<EventParticipate> list=this.getHibernateTemplate().find(hql,user,card);
			if(list!=null&&!list.isEmpty()){
				result=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 更新参与对象
	 * @param p
	 * @return
	 */
	public Boolean updateEventParticipate(EventParticipate p) {
		Boolean result=false;
		try {
			this.getHibernateTemplate().update(p);
			result=true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 统计已经确认的用户
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountEventParticipateBeChoosed() {
		String hql="SELECT COUNT(*) FROM EventParticipate WHERE ischosen=0";
		int result=0;
		try {
			List<Long> list=this.getHibernateTemplate().find(hql);
			if(list!=null&&!list.isEmpty()){
				result=list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 查出所有明信片活动
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LovePostCard> findAllPostCard() {
		String hql="FROM LovePostCard ORDER BY lovestarttime DESC";
		List<LovePostCard> results=new ArrayList<>();
		try {
			List<LovePostCard> list=this.getHibernateTemplate().find(hql);
			if(list!=null&&!list.isEmpty()){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 查找单一留言对象获取地址
	 * @param u
	 * @param card
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public LovePostCardComment findSingleCommentByCardAndUser(TripUser u, LovePostCard card) {
		String hql="FROM LovePostCardComment WHERE user=? AND postCard=?";
		LovePostCardComment result=new LovePostCardComment();
		try {
			List<LovePostCardComment> list=this.getHibernateTemplate().find(hql,u,card);
			if(list!=null&&!list.isEmpty()){
				result=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	
}
