package cn.haohao.vas.core.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 单用户登录
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public class SingleLoginSupporter {

	private static Map<Long, String> sessionIds = new HashMap<Long, String>();

	/**
	 * 验证SessionId的是否合法（去除重新登录）
	 * 
	 * @param operatorId
	 * @param sessionId
	 * @return
	 */
	public static boolean validSessionId(Long operatorId, String sessionId) {
		if (sessionIds.containsKey(operatorId) && sessionIds.get(operatorId).equals(sessionId)) {
			return true;
		}
		return false;
	}

	/**
	 * 合法登录时记录SessionId
	 * 
	 * @param operatorId
	 * @param sessionId
	 */
	public static void putSessionId(Long operatorId, String sessionId) {
		sessionIds.put(operatorId, sessionId);
	}

	/**
	 * Session超时时，去掉SessionId
	 * 
	 * @param operatorId
	 */
	public static void removeSessionId(Long operatorId) {
		if (sessionIds.containsKey(operatorId))
			sessionIds.remove(operatorId);
	}

}
