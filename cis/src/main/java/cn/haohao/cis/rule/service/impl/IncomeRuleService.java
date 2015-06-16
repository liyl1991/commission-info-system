package cn.haohao.cis.rule.service.impl;
//j-import-b
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import cn.haohao.cis.rule.dao.IIncomeRuleDao;
import cn.haohao.cis.rule.service.IIncomeRuleService;
import cn.haohao.cis.rule.model.IncomeRule;
import cn.haohao.cis.rule.vo.IncomeRuleQueryObj;
import cn.haohao.cis.rule.vo.IncomeRuleUpdateObj;
//j-import-e
/**
 *	SERVICE IMPL
 */
@Service
public class IncomeRuleService implements IIncomeRuleService {
	
	@Autowired
	private IIncomeRuleDao incomeRuleDao;
	
	public void batchDeleteIncomeRule(List<IncomeRule> incomeRuleList) {
		incomeRuleDao.batchDelete(incomeRuleList);
	}

	public IncomeRule createIncomeRule(IncomeRule incomeRule) {
		incomeRuleDao.create(incomeRule);
		return incomeRule;
	}

	public void deleteIncomeRule(IncomeRuleQueryObj incomeRuleQueryObj) {
		incomeRuleDao.delete(incomeRuleQueryObj);
	}

	public IncomeRule getIncomeRuleById(Integer incomeRuleId) {
		return incomeRuleDao.getById(incomeRuleId);
	}

	public IncomeRule modifyIncomeRule(IncomeRule incomeRule) {
		incomeRuleDao.update(incomeRule);
		return incomeRule;
	}

	public Page<IncomeRule> pageQueryIncomeRule(IncomeRuleQueryObj incomeRulequeryObj) {
		return incomeRuleDao.pageCountByArgs(incomeRulequeryObj);
	}
	
	public List<IncomeRule> queryIncomeRule(IncomeRuleQueryObj incomeRulequeryObj) {
		return incomeRuleDao.queryByArgs(incomeRulequeryObj);
	}
	
	public void updateDynamic(IncomeRuleUpdateObj updateObj){
		incomeRuleDao.updateDynamic(updateObj);
	}
	public Long countByArgs(IncomeRuleQueryObj queryObj){
		return incomeRuleDao.countByArgs(queryObj);
	}
}