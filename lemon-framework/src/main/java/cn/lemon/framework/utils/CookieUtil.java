package cn.lemon.framework.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cookie的操作类
 * 
 * @author lonyee
 * @version v 1.0
 */
public class CookieUtil {
	private static Logger logger = LoggerFactory.getLogger(CookieUtil.class);
		
	/**
	 * 得到指定键的值
	 * 
	 * @param request
	 * @param name 指定的键
	 * @return 返回值，不存在返回空
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		Map<String, String> cookieHt = getCookie(request);
		return cookieHt.get(name);
	}

	/**
	 * 读取所有Cookie
	 * 
	 * @param request
	 * @return 返回cookie的键值对Hashtable
	 */
	public static Map<String, String> getCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Map<String, String> cookieHt = new HashMap<String, String>();
		if (null != cookies) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				try {
					cookieHt.put(cookie.getName(), java.net.URLDecoder.decode(cookie.getValue(), "UTF-8"));
					//System.out.println(cookie.getName() +":"+ cookie.getValue());
				}
				catch (UnsupportedEncodingException e) {
					logger.warn("获取cookie[{}]失败。{}", cookie.getName(), e.getMessage());
				}
			}
		}
		return cookieHt;
	}

	/**
	 * 创建cookie（随浏览器销毁）
	 * 
	 * @param response
	 * @param nameValues 存入cookie的键值对
	 */
	public static void setCookie(HttpServletRequest request,HttpServletResponse response, Map<String, String> nameValues) {
		setCookie(request, response, nameValues, null);
	}
	
	/**
	 * 创建cookie
	 * 
	 * @param response
	 * @param nameValues 存入cookie的键值对
	 * @param hours 设置cookie的有效期（为空时随浏览器销毁）
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, Map<String, String> nameValues, Integer hours) {
		setCookie(request, response, nameValues, hours, null);
	}

	/**
	 * 创建cookie
	 * 
	 * @param response
	 * @param nameValues 存入cookie的键值对
	 * @param hours 设置cookie的有效期（为空时随浏览器销毁）
	 * @param isHttpOnly 是否js不能访问
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, Map<String, String> nameValues, Integer hours, Boolean isHttpOnly) {
		if (nameValues==null) {
			return;
		}
		for (Map.Entry<String, String> entry : nameValues.entrySet()) {
			setCookie(request, response, entry.getKey(), entry.getValue(), hours, isHttpOnly);
		}
	}
	
	/**
	 * 创建cookie
	 * 
	 * @param response
	 * @param hours 设置cookie的有效期（为空时随浏览器销毁）
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer hours) {
		setCookie(request, response, name, value, hours, null);
	}
	
	/**
	 * 创建cookie
	 * 
	 * @param response
	 * @param hours 设置cookie的有效期（为空时随浏览器销毁）
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer hours, Boolean isHttpOnly) {
		try {
			if (value!=null){
				value = java.net.URLEncoder.encode(value, "UTF-8");
			}
		}
		catch (UnsupportedEncodingException e) {
			logger.warn("生成cookie[{}]失败。{}", name, e.getMessage());
		}
		Cookie cookie = new Cookie(name, value);
		if (null != hours) {
			//（生成缓存文件，否则在内存中）
			cookie.setMaxAge(hours * 60 * 60);
			cookie.setPath("/");
		}
		if (null != isHttpOnly) {
			cookie.setHttpOnly(isHttpOnly);
		}
		String domain = request.getServerName();
		boolean isIp = StringUtil.isIpAddress(domain);
		if (!isIp){
			String[] dis = domain.split(".");
			if (dis.length>=2){
				cookie.setDomain("."+ dis[dis.length-2]+"."+ dis[dis.length-1]);
			}
		}
		response.addCookie(cookie);
	}

	/**
	 * 销毁所有cookie
	 * 
	 * @param request
	 * @param response
	 */
	public static void removeAllCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				cookie.setValue(null);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}
}
