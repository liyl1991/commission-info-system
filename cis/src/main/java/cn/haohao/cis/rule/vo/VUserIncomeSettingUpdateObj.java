package cn.haohao.cis.rule.vo;
//j-import-b
import cn.haohao.cis.rule.model.VUserIncomeSetting;
//j-import-e
/**
 *	VO
 */
public class VUserIncomeSettingUpdateObj extends VUserIncomeSetting {
	
	private static final long serialVersionUID = 1L;
	private VUserIncomeSetting newUpdAttObj=new VUserIncomeSetting();

	public void setNewUpdAttObj(VUserIncomeSetting newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public VUserIncomeSetting getNewUpdAttObj() {
		return newUpdAttObj;
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}