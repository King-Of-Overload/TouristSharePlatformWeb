package alan.share.lovecard.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import alan.share.user.model.TripUser;

public class SendNow implements Serializable {
	private String title;
	private String orginizerName;
	private String orginizerId;
	private String loveId;
	private int personNumber;
	private List<TripUser> users=new ArrayList<>();
	
	public List<TripUser> getUsers() {
		return users;
	}
	public void setUsers(List<TripUser> users) {
		this.users = users;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrginizerName() {
		return orginizerName;
	}
	public void setOrginizerName(String orginizerName) {
		this.orginizerName = orginizerName;
	}
	public String getOrginizerId() {
		return orginizerId;
	}
	public void setOrginizerId(String orginizerId) {
		this.orginizerId = orginizerId;
	}
	public String getLoveId() {
		return loveId;
	}
	public void setLoveId(String loveId) {
		this.loveId = loveId;
	}
	public int getPersonNumber() {
		return personNumber;
	}
	public void setPersonNumber(int personNumber) {
		this.personNumber = personNumber;
	}
	
}
