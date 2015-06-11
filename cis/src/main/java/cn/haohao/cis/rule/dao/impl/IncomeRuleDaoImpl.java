package cn.haohao.cis.rule.dao.impl;
//j-import-b
import cn.haohao.cis.rule.model.IncomeRule;
import cn.haohao.cis.rule.dao.IIncomeRuleDao;
import org.springframework.stereotype.Repository;
import cn.haohao.vas.core.dao.BaseDaoMybatisImpl;
//j-import-e
/**
 *	DAO
 */
@Repository
public class IncomeRuleDaoImpl extends BaseDaoMybatisImpl<IncomeRule> implements IIncomeRuleDao {
	public IncomeRuleDaoImpl(){
		this(IncomeRule.class);
	}
	public IncomeRuleDaoImpl(Class<IncomeRule> type) {
		super(type);
	}
}