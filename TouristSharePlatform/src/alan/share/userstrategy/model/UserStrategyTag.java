package alan.share.userstrategy.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 攻略标签
 * @author Alan
 *
 */
public class UserStrategyTag implements Serializable {
	private int ustrategytagid;
	private String ustrategytagname;
	private int ustrategyclickednum;
	private Set<UserStrategy> strategies=new HashSet<>();
	
	public Set<UserStrategy> getStrategies() {
		return strategies;
	}
	public void setStrategies(Set<UserStrategy> strategies) {
		this.strategies = strategies;
	}
	public int getUstrategytagid() {
		return ustrategytagid;
	}
	public void setUstrategytagid(int ustrategytagid) {
		this.ustrategytagid = ustrategytagid;
	}
	public String getUstrategytagname() {
		return ustrategytagname;
	}
	public void setUstrategytagname(String ustrategytagname) {
		this.ustrategytagname = ustrategytagname;
	}
	public int getUstrategyclickednum() {
		return ustrategyclickednum;
	}
	public void setUstrategyclickednum(int ustrategyclickednum) {
		this.ustrategyclickednum = ustrategyclickednum;
	}
	public UserStrategyTag() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
