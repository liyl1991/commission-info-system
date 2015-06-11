package cn.haohao.cis.rule.model;

//j-import-b
import java.io.Serializable;
//j-import-e

/**
 * IncomeRule
 * @author generator
 * @version 1.0
 * @since 1.0
 */
public class IncomeRule implements Serializable{

	private static final long serialVersionUID = 1L;

	//构造函数
	public IncomeRule(){
		
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
	//方法 end

	public Long getId(){
		if (ruleId==null) return null;
		return Long.valueOf(this.ruleId);
	}
}
