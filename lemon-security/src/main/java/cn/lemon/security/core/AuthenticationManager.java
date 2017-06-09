package cn.lemon.security.core;

import cn.lemon.security.exception.UnAuthenticationException;
import cn.lemon.security.exception.UnAuthorizationException;
import cn.lemon.security.dto.ClientDetail;
import cn.lemon.security.store.IAuthcStore;
import cn.lemon.security.dto.UserDetail;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 客户端授权验证
 * Created by lonyee on 2017/5/31.
 */
public class AuthenticationManager implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(AuthenticationManager.class);

    private IAuthcStore authcStore;

    public AuthenticationManager (IAuthcStore authcStore) {
        this.authcStore = authcStore;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.state(this.authcStore != null, "IAuthcStore must be provided");
    }

    /**
     * 验证客户端是否在可以访问资源
     */
    public UserDetail authenticate(String token, String serviceId) {
        if (Strings.isNullOrEmpty(token)) {
            throw new UnAuthenticationException("client is not authorized to access.");
        }
        if (Strings.isNullOrEmpty(serviceId)) {
            throw new UnAuthorizationException("client illegal access to service "+ serviceId +".");
        }
        //获取token信息+userId
        ClientDetail clientDetail = authcStore.getClientDetailByToken(token);
        if (clientDetail == null) {
            throw new UnAuthenticationException("client is not authorized to access.");
        }
        if (clientDetail.getServiceIds()==null || !clientDetail.getServiceIds().contains(serviceId)) {
            throw new UnAuthorizationException("client is not allowed to access service "+ serviceId +".");
        }
        UserDetail userDetail = null;
        //获取用户信息
        if (clientDetail.getUserId() !=null && clientDetail.getUserId() > 0L) {
            userDetail = authcStore.getUserDetailByUserId(clientDetail.getUserId());
        }
        logger.debug("authenticate token: {}, serviceId: {}, userDetail: {}", token, serviceId, userDetail);

        return userDetail;
    }
}
