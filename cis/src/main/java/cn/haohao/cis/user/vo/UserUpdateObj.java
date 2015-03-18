package cn.haohao.cis.user.vo;
//j-import-b
import cn.haohao.cis.user.model.User;
//j-import-e
/**
 *	VO
 */
public class UserUpdateObj extends User {
	
	private static final long serialVersionUID = 1L;
	private User newUpdAttObj=new User();

	public void setNewUpdAttObj(User newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public User getNewUpdAttObj() {
		return newUpdAttObj;
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}