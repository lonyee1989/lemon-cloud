package cn.lemon.security.controller;

import cn.lemon.framework.core.BasicController;
import cn.lemon.security.config.Constant;
import cn.lemon.security.core.AuthorizationManager;
import cn.lemon.security.core.Principal;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录授权管理
 * Created by lonyee on 2017/6/7.
 */
@RestController
public class AuthzStoreController extends BasicController {
    @Resource
    private AuthorizationManager authorizationManager;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login(Principal principal) {
       return authorizationManager.authorize(principal);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public void logout(@RequestHeader(value = Constant.TOKEN) String token) {
        authorizationManager.logout(token);
    }
}
