package cn.haohao.cis.user.vo;
//j-import-b
import cn.haohao.cis.user.model.VuserIncome;
//j-import-e
/**
 *	VO
 */
public class VuserIncomeUpdateObj extends VuserIncome {
	
	private static final long serialVersionUID = 1L;
	private VuserIncome newUpdAttObj=new VuserIncome();

	public void setNewUpdAttObj(VuserIncome newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public VuserIncome getNewUpdAttObj() {
		return newUpdAttObj;
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}