/**
 * 
 */
package com.ten.fengbo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:UBB数据格式转换
 * @author fengbo
 * @date 2017-2-7 下午3:58:41
 * 
 */
public class UbbtohtmlUtil {

	static Pattern p1=Pattern.compile("<([^>]*)>", Pattern.DOTALL);  
	  
    public static String clearHtmlTag(String s)  
    {  
        try  
        {  
            Matcher m = null;  
            m = p1.matcher(s);  
            while (m.find())  
            {  
                for (int i = 1; i <= m.groupCount(); i++)  
                {  
                    //System.out.println("找到 = " + m.group());  
                    s = s.replaceAll(m.group(), "");  
                }  
            }  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
        return s.trim();  
    }  
  
    public static String delHtmlTag(String str)  
    {  
        int index1 = -1;  
        int index2 = -1;  
        while (true)  
        {  
            index1 = str.indexOf("<");  
            index2 = str.indexOf(">", index1);  
            if (index1 == -1)  
            {  
                break;  
            }  
            if (index2 == -1)  
            {  
                index2 = str.length();  
            }  
            else  
            {  
                index2 = index2 + 1;  
            }  
            str = str.substring(0, index1)  
                  + str.substring(index2, str.length());  
  
        }  
        str = str.replaceAll("\r", "<br/>");  
        str = str.replaceAll("\t", "    ");  
        return str;  
    }  
  
    /** 
     * 转换UBB 
     * @param text 
     * @return 
     */  
    public static String UbbDecode(String text)  
    {  
        text = replace(text, "tr(.*?)", "tr", "<tr>", "</tr>");  
  
        text = replace(text, "td(.*?)", "td", "<td>", "</td>");  
  
        text = replace(text, "table(.*?)", "table", "<table>", "</table>");  
  
        text = replace(text, "align=(.*?)", "align", "<p align=$2>", "</align>");  
  
        text = replace(text, "url=(.*?)", "url", "<a href='$2' target=_blank>",  
                       "</a>");  
  
        text = replace(text, "img", "img", "<img border=0 src=", "></img>");  
  
        text = replace(text, "img=(.+?),(.+?)", "img",  
                       "<img width=\"$2\" hight=\"$3\" border=0 src=",  
                       "></img>");  
  
        text = replace(text, "size=(.+?)", "size", "<font size=$2>", "</font>");  
  
        text = replace(text, "font=(.+?)", "font", "<font face=$2>", "</font>");  
  
        text = replace(text, "color=(.+?)", "color", "<font color=$2>",  
                       "</font>");  
  
        text = replace(text, "email=(.+?)", "email", "<a href='mailto:$2'>", "</a>");  
         
        text = replace(text, "u", "<u>", "</u>");  
        text = replace(text, "i", "<i>", "</i>");  
        text = replace(text, "li", "<li>", "</li>");  
        text = replace(text, "list=(.*?)", "<ol>", "</ol>");  
        text = replace(text, "list(.*?)", "<ul>", "</ul>");  
        text = replace(text, "\\*", "<li>", "</li>");  
        text = replace(text, "ol", "<ol>", "</ol>");  
        text = replace(text, "ul", "<ul>", "</ul>");  
        text = replace(text, "b", "<b>", "</b>");  
        text = replace(text, "h1", "<h1>", "</h1>");  
        text = replace(text, "h2", "<h2>", "</h2>");  
        text = replace(text, "h3", "<h3>", "</h3>");  
        text = replace(text, "h4", "<h4>", "</h4>");  
        text = replace(text, "h5", "<h5>", "</h5>");  
        text = replace(text, "h6", "<h6>", "</h6>");  
  
        return text;  
    }  
  
    public static String replace(String text, String reg, String replaceStr1,  
                                 String replaceStr2)  
    {  
        return replace(text, reg, reg, replaceStr1, replaceStr2);  
    }  
  
    public static String replace(String text, String reg, String regEnd,  
                                 String replaceStr1, String replaceStr2)  
    {  
        Matcher m = null;  
  
        m = Pattern.compile(  
                "(\\[" + reg + "\\])",  
                Pattern.DOTALL | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE)  
            .matcher(text);  
        text = m.replaceAll(replaceStr1);  
  
        m = Pattern.compile(  
                "(\\[/" + regEnd + "\\])",  
                Pattern.DOTALL | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE)  
            .matcher(text);  
        text = m.replaceAll(replaceStr2);  
  
        return text;  
    }  
    public static void main(String arg[])  
    {  
        System.out.println(UbbDecode(" [img=423,335]http://XXXXXX/attachment/201108/11/166161_1313042512MPmC.jpg[/img]"));  
    }  

	
}
