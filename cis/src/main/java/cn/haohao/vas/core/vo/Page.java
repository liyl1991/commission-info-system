package cn.haohao.vas.core.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 分页对象. 包含数据及分页信息. 参考自springside
 * 
 * @author chenjp
 */
public class Page implements java.io.Serializable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 页面大小
	 */
	private int pageSize = 20;

	/**
	 * 记录总数
	 */
	private long totalItems = 0L;

	/**
	 * 当前页
	 */
	private int currentPage = 1;

	public Page() {

	}

	/**
	 * construct the page by everyPage
	 * 
	 * @param everyPage
	 */
	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	public Page(int pageSize, int currentPage) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
	}

	/** The whole constructor */
	public Page(int pageSize, long totalItems, int currentPage) {
		this.pageSize = pageSize;
		this.totalItems = totalItems;
		this.currentPage = currentPage;
	}

	public int getBeginIndex() {
		if (currentPage < 1)
			currentPage = 1;
		return (currentPage - 1) * pageSize + 1;
	}

	public int getEndIndex() {
		if (currentPage < 1)
			currentPage = 1;
		return (currentPage) * pageSize;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		if (totalItems == 0 || pageSize == 0)
			return 1;
		else
			return (int) ((totalItems + pageSize - 1) / pageSize);
	}

	/**
	 * @return the totalItems
	 */
	public long getTotalItems() {
		return totalItems;
	}

	/**
	 * @param totalItems
	 *            the totalItems to set
	 */
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}

	public static Integer getStartRowNum(Page page) {
		if (page == null)
			page = new Page();
		return page.getBeginIndex();
	}

	public static Integer getEndRowNum(Page page) {
		if (page == null)
			page = new Page();
		return page.getEndIndex();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("page size:", getPageSize()).append("current page", getCurrentPage())
				.append("total items:" + getTotalItems()).toString();
	}
}
