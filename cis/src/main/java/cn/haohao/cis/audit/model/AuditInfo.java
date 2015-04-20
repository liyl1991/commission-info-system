package cn.haohao.cis.audit.model;

//j-import-b
import java.io.Serializable;
//j-import-e

/**
 * AuditInfo
 * @author generator
 * @version 1.0
 * @since 1.0
 */
public class AuditInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	//构造函数
	public AuditInfo(){
		
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
	 * 审批状态：1、通过，2、等待，3、驳回
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
	 * 获取 审批状态：1、通过，2、等待，3、驳回
	 */
	public java.lang.Integer getAuditStatus(){
		return this.auditStatus;
	}
	/*
	 * 设置 审批状态：1、通过，2、等待，3、驳回
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
	//方法 end

	public Long getId(){
		if (userId==null) return null;
		return Long.valueOf(this.userId);
	}
}
