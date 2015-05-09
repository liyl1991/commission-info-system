package cn.haohao.cis.notice.service.impl;
//j-import-b
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import cn.haohao.cis.notice.dao.INoticeDao;
import cn.haohao.cis.notice.dao.INoticeUnreadDao;
import cn.haohao.cis.notice.service.INoticeService;
import cn.haohao.cis.notice.model.Notice;
import cn.haohao.cis.notice.vo.NoticeQueryObj;
import cn.haohao.cis.notice.vo.NoticeUpdateObj;
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class NoticeService implements INoticeService {
	
	@Autowired
	private INoticeDao noticeDao;
	@Autowired
	private INoticeUnreadDao noticeUnreadDao;
	public void batchDeleteNotice(List<Notice> noticeList) {
		noticeDao.batchDelete(noticeList);
	}

	public Notice createNotice(Notice notice) {
		noticeDao.create(notice);
		NoticeQueryObj queryObj = new NoticeQueryObj();
		BeanUtils.copyProperties(notice, queryObj, NoticeQueryObj.class);
		List<Notice> list = this.noticeDao.queryByArgs(queryObj);
		if(list.size()==1)
			this.noticeUnreadDao.createUnreadByNoticeId(list.get(0).getNoticeId());
		return notice;
	}

	public void deleteNotice(NoticeQueryObj noticeQueryObj) {
		noticeDao.delete(noticeQueryObj);
	}

	public Notice getNoticeById(Integer noticeId) {
		return noticeDao.getById(noticeId);
	}

	public Notice modifyNotice(Notice notice) {
		noticeDao.update(notice);
		return notice;
	}

	public Page<Notice> pageQueryNotice(NoticeQueryObj noticequeryObj) {
		return noticeDao.pageCountByArgs(noticequeryObj);
	}
	
	public List<Notice> queryNotice(NoticeQueryObj noticequeryObj) {
		return noticeDao.queryByArgs(noticequeryObj);
	}
	
	public void updateDynamic(NoticeUpdateObj updateObj){
		noticeDao.updateDynamic(updateObj);
	}
	public Long countByArgs(NoticeQueryObj queryObj){
		return noticeDao.countByArgs(queryObj);
	}

	@Override
	public Page<Notice> pageQueryIsReadByArgs(NoticeQueryObj queryObj) {
		return this.noticeDao.pageQueryIsReadByArgs(queryObj);
	}

	@Override
	public Notice getTopNotice() {
		return this.noticeDao.getTopNotice();
	}
}