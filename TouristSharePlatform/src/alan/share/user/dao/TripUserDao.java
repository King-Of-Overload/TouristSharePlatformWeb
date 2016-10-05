package alan.share.user.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import alan.share.officialstrategy.model.City;
import alan.share.product.model.UserCollections;
import alan.share.user.model.PayAttention;
import alan.share.user.model.SecrectMessage;
import alan.share.user.model.TripUser;
import alan.share.user.model.Visitor;

/**
 * 用户业务逻辑操作
 * 
 * @author Alan
 *
 */
public class TripUserDao extends HibernateDaoSupport {

	// 查询已有用户
	public TripUser findByUserName(String username) {
		String hql = "FROM TripUser WHERE username=?";
		List<TripUser> list;
		try {
			list = this.getHibernateTemplate().find(hql, username);
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 将用户基本信息存入数据库
	public void save(TripUser user) {
		try {
			this.getHibernateTemplate().save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 根据激活码查找用户
	public TripUser findByActiveCode(String code) {
		String hql = "FROM TripUser WHERE useractivecode=?";
		List<TripUser> list;
		try {
			list = this.getHibernateTemplate().find(hql, code);
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 更新激活后的用户基本信息
	@Transactional
	public void update(TripUser existUser) {
		try {
			this.getHibernateTemplate().update(existUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 用户登录
	public TripUser findByUserNameAndPassword(String username, String password) {
		String hql = "FROM TripUser WHERE username=? AND userpassword=?";
		try {
			List<TripUser> list = this.getHibernateTemplate().find(hql, username, password);
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新用户头像
	 * 
	 * @param userId
	 *            用户id
	 * @param headerImage
	 *            用户头像
	 * @return
	 */
	public Boolean updateUserHeaderImage(TripUser user, String headerImage) {
		Boolean result = false;
		TripUser newUser = null;
		try {
			newUser = new TripUser();
			user.setHeaderimage(headerImage);
			newUser = user;
			this.getHibernateTemplate().update(newUser);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (newUser != null)
				newUser = null;
		}
		return result;
	}

	/**
	 * 根据cookie值查询用户
	 * 
	 * @param userCookieValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TripUser findByUserCookieValue(String userCookieValue) {
		String hql = "FROM TripUser WHERE usercookievalue=?";
		List<TripUser> list;
		try {
			list = this.getHibernateTemplate().find(hql, userCookieValue);
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param userid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TripUser findUserByUserId(String userid) {
		String hql = "FROM TripUser WHERE userid=?";
		TripUser result = null;
		try {
			List<TripUser> list = this.getHibernateTemplate().find(hql, userid);
			if (list.size() > 0 && list != null) {
				result = list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 统计粉丝数
	 * 
	 * @param userid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountPayAttentionByFollowId(String userid) {
		String hql = "SELECT COUNT(*) FROM PayAttention WHERE followid=?";
		int result = 0;
		try {
			List<Long> list = this.getHibernateTemplate().find(hql, userid);
			if (list != null && list.size() > 0) {
				result = list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据userid查询最近访问记录
	 * 
	 * @param userid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Visitor> findVisitorsByVisitorId(String userid) {
		String hql = "FROM Visitor WHERE visitorid=? ORDER BY visitdate desc";
		List<Visitor> results = null;
		try {
			List<Visitor> list = this.getHibernateTemplate().find(hql, userid);
			if (list != null && list.size() > 0) {
				results = list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 保存访问用户空间记录
	 * 
	 * @param currentUser
	 *            当前登录用户
	 * @param userid
	 *            被访问用户id
	 */
	@SuppressWarnings("unchecked")
	public void saveUserVisitRecord(Visitor v) {
		String hql="FROM Visitor WHERE user=? AND visitorid=?";
		try {
			List<Visitor> list=this.getHibernateTemplate().find(hql,v.getUser(),v.getVisitorid());
			if(list!=null&&list.size()>0){
				Visitor currentVisitor=list.get(0);
				Calendar c=Calendar.getInstance();
				c.setTime(new Date());
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				currentVisitor.setVisitdate(format.parse(format.format(c.getTime())));
				this.getHibernateTemplate().update(currentVisitor);
			}else{
				this.getHibernateTemplate().save(v);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据用户对象查找出关注集合
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PayAttention> findFocusListByUserObject(TripUser user) {
		String hql="FROM PayAttention WHERE user=?";
		List<PayAttention> results=null;
		try {
			List<PayAttention> list=this.getHibernateTemplate().find(hql,user);
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 修改当前用户的关注状态
	 * @param operateUser 当前操作用户
	 * @param userid 被关注的用户
	 * @param mode 模式 
	 * @return
	 */
	public String saveUserFocusOrCancelStatus(TripUser operateUser, String userid, String mode) {
		String result="";
		PayAttention p=null;
		try {
			if(mode.equals("focus")){//如果执行关注操作
				p=new PayAttention();
				p.setUser(operateUser);
				p.setFollowid(userid);
				this.getHibernateTemplate().save(p);
				result="focusSuccess";
			}else if(mode.equals("cancel")){//如果执行取消关注操作
				List<PayAttention> focusList=this.findFocusListByUserObject(operateUser);
				PayAttention existP=null;
				if(focusList!=null&&focusList.size()>0){
					for(PayAttention pa:focusList){
						if(pa.getFollowid().equals(userid)){
							existP=pa;
							break;
						}
					}
					this.getHibernateTemplate().delete(existP);
					result="cancelSuccess";
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据用户id查找出粉丝集合
	 * @param userid 用户id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PayAttention> findPayAttentionListByUserId(String userid) {
		String hql="FROM PayAttention WHERE followid=?";
		List<PayAttention> results=null;
		try {
			List<PayAttention> list=this.getHibernateTemplate().find(hql,userid);
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 保存悄悄话信息
	 * @param smessage 悄悄话对象
	 * @author Alan
	 * @return
	 */
	@Transactional
	public Boolean saveUserSecretWord(SecrectMessage smessage) {
		Boolean result=false;
		try {
			this.getHibernateTemplate().save(smessage);
			result=true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 读取当前用户未读消息数
	 * @param currentUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountUnReadMessageSize(TripUser currentUser) {
		String hql="SELECT COUNT(*) FROM SecrectMessage WHERE receiver=? AND isread=1";
		int result=0;
		try {
			List<Long> list=this.getHibernateTemplate().find(hql,currentUser);
			if(list!=null&&list.size()>0){
				result=list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据接收者查出所有留言
	 * @param visitUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SecrectMessage> findMessageByReceiver(TripUser visitUser) {
		String hql="FROM SecrectMessage WHERE receiver=?";
		List<SecrectMessage> results=null;
		try {
			List<SecrectMessage> list=this.getHibernateTemplate().find(hql,visitUser);
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据id查询悄悄话对象
	 * @param messageid 悄悄话id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SecrectMessage findSecretMessageByMid(String messageid) {
		String hql="FROM SecrectMessage WHERE smessageid=?";
		SecrectMessage result=null;
		try {
			List<SecrectMessage> list=this.getHibernateTemplate().find(hql,messageid);
			if(list!=null&&list.size()>0){
				result=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 更新悄悄话对象
	 * @param existMessage 悄悄话对象
	 * @return
	 */
	@Transactional
	public Boolean updateSecretMessage(SecrectMessage existMessage) {
		Boolean result=false;
		try {
			this.getHibernateTemplate().update(existMessage);
			result=true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据悄悄话对象删除该条消息
	 * @param message 悄悄话对象
	 * @return
	 */
	@Transactional
	public Boolean removeSecrectMessageById(SecrectMessage message) {
		Boolean result=false;
		try {
			this.getHibernateTemplate().delete(message);
			result=true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据用户对象查询收藏集合
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserCollections> findUserCollectionsByTripUser(TripUser user) {
		String hql="FROM UserCollections WHERE user=? ORDER BY collectdate DESC";
		List<UserCollections> results=new ArrayList<>();
		try {
			List<UserCollections> list=this.getHibernateTemplate().find(hql,user);
			if(list!=null&&!list.isEmpty()){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据城市找出用户
	 * @param city
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TripUser> findUsersByCity(City city) {
		String hql="FROM TripUser WHERE city=?";
		List<TripUser> results=new ArrayList<>();
		try {
			List<TripUser> list=this.getHibernateTemplate().find(hql,city);
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 找出所有未读消息
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SecrectMessage> findUnReadMessageByUser(TripUser user) {
		String hql="FROM SecrectMessage WHERE receiver=? AND isread=1";
		List<SecrectMessage> results=new ArrayList<>();
		try {
			List<SecrectMessage> list=this.getHibernateTemplate().find(hql,user);
			if(list!=null&&!list.isEmpty()){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

}
