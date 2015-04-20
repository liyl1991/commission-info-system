package cn.haohao.cis.audit.model;

//j-import-b
import java.io.Serializable;
//j-import-e

/**
 * VauditInfo
 * @author generator
 * @version 1.0
 * @since 1.0
 */
public class VauditInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	//构造函数
	public VauditInfo(){
		
	}
	
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
	
	//属性 begin
	/*
	 * 用户id
	 */
	private java.lang.Integer userId;
	/*
	 * 审批人
	 */
	private java.lang.Integer auditAdmin;
	/*
	 * 审批状态：1、通过，2、驳回，3、等待
	 */
	private java.lang.Integer auditStatus;
	/*
	 * 注册时间
	 */
	private java.util.Date registerDate;
	/*
	 * 审批时间
	 */
	private java.util.Date auditDate;
	/*
	 * 姓名
	 */
	private java.lang.String name;
	/*
	 * 编号
	 */
	private java.lang.String idCard;
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
	 * 扩展备用1
	 */
	private java.lang.String extend1;
	/*
	 * 扩展备用2
	 */
	private java.lang.String extend2;
	/*
	 * 状态：1、正常、2、不可用
	 */
	private java.lang.Integer status;
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
	 * 获取 审批人
	 */
	public java.lang.Integer getAuditAdmin(){
		return this.auditAdmin;
	}
	/*
	 * 设置 审批人
	 */
	public void setAuditAdmin(java.lang.Integer auditAdmin){
		this.auditAdmin = auditAdmin;
	}
	/*
	 * 获取 审批状态：1、通过，2、驳回，3、等待
	 */
	public java.lang.Integer getAuditStatus(){
		return this.auditStatus;
	}
	/*
	 * 设置 审批状态：1、通过，2、驳回，3、等待
	 */
	public void setAuditStatus(java.lang.Integer auditStatus){
		this.auditStatus = auditStatus;
	}
	/*
	 * 获取 注册时间
	 */
	public java.util.Date getRegisterDate(){
		return this.registerDate;
	}
	/*
	 * 设置 注册时间
	 */
	public void setRegisterDate(java.util.Date registerDate){
		this.registerDate = registerDate;
	}
	/*
	 * 获取 审批时间
	 */
	public java.util.Date getAuditDate(){
		return this.auditDate;
	}
	/*
	 * 设置 审批时间
	 */
	public void setAuditDate(java.util.Date auditDate){
		this.auditDate = auditDate;
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
	//方法 end

	public Long getId(){
		if (userId==null) return null;
		return Long.valueOf(this.userId);
	}
}
