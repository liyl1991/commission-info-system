package cn.haohao.vas.core.utils;

import org.apache.commons.lang.Validate;

/**
 * 验证小组件
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public class ValidateUtils {

	public static void notNull(Object obj) {
		if (obj == null) {
			throw new ValidateException();
		}
	}

	public static boolean isNotNull(Object obj) {
		Validate.notNull(obj);
		return obj != null;
	}
}
