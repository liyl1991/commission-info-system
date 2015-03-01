package cn.haohao.vas.core.tools;

import java.lang.reflect.Field;

public class CodeMakerUtils {

	/**
	 * 转属性名为表字段名
	 * 
	 * @param property
	 * @return
	 */
	public static String convertProperty2Column(String property) {
		char[] pros = property.toCharArray();
		StringBuffer column = new StringBuffer();
		for (char pro : pros) {
			if (pro >= 'A' && pro < 'Z') {
				column.append("_");
				column.append(String.valueOf(pro).toLowerCase());
			} else {
				column.append(pro);
			}
		}
		return column.toString();
	}

	/**
	 * 判断某属性是否有get与set方法
	 * 
	 * @param modelClass
	 * @param field
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasGetSetMethod(Class modelClass, Field field) {
		String fieldName = field.getName();
		String upFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());

		String getMethodName = "get" + upFieldName;
		String setMethodName = "set" + upFieldName;

		try {
			if (modelClass.getMethod(getMethodName) != null
					|| modelClass.getMethod(setMethodName, field.getType()) != null) {
				return true;
			}
		} catch (Exception e) {
		}

		return false;
	}

	/**
	 * 转Model名为变量名
	 * 
	 * @param modelName
	 * @return
	 */
	public static String convertModel2Variable(String modelName) {
		String firth = modelName.substring(0, 1);
		return firth.toLowerCase() + modelName.substring(1, modelName.length());
	}
}
