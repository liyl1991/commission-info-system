package cn.haohao.vas.core.vo;

import java.io.Serializable;

/**
 * 分页查询对象
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public class PageQueryObj implements IPageAbleObj, Serializable {

	private static final long serialVersionUID = 1L;

	protected Page page;

	public PageQueryObj() {
	}

	public PageQueryObj(Page page) {
		this.page = page;
	}

	public Integer getStartRowNum() {
		return Page.getStartRowNum(page);
	}

	public Integer getEndRowNum() {
		return Page.getEndRowNum(page);
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
