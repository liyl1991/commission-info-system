package cn.haohao.cis.income.model;

//j-import-b
import java.io.Serializable;
//j-import-e

/**
 * UserIncome
 * @author generator
 * @version 1.0
 * @since 1.0
 */
public class UserIncome implements Serializable{

	private static final long serialVersionUID = 1L;

	//构造函数
	public UserIncome(){
		
	}
	
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
	
	//属性 begin
	/*
	 * 收入信息id
	 */
	private java.lang.Integer incomeId;
	/*
	 * 用户id
	 */
	private java.lang.Integer userId;
	/*
	 * 月份
	 */
	private java.util.Date incomeDate;
	/*
	 * 收入
	 */
	private java.lang.Float income;
	/*
	 * 业绩
	 */
	private java.lang.Float performance;
	/*
	 * 使用提成规则的id
	 */
	private java.lang.Integer settingId;
	/*
	 * 提成来源id
	 */
	private java.lang.Integer fromIncomeId;
	/*
	 * 提成来源下级
	 */
	private Integer fromDownline;
	//属性 end
	
	//方法 begin
	/*
	 * 获取 收入信息id
	 */
	public java.lang.Integer getIncomeId(){
		return this.incomeId;
	}
	public Integer getFromDownline() {
		return fromDownline;
	}
	public void setFromDownline(Integer fromDownline) {
		this.fromDownline = fromDownline;
	}
	/*
	 * 设置 收入信息id
	 */
	public void setIncomeId(java.lang.Integer incomeId){
		this.incomeId = incomeId;
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
	 * 获取 月份
	 */
	public java.util.Date getIncomeDate(){
		return this.incomeDate;
	}
	/*
	 * 设置 月份
	 */
	public void setIncomeDate(java.util.Date incomeDate){
		this.incomeDate = incomeDate;
	}
	/*
	 * 获取 收入
	 */
	public java.lang.Float getIncome(){
		return this.income;
	}
	/*
	 * 设置 收入
	 */
	public void setIncome(java.lang.Float income){
		this.income = income;
	}
	/*
	 * 获取 业绩
	 */
	public java.lang.Float getPerformance(){
		return this.performance;
	}
	/*
	 * 设置 业绩
	 */
	public void setPerformance(java.lang.Float performance){
		this.performance = performance;
	}
	/*
	 * 获取 使用提成规则的id
	 */
	public java.lang.Integer getSettingId(){
		return this.settingId;
	}
	/*
	 * 设置 使用提成规则的id
	 */
	public void setSettingId(java.lang.Integer settingId){
		this.settingId = settingId;
	}
	/*
	 * 获取 提成来源id
	 */
	public java.lang.Integer getFromIncomeId(){
		return this.fromIncomeId;
	}
	/*
	 * 设置 提成来源id
	 */
	public void setFromIncomeId(java.lang.Integer fromIncomeId){
		this.fromIncomeId = fromIncomeId;
	}
	//方法 end

	public Long getId(){
		if (incomeId==null) return null;
		return Long.valueOf(this.incomeId);
	}
}
