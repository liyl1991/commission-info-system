package cn.haohao.vas.core.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.haohao.vas.core.model.BaseModel;
import cn.haohao.vas.core.vo.Page;
import cn.haohao.vas.core.vo.Result;

/**
 * 日志小工具
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public class LoggerUtils {

	static public StringBuffer getLogInfo(Object[] args) {
		StringBuffer logInfo = new StringBuffer();
		for (Object arg : args) {
			logInfo.append("[ ");
			logInfo.append(getLogInfo(arg));
			logInfo.append(" ],");
		}
		return logInfo;
	}

	@SuppressWarnings("unchecked")
	static public StringBuffer getLogInfo(Object arg) {
		StringBuffer logInfo = new StringBuffer();
		if (arg == null) {
			logInfo.append("null");
		} else if (arg instanceof Map) {
			logInfo.append("Map: ");
			Map map = (Map) arg;
			Set keys = map.keySet();
			for (Object key : keys) {
				logInfo.append(key);
				logInfo.append("=");
				logInfo.append(map.get(key));
				logInfo.append(";");
			}
		} else if (arg instanceof List) {
			logInfo.append("list: size=");
			logInfo.append(((List) arg).size());
		} else if (arg instanceof Page) {
			Page page = (Page) arg;
			logInfo.append("Page: ");
			logInfo.append("currentPage=");
			logInfo.append(page.getCurrentPage());
			logInfo.append(";pageSize=");
			logInfo.append(page.getPageSize());
			logInfo.append(";totailItems=");
			logInfo.append(page.getTotalItems());
			logInfo.append("");
		} else if (arg instanceof Result) {
			Result result = (Result) arg;
			logInfo.append("result: size=");
			List list = result.getContent();
			logInfo.append(list.size());
			logInfo.append("; page:");
			Page page = result.getPage();
			logInfo.append("currentPage=");
			logInfo.append(page.getCurrentPage());
			logInfo.append(";pageSize=");
			logInfo.append(page.getPageSize());
			logInfo.append(";totailItems=");
			logInfo.append(page.getTotalItems());
		} else if (arg instanceof BaseModel) {
			logInfo.append(arg.toString());
		} else {
			logInfo.append(arg.getClass().getName());
			logInfo.append(": ");
			logInfo.append(arg.toString());
		}
		return logInfo;
	}

}
