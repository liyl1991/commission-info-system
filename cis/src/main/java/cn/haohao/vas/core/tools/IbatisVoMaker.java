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
public class IbatisVoMaker {

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
			String voPackage = modelPackage.substring(0, modelPackage.lastIndexOf(".")) + ".vo";

			System.out.println(modelPackage);
			System.out.println(voPackage);

			StringBuffer javaStr = new StringBuffer();

			// package
			javaStr.append("package ").append(voPackage).append(";").append(ln).append(ln);

			// import
			javaStr.append("import cn.haohao.vas.core.vo.IPageAbleObj;").append(ln);
			javaStr.append("import cn.haohao.vas.core.vo.Page;").append(ln);
			javaStr.append("import ").append(modelClass.getName()).append(";").append(ln).append(ln);

			// class
			javaStr.append("public class ").append(simpleName).append("QueryObj extends ").append(simpleName).append(
					" implements IPageAbleObj{").append(ln).append(ln);
			javaStr.append("private static final long serialVersionUID = 1L;").append(ln);
			javaStr.append("private Page page;").append(ln);

			// getPage
			javaStr.append("public Page getPage() {").append(ln);
			javaStr.append("return this.page;").append(ln);
			javaStr.append("}").append(ln);

			// setPage
			javaStr.append("public void setPage(Page page) {").append(ln);
			javaStr.append("this.page = page;").append(ln);
			javaStr.append("}").append(ln);

			// getStartRowNum
			javaStr.append("public Integer getStartRowNum() {").append(ln);
			javaStr.append("return Page.getStartRowNum(page);").append(ln);
			javaStr.append("}").append(ln);

			// setPage
			javaStr.append("public Integer getEndRowNum() {").append(ln);
			javaStr.append("return Page.getEndRowNum(page);").append(ln);
			javaStr.append("}").append(ln);

			javaStr.append(ln).append("}");

			System.out.println(javaStr);

			String packagePath = StringUtils.join(voPackage.split("\\."), "/");
			System.out.println(packagePath);
			FileUtils.forceMkdir(new File(srcPath + "/" + packagePath));

			File file = new File(srcPath + "/" + packagePath + "/" + simpleName + "QueryObj.java");
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
		// IbatisVoMaker.make("cn.haohao.vas.sys.model.Organ", "Y:/new_workspace/BaseApplication/sys/");
		if (args == null || args.length != 2) {
			throw new RuntimeException("请正确输入参数");
		}
		IbatisVoMaker.make(args[0], args[1]);
	}
}
