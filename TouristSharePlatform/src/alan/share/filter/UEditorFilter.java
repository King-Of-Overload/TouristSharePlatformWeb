package alan.share.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * Servlet Filter implementation class UEditorFilter
 */

public class UEditorFilter extends StrutsPrepareAndExecuteFilter implements Filter {
       

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		String url=req.getRequestURI();
		System.out.println(url);
		if(url.contains("/TouristSharePlatform/userStrategyImages/images/")){
			System.out.println("自定义过滤器");
			chain.doFilter(request, response);
		}else{
			System.out.println("使用默认过滤器");
			super.doFilter(request, response, chain);
			
		}
	}


}
