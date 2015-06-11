package cn.haohao.cis.rule.dao.impl;
//j-import-b
import cn.haohao.cis.rule.model.ReachSetting;
import cn.haohao.cis.rule.dao.IReachSettingDao;
import org.springframework.stereotype.Repository;
import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class ReachSettingDaoImpl extends BaseDaoMybatisImpl<ReachSetting> implements IReachSettingDao {
	public ReachSettingDaoImpl(){
		this(ReachSetting.class);
	}
	public ReachSettingDaoImpl(Class<ReachSetting> type) {
		super(type);
	}
}