package cn.haohao.cis.rule.vo;
//j-import-b
import cn.haohao.cis.rule.model.ReachSetting;
//j-import-e
/**
 *	VO
 */
public class ReachSettingUpdateObj extends ReachSettingQueryObj {
	
	private static final long serialVersionUID = 1L;
	private ReachSetting newUpdAttObj=new ReachSetting();

	public void setNewUpdAttObj(ReachSetting newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public ReachSetting getNewUpdAttObj() {
		return newUpdAttObj;
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}