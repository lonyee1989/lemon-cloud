package cn.lemon.security.service;

import cn.lemon.framework.BasicService;
import cn.lemon.framework.encrypt.Md5Util;
import cn.lemon.framework.query.Query;
import cn.lemon.security.bean.MenuPermission;
import cn.lemon.security.bean.User;
import cn.lemon.security.dao.IMenuPermissionDao;
import cn.lemon.security.dao.IUserDao;
import cn.lemon.security.response.ResultSecMessage;
import com.google.common.collect.Lists;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户登录服务接口
 * Created by lonyee on 2017/4/14.
 */
@Service
public class UserService extends BasicService implements UserDetailsService {
    @Resource
    private IUserDao userDao;
    @Resource
    private IMenuPermissionDao menuPermissionDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByMobile(userName);
        if(user == null) {
            throw new UsernameNotFoundException(ResultSecMessage.F5001.getMessage());
        }
        if (!user.getUsable()) {
            throw new UsernameNotFoundException(ResultSecMessage.F5007.getMessage());
        }
        //添加用户的权限。
        List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
        Query query = new Query();
        query.put("userId", user.getId());
        List<MenuPermission> permissionList = menuPermissionDao.findAll(query);
        for(MenuPermission permission: permissionList) {
            authorities.add(new SimpleGrantedAuthority(permission.getCode()));
            logger.debug("add authoritie code:[{}], name:[{}]", permission.getCode(), permission.getName());
        }
        return new org.springframework.security.core.userdetails.User(user.getMobile(), user.getPassword(), authorities);
    }
}
