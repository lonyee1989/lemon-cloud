package cn.lemon.framework.core;

import java.io.Serializable;
import java.util.Date;

import cn.lemon.framework.utils.BeanUtil;
import cn.lemon.framework.utils.JsonUtil;
import cn.lemon.framework.utils.SerialNumberUtil;

import com.google.common.base.Objects;

/**
 * <p>
 * 实体模型的基类(实现了序列化接口),
 * 所有的Domain对象都需要从BaseEntity继承
 * </p>
 *
 * @author lonyee
 * @date 2016-07-14
 */
public abstract class BasicEntityBean implements Serializable {
    
	private static final long serialVersionUID = -1394178167652755500L;
	public BasicEntityBean() {}
	public BasicEntityBean(BasicViewBean viewBean) {
		BeanUtil.toBeanValues(viewBean, this);
	}
	
	/**
     * 主键id
     */
    private Long id = SerialNumberUtil.instance().nextId();
    /**
     * 创建人员
     */
    private Long creator;
    /**
     * 创建时间
     */
    private Date createdDate;
    /**
     * 最后修改人员
     */
    private Long updater;
    /**
     * 最后修改时间
     */
    private Date updatedDate;
    /**
     * 是否可用（软删除使用）
     */
    private Boolean usable = true;
    
    public Long getId() {
        return (id==null || id<=0)? null: id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdater() {
		return updater;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Boolean getUsable() {
		return usable;
	}

	public void setUsable(Boolean usable) {
		this.usable = usable;
	}

	@Override
    public String toString() {
		return JsonUtil.writeValue(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicEntityBean that = (BasicEntityBean) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
