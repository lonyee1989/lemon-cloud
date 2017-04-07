package cn.lemon.framework.query;

import java.util.HashMap;

/**
 * 查询条件基础类, 默认查询可用的记录 (继承于HashMap)
 * @author lonyee
 *
 */
public class Query extends HashMap<String, Object> {

	private static final long	serialVersionUID	= 6287357749898704919L;
	
	/**
	 * usable默认true 查询可用的记录 
	 */
	public Query(){
		this.put("usable", true);
	}
	/**
	 * 设置usable 查询记录是否可用的
	 */
	public Query(Boolean usable){
		this.put("usable", usable);
	}
	/**
	 * 设置usable 查询记录是否可用的
	 */
	public void setUsable(Boolean usable) {
		this.put("usable", usable);
	}   
}
