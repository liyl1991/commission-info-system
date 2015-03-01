package cn.haohao.vas.core.dao;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.IntroductionInterceptor;

import cn.haohao.vas.core.vo.IPageAbleObj;

/**
 * 过滤器
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public class FinderIntroductionInterceptor implements IntroductionInterceptor {

	protected Log logger = LogFactory.getLog(getClass());

	/**
	 * 执行方法过滤
	 * 
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		FinderExecutor executor = null;
		if(methodInvocation.getThis().getClass() == GenericDaoIbatisImpl.class){
			executor = (FinderExecutor) methodInvocation.getThis();
		}else{
			return methodInvocation.proceed();
		}
		
		Method method = methodInvocation.getMethod();
		String methodName = method.getName();

		if (isGeneral(methodName)) {
			return methodInvocation.proceed();
		}

		// 取参数
		Object[] args = methodInvocation.getArguments();
		Object ragObj = null;
		if (args != null && args.length == 1) {
			ragObj = args[0];
		}

		// 执行
		if (methodName.startsWith("get")) {
			return executor.execGet(method, ragObj);
		} else if (methodName.startsWith("create")) {
			executor.execInsert(method, ragObj);
			return null;
		} else if (methodName.startsWith("update")) {
			executor.execUpdate(method, ragObj);
			return null;
		} else if (methodName.startsWith("delete")) {
			executor.execDelete(method, ragObj);
			return null;
		} else if (methodName.startsWith("query")) {
			return executor.execSelect(method, ragObj);
		} else if (methodName.startsWith("select")) {
			return executor.execSelect(method, ragObj);
		} else if (methodName.startsWith("list")) {
			return executor.execSelect(method, ragObj);
		} else if (methodName.startsWith("pageCount")) {
			return executor.execPageCount(method, (IPageAbleObj) ragObj);
		} else if (methodName.startsWith("page")) {
			return executor.execPage(method, (IPageAbleObj) ragObj);
		} else if (methodName.startsWith("count")) {
			return executor.execCount(method, ragObj);
		} else {
			return methodInvocation.proceed();
		}
		
	}

	private boolean isGeneral(String methodName) {
		if ("getSequence".equals(methodName) || "create".equals(methodName) || "update".equals(methodName)
				|| "delete".equals(methodName) || "getById".equals(methodName) || "queryByArgs".equals(methodName)
				|| "selectByArgs".equals(methodName) || "pageCountByArgs".equals(methodName)
				|| "batchCreate".equals(methodName) || "batchUpdate".equals(methodName)
				|| "batchDelete".equals(methodName))
			return true;
		else
			return false;
	}

	@SuppressWarnings("unchecked")
	public boolean implementsInterface(Class intf) {
		return intf.isInterface() && FinderExecutor.class.isAssignableFrom(intf);
	}
}
