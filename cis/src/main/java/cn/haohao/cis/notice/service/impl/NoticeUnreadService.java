package cn.haohao.cis.notice.service.impl;
//j-import-b
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import cn.haohao.cis.notice.dao.INoticeUnreadDao;
import cn.haohao.cis.notice.service.INoticeUnreadService;
import cn.haohao.cis.notice.model.NoticeUnread;
import cn.haohao.cis.notice.vo.NoticeUnreadQueryObj;
import cn.haohao.cis.notice.vo.NoticeUnreadUpdateObj;
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class NoticeUnreadService implements INoticeUnreadService {
	
	@Autowired
	private INoticeUnreadDao noticeUnreadDao;
	
	public void batchDeleteNoticeUnread(List<NoticeUnread> noticeUnreadList) {
		noticeUnreadDao.batchDelete(noticeUnreadList);
	}

	public NoticeUnread createNoticeUnread(NoticeUnread noticeUnread) {
		noticeUnreadDao.create(noticeUnread);
		return noticeUnread;
	}

	public void deleteNoticeUnread(NoticeUnreadQueryObj noticeUnreadQueryObj) {
		noticeUnreadDao.delete(noticeUnreadQueryObj);
	}

	public NoticeUnread getNoticeUnreadById(Long noticeUnreadId) {
		return noticeUnreadDao.getById(noticeUnreadId);
	}

	public NoticeUnread modifyNoticeUnread(NoticeUnread noticeUnread) {
		noticeUnreadDao.update(noticeUnread);
		return noticeUnread;
	}

	public Page<NoticeUnread> pageQueryNoticeUnread(NoticeUnreadQueryObj noticeUnreadqueryObj) {
		return noticeUnreadDao.pageCountByArgs(noticeUnreadqueryObj);
	}
	
	public List<NoticeUnread> queryNoticeUnread(NoticeUnreadQueryObj noticeUnreadqueryObj) {
		return noticeUnreadDao.queryByArgs(noticeUnreadqueryObj);
	}
	
	public void updateDynamic(NoticeUnreadUpdateObj updateObj){
		noticeUnreadDao.updateDynamic(updateObj);
	}
	public Long countByArgs(NoticeUnreadQueryObj queryObj){
		return noticeUnreadDao.countByArgs(queryObj);
	}
}