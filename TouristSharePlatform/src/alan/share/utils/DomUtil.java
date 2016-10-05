package alan.share.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DomUtil {
	private static String htmlMatch = "";   
	public static String getSingleImageFromHtmlDocument(String htmlContent) {
		Document docHtml = Jsoup.parse(htmlContent);
		String imgSrc = "";
		Elements imgs = docHtml.getElementsByTag("img");
		int count = 0;
		for (Element element : imgs) {
			if (count < 1) {
				imgSrc = element.attr("src");
				count++;
			}
		}
		return imgSrc;
	}
	
	/**
	 * 通用方法，得出html中的图片字符串
	 * @param htmlContent 原DOM结构
	 * @param number 想要得到的个数
	 * @author Alan
	 */
	public static List<String> getImageListFromHtmlDocument(String htmlContent,int number){
		List<String> results=new ArrayList<>();
		if(htmlContent!=null&&!htmlContent.equals("")){
			Document docHtml=Jsoup.parse(htmlContent);
			Elements imgs=docHtml.getElementsByTag("img");
			int count=0;
			for(Element e:imgs){
				if(count<number){
					results.add(e.attr("src"));
					count++;
				}
			}
		}
		return results;
	}

	// 通过递归删除html文件中的配对的html标签      
    
    public static String removeMatchHtmlTag() {      
        // String html="<p></p><table><tr><td></td><td></td></tr></table>";      
        Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>");      
        Matcher m = p.matcher(htmlMatch);      
        if (m.find()) {      
        //  System.out.println(htmlMatch);      
            htmlMatch = htmlMatch.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>","$2");      
     
            removeMatchHtmlTag();      
        }      
        return htmlMatch;      
    }      
    
    
    public static String subStringHTML(String param, int length, String endWith) {      
        
        if(length<1) {System.out.println("length must >0");return null;}      
              
        if(param.length()<length){return param;}      
              
        StringBuffer result = new StringBuffer();      
        StringBuffer str = new StringBuffer();      
        int n = 0;      
     
        char temp;      
     
        boolean isCode = false; // 是不是HTML代码      
        boolean isHTML = false; // 是不是HTML特殊字符,如      
        for (int i = 0; i < param.length(); i++) {      
            temp = param.charAt(i);      
            if (temp == '<') {      
                isCode = true;      
            }      
            else if (temp == '&') {      
                isHTML = true;      
            }      
            else if (temp == '>' && isCode) {      
                n = n - 1;      
                isCode = false;      
            }      
            else if (temp == ';' && isHTML) {      
                isHTML = false;      
            }      
            if (!isCode && !isHTML) {      
                n = n + 1;      
                // UNICODE码字符占两个字节      
                if ((temp + "").getBytes().length > 1) {      
                    n = n + 1;      
                }      
                str.append(temp);      
            }      
            result.append(temp);      
            if (n >= length) {      
                break;      
            }      
        }      
     
        result.append(endWith);      
        // 取出截取字符串中的HTML标记      
        String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)","$1$2");      
     
        // 去掉不需要结束标记的HTML标记      
     
        temp_result = temp_result      
                .replaceAll("<(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/>","");      
     
        // 去掉成对的HTML标记      
        // temp_result=temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>","$2");      
        htmlMatch = temp_result;      
        temp_result = removeMatchHtmlTag();      
        //System.out.println("6666:" + temp_result);      
        // 用正则表达式取出标记      
     
        Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");      
        Matcher m = p.matcher(temp_result);      
        List endHTML = new ArrayList();      
     
        while (m.find()) {      
            endHTML.add(m.group(1));      
        }      
     
        // 补全不成对的HTML标记      
        for (int i = endHTML.size() - 1; i >= 0; i--) {      
            result.append("</");      
            result.append(endHTML.get(i));      
            result.append(">");      
     
        }      
        return result.toString();      
     
    }

    /**
     * 找出dom结构中所有的image属性
     * @param htmlContent
     * @return
     */
	public static List<String> getAllImageListFromHtmlDoc(String htmlContent) {
		List<String> results=new ArrayList<>();
		if(htmlContent!=null&&!htmlContent.equals("")){
			Document docHtml=Jsoup.parse(htmlContent);
			Elements imgs=docHtml.getElementsByTag("img");
			for(Element e:imgs){
					results.add(e.attr("src"));
			}
		}
		return results;
	}      
}
