package cn.haohao.cis.audit.vo;
//j-import-b
import cn.haohao.cis.audit.model.AuditInfo;
//j-import-e
/**
 *	VO
 */
public class AuditInfoUpdateObj extends AuditInfo {
	
	private static final long serialVersionUID = 1L;
	private AuditInfo newUpdAttObj=new AuditInfo();

	public void setNewUpdAttObj(AuditInfo newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public AuditInfo getNewUpdAttObj() {
		return newUpdAttObj;
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}