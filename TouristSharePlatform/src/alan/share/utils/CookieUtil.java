package alan.share.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import alan.share.user.model.TripUser;

/**
 * cookie工具类
 * 
 * @author Alan
 *
 */
public class CookieUtil {
	public final static String cookieDomainName = "cookieUser";// 保存cookie时的cookieName
	public final static String webKey = "520";// 加密cookie时的网站自定码
	public final static long cookieMaxAge = 60 * 60 * 24 * 7 * 2;// 设置cookie有效期是两个星期
	public static String userCookie;
	


	/**
	 * 保存cookie
	 * 
	 * @param user
	 *            当前用户
	 * @param response
	 *            response对象
	 */
	public static void saveCookie(TripUser user, HttpServletResponse response) {
		long validTime = System.currentTimeMillis() + (cookieMaxAge * 5000);// cookie的有效期
		String cookieValueWithMd5 = getMD5(
				user.getUsername() + ":" + user.getUserpassword() + ":" + validTime + ":" + webKey); // MD5加密用户详细信息
		String cookieValue = user.getUserid() + ":" + validTime + ":" + cookieValueWithMd5;// 将要被保存的完整的Cookie值
		String cookieValueBase64 = new String(Base64.encode(cookieValue.getBytes()));// 再一次对Cookie的值进行BASE64编码
		Cookie cookie = new Cookie(cookieDomainName, cookieValueBase64); // 开始保存Cookie
		cookie.setMaxAge(60 * 60 * 24 * 365 * 2); // 存两年(这个值应该大于或等于validTime)
		cookie.setPath("/"); // cookie有效路径是网站根目录
		userCookie=cookieValueBase64;
		response.addCookie(cookie); // 向客户端写入
	}

	/**
	 * 读取cookie数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static String readCookieReturnId(HttpServletRequest request) throws Exception{
		Cookie cookies[] = request.getCookies();
		String cookieValue = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookieDomainName.equals(cookies[i].getName())) {
					cookieValue = cookies[i].getValue();
					break;
				}
			}
		}
		if (cookieValue == null) { // 如果cookieValue为空,返回
			return null;
		}
		// 如果cookieValue不为空,才执行下面的代码
		String cookieValueAfterDecode = new String(Base64.decode(cookieValue), "utf-8");// 先得到的CookieValue进行Base64解码
		 //对解码后的值进行分拆,得到一个数组
		String cookieValues[] = cookieValueAfterDecode.split(":");
		//取出cookie中的用户名,并到数据库中检查这个用户名
		String userid = cookieValues[0];
		return userid;
	}
	
	public static String readRealCookieValue(HttpServletRequest request){
		Cookie cookies[] = request.getCookies();
		String cookieValue = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookieDomainName.equals(cookies[i].getName())) {
					cookieValue = cookies[i].getValue();
					break;
				}
			}
		}
		if (cookieValue == null) { // 如果cookieValue为空,返回
			return null;
		}
		return cookieValue;
	}

	/**
	 * 用户注销时,清除Cookie,在需要时可随时调用
	 * 
	 * @param response
	 *            response对象
	 */
	public static void clearCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(cookieDomainName, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 获取Cookie组合字符串的MD5码的字符串
	 * 
	 * @param value
	 * @return
	 */
	public static String getMD5(String value) {
		String result = null;
		try {
			byte[] valueByte = value.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(valueByte);
			result = toHex(md.digest());
		} catch (NoSuchAlgorithmException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * 将传递进来的字节数组转换成十六进制的字符串形式并返回
	 * 
	 * @param buffer
	 * @return
	 */
	private static String toHex(byte[] buffer) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}
		return sb.toString();
	}

}
