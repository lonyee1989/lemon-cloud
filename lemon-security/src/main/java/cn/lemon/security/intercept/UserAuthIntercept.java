package cn.lemon.security.intercept;

import cn.lemon.security.config.Constant;
import cn.lemon.security.exception.UnAuthenticationException;
import com.google.common.base.Strings;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录授权拦截
 * 
 * @author lonyee
 *
 */
public class UserAuthIntercept extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String userInfo = request.getHeader(Constant.USER_INFO);
		if (Strings.isNullOrEmpty(userInfo)) {
			throw new UnAuthenticationException("user is not authorized to access.");
		}
		// UserDetail userDetail = JsonUtil.readValue(userInfo, UserDetail.class);
		return true;
	}
}
