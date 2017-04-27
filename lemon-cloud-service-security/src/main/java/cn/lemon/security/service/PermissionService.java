package cn.lemon.security.service;

import cn.lemon.framework.query.Query;
import cn.lemon.security.bean.MenuPermission;
import cn.lemon.security.dao.IMenuPermissionDao;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 重写权限验证 (如果登录时只写入角色信息，重写此方法扩展权限验证)
 * Created by lonyee on 2017/4/14.
 */
//@Service
public class PermissionService implements PermissionEvaluator {
    @Resource
    private IMenuPermissionDao menuPermissionDao;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        //@PreAuthorize("authenticated and hasPermission('hello', 'view')")
        String mobile = authentication.getName();
        Query query = new Query();
        query.put("mobile", mobile);
        query.put("target", targetDomainObject);
        query.put("permission", permission);
        MenuPermission mp = menuPermissionDao.find(query);
        return mp!=null;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        String mobile = authentication.getName();
        Query query = new Query();
        query.put("mobile", mobile);
        query.put("target", targetId);
        query.put("targetType", targetType);
        query.put("permission", permission);
        MenuPermission mp = menuPermissionDao.find(query);
        return mp!=null;
    }
}
