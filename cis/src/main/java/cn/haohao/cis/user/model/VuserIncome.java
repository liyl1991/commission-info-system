package cn.haohao.cis.user.model;

//j-import-b
import java.io.Serializable;
//j-import-e

/**
 * VuserIncome
 * @author generator
 * @version 1.0
 * @since 1.0
 */
public class VuserIncome implements Serializable{

	private static final long serialVersionUID = 1L;

	//构造函数
	public VuserIncome(){
		
	}
	
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
	
	//属性 begin
	/*
	 * 用户id
	 */
	private java.lang.Integer userId;
	/*
	 * 编号
	 */
	private java.lang.String idCard;
	/*
	 * 姓名
	 */
	private java.lang.String name;
	/*
	 * 性别：1、男，2、女，3、保密
	 */
	private java.lang.String sex;
	/*
	 * 出生月年
	 */
	private java.util.Date birthday;
	/*
	 * 住址
	 */
	private java.lang.String address;
	/*
	 * 职业
	 */
	private java.lang.String career;
	/*
	 * 等级：A/B/C/D/E/x
	 */
	private java.lang.String level;
	/*
	 * 状态：1、正常、2、不可用
	 */
	private java.lang.Integer status;
	/*
	 * 上线员工
	 */
	private java.lang.Integer uplineUser;
	/*
	 * 角色
	 */
	private Integer userRole;
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
	 * 获取 编号
	 */
	public java.lang.String getIdCard(){
		return this.idCard;
	}
	/*
	 * 设置 编号
	 */
	public void setIdCard(java.lang.String idCard){
		this.idCard = idCard;
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
	 * 获取 性别：1、男，2、女，3、保密
	 */
	public java.lang.String getSex(){
		return this.sex;
	}
	/*
	 * 设置 性别：1、男，2、女，3、保密
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/*
	 * 获取 出生月年
	 */
	public java.util.Date getBirthday(){
		return this.birthday;
	}
	/*
	 * 设置 出生月年
	 */
	public void setBirthday(java.util.Date birthday){
		this.birthday = birthday;
	}
	/*
	 * 获取 住址
	 */
	public java.lang.String getAddress(){
		return this.address;
	}
	/*
	 * 设置 住址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/*
	 * 获取 职业
	 */
	public java.lang.String getCareer(){
		return this.career;
	}
	/*
	 * 设置 职业
	 */
	public void setCareer(java.lang.String career){
		this.career = career;
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
	 * 获取 状态：1、正常、2、不可用
	 */
	public java.lang.Integer getStatus(){
		return this.status;
	}
	/*
	 * 设置 状态：1、正常、2、不可用
	 */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	/*
	 * 获取 上线员工
	 */
	public java.lang.Integer getUplineUser(){
		return this.uplineUser;
	}
	/*
	 * 设置 上线员工
	 */
	public void setUplineUser(java.lang.Integer uplineUser){
		this.uplineUser = uplineUser;
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
	
	//方法 end

	public Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}
	public Long getId(){
		if (userId==null) return null;
		return Long.valueOf(this.userId);
	}
}
