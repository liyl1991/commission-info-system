package cn.haohao.vas.core.dao;

import java.io.Serializable;
import java.util.List;

import cn.haohao.vas.core.vo.IPageAbleObj;
import cn.haohao.vas.core.vo.Result;

/**
 * 通用Dao接口
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public interface GenericDao<T> {

	/**
	 * 取序列
	 * 
	 * @return 序列值
	 */
	Long getSequence();

	/**
	 * 添加对象
	 * 
	 * @param newObj
	 *            添加对象
	 */
	void create(T newObj);

	/**
	 * 修改对象
	 * 
	 * @param newObj
	 *            修改对象
	 */
	void update(T newObj);

	/**
	 * 删除对象
	 * 
	 * @param obj
	 *            删除对象
	 */
	void delete(T obj);

	/**
	 * 批量添加
	 * 
	 * @param objectList
	 */
	void batchCreate(List<T> objectList);

	/**
	 * 批量更新
	 * 
	 * @param objectList
	 */
	void batchUpdate(List<T> objectList);

	/**
	 * 批量删除
	 * 
	 * @param objectList
	 */
	void batchDelete(List<T> objectList);

	/**
	 * 读取对象
	 * 
	 * @param objId
	 *            对象标识
	 * @return 对象
	 */
	T getById(Serializable objId);

	/**
	 * 条件查询
	 * 
	 * @param queryObj
	 *            查询条件
	 * @return 对象列表
	 */
	List<T> queryByArgs(Object queryObj);

	/**
	 * 翻页查询
	 * 
	 * @param queryObj
	 *            查询条件
	 * @return 对象列表
	 */
	Result<T> pageCountByArgs(IPageAbleObj queryObj);

}
