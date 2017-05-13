package cn.lemon.cloud.account.dto;

import cn.lemon.framework.core.BasicDtoBean;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lonyee on 2017/5/9.
 */
@Getter
@Setter
public class UserDto extends BasicDtoBean {
    /** 主键id */
    private Long id;
    /** 国别代码 86 中国 **/
    private String nation;
    /** 登录手机号 **/
    private String mobile;
    /** 密码 **/
    private String password;
    /** 用户昵称 **/
    private String nickName;
    /** 头像地址 **/
    private String headImageUrl;
    /** 性别 0 未知 1 男性 2 女性 **/
    private Integer sex;
    /**  是否可用 */
    private Boolean usable;

}
