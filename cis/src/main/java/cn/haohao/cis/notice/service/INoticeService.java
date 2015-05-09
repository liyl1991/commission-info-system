package cn.haohao.cis.notice.service;
//j-import-b
import java.util.List;
import org.springframework.data.domain.Page;
import cn.haohao.cis.notice.model.Notice;
import cn.haohao.cis.notice.vo.NoticeQueryObj;
import cn.haohao.cis.notice.vo.NoticeUpdateObj;
//j-import-e
/**
 *	SERVICE
 */
public interface INoticeService {
	/**
	 * u-insert-b@note1.0@
	 * u-insert-e@note1.0@
	 * @param notice
	 * @return
	 */
	public Notice createNotice(Notice notice);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param notice
	 * @return
	 */
	public Notice modifyNotice(Notice notice);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param notice
	 * @return
	 */
	public void deleteNotice(NoticeQueryObj noticeQueryObj);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param noticeList
	 * @return
	 */
	public void batchDeleteNotice(List<Notice> noticeList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param noticeId
	 * @return
	 */
	public Notice getNoticeById(Integer noticeId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param noticequeryObj
	 * @return
	 */
	public Page<Notice> pageQueryNotice(NoticeQueryObj noticequeryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param noticequeryObj
	 * @return
	 */
	public List<Notice> queryNotice(NoticeQueryObj noticequeryObj);
	/**
	 * u-insert-b@note8.0@
	 * u-insert-e@note8.0@
	 * @param pmsProblem
	 * @return
	 */
	public void updateDynamic(NoticeUpdateObj updateObj);
	/**
	 * u-insert-b@note9.0@
	 * u-insert-e@note9.0@
	 * @param pmsProblemList
	 * @return
	 */
	public Long countByArgs(NoticeQueryObj queryObj);
	
	public Page<Notice> pageQueryIsReadByArgs(NoticeQueryObj queryObj);
	
	public Notice getTopNotice();
}