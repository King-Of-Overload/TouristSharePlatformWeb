package alan.share.userstrategy.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
/**
 * 攻略
 * @author Alan
 *
 */
import java.util.Set;

import alan.share.officialstrategy.model.City;
import alan.share.user.model.TripUser;
import alan.share.userstrategy.action.UserStrategyAction;
public class UserStrategy implements Serializable {
	private String ustrategyid;//攻略id
	private String ustrategyname;//攻略名
	private String ustrategycontent;//攻略内容
	private Date ustrategydate;//日期
	private int uclickednum;//点击量
	private int ulikecount;//点赞数
	private String ustrategycoverstory;//攻略简介
	private TripUser tripUser;//用户对象
	private City city;//城市
	private String ustrategyplaintext;//纯文本
	private Set<UserStrategyTag> tags=new HashSet<>();
	private int isesense;
	private String coverImage;
	
	
	

	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public int getIsesense() {
		return isesense;
	}
	public void setIsesense(int isesense) {
		this.isesense = isesense;
	}
	public String getUstrategyplaintext() {
		return ustrategyplaintext;
	}
	public void setUstrategyplaintext(String ustrategyplaintext) {
		this.ustrategyplaintext = ustrategyplaintext;
	}
	public Set<UserStrategyTag> getTags() {
		return tags;
	}
	public void setTags(Set<UserStrategyTag> tags) {
		this.tags = tags;
	}
	public String getUstrategyid() {
		return ustrategyid;
	}
	public void setUstrategyid(String ustrategyid) {
		this.ustrategyid = ustrategyid;
	}
	public String getUstrategyname() {
		return ustrategyname;
	}
	public void setUstrategyname(String ustrategyname) {
		this.ustrategyname = ustrategyname;
	}
	public String getUstrategycontent() {
		return ustrategycontent;
	}
	public void setUstrategycontent(String ustrategycontent) {
		this.ustrategycontent = ustrategycontent;
	}
	public Date getUstrategydate() {
		return ustrategydate;
	}
	public void setUstrategydate(Date ustrategydate) {
		this.ustrategydate = ustrategydate;
	}
	public int getUclickednum() {
		return uclickednum;
	}
	public void setUclickednum(int uclickednum) {
		this.uclickednum = uclickednum;
	}
	public int getUlikecount() {
		return ulikecount;
	}
	public void setUlikecount(int ulikecount) {
		this.ulikecount = ulikecount;
	}
	public String getUstrategycoverstory() {
		return ustrategycoverstory;
	}
	public void setUstrategycoverstory(String ustrategycoverstory) {
		this.ustrategycoverstory = ustrategycoverstory;
	}
	public TripUser getTripUser() {
		return tripUser;
	}
	public void setTripUser(TripUser tripUser) {
		this.tripUser = tripUser;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}

	public UserStrategy() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
