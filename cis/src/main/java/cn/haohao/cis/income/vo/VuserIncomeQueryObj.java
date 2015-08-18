package cn.haohao.cis.income.vo;
//j-import-b
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import cn.haohao.vas.core.vo.IPageable;
import cn.haohao.cis.income.model.VuserIncome;
//j-import-e
/**
 *	VO
 */
public class VuserIncomeQueryObj extends VuserIncome implements IPageable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 页面大小
	 */
	private int pageSize = 10;
	/**
	 * 当前页
	 */
	private int currentPage = 1;
	
	private String nameOrIdCardLike;
	
	private String levelIn;
	
	private Integer year;
	
	private Integer month;
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
	public VuserIncomeQueryObj next() {
		this.currentPage += 1;
		return this;
	}
	public VuserIncomeQueryObj previousOrFirst() {
		if(currentPage != 1)
			this.currentPage -= 1;
		return this;
	}
	public VuserIncomeQueryObj first() {
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
	public String getNameOrIdCardLike() {
		if(StringUtils.isNotEmpty(nameOrIdCardLike))
			return "%"+this.nameOrIdCardLike+"%";
		return null;
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
	public void setYear(Integer year) {
		this.year = year;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Date getDateSearch(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		if(this.year != null && this.month != null){
			try {
				return sdf.parse(this.year + "/" + this.month + "/02");
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
		 
	}
}