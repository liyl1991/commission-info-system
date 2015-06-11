package cn.haohao.cis.rule.service;
//j-import-b
import java.util.List;
import org.springframework.data.domain.Page;
import cn.haohao.cis.rule.model.IncomeRule;
import cn.haohao.cis.rule.vo.IncomeRuleQueryObj;
import cn.haohao.cis.rule.vo.IncomeRuleUpdateObj;
//j-import-e
/**
 *	SERVICE
 */
public interface IIncomeRuleService {
	/**
	 * u-insert-b@note1.0@
	 * u-insert-e@note1.0@
	 * @param incomeRule
	 * @return
	 */
	public IncomeRule createIncomeRule(IncomeRule incomeRule);
	/**
	 * u-insert-b@note2.0@
	 * u-insert-e@note2.0@
	 * @param incomeRule
	 * @return
	 */
	public IncomeRule modifyIncomeRule(IncomeRule incomeRule);
	/**
	 * u-insert-b@note3.0@
	 * u-insert-e@note3.0@
	 * @param incomeRule
	 * @return
	 */
	public void deleteIncomeRule(IncomeRuleQueryObj incomeRuleQueryObj);
	/**
	 * u-insert-b@note4.0@
	 * u-insert-e@note4.0@
	 * @param incomeRuleList
	 * @return
	 */
	public void batchDeleteIncomeRule(List<IncomeRule> incomeRuleList);
	/**
	 * u-insert-b@note5.0@
	 * u-insert-e@note5.0@
	 * @param incomeRuleId
	 * @return
	 */
	public IncomeRule getIncomeRuleById(Integer incomeRuleId);
	/**
	 * u-insert-b@note6.0@
	 * u-insert-e@note6.0@
	 * @param incomeRulequeryObj
	 * @return
	 */
	public Page<IncomeRule> pageQueryIncomeRule(IncomeRuleQueryObj incomeRulequeryObj);
	/**
	 * u-insert-b@note7.0@
	 * u-insert-e@note7.0@
	 * @param incomeRulequeryObj
	 * @return
	 */
	public List<IncomeRule> queryIncomeRule(IncomeRuleQueryObj incomeRulequeryObj);
	/**
	 * u-insert-b@note8.0@
	 * u-insert-e@note8.0@
	 * @param pmsProblem
	 * @return
	 */
	public void updateDynamic(IncomeRuleUpdateObj updateObj);
	/**
	 * u-insert-b@note9.0@
	 * u-insert-e@note9.0@
	 * @param pmsProblemList
	 * @return
	 */
	public Long countByArgs(IncomeRuleQueryObj queryObj);
}