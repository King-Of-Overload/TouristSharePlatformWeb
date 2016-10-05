package alan.share.index.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import alan.share.user.model.Comments;
import alan.share.user.model.Reply;
import alan.share.user.model.TripUser;

public class IndexDao extends HibernateDaoSupport {

	public Comments saveComments(Comments comment) {
		Comments result=comment;
		try {
			this.getHibernateTemplate().save(comment);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据评论id找到评论对象
	 * @param commentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Comments findCommentByCId(String commentId) {
		String hql="FROM Comments WHERE commentid=?";
		Comments comment=null;
		try {
			List<Comments> list=this.getHibernateTemplate().find(hql,commentId);
			if(list!=null&&list.size()>0){
				comment=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return comment;
	}

	/**
	 * 保存回复对象
	 * @param reply
	 * @return
	 */
	@Transactional
	public Reply saveReply(Reply reply) {
		try {
			this.getHibernateTemplate().save(reply);
		} catch (Exception e) {
		}
		return reply;
	}

	/**
	 * 根据类型id找到评论集合
	 * @param topicId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Comments> findCommentsListByTopicId(String topicId) {
		String hql="FROM Comments WHERE topicid=? ORDER BY commentdate";
		List<Comments> results=null;
		try {
			List<Comments> list=this.getHibernateTemplate().find(hql,topicId);
			if(list!=null&&!list.isEmpty()){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据评论id找到下面的回复
	 * @param commentid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Reply> findReplyListByComment(Comments comment) {
		String hql="FROM Reply WHERE comment=? ORDER BY replydate";
		List<Reply> result=null;
		try {
			List<Reply> list=this.getHibernateTemplate().find(hql,comment);
			if(null!=list&&!list.isEmpty()){
				result=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据主题id统计评论数
	 * @param topicid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountCommentsNumber(String topicid) {
		String hql="SELECT COUNT(*) FROM Comments WHERE topicid=?";
		int reuslt=0;
		try {
			List<Long> list=this.getHibernateTemplate().find(hql,topicid);
			if(list!=null&&list.size()>0){
				reuslt=list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return reuslt;
	}

	/**
	 * 根据发送者找出评论集合
	 * @param fromUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Comments> findCommentsListByFromUser(TripUser fromUser) {
		String hql="FROM Comments WHERE fromuser=?";
		List<Comments> results=new ArrayList<>();
		try {
			List<Comments> list=this.getHibernateTemplate().find(hql,fromUser);
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 查出所有的评论
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
