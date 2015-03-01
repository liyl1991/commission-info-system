package cn.haohao.vas.datadict.vo;
//j-import-b
import cn.haohao.vas.core.vo.DataDict;
import cn.haohao.vas.core.vo.IPageAbleObj;
import cn.haohao.vas.core.vo.Page;
//j-import-e
/**
 *	VO
 */
public class DataDictQueryObj extends DataDict implements IPageAbleObj{
	
	private static final long serialVersionUID = 1L;
	private Page page=new Page();
	public Page getPage() {
		return this.page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Integer getStartRowNum() {
		return Page.getStartRowNum(page);
	}
	public Integer getEndRowNum() {
		return Page.getEndRowNum(page);
	}
//  u-insert-b@other1.0@
//  u-insert-e@other1.0@
}