package cn.haohao.cis.audit.vo;
//j-import-b
import cn.haohao.cis.audit.model.VauditInfo;
//j-import-e
/**
 *	VO
 */
public class VauditInfoUpdateObj extends VauditInfo {
	
	private static final long serialVersionUID = 1L;
	private VauditInfo newUpdAttObj=new VauditInfo();

	public void setNewUpdAttObj(VauditInfo newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public VauditInfo getNewUpdAttObj() {
		return newUpdAttObj;
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}