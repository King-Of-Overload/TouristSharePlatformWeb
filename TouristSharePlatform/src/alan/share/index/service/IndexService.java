package alan.share.index.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import alan.share.index.dao.IndexDao;
import alan.share.photo.dao.PhotoDao;
import alan.share.photo.model.UserAlbums;
import alan.share.photo.model.UserPhotos;
import alan.share.product.model.Product;
import alan.share.skillacademy.dao.SkillAcademyDao;
import alan.share.skillacademy.model.SkillAcademy;
import alan.share.user.dao.TripUserDao;
import alan.share.user.model.Comments;
import alan.share.user.model.Reply;
import alan.share.user.model.TripUser;
import alan.share.userstrategy.dao.UserStrategyDao;
import alan.share.userstrategy.model.UserStrategy;
import alan.share.utilbean.index.IndexBannerBean;
import alan.share.utils.DomUtil;
import alan.share.utils.PageBean;
import alan.share.utils.UUIDUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class IndexService {
	private UserStrategyDao strategyDao;
	private PhotoDao photoDao;
	private SkillAcademyDao academyDao;
	private TripUserDao userDao;
	private IndexDao indexDao;
	
	
	
	public IndexDao getIndexDao() {
		return indexDao;
	}

	public void setIndexDao(IndexDao indexDao) {
		this.indexDao = indexDao;
	}

	public TripUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(TripUserDao userDao) {
		this.userDao = userDao;
	}

	public UserStrategyDao getStrategyDao() {
		return strategyDao;
	}

	public void setStrategyDao(UserStrategyDao strategyDao) {
		this.strategyDao = strategyDao;
	}

	public PhotoDao getPhotoDao() {
		return photoDao;
	}

	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	public SkillAcademyDao getAcademyDao() {
		return academyDao;
	}

	public void setAcademyDao(SkillAcademyDao academyDao) {
		this.academyDao = academyDao;
	}

	/**
	 * 获取首页轮播图数据
	 * 此处由于后台未开发，先进行数据模拟
	 * @return
	 */
	public List<IndexBannerBean> initialIndexBannerList() {
		List<IndexBannerBean> results=new ArrayList<>();
		UserStrategy s1=strategyDao.findStrategyByStrategyId("21d9af33c14c8ff203a45340903b904d");//香港攻略
		UserStrategy s2=strategyDao.findStrategyByStrategyId("9b0e2b8b0e47fce218d67b06114d2858");//厦门花的时光旅行
		IndexBannerBean bean1=this.packStrategyIndexBannerObject(s1, "你好，HongKong","香港作为中西文化汇流的聚点，高度的文化差异造就了香港成为国际性的大都会","这里可谓包罗万象，这是个浓缩的精华的世界，是繁华热闹的不夜城","太平山缆车、浅水湾海景、维港夜景让这颗东方明珠浪漫依然","让我们一起来见识独具魅力的香港面貌吧","userStrategyImages/images/20160805/1470395524936070528.jpg");
		IndexBannerBean bean2=this.packStrategyIndexBannerObject(s2, "海中之城，城中存海", "厦门就如镶嵌在俗世里的蓬莱","如今慕那悠闲与慵懒劲儿而来的人越来越多", "但始终堵不住它若隐若现的灵气外露", "沉静下来，用心才能感受到厦门的生活状态和不一样的小情怀","userStrategyImages/images/20160730/1469880297957027500.jpg");
		results.add(bean1);
		results.add(bean2);
		UserAlbums album1=photoDao.findAlbumByAlbumId("548aa15efe9032bb0bda24af5df451f5");//扬州瘦西湖
		UserAlbums album2=photoDao.findAlbumByAlbumId("ce8ab558d59ae1f169339ae59769264b");//迪士尼
		IndexBannerBean bean3=this.packAlbumIndexBannerObject(album1,"夏记晚风和温柔的你","烟雨之后 再回眸我的泪比西湖还瘦","湖心停留 心事不走却看路人过扬州","烟雨之后 撑一把油纸伞 瘦西湖重走","雨水淋湿 那些我的心事 泪未干语不休","useralbums/8d39695ec5ac4c2cb579e2cc433426cc/夏记 烟火 晚风 和 温柔的你/20160816132256_702.jpg");
		IndexBannerBean bean4=this.packAlbumIndexBannerObject(album2,"上海迪士尼度假区","亲临上海迪士尼","少女心爆棚","漫步童话时光","Lets GO","useralbums/e355fddd6bba4793bc6f5cf990068877/上海迪士尼度假区/20160805193514_530.jpg");
		results.add(bean3);
		results.add(bean4);
		SkillAcademy aca1=academyDao.findSkillAcademyBySkillId("a918d65231c24cf6a72eb2b8d5c2e4c0");//夏日校园写真
		SkillAcademy aca2=academyDao.findSkillAcademyBySkillId("f5e86772610b4b4faa0021b856851098");//古风
		IndexBannerBean bean5=this.packAcademyIndexBannerObject(aca1,"夏日校园写真","一提到夏天，好像总绕不开校园","因为暑假、高考、毕业等记忆充满了夏日校园的每一个角落","而对摄影师来说，夏天也是拍摄校园题材的好时机","所以趁着天气正好，策划一组夏日校园写真吧","userStrategyImages/images/20160828/36514166_1024.jpg");
		IndexBannerBean bean6=this.packAcademyIndexBannerObject(aca2,"梦幻古风后期思路","在调色的时候就想做一个暗调外加带点神秘色彩的主题","拍摄时妹子带了鹿角","于是就想到一个加萤火虫的效果","感兴趣的赶紧猛戳","userStrategyImages/images/20160816/1471327252710002600.jpg");
		results.add(bean5);
		results.add(bean6);
		return results;
	}
	
	private IndexBannerBean packAcademyIndexBannerObject(SkillAcademy aca,String name,String p1,String p2,String p3,String p4,String img){
		IndexBannerBean bean=new IndexBannerBean();
		bean.setCoverImage(img);
		bean.setId(aca.getSkilid());
		bean.setName(name);
		bean.setP1(p1);
		bean.setP2(p2);
		bean.setP3(p3);
		bean.setP4(p4);
		bean.setType("skillacademy");
		return bean;
	}
	
	private IndexBannerBean packStrategyIndexBannerObject(UserStrategy s,String name,String p1,String p2,String p3,String p4,String img){
		IndexBannerBean bean=new IndexBannerBean();
		bean.setCoverImage(img);
		bean.setId(s.getUstrategyid());
		bean.setName(name);
		bean.setP1(p1);
		bean.setP2(p2);
		bean.setP3(p3);
		bean.setP4(p4);
		bean.setType("strategy");
		return bean;
	}
	
	private IndexBannerBean packAlbumIndexBannerObject(UserAlbums a,String name,String p1,String p2,String p3,String p4,String img){
		IndexBannerBean bean=new IndexBannerBean();
		bean.setCoverImage(img);
		bean.setId(a.getAlbumid());
		bean.setName(name);
		bean.setP1(p1);
		bean.setP2(p2);
		bean.setP3(p3);
		bean.setP4(p4);
		bean.setType("album");
		return bean;
	}

	/**
	 * 将set集合中的数据抽取成攻略集合
	 * @param strategies
	 * @return
	 */
	public List<UserStrategy> extractListFromUserStrategyTagSet(Set<UserStrategy> strategies) {
		List<UserStrategy> results=new ArrayList<>();
		Iterator<UserStrategy> iterator=strategies.iterator();
			while(iterator.hasNext()){
				UserStrategy s=iterator.next();
				s.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(s.getUstrategycontent()));
				results.add(s);
			}
			int length=0;
			if(results.size()>10){
				length=10;
			}else{
				length=results.size();
			}
			results.subList(0, length);
		return results;
	}
	


	public void setStrategyListCoverImage(List<UserStrategy> list) {
		for (UserStrategy u : list) {
			u.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(u.getUstrategycontent()));
		}
	}

	/**
	 * 设置产品封面
	 * @param list
	 */
	public void setProductListCoverImage(List<Product> list) {
		if(null!=list&&list.size()>0){
			for(Product p:list){
				p.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(p.getPcontent()));
			}
		}
	}

	/**
	 * 将pageBean 封装成json对象
	 * @param strategies
	 * @return
	 */
	public JSONObject enstoreUserStrategyPageBeanListToJSONArray(PageBean<UserStrategy> strategies) throws Exception{
		JSONObject object=new JSONObject();
		JSONArray strs=null;
		JSONObject singleStrategy=null;
		if(strategies!=null){
			object.put("page",strategies.getPage());
			object.put("totalCount", strategies.getTotalCount());//总记录数
			object.put("totalPage", strategies.getTotalPage());//总页数
			object.put("limit", strategies.getLimit());//每页记录数
			if(null!=strategies.getList()&&!strategies.getList().isEmpty()){
				strs=new JSONArray();
				for(UserStrategy s:strategies.getList()){
					singleStrategy=new JSONObject();
					singleStrategy.put("ustrategyid", s.getUstrategyid());
					singleStrategy.put("ustrategyname", s.getUstrategyname());
					singleStrategy.put("uclickednum", s.getUclickednum());
					int commentNum=indexDao.findCountCommentsNumber(s.getUstrategyid());
					singleStrategy.put("commentNum", commentNum);
					singleStrategy.put("ustrategyplaintext", s.getUstrategyplaintext().substring(0, 150));
					singleStrategy.put("isesense", s.getIsesense());
					singleStrategy.put("coverImage", s.getCoverImage());
					strs.put(singleStrategy);
				}
				object.put("list", strs);//攻略记录
			}else{
				object.put("list","");
			}
		}
		return object;
	}

	/**
	 * 保存评论信息
	 * @param userid 用户id 
	 * @param topicid 被评论对象id
	 * @param content 内容
	 * @param topicType 类型 strategy skillacademy
	 * @return
	 * @throws ParseException 
	 */
	public Comments saveComments(String userid, String topicid, String content, String topicType) throws ParseException {
		Comments comment=new Comments();
		comment.setCommentcontent(content);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		comment.setCommentdate(format.parse(format.format(calendar.getTime())));
		comment.setCommentid(UUIDUtils.getUUID());
		TripUser fromuser=userDao.findUserByUserId(userid);
		comment.setFromuser(fromuser);
		comment.setTopicid(topicid);
		comment.setTopictype(topicType);
		return indexDao.saveComments(comment);
	}

	/**
	 * 保存回复对象
	 * @param userid
	 * @param replyContent
	 * @param commentId
	 * @return
	 * @throws ParseException 
	 */
	public Reply saveReply(String userid, String replyContent, String commentId) throws ParseException {
		Reply reply=new Reply();
		Comments comment=indexDao.findCommentByCId(commentId);
		reply.setComment(comment);
		reply.setReplycontent(replyContent);
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		reply.setReplydate(format.parse(format.format(c.getTime())));
		reply.setReplyid(UUIDUtils.getUUID());
		TripUser sender =userDao.findUserByUserId(userid);
		reply.setSender(sender);
		return indexDao.saveReply(reply);
	}

	/**
	 * 根据类型id查询
	 * @param topicId
	 * @return
	 */
	public List<Comments> findCommentsListByTopicId(String topicId) {
		List<Comments> commentList=indexDao.findCommentsListByTopicId(topicId);
		if(null!=commentList&&!commentList.isEmpty()){
			for(int i=0;i<commentList.size();i++){
				Comments c=commentList.get(i);
				List<Reply> replyList=indexDao.findReplyListByComment(c);
				if(null!=replyList&&!replyList.isEmpty()){
					c.setReplies(replyList);
				}
			}
		}
		return commentList;
	}

	/**
	 * 将评论集合封装成json数组
	 * @param commentList
	 * @return
	 */
	public JSONArray enstoreCommentListToJSONArray(List<Comments> commentList) {
		JSONArray resultArray=null;
		JSONObject commentObject=null;
		JSONArray replyArray=null;
		JSONObject replyObject=null;
		DateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH.mm.ss");
		try {
			resultArray=new JSONArray();//结果jsonArray
			if(null!=commentList&&!commentList.isEmpty()){
				for(Comments comment:commentList){
					commentObject=new JSONObject();
					commentObject.put("commentid", comment.getCommentid());
					commentObject.put("topicid", comment.getTopicid());
					commentObject.put("topictype", comment.getTopictype());
					commentObject.put("commentcontent", comment.getCommentcontent());
					commentObject.put("fromUserName", comment.getFromuser().getUsername());
					commentObject.put("commentdate", format.format(comment.getCommentdate()));
					commentObject.put("headerImage", comment.getFromuser().getHeaderimage());
					replyArray=new JSONArray();
					List<Reply> reply=comment.getReplies();
					for(Reply r:reply){
						replyObject=new JSONObject();
						replyObject.put("replyid", r.getReplyid());
						replyObject.put("replycontent", r.getReplycontent());
						replyObject.put("replydate", format.format(r.getReplydate()));
						replyObject.put("replyName", r.getSender().getUsername());
						replyObject.put("headerImage", r.getSender().getHeaderimage());
						replyArray.put(replyObject);
					}
					commentObject.put("replyList", replyArray);
					resultArray.put(commentObject);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return resultArray;
	}
	
	/**
	 * 根据主题id查询评论数
	 * @return
	 */
	public int findCountCommentsNumberByTopicId(String topicid){
		return indexDao.findCountCommentsNumber(topicid);
	}

	/**
	 * 根据发送者找出评论集合
	 * @param fromUser
	 * @return
	 */
	public List<Comments> findCommentsListByFromUser(TripUser fromUser) {
		return indexDao.findCommentsListByFromUser(fromUser);
	}

	/**
	 * 根据评论对象查出回复集合
	 * @param c
	 * @return
	 */
	public List<Reply> findReplyListByComment(Comments c) {
		return indexDao.findReplyListByComment(c);
	}

	/**
	 * 添加评论
	 * @param userid
	 * @param commentList
	 * @return
	 */
	public List<Comments> addRelatedComments(String userid) {
		List<Comments> results=new ArrayList<>();
		List<Comments> commentsList=indexDao.findAllComments();
		if(null!=commentsList&&!commentsList.isEmpty()){
			for(Comments c:commentsList){
				if(c.getTopictype().equals("strategy")){
					UserStrategy strategy=strategyDao.findStrategyByStrategyId(c.getTopicid());
					if(null!=strategy){
						if(strategy.getTripUser().getUserid().equals(userid)){
							results.add(c);
						}
					}
				}else if(c.getTopictype().equals("skillacademy")){
					SkillAcademy academy=academyDao.findSkillAcademyBySkillId(c.getTopicid());
					if(null!=academy){
						if(academy.getUser().getUserid().equals(userid)){
							results.add(c);
						}
					}
				}
			}
		}
		return results;
	}
	
}
