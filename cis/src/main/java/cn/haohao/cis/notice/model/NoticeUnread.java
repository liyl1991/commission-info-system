package cn.haohao.cis.notice.model;

//j-import-b
import java.io.Serializable;
//j-import-e

/**
 * NoticeUnread
 * @author generator
 * @version 1.0
 * @since 1.0
 */
public class NoticeUnread implements Serializable{

	private static final long serialVersionUID = 1L;

	//构造函数
	public NoticeUnread(){
		
	}
	
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
	
	//属性 begin
	/*
	 * 员工id
	 */
	private java.lang.Integer userId;
	/*
	 * 公告id
	 */
	private java.lang.Integer noticeId;
	//属性 end
	
	//方法 begin
	/*
	 * 获取 员工id
	 */
	public java.lang.Integer getUserId(){
		return this.userId;
	}
	/*
	 * 设置 员工id
	 */
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	/*
	 * 获取 公告id
	 */
	public java.lang.Integer getNoticeId(){
		return this.noticeId;
	}
	/*
	 * 设置 公告id
	 */
	public void setNoticeId(java.lang.Integer noticeId){
		this.noticeId = noticeId;
	}
	//方法 end

	public Long getId(){
		if (userId==null) return null;
		return Long.valueOf(this.userId);
	}
}
