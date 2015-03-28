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
	/**
	 * 等级小于
	 */
	private String levelLt;
	/**
	 * 不等于
	 */
	private String levelNotEq;
	public void setNewUpdAttObj(User newUpdAttObj) {
		this.newUpdAttObj = newUpdAttObj;
	}

	public User getNewUpdAttObj() {
		return newUpdAttObj;
	}

	public String getLevelLt() {
		return levelLt;
	}

	public void setLevelLt(String levelLt) {
		this.levelLt = levelLt;
	}

	public String getLevelNotEq() {
		return levelNotEq;
	}

	public void setLevelNotEq(String levelNotEq) {
		this.levelNotEq = levelNotEq;
	}
}