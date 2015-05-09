package cn.haohao.cis.notice.vo;
//j-import-b
import cn.haohao.cis.notice.model.Notice;
//j-import-e
/**
 *	VO
 */
public class NoticeUpdateObj extends Notice {
	
	private static final long serialVersionUID = 1L;
	private Notice newUpdAttObj=new Notice();
	/**
	 * 名字匹配
	 */
	private String titleMatch;
	
	private String setTopDateNull;
	public String getSetTopDateNull() {
		return setTopDateNull;
	}

	public void setSetTopDateNull(String setTopDateNull) {
		this.setTopDateNull = setTopDateNull;
	}

	public String getTitleMatch() {
		return titleMatch;
	}

	public void setTitleMatch(String titleMatch) {
		this.titleMatch = titleMatch;
	}

	public void setNewUpdAttObj(Notice newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public Notice getNewUpdAttObj() {
		return newUpdAttObj;
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}