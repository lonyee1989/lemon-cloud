package cn.lemon.framework.query;

import java.util.Map;

import com.google.common.collect.Maps;


/**
 * 分页查询条件
 * @author lonyee
 */
public class QueryPage extends Page {
	
	private static final long serialVersionUID = 8981998004403741046L;
	
	/** 查询条件 */
	private Map<String, Object> condition = Maps.newConcurrentMap();
	/** 排序方式，根据设置字段的前后顺序 */
	private Map<String, Object> sort = Maps.newTreeMap();
	
	/**
	 * usable默认true 查询可用的记录 
	 */
	public QueryPage(){
		this.condition.put("usable", true);
	}
	/**
	 * 设置usable 查询记录是否可用的
	 */
	public QueryPage(Boolean usable){
		this.condition.put("usable", usable);
	}
	/**
	 * 设置usable 查询记录是否可用的
	 */
	public void setUsable(Boolean usable) {
		this.condition.put("usable", usable);
	}   
	
	/**
	 * 设置查询条件
	 * @param field 字段名称
	 * @param value 查询数据值
	 */
	public void setCondition(String field, Object value) {
		this.condition.put(field, value);
	}
	
	/**
	 * 设置排序方式，根据设置字段的前后顺序
	 * @param sort 排序字段名称
	 * @param order 排序方式 asc, desc
	 */
	public void setSort(String sort, Order order) {
		this.sort.put(sort, order);
	}
	
	/**
	 * 设置排序方式，根据设置字段的前后顺序 默认 asc
	 * @param sort 排序字段名称
	 */
	public void setSort(String sort) {
		this.sort.put(sort, Order.ASC);
	}
	
	
	public enum Order {
		/** 正序 */
		ASC,
		/** 倒序 */
		DESC
	}
}
