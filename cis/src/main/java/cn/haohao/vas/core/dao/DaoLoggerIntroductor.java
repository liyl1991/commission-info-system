package cn.haohao.vas.core.dao;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import cn.haohao.vas.core.utils.LoggerUtils;

/**
 * 记录Dao的日志信息，log：Debug
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public class DaoLoggerIntroductor implements MethodBeforeAdvice, AfterReturningAdvice {

	protected Log logger = LogFactory.getLog(getClass());

	public void before(Method method, Object[] args, Object target) throws Throwable {
		if (logger.isDebugEnabled()) {
			StringBuffer logInfo = new StringBuffer();
			logInfo.append("Execute DaoMethod: '");
			logInfo.append(method.getName());
			logInfo.append("' . Args:{");
			// 参数信息
			logInfo.append(LoggerUtils.getLogInfo(args));
			logInfo.append(" }");
			logger.debug(logInfo.toString());
		}
	}

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		if (logger.isDebugEnabled()) {
			StringBuffer logInfo = new StringBuffer();
			logInfo.append("Success! Execute DaoMethod: '");
			logInfo.append(method.getName());
			logInfo.append("'. Return{ ");
			// 返回值信息
			logInfo.append(LoggerUtils.getLogInfo(returnValue));
			logInfo.append(" }");
			logger.debug(logInfo.toString());
		}
	}
}
