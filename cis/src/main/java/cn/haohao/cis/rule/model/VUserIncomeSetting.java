package cn.haohao.cis.rule.model;

//j-import-b
import java.io.Serializable;
//j-import-e

/**
 * VUserIncomeSetting
 * @author generator
 * @version 1.0
 * @since 1.0
 */
public class VUserIncomeSetting implements Serializable{

	private static final long serialVersionUID = 1L;

	//构造函数
	public VUserIncomeSetting(){
		
	}
	
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
	
	//属性 begin
	/*
	 * 主键
	 */
	private java.lang.Integer ruleId;
	/*
	 * 规则内容
	 */
	private java.lang.String detailContent;
	/*
	 * 主键
	 */
	private java.lang.Integer commonSettingId;
	/*
	 * 比例
	 */
	private java.lang.Float commonSetting;
	/*
	 * settingLevel
	 */
	private java.lang.String settingLevel;
	/*
	 * specialSettingId
	 */
	private java.lang.Integer specialSettingId;
	/*
	 * 比例
	 */
	private java.lang.Float specialSetting;
	/*
	 * 用户id
	 */
	private java.lang.Integer userId;
	//属性 end
	
	//方法 begin
	/*
	 * 获取 主键
	 */
	public java.lang.Integer getRuleId(){
		return this.ruleId;
	}
	/*
	 * 设置 主键
	 */
	public void setRuleId(java.lang.Integer ruleId){
		this.ruleId = ruleId;
	}
	/*
	 * 获取 规则内容
	 */
	public java.lang.String getDetailContent(){
		return this.detailContent;
	}
	/*
	 * 设置 规则内容
	 */
	public void setDetailContent(java.lang.String detailContent){
		this.detailContent = detailContent;
	}
	/*
	 * 获取 主键
	 */
	public java.lang.Integer getCommonSettingId(){
		return this.commonSettingId;
	}
	/*
	 * 设置 主键
	 */
	public void setCommonSettingId(java.lang.Integer commonSettingId){
		this.commonSettingId = commonSettingId;
	}
	/*
	 * 获取 比例
	 */
	public java.lang.Float getCommonSetting(){
		return this.commonSetting;
	}
	/*
	 * 设置 比例
	 */
	public void setCommonSetting(java.lang.Float commonSetting){
		this.commonSetting = commonSetting;
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
	 * 获取 specialSettingId
	 */
	public java.lang.Integer getSpecialSettingId(){
		return this.specialSettingId;
	}
	/*
	 * 设置 specialSettingId
	 */
	public void setSpecialSettingId(java.lang.Integer specialSettingId){
		this.specialSettingId = specialSettingId;
	}
	/*
	 * 获取 比例
	 */
	public java.lang.Float getSpecialSetting(){
		return this.specialSetting;
	}
	/*
	 * 设置 比例
	 */
	public void setSpecialSetting(java.lang.Float specialSetting){
		this.specialSetting = specialSetting;
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
	//方法 end

	public Long getId(){
		if (ruleId==null) return null;
		return Long.valueOf(this.ruleId);
	}
}
