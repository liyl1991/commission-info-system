package cn.haohao.cis.income.vo;
//j-import-b
import cn.haohao.cis.income.model.VuserFromDownlineDetail;
//j-import-e
/**
 *	VO
 */
public class VuserFromDownlineDetailUpdateObj extends VuserFromDownlineDetail {
	
	private static final long serialVersionUID = 1L;
	private VuserFromDownlineDetail newUpdAttObj=new VuserFromDownlineDetail();

	public void setNewUpdAttObj(VuserFromDownlineDetail newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public VuserFromDownlineDetail getNewUpdAttObj() {
		return newUpdAttObj;
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}