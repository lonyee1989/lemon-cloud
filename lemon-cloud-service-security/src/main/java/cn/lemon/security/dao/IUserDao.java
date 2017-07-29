package cn.lemon.security.dao;

import cn.lemon.framework.IBasicDao;
import cn.lemon.security.bean.User;

/**************************
 * user
 * 用户表
 * @author lonyee
 * @date 2017-04-05 15:20:52
 * 
 **************************/
public interface IUserDao extends IBasicDao<User, Long>{
    /**
     * 根据手机号查询用户信息
     */
    User findByMobile(String mobile);
}
