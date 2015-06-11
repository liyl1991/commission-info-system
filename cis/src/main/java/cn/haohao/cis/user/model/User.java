package cn.haohao.cis.user.model;

//j-import-b
import java.io.Serializable;
//j-import-e

import cn.haohao.cis.rule.model.IncomeSetting;

/**
 * User
 * @author generator
 * @version 1.0
 * @since 1.0
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	//构造函数
	public User(){
		
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
	 * 密码
	 */
	private java.lang.String password;
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
	 * 角色：1、用户，2、管理员
	 */
	private java.lang.Integer userRole;
	/*
	 * 上线员工
	 */
	private java.lang.Integer uplineUser;
	/*
	 * 扩展备用1
	 */
	private java.lang.String extend1;
	/*
	 * 扩展备用2
	 */
	private java.lang.String extend2;
	
	private IncomeSetting incomeSetting;
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
	 * 获取 密码
	 */
	public java.lang.String getPassword(){
		return this.password;
	}
	/*
	 * 设置 密码
	 */
	public void setPassword(java.lang.String password){
		this.password = password;
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
	 * 获取 角色：1、用户，2、管理员
	 */
	public java.lang.Integer getUserRole(){
		return this.userRole;
	}
	/*
	 * 设置 角色：1、用户，2、管理员
	 */
	public void setUserRole(java.lang.Integer userRole){
		this.userRole = userRole;
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
	 * 获取 扩展备用1
	 */
	public java.lang.String getExtend1(){
		return this.extend1;
	}
	/*
	 * 设置 扩展备用1
	 */
	public void setExtend1(java.lang.String extend1){
		this.extend1 = extend1;
	}
	/*
	 * 获取 扩展备用2
	 */
	public java.lang.String getExtend2(){
		return this.extend2;
	}
	/*
	 * 设置 扩展备用2
	 */
	public void setExtend2(java.lang.String extend2){
		this.extend2 = extend2;
	}
	//方法 end

	public Long getId(){
		if (userId==null) return null;
		return Long.valueOf(this.userId);
	}
	
	public IncomeSetting getIncomeSetting() {
		return incomeSetting;
	}
	public void setIncomeSetting(IncomeSetting incomeSetting) {
		this.incomeSetting = incomeSetting;
	}
	/**
	 * 是否是管理员
	 */
	public Boolean isAdmin(){
		if(this.userRole==2) return true;
		else return false;
	}
	public Boolean isLevelB(){
		return "B".equalsIgnoreCase(this.level);
	}
}
