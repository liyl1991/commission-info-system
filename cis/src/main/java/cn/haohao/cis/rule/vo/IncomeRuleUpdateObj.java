package cn.haohao.cis.rule.vo;
//j-import-b
import cn.haohao.cis.rule.model.IncomeRule;
//j-import-e
/**
 *	VO
 */
public class IncomeRuleUpdateObj extends IncomeRule {
	
	private static final long serialVersionUID = 1L;
	private IncomeRule newUpdAttObj=new IncomeRule();

	public void setNewUpdAttObj(IncomeRule newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public IncomeRule getNewUpdAttObj() {
		return newUpdAttObj;
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}