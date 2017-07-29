package cn.lemon.framework.core;

import java.io.Serializable;

import cn.lemon.framework.utils.BeanUtil;
import cn.lemon.framework.utils.JsonUtil;
import lombok.Getter;
import lombok.Setter;

public abstract class BasicDtoBean implements Serializable {
	
	private static final long	serialVersionUID	= -6466964376068440943L;
	
	public BasicDtoBean() {}
	public <T> BasicDtoBean(BasicEntityBean<T> entityBean) {
		BeanUtil.toBeanValues(entityBean, this);
	}

	public <T extends BasicEntityBean> T toEntityBean(Class<T> entityClazz) {
		return BeanUtil.toBeanValues(this, entityClazz);
	}

	@Override
    public String toString() {
		return JsonUtil.writeValue(this);
    }
}
