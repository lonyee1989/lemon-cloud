package cn.lemon.security.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录凭证信息
 * Created by lonyee on 2017/6/7.
 */
@Getter
@Setter
@ToString
public class Principal {
    /** 客户端ID **/
    private String clientId;
    /** 客户端密钥 **/
    private String clientSecret;
    /** 授权类型: client 仅客户端授权, password 用户密码登录, code 用户验证码登录 **/
    private String grantType;
    /** 国别编码 **/
    private String nation;
    /** 手机号码 **/
    private String mobile;
    /** 登录密码 **/
    private String password;
    /** 验证码 **/
    private String code;
}
