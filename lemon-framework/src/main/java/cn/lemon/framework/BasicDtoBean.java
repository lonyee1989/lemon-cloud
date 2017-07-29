package cn.lemon.framework;

import java.io.Serializable;

import cn.lemon.framework.utils.BeanUtil;
import cn.lemon.framework.utils.JsonUtil;


public abstract class BasicDtoBean implements Serializable {
	
	private static final long	serialVersionUID	= -6466964376068440943L;
	
	public BasicDtoBean() {}
	public <T> BasicDtoBean(BasicEntityBean<T> entityBean) {
		BeanUtil.toBeanValues(entityBean, this);
	}
	
	@Override
    public String toString() {
		return JsonUtil.writeValue(this);
    }
}
