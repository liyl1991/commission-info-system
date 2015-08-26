package cn.haohao.cis.rule.vo;
import java.util.List;


//j-import-b
import cn.haohao.cis.rule.model.IncomeSetting;
//j-import-e
/**
 *	VO
 */
public class IncomeSettingUpdateObj extends IncomeSettingQueryObj {
	
	private static final long serialVersionUID = 1L;
	private IncomeSetting newUpdAttObj=new IncomeSetting();

	private List<IncomeSetting> settings;
	
	public List<IncomeSetting> getSettings() {
		return settings;
	}

	public void setSettings(List<IncomeSetting> settings) {
		this.settings = settings;
	}

	public void setNewUpdAttObj(IncomeSetting newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public IncomeSetting getNewUpdAttObj() {
		return newUpdAttObj;
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}