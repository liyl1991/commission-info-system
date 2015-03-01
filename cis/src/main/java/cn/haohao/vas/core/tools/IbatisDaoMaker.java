package cn.haohao.vas.core.tools;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Dao类自动生成
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public class IbatisDaoMaker {

	@SuppressWarnings("unchecked")
	public static void make(String className, String srcPath) {
		if (className == null || className.trim().length() < 1 || srcPath == null || srcPath.trim().length() < 1) {
			throw new RuntimeException("类名与目标src路径不能为空！");
		}

		char[] ln = { '\n' };
		try {
			Class modelClass = Class.forName(className);
			String simpleName = modelClass.getSimpleName();
			String modelPackage = modelClass.getPackage().getName();
			String daoPackage = modelPackage.substring(0, modelPackage.lastIndexOf(".")) + ".dao";

			System.out.println(modelPackage);
			System.out.println(daoPackage);

			StringBuffer javaStr = new StringBuffer();

			// package
			javaStr.append("package ").append(daoPackage).append(";").append(ln).append(ln);

			// import
			javaStr.append("import ").append(modelClass.getName()).append(";").append(ln);
			javaStr.append("import cn.haohao.vas.core.dao.GenericDao;").append(ln).append(ln);

			// interface
			javaStr.append("public interface I").append(simpleName).append("Dao extends GenericDao<")
					.append(simpleName).append("> {").append(ln).append(ln).append("}");

			System.out.println(javaStr);

			String packagePath = StringUtils.join(daoPackage.split("\\."), "/");
			System.out.println(packagePath);
			FileUtils.forceMkdir(new File(srcPath + "/" + packagePath));

			File file = new File(srcPath + "/" + packagePath + "/I" + simpleName + "Dao.java");
			FileUtils.writeStringToFile(file, javaStr.toString(), "utf-8");

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
		IbatisDaoMaker.make(args[0], args[1]);
	}
}
