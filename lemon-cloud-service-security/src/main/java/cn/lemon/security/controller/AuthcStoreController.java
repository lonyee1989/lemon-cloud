package cn.lemon.security.controller;

import cn.lemon.security.dto.ClientDetail;
import cn.lemon.security.dto.UserDetail;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户端授权验证
 * Created by lonyee on 2017/6/7.
 */
@RestController
public class AuthcStoreController {

    @RequestMapping(method = RequestMethod.GET, value = "/auth/client/{token}")
    public ClientDetail getClientDetailByToken(@PathVariable(value = "token") String token) {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/auth/user/{userId}")
    public UserDetail getUserDetailByUserId(@PathVariable(value = "userId") Long userId) {
        return null;
    }
}
