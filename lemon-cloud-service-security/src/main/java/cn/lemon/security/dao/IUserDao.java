package cn.lemon.security.dao;

import cn.lemon.security.bean.User;
import cn.lemon.framework.core.IBasicDao;

/**************************
 * user
 * 用户表
 * @author lonyee
 * @date 2017-04-05 15:20:52
 * 
 **************************/
public interface IUserDao extends IBasicDao<User>{
    /**
     * 根据手机号查询用户信息
     */
    User findByMobile(String mobile);
}
