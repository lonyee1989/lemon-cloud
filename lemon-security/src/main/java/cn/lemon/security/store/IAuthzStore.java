package cn.lemon.security.store;

import cn.lemon.security.dto.ClientDetail;
import cn.lemon.security.dto.UserDetail;

/**
 * 登录授权管理存储
 * Created by lonyee on 2017/6/7.
 */
public interface IAuthzStore {

    /** 根据clientId获取客户端信息 */
     ClientDetail getClientDetailByClientId(String clientId, String clientSecret, String grantType);

    /** 根据Token存储客户端信息 */
     void setClientDetailByToken(String token, ClientDetail clientDetail);

    /** 根据登录信息获取用户信息 */
     UserDetail getUserDetailByMobile(String nation, String mobile, String password);

    /** 根据验证码登录信息获取用户信息 */
     UserDetail getUserDetailByMobileCode(String nation, String mobile, String code);

    /** 根据userId获取用户信息 */
     void setUserDetailByUserId(Long userId, UserDetail userDetail);

    /** 移除授权Token **/
     void removeAccessToken(String token);
}
