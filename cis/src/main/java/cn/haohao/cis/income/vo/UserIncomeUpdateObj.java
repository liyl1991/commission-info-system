package cn.haohao.cis.income.vo;
//j-import-b
import cn.haohao.cis.income.model.UserIncome;
//j-import-e
/**
 *	VO
 */
public class UserIncomeUpdateObj extends UserIncome {
	
	private static final long serialVersionUID = 1L;
	private UserIncome newUpdAttObj=new UserIncome();

	public void setNewUpdAttObj(UserIncome newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public UserIncome getNewUpdAttObj() {
		return newUpdAttObj;
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}