package alan.share.lovecard.service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import alan.share.lovecard.dao.LovePostCardDao;
import alan.share.lovecard.model.EventParticipate;
import alan.share.lovecard.model.LovePostCard;
import alan.share.lovecard.model.LovePostCardComment;
import alan.share.user.dao.TripUserDao;
import alan.share.user.model.TripUser;
import alan.share.userstrategy.model.UserStrategy;
import alan.share.utils.CookieUtil;
import alan.share.utils.PageBean;
import alan.share.utils.UUIDUtils;

public class LovePostCardService {
	private TripUserDao userDao;
	private LovePostCardDao postCardDao;
	
	public LovePostCardDao getPostCardDao() {
		return postCardDao;
	}


	public void setPostCardDao(LovePostCardDao postCardDao) {
		this.postCardDao = postCardDao;
	}


	public TripUserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(TripUserDao userDao) {
		this.userDao = userDao;
	}


	/**
	 * 保存明信片活动对象
	 * @param lovetitle
	 * @param lovedescription
	 * @param lovenumber
	 * @param lovedeadline
	 * @param lovetags
	 * @param imgFile
	 * @param fileName
	 * @param username
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public LovePostCard saveLovePostCard(String lovetitle, String lovedescription, String lovenumber,
			String lovedeadline, String lovetags, File imgFile, String fileName,String userid) throws ParseException, IOException {
		TripUser currentUser=userDao.findUserByUserId(userid);
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		LovePostCard card=new LovePostCard();
		card.setLovecontent(lovedescription);
		card.setLovedeadline(format.parse(lovedeadline.toString()));
		card.setLoveid(UUIDUtils.getUUID());
		card.setLovenumber(Integer.parseInt(lovenumber));
		String path=ServletActionContext.getServletContext().getRealPath("/images/postcardcover");
		if(imgFile!=null){
			File saveFile=new File(new File(path), fileName);
			if(!saveFile.getParentFile().exists()){
				saveFile.getParentFile().mkdirs();
				FileUtils.copyFile(imgFile, saveFile);
			}else{
				FileUtils.copyFile(imgFile, saveFile);
			}
		}
		card.setLovepic("images/postcardcover/"+fileName);
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		card.setLovestarttime(format.parse(format.format(c.getTime())));
		card.setLovetags(lovetags);
		card.setLovetitle(lovetitle);
		card.setUser(currentUser);
		LovePostCard postCard=postCardDao.saveLovePostCard(card);
		return postCard;
	}


	/**
	 * 带分页的查询所有明信片活动
	 * @return
	 */
	public PageBean<LovePostCard> findAllPostCardsWithPagination(int page) {
		PageBean<LovePostCard> pageBean=new PageBean<>();
		pageBean.setPage(page);
		int limit=6;
		pageBean.setLimit(limit);
		int totalCount=0;
		totalCount=postCardDao.findCountLovePostCards();
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		if(totalCount<=pageBean.getLimit()){
			totalPage=1;
		}else{
			if (totalCount % limit == 0) {
				totalPage = totalCount/limit;
			} else {
				totalPage = totalCount / limit + 1;
			}
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<LovePostCard> list=postCardDao.findAllLovePostCardsWithPagination(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}


	/**
	 * 根据活动id找出明信片活动对象
	 * @param loveid
	 * @return
	 */
	public LovePostCard findLovePostCardByLoveId(String loveid) {
		return postCardDao.findLovePostCardByLoveId(loveid);
	}


	/**
	 * 检查用户参加活动状态
	 * @param userid
	 * @return
	 */
	public String checkUserJoinStatus(String userid,String loveid) {
		TripUser user=userDao.findUserByUserId(userid);
		LovePostCard card=postCardDao.findLovePostCardByLoveId(loveid);
		return postCardDao.checkUserJoinStatus(user,card);
	}


	/**
	 * 保存参加对象
	 * @param userid
	 * @param loveid
	 * @return
	 */
	public Boolean saveEventParticipate(String userid, String loveid) {
		TripUser user=userDao.findUserByUserId(userid);
		LovePostCard card=postCardDao.findLovePostCardByLoveId(loveid);
		EventParticipate p=new EventParticipate();
		p.setCard(card);
		p.setUser(user);
		p.setIschosen(1);
		return postCardDao.saveEventParticipate(p);
	}


	/**
	 * 根据明信片对象查找所有留言
	 * @param card
	 * @return
	 */
	public List<LovePostCardComment> findCommentsByLovePostCard(LovePostCard card) {
		List<LovePostCardComment> results=postCardDao.findCommentsByLovePostCard(card);
		if(results!=null&&!results.isEmpty()){
			for(LovePostCardComment c:results){
				List<EventParticipate> participates=postCardDao.findUserJoinOrNotByUserAndPostCard(c.getUser(),c.getPostCard());
				if(!participates.isEmpty()){//参加了
					c.setIsJoinIn(0);
					if(participates.get(0).getIschosen()==0){
						c.setIsChosen(0);
					}else{c.setIsChosen(1);}
				}else{//没参加
					c.setIsJoinIn(1);
					c.setIsChosen(1);
				}
			}
		}
		return results;
	}


	/**
	 * 保存用户明信片活动留言
	 * @param userid
	 * @param commentcontent
	 * @param address
	 * @param postcode
	 * @param receivername
	 * @return
	 * @throws ParseException 
	 */
	public LovePostCardComment saveLovePostCardComment(String userid, String commentcontent, String address,
			String postcode, String receivername,String loveid) throws ParseException {
		LovePostCardComment comment=new LovePostCardComment();
		comment.setLoveaddress(address);
		comment.setLovecommentcontent(commentcontent);
		comment.setLovecommentid(UUIDUtils.getUUID());
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		comment.setLovedate(format.parse(format.format(calendar.getTime())));
		comment.setLovepostcode(postcode);
		comment.setLovereceivername(receivername);
		LovePostCard card=postCardDao.findLovePostCardByLoveId(loveid);
		comment.setPostCard(card);
		TripUser user=userDao.findUserByUserId(userid);
		comment.setUser(user);
		return postCardDao.saveLovePostCardComment(comment);
	}


	/**
	 * 统计留言个数
	 * @return
	 */
	public int findCountComments() {
		return postCardDao.findCountComments();
	}


	/**
	 * 查找所有留言对象
	 * @return
	 */
	public List<LovePostCardComment> findAllPostCardComments() {
		return postCardDao.findAllPostCardComments();
	}


	/**
	 * 根据用户id查找发布的活动数据
	 * @param userid
	 * @return
	 */
	public List<LovePostCard> findAllPostCardByUserid(String userid) {
		TripUser user=userDao.findUserByUserId(userid);
		return postCardDao.findAllPostCardByUser(user);
	}


	/**
	 * 根据用户id查找用户参加的活动
	 * @param userid
	 * @return
	 */
	public List<LovePostCard> findLovePostCardFromJoinListByUserId(String userid) {
		TripUser user=userDao.findUserByUserId(userid);
		List<EventParticipate> ps=postCardDao.findLovePostCardFromJoinListByUser(user);
		List<LovePostCard> cards=new ArrayList<>();
		for(EventParticipate p:ps){
			cards.add(p.getCard());
		}
		for(LovePostCard card:cards){
			int commentNumber=postCardDao.findCountCommentsByPostCard(card);
			card.setCommentNumber(commentNumber);
			int joinNumber=postCardDao.findCountParticipateNumByPostCard(card);
			card.setJoinNumber(joinNumber);
		}
		return cards;
	}


	/**
	 * 根据明信片对象查询活动参加中被选中的集合
	 * @param card
	 * @return
	 */
	public List<EventParticipate> findEventParticipatesIsChosenByCard(LovePostCard card) {
		return postCardDao.findEventParticipatesIsChosenByCard(card);
	}


	/**
	 * 更新参加活动用户的选中情况
	 * @param userid
	 * @param loveid
	 * @return
	 */
	public Boolean updateEventParticipateChoseStatus(String userid, String loveid) {
		TripUser user=userDao.findUserByUserId(userid);
		LovePostCard card=postCardDao.findLovePostCardByLoveId(loveid);
		EventParticipate p=postCardDao.findEventParticipateByUserAndPostCard(user,card);
		p.setIschosen(0);
		return postCardDao.updateEventParticipate(p);
	}


	/**
	 * 查出所有已经确认了的用户个数
	 * @return
	 */
	public int findCountEventParticipateBeChoosed() {
		return postCardDao.findCountEventParticipateBeChoosed();
	}


	/**
	 * 查出所有明信片活动
	 * @return
	 */
	public List<LovePostCard> findAllPostCard() {
		return postCardDao.findAllPostCard();
	}

	/**
	 * 找出单一评论对象获取地址
	 * @param u
	 * @param card
	 * @return
	 */
	public LovePostCardComment findSingleCommentByCardAndUser(TripUser u, LovePostCard card) {
		return postCardDao.findSingleCommentByCardAndUser(u,card);
	}
	
	
}
