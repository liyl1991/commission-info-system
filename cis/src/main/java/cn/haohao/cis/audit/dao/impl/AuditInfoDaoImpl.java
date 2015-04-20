package cn.haohao.cis.audit.dao.impl;
//j-import-b
import cn.haohao.cis.audit.model.AuditInfo;
import cn.haohao.cis.audit.dao.IAuditInfoDao;
import org.springframework.stereotype.Repository;
import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class AuditInfoDaoImpl extends BaseDaoMybatisImpl<AuditInfo> implements IAuditInfoDao {
	public AuditInfoDaoImpl(){
		this(AuditInfo.class);
	}
	public AuditInfoDaoImpl(Class<AuditInfo> type) {
		super(type);
	}
}