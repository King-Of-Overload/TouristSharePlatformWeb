package alan.share.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import alan.share.utils.CookieUtil;
/**
 * 登陆拦截器
 * @author Alan
 *
 */
public class LoginInterceptor extends MethodFilterInterceptor {



	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		 ActionContext actionContext = invocation.getInvocationContext();   
	     HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST); 
	     String userid=CookieUtil.readCookieReturnId(request);
	     //TripUser existUser=(TripUser) request.getSession().getAttribute("sessionUser");
	     if(null!=userid){
	    	 System.out.println("正常");
	    	 return invocation.invoke();
	     }else{
	    	// 跳转到登录页面:
				ActionSupport support = (ActionSupport) invocation.getAction();
				support.addActionError("您还没有登录!没有权限访问!");
				return ActionSupport.LOGIN;
	     }
	}

}
