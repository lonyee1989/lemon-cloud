package cn.lemon.cloud.account.entity;

import cn.lemon.framework.core.BasicEntityBean;
import lombok.Getter;
import lombok.Setter;


/**************************
 * user
 * 用户表
 * @author lonyee
 * @date 2017-04-05 15:20:52
 * 
 **************************/
@Getter
@Setter
public class User extends BasicEntityBean {
	//fields
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
	/** 邮箱 **/
	private String email;
	/** 性别 0 未知 1 男性 2 女性 **/
	private Integer sex;
	/** 真实姓名 **/
	private String realName;
	/** 证件类型 0 身份证 1 护照 2 军官证 3 港澳通行证 4 台湾通行证 9 其他证件 **/
	private Integer cardType;
	/** 证件编号 **/
	private String cardNo;
	/** 所在地区ID **/
	private Integer areaId;
	/** 所在区域 **/
	private String area;
	/** 详细地址 **/
	private String address;
	/** 备注说明 **/
	private String remark;
	/** 个性标签 如：小清新,时尚达人,帅气 **/
	private String tags;
	/** 实名认证 0未认证, 1待认证, 5未通过, 9已认证 **/
	private Integer auditted;

}
