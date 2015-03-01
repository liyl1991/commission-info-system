package cn.haohao.vas.core.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果类：包括分页信息与分页的具体内容
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 分页信息
	 */
	private Page page;

	/**
	 * 具体内容
	 */
	private List<T> content;

	/**
	 * The default constructor
	 */
	public Result() {
		super();
	}

	/**
	 * The constructor using fields
	 * 
	 * @param page
	 * @param content
	 */
	public Result(Page page, List<T> content) {
		this.page = page;
		this.content = content;
	}

	/**
	 * @return Returns the content.
	 */
	public List<T> getContent() {
		return content;
	}

	/**
	 * @return Returns the page.
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param content
	 *            The content to set.
	 */
	public void setContent(List<T> content) {
		this.content = content;
	}

	/**
	 * @param page
	 *            The page to set.
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	public int size() {
		if (content == null)
			return 0;
		else
			return content.size();
	}

	public T get(int index) {
		if (content == null)
			return null;
		else
			return content.get(index);
	}
}
