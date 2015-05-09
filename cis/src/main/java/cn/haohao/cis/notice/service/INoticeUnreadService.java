package cn.haohao.cis.notice.service;
//j-import-b
import java.util.List;
import org.springframework.data.domain.Page;
import cn.haohao.cis.notice.model.NoticeUnread;
import cn.haohao.cis.notice.vo.NoticeUnreadQueryObj;
import cn.haohao.cis.notice.vo.NoticeUnreadUpdateObj;
//j-import-e
/**
 *	SERVICE
 */
public interface INoticeUnreadService {
	/**
	 * u-insert-b@note1.0@
	 * u-insert-e@note1.0@
	 * @param noticeUnread
	 * @return
	 */
	public NoticeUnread createNoticeUnread(NoticeUnread noticeUnread);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param noticeUnread
	 * @return
	 */
	public NoticeUnread modifyNoticeUnread(NoticeUnread noticeUnread);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param noticeUnread
	 * @return
	 */
	public void deleteNoticeUnread(NoticeUnreadQueryObj noticeUnreadQueryObj);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param noticeUnreadList
	 * @return
	 */
	public void batchDeleteNoticeUnread(List<NoticeUnread> noticeUnreadList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param noticeUnreadId
	 * @return
	 */
	public NoticeUnread getNoticeUnreadById(Long noticeUnreadId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param noticeUnreadqueryObj
	 * @return
	 */
	public Page<NoticeUnread> pageQueryNoticeUnread(NoticeUnreadQueryObj noticeUnreadqueryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param noticeUnreadqueryObj
	 * @return
	 */
	public List<NoticeUnread> queryNoticeUnread(NoticeUnreadQueryObj noticeUnreadqueryObj);
	/**
	 * u-insert-b@note8.0@
	 * u-insert-e@note8.0@
	 * @param pmsProblem
	 * @return
	 */
	public void updateDynamic(NoticeUnreadUpdateObj updateObj);
	/**
	 * u-insert-b@note9.0@
	 * u-insert-e@note9.0@
	 * @param pmsProblemList
	 * @return
	 */
	public Long countByArgs(NoticeUnreadQueryObj queryObj);
}