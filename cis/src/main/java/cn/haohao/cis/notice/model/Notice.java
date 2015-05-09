package cn.haohao.cis.notice.model;

//j-import-b
import java.io.Serializable;
//j-import-e

/**
 * Notice
 * @author generator
 * @version 1.0
 * @since 1.0
 */
public class Notice implements Serializable{

	private static final long serialVersionUID = 1L;

	//构造函数
	public Notice(){
		
	}
	
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
	
	//属性 begin
	/*
	 * 主键
	 */
	private java.lang.Integer noticeId;
	/*
	 * 标题
	 */
	private java.lang.String title;
	/*
	 * 内容
	 */
	private java.lang.String content;
	/*
	 * 创建时间
	 */
	private java.util.Date createDate;
	/*
	 * 创建者id
	 */
	private java.lang.Integer createUser;
	/*
	 * 置顶标识：1、置顶，2、未置顶
	 */
	private java.lang.Integer topFlag;
	/*
	 * 置顶时间
	 */
	private java.util.Date topDate;
	/*
	 * 状态：1、正常，2、不可用
	 */
	private java.lang.Integer status;
	/*
	 * 已读标识：1、已读，2、未读
	 */
	private Integer readFlag;
	//属性 end
	
	//方法 begin
	/*
	 * 获取 主键
	 */
	public java.lang.Integer getNoticeId(){
		return this.noticeId;
	}
	public Integer getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
	}
	/*
	 * 设置 主键
	 */
	public void setNoticeId(java.lang.Integer noticeId){
		this.noticeId = noticeId;
	}
	/*
	 * 获取 标题
	 */
	public java.lang.String getTitle(){
		return this.title;
	}
	/*
	 * 设置 标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/*
	 * 获取 内容
	 */
	public java.lang.String getContent(){
		return this.content;
	}
	/*
	 * 设置 内容
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/*
	 * 获取 创建时间
	 */
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	/*
	 * 设置 创建时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/*
	 * 获取 创建者id
	 */
	public java.lang.Integer getCreateUser(){
		return this.createUser;
	}
	/*
	 * 设置 创建者id
	 */
	public void setCreateUser(java.lang.Integer createUser){
		this.createUser = createUser;
	}
	/*
	 * 获取 置顶标识：1、置顶，2、未置顶
	 */
	public java.lang.Integer getTopFlag(){
		return this.topFlag;
	}
	/*
	 * 设置 置顶标识：1、置顶，2、未置顶
	 */
	public void setTopFlag(java.lang.Integer topFlag){
		this.topFlag = topFlag;
	}
	/*
	 * 获取 置顶时间
	 */
	public java.util.Date getTopDate(){
		return this.topDate;
	}
	/*
	 * 设置 置顶时间
	 */
	public void setTopDate(java.util.Date topDate){
		this.topDate = topDate;
	}
	/*
	 * 获取 状态：1、正常，2、不可用
	 */
	public java.lang.Integer getStatus(){
		return this.status;
	}
	/*
	 * 设置 状态：1、正常，2、不可用
	 */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	//方法 end

	public Long getId(){
		if (noticeId==null) return null;
		return Long.valueOf(this.noticeId);
	}
}
