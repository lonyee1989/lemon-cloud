package cn.lemon.cloud.account.controller;

import cn.lemon.cloud.account.dto.UserDto;
import cn.lemon.cloud.account.entity.User;
import cn.lemon.cloud.account.repository.IUserRepository;
import cn.lemon.framework.core.BasicController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lonyee on 2017/4/6.
 */
@RestController
public class UserController extends BasicController {
    @Resource
    private IUserRepository userRepository;

    @RequestMapping(value = "/user/${moblie}" ,method = RequestMethod.GET)
    public UserDto getUserByMoblie(@PathVariable String moblie) {
        User user = userRepository.findByMobile(moblie);
        UserDto userDto = user.toDtoBean(UserDto.class);
        return userDto;
    }
}
