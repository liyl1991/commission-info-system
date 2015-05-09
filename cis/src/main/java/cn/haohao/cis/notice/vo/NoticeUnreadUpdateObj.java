package cn.haohao.cis.notice.vo;
//j-import-b
import cn.haohao.cis.notice.model.NoticeUnread;
//j-import-e
/**
 *	VO
 */
public class NoticeUnreadUpdateObj extends NoticeUnread {
	
	private static final long serialVersionUID = 1L;
	private NoticeUnread newUpdAttObj=new NoticeUnread();

	public void setNewUpdAttObj(NoticeUnread newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public NoticeUnread getNewUpdAttObj() {
		return newUpdAttObj;
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}