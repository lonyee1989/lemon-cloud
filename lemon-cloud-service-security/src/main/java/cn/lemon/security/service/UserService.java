package cn.lemon.security.service;

import cn.lemon.security.dao.IUserDao;
import cn.lemon.security.entity.User;
import com.google.common.collect.Lists;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录服务接口
 * Created by lonyee on 2017/4/14.
 */
@Service
public class UserService implements UserDetailsService {
    @Resource
    private IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByMobile(userName);
        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        if (!user.getUsable()) {
            throw new UsernameNotFoundException("账号已被禁用");
        }
        List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        /** 把角色写进去 */
        /*for(SysRole role:user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println(role.getName());
        }*/

        return new org.springframework.security.core.userdetails.User(
                    user.getMobile(), user.getPassword(), authorities);
    }
}
