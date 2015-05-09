package cn.haohao.cis.notice.dao.impl;
//j-import-b
import java.util.List;

import cn.haohao.cis.notice.model.Notice;
import cn.haohao.cis.notice.vo.NoticeQueryObj;
import cn.haohao.cis.notice.dao.INoticeDao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class NoticeDaoImpl extends BaseDaoMybatisImpl<Notice> implements INoticeDao {
	public NoticeDaoImpl(){
		this(Notice.class);
	}
	public NoticeDaoImpl(Class<Notice> type) {
		super(type);
	}
	@Override
	public Page<Notice> pageQueryIsReadByArgs(NoticeQueryObj queryObj) {
		Long count = (Long) this.sqlSessionTemplate.selectOne(this.getStatementName() + ".pageCountIsReadByArgs.count",
				queryObj);
		List<Notice> list = this.sqlSessionTemplate.selectList(this.getStatementName() + ".pageCountIsReadByArgs", queryObj);
		Page<Notice> page = new PageImpl<Notice>(list,queryObj,count);
		return page;
	}
	@Override
	public Notice getTopNotice() {
		return this.sqlSessionTemplate.selectOne(this.getStatementName() + ".getTopNotice");
	}
}