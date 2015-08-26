package cn.haohao.cis.rule.vo;
//j-import-b
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import cn.haohao.vas.core.vo.IPageable;
import cn.haohao.cis.rule.model.IncomeSetting;
//j-import-e
/**
 *	VO
 */
public class IncomeSettingQueryObj extends IncomeSetting implements IPageable{
	
	private static final long serialVersionUID = 1L;
	private boolean usingEndDateEq = false;
	private Date effectiveDate;
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
	public IncomeSettingQueryObj next() {
		this.currentPage += 1;
		return this;
	}
	public IncomeSettingQueryObj previousOrFirst() {
		if(currentPage != 1)
			this.currentPage -= 1;
		return this;
	}
	public IncomeSettingQueryObj first() {
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
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public boolean isUsingEndDateEq() {
		return usingEndDateEq;
	}
	public void setUsingEndDateEq(boolean usingEndDateEq) {
		this.usingEndDateEq = usingEndDateEq;
	}
}