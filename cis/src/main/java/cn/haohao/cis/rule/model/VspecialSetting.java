package cn.haohao.cis.rule.model;

//j-import-b
import java.io.Serializable;
//j-import-e

/**
 * VspecialSetting
 * @author generator
 * @version 1.0
 * @since 1.0
 */
public class VspecialSetting implements Serializable{

	private static final long serialVersionUID = 1L;

	//构造函数
	public VspecialSetting(){
		
	}
	
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
	
	//属性 begin
	/*
	 * settingId
	 */
	private java.lang.Integer settingId;
	/*
	 * 比例
	 */
	private java.lang.Float proportion;
	/*
	 * ruleId
	 */
	private java.lang.Integer ruleId;
	/*
	 * 用户id
	 */
	private java.lang.Integer userId;
	/*
	 * settingLevel
	 */
	private java.lang.String settingLevel;
	/*
	 * 使用开始时间
	 */
	private java.util.Date usingDate;
	/*
	 * 弃用时间
	 */
	private java.util.Date endDate;
	
	private Integer status;
	//属性 end
	
	//方法 begin
	/*
	 * 获取 settingId
	 */
	public java.lang.Integer getSettingId(){
		return this.settingId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	/*
	 * 设置 settingId
	 */
	public void setSettingId(java.lang.Integer settingId){
		this.settingId = settingId;
	}
	/*
	 * 获取 比例
	 */
	public java.lang.Float getProportion(){
		return this.proportion;
	}
	/*
	 * 设置 比例
	 */
	public void setProportion(java.lang.Float proportion){
		this.proportion = proportion;
	}
	/*
	 * 获取 ruleId
	 */
	public java.lang.Integer getRuleId(){
		return this.ruleId;
	}
	/*
	 * 设置 ruleId
	 */
	public void setRuleId(java.lang.Integer ruleId){
		this.ruleId = ruleId;
	}
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
	 * 获取 settingLevel
	 */
	public java.lang.String getSettingLevel(){
		return this.settingLevel;
	}
	/*
	 * 设置 settingLevel
	 */
	public void setSettingLevel(java.lang.String settingLevel){
		this.settingLevel = settingLevel;
	}
	/*
	 * 获取 使用开始时间
	 */
	public java.util.Date getUsingDate(){
		return this.usingDate;
	}
	/*
	 * 设置 使用开始时间
	 */
	public void setUsingDate(java.util.Date usingDate){
		this.usingDate = usingDate;
	}
	/*
	 * 获取 弃用时间
	 */
	public java.util.Date getEndDate(){
		return this.endDate;
	}
	/*
	 * 设置 弃用时间
	 */
	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
	}
	//方法 end

	public Long getId(){
		if (settingId==null) return null;
		return Long.valueOf(this.settingId);
	}
}
