package cn.lemon.security.filter;

import cn.lemon.framework.response.ResultMessage;
import cn.lemon.framework.utils.JsonUtil;
import cn.lemon.security.config.Constant;
import cn.lemon.security.core.AuthenticationManager;
import cn.lemon.security.dto.UserDetail;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 利用Zuul服务网关过滤用户访问权限
 * Created by lonyee on 2017/4/6.
 */
public class AccessFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);
    private AuthenticationManager authenticationManager;

    public AccessFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String filterType() {
        //四种不同生命周期过滤器
        //pre：可以在请求被路由之前调用
        //route：在路由请求时候被调用
        //post：在route和error过滤器之后被调用
        //error：处理请求时发生错误时被调用
        return "pre";
    }
    @Override
    public int filterOrder() {
        return 8; // 优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true; // 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        String token = request.getHeader(Constant.TOKEN);
        if (Strings.isNullOrEmpty(token)) {
            token = request.getParameter(Constant.TOKEN);
        }
        UserDetail userDetail = null;
        try {
            Object serviceId = ctx.get("serviceId");
            userDetail = authenticationManager.authenticate(token, serviceId.toString());
            if (userDetail!=null) {
                ctx.addZuulRequestHeader(Constant.USER_INFO, JsonUtil.writeValue(userDetail));
            }
        } catch (RuntimeException ex) {
            logger.warn(ex.getMessage());
            ctx.setSendZuulResponse(false); //过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(ResultMessage.F4001.getCode());
            ctx.setResponseBody(ResultMessage.F4001.toString());
            return false;
        }
        logger.info("user key: [{}], user: [{}].", token, userDetail.toString());
        return true;
    }
}
