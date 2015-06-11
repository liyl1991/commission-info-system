package cn.haohao.cis.rule.vo;
//j-import-b
import cn.haohao.cis.rule.model.SpecialSetting;
//j-import-e
/**
 *	VO
 */
public class SpecialSettingUpdateObj extends SpecialSetting {
	
	private static final long serialVersionUID = 1L;
	private SpecialSetting newUpdAttObj=new SpecialSetting();

	public void setNewUpdAttObj(SpecialSetting newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public SpecialSetting getNewUpdAttObj() {
		return newUpdAttObj;
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}