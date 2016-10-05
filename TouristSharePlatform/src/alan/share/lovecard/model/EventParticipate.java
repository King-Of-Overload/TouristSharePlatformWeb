package alan.share.lovecard.model;

import java.io.Serializable;

import alan.share.user.model.TripUser;

public class EventParticipate implements Serializable {
	private int lpid;
	private LovePostCard card;
	private TripUser user;
	private int ischosen;
	
	public int getIschosen() {
		return ischosen;
	}
	public void setIschosen(int ischosen) {
		this.ischosen = ischosen;
	}
	public int getLpid() {
		return lpid;
	}
	public void setLpid(int lpid) {
		this.lpid = lpid;
	}
	public LovePostCard getCard() {
		return card;
	}
	public void setCard(LovePostCard card) {
		this.card = card;
	}
	public TripUser getUser() {
		return user;
	}
	public void setUser(TripUser user) {
		this.user = user;
	}
	public EventParticipate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
