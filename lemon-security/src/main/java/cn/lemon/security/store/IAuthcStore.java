package cn.lemon.security.store;

import cn.lemon.security.dto.ClientDetail;
import cn.lemon.security.dto.UserDetail;

/**
 * 客户端授权验证存储
 * Created by lonyee on 2017/6/7.
 */
public interface IAuthcStore {

    /** 根据Token获取客户端信息 */
    public abstract ClientDetail getClientDetailByToken(String token);

    /** 根据userId获取用户信息 */
    public abstract UserDetail getUserDetailByUserId(Long userId);
}
