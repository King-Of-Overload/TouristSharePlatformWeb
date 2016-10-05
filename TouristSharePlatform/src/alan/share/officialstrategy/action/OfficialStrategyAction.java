package alan.share.officialstrategy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import alan.share.officialstrategy.model.OfficialStrategy;
import alan.share.officialstrategy.service.OfficialStrategyService;

public class OfficialStrategyAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private OfficialStrategyService officialService;
	
	public OfficialStrategyService getOfficialService() {
		return officialService;
	}

	public void setOfficialService(OfficialStrategyService officialService) {
		this.officialService = officialService;
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
	 * 跳转到官方攻略的页面
	 * @return
	 */
	public String goToOfficialStrategy(){
		List<OfficialStrategy> oList=officialService.getAllOfficialStrategy();
		ValueStack stack=ActionContext.getContext().getValueStack();
		stack.set("oList", oList);
		System.out.println(oList.get(0).getProvinces());
		return "gotoofficialstrategy";
	}
	
	/**
	 * 点击省份查询与该省份有关的攻略
	 * @return
	 */
	public String findStrategyByProvince(){
		int provinceid=Integer.valueOf(request.getParameter("provinceid"));
		List<OfficialStrategy> strategies=officialService.findStrategyByPid(provinceid);
		ValueStack stack=ActionContext.getContext().getValueStack();
		stack.set("oList", strategies);
		return "gotoofficialstrategy";
	}
	
	/**
	 * 根据城市名查询
	 * @return
	 */
	public String findStrategyByCity(){
		int cityid=Integer.valueOf(request.getParameter("cityid"));
		List<OfficialStrategy> strategies=officialService.findStrategyByCid(cityid);
		ValueStack stack=ActionContext.getContext().getValueStack();
		stack.set("oList", strategies);
		return "gotoofficialstrategy";
	}

}
