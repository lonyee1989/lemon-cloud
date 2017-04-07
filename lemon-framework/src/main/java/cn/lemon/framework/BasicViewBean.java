package cn.lemon.framework;

import java.io.Serializable;

import cn.lemon.framework.utils.JsonUtil;

public abstract class BasicViewBean implements Serializable {

	private static final long	serialVersionUID	= 3280372556384919656L;

	public BasicViewBean() {
		
	}
	
	@Override
    public String toString() {
        return JsonUtil.writeValue(this);
    }
}
