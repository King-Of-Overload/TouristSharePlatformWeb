package alan.share.lovecard.model;

import java.io.Serializable;
import java.util.Date;

import alan.share.user.model.TripUser;

public class LovePostCardComment implements Serializable {
	private String lovecommentid;
	private String lovecommentcontent;
	private String loveaddress;
	private String lovepostcode;
	private String lovereceivername;
	private TripUser user;
	private LovePostCard postCard;
	private Date lovedate;
	private int isChosen;
	private int isJoinIn;
	
	
	
	public int getIsChosen() {
		return isChosen;
	}
	public void setIsChosen(int isChosen) {
		this.isChosen = isChosen;
	}
	public int getIsJoinIn() {
		return isJoinIn;
	}
	public void setIsJoinIn(int isJoinIn) {
		this.isJoinIn = isJoinIn;
	}
	public Date getLovedate() {
		return lovedate;
	}
	public void setLovedate(Date lovedate) {
		this.lovedate = lovedate;
	}
	public String getLovecommentid() {
		return lovecommentid;
	}
	public void setLovecommentid(String lovecommentid) {
		this.lovecommentid = lovecommentid;
	}
	public String getLovecommentcontent() {
		return lovecommentcontent;
	}
	public void setLovecommentcontent(String lovecommentcontent) {
		this.lovecommentcontent = lovecommentcontent;
	}
	public String getLoveaddress() {
		return loveaddress;
	}
	public void setLoveaddress(String loveaddress) {
		this.loveaddress = loveaddress;
	}
	public String getLovepostcode() {
		return lovepostcode;
	}
	public void setLovepostcode(String lovepostcode) {
		this.lovepostcode = lovepostcode;
	}
	public String getLovereceivername() {
		return lovereceivername;
	}
	public void setLovereceivername(String lovereceivername) {
		this.lovereceivername = lovereceivername;
	}
	public TripUser getUser() {
		return user;
	}
	public void setUser(TripUser user) {
		this.user = user;
	}
	public LovePostCard getPostCard() {
		return postCard;
	}
	public void setPostCard(LovePostCard postCard) {
		this.postCard = postCard;
	}
	public LovePostCardComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
