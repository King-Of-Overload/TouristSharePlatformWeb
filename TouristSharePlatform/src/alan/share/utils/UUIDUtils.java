package alan.share.utils;

import java.util.UUID;

/**
 * 生成随机字符串的工具类
 * @author Alan
 *
 */
public class UUIDUtils {
	
	/**
	 * 生成并获得随机的字符串
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
