package cn.lemon.cloud.account.entity;

import cn.lemon.framework.core.BasicEntityBean;
import lombok.Getter;
import lombok.Setter;


/**************************
 * menu_permission
 * 菜单-权限表
 * @author lonyee
 * @date 2017-04-17 16:36:05
 * 
 **************************/
@Getter
@Setter
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

}
