package cn.lemon.security.bean;

import cn.lemon.framework.core.BasicEntityBean;


/**************************
 * menu_permission
 * 菜单-权限表
 * @author lonyee
 * @date 2017-04-17 16:36:05
 * 
 **************************/
public class MenuPermission extends BasicEntityBean {
	//fields
	/** 菜单Id **/
	private Long menuId;
	/** 权限码 **/
	private String code;
	/** 权限 **/
	private String name;
	/** 链接地址 **/
	private String url;
	/** 备注 **/
	private String remark;


	public void setMenuId(Long menuId) {
		if (menuId != null) {
			this.menuId=menuId;
		}
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setCode(String code) {
		if (code != null) {
			this.code=code;
		}
	}
	public String getCode() {
		return code;
	}
	public void setName(String name) {
		if (name != null) {
			this.name=name;
		}
	}
	public String getName() {
		return name;
	}
	public void setUrl(String url) {
		if (url != null) {
			this.url=url;
		}
	}
	public String getUrl() {
		return url;
	}
	public void setRemark(String remark) {
		if (remark != null) {
			this.remark=remark;
		}
	}
	public String getRemark() {
		return remark;
	}
}
