package cn.haohao.cis.audit.dao.impl;
//j-import-b
import cn.haohao.cis.audit.model.VauditInfo;
import cn.haohao.cis.audit.dao.IVauditInfoDao;
import org.springframework.stereotype.Repository;
import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class VauditInfoDaoImpl extends BaseDaoMybatisImpl<VauditInfo> implements IVauditInfoDao {
	public VauditInfoDaoImpl(){
		this(VauditInfo.class);
	}
	public VauditInfoDaoImpl(Class<VauditInfo> type) {
		super(type);
	}
}