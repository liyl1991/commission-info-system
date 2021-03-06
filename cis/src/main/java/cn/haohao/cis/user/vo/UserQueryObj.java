package cn.haohao.cis.user.vo;
//j-import-b
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import cn.haohao.vas.core.vo.IPageable;
import cn.haohao.cis.user.model.User;
//j-import-e
/**
 *	VO
 */
public class UserQueryObj extends User implements IPageable{
	/**
	 * 等级小于
	 */
	private String levelLt;
	/**
	 * 不等于
	 */
	private String levelNotEq;
	
	private String levelNotIn;
	
	private Integer grandUserId;
	
	private String nameOrIdCardLike;
	private String levelIn;
	private static final long serialVersionUID = 1L;
	/**
	 * 页面大小
	 */
	private int pageSize = 10;
	/**
	 * 当前页
	 */
	private int currentPage = 1;
	/**
	 * 排序 
	 */
	private Sort sort;
	public Integer getStartRowNum() {
		if (currentPage < 1)
			currentPage = 1;
		return (currentPage - 1) * pageSize;
	}
	public Integer getEndRowNum() {
		if (currentPage < 1)
			currentPage = 1;
		return (currentPage) * pageSize;
	}
	public int getPageNumber() {
		return this.currentPage;
	}
	public int getPageSize() {
		return this.pageSize;
	}
	public int getOffset() {
		return currentPage*pageSize;
	}
	public Sort getSort() {
		return this.sort;
	}
	public UserQueryObj next() {
		this.currentPage += 1;
		return this;
	}
	public UserQueryObj previousOrFirst() {
		if(currentPage != 1)
			this.currentPage -= 1;
		return this;
	}
	public UserQueryObj first() {
		this.currentPage = 1;
		return this;
	}
	public boolean hasPrevious() {
		return this.currentPage>1;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setSort(Sort sort){
		this.sort = sort;
	}
	public void setSort(List<Order> orders){
		this.sort = new Sort(orders);
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
	public Integer getGrandUserId() {
		return grandUserId;
	}
	public void setGrandUserId(Integer grandUserId) {
		this.grandUserId = grandUserId;
	}
	public String getLevelNotIn() {
		return levelNotIn;
	}
	public void setLevelNotIn(String levelNotIn) {
		this.levelNotIn = levelNotIn;
	}
	public String getNameOrIdCardLike() {
		if(this.nameOrIdCardLike == null)
			return null;
		return "%"+nameOrIdCardLike+"%";
	}
	public void setNameOrIdCardLike(String nameOrIdCardLike) {
		this.nameOrIdCardLike = nameOrIdCardLike;
	}
	public List<String> getLevelIn() {
		if( this.levelIn != null && this.levelIn.trim().length() !=0){
			List<String> list = new ArrayList<String>();
			for (String lv : this.levelIn.split(",")) {
				list.add(lv);
			}
			return list;
		}
		return null;
	}
	public void setLevelIn(String levelIn) {
		this.levelIn = levelIn;
	}
	
}