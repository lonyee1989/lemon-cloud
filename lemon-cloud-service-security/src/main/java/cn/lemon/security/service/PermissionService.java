package cn.lemon.security.service;

import cn.lemon.security.dao.IUserDao;
import cn.lemon.security.entity.User;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 重写权限验证
 * Created by lonyee on 2017/4/14.
 */
@Service
public class PermissionService implements PermissionEvaluator {
    @Resource
    private IUserDao userDao;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        //@PreAuthorize("authenticated and hasPermission('hello', 'view')")
        String username = authentication.getName();
        User user = userDao.findByMobile(username);
        return true;//roleService.authorized(user.getId(), targetDomainObject.toString(), permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // not supported
        return false;
    }
}
