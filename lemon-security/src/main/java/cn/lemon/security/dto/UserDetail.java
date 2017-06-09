package cn.lemon.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

/**
 * 用户信息
 * Created by lonyee on 2017/5/17.
 */
@Getter
@Setter
@ToString
public class UserDetail implements Serializable {
    /** 用户ID **/
    private Long id = 0L;
    /** 国别代码 86 中国 **/
    private String nation;
    /** 登录手机号 **/
    private String mobile;
    /** 用户昵称 **/
    private String nickName;
    /** 头像地址 **/
    private String headImageUrl;
    /** 性别 0 未知 1 男性 2 女性 **/
    private Integer sex;
    /** 权限列表 **/
    private Set<String> authentications;
}
