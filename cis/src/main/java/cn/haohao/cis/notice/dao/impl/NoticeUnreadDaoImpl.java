package cn.haohao.cis.notice.dao.impl;
//j-import-b
import cn.haohao.cis.notice.model.NoticeUnread;
import cn.haohao.cis.notice.dao.INoticeUnreadDao;

import org.springframework.stereotype.Repository;

import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class NoticeUnreadDaoImpl extends BaseDaoMybatisImpl<NoticeUnread> implements INoticeUnreadDao {
	public NoticeUnreadDaoImpl(){
		this(NoticeUnread.class);
	}
	public NoticeUnreadDaoImpl(Class<NoticeUnread> type) {
		super(type);
	}
	@Override
	public void createUnreadByNoticeId(Integer noticeId) {
		this.sqlSessionTemplate.insert(this.getStatementName() + ".createUnreadByNoticeId", noticeId);
	}
}