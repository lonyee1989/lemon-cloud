package cn.lemon.framework.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * cookie session公共类
 * Created by lonyee on 2017/5/4.
 */
public class RequestContextUtil {

    /**
     * 获取参数（包含请求参数和session）
     */
    public static RequestAttributes getRequestAttributes() {
        return RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取请求参数
     */
    public static Object getAttributeValue(String name) {
        return getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

    /**
     * 设置请求参数
     */
    public static void setAttributeValue(String name, Object value) {
        getRequestAttributes().setAttribute(name, value, RequestAttributes.SCOPE_REQUEST);
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        HttpServletRequest request = ((ServletRequestAttributes) getRequestAttributes()).getRequest();
        return request.getSession();
    }

    /**
     * 获取session值
     */
    public static Object getSessionValue(String name) {
        return getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 获取session值
     */
    public static void setSessionValue(String name, Object value) {
        getRequestAttributes().setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 获取全局session值
     */
    public static Object getGlobalSessionValue(String name) {
        return getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_GLOBAL_SESSION);
    }

    /**
     * 设置全局session值
     */
    public static void setGlobalSessionValue(String name, Object value) {
        getRequestAttributes().setAttribute(name, value, RequestAttributes.SCOPE_GLOBAL_SESSION);
    }

    /**
     * 获取所有cookie
     */
    public static Map<String, Cookie> getCookies() {
        HttpServletRequest request = ((ServletRequestAttributes) getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 获取cookie值
     */
    public static Object getCookieValue(String name) {
        Map<String,Cookie> cookieMap = getCookies();
        return cookieMap.containsKey(name)? cookieMap.get(name).getValue(): null;
    }

    public static void setCookie(String name,String value) {
        setCookie(name, value, 0, false);
    }

    public static void setCookie(String name,String value, Boolean isHttpOnly) {
        setCookie(name, value, 0, isHttpOnly);
    }

    /**
     * 设置cookie值
     */
    public static void setCookie(String name,String value, int maxAge, Boolean isHttpOnly){
        HttpServletResponse response = ((ServletRequestAttributes) getRequestAttributes()).getResponse();
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setHttpOnly(isHttpOnly);
        if(maxAge>0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * 获取header值
     */
    public static String getHeaderValue(String name) {
        HttpServletRequest request = ((ServletRequestAttributes) getRequestAttributes()).getRequest();
        return request.getHeader(name);
    }
}
