package cn.haohao.vas.core.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
/**
 * 通用Dao的ibatis实现
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public class BaseDaoMybatisImpl<T> implements BaseDao<T> {

	protected Log logger = LogFactory.getLog(getClass());

	private Class<T> type;
	
	@Autowired(required = true)
	protected SqlSession sqlSessionTemplate;
	
	public BaseDaoMybatisImpl(Class<T> type) {
		this.type = type;
	}
	public Long getSequence() {
		
		return (Long) this.sqlSessionTemplate.selectOne(this.getStatementName() + ".getSequence");
		//queryForObject(this.getStatementName() + ".getSequence");
	}

	public void create(T newObj) {
		this.sqlSessionTemplate.insert(this.getStatementName() + ".create", newObj);
	}

	public void update(T newObj) {
		this.sqlSessionTemplate.update(this.getStatementName() + ".update", newObj);
	}

	public void delete(T obj) {
		this.sqlSessionTemplate.delete(this.getStatementName() + ".delete", obj);
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
		return (T) this.sqlSessionTemplate.selectOne(this.getStatementName() + ".getById", objId);
	}

	public List<T> queryByArgs(Object queryObj) {
		if (queryObj == null)
			return this.sqlSessionTemplate.selectList(this.getStatementName() + ".queryByArgs");
		else
			return this.sqlSessionTemplate.selectList(this.getStatementName() + ".queryByArgs", queryObj);
	}

	public Page<T> pageCountByArgs(Pageable queryObj) {
		Long count = (Long) this.sqlSessionTemplate.selectOne(this.getStatementName() + ".pageCountByArgs.count",
				queryObj);
		List<T> list = this.sqlSessionTemplate.selectList(this.getStatementName() + ".pageCountByArgs", queryObj);
		Page<T> page = new PageImpl<T>(list,queryObj,count);
		return page;
	}

	public void execDelete(Method method, Object arg) {
		this.sqlSessionTemplate.delete(getStatementName(method), arg);
	}

	public void execInsert(Method method, Object arg) {
		this.sqlSessionTemplate.insert(getStatementName(method), arg);
	}

	public void execUpdate(Method method, Object arg) {
		this.sqlSessionTemplate.update(getStatementName(method), arg);
	}

	public Object execGet(Method method, Object arg) {
		return this.sqlSessionTemplate.selectOne(getStatementName(method), arg);
	}
	public List<Object> execSelect(Method method, Object arg) {
		if (arg == null)
			return this.sqlSessionTemplate.selectList(getStatementName(method));
		else
			return this.sqlSessionTemplate.selectList(getStatementName(method), arg);
	}

	public Page<Object> execPageCount(Method method, Pageable queryObj) {
		Long count = (Long) this.sqlSessionTemplate.selectOne(getStatementName(method) + ".count", queryObj);
		List<Object> list = this.sqlSessionTemplate.selectList(getStatementName(method), queryObj);
		Page<Object> page = new PageImpl<Object>(list,queryObj,count);
		return page;
	}

	public List<Object> execPage(Method method, Pageable queryObj) {
		return this.sqlSessionTemplate.selectList(getStatementName(method), queryObj);
	}

	public Long execCount(Method method, Object arg) {
		if (arg == null)
			return (Long) this.sqlSessionTemplate.selectOne(getStatementName(method));
		else
			return (Long) this.sqlSessionTemplate.selectOne(getStatementName(method), arg);
	}

	/**
	 * 取类名
	 * 
	 * @return
	 */
	protected String getStatementName() {
		return type.getSimpleName();
	}

	/**
	 * 
	 * @param method
	 * @return
	 */
	protected String getStatementName(Method method) {
		return type.getSimpleName() + "." + method.getName();
	}

	public void updateDynamic(Object updateObj) {
		this.sqlSessionTemplate.update(this.getStatementName() + ".updateDynamic", updateObj);
	}

	public Long countByArgs(Object queryObj) {
		if (queryObj == null)
			return (Long) this.sqlSessionTemplate.selectOne(this.getStatementName() + ".countByArgs");
		else
			return (Long) this.sqlSessionTemplate.selectOne(this.getStatementName() + ".countByArgs", queryObj);
	}

}
