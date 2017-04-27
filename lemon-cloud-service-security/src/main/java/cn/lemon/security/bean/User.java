package cn.lemon.security.bean;

import cn.lemon.framework.BasicEntityBean;



/**************************
 * user
 * 用户表
 * @author lonyee
 * @date 2017-04-05 15:20:52
 * 
 **************************/
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


	public void setNation(String nation) {
		if (nation != null) {
			this.nation=nation;
		}
	}
	public String getNation() {
		return nation;
	}
	public void setMobile(String mobile) {
		if (mobile != null) {
			this.mobile=mobile;
		}
	}
	public String getMobile() {
		return mobile;
	}
	public void setPassword(String password) {
		if (password != null) {
			this.password=password;
		}
	}
	public String getPassword() {
		return password;
	}
	public void setNickName(String nickName) {
		if (nickName != null) {
			this.nickName=nickName;
		}
	}
	public String getNickName() {
		return nickName;
	}
	public void setHeadImageUrl(String headImageUrl) {
		if (headImageUrl != null) {
			this.headImageUrl=headImageUrl;
		}
	}
	public String getHeadImageUrl() {
		return headImageUrl;
	}
	public void setEmail(String email) {
		if (email != null) {
			this.email=email;
		}
	}
	public String getEmail() {
		return email;
	}
	public void setSex(Integer sex) {
		if (sex != null) {
			this.sex=sex;
		}
	}
	public Integer getSex() {
		return sex;
	}
	public void setRealName(String realName) {
		if (realName != null) {
			this.realName=realName;
		}
	}
	public String getRealName() {
		return realName;
	}
	public void setCardType(Integer cardType) {
		if (cardType != null) {
			this.cardType=cardType;
		}
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardNo(String cardNo) {
		if (cardNo != null) {
			this.cardNo=cardNo;
		}
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setAreaId(Integer areaId) {
		if (areaId != null) {
			this.areaId=areaId;
		}
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setArea(String area) {
		if (area != null) {
			this.area=area;
		}
	}
	public String getArea() {
		return area;
	}
	public void setAddress(String address) {
		if (address != null) {
			this.address=address;
		}
	}
	public String getAddress() {
		return address;
	}
	public void setRemark(String remark) {
		if (remark != null) {
			this.remark=remark;
		}
	}
	public String getRemark() {
		return remark;
	}
	public void setTags(String tags) {
		if (tags != null) {
			this.tags=tags;
		}
	}
	public String getTags() {
		return tags;
	}
	public void setAuditted(Integer auditted) {
		if (auditted != null) {
			this.auditted=auditted;
		}
	}
	public Integer getAuditted() {
		return auditted;
	}
}
