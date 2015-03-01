package cn.haohao.vas.core.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.haohao.vas.core.vo.IPageAbleObj;
import cn.haohao.vas.core.vo.Page;
import cn.haohao.vas.core.vo.Result;

/**
 * 通用Dao的ibatis实现
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public class GenericDaoIbatisImpl<T> extends SqlMapClientDaoSupport implements GenericDao<T>, FinderExecutor {

	protected Log logger = LogFactory.getLog(getClass());

	private Class<T> type;

	public GenericDaoIbatisImpl(Class<T> type) {
		this.type = type;
	}

	public Long getSequence() {
		return (Long) getSqlMapClientTemplate().queryForObject(getStatementName() + ".getSequence");
	}

	public void create(T newObj) {
		getSqlMapClientTemplate().insert(getStatementName() + ".create", newObj);
	}

	public void update(T newObj) {
		getSqlMapClientTemplate().update(getStatementName() + ".update", newObj);
	}

	public void delete(T obj) {
		getSqlMapClientTemplate().delete(getStatementName() + ".delete", obj);
	}

	public void batchCreate(List<T> objectList) {
		if (objectList != null) {
			for (T t : objectList) {
				create(t);
			}
		}
	}

	public void batchDelete(List<T> objectList) {
		if (objectList != null) {
			for (T t : objectList) {
				delete(t);
			}
		}
	}

	public void batchUpdate(List<T> objectList) {
		if (objectList != null) {
			for (T t : objectList) {
				update(t);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public T getById(Serializable objId) {
		return (T) getSqlMapClientTemplate().queryForObject(getStatementName() + ".getById", objId);
	}

	@SuppressWarnings("unchecked")
	public List<T> queryByArgs(Object queryObj) {
		if (queryObj == null)
			return getSqlMapClientTemplate().queryForList(getStatementName() + ".queryByArgs");
		else
			return getSqlMapClientTemplate().queryForList(getStatementName() + ".queryByArgs", queryObj);
	}

	@SuppressWarnings("unchecked")
	public Result<T> pageCountByArgs(IPageAbleObj queryObj) {
		Long count = (Long) getSqlMapClientTemplate().queryForObject(getStatementName() + ".pageCountByArgs.count",
				queryObj);
		List<T> list = getSqlMapClientTemplate().queryForList(getStatementName() + ".pageCountByArgs", queryObj);
		Result<T> result = new Result<T>();
		result.setContent(list);
		Page page = queryObj.getPage();
		page.setTotalItems(count);
		result.setPage(page);
		return result;
	}

	public void execDelete(Method method, Object arg) {
		getSqlMapClientTemplate().delete(getStatementName(method), arg);
	}

	public void execInsert(Method method, Object arg) {
		getSqlMapClientTemplate().insert(getStatementName(method), arg);
	}

	public void execUpdate(Method method, Object arg) {
		getSqlMapClientTemplate().update(getStatementName(method), arg);
	}

	public Object execGet(Method method, Object arg) {
		return getSqlMapClientTemplate().queryForObject(getStatementName(method), arg);
	}

	@SuppressWarnings("unchecked")
	public List<Object> execSelect(Method method, Object arg) {
		if (arg == null)
			return getSqlMapClientTemplate().queryForList(getStatementName(method));
		else
			return getSqlMapClientTemplate().queryForList(getStatementName(method), arg);
	}

	@SuppressWarnings("unchecked")
	public Result<Object> execPageCount(Method method, IPageAbleObj queryObj) {
		Long count = (Long) getSqlMapClientTemplate().queryForObject(getStatementName(method) + ".count", queryObj);
		List<Object> list = getSqlMapClientTemplate().queryForList(getStatementName(method), queryObj);
		Result<Object> result = new Result<Object>();
		result.setContent(list);
		Page page = queryObj.getPage();
		page.setTotalItems(count);
		result.setPage(page);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Object> execPage(Method method, IPageAbleObj queryObj) {
		return getSqlMapClientTemplate().queryForList(getStatementName(method), queryObj);
	}

	public Long execCount(Method method, Object arg) {
		if (arg == null)
			return (Long) getSqlMapClientTemplate().queryForObject(getStatementName(method));
		else
			return (Long) getSqlMapClientTemplate().queryForObject(getStatementName(method), arg);
	}

	/**
	 * 取类名
	 * 
	 * @return
	 */
	private String getStatementName() {
		return type.getSimpleName();
	}

	/**
	 * 
	 * @param method
	 * @return
	 */
	private String getStatementName(Method method) {
		return type.getSimpleName() + "." + method.getName();
	}

}
