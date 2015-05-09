package cn.haohao.cis.notice.dao;
import org.springframework.data.domain.Page;

import cn.haohao.cis.notice.model.Notice;
import cn.haohao.cis.notice.vo.NoticeQueryObj;
import cn.haohao.vas.core.dao.BaseDao;
/**
 *	DAO
 *
 */
public interface INoticeDao extends BaseDao<Notice> {
	
	Page<Notice> pageQueryIsReadByArgs(NoticeQueryObj queryObj);
	Notice getTopNotice();
}