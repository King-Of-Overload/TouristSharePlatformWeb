package alan.share.lovecard.action;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.jdbc.core.metadata.PostgresCallMetaDataProvider;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import alan.share.lovecard.model.EventParticipate;
import alan.share.lovecard.model.LovePostCard;
import alan.share.lovecard.model.LovePostCardComment;
import alan.share.lovecard.model.SendNow;
import alan.share.lovecard.service.LovePostCardService;
import alan.share.user.model.TripUser;
import alan.share.user.service.TripUserService;
import alan.share.utils.CookieUtil;
import alan.share.utils.PageBean;

public class LoveCardAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private LovePostCardService postCardService;
	private TripUserService userService;
	
	private File cover;//图片文件
	private String coverFileName;
	
	
	public TripUserService getUserService() {
		return userService;
	}

	public void setUserService(TripUserService userService) {
		this.userService = userService;
	}

	public File getCover() {
		return cover;
	}

	public void setCover(File cover) {
		this.cover = cover;
	}

	public String getCoverFileName() {
		return coverFileName;
	}

	public void setCoverFileName(String coverFileName) {
		this.coverFileName = coverFileName;
	}
	

	public LovePostCardService getPostCardService() {
		return postCardService;
	}

	public void setPostCardService(LovePostCardService postCardService) {
		this.postCardService = postCardService;
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
	 * 跳转到爱的明信片首页
	 * @return
	 */
	public String goToLoveCard(){
		try {
			this.encodingReqAndRes();
			int page=Integer.parseInt(request.getParameter("page"));
			PageBean<LovePostCard> postCards=postCardService.findAllPostCardsWithPagination(page);
			int commentNumber=postCardService.findCountComments();
			List<LovePostCardComment> commentList=postCardService.findAllPostCardComments();
			int length=0;
			if(commentList.size()>6){
				length=6;
			}else{
				length=commentList.size();
			}
			commentList.subList(0, length);
			//明信片数量
			int postCardNumber=postCardService.findCountEventParticipateBeChoosed();
			//正在寄出
			List<LovePostCard> pcs=postCardService.findAllPostCard();
			List<SendNow> sendNowList=new ArrayList<>();
			for(LovePostCard p:pcs){
				List<EventParticipate> joinList=new ArrayList<>();
				Set<EventParticipate> set=p.getJoins();
				if(set.size()>0){
					Iterator<EventParticipate> iterator=set.iterator();
					while(iterator.hasNext()){
						joinList.add(iterator.next());
					}
				}
				if(joinList.size()>=p.getLovenumber()){
					SendNow now=null;
					List<TripUser> users=new ArrayList<>();
					for(EventParticipate ep:joinList){
						if(ep.getIschosen()==0){
							users.add(ep.getUser());
						}
					}
					if(users.size()==p.getLovenumber()){
						now=new SendNow();
						now.setLoveId(p.getLoveid());
						now.setTitle(p.getLovetitle());
						now.setOrginizerId(p.getUser().getUserid());
						now.setOrginizerName(p.getUser().getUsername());
						now.setPersonNumber(users.size());
						now.setUsers(users);
						sendNowList.add(now);
					}
				}
			}
			ValueStack stack=ActionContext.getContext().getValueStack();
			stack.set("postCards", postCards);
			stack.set("commentNumber", commentNumber);
			stack.set("commentList", commentList);
			stack.set("postCardNumber", postCardNumber);
			stack.set("sendNowList", sendNowList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToLoveCard";
	}
	
	/**
	 * 跳转到创建寄片界面
	 * @return
	 */
	public String goToCreateLoveCard(){
		return "goToCreateLoveCard";
	}
	
	/**
	 * 提交明信片数据
	 * @return
	 */
	public String submitLoveCard(){
		try {
			this.encodingReqAndRes();
			String lovetitle=request.getParameter("lovetitle");//标题
			String lovedescription=request.getParameter("lovedescription");//内容
			String lovenumber=request.getParameter("lovenumber");//明信片个数
			String lovedeadline=request.getParameter("lovedeadline");//截止日期
			String lovetags=request.getParameter("lovetags");//标签
			System.out.println(lovetitle+","+lovedescription+","+lovenumber+","+lovedeadline+","+lovetags+","+cover+","+coverFileName);
			File imgFile=cover;
			String fileName=coverFileName;
			String userid=CookieUtil.readCookieReturnId(request);
			LovePostCard postCard=postCardService.saveLovePostCard(lovetitle,lovedescription,lovenumber,lovedeadline,lovetags,imgFile,fileName,userid);
			List<LovePostCardComment> loveComments=postCardService.findCommentsByLovePostCard(postCard);
			ValueStack stack=ActionContext.getContext().getValueStack();
			stack.set("postCard", postCard);
			stack.set("loveComments", loveComments);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToLoveCardEvent";
	}
	
	/**
	 * 跳转到活动详情界面
	 * @return
	 */
	public String goToLoveCardEvent(){
		try {
			this.encodingReqAndRes();
			String loveid=request.getParameter("loveid");
			TripUser currentUser=userService.findUserByUserId(CookieUtil.readCookieReturnId(request));
			LovePostCard card=postCardService.findLovePostCardByLoveId(loveid);
			List<LovePostCardComment> loveComments=postCardService.findCommentsByLovePostCard(card);
			List<EventParticipate> participates=postCardService.findEventParticipatesIsChosenByCard(card);
			ValueStack stack=ActionContext.getContext().getValueStack();
			stack.set("postCard", card);
			stack.set("loveComments", loveComments);
			stack.set("participates", participates);
			stack.set("currentUser", currentUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToLoveCardEvent";
	}
	
	/**
	 * 修改选择状态
	 * @return
	 */
	public String chooseHimOrHer(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=request.getParameter("userid");
			String loveid=request.getParameter("loveid");
			Boolean result=postCardService.updateEventParticipateChoseStatus(userid,loveid);
			if(result==true){
				out.print("success");
			}else{
				out.print("error");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 检查参加明信片活动情况
	 * @return
	 */
	public String checkUserJoinStatus(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=request.getParameter("userid");
			String loveid=request.getParameter("loveid");
			String status=postCardService.checkUserJoinStatus(userid,loveid);
			out.print(status);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 点击我要参加改变用户参加状态
	 * @return
	 */
	public String saveParticipateStatus(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=request.getParameter("userid");
			String loveid=request.getParameter("loveid");
			Boolean result=postCardService.saveEventParticipate(userid,loveid);
			if(result==true){
				out.print("success");
			}else{
				out.print("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 保存用户明信片活动留言
	 * @return
	 */
	public String saveLovePostCardComment(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=request.getParameter("userid");
			String commentcontent=request.getParameter("commentcontent");
			String address=request.getParameter("address");
			String postcode=request.getParameter("postcode");
			String receivername=request.getParameter("receivername");
			String loveid=request.getParameter("loveid");
			LovePostCardComment comment=postCardService.saveLovePostCardComment(userid,commentcontent,address,postcode,receivername,loveid);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			out.print(format.format(comment.getLovedate()).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 跳转到查看确认名单
	 * @return
	 */
	public String showAllChosenUser(){
		try {
			this.encodingReqAndRes();
			String loveid=request.getParameter("loveid");
			LovePostCard card=postCardService.findLovePostCardByLoveId(loveid);
			List<EventParticipate> joinList=postCardService.findEventParticipatesIsChosenByCard(card);
			List<LovePostCardComment> comments=new ArrayList<>();
			if(joinList!=null&&!joinList.isEmpty()){
				for(EventParticipate p:joinList){
					TripUser u=p.getUser();
					LovePostCardComment c=postCardService.findSingleCommentByCardAndUser(u,card);
					comments.add(c);
				}
			}
			ValueStack stack=ActionContext.getContext().getValueStack();
			stack.set("comments", comments);
			stack.set("loveid", loveid);
			stack.set("lovetitle", card.getLovetitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showAllChosenUser";
	}
	
	/**
	 * 跳转到用户明信片个人空间
	 * @return
	 */
	public String goToUserPostCardSpace(){
		try {
			this.encodingReqAndRes();
			String userid=request.getParameter("userid");
			TripUser existUser=userService.findUserByUserId(userid);
			//发起的数据
			List<LovePostCard> advertiseList=postCardService.findAllPostCardByUserid(userid);
			//用户参与
			List<LovePostCard> participateList=postCardService.findLovePostCardFromJoinListByUserId(userid);
			
			ValueStack stack=ActionContext.getContext().getValueStack();
			stack.set("existUser", existUser);
			stack.set("advertiseList", advertiseList);
			stack.set("participateList", participateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToUserPostCardSpace";
	}
	

	/**
	 * 编码
	 * @throws UnsupportedEncodingException
	 */
	private void encodingReqAndRes() throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	}

}
