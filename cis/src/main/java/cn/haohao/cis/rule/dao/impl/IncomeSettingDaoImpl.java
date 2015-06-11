package cn.haohao.cis.rule.dao.impl;
//j-import-b
import cn.haohao.cis.rule.model.IncomeSetting;
import cn.haohao.cis.rule.dao.IIncomeSettingDao;
import org.springframework.stereotype.Repository;
import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class IncomeSettingDaoImpl extends BaseDaoMybatisImpl<IncomeSetting> implements IIncomeSettingDao {
	public IncomeSettingDaoImpl(){
		this(IncomeSetting.class);
	}
	public IncomeSettingDaoImpl(Class<IncomeSetting> type) {
		super(type);
	}
}