package cn.haohao.cis.rule.model;

//j-import-b
import java.io.Serializable;
//j-import-e

/**
 * SepcialSetting
 * @author generator
 * @version 1.0
 * @since 1.0
 */
public class SpecialSetting implements Serializable{

	private static final long serialVersionUID = 1L;

	//构造函数
	public SpecialSetting(){
		
	}
	
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
	
	//属性 begin
	/*
	 * 用户id
	 */
	private java.lang.Integer userId;
	/*
	 * settingId
	 */
	private java.lang.Integer settingId;
	/*
	 * 类型：1、提成比例，2、达标指数
	 */
	private java.lang.Integer type;
	//属性 end
	
	//方法 begin
	/*
	 * 获取 用户id
	 */
	public java.lang.Integer getUserId(){
		return this.userId;
	}
	/*
	 * 设置 用户id
	 */
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	/*
	 * 获取 settingId
	 */
	public java.lang.Integer getSettingId(){
		return this.settingId;
	}
	/*
	 * 设置 settingId
	 */
	public void setSettingId(java.lang.Integer settingId){
		this.settingId = settingId;
	}
	/*
	 * 获取 类型：1、提成比例，2、达标指数
	 */
	public java.lang.Integer getType(){
		return this.type;
	}
	/*
	 * 设置 类型：1、提成比例，2、达标指数
	 */
	public void setType(java.lang.Integer type){
		this.type = type;
	}
	//方法 end

	public Long getId(){
		if (userId==null) return null;
		return Long.valueOf(this.userId);
	}
}
