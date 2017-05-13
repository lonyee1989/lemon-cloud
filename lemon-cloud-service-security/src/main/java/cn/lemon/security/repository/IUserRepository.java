package cn.lemon.security.repository;

import cn.lemon.security.entity.User;
import cn.lemon.framework.core.IBasicDao;

/**************************
 * user
 * 用户表
 * @author lonyee
 * @date 2017-04-05 15:20:52
 * 
 **************************/
public interface IUserRepository extends IBasicDao<User>{
    /**
     * 根据手机号查询用户信息
     */
    User findByMobile(String mobile);
}
