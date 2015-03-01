package cn.haohao.vas.core.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 模型基类
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract Long getId();

	/*
	 * 实体类声明。
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("ClassName:");
		str.append(this.getClass().getName());
		str.append(";ID:");
		str.append(String.valueOf(getId()));
		str.append(".");
		return str.toString();
	}

	/*
	 * 为相等提供实现。
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (!this.getClass().getName().equals(o.getClass().getName())) {
			return false;
		}
		BaseModel obj = (BaseModel) o;
		if (this.getId() == null && obj.getId() == null) {
			return super.equals(o);
		}
		if (this.getId().equals(obj.getId())) {
			return true;
		}
		return false;
	}

	/*
	 * HASH CODE
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		if (getId() == null)
			return super.hashCode();
		return new HashCodeBuilder().append(this.getClass().getName().toCharArray()).append(getId()).toHashCode();
	}

	/**
	 * 默认实现的相等方法
	 * 
	 * @param self
	 * @param other
	 * @return
	 */
	public boolean equalsUtil(Object self, Object other) {
		return EqualsBuilder.reflectionEquals(self, other);
	}

	/**
	 * 默认实现的hashcode方法
	 * 
	 * @param self
	 * @return
	 */
	public int hashCodeUtil(Object self) {
		return HashCodeBuilder.reflectionHashCode(self);
	}

	/**
	 * Object.hashCode();
	 * 
	 * @return
	 */
	public int hashCodeUtil() {
		return super.hashCode();
	}

}
