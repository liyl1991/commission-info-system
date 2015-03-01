package cn.haohao.vas.core.tools;

import java.io.File;
import java.lang.reflect.Field;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Ibatis配置文件自动生成
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public class IbatisConfigMaker {

	@SuppressWarnings("unchecked")
	public static void make(String className, String srcPath) {
		if (className == null || className.trim().length() < 1 || srcPath == null || srcPath.trim().length() < 1) {
			throw new RuntimeException("类名与目标src路径不能为空！");
		}

		char[] ln = { '\n' };
		try {
			Class modelClass = Class.forName(className);
			String simpleName = modelClass.getSimpleName();
			StringBuffer xmlStr = new StringBuffer();
			xmlStr.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append(ln);
			xmlStr
					.append(
							"<!DOCTYPE sqlMap PUBLIC \"-//ibatis.apache.org//DTD SQL Map 2.0//EN\" \"http://ibatis.apache.org/dtd/sql-map-2.dtd\">")
					.append(ln);
			xmlStr.append("<sqlMap namespace=\"" + simpleName + "\">").append(ln).append(ln);

			xmlStr.append("<typeAlias alias=\"" + simpleName + "\" type=\"" + modelClass.getName() + "\" />")
					.append(ln).append(ln);

			// resultMap
			StringBuffer resultMap = new StringBuffer();
			resultMap.append("<resultMap id=\"" + simpleName + "Result\" class=\"" + simpleName + "\">").append(ln);

			// Insert
			StringBuffer insertStr = new StringBuffer();
			insertStr.append("<insert id=\"" + simpleName + ".create\">").append(ln);
			insertStr.append("<![CDATA[").append(ln);
			insertStr.append("insert into t_" + simpleName.toLowerCase() + " (");
			StringBuffer pamStr = new StringBuffer();

			// Update
			StringBuffer updateStr = new StringBuffer();
			updateStr.append("<update id=\"" + simpleName + ".update\">").append(ln);
			updateStr.append("<![CDATA[").append(ln);
			updateStr.append("update t_" + simpleName.toLowerCase() + " set ").append(ln);

			// Delete
			StringBuffer deleteStr = new StringBuffer();
			deleteStr.append("<delete id=\"" + simpleName + ".delete\">").append(ln);
			deleteStr.append("<![CDATA[").append(ln);
			deleteStr.append("delete from t_" + simpleName.toLowerCase() + " ").append(ln);

			// GetById
			StringBuffer getByIdStr = new StringBuffer();
			getByIdStr.append("<select id=\"" + simpleName + ".getById\" resultMap=\"" + simpleName + "Result\">")
					.append(ln);
			getByIdStr.append("<![CDATA[").append(ln);
			getByIdStr.append("select * from t_" + simpleName.toLowerCase() + " ").append(ln);

			// queryByArgs
			StringBuffer queryByArgsStr = new StringBuffer();
			queryByArgsStr.append(
					"<select id=\"" + simpleName + ".queryByArgs\" resultMap=\"" + simpleName + "Result\">").append(ln);
			queryByArgsStr.append("<![CDATA[").append(ln);
			queryByArgsStr.append("select * from t_" + simpleName.toLowerCase() + " ").append(ln);

			// pageCountByArgs
			StringBuffer pageCountByArgs = new StringBuffer();
			pageCountByArgs.append(
					"<select id=\"" + simpleName + ".pageCountByArgs\" resultMap=\"" + simpleName + "Result\">")
					.append(ln);
			pageCountByArgs.append("<![CDATA[").append(ln);
			pageCountByArgs.append("SELECT * FROM (SELECT ROWS_.*, ROWNUM ROWNUM_ FROM (").append(ln);
			pageCountByArgs.append("select * from t_" + simpleName.toLowerCase() + " ").append(ln);

			// pageCountByArgs.count
			StringBuffer pageCount = new StringBuffer();
			pageCount.append("<select id=\"" + simpleName + ".pageCountByArgs.count\" resultClass=\"java.lang.Long\">")
					.append(ln);
			pageCount.append("<![CDATA[").append(ln);
			pageCount.append("select count(1) from t_" + simpleName.toLowerCase() + " ").append(ln);

			for (Field field : modelClass.getDeclaredFields()) {

				// hasGetSetMethod
				if (!CodeMakerUtils.hasGetSetMethod(modelClass, field)) {
					continue;
				}
				String propertyName = field.getName();
				String columnName = CodeMakerUtils.convertProperty2Column(propertyName);

				resultMap.append("<result property=\"" + propertyName + "\" column=\"" + columnName + "\" />").append(
						ln);

				insertStr.append(columnName).append(",");
				pamStr.append("#").append(propertyName).append("#,");
				updateStr.append(columnName).append("=#").append(propertyName).append("#,");

				// System.out.println(propertyName);
				// System.out.println(columnName);
			}
			pamStr.deleteCharAt(pamStr.length() - 1);

			// resultMap
			resultMap.append("</resultMap>").append(ln);
			xmlStr.append(resultMap).append(ln);

			// Sequence
			xmlStr.append("<select id=\"" + simpleName + ".getSequence\" resultClass=\"java.lang.Long\">").append(ln);
			xmlStr.append("<![CDATA[").append(ln);
			xmlStr.append("select seq_" + simpleName.toLowerCase() + "_id.nextval from dual").append(ln);
			xmlStr.append("]]>").append(ln);
			xmlStr.append("</select>").append(ln).append(ln);

			// Insert
			insertStr.deleteCharAt(insertStr.length() - 1);
			insertStr.append(") ").append(ln).append("values (").append(pamStr).append(")").append(ln);
			insertStr.append("]]>").append(ln);
			insertStr.append("</insert>").append(ln);
			xmlStr.append(insertStr).append(ln);

			// Update
			updateStr.deleteCharAt(updateStr.length() - 1).append(ln);
			updateStr.append("where 1 = #1#").append(ln);
			updateStr.append("]]>").append(ln);
			updateStr.append("</update>").append(ln);
			xmlStr.append(updateStr).append(ln);

			// Delete
			deleteStr.append("where 1 = #1#").append(ln);
			deleteStr.append("]]>").append(ln);
			deleteStr.append("</delete>").append(ln);
			xmlStr.append(deleteStr).append(ln);

			// GetById
			getByIdStr.append("where 1 = #1#").append(ln);
			getByIdStr.append("]]>").append(ln);
			getByIdStr.append("</select>").append(ln);
			xmlStr.append(getByIdStr).append(ln);

			// queryByArgsStr
			// queryByArgsStr.append("where 1 = #1#").append(ln);
			queryByArgsStr.append("]]>").append(ln);
			queryByArgsStr.append("</select>").append(ln);
			xmlStr.append(queryByArgsStr).append(ln);

			// pageCountByArgs
			// pageCountByArgs.append("where 1 = #1#").append(ln);
			pageCountByArgs.append(") ROWS_ WHERE ROWNUM <= #endRowNum# ) WHERE ROWNUM_ >= #startRowNum#").append(ln);
			pageCountByArgs.append("]]>").append(ln);
			pageCountByArgs.append("</select>").append(ln);
			xmlStr.append(pageCountByArgs).append(ln);

			// pageCountByArgs.count
			// pageCount.append("where 1 = #1#").append(ln);
			pageCount.append("]]>").append(ln);
			pageCount.append("</select>").append(ln);
			xmlStr.append(pageCount).append(ln);

			xmlStr.append("</sqlMap>");
			System.out.println(xmlStr);

			String packageName = modelClass.getPackage().getName();
			String packagePath = StringUtils.join(packageName.split("\\."), "/");
			System.out.println(packagePath);
			FileUtils.forceMkdir(new File(srcPath + "/" + packagePath + "/ibatis"));

			File file = new File(srcPath + "/" + packagePath + "/ibatis/" + simpleName + ".xml");
			FileUtils.writeStringToFile(file, xmlStr.toString(), "utf-8");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * args[0]:类名全称 </p> args[1]:目标src路径
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args == null || args.length != 2) {
			throw new RuntimeException("请正确输入参数");
		}
		IbatisConfigMaker.make(args[0], args[1]);
	}
}
