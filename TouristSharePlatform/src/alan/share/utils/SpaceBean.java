package alan.share.utils;

import java.io.Serializable;
/**
 * 用户主页动态pojo对象
 * @author Alan
 *
 */
import java.util.Date;

import alan.share.user.model.TripUser;
public class SpaceBean implements Serializable {
	private String id;
	private TripUser user;
	private Date time;
	private String title;
	private String description;
	private String coverImage;
	private int clickedNum;
	private int likeCount;//点赞数，可能为空
	private int commentNum;//评论数，可能为空
	private String type;//实体类型 strategy album,skillacademy
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TripUser getUser() {
		return user;
	}
	public void setUser(TripUser user) {
		this.user = user;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public int getClickedNum() {
		return clickedNum;
	}
	public void setClickedNum(int clickedNum) {
		this.clickedNum = clickedNum;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public SpaceBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
