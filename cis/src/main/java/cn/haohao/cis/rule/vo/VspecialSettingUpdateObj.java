package cn.haohao.cis.rule.vo;
//j-import-b
import cn.haohao.cis.rule.model.VspecialSetting;
//j-import-e
/**
 *	VO
 */
public class VspecialSettingUpdateObj extends VspecialSetting {
	
	private static final long serialVersionUID = 1L;
	private VspecialSetting newUpdAttObj=new VspecialSetting();

	public void setNewUpdAttObj(VspecialSetting newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public VspecialSetting getNewUpdAttObj() {
		return newUpdAttObj;
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}