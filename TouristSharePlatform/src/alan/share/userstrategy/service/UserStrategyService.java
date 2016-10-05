package alan.share.userstrategy.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import alan.share.officialstrategy.model.City;
import alan.share.skillacademy.model.SkillAcademy;
import alan.share.user.model.Comments;
import alan.share.user.model.TripUser;
import alan.share.userstrategy.dao.UserStrategyDao;
import alan.share.userstrategy.model.UserStrategy;
import alan.share.userstrategy.model.UserStrategyTag;
import alan.share.utils.PageBean;
import alan.share.utils.StringUtil;

public class UserStrategyService {
	private UserStrategyDao userStrategyDao;

	public UserStrategyDao getUserStrategyDao() {
		return userStrategyDao;
	}

	public void setUserStrategyDao(UserStrategyDao userStrategyDao) {
		this.userStrategyDao = userStrategyDao;
	}

	/**
	 * 查找所有攻略标签
	 * 
	 * @return
	 */
	public List<UserStrategyTag> findAllUserStrategyTags() {
		return userStrategyDao.findAllUserStrategyTags();
	}

	/**
	 * 封装要保存的攻略对象
	 * 
	 * @param existUser
	 *            所属用户
	 * @param tougao
	 *            是否投稿
	 * @param strategyName
	 *            攻略名称
	 * @param strategyContent
	 *            攻略内容
	 * @param coveryStory
	 *            简介
	 * @param city
	 *            城市
	 * @param tags
	 *            标签
	 * @return
	 */
	public Boolean saveUserStrategy(TripUser existUser, String tougao, String strategyName, String strategyContent,
			String coveryStory, City city, String tags, String plainText,String id) {
		Boolean result = false;
		try {
			UserStrategy userStrategy = new UserStrategy();
			userStrategy.setUstrategyid(id);
			userStrategy.setUstrategyname(strategyName);// 攻略名称
			userStrategy.setUstrategycontent(strategyContent);// html攻略内容
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			userStrategy.setUstrategydate(format.parse(format.format(new Date())));// 设置时间
			userStrategy.setUclickednum(0);// 点击量
			userStrategy.setUlikecount(0);// 点赞数
			userStrategy.setUstrategycoverstory(coveryStory);// 简介
			userStrategy.setTripUser(existUser);// 所属用户
			userStrategy.setCity(city);// 城市
			userStrategy.setUstrategyplaintext(plainText);// 纯文本
			String[] tagArray = tags.split(";");
			Set<UserStrategyTag> strategyTags = userStrategyDao.queryUserStrategyTagsByName(tagArray, tougao);
			userStrategy.setTags(strategyTags);
			result = userStrategyDao.saveUserStrategy(userStrategy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查出所有攻略
	 * 
	 * @return
	 */
	public List<UserStrategy> findAllUserStrategy() {
		return userStrategyDao.findAllUserStrategy();
	}

	/**
	 * 根据id查出攻略
	 * 
	 * @param uStrategyId
	 * @return
	 */
	public UserStrategy findStrategyByStrategyId(String uStrategyId) {
		return userStrategyDao.findStrategyByStrategyId(uStrategyId);
	}

	/**
	 * 带分页的查询所有攻略文章
	 * 
	 * @param page
	 * @return
	 */
	public PageBean<UserStrategy> findAllUserStrategyByPagination(int page) {
		PageBean<UserStrategy> pageBean = new PageBean<>();
		pageBean.setPage(page);// 设置当前页码
		int limit = 6;
		pageBean.setLimit(limit);// 设置每页显示的记录数
		// 设置总记录数
		int totalCount = 0;
		totalCount = userStrategyDao.findCountUserStrategy();// 总记录
		pageBean.setTotalCount(totalCount);// 设置总记录数
		// 总页数设置
		int totalPage = 0;
		if (totalCount <= pageBean.getLimit()) {
			totalPage = 1;
		} else {
			if (totalCount % limit == 0) {
				totalPage = totalCount/limit;
			} else {
				totalPage = totalCount / limit + 1;
			}
		}
		pageBean.setTotalPage(totalPage);// 设置总页数
		// 查询每页显示的数据集合
		int begin = (page - 1) * limit;// 从哪一个数据开始向下查询
		List<UserStrategy> list = userStrategyDao.findAllUserStrategyByPagination(begin, limit);
		pageBean.setList(list);// 设置数据集合
		return pageBean;
	}

	/**
	 * 攻略页面的搜索功能，根据关键字搜索相关用户与相关文章
	 * 
	 * @param searchVal
	 *            关键字
	 * @param page
	 *            当前页数
	 * @return
	 */
	public PageBean<UserStrategy> findUserStrategyBySearchValueWithPagination(String searchVal, int page) {
		PageBean<UserStrategy> pageBean = new PageBean<>();
		pageBean.setPage(page);// 设置当前页码
		int limit = 6;
		pageBean.setLimit(limit);// 设置每页显示的记录数
		// 设置总记录数
		int totalCount = 0;
		totalCount = userStrategyDao.findCountUserStrategyBySearchValue(searchVal);// 总记录
		pageBean.setTotalCount(totalCount);// 设置总记录数
		// 总页数设置
		int totalPage = 0;
		if (totalCount <= pageBean.getLimit()) {
			totalPage = 1;
		} else {
			if (totalCount % limit == 0) {
				totalPage = totalCount / limit;
			} else {
				totalPage = totalCount / limit + 1;
			}
		}
		pageBean.setTotalPage(totalPage);// 设置总页数
		// 查询每页显示的数据集合
		int begin = (page - 1) * limit;// 从哪一个数据开始向下查询
		List<UserStrategy> list = userStrategyDao.findUserStrategyBySearchValueWithPagination(begin, limit, searchVal);
		
		pageBean.setList(list);// 设置数据集合
		return pageBean;
	}

	/**
	 * 多条件分页查询用户攻略
	 * 
	 * @param page
	 *            当前页数
	 * @param strategyTag
	 *            攻略所属标签 全部 以及其它字符串
	 * @param startTime
	 *            开始时间 毫秒数
	 * @param endTime
	 *            结束时间 毫秒数
	 * @param recommandTag
	 *            全部 大师作品 优质精华
	 * @return
	 */
	public PageBean<UserStrategy> findUserStrategyByMutipleConditionWithPagination(int page, String strategyTag,
			String startTime, String endTime, String recommandTag) {
		PageBean<UserStrategy> pageBean = new PageBean<>();
		pageBean.setPage(page);// 设置当前页
		int limit = 6;
		pageBean.setLimit(limit);// 设置每页显示的记录数
		String startDate = "";
		String endDate = "";
		Set<UserStrategyTag> set = null;
		if (!startTime.equals("null") && !endTime.equals("null")) {
			Calendar startC = Calendar.getInstance();
			Calendar endC = Calendar.getInstance();
			startC.setTimeInMillis(Long.parseLong(startTime));
			endC.setTimeInMillis(Long.parseLong(endTime));
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			startDate = format.format(startC.getTime());
			endDate = format.format(endC.getTime());
		}
		if (!strategyTag.equals("全部")) {
			String[] tags = new String[] { strategyTag };
			set = userStrategyDao.findUserStrategyTagByNames(tags);
		}
		// 设置总记录数
		int totalCount = 0;
		totalCount = userStrategyDao.findCountUserStrategyByMutapleCondition(startDate, endDate, set, recommandTag);
		pageBean.setTotalCount(totalCount);// 设置总记录数
		// 总页数设置
		int totalPage = 0;
		if (totalCount <= pageBean.getLimit()) {
			totalPage = 1;
		} else {
			if (totalCount % limit == 0) {
				totalPage = totalCount / limit;
			} else {
				totalPage = totalCount / limit + 1;
			}
		}
		pageBean.setTotalPage(totalPage);// 设置总页数
		// 查询记录
		int begin = (page - 1) * limit;// 从哪一个数据开始向下查询
		List<UserStrategy> list = userStrategyDao.findUserStrategyByMutipleConditionWithPagination(begin, limit,
				startDate, endDate, set, recommandTag);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 带分页查询攻略数据
	 * 
	 * @param page
	 * @param existUser
	 * @return
	 */
	public PageBean<UserStrategy> findUserStrategyByTripUserWithPagination(int page, TripUser existUser) {
		PageBean<UserStrategy> pageBean = new PageBean<>();
		pageBean.setPage(page);// 当前页面
		int limit = 2;
		pageBean.setLimit(limit);// 设置每页显示的记录数
		// 设置总页数
		int totalCount = 0;
		totalCount = userStrategyDao.findCountUserStrategyByTripUser(existUser);// 总记录
		pageBean.setTotalCount(totalCount);// 设置总记录数
		// 总页数设置
		int totalPage = 0;
		if (totalCount <= pageBean.getLimit()) {
			totalPage = 1;
		} else {
			if (totalCount % limit == 0) {
				totalPage = totalCount / limit;
			} else {
				totalPage = totalCount / limit + 1;
			}
		}
		pageBean.setTotalPage(totalPage);// 设置总页数
		int begin =(page - 1) * limit;// 从哪一个数据开始向下查询
		List<UserStrategy> list=userStrategyDao.findUserStrategyByTripUserWithPagination(begin,limit,existUser);
		pageBean.setList(list);
		return pageBean;
	}

	public List<UserStrategy> findUserStrategyByTripUser(TripUser visitUser) {
		return userStrategyDao.findUserStrategyByTripUser(visitUser);
	}

	/**
	 * 根据标签名查找攻略标签对象
	 * @param tagName 攻略标签名
	 * @return
	 */
	public UserStrategyTag findUserStrategyTagByTagName(String tagName) {
		return userStrategyDao.findUserStrategyTagBySingleName(tagName);
	}

	/**
	 * 检查点赞情况
	 * @param userid
	 * @param strategyId
	 * @param type
	 * @return
	 */
	public int checkClickZanStatus(String userid, String id,String type) {
		return userStrategyDao.checkClickZanStatus(userid,id,type);
	}

	/**
	 * 点赞或者是取消赞
	 * @param userid 用户id
	 * @param strategyId 攻略id
	 * @param type 类型
	 * @param operateType 操作类型
	 * @return
	 */
	public String clickOrCancelZan(String userid, String strategyId, String type, String operateType) {
		return userStrategyDao.clickOrCancelZan(userid,strategyId,type,operateType);
	}

	/**
	 * 找出所有评论
	 * @return
	 */
	public List<Comments> findAllComments() {
		return userStrategyDao.findAllComments();
	}

}
