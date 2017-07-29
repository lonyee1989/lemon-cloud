package cn.lemon.security.core;

import cn.lemon.framework.encrypt.Md5Util;
import cn.lemon.security.config.Constant;
import cn.lemon.security.exception.UnAuthenticationException;
import cn.lemon.security.dto.ClientDetail;
import cn.lemon.security.store.IAuthzStore;
import cn.lemon.security.dto.UserDetail;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 登录授权管理
 * Created by lonyee on 2017/6/6.
 */
public class AuthorizationManager implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(AuthorizationManager.class);

    private IAuthzStore authzStore;

    public AuthorizationManager(IAuthzStore authzStore) {
        this.authzStore = authzStore;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.state(this.authzStore != null, "IAuthzStore must be provided");
    }

    /** 退出系统 **/
    public void logout(String token) {
        logger.debug("logout token: {}", token);
        authzStore.removeAccessToken(token);
    }

    /** 授权登录系统 **/
    public String authorize(Principal principal) {
        ClientDetail clientDetail = authzStore.getClientDetailByClientId(principal.getClientId(), principal.getClientSecret(), principal.getGrantType());
        if (clientDetail == null) {
            throw new UnAuthenticationException("client "+ principal.getClientId() +" is not authorized to access.");
        }
        UserDetail userDetail = null;
        String grantType = !Strings.isNullOrEmpty(principal.getGrantType())? principal.getGrantType().toLowerCase(): Constant.GRANT_CLIENT;
        logger.debug("authorize request grantType: {}", grantType);
        switch (grantType) {
            case Constant.GRANT_PASSWORD:
                userDetail = authzStore.getUserDetailByMobile(principal.getNation(), principal.getMobile(), principal.getPassword());
                break;
            case Constant.GRANT_CODE:
                userDetail = authzStore.getUserDetailByMobileCode(principal.getNation(), principal.getMobile(), principal.getCode());
                break;
            default:
                break;
        }
        if (userDetail !=null) {
            authzStore.setUserDetailByUserId(userDetail.getId(), userDetail);
            clientDetail.setUserId(userDetail.getId());
        }
        String token = this.getAccessToken(clientDetail);
        authzStore.setClientDetailByToken(token, clientDetail);
        logger.debug("authorize token: {}, userId: ", token, clientDetail.getUserId());

        return token;
    }

    /** 获取访问token **/
    protected String getAccessToken(ClientDetail clientDetail) {
        String accessInfo = String.format("clientId=%s,userId=%s,timestamp=%s", clientDetail.getId(), clientDetail.getUserId(), System.currentTimeMillis());
        return Md5Util.getMD5(accessInfo);
    }

}
