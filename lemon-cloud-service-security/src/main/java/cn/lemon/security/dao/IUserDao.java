package cn.lemon.security.dao;

import cn.lemon.security.entity.User;
import cn.lemon.framework.core.IBasicDao;
import org.apache.ibatis.annotations.Param;

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
    User findByMobile(@Param("nation") String nation, @Param("mobile") String mobile);
}
