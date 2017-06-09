package cn.lemon.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

/**
 * 客户端信息
 * Created by lonyee on 2017/5/17.
 */
@Getter
@Setter
@ToString
public class ClientDetail implements Serializable {

    /** 客户端ID **/
    private Long id = 0L;
    /** 用户ID **/
    private Long userId = -1L;
    /** 客户端名称 **/
    private String name;
    /** 客户端ID **/
    private String clientId;
    /** 客户端密钥 **/
    private String clientSecret;
    /** 可访问资源Id **/
    private Set<String> serviceIds;
    /** 授权访问类型 **/
    private Set<String> authorizedGrantTypes;
    /** 授权有效时间 **/
    private Integer accessTokenValidity;

}
