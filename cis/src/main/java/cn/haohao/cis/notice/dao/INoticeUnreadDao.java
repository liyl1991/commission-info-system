package cn.haohao.cis.notice.dao;
import cn.haohao.cis.notice.model.NoticeUnread;
import cn.haohao.vas.core.dao.BaseDao;
/**
 *	DAO
 *
 */
public interface INoticeUnreadDao extends BaseDao<NoticeUnread> {
	public void createUnreadByNoticeId(Integer noticeId);
}