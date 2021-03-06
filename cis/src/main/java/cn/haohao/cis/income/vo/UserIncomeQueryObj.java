package cn.haohao.cis.income.vo;
//j-import-b
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import cn.haohao.vas.core.vo.IPageable;
import cn.haohao.cis.income.model.UserIncome;
//j-import-e
/**
 *	VO
 */
public class UserIncomeQueryObj extends UserIncome implements IPageable{
	
	private static final long serialVersionUID = 1L;
	private Integer yearEq;
	private Integer monthEq;
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
	public UserIncomeQueryObj next() {
		this.currentPage += 1;
		return this;
	}
	public UserIncomeQueryObj previousOrFirst() {
		if(currentPage != 1)
			this.currentPage -= 1;
		return this;
	}
	public UserIncomeQueryObj first() {
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
	public Integer getYearEq() {
		return yearEq;
	}
	public void setYearEq(Integer yearEq) {
		this.yearEq = yearEq;
	}
	public Integer getMonthEq() {
		return monthEq;
	}
	public void setMonthEq(Integer monthEq) {
		this.monthEq = monthEq;
	}
	
}