package cn.haohao.vas.core.tools;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Manager类自动生成
 * 
 * @author jevons.zheng
 * @since 2010-04-07
 * @version 1.0
 */
public class ManagerInterfaceMaker {

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
			String mgrPackage = modelPackage.substring(0, modelPackage.lastIndexOf(".")) + ".mgr";
			String voPackage = modelPackage.substring(0, modelPackage.lastIndexOf(".")) + ".vo";

			System.out.println(modelPackage);
			System.out.println(mgrPackage);

			StringBuffer javaStr = new StringBuffer();

			// package
			javaStr.append("package ").append(mgrPackage).append(";").append(ln).append(ln);

			// import
			javaStr.append("import java.util.List;").append(ln).append(ln);
			javaStr.append("import cn.haohao.vas.core.vo.Result;").append(ln);
			javaStr.append("import ").append(modelClass.getName()).append(";").append(ln);
			javaStr.append("import ").append(voPackage).append(".").append(simpleName).append("QueryObj;").append(ln)
					.append(ln);

			// class
			javaStr.append("public interface ").append("I").append(simpleName).append("Mgr {").append(ln).append(ln);

			// create
			javaStr.append("public ").append(simpleName).append(" create").append(simpleName).append("(").append(
					simpleName).append(" ").append(CodeMakerUtils.convertModel2Variable(simpleName)).append(");")
					.append(ln).append(ln);

			// modify
			javaStr.append("public ").append(simpleName).append(" modify").append(simpleName).append("(").append(
					simpleName).append(" ").append(CodeMakerUtils.convertModel2Variable(simpleName)).append(");")
					.append(ln).append(ln);

			// delete
			javaStr.append("public void").append(" delete").append(simpleName).append("(").append(simpleName).append(
					" ").append(CodeMakerUtils.convertModel2Variable(simpleName)).append(");").append(ln).append(ln);

			// batchDelete
			javaStr.append("public void").append(" batchDelete").append(simpleName).append("(List<").append(simpleName)
					.append("> ").append(CodeMakerUtils.convertModel2Variable(simpleName)).append("List);").append(ln)
					.append(ln);

			// getById
			javaStr.append("public ").append(simpleName).append(" get").append(simpleName).append("ById(Long ").append(
					CodeMakerUtils.convertModel2Variable(simpleName)).append("Id);").append(ln).append(ln);

			// query
			javaStr.append("public Result<").append(simpleName).append("> query").append(simpleName).append("(")
					.append(simpleName).append("QueryObj ").append(CodeMakerUtils.convertModel2Variable(simpleName))
					.append("queryObj);").append(ln).append(ln);

			// class end
			javaStr.append(ln).append("}");

			System.out.println(javaStr);

			String packagePath = StringUtils.join(mgrPackage.split("\\."), "/");
			System.out.println(packagePath);
			FileUtils.forceMkdir(new File(srcPath + "/" + packagePath));

			File file = new File(srcPath + "/" + packagePath + "/I" + simpleName + "Mgr.java");
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
		// ManagerInterfaceMaker.make("cn.haohao.vas.sys.model.Organ", "Y:/new_workspace/BaseApplication/sys/");
		if (args == null || args.length != 2) {
			throw new RuntimeException("请正确输入参数");
		}
		ManagerInterfaceMaker.make(args[0], args[1]);
	}
}
