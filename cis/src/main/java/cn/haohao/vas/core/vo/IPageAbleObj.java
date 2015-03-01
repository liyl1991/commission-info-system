package cn.haohao.vas.core.vo;

/**
 * 分页查询接口
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public interface IPageAbleObj {

	public Page getPage();

	public void setPage(Page page);

	public Integer getStartRowNum();

	public Integer getEndRowNum();
}
