package cn.lemon.security.service;

import cn.lemon.framework.core.BasicService;
import cn.lemon.framework.encrypt.Md5Util;
import cn.lemon.framework.query.Query;
import cn.lemon.framework.response.ServiceException;
import cn.lemon.framework.utils.BeanUtil;
import cn.lemon.framework.utils.JsonUtil;
import cn.lemon.security.dao.IClientDetailDao;
import cn.lemon.security.dao.IMenuPermissionDao;
import cn.lemon.security.dao.IUserDao;
import cn.lemon.security.dto.ClientDetail;
import cn.lemon.security.dto.UserDetail;
import cn.lemon.security.entity.MenuPermission;
import cn.lemon.security.entity.User;
import cn.lemon.security.response.ResultSecMessage;
import cn.lemon.security.store.IAuthzStore;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by lonyee on 2017/6/7.
 */
@Service
public class AuthStoreService extends BasicService implements IAuthzStore {

    private static String SECURITY_TOKEN = "LEMON:SECURITY:TOKEN:";
    private static String SECURITY_USER_ID = "LEMON:SECURITY:USER:";
    private int expireTime = 30*24*60*60; //将过期时间设为30天

    private static String SECURITY_CODE = "LEMON:SECURITY:CODE:";
    private int expireCodeTime = 5*60; //将过期时间设为5分钟

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private IClientDetailDao clientDetailDao;
    @Resource
    private IUserDao userDao;
    @Resource
    private IMenuPermissionDao menuPermissionDao;

    /** 根据Token获取客户端详情 **/
    public ClientDetail getClientDetailByToken(String token) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(SECURITY_TOKEN + token);
        String res = ops.get();
        if (Strings.isNullOrEmpty(res)) {
            return null;
        }
        //延长授权时间
        ops.expire(expireTime, TimeUnit.MILLISECONDS);
        return JsonUtil.readValue(res, ClientDetail.class);
    }

    /** 根据用户ID获取用户详情 **/
    public UserDetail getUserDetailByUserId(Long userId) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(SECURITY_USER_ID + userId);
        String res = ops.get();
        if (Strings.isNullOrEmpty(res)) {
            return null;
        }
        //延长授权时间
        ops.expire(expireTime, TimeUnit.MILLISECONDS);
        return JsonUtil.readValue(res, UserDetail.class);
    }

    /** 缓存客户端Token信息 **/
    @Override
    public void setClientDetailByToken(String token, ClientDetail clientDetail) {
        String json = JsonUtil.writeValue(clientDetail);
        redisTemplate.boundValueOps(SECURITY_TOKEN + token).set(json, expireTime);
    }

    /** 缓存验证码 **/
    public void setMobileCode(String nation, String mobile, String code) {
        redisTemplate.boundValueOps(SECURITY_CODE + nation +":" + mobile).set(code, expireCodeTime);
    }

    /** 设置登录用户缓存 **/
    @Override
    public void setUserDetailByUserId(Long userId, UserDetail userDetail) {
        String json = JsonUtil.writeValue(userDetail);
        redisTemplate.boundValueOps(SECURITY_USER_ID + userId).set(json, expireTime);
    }

    /** 移除登录状态信息 **/
    @Override
    public void removeAccessToken(String token) {
        //只清除token信息，用户信息可以存在
        redisTemplate.delete(SECURITY_TOKEN + token);
    }


    /** 根据传入信息从数据库获取客户端信息 **/
    @Override
    public ClientDetail getClientDetailByClientId(String clientId, String clientSecret, String grantType) {
        Query query = new Query();
        query.put("clientId", clientId);
        query.put("clientSecret", clientSecret);
        query.put("authorizedGrantTypes", grantType);
        return clientDetailDao.find(query);
    }

    /** 根据传入信息从数据库获取用户信息 **/
    @Override
    public UserDetail getUserDetailByMobile(String nation, String mobile, String password) {
        User user = userDao.findByMobile(nation, mobile);
        if (user == null) {
            return null;
        }
        if (!Md5Util.getMD5(password).equals(user.getPassword())) {
            return null;
        }
        UserDetail userDetail = BeanUtil.toBeanValues(user, UserDetail.class);
        // 设置权限码
        this.setAuthentications(userDetail);
        return userDetail;
    }

    /** 根据传入信息从数据库获取用户信息 **/
    @Override
    public UserDetail getUserDetailByMobileCode(String nation, String mobile, String code) {
        String rtCode = redisTemplate.boundValueOps(SECURITY_CODE + nation +":" + mobile).get();
        if (!Strings.isNullOrEmpty(code) && code.equalsIgnoreCase(rtCode)) {
            logger.warn("nation: {}, mobile: {},  code: {}, right code: {}.", nation, mobile, code, rtCode);
            throw new ServiceException(ResultSecMessage.F5004); //验证码错误
        }
        User user = userDao.findByMobile(nation, mobile);
        UserDetail userDetail = BeanUtil.toBeanValues(user, UserDetail.class);
        // 设置权限码
        this.setAuthentications(userDetail);
        return userDetail;
    }

    private void setAuthentications(UserDetail userDetail) {
        if (userDetail == null) {
            return;
        }
        Query query = new Query();
        query.put("userId", userDetail.getId());
        List<MenuPermission> plist = menuPermissionDao.findAll(query);
        if (plist.size()>0) {
            Set<String> authentications = Sets.newHashSet();
            for (MenuPermission permission : plist) {
                authentications.add(permission.getCode());
            }
            userDetail.setAuthentications(authentications);
        }
    }

}
