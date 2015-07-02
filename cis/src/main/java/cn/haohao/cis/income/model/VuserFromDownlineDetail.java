package cn.haohao.cis.income.model;

//j-import-b
import java.io.Serializable;
//j-import-e

/**
 * VuserFromDownlineDetail
 * @author generator
 * @version 1.0
 * @since 1.0
 */
public class VuserFromDownlineDetail implements Serializable{

	private static final long serialVersionUID = 1L;

	//构造函数
	public VuserFromDownlineDetail(){
		
	}
	
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
	
	//属性 begin
	/*
	 * 用户id
	 */
	private java.lang.Integer userId;
	/*
	 * 月份
	 */
	private java.util.Date incomeDate;
	/*
	 * 来源下线
	 */
	private java.lang.Integer fromDownline;
	/*
	 * income
	 */
	private java.lang.Float income;
	/*
	 * performance
	 */
	private java.lang.Float performance;
	/*
	 * 姓名
	 */
	private java.lang.String name;
	/*
	 * 等级：A/B/C/D/E/x
	 */
	private java.lang.String level;
	/*
	 * 姓名
	 */
	private java.lang.String downlineName;
	/*
	 * 等级：A/B/C/D/E/x
	 */
	private java.lang.String downlineLevel;
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
	 * 获取 来源下线
	 */
	public java.lang.Integer getFromDownline(){
		return this.fromDownline;
	}
	/*
	 * 设置 来源下线
	 */
	public void setFromDownline(java.lang.Integer fromDownline){
		this.fromDownline = fromDownline;
	}
	/*
	 * 获取 income
	 */
	public java.lang.Float getIncome(){
		return this.income;
	}
	/*
	 * 设置 income
	 */
	public void setIncome(java.lang.Float income){
		this.income = income;
	}
	/*
	 * 获取 performance
	 */
	public java.lang.Float getPerformance(){
		return this.performance;
	}
	/*
	 * 设置 performance
	 */
	public void setPerformance(java.lang.Float performance){
		this.performance = performance;
	}
	/*
	 * 获取 姓名
	 */
	public java.lang.String getName(){
		return this.name;
	}
	/*
	 * 设置 姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/*
	 * 获取 等级：A/B/C/D/E/x
	 */
	public java.lang.String getLevel(){
		return this.level;
	}
	/*
	 * 设置 等级：A/B/C/D/E/x
	 */
	public void setLevel(java.lang.String level){
		this.level = level;
	}
	/*
	 * 获取 姓名
	 */
	public java.lang.String getDownlineName(){
		return this.downlineName;
	}
	/*
	 * 设置 姓名
	 */
	public void setDownlineName(java.lang.String downlineName){
		this.downlineName = downlineName;
	}
	/*
	 * 获取 等级：A/B/C/D/E/x
	 */
	public java.lang.String getDownlineLevel(){
		return this.downlineLevel;
	}
	/*
	 * 设置 等级：A/B/C/D/E/x
	 */
	public void setDownlineLevel(java.lang.String downlineLevel){
		this.downlineLevel = downlineLevel;
	}
	//方法 end

	public Long getId(){
		if (userId==null) return null;
		return Long.valueOf(this.userId);
	}
}
