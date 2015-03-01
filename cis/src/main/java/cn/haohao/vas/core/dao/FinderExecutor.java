package cn.haohao.vas.core.dao;

import java.lang.reflect.Method;
import java.util.List;

import cn.haohao.vas.core.vo.IPageAbleObj;
import cn.haohao.vas.core.vo.Result;

/**
 * 查找器，过虑出相应的方法进行包装，实现：添加、修改、删除、查询。
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public interface FinderExecutor {

	/**
	 * 获取单个对象
	 * 
	 * @param method
	 *            方法
	 * @param arg
	 *            要获取的对象参数
	 * @return 返回对象
	 */
	Object execGet(Method method, Object arg);

	/**
	 * 删除操作
	 * 
	 * @param method
	 *            方法
	 * @param arg
	 *            要删除的对象参数
	 */
	void execDelete(Method method, Object arg);

	/**
	 * 修改操作
	 * 
	 * @param method
	 *            方法
	 * @param arg
	 *            要修改的对象参数
	 */
	void execUpdate(Method method, Object arg);

	/**
	 * 添加操作
	 * 
	 * @param method
	 *            方法
	 * @param arg
	 *            要添加的对象参数
	 */
	void execInsert(Method method, Object arg);

	/**
	 * 查询操作
	 * 
	 * @param method
	 *            方法
	 * @param arg
	 *            查询条件对象
	 * @return 对象列表
	 */
	List<Object> execSelect(Method method, Object arg);

	/**
	 * 翻页查询操作
	 * 
	 * @param method
	 *            方法
	 * @param queryObj
	 *            翻页查询条件对象
	 * @return 对象列表
	 */
	List<Object> execPage(Method method, IPageAbleObj queryObj);

	/**
	 * 翻页并计数查询操作
	 * 
	 * @param method
	 *            方法
	 * @param queryObj
	 *            翻页查询条件对象
	 * @return 对象结果集
	 */
	Result<Object> execPageCount(Method method, IPageAbleObj queryObj);

	/**
	 * 计数操作
	 * 
	 * @param method
	 *            方法
	 * @param arg
	 *            查询条件
	 * @return 计数结果
	 */
	Long execCount(Method method, Object arg);

}
